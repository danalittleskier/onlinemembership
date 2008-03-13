package org.ussa.spring.flows.registration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.context.servlet.ServletExternalContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.ussa.beans.AccountBean;
import org.ussa.beans.CartBean;
import org.ussa.dao.AddressDao;
import org.ussa.dao.ClubDao;
import org.ussa.dao.MemberDao;
import org.ussa.dao.StateDao;
import org.ussa.dao.InventoryDao;
import org.ussa.manager.MemberManager;
import org.ussa.model.Address;
import org.ussa.model.AddressPk;
import org.ussa.model.Club;
import org.ussa.model.Member;
import org.ussa.model.State;
import org.ussa.model.Inventory;

// wells fargo imports
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URL;
import java.security.*;
import java.util.*;
import javax.net.ssl.*;
import java.io.*;

public class PaymentAction extends MultiAction implements Serializable {

	private MemberManager memberManager;
    private MemberDao memberDao;
    private AddressDao addressDao;
    private StateDao stateDao;
    private ClubDao clubDao;
    private InventoryDao inventoryDao;

    String responseCode;

    public Event processOrder(RequestContext context) throws Exception
    {
        HttpServletRequest request = ((ServletExternalContext)context.getExternalContext()).getRequest();
        AccountBean obj = (AccountBean) context.getFlowScope().get("accountBean");


        String fname = context.getRequestParameters().get("fname");
        String lname = context.getRequestParameters().get("lname");
        String address = context.getRequestParameters().get("address");
        String city = context.getRequestParameters().get("city");
        String state = context.getRequestParameters().get("state");
        String zip = context.getRequestParameters().get("zip");
        String country = context.getRequestParameters().get("country");
        String month = context.getRequestParameters().get("month");
        String year = context.getRequestParameters().get("year");
        String cardNum = context.getRequestParameters().get("ccnum");
        String amount = context.getRequestParameters().get("amount");
        System.out.println("test output process Order" + fname);
        System.out.println("test output process Order" + lname);
        System.out.println("test output process Order" + address);
        System.out.println("test output process Order" + city);
        System.out.println("test output process Order" + state);
        System.out.println("test output process Order" + zip);
        System.out.println("test output process Order" + country);
        System.out.println("test output process Order" + month);
        System.out.println("test output process Order" + year);
        System.out.println("test output process Order" + cardNum);


        // wells fargo registration payment

        try{
            StringBuffer sb = new StringBuffer();

            // mandatory name/value pairs for all AIM CC transactions
            // as well as some "good to have" values
            sb.append("x_login=3M7s8eKK6&");
            // recommendation for USSA dev:  It is strongly recommended that the merchant periodically change the transaction key.
            sb.append("x_tran_key=7u86RL9PM4s4aRyF&");
            // This transaction key should be changed every x months
            sb.append("x_version=3.1&");
            sb.append("x_test_request=TRUE&");             // for testing
            sb.append("x_method=CC&");
            sb.append("x_type=AUTH_CAPTURE&");
            //sb.append("x_amount=" + paymentBean.getAmount() + ".00&");
            sb.append("x_amount="+amount+"&");
            sb.append("x_delim_data=TRUE&");
            sb.append("x_delim_char=|&");
            sb.append("x_relay_response=FALSE&");

            // CC information - approved credit card test numbers
            //370000000000002       American Express
            //6011000000000012      Discover
            //5424000000000015      MasterCard
            //4007000000027         Visa
            sb.append("x_card_num=" + cardNum + "&");
            sb.append("x_exp_date=" + month + year + "&");
            //sb.append("x_card_code=225&");

            // Cardholder's Information
            sb.append("x_first_name=" + fname + "&");
            sb.append("x_last_name=" + lname + "&");
            sb.append("x_address=" + address + "&");
            sb.append("x_city=" + city + "&");
            sb.append("x_state=" + state + "&");
            sb.append("x_zip=" + zip + "&");
            sb.append("x_country=" + "usa" + "&");
            //sb.append("x_phone=(435)647-2040&");
            //sb.append("x_email=shaas@ussa.org&");
            //sb.append("x_customer_ip=255.255.255.255&");

            // not required...but my test account is set up to require it
            sb.append("x_description=Java Transaction&");

            // recommendation - possibly gather client information for storage
            // recommendation - x_customer_ip for fraud detection system

            // recommendation - we can also add x_duplicate_window - this is check for a duplicate process - may be handy to avoid double click payment


            // open secure connection
            // add error handling if this url is down ***************************
            URL url = new URL(
                    "https://certification.authorize.net/gateway/transact.dll");
            //  Uncomment the line ABOVE for test accounts or BELOW for live merchant accounts
            //   https://secure.authorize.net/gateway/transact.dll

      /* NOTE: If you want to use SSL-specific features,change to:
          HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
       */

            URLConnection connection = url.openConnection();
            connection.setDoOutput(true);
            connection.setUseCaches(false);

            // not necessarily required but fixes a bug with some servers
            connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            // POST the data in the string buffer
            DataOutputStream out = new DataOutputStream( connection.getOutputStream() );
            out.write(sb.toString().getBytes());
            out.flush();
            out.close();

            // process and read the gateway response
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            line = in.readLine();
            in.close();	                     // no more data
            System.err.println(line);




            // ONLY FOR THOSE WHO WANT TO CAPTURE GATEWAY RESPONSE INFORMATION
            // make the reply readable (be sure to use the x_delim_char for the split operation)
            Vector ccrep = split("|", line);


            System.out.print("Response Code: ");
            System.out.println(ccrep.elementAt(0));
            responseCode = ccrep.elementAt(0).toString();
            System.out.print("Human Readable Response Code: ");
            System.out.println(ccrep.elementAt(3));
            System.out.print("Approval Code: ");
            System.out.println(ccrep.elementAt(4));
            System.out.print("Trans ID: ");
            System.out.println(ccrep.elementAt(6));
            System.out.print("MD5 Hash Server: ");
            System.out.println(ccrep.elementAt(37));

            // set transaction code
            if(Integer.parseInt(responseCode) > 1) {

            	obj.setPaymentTransactionCode(ccrep.elementAt(3).toString());
            }

            else {
            	obj.setPaymentTransactionCode("success");
            }


            System.out.println("transaction code= " + obj.getPaymentTransactionCode());

        }catch(Exception e)
        {
        	//obj.setPaymentTransactionCode("failure");
        	//System.out.println("transaction failure code= " + obj.getPaymentTransactionCode());
        	e.printStackTrace();

        }


        return result("form");
    }

public static Vector split(String pattern, String in)

