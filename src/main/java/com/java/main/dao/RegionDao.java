package com.java.main.dao;

import java.util.List;

import com.java.main.dto.RegionInputDTO;




public interface RegionDao {
	
	
public List<Object[]> getRegionDetails(String queryString);

public List<Object[]> getRegionSummaryDetails(String queryString);
public List<Object[]> getLobRegionDetails(String string);

public List<Object[]> getLOBRegionSummaryDetails(String string);

public void submitForecastOnRegion(RegionInputDTO input,
			String attribute, String user_role,String forecastId);
}
