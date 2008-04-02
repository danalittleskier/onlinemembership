package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "MEMBERLEGAL")

public class MemberLegal implements Serializable
{
	@EmbeddedId
	MemberLegalPk memberLegalPk;

	@Column(name = "INSURANCE_COMPANY", nullable = true, length = 50)
	private String insuranceCompany;

	@Column(name = "INSURANCE_POLICY", nullable = true, length = 20)
	private String insurancePolicy;

	@Column(name = "INSURANCE_PHONE", nullable = true, length = 15)
	private String insurancePhone;

	@Column(name = "INSURANCE_WAIVER", nullable = true, length = 1)
	private String insuranceWaiver;

	@Column(name = "INSURANCE_WAIVER_DATE", nullable = true)
	private Date insuranceWaiverDate;

	@Column(name = "RELEASE_WAIVER", nullable = true, length = 1)
	private String releaseWaiver;

	@Column(name = "RELEASE_WAIVER_DATE", nullable = true)
	private Date releaseWaiverDate;


	public MemberLegalPk getMemberLegalPk()
	{
		return memberLegalPk;
	}

	public void setMemberLegalPk(MemberLegalPk memberLegalPk)
	{
		this.memberLegalPk = memberLegalPk;
	}

	public String getInsuranceCompany()
	{
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany)
	{
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsurancePolicy()
	{
		return insurancePolicy;
	}

	public void setInsurancePolicy(String insurancePolicy)
	{
		this.insurancePolicy = insurancePolicy;
	}

	public String getInsurancePhone()
	{
		return insurancePhone;
	}

	public void setInsurancePhone(String insurancePhone)
	{
		this.insurancePhone = insurancePhone;
	}

	public String getInsuranceWaiver()
	{
		return insuranceWaiver;
	}

	public void setInsuranceWaiver(String insuranceWaiver)
	{
		this.insuranceWaiver = insuranceWaiver;
	}

	public Date getInsuranceWaiverDate()
	{
		return insuranceWaiverDate;
	}

	public void setInsuranceWaiverDate(Date insuranceWaiverDate)
	{
		this.insuranceWaiverDate = insuranceWaiverDate;
	}

	public String getReleaseWaiver()
	{
		return releaseWaiver;
	}

	public void setReleaseWaiver(String releaseWaiver)
	{
		this.releaseWaiver = releaseWaiver;
	}

	public Date getReleaseWaiverDate()
	{
		return releaseWaiverDate;
	}

	public void setReleaseWaiverDate(Date releaseWaiverDate)
	{
		this.releaseWaiverDate = releaseWaiverDate;
	}
}
