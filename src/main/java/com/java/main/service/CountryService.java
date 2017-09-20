package com.java.main.service;

import com.java.main.dto.CountryInputVO;
import com.java.main.dto.CountryOutPutVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;

public interface CountryService {

	public CountryOutPutVO getCountryDeatils(CountryInputVO input,String globalId);
	
	public CountryOutPutVO submitForecastOnCountry(CountryInputVO input,
			String attribute, String user_role);
	
	public LOBOutDTO getLobCountryDetails(ReportGridInputMultipleVO input,String attribute, String user_role);
}
