package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class AddressPk implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "USSA_ID")
	private Member member;

	@Column(name = "ADDRESS_TYPE", nullable = true, length = 2, unique = false)
	private String type;

    public AddressPk() {
        // empty constructor required for Hibernate
    }

    public AddressPk(Member member, String type) 
    {
        this.member = member;
        this.type = type;
    }

    public Member getMember() 
    {
        return member;
    }

    public void setMember(Member member) 
    {
        this.member = member;
    }

    public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddressPk addressPk = (AddressPk) o;

        if (member != null ? !member.equals(addressPk.member) : addressPk.member != null) return false;
        if (type != null ? !type.equals(addressPk.type) : addressPk.type != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (member != null ? member.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
