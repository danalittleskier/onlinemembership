package org.ussa.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="PARENTINFO")

public class ParentInfo implements Serializable {

	@Id
    @GeneratedValue(generator="foreign")
      @GenericGenerator(name="foreign", strategy = "foreign", parameters={
        @Parameter(name="property", value="member")
      }) 
    @Column(name = "USSA_ID", length=7)
	private Long id;
    
    @OneToOne
    @PrimaryKeyJoinColumn
    private Member member;

    @Column(name = "PARENT1FIRST", nullable = true, length=30, unique=false)
	private String parent1First;

	@Column(name = "PARENT1LAST", nullable = true, length=30, unique=false)
	private String parent1Last;

	@Column(name = "PARENT1EMAIL", nullable = true, length=60, unique=false)
	private String parent1Email;

	@Column(name = "PARENT1RELATION", nullable = true, length=30, unique=false)
	private String parent1Relation;

	@Column(name = "PARENT2FIRST", nullable = true, length=30, unique=false)
	private String parent2First;

	@Column(name = "PARENT2LAST", nullable = true, length=30, unique=false)
	private String parent2Last;

	@Column(name = "PARENT2EMAIL", nullable = true, length=60, unique=false)
	private String parent2Email;

	@Column(name = "PARENT2RELATION", nullable = true, length=30, unique=false)
	private String parent2Relation;


	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getParent1First()
	{
		return parent1First;
	}

	public void setParent1First(String parent1First)
	{
		this.parent1First = parent1First;
	}

	public String getParent1Last()
	{
		return parent1Last;
	}

	public void setParent1Last(String parent1Last)
	{
		this.parent1Last = parent1Last;
	}

	public String getParent1Email()
	{
		return parent1Email;
	}

	public void setParent1Email(String parent1Email)
	{
		this.parent1Email = parent1Email;
	}

	public String getParent1Relation()
	{
		return parent1Relation;
	}

	public void setParent1Relation(String parent1Relation)
	{
		this.parent1Relation = parent1Relation;
	}

	public String getParent2First()
	{
		return parent2First;
	}

	public void setParent2First(String parent2First)
	{
		this.parent2First = parent2First;
	}

	public String getParent2Last()
	{
		return parent2Last;
	}

	public void setParent2Last(String parent2Last)
	{
		this.parent2Last = parent2Last;
	}

	public String getParent2Email()
	{
		return parent2Email;
	}

	public void setParent2Email(String parent2Email)
	{
		this.parent2Email = parent2Email;
	}

	public String getParent2Relation()
	{
		return parent2Relation;
	}

	public void setParent2Relation(String parent2Relation)
	{
		this.parent2Relation = parent2Relation;
	}

}
