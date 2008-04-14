package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import java.io.Serializable;

@NamedQueries({
		})

@Entity
@Table(name = "STATE")

public class State implements Serializable
{
	@Id
	@Column(name = "CODE", length = 2)
	private String id;

	@Column(name = "DESCRIPTION", nullable = true, length = 30)
	private String description;

	@Column(name = "USstate", nullable = true, length = 2, unique = false)
	private String us;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUs()
	{
		return us;
	}

	public void setUs(String us)
	{
		this.us = us;
	}

}
