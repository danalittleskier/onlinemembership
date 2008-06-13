package org.ussa.service.impl;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.ussa.beans.AccountBean;
import org.ussa.beans.PaymentBean;
import org.ussa.model.Address;
import org.ussa.model.Member;
import org.ussa.service.CreditCardProcessingService;
import org.ussa.exception.CreditCardException;
import org.ussa.exception.CreditCardDeclinedException;

public class AuthorizeNetCreditCardProcessingServiceImpl implements CreditCardProcessingService
{

	private static String DELIMITER = "|";

	private String login;
	private String tranKey;
	private boolean testMode;

	private static Log log = LogFactory.getLog(AuthorizeNetCreditCardProcessingServiceImpl.class);

	public void processCard(AccountBean accountBean) throws Exception
	{
		PaymentBean paymentBean = accountBean.getPaymentBean();
		GatewayResponse response = sendGatewayRequest(accountBean);

		paymentBean.setCompletedTransactionId(String.valueOf(response.getTransId()));

		if (response.getResponseCode() == GatewayResponse.RESPONSE_CODE_DECLINED)
		{
			log.debug(response.toString());
			log.warn("CREDIT CARD DECLINE ERROR: " + response.getResponseDescription());
			log.warn("RESPONSE REASON CODE = " + response.getResponseReasonCode());
			throw new CreditCardDeclinedException(response.getResponseDescription());
		}

		if (response.getResponseCode() != GatewayResponse.RESPONSE_CODE_APPROVE){
			log.warn("CREDIT CARD DECLINE ERROR: " + response.getResponseDescription());
			log.warn("RESPONSE REASON CODE = " + response.getResponseReasonCode());
			throw new CreditCardException(response.getResponseDescription());
		}
	}

	private GatewayResponse sendGatewayRequest(AccountBean accountBean) throws Exception
	{
		PaymentBean paymentBean = accountBean.getPaymentBean();

		StringBuffer params = new StringBuffer();

		// mandatory name/value pairs for all AIM CC transactions
		// as well as some "good to have" values
		params.append("x_login=").append(login).append("&");
		params.append("x_tran_key=").append(tranKey).append("&");
		params.append("x_version=3.1&");
		params.append("x_test_request=").append(testMode ? "TRUE" : "FALSE").append("&");
		params.append("x_method=CC&");
		params.append("x_type=AUTH_CAPTURE&");
		params.append("x_delim_data=TRUE&");
		params.append("x_delim_char=").append(DELIMITER).append("&");
		params.append("x_relay_response=FALSE&");

		Member member = accountBean.getMember();
		Address address = accountBean.getAddress();
		// address values. these are not optional
		appendParam(params, "x_first_name", member.getFirstName(), 50);
		appendParam(params, "x_last_name", member.getLastName(), 50);
		appendParam(params, "x_address", paymentBean.getAddress(), 60);
		appendParam(params, "x_email", member.getEmail(), 60);
//		appendParam(params, "x_city", address.getCity(), 40);
//		appendParam(params, "x_state", address.getStateCode(), 40);
		appendParam(params, "x_zip", paymentBean.getZip(), 20);
		if(member.getId() != null)
			appendParam(params, "x_invoice_num", Long.toString(member.getId()));

//		appendParam(params, "x_country", address.getCountry(), 60);
//		appendParam(params, "x_phone", address.getPhoneHome(), 25);

		// cc values
		appendParam(params, "x_amount", accountBean.getCartBean().getTotal().toString());
		appendParam(params, "x_card_num", paymentBean.getCardNumber(), 22);
		appendParam(params, "x_exp_date", paymentBean.getExpireMonth() + paymentBean.getExpireYear());
		appendParam(params, "x_card_code", paymentBean.getSecurityCode(), 6);
		appendParam(params, "x_duplicate_window", "0", 5);

		// not required...but my testMode account is set up to require it
		appendParam(params, "x_description", "USSA Membership");

		if (log.isDebugEnabled()) log.debug("Gateway Request: " + params.toString());

		// open secure connection
		URL url = new URL("https://secure.authorize.net/gateway/transact.dll");

		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		connection.setUseCaches(false);

		// not necessarily required but fixes a bug with some servers
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		// POST the data in the string buffer
		DataOutputStream out = null;
		try
		{
			out = new DataOutputStream(connection.getOutputStream());
			out.write(params.toString().getBytes());
			out.flush();
		}
		finally
		{
			if (out != null)
			{
				out.close();
			}
		}

		// process and read the gateway response
		BufferedReader in = null;
		GatewayResponse response = null;
		String line;
		try
		{
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			line = in.readLine();
			if (log.isDebugEnabled()) log.debug("Gateway Response: " + line);
			response = new GatewayResponse(line);
		}
		finally
		{
			if (in != null)
			{
				in.close();
			}
		}

		if (log.isDebugEnabled())
		{
			log.debug("Human Readable GatewayResponse Code: " + response.getResponseDescription());
			log.debug("GatewayResponse Code: " + response.getResponseCode());
//    log.debug("GatewayResponse SubCode: " + response.getResponseSubCode());
//    log.debug("GatewayResponse Reason Code: " + response.getResponseReasonCode());
//    log.debug("Approval Code: " + response.getApprovalCode());
//    log.debug("Avs Result Code: " + response.getAvsResultCode());
			log.debug("Trans ID: " + response.getTransId());
		}

		return response;
	}