    {
        int s1=0, s2=-1;
        Vector out = new Vector(30);
        while(true){
            s2 = in.indexOf(pattern, s1);
            if(s2 != -1){
                out.addElement(in.substring(s1, s2));
            }else{
                //the end part of the string (string not pattern terminated)
                String _ = in.substring(s1);
                if(_ != null && !_.equals("")){
                    out.addElement(_);
                }
                break;
            }
            s1 = s2;
            s1 += pattern.length();
        }
        return out;
    }

public MemberManager getMemberManager() {
	return memberManager;
}

public void setMemberManager(MemberManager memberManager) {
	this.memberManager = memberManager;
}

public MemberDao getMemberDao() {
	return memberDao;
}

public void setMemberDao(MemberDao memberDao) {
	this.memberDao = memberDao;
}

public AddressDao getAddressDao() {
	return addressDao;
}

public void setAddressDao(AddressDao addressDao) {
	this.addressDao = addressDao;
}

public StateDao getStateDao() {
	return stateDao;
}

public void setStateDao(StateDao stateDao) {
	this.stateDao = stateDao;
}

public ClubDao getClubDao() {
	return clubDao;
}

public void setClubDao(ClubDao clubDao) {
	this.clubDao = clubDao;
}

public InventoryDao getInventoryDao() {
	return inventoryDao;
}

public void setInventoryDao(InventoryDao inventoryDao) {
	this.inventoryDao = inventoryDao;
}


}
