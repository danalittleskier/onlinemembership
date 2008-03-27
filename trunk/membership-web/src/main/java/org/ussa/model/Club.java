package org.ussa.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

import org.appfuse.model.BaseObject;

@NamedQueries({
		})

@Entity
@Table(name = "MEMBER")

public class Club extends BaseObject implements Serializable
{
	//Member table fields for a club
	@Id
	@Column(name = "USSA_ID")
	private Long id;

	@Column(name = "MEMBER_TYPE", nullable = true, length = 2, unique = false)
	private String type;

	@Column(name = "CLUB_NAME", nullable = true, length = 50, unique = false)
	private String name;

	@Column(name = "CLUB_PRESIDENT", nullable = true, length = 50, unique = false)
	private String president;

	@Column(name = "CLUB_SECRETARY", nullable = true, length = 50, unique = false)
	private String secretary;

	@Column(name = "CLUB_HEADCOACH", nullable = true, length = 50, unique = false)
	private String headcoach;

	@Column(name = "EMAIL", nullable = true, length = 60, unique = false)
	private String email;

	@Column(name = "STATE_CODE", nullable = true, length = 2, unique = false)
	private String stateCode;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "DIVISION_CODE", nullable = true, unique = false)
	private Division division;

//    @Column(name = "EXPIRE_SEASON", nullable = true, length=4, unique=false)
//    private String expireSeason;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "MEMBERCLUB",
			inverseJoinColumns = {@JoinColumn(name = "IND_USSA_ID")},
			joinColumns = @JoinColumn(name = "CLUB_USSA_ID")
	)
	private Set<Member> members;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPresident()
	{
		return president;
	}

	public void setPresident(String president)
	{
		this.president = president;
	}

	public String getSecretary()
	{
		return secretary;
	}

	public void setSecretary(String secretary)
	{
		this.secretary = secretary;
	}

	public String getHeadcoach()
	{
		return headcoach;
	}

	public void setHeadcoach(String headcoach)
	{
		this.headcoach = headcoach;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getStateCode()
	{
		return stateCode;
	}

	public void setStateCode(String stateCode)
	{
		this.stateCode = stateCode;
	}

	public Division getDivision()
	{
		return division;
	}

	public void setDivision(Division division)
	{
		this.division = division;
	}

	public Set<Member> getMembers()
	{
		return members;
	}

	public void setMembers(Set<Member> members)
	{
		this.members = members;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}


}
