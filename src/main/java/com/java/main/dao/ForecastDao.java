package com.java.main.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.List;

import com.java.main.dto.AccountNameInputVO;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.ForecastOutVO;
import com.java.main.dto.OpportunityDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SaveOpportunityInputVO;
import com.java.main.model.Opportunity;

public interface ForecastDao {
	public List<Object[]> getOpportunities(String globalId);

	public Opportunity updateOpportunity(Opportunity op, String globalId, String userRole);

	public Opportunity deleteOpportunity(Opportunity op, String globalId, String userRole);

	public List<Object[]> getDynamicOpportunity(ReportGridInputMultipleVO dto);

	public void submitForecastBFC(OpportunityDTO op,String globalId,String userRole);

	void submitForecastSRFC(OpportunityDTO op, String userName, String userRole);

	void submitForecastCFC(OpportunityDTO op, String userName, String userRole);

	void submitForecastRFC(OpportunityDTO op, String userName, String userRole);
	public BigDecimal getConvertAmount(BigDecimal localCurAmt,String isFactAmt,String curCode,String sourceType);
	public void submitForecastBFCJdbcBatchUpdate(List<OpportunityDTO> op,String globalId,String userRole)throws SQLException;
	public void reSubmitForecastBFCJdbcBatchUpdate(List<OpportunityDTO> op,String globalId,String userRole)throws SQLException;
//ReSubmitForecastBFCJdbcBatchUpdate
	public List<Object[]> getAddNewOpportunity(String globalId);

	public String SaveAddNewOpportunity(SaveOpportunityInputVO saveOpportunityInputVO, String globalId,String userRole,String userName);

	public List<String> getLob();

	public List<String> getCurrency();

	public List<Object[]> getAccountName(AccountNameInputVO accountNameInputVO);

	public String getRate(String string, String currency, String sourceType); 
	public String getRefresh(String queryString);
	public String callDataPullFunction(String queryString);

	public List<String> getStage();
	public String callFunctionForEditSaveDelete(String queryString);
	public String callFunctionForSubmitForecast(String queryString);
	public String callTempAutoSchedule(String queryString);
	
	public BigInteger isAllBranchSubmitted(String queryString);
	public BigInteger isAllCountrySubmitted(String queryString);
	public BigInteger isAllSubRegionSubmitted(String queryString);
}
