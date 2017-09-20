package com.java.main.service;

import java.math.BigDecimal;
import java.util.List;

import com.java.main.dto.AccountNameInputVO;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.ConvertedAmountDTO;
import com.java.main.dto.DropDownVO;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.ForecastOutVO;
import com.java.main.dto.OpportunityDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SaveOpportunityInputVO;
import com.java.main.dto.SubmitFCInputVO;

public interface ForecasteService {

	public ForecastOutVO getopportunities(String globalId);

	public ForecastOutVO updateOpportunity(ForecastInputVO input, String globalId, String userRole);

	public ForecastOutVO deleteOpportunity(ForecastInputVO input, String globalId, String userRole);

	public ForecastOutVO getDynamicOpportunity(ReportGridInputMultipleVO dto);

	public ForecastOutVO submitForecastALL(SubmitFCInputVO dto, String golbalId,
			String userRole);
	public ForecastOutVO reSubmitForecastALL(SubmitFCInputVO dto, String golbalId,
			String userRole);

	public AddNewOpportunity getAddNewOpportunity(String globalId,String userRole);

	public String SaveAddNewOpportunity(
			SaveOpportunityInputVO saveOpportunityInputVO, String globalId,
			String userRole,String userName);

	public List<DropDownVO> getLob();

	public List<DropDownVO> getCurrency();

	public List<DropDownVO> getAccountName(AccountNameInputVO accountNameInputVO);

	public String getRate(ConvertedAmountDTO convertedAmountDTO);
	public ForecastOutVO getRefresh(ReportGridInputMultipleVO dto,String globalId);
	
	public String callDataPullFunction(String queryString);
	public ForecastOutVO functionCallForditAndSave(OpportunityDTO input, String globalId, String userRole,String isEdit);
	public ForecastOutVO functionCallForSubmitForecast(ForecastInputVO input, String globalId, String userRole,String isResubmit);
	
	public ForecastOutVO getRefreshParent(ReportGridInputMultipleVO dto,String globalId);
	public String callTempAutoSchedule(String queryString);


	public String isSubmittedForCountryFC(SubmitFCInputVO submitFCInputVO);
	public String isSubmittedForRegionalFC(SubmitFCInputVO submitFCInputVO);
	public String isSubmittedForSubRegionFC(SubmitFCInputVO submitFCInputVO);
}
