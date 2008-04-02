package org.ussa.dao;

import java.util.List;

import org.ussa.beans.LineItemBean;

public interface RecommendedMembershipsDao
{

	public List<LineItemBean> getRecommendedMemberships(String currentYear, Long ussaID, String lastSeason);

}
