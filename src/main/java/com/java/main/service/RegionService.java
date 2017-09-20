package com.java.main.service;

import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;

public interface RegionService {

	public RegionOutPutDTO getRegionDeatils(RegionInputDTO regionInputDTO,String globalId,String user_role);

	public RegionOutPutDTO submitForecastOnRegion(RegionInputDTO input,
			String attribute, String user_role);
	public LOBOutDTO getLobRegionDetails(ReportGridInputMultipleVO input,String attribute, String user_role);
	
	
}
