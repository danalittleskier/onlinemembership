package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class AddressPk implements Serializable
{
	@Column(name = "USSA_ID", length = 5)
	private Long id;

	@Column(name = "ADDRESS_TYPE", nullable = true, length = 2, unique = false)
	private String type;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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