	private void appendParam(StringBuffer buffer, String paramName, String paramValue)
	{
		appendParam(buffer, paramName, paramValue, 0);
	}

	private void appendParam(StringBuffer buffer, String paramName, String paramValue, int maxLength)
	{
		buffer.append(paramName).append("=").append(prepareString(paramValue, maxLength)).append("&");
	}

	private String prepareString(String value, int maxLength)
	{
		if (StringUtils.isNotEmpty(value))
		{
			if (maxLength > 0 && value.length() > maxLength)
				value = value.substring(0, maxLength - 1);

			value = StringUtils.replace(value, "&", "");
		}

		return value;
	}

	private static class GatewayResponse
	{

		static long RESPONSE_CODE_APPROVE = 1;
		static long RESPONSE_CODE_DECLINED = 2;
		static long RESPONSE_CODE_ERROR = 3;
		static long RESPONSE_HELD_FOR_REVIEW = 4;

		private long responseCode;
		private long responseSubCode;
		private long responseReasonCode;
		private String responseDescription;
		private String approvalCode;
		private String avsResultCode;
		private long transId;

		GatewayResponse(String responseString)
		{
			List<String> splitResponse = split(DELIMITER, responseString);
			responseCode = Long.parseLong(splitResponse.get(0));
			responseSubCode = Long.parseLong(splitResponse.get(1));
			responseReasonCode = Long.parseLong(splitResponse.get(2));
			responseDescription = splitResponse.get(3);
			approvalCode = splitResponse.get(4);
			avsResultCode = splitResponse.get(5);
			transId = Long.parseLong(splitResponse.get(6));
		}

		long getResponseCode()
		{
			return responseCode;
		}

		long getResponseSubCode()
		{
			return responseSubCode;
		}

		long getResponseReasonCode()
		{
			return responseReasonCode;
		}

		String getResponseDescription()
		{
			return responseDescription;
		}

		String getApprovalCode()
		{
			return approvalCode;
		}

		String getAvsResultCode()
		{
			return avsResultCode;
		}

		long getTransId()
		{
			return transId;
		}

		// utility functions
		private List<String> split(String pattern, String in)
		{
			int s1 = 0, s2;
			List<String> out = new ArrayList<String>(30);
			while (true)
			{
				s2 = in.indexOf(pattern, s1);
				if (s2 != -1)
				{
					out.add(in.substring(s1, s2));
				}
				else
				{
					//the end part of the string (string not pattern terminated)
					String _ = in.substring(s1);
					if (_ != null && !_.equals(""))
					{
						out.add(_);
					}
					break;
				}
				s1 = s2;
				s1 += pattern.length();
			}
			return out;
		}

		public String toString()
		{
			return getResponseDescription() + " response_code: " + getResponseCode() + ", response_subcode: " + getResponseSubCode() + ", reason_code: " + getResponseReasonCode();
		}
	}

	public String getLogin()
	{
		return login;
	}

	public void setLogin(String login)
	{
		this.login = login;
	}

	public String getTranKey()
	{
		return tranKey;
	}

	public void setTranKey(String tranKey)
	{
		this.tranKey = tranKey;
	}

	public boolean isTestMode()
	{
		return testMode;
	}

	public void setTestMode(boolean testMode)
	{
		this.testMode = testMode;
	}
}

