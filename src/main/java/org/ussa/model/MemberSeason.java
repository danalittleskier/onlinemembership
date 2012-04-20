package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "MEMBERSEASON")

public class MemberSeason implements Serializable
{
	//default values
	public MemberSeason(){
		this.currentFlag = "Y";
		this.waiverSigned = "Y";
		this.provisionalFis = "N";
		this.teamAgreement = "N";
		this.divMedicalRelease = "N";
		this.prep = "N";
		//this.sentConfirmationEmail = 0;
		
	}
	
	@EmbeddedId
	MemberSeasonPk memberSeasonPk;

	@Column(name = "CURRENT_FLAG", length = 1, nullable = true, unique = false)
	private String currentFlag;

	@Column(name = "WAIVER_SIGNED", length = 1, nullable = true, unique = false)
	private String waiverSigned;

	@Column(name = "MEDICAL_EXCEPTION", length = 1, nullable = false, unique = false)
	private String medicalException;

	@Column(name = "PROVISIONAL_FIS", length = 1, nullable = true, unique = false)
	private String provisionalFis;

	@Column(name = "APP_PROCESS_DATE", nullable = true, unique = false)
	private Date appProcessDate;

	@Column(name = "APP_RECEIVE_DATE", nullable = true, unique = false)
	private Date appReceiveDate;

	@Column(name = "RENEWAL_SENT", nullable = true, unique = false)
	private Date renewalSent;

	@Column(name = "TEAM_AGREEMENT", length = 1, nullable = true, unique = false)
	private String teamAgreement;

	@Column(name = "DIV_MEDICAL_RELEASE", length = 1, nullable = true, unique = false)
	private String divMedicalRelease;

	@Column(name = "PREP", length = 1, nullable = true, unique = false)
	private String prep;

	@Column(name = "BACKGROUND_CHECK_FLAG", length = 1, nullable = true, unique = false)
	private String backgroundCheckFlag;

	@Column(name = "BACKGROUND_CHECK_SENT_DATE", nullable = true, unique = false)
	private Date backgroundCheckSentDate;

	@Column(name = "BACKGROUND_CHECK_RECEIVE_DATE", nullable = true, unique = false)
	private Date backgroundCheckReceiveDate;
	
	@Column(name = "BACKGROUND_CHECK_RENEWAL_SEASON",  nullable = true, unique = false)
	private String backgroundRenewalSeason;
	
	@Column(name = "FAST_START_COURSE_CHECK_FLAG", length = 1, nullable = true, unique = false)
	private String fastStartCourseCheckFlag;
	
	@Column(name = "FAST_START_COURSE_CHECK_DATE", nullable = true, unique = false)
	private Date fastStartCourseCheckDate;
	
	//@Column(name = "SENT_CONFIRMATION_EMAILS", length = 1, nullable = true, unique = false)
	//private Long sentConfirmationEmail;

	public String getBackgroundRenewalSeason() {
		return backgroundRenewalSeason;
	}

	public void setBackgroundRenewalSeason(String backgroundRenewalSeason) {
		this.backgroundRenewalSeason = backgroundRenewalSeason;
	}

	public MemberSeasonPk getMemberSeasonPk()
	{
		return memberSeasonPk;
	}

	public void setMemberSeasonPk(MemberSeasonPk memberSeasonPk)
	{
		this.memberSeasonPk = memberSeasonPk;
	}

	public String getCurrentFlag()
	{
		return currentFlag;
	}

	public void setCurrentFlag(String currentFlag)
	{
		this.currentFlag = currentFlag;
	}

	public String getWaiverSigned()
	{
		return waiverSigned;
	}

	public void setWaiverSigned(String waiverSigned)
	{
		this.waiverSigned = waiverSigned;
	}

	public String getMedicalException()
	{
		return medicalException;
	}

	public void setMedicalException(String medicalException)
	{
		this.medicalException = medicalException;
	}

	public String getProvisionalFis()
	{
		return provisionalFis;
	}

	public void setProvisionalFis(String provisionalFis)
	{
		this.provisionalFis = provisionalFis;
	}

	public Date getAppProcessDate()
	{
		return appProcessDate;
	}

	public void setAppProcessDate(Date appProcessDate)
	{
		this.appProcessDate = appProcessDate;
	}

	public Date getAppReceiveDate()
	{
		return appReceiveDate;
	}

	public void setAppReceiveDate(Date appReceiveDate)
	{
		this.appReceiveDate = appReceiveDate;
	}

	public Date getRenewalSent()
	{
		return renewalSent;
	}

	public void setRenewalSent(Date renewalSent)
	{
		this.renewalSent = renewalSent;
	}

	public String getTeamAgreement()
	{
		return teamAgreement;
	}

	public void setTeamAgreement(String teamAgreement)
	{
		this.teamAgreement = teamAgreement;
	}

	public String getDivMedicalRelease()
	{
		return divMedicalRelease;
	}

	public void setDivMedicalRelease(String divMedicalRelease)
	{
		this.divMedicalRelease = divMedicalRelease;
	}

	public String getPrep()
	{
		return prep;
	}

	public void setPrep(String prep)
	{
		this.prep = prep;
	}

	public String getBackgroundCheckFlag()
	{
		return backgroundCheckFlag;
	}

	public void setBackgroundCheckFlag(String backgroundCheckFlag)
	{
		this.backgroundCheckFlag = backgroundCheckFlag;
	}

	public Date getBackgroundCheckSentDate()
	{
		return backgroundCheckSentDate;
	}

	public void setBackgroundCheckSentDate(Date backgroundCheckSentDate)
	{
		this.backgroundCheckSentDate = backgroundCheckSentDate;
	}

	public Date getBackgroundCheckReceiveDate()
	{
		return backgroundCheckReceiveDate;
	}

	public void setBackgroundCheckReceiveDate(Date backgroundCheckReceiveDate)
	{
		this.backgroundCheckReceiveDate = backgroundCheckReceiveDate;
	}

	public String getFastStartCourseCheckFlag() {
		return fastStartCourseCheckFlag;
	}

	public void setFastStartCourseCheckFlag(String fastStartCourseCheckFlag) {
		this.fastStartCourseCheckFlag = fastStartCourseCheckFlag;
	}

	public Date getFastStartCourseCheckDate() {
		return fastStartCourseCheckDate;
	}

	public void setFastStartCourseCheckDate(Date fastStartCourseCheckDate) {
		this.fastStartCourseCheckDate = fastStartCourseCheckDate;
	}

	//public Long getSentConfirmationEmail() {
	//	return sentConfirmationEmail;
	//}

	//public void setSentConfirmationEmail(Long sentConfirmationEmail) {
	//	this.sentConfirmationEmail = sentConfirmationEmail;
	//}

	

	
}
