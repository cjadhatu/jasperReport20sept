package com.java.main.service;

import com.java.main.dto.CountryInputVO;
import com.java.main.dto.CountryOutPutVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SubRegionInputVO;
import com.java.main.dto.SubRegionOutPutVO;

public interface SubRegionService {

	public RegionOutPutDTO getSubRegionDeatils(SubRegionInputVO regionInputDTO,String globalId);
	public SubRegionOutPutVO submitForecastOnSubRegion(SubRegionInputVO input,
			String attribute, String user_role);
	public LOBOutDTO getLobSubRegionDetails(ReportGridInputMultipleVO input,String attribute, String user_role);
}
