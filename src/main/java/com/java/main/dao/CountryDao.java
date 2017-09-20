package com.java.main.dao;

import java.util.List;

import com.java.main.dto.CountryInputVO;




public interface CountryDao {
	
	
public List<Object[]> getCountryDetails(String queryString);

public List<Object[]> getCountrySummaryDetails(String queryString);
public void submitForecastOnCountry(CountryInputVO input,
		String attribute, String user_role,String forecastId);
public List<Object[]> getLOBCountryDetails(String string);
}
