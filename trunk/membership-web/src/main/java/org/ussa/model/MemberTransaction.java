package org.ussa.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.appfuse.model.BaseObject;

@Entity
@Table(name = "MEMBERTRANSACTION")

public class MemberTransaction extends BaseObject implements Serializable
{
	@Id
	@Column(name = "ID", nullable = false, length = 7)
	private Long id;

	@Column(name = "USSA_ID", nullable = false, length = 7)
	private Long ussaId;

	@Column(name = "SEASON", nullable = false, length = 4, unique = false)
	private String season;

	@Column(name = "INV_ID", nullable = false, length = 8, unique = false)
	private String invId;

	@Column(name = "QTY", nullable = true, length = 4, unique = false)
	private Integer qty;

	@Column(name = "AMOUNT", nullable = true, unique = false)
	private BigDecimal amount;

	@Column(name = "SENT_DATE", nullable = true, unique = false)
	private Date sentDate;

	@Column(name = "PURCHASE_DATE", nullable = true, unique = false)
	private Date purchaseDate;

	public MemberTransaction()
	{
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getUssaId()
	{
		return ussaId;
	}

	public void setUssaId(Long ussaId)
	{
		this.ussaId = ussaId;
	}

	public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}

	public String getInvId()
	{
		return invId;
	}

	public void setInvId(String invId)
	{
		this.invId = invId;
	}

	public Integer getQty()
	{
		return qty;
	}

	public void setQty(Integer qty)
	{
		this.qty = qty;
	}

	public BigDecimal getAmount()
	{
		return amount;
	}

	public void setAmount(BigDecimal amount)
	{
		this.amount = amount;
	}

	public Date getSentDate()
	{
		return sentDate;
	}

	public void setSentDate(Date sentDate)
	{
		this.sentDate = sentDate;
	}

	public Date getPurchaseDate()
	{
		return purchaseDate;
	}

	public void setPurchaseDate(Date purchaseDate)
	{
		this.purchaseDate = purchaseDate;
	}

	@Override
	public boolean equals(Object o)
	{
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public int hashCode()
	{
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return null;
	}
}
