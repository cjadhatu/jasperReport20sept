package com.java.main.dao;

import java.util.List;

import com.java.main.dto.SubRegionInputVO;




public interface SubRegionDao {
	
	
public List<Object[]> getSubRegionDetails(String queryString);

public List<Object[]> getsubRegionSummaryDetails(String queryString);
public void submitForecastOnSubRegion(SubRegionInputVO input,
		String attribute, String user_role,String forecastId);
public List<Object[]> getLOBSubRegionDetails(String string);
}
