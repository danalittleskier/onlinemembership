package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


@Entity
@Table(name = "MEMBERADDRESS")

public class Address implements Serializable
{
	public static final String ADDRESS_TYPE_PRIMARY = "P";
	public static final String ADDRESS_TYPE_SHIPPING = "S";
	public static final String ADDRESS_TYPE_WINTER = "W";

	@EmbeddedId
	private AddressPk addressPk;

	@Column(name = "COMPANY", nullable = true, length = 40, unique = false)
	private String company;

	@Column(name = "DELIVERY_ADDRESS", nullable = true, length = 40, unique = false)
	private String deliveryAddress;

	@Column(name = "SECONDARY_ADDRESS", nullable = true, length = 40, unique = false)
	private String secondaryAddress;

	@Column(name = "CITY", nullable = true, length = 30, unique = false)
	private String city;

	@Column(name = "STATE_CODE", nullable = true, length = 2, unique = false)
	private String stateCode;

	@Column(name = "POSTAL_CODE", nullable = true, length = 10, unique = false)
	private String postalCode;

	@Column(name = "COUNTRY", nullable = true, length = 30, unique = false)
	private String country;

	@Column(name = "PHONE_HOME", nullable = true, length = 16, unique = false)
	private String phoneHome;

	@Column(name = "PHONE_WORK", nullable = true, length = 16, unique = false)
	private String phoneWork;

	@Column(name = "PHONE_FAX", nullable = true, length = 16, unique = false)
	private String phoneFax;

	@Column(name = "PHONE_OTHER", nullable = true, length = 16, unique = false)
	private String phoneOther;

    public Address() {
        // empty constructor required for Hibernate
    }

    public Address(Member member, String type) {
        addressPk = new AddressPk(member, type);
    }

    public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getDeliveryAddress()
	{
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress)
	{
		this.deliveryAddress = deliveryAddress;
	}

	public String getSecondaryAddress()
	{
		return secondaryAddress;
	}

	public void setSecondaryAddress(String secondaryAddress)
	{
		this.secondaryAddress = secondaryAddress;
	}

	public String getCity()
	{
		return city;
	}

	public void setCity(String city)
	{
		this.city = city;
	}

	public String getStateCode()
	{
		return stateCode;
	}

	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	public String getPostalCode()
	{
		return postalCode;
	}

	public void setPostalCode(String postalCode)
	{
		this.postalCode = postalCode;
	}

	public String getCountry()
	{
		return country;
	}

	public void setCountry(String country)
	{
		this.country = country;
	}

	public String getPhoneHome()
	{
		return phoneHome;
	}

	public void setPhoneHome(String phoneHome)
	{
		this.phoneHome = phoneHome;
	}

	public String getPhoneWork()
	{
		return phoneWork;
	}

	public void setPhoneWork(String phoneWork)
	{
		this.phoneWork = phoneWork;
	}

	public String getPhoneFax()
	{
		return phoneFax;
	}

	public void setPhoneFax(String phoneFax)
	{
		this.phoneFax = phoneFax;
	}

	public String getPhoneOther()
	{
		return phoneOther;
	}

	public void setPhoneOther(String phoneOther)
	{
		this.phoneOther = phoneOther;
	}

	public AddressPk getAddressPk()
	{
		return addressPk;
	}

	public void setAddressPk(AddressPk addressPk)
	{
		this.addressPk = addressPk;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (addressPk != null ? !addressPk.equals(address.addressPk) : address.addressPk != null) return false;

        return true;
    }

    public int hashCode() {
        return (addressPk != null ? addressPk.hashCode() : 0);
    }
}
