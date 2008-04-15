package org.ussa.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
public class MemberSeasonPk implements Serializable
{
    @ManyToOne
    @JoinColumn(name = "USSA_ID")
    private Member member;

	@Column(name = "SEASON", nullable = false, length = 4)
	private String season;


    public MemberSeasonPk() {
        // empty constructor required for Hibernate
    }

    public MemberSeasonPk(Member member, String season) {
        this.member = member;
        this.season = season;
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

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MemberSeasonPk that = (MemberSeasonPk) o;

        if (member != null ? !member.equals(that.member) : that.member != null) return false;
        if (season != null ? !season.equals(that.season) : that.season != null) return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = (member != null ? member.hashCode() : 0);
        result = 31 * result + (season != null ? season.hashCode() : 0);
        return result;
    }
}
