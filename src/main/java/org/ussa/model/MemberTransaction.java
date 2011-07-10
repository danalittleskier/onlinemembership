package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "MEMBERTRANSACTION")

public class MemberTransaction implements Serializable
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false, length = 7)
	private Long id;

    @ManyToOne
    @JoinColumn(name = "USSA_ID", nullable = false)
    private Member member;

	@Column(name = "SEASON", nullable = false, length = 4, unique = false)
	private String season;

	@ManyToOne
	@JoinColumn(name = "INV_ID", nullable = false)
	private Inventory inventory;

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
        // empty constructor required by Hibernate
    }

    public MemberTransaction(Member member) 
    {
        this.member = member;
    }

    public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

    public Member getMember() 
    {
        return member;
    }

    public void setMember(Member member) 
    {
        this.member = member;
    }

    public String getSeason()
	{
		return season;
	}

	public void setSeason(String season)
	{
		this.season = season;
	}


	public Inventory getInventory()
	{
		return inventory;
	}

	public void setInventory(Inventory inventory)
	{
		this.inventory = inventory;
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
}
