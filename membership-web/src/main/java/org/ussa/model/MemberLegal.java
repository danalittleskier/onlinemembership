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
	MemberSeasonPk memberSeasonPk;

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

	@Column(name = "FIS_RELEASE_FORM", nullable = true, length = 1)
	private String fisReleaseForm;

	@Column(name = "FIS_RELEASE_FORM_DATE", nullable = true)
	private Date fisReleaseFormDate;

	@Column(name = "IPC_RELEASE_FORM", nullable = true, length = 1)
	private String ipcReleaseForm;

	@Column(name = "IPC_RELEASE_FORM_DATE", nullable = true)
	private Date ipcReleaseFormDate;

	public MemberLegal()
	{
		// empty constructor required for Hibernate
	}

	public MemberLegal(Member member, String season)
	{
		memberSeasonPk = new MemberSeasonPk(member, season);
	}

	public MemberSeasonPk getMemberSeasonPk()
	{
		return memberSeasonPk;
	}

	public void setMemberSeasonPk(MemberSeasonPk memberSeasonPk)
	{
		this.memberSeasonPk = memberSeasonPk;
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

	public String getFisReleaseForm()
	{
		return fisReleaseForm;
	}

	public void setFisReleaseForm(String fisReleaseForm)
	{
		this.fisReleaseForm = fisReleaseForm;
	}

	public Date getFisReleaseFormDate()
	{
		return fisReleaseFormDate;
	}

	public void setFisReleaseFormDate(Date fisReleaseFormDate)
	{
		this.fisReleaseFormDate = fisReleaseFormDate;
	}

	public String getIpcReleaseForm()
	{
		return ipcReleaseForm;
	}

	public void setIpcReleaseForm(String ipcReleaseForm)
	{
		this.ipcReleaseForm = ipcReleaseForm;
	}

	public Date getIpcReleaseFormDate()
	{
		return ipcReleaseFormDate;
	}

	public void setIpcReleaseFormDate(Date ipcReleaseFormDate)
	{
		this.ipcReleaseFormDate = ipcReleaseFormDate;
	}

	public boolean equals(Object o)
	{
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MemberLegal that = (MemberLegal) o;

		if (memberSeasonPk != null ? !memberSeasonPk.equals(that.memberSeasonPk) : that.memberSeasonPk != null)
			return false;

		return true;
	}

	public int hashCode()
	{
		return (memberSeasonPk != null ? memberSeasonPk.hashCode() : 0);
	}
}
