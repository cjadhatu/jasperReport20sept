package com.java.main.dao;

import java.util.List;

import com.java.main.dto.BranchInputVO;
import com.java.main.dto.CountryInputVO;

public interface BranchDao {
	public List<String> getBranchesByRegion(String regionCode);

	public List<Object[]> getBranchDetails(String queryString);

	public List<Object[]> getBranchSummaryDetails(String queryString);
	
	public void submitForecastOnBranch(BranchInputVO input,
			String attribute, String user_role,String forecastId);
	public List<Object[]> getLOBBranchDetails(String string);
}
