package com.java.main.service;

import java.util.List;

import com.java.main.dto.BranchInputVO;
import com.java.main.dto.BranchOutPutDTO;
import com.java.main.dto.BranchVO;
import com.java.main.dto.CountryOutPutVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;

public interface BranchService {

	public List<BranchVO> geBranchList(String regionCode);

	public BranchOutPutDTO getBranchDetails(BranchInputVO input,String attribute);

	public BranchOutPutDTO submitForecastOnBranch(BranchInputVO input,String attribute, String user_role);

	public LOBOutDTO getLobBranchDetails(ReportGridInputMultipleVO input,String attribute, String user_role);
}
