package org.ussa.beans;

public class PaymentBean
{
	private String amount;
	private String cardNumber;
	private String expireMonth;
	private String expireYear;
	private String securityCode;

	private String completedTransactionId;

	public String getAmount()
	{
		return amount;
	}

	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	public String getCardNumber()
	{
		return cardNumber;
	}

	public void setCardNumber(String cardNumber)
	{
		this.cardNumber = cardNumber;
	}

	public String getExpireMonth()
	{
		return expireMonth;
	}

	public void setExpireMonth(String expireMonth)
	{
		this.expireMonth = expireMonth;
	}

	public String getExpireYear()
	{
		return expireYear;
	}

	public void setExpireYear(String expireYear)
	{
		this.expireYear = expireYear;
	}

	public String getSecurityCode()
	{
		return securityCode;
	}

	public void setSecurityCode(String securityCode)
	{
		this.securityCode = securityCode;
	}

	public String getCompletedTransactionId()
	{
		return completedTransactionId;
	}

	public void setCompletedTransactionId(String completedTransactionId)
	{
		this.completedTransactionId = completedTransactionId;
	}
}
