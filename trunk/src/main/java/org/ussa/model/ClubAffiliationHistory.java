package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "CLUBAFFILIATIONHISTORY")

public class ClubAffiliationHistory implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 7)
	private Long id;
	
	@Column(name = "IND_USSA_ID", nullable = false, length = 7, unique=false)
	private Long indUssaId;

	@Column(name = "CLUB_USSA_ID", nullable = false, length = 7, unique=false)
	private Long clubUssaId;
	
	@Column(name = "PROCESS_DATE", nullable= false, unique=false)
	private Date processDate;
	
	@Column(name = "CHANGED_BY", nullable=true, length = 50)
	private String changedBy;
	
	public ClubAffiliationHistory()
	{
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getIndUssaId()
	{
		return indUssaId;
	}

	public void setIndUssaId(Long indUssaId)
	{
		this.indUssaId = indUssaId;
	}

	public Long getClubUssaId()
	{
		return clubUssaId;
	}

	public void setClubUssaId(Long clubUssaId)
	{
		this.clubUssaId = clubUssaId;
	}

	public Date getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}

	public String getChangedBy() {
		return changedBy;
	}

	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
