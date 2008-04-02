package org.ussa.beans;

public class PaymentBean
{
	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private String state;
	private String zip;
	private String amount;
	private String cardNumber;
	private String expireMonth;
	private String expireYear;
	private String securityCode;

	private String completedTransactionId;

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getState()
	{
		return state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getZip()
	{
		return zip;
	}

	public void setZip(String zip)
	{
		this.zip = zip;
	}

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
