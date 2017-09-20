package com.java.main.service;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.support.DaoSupport;
import org.springframework.stereotype.Service;

import com.java.main.dao.AddNewOpportunityDao;
import com.java.main.dao.ForecastDao;
import com.java.main.dao.LoginDao;
import com.java.main.dao.ReportDao;
import com.java.main.dto.AccountNameInputVO;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.ConvertedAmountDTO;
import com.java.main.dto.DropDownVO;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.ForecastOutVO;
import com.java.main.dto.OpportunityDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.ReportOnLoadDTO;
import com.java.main.dto.SaveOpportunityInputVO;
import com.java.main.dto.SubmitFCInputVO;
import com.java.main.dto.ValueDTO;
import com.java.main.model.Opportunity;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.DateCompare1;
import com.java.main.utility.WFTConstant;

@Service
public class ForecastServiceImpl implements ForecasteService {
@Autowired
ForecastDao ForecastDao;

@Autowired
ReportService reportService;

@Autowired
AddNewOpportunityDao addNewOpportunityDao; 

@Autowired
LoginDao loginDao; 

public ForecastOutVO getopportunities(String globalId) {
	// TODO Auto-generated method stub
	Locale locale = new Locale("en", "US");
	NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
	ForecastOutVO response=new ForecastOutVO();
	 BigDecimal totalEstAmt=new BigDecimal(0);
	 BigDecimal totalFactAmt=new BigDecimal(0);
	 BigDecimal totalUnfactAmt=new BigDecimal(0);
	List<OpportunityDTO> opps=new ArrayList<OpportunityDTO>();
	//opp_id,account_name,opp_name,
	//close_date,unfactored_amount,
	//factored_amount,gross_margin,stagename
	List<Object[]> objs=ForecastDao.getOpportunities(globalId);
	String isValid="Y";
	String isActive="A";
	for(Object[] obj:objs){
		OpportunityDTO op=new OpportunityDTO();
		if(obj[0]!=null)
		op.setOppId((BigDecimal)obj[0]);
		if(obj[1]!=null)
		op.setAccName((String)obj[1]);
		if(obj[2]!=null)
		op.setOppName((String)obj[2]);
		if(obj[3]!=null){
			Date mydate=(Date)obj[3];
			//System.out.println("Date "+mydate);
			String str=new SimpleDateFormat("MM-dd-yyyy").format(mydate);
		//op.setPlanDate(((Date)obj[3]).toString());
		op.setPlanDate(str);
		 isValid=DateCompare1.isValidDate(mydate);
		
		}
		if(obj[4]!=null){
			op.setUnfactAmt(fmt.format(((BigDecimal) obj[4])));
			//totalUnfactAmt=totalUnfactAmt.add((BigDecimal) obj[4]);
		}
		if(obj[5]!=null){
			op.setFactAmt(fmt.format(((BigDecimal) obj[5])));
			//totalFactAmt=totalFactAmt.add((BigDecimal) obj[5]);
		}
		if(obj[6]!=null)
		op.setMargin(((BigDecimal)obj[6]).toString());
		if(obj[7]!=null)
		op.setStage((String)obj[7]);
		
		
		
		if(obj[8]!=null){
			op.setOppNumber((String)obj[8]);
		}
		if(obj[9]!=null){
			op.setProbability(((BigDecimal)obj[9]).toString());
		}
		if(obj[10]!=null){
			op.setSalesLeadRep((String)obj[10]);
		}
		if(obj[11]!=null && "I".equalsIgnoreCase((String)obj[11])){
			isActive="I";
		}
		if(obj[11]!=null){
			op.setOppStatus((String)obj[11]);
		}
		if("N".equalsIgnoreCase(isValid)){
			op.setIsRemoved("Y");	
		}
		if("I".equalsIgnoreCase(isActive)){
			op.setIsRemoved("Y");	
		}
		if("N".equalsIgnoreCase(op.getIsRemoved()) &&  obj[4]!=null){
			totalUnfactAmt=totalUnfactAmt.add((BigDecimal) obj[4]);
			
		}
		if("N".equalsIgnoreCase(op.getIsRemoved()) &&  obj[5]!=null){
			
			totalFactAmt=totalFactAmt.add((BigDecimal) obj[5]);
		}
		//adding new fields 19 APril
		if(obj[12]!=null){
		op.setBranch((String)obj[12]);
		}
		if(obj[13]!=null){
			op.setLob((String)obj[13]);
		}
		if(obj[14]!=null){
			op.setCurrency((String)obj[14]);
			
		}
		if(obj[15]!=null){
			
			//op.setUnfactAmt(fmt.format(((BigDecimal) obj[4])));
			op.setFactAmtLocalCur(fmt.format((BigDecimal)obj[15]).toString());
			op.setFactAmtLocalCur(op.getFactAmtLocalCur().substring(1));
			//op.setFactAmtLocalCur(((BigDecimal)obj[15]).toString());
		}
		if(obj[16]!=null){
			op.setUnfactAmtLocalCur(fmt.format((BigDecimal)obj[16]).toString());
			op.setUnfactAmtLocalCur(op.getUnfactAmtLocalCur().substring(1));
			//op.setUnfactAmtLocalCur(((BigDecimal)obj[16]).toString());
		}
		if(obj[17]!=null){
			op.setSalesManager((String)obj[17]);
		}
		if(obj[18]!=null){
			op.setLeadsource((String)obj[18]);
			
		}
		if(obj[19]!=null){
			op.setMustWinflag(((Boolean)obj[19]).toString());
		}
		if(obj[20]!=null){
			op.setMustWinflag(((String)obj[20]));
		}
		opps.add(op);
		
	}
	response.setTotalFactAmt(fmt.format(totalFactAmt));
	response.setTotalUnfactAmt(fmt.format(totalUnfactAmt));
	response.setOppData(opps);
	return response;
}

public ForecastOutVO updateOpportunity(ForecastInputVO input, String globalId, String userRole) {
	// TODO Auto-generated method stub
	List<OpportunityDTO> opps=input.getOppData();
	for(OpportunityDTO opp:opps){
		Opportunity op=new Opportunity();
		//op.setAccountName(opp.getAccName());
		if(opp.getMargin()==null || (opp.getMargin().isEmpty())){
			op.setGrossMargin(new BigDecimal(0));
		}
		else
		{
		op.setGrossMargin(new BigDecimal(opp.getMargin()));
		}
		op.setOppId(opp.getOppId());
		//op.setOppName(opp.getOppName());
		//op.setOppNumber(opp.getOppNumber());
		//op.setProbability(new BigDecimal(opp.getProbability()));
		if(opp.getFactAmtLocalCur()==null)
		{
			//op.setFactoredAmount(new BigDecimal(0));
			op.setFactoredAmount(new BigDecimal(0));
		}
		else
		{
			//op.setFactoredAmount(DataTypeConvert.getBigDecFromCommaSepString(opp.getFactAmt()));
			op.setFactoredAmount(DataTypeConvert.getBigDecFromCommaSepString(opp.getFactAmtLocalCur()));
			
		}
		if(opp.getUnfactAmtLocalCur()==null){
			//op.setFactoredAmount(new BigDecimal(0));
			op.setUnFactoredAmount(new BigDecimal(0));
		}
		else
		{
			op.setUnFactoredAmount(DataTypeConvert.getBigDecFromCommaSepString(opp.getUnfactAmtLocalCur()));
		}
		if(opp.getProbability()!=null && !(opp.getProbability().isEmpty()) ){
			op.setProbability(DataTypeConvert.getBigDecFromCommaSepString(opp.getProbability()));
		}
		else{
			op.setProbability(new BigDecimal(0));
		}
		if(opp.getStage()==null || opp.getStage().isEmpty()){
			op.setStageName("");
		}
		else{
			op.setStageName(opp.getStage());
		}
		if(opp.getPlanDate().length()>10){
			
			op.setCloseDate(DateUtility.getDateForEditOpportunityIfDateEdit(opp.getPlanDate()));
		}
		else{
			op.setCloseDate(DateUtility.getDateForEditOpportunity(opp.getPlanDate()));	
		}
		if(opp.getCurrency()!=null && !(opp.getCurrency().isEmpty())){
			op.setCurrency(opp.getCurrency());
		}
		else
		{
			op.setCurrency("");
		}
		if(opp.getFactAmt()==null){
			op.setConvertedFactoredAmt(new BigDecimal(0));	
		}
		else{
			op.setConvertedFactoredAmt(DataTypeConvert.getBigDecFromCommaSepString(opp.getFactAmt()));
		}
		if(opp.getUnfactAmt()==null){
			op.setConvertedUnfactoredAmt(new BigDecimal(0));
		}
		else{
			op.setConvertedUnfactoredAmt(DataTypeConvert.getBigDecFromCommaSepString(opp.getUnfactAmt()));
		}
		/*if(opp.getCurrency()!=null && !(opp.getCurrency().isEmpty()) && opp.getSourceType()!=null && !(opp.getSourceType().isEmpty())){
			op.setConvertedFactoredAmt(ForecastDao.getConvertAmount(op.getFactoredAmount(),"Y",opp.getCurrency(),opp.getSourceType()));
			op.setConvertedUnfactoredAmt(ForecastDao.getConvertAmount(op.getUnFactoredAmount(),"N",opp.getCurrency(),opp.getSourceType()));
		}*/
		Opportunity upadated=ForecastDao.updateOpportunity(op,globalId,userRole);
		functionCallForditAndSave(opp,globalId,userRole,"E");
	}
	ReportGridInputMultipleVO fetchInput=new ReportGridInputMultipleVO();
	fetchInput.setBranches(input.getFetchJson().getBranches());
	fetchInput.setCountries(input.getFetchJson().getCountries());
	fetchInput.setRegions(input.getFetchJson().getRegions());
	fetchInput.setSubregions(input.getFetchJson().getSubregions());
	fetchInput.setTypeSources(input.getFetchJson().getTypeSources());
	
	//Function CALLED DB edit and save START
	//functionCallForditAndSave(input,globalId,userRole,"E");
	//Function CALLED DB edit and save END
	
	ForecastOutVO response=getDynamicOpportunity(fetchInput);

	
	return response;
}
public ForecastOutVO deleteOpportunity(ForecastInputVO input, String globalId, String userRole) {
	// TODO Auto-generated method stub
	List<OpportunityDTO> opps=input.getOppData();
	for(OpportunityDTO opp:opps){
		Opportunity op=new Opportunity();
		op.setOppId(opp.getOppId());
		op.setOppStatus("I");
		
		Opportunity upadated=ForecastDao.deleteOpportunity(op,globalId,userRole);
		
		//new code
		
		functionCallForditAndSave(opp, globalId, userRole, "D");
		
		ReportGridInputMultipleVO dto= new ReportGridInputMultipleVO();
		List<ValueDTO> btos=new ArrayList<ValueDTO>();
		dto.setBranches(input.getFetchJson().getBranches());
		dto.setCountries(input.getFetchJson().getCountries());
		dto.setRegions(input.getFetchJson().getRegions());
		dto.setSubregions(input.getFetchJson().getSubregions());
		dto.setTypeSources(input.getFetchJson().getTypeSources());
		
	}
	//ForecastOutVO response=getopportunities(new String());
	ReportGridInputMultipleVO dto= new ReportGridInputMultipleVO();
	dto.setBranches(input.getFetchJson().getBranches());
	dto.setCountries(input.getFetchJson().getCountries());
	dto.setRegions(input.getFetchJson().getRegions());
	dto.setSubregions(input.getFetchJson().getSubregions());
	dto.setTypeSources(input.getFetchJson().getTypeSources());
	
	//Delete Forecast Function Calling START
	try {
		//functionCallForditAndSave(input, globalId, userRole, "N");
	} catch (Exception e) {
		System.out.println("Exception on calling functionCallForditAndSave for delete in service exception is"+e.getMessage());
		// TODO: handle exception
	}
	
	//Delete Forecast Function Calling END
	
	ForecastOutVO response=getDynamicOpportunity(dto);
	return response;
}

public ForecastOutVO getDynamicOpportunity(ReportGridInputMultipleVO dto) {
	// TODO Auto-generated method stub
	Locale locale = new Locale("en", "US");
	NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
	ForecastOutVO response=new ForecastOutVO();
	 BigDecimal totalEstAmt=new BigDecimal(0);
	 BigDecimal totalFactAmt=new BigDecimal(0);
	 BigDecimal totalUnfactAmt=new BigDecimal(0);
	 BigDecimal totalFactAmtAll=new BigDecimal(0);
	 BigDecimal totalUnfactAmtAll=new BigDecimal(0);
	List<OpportunityDTO> opps=new ArrayList<OpportunityDTO>();
	//opp_id,account_name,opp_name,
	//close_date,unfactored_amount,
	//factored_amount,gross_margin,stagename
	List<Object[]> objs=ForecastDao.getDynamicOpportunity(dto);
	String isValid="Y";
	String isActive="A";
	for(Object[] obj:objs){
		OpportunityDTO op=new OpportunityDTO();
		if(obj[0]!=null)
		op.setOppId((BigDecimal)obj[0]);
		if(obj[1]!=null)
		op.setAccName((String)obj[1]);
		if(obj[2]!=null)
		op.setOppName((String)obj[2]);
		if(obj[3]!=null){
			Date mydate=(Date)obj[3];
			//System.out.println("Date "+mydate);
			//System.out.println("******* mydate"+mydate);
			String str=new SimpleDateFormat("MM-dd-yyyy").format(mydate);
			//System.out.println("******* mydate ---str"+str);
		//op.setPlanDate(((Date)obj[3]).toString());
		op.setPlanDate(str);
		 isValid=DateCompare1.isValidDate(mydate);
		
		}
		if(obj[4]!=null){
			op.setUnfactAmt(fmt.format(((BigDecimal) obj[4])));
			totalUnfactAmtAll=totalUnfactAmtAll.add((BigDecimal) obj[4]);
			//totalUnfactAmt=totalUnfactAmt.add((BigDecimal) obj[4]);
		}
		if(obj[5]!=null){
			op.setFactAmt(fmt.format(((BigDecimal) obj[5])));
			totalFactAmtAll=totalFactAmtAll.add((BigDecimal) obj[5]);
			//totalFactAmt=totalFactAmt.add((BigDecimal) obj[5]);
		}
		if(obj[6]!=null)
		op.setMargin(((BigDecimal)obj[6]).toString());
		if(obj[7]!=null)
		op.setStage((String)obj[7]);
		
		
		
		if(obj[8]!=null){
			op.setOppNumber((String)obj[8]);
		}
		if(obj[9]!=null){
			//change for bug no-38  date:-12-may-2017
			BigDecimal bd=(BigDecimal)obj[9];
			op.setProbability(bd.setScale(0, BigDecimal.ROUND_UP).toString());
			//op.setProbability(((BigDecimal)obj[9]).toString());
		}
		if(obj[10]!=null){
			op.setSalesLeadRep((String)obj[10]);
		}
		if(obj[11]!=null && "I".equalsIgnoreCase((String)obj[11])){
			isActive="I";
		}
		if(obj[11]!=null){
			op.setOppStatus((String)obj[11]);
		}
		if("N".equalsIgnoreCase(isValid)){
			op.setIsRemoved("Y");	
		}
		if("I".equalsIgnoreCase(isActive)){
			op.setIsRemoved("Y");	
		}
		if("N".equalsIgnoreCase(op.getIsRemoved()) &&  obj[4]!=null){
			totalUnfactAmt=totalUnfactAmt.add((BigDecimal) obj[4]);
			
		}
		if("N".equalsIgnoreCase(op.getIsRemoved()) &&  obj[5]!=null){
			
			totalFactAmt=totalFactAmt.add((BigDecimal) obj[5]);
		}
		//adding new fields 19 APril
		if(obj[12]!=null){
		op.setBranch((String)obj[12]);
		}
		if(obj[13]!=null){
			op.setLob((String)obj[13]);
		}
		if(obj[14]!=null){
			op.setCurrency((String)obj[14]);
			
		}
		if(obj[15]!=null){
			
			//op.setUnfactAmt(fmt.format(((BigDecimal) obj[4])));
			op.setFactAmtLocalCur(fmt.format((BigDecimal)obj[15]).toString());
			op.setFactAmtLocalCur(op.getFactAmtLocalCur().substring(1));
			//op.setFactAmtLocalCur(((BigDecimal)obj[15]).toString());
		}
		if(obj[16]!=null){
			op.setUnfactAmtLocalCur(fmt.format((BigDecimal)obj[16]).toString());
			op.setUnfactAmtLocalCur(op.getUnfactAmtLocalCur().substring(1));
			//op.setUnfactAmtLocalCur(((BigDecimal)obj[16]).toString());
		}
		if(obj[17]!=null){
			op.setSalesManager((String)obj[17]);
		}
		if(obj[18]!=null){
			op.setLeadsource((String)obj[18]);
			
		}
		if(obj[19]!=null){
			op.setMustWinflag(((Boolean)obj[19]).toString());
		}
		if(obj[20]!=null){
			op.setOppSubmitStatus((String)obj[20]);
		}
		if(obj[21]!=null){
			op.setSourceType((String)obj[21]);
		}
		if(obj[22]!=null){
			op.setSfdcId((String)obj[22]);
		}
		if(obj[23]!=null){
			op.setCountry((String)obj[23]);
		}
		if(obj[24]!=null){
			op.setSubRegion((String)obj[24]);
		}
		if(obj[25]!=null){
			op.setRegion((String)obj[25]);
		}
		opps.add(op);
		
	}
	/*response.setTotalFactAmt(fmt.format(totalFactAmt));
	response.setTotalUnfactAmt(fmt.format(totalUnfactAmt));*/
	response.setTotalFactAmt(fmt.format(totalFactAmtAll));
	response.setTotalUnfactAmt(fmt.format(totalUnfactAmtAll));
	response.setTotalCurMonthsfactAmt(fmt.format(totalFactAmt));
	response.setTotalCurMonthsUnfactAmt(fmt.format(totalUnfactAmt));
	//old code
	//response.setOppData(opps)  
	response.setOppData(getFinalOppsWithSfdcUrl(opps));
	
	
	
	return response;
}

public ForecastOutVO submitForecastALL(SubmitFCInputVO dto, String golbalId,
		String userRole) {
	Random rand = new Random();

	int  n1 = rand.nextInt() + 1;
	//System.out.println("n1"+n1);
	String st=""+n1;
	//System.out.println(st.substring(1, 6));
	//String str="BC"+st.substring(1, 6);
	String str="";
	str=st.substring(1, 6);
	
	
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
		str="H"+st.substring(1, 6);
	}
	if("Regional Forecaster".equalsIgnoreCase(userRole)){
		str="R"+st.substring(1, 6);
	}
	if("Country Forecaster".equalsIgnoreCase(userRole)){
		str="C"+st.substring(1, 6);
	}
	if("Branch Forecaster".equalsIgnoreCase(userRole)){
		str="B"+st.substring(1, 6);
	}
	if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
		str="S"+st.substring(1, 6);
	}
	System.out.println("Final code "+str);
	List<OpportunityDTO> opps=dto.getOppData();
	/*for(OpportunityDTO opp:opps ){
		opp.setForecastId(str);
		ForecastDao.submitForecastBFC(opp, golbalId, userRole);
	}
	*/
	for(OpportunityDTO opp:opps ){
		opp.setForecastId(str);
		
	}
	try {
		ForecastDao.submitForecastBFCJdbcBatchUpdate(opps, golbalId, userRole);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ReportGridInputMultipleVO reportGridInputMultipleVO=new ReportGridInputMultipleVO();
	reportGridInputMultipleVO.setBranches(dto.getBranches());
	reportGridInputMultipleVO.setCountries(dto.getCountries());
	reportGridInputMultipleVO.setRegions(dto.getRegions());
	reportGridInputMultipleVO.setSubregions(dto.getSubregions());
	//Calling function for Submit forecast START
	ForecastInputVO vo=new ForecastInputVO();
	
	String sourceType="";
	List<ValueDTO> typeSources=new ArrayList<ValueDTO>();
	ValueDTO sdto=new ValueDTO();
	try {
		/*if(!("Branch Forecaster".equalsIgnoreCase(userRole))){
			//sourceType=loginDao.getSorceTypeByGlobalId(golbalId);
			//sdto.setName(loginDao.getSorceTypeByGlobalId(golbalId));
			//typeSources.add(sdto);
			//reportGridInputMultipleVO.setTypeSources(typeSources);
			//pppp
		}*/
		vo.setFetchJson(reportGridInputMultipleVO);
		functionCallForSubmitForecast(vo, golbalId, userRole,new String());
	} catch (Exception e) {
		System.out.println("Exception in functionCallForSubmitForecast service exception is "+e.getMessage());
		// TODO: handle exception
	}
	//Calling function for Submit forecast END
	
	//ForecastOutVO response=getDynamicOpportunity(reportGridInputMultipleVO);
	ForecastOutVO response=new ForecastOutVO();
	response.setSaved(true);
	 
	return response;
}

//*************Re-Submit service START
public ForecastOutVO reSubmitForecastALL(SubmitFCInputVO dto, String golbalId,
		String userRole) {
	Random rand = new Random();

	int  n1 = rand.nextInt() + 1;
	//System.out.println("n1"+n1);
	String st=""+n1;
	//System.out.println(st.substring(1, 6));
	//String str="BC"+st.substring(1, 6);
	String str="";
	str=st.substring(1, 6);
	
	
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
		str="H"+st.substring(1, 6);
	}
	if("Regional Forecaster".equalsIgnoreCase(userRole)){
		str="R"+st.substring(1, 6);
	}
	if("Country Forecaster".equalsIgnoreCase(userRole)){
		str="C"+st.substring(1, 6);
	}
	if("Branch Forecaster".equalsIgnoreCase(userRole)){
		str="B"+st.substring(1, 6);
	}
	if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
		str="S"+st.substring(1, 6);
	}
	System.out.println("Final code "+str);
	List<OpportunityDTO> opps=dto.getOppData();
	/*for(OpportunityDTO opp:opps ){
		opp.setForecastId(str);
		ForecastDao.submitForecastBFC(opp, golbalId, userRole);
	}
	*/
	for(OpportunityDTO opp:opps ){
		opp.setForecastId(str);
		
	}
	try {
		
		ForecastDao.reSubmitForecastBFCJdbcBatchUpdate(opps, golbalId, userRole);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	ReportGridInputMultipleVO reportGridInputMultipleVO=new ReportGridInputMultipleVO();
	reportGridInputMultipleVO.setBranches(dto.getBranches());
	reportGridInputMultipleVO.setCountries(dto.getCountries());
	reportGridInputMultipleVO.setRegions(dto.getRegions());
	reportGridInputMultipleVO.setSubregions(dto.getSubregions());
	//Calling function for Submit forecast START
	ForecastInputVO vo=new ForecastInputVO();
	vo.setFetchJson(reportGridInputMultipleVO);
	try {
		
		functionCallForSubmitForecast(vo, golbalId, userRole,"Y");
	} catch (Exception e) {
		System.out.println("Exception in functionCallForSubmitForecast service exception is "+e.getMessage());
		// TODO: handle exception
	}
	//Calling function for Submit forecast END
	
	//ForecastOutVO response=getDynamicOpportunity(reportGridInputMultipleVO);
	ForecastOutVO response=new ForecastOutVO();
	response.setSaved(true);
	 
	return response;
}
//**********Re-SUbmit Service END*******************


public AddNewOpportunity getAddNewOpportunity(String globalId,String userRole) {
	// TODO Auto-generated method stub
	String sources = "";
	String regions = "";
	String subRegions = "";
	String countries = "";
	String branches = "";
	
	AddNewOpportunity addNewOpportunity=new AddNewOpportunity();
	
	ReportInputMultipleVO  reportInputMultipleVO=new   ReportInputMultipleVO();
	
     List<ValueDTO> regionsList=new ArrayList<ValueDTO>();
	 List<ValueDTO> countriesList=new ArrayList<ValueDTO>();
	 List<ValueDTO> subregionsList=new ArrayList<ValueDTO>();
	 List<ValueDTO> typeSourcesList=new ArrayList<ValueDTO>();
	 List<ValueDTO> branchesList=new ArrayList<ValueDTO>();
	
	List<Object[]> getopprList=addNewOpportunityDao.getAddNewOpportunity(globalId);
	
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
		
		
		
		for(Object[] value:getopprList)		
		   {				
			addNewOpportunity.setSfid((String)value[1]);
			addNewOpportunity.setGlobalid((String)value[2]);
			addNewOpportunity.setUserid(((Integer)value[3]).toString());
			addNewOpportunity.setUser_name((String)value[4]);
			addNewOpportunity.setManager_name((String)value[5]);
			addNewOpportunity.setForecast_rolecode((String)value[6]);
			addNewOpportunity.setLob((String)value[11]);
									
		    }
		
	    List<DropDownVO> getSourcetype=reportService.getDataSource();
		
		for (DropDownVO  dropdwnval : getSourcetype) {
			ValueDTO sourceTypeDTO=new ValueDTO();
			sourceTypeDTO.setName(dropdwnval.getName());
			typeSourcesList.add(sourceTypeDTO);
		}
		addNewOpportunity.setSourceType(typeSourcesList);
		
		
	}
	
	
	if("Regional Forecaster".equalsIgnoreCase(userRole)){
		
		
		
		
		for(Object[] value:getopprList)		
		   {
			sources=(String)value[0];
			regions=(String)value[7];
			addNewOpportunity.setSelectedSourceType(sources);
			addNewOpportunity.setSfid((String)value[1]);
			addNewOpportunity.setGlobalid((String)value[2]);
			if(value[3]!=null)
			addNewOpportunity.setUserid(((Integer)value[3]).toString());
			addNewOpportunity.setUser_name((String)value[4]);
			addNewOpportunity.setManager_name((String)value[5]);
			addNewOpportunity.setForecast_rolecode((String)value[6]);
			addNewOpportunity.setSelectedregion(regions);
			addNewOpportunity.setLob((String)value[11]);
									
		    }
		
		ValueDTO sourceDTO=new ValueDTO();
		sourceDTO.setName(sources);
		typeSourcesList.add(sourceDTO);
		
		
		
		ValueDTO regionDTO=new ValueDTO();
		System.out.println(regions);
		regionDTO.setName(regions);
		regionsList.add(regionDTO);
		
		reportInputMultipleVO.setTypeSources(typeSourcesList);
		reportInputMultipleVO.setRegions(regionsList);
		
		addNewOpportunity.setSourceType(typeSourcesList);
		addNewOpportunity.setRegion(regionsList);
		
		
		
		
		
		List<DropDownVO> subreglist=reportService.getSubRegionbyRegion(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : subreglist) {
			ValueDTO subregion=new ValueDTO();
			subregion.setName(dropdwnval.getName());
			subregionsList.add(subregion);
		}
		
		reportInputMultipleVO.setSubregions(subregionsList);
		addNewOpportunity.setSubRegion(subregionsList);
		
		
		List<DropDownVO> getcountry=reportService.getcountry(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : getcountry) {
			ValueDTO country=new ValueDTO();
			country.setName(dropdwnval.getName());
			countriesList.add(country);
		}
		
		reportInputMultipleVO.setCountries(countriesList);
		addNewOpportunity.setCountry(countriesList);
		
		
      List<DropDownVO> getBranch=reportService.getBranches(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : getBranch) {
			ValueDTO Branch=new ValueDTO();
			Branch.setName(dropdwnval.getName());
			branchesList.add(Branch);
		}
		
		reportInputMultipleVO.setBranches(branchesList);
		addNewOpportunity.setBranch(branchesList);
		
		
	}
	if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
		
		
		for(Object[] value:getopprList)		
		   {
			sources=(String)value[0];
			regions=(String)value[7];
			countries=(String)value[9];
			subRegions=(String)value[8];
			
			addNewOpportunity.setSelectedSourceType(sources);
			addNewOpportunity.setSfid((String)value[1]);
			addNewOpportunity.setGlobalid((String)value[2]);
			addNewOpportunity.setUserid(((Integer)value[3]).toString());
			addNewOpportunity.setUser_name((String)value[4]);
			addNewOpportunity.setManager_name((String)value[5]);
			addNewOpportunity.setForecast_rolecode((String)value[6]);
			addNewOpportunity.setSelectedregion(regions);
			addNewOpportunity.setSelectedcountry(countries);
			addNewOpportunity.setSelectedsubRegion(subRegions);
			addNewOpportunity.setLob((String)value[11]);
									
		    }
		
		ValueDTO sourceDTO=new ValueDTO();
		sourceDTO.setName(sources);
		typeSourcesList.add(sourceDTO);
		
		
		
		ValueDTO regionDTO=new ValueDTO();
		System.out.println(regions);
		regionDTO.setName(regions);
		regionsList.add(regionDTO);
		
		
		ValueDTO subRegionDTO=new ValueDTO();
		System.out.println(subRegions);
		subRegionDTO.setName(subRegions);
		subregionsList.add(subRegionDTO);
		
		reportInputMultipleVO.setTypeSources(typeSourcesList);
		reportInputMultipleVO.setRegions(regionsList);
		reportInputMultipleVO.setSubregions(subregionsList);
		
		addNewOpportunity.setSourceType(typeSourcesList);
		addNewOpportunity.setRegion(regionsList);
		addNewOpportunity.setSubRegion(subregionsList);
		
		
		List<DropDownVO> getcountry=reportService.getcountry(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : getcountry) {
			ValueDTO country=new ValueDTO();
			country.setName(dropdwnval.getName());
			countriesList.add(country);
		}
		
		reportInputMultipleVO.setCountries(countriesList);
		addNewOpportunity.setCountry(countriesList);
		
		
   List<DropDownVO> getBranch=reportService.getBranches(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : getBranch) {
			ValueDTO Branch=new ValueDTO();
			Branch.setName(dropdwnval.getName());
			branchesList.add(Branch);
		}
		
		reportInputMultipleVO.setBranches(branchesList);
		addNewOpportunity.setBranch(branchesList);
		
		
	}
	
	
	if("Country Forecaster".equalsIgnoreCase(userRole)){
		
		
		for(Object[] value:getopprList)		
		   {
			sources=(String)value[0];
			regions=(String)value[7];
			//subRegions=(String)value[8];
			countries=(String)value[9];
			
			addNewOpportunity.setSelectedSourceType(sources);
			addNewOpportunity.setSfid((String)value[1]);
			addNewOpportunity.setGlobalid((String)value[2]);
			addNewOpportunity.setUserid(((Integer)value[3]).toString());
			addNewOpportunity.setUser_name((String)value[4]);
			addNewOpportunity.setManager_name((String)value[5]);
			addNewOpportunity.setForecast_rolecode((String)value[6]);
			addNewOpportunity.setSelectedregion(regions);
			//addNewOpportunity.setSelectedsubRegion(subRegions);
			addNewOpportunity.setSelectedcountry(countries);
			addNewOpportunity.setLob((String)value[11]);
									
		    }
		
		ValueDTO sourceDTO=new ValueDTO();
		sourceDTO.setName(sources);
		typeSourcesList.add(sourceDTO);
		
		
		
		ValueDTO regionDTO=new ValueDTO();
		System.out.println(regions);
		regionDTO.setName(regions);
		regionsList.add(regionDTO);
		
		
	/*	ValueDTO subRegionDTO=new ValueDTO();
		System.out.println(subRegions);
		subRegionDTO.setName(subRegions);
		subregionsList.add(subRegionDTO);*/
		
		
		ValueDTO countryDTO=new ValueDTO();
		System.out.println(countries);
		countryDTO.setName(countries);
		countriesList.add(countryDTO);
		
		reportInputMultipleVO.setTypeSources(typeSourcesList);
		reportInputMultipleVO.setRegions(regionsList);
		//reportInputMultipleVO.setSubregions(subregionsList);
		reportInputMultipleVO.setCountries(countriesList);
		
		addNewOpportunity.setSourceType(typeSourcesList);
		addNewOpportunity.setRegion(regionsList);
		//addNewOpportunity.setSubRegion(subregionsList);
		addNewOpportunity.setCountry(countriesList);
		
		
	List<DropDownVO> subreglist=reportService.getSubRegionbyRegion(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : subreglist) {
			ValueDTO subregion=new ValueDTO();
			subregion.setName(dropdwnval.getName());
			subregionsList.add(subregion);
		}
		
		reportInputMultipleVO.setSubregions(subregionsList);
		addNewOpportunity.setSubRegion(subregionsList);
		
		
   List<DropDownVO> getBranch=reportService.getBranches(reportInputMultipleVO);
		
		for (DropDownVO  dropdwnval : getBranch) {
			ValueDTO Branch=new ValueDTO();
			Branch.setName(dropdwnval.getName());
			branchesList.add(Branch);
		}
		
		reportInputMultipleVO.setBranches(branchesList);
		addNewOpportunity.setBranch(branchesList);
		
		
	}
	
	if("Branch Forecaster".equalsIgnoreCase(userRole)){
		
		
		for(Object[] value:getopprList)		
		   {
			sources=(String)value[0];
			regions=(String)value[7];
			subRegions=(String)value[8];
			countries=(String)value[9];
			branches=(String)value[10];
			addNewOpportunity.setSelectedSourceType(sources);
			addNewOpportunity.setSfid((String)value[1]);
			addNewOpportunity.setGlobalid((String)value[2]);
			addNewOpportunity.setUserid(((Integer)value[3]).toString());
			addNewOpportunity.setUser_name((String)value[4]);
			addNewOpportunity.setManager_name((String)value[5]);
			addNewOpportunity.setForecast_rolecode((String)value[6]);
			addNewOpportunity.setSelectedregion(regions);
			addNewOpportunity.setSelectedsubRegion(subRegions);
			addNewOpportunity.setSelectedcountry(countries);
			addNewOpportunity.setSelectedbranch(branches);
			addNewOpportunity.setLob((String)value[11]);
									
		    }
		
		ValueDTO sourceDTO=new ValueDTO();
		sourceDTO.setName(sources);
		typeSourcesList.add(sourceDTO);
		
		
		
		ValueDTO regionDTO=new ValueDTO();
		System.out.println(regions);
		regionDTO.setName(regions);
		regionsList.add(regionDTO);
		
		
		ValueDTO subRegionDTO=new ValueDTO();
		System.out.println(subRegions);
		subRegionDTO.setName(subRegions);
		subregionsList.add(subRegionDTO);
		
		
		ValueDTO countryDTO=new ValueDTO();
		System.out.println(countries);
		countryDTO.setName(countries);
		countriesList.add(countryDTO);
		
		
		ValueDTO branchesDTO=new ValueDTO();
		System.out.println(branches);
		branchesDTO.setName(branches);
		branchesList.add(branchesDTO);
		
		
		reportInputMultipleVO.setTypeSources(typeSourcesList);
		reportInputMultipleVO.setRegions(regionsList);
		reportInputMultipleVO.setSubregions(subregionsList);
		reportInputMultipleVO.setCountries(countriesList);
		reportInputMultipleVO.setBranches(branchesList);
		
		addNewOpportunity.setSourceType(typeSourcesList);
		addNewOpportunity.setRegion(regionsList);
		addNewOpportunity.setSubRegion(subregionsList);
		addNewOpportunity.setCountry(countriesList);
		addNewOpportunity.setBranch(branchesList);
		
		
		
	}
	
	
	List<String> resultSet = ForecastDao.getLob();
	
	List<DropDownVO> lobResponse = new ArrayList<DropDownVO>();
	for (String str : resultSet) {
		DropDownVO dd = new DropDownVO();
		// dd.setId(Integer.parseInt(str));
		dd.setName(str);
		lobResponse.add(dd);
	}
	ReportOnLoadDTO lobres = new ReportOnLoadDTO();
	lobres.setDropdownValues(lobResponse);
	
	addNewOpportunity.setGetlob(lobres);
	
	List<String> currencyresultSet = ForecastDao.getCurrency();
	List<DropDownVO> currencyresponse = new ArrayList<DropDownVO>();
	for (String str : currencyresultSet) {
		DropDownVO dd = new DropDownVO();
		// dd.setId(Integer.parseInt(str));
		dd.setName(str);
		currencyresponse.add(dd);
	}
	
	ReportOnLoadDTO currencyres = new ReportOnLoadDTO();
	currencyres.setDropdownValues(currencyresponse);
	
	addNewOpportunity.setGetCurrency(currencyres);
	
	List<String> stageResultlist = ForecastDao.getStage();
	List<DropDownVO> stageResponse = new ArrayList<DropDownVO>();
	
	for (String str : stageResultlist) {
		DropDownVO dd = new DropDownVO();
		// dd.setId(Integer.parseInt(str));
		dd.setName(str);
		stageResponse.add(dd);
	}
	
	ReportOnLoadDTO stageRes = new ReportOnLoadDTO();
	stageRes.setDropdownValues(stageResponse);
	addNewOpportunity.setStageName(stageRes);
	String strFinal="";
	try {
		Random rand = new Random();

	int  n1 = rand.nextInt() + 1;
	String st=""+n1;
	String str="";
	
	
    str=st.substring(1,5);
	char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	StringBuilder sb = new StringBuilder();
	Random random = new Random();
	for (int i = 0; i < 7; i++) {
	    char c = chars[random.nextInt(chars.length)];
	    sb.append(c);
	}
	String output = sb.toString();
	output=output+str;
	System.out.println(output);  
	
	strFinal=WFTConstant.OPP_NUM_PREFIX+output;
	addNewOpportunity.setOpportunityNumber(strFinal);
		
	} catch (Exception e) {
		// TODO: handle exception
	}  
	
	
	return addNewOpportunity;
}

public String SaveAddNewOpportunity(SaveOpportunityInputVO saveOpportunityInputVO, String globalId,String userRole,String userName) {
	// TODO Auto-generated method stub
	String  value =ForecastDao.SaveAddNewOpportunity(saveOpportunityInputVO,globalId,userRole,userName);
	
	ReportGridInputMultipleVO callFucDTO=new ReportGridInputMultipleVO();
	
	
	 List<ValueDTO> typeSources=new ArrayList<ValueDTO>();
	 List<ValueDTO> regions=new ArrayList<ValueDTO>();
	 List<ValueDTO> countries=new ArrayList<ValueDTO>();
	 List<ValueDTO> subregions=new ArrayList<ValueDTO>();
	 List<ValueDTO> branches=new ArrayList<ValueDTO>();
	 
	 ValueDTO typeSourcesDto=new ValueDTO();
	 typeSourcesDto.setName(saveOpportunityInputVO.getOppDataSource());
	 typeSources.add(typeSourcesDto);
	
	 
	 ValueDTO regionsDto=new ValueDTO();
	 regionsDto.setName(saveOpportunityInputVO.getRegion());
	 regions.add(regionsDto);
	 
	 
	 ValueDTO countriesDto=new ValueDTO();
	 countriesDto.setName(saveOpportunityInputVO.getJciReportingCountry());
	 countries.add(countriesDto);
	 
	 
	 ValueDTO subregionsDto=new ValueDTO();
	 subregionsDto.setName(saveOpportunityInputVO.getSubRegion());
	 subregions.add(subregionsDto);
	 
	 ValueDTO bracnchDto=new ValueDTO();
	 bracnchDto.setName(saveOpportunityInputVO.getBranchCode());
	 branches.add(bracnchDto);
	 
	 
	 
	 callFucDTO.setTypeSources(typeSources); 
	 callFucDTO.setRegions(regions);
	 callFucDTO.setSubregions(subregions);
	 callFucDTO.setCountries(countries);
	 callFucDTO.setBranches(branches);
	 
	 ForecastInputVO forecastInputVO=new ForecastInputVO();
	 forecastInputVO.setFetchJson(callFucDTO);
	OpportunityDTO d=new OpportunityDTO();
	d.setSourceType(saveOpportunityInputVO.getOppDataSource());
	d.setRegion(saveOpportunityInputVO.getRegion());
	d.setCountry(saveOpportunityInputVO.getJciReportingCountry());
	d.setSubRegion(saveOpportunityInputVO.getSubRegion());
	d.setBranch(saveOpportunityInputVO.getBranchCode());
	functionCallForditAndSave(d,userName,userRole,"A");
	
	return value;
}

public List<DropDownVO> getLob() {
	// TODO Auto-generated method stub
	List<String> resultSet = ForecastDao.getLob();
	List<DropDownVO> response = new ArrayList<DropDownVO>();
	for (String str : resultSet) {
		DropDownVO dd = new DropDownVO();
		// dd.setId(Integer.parseInt(str));
		if(str!=null && !(str.isEmpty())){
		dd.setName(str);
		}
		response.add(dd);
	}
	return response;
	
}

public List<DropDownVO> getCurrency() {
	// TODO Auto-generated method stub
		List<String> resultSet = ForecastDao.getCurrency();
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();
			// dd.setId(Integer.parseInt(str));
			dd.setName(str);
			response.add(dd);
		}
		return response;
}

public List<DropDownVO> getAccountName(AccountNameInputVO accountNameInputVO) {
	// TODO Auto-generated method stub
	List<Object[]> resultSet = ForecastDao.getAccountName(accountNameInputVO);
	List<DropDownVO> response = new ArrayList<DropDownVO>();
	for (Object[] str : resultSet) {
		DropDownVO dd = new DropDownVO();
		
		dd.setAccountName((String)str[0]);
		dd.setName((String)str[1]);
		response.add(dd);
	}
	return response;
}

public String getRate(ConvertedAmountDTO convertedAmountDTO) {
	// TODO Auto-generated method stub
	String  value="";
	if(convertedAmountDTO.getCurrency()!=null && !(convertedAmountDTO.getCurrency().isEmpty()) && convertedAmountDTO.getSourceType()!=null && !(convertedAmountDTO.getSourceType().isEmpty())){
		  value=ForecastDao.getRate("Y",convertedAmountDTO.getCurrency(),convertedAmountDTO.getSourceType());
		//op.setConvertedUnfactoredAmt(ForecastDao.getConvertAmount(op.getUnFactoredAmount(),"N",opp.getCurrency(),opp.getSourceType()));
	}
	return value;
}

public ForecastOutVO getRefresh(ReportGridInputMultipleVO dto,String globalId) {
	// TODO Auto-generated method stub
	String s1=null;
	StringBuffer sb=new StringBuffer("SELECT webforecastdev.fn_load_forecast_opp_data(");
	sb.append("'"+dto.getTypeSources().get(0).getName()+"',");
	//sb.append("'',");
	sb.append(s1+",");
	sb.append("'"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"',");
	sb.append("'On Demand',");
	sb.append("'"+dto.getRegions().get(0).getName()+"',");  //'China',  --Region 
	sb.append("'"+dto.getCountries().get(0).getName()+"',");  //'China',  -- country  
	sb.append("'',"); //'', --sub region
	sb.append("'"+dto.getBranches().get(0).getName()+"',");  //--branch 
	sb.append("'',"); //'', --lob
	sb.append("'"+globalId+"');");          //'chreg' – login user global id
	System.out.println("Final Refresh Query "+sb.toString());
	ForecastDao.getRefresh(sb.toString());
	
	
	//return getDynamicOpportunity(dto);
	return new ForecastOutVO();
}
public String callDataPullFunction(String queryString){
	queryString="Select webforecastdev.fn_execute_opp_data_routine()";
	ForecastDao.callDataPullFunction(queryString);
	return null;
}

public List<OpportunityDTO> getFinalOppsWithSfdcUrl(List<OpportunityDTO> inOpps){
	
	for(OpportunityDTO op:inOpps){
		if(op.getSfdcId()!=null && !(op.getSfdcId().isEmpty()) && op.getSourceType()!=null ){
			op.setIsRedirect(true);
			if("OpenGlobe".equalsIgnoreCase(op.getSourceType().trim())){
				op.setSfdcUrl(getBeSfdcUrl(op.getSfdcId()));
			}
			if("WW".equalsIgnoreCase(op.getSourceType().trim())){
				op.setSfdcUrl(getTycoWWSfdcUrl(op.getSfdcId()));
			}
			if("Wormald".equalsIgnoreCase(op.getSourceType().trim())){
				op.setSfdcUrl(getTycoWorMaldSfdcUrl(op.getSfdcId()));
			}
			
		}
	}
	return inOpps;
}
public String getBeSfdcUrl(String sfdcId){
	String url=WFTConstant.SFDC_BE_URL+sfdcId;
	return url;
}
public String getTycoWWSfdcUrl(String sfdcId){
	String url=WFTConstant.SFDC_WW_URL+sfdcId;
	return url;

}
public String getTycoWorMaldSfdcUrl(String sfdcId){
	String url=WFTConstant.SFDC_WM_URL+sfdcId;
	return url;
}

public ForecastOutVO functionCallForditAndSave(OpportunityDTO input,
		String globalId, String userRole,String isEdit) {
	// TODO Auto-generated method stub
	/*SELECT webforecastdev.fn_update_forecast_summary(
			 para1= 'May-2017', --Month year 
			 para2='Edit', --Event 
			 para3='admin', --Global ID 
			 para4='B', ---user role code 
			 para5='OpenGlobe',  -- data source Required only if parent page has. e.g. for branch user it is required 
			 para6='South East Asia', --region
			 para7='', --subregion
			 para8='Malaysia Ymss',  --country
			 para9='MY - YMSS - KLHQ'
			)
	*/
	
	StringBuffer sb=new StringBuffer("SELECT webforecastdev.fn_update_forecast_summary(");
	String para1,para3="";
	String para2="";
	String para4="";
	String para5="";
	String para6="";
	String para7="";
	String para8="";
	String para9="";
	para1="'"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'";
	sb.append(para1+",");
	if("E".equalsIgnoreCase(isEdit)){
		para2="Edit";
	}
	if("D".equalsIgnoreCase(isEdit)){
		para2="Delete";
	}
	if("A".equalsIgnoreCase(isEdit)){
		para2="Add";
	}
	else{
		
	}
	sb.append("'"+para2+"',");
	para3=globalId;
	sb.append("'"+para3+"',");
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
		para4="A";
	}
	if("Regional Forecaster".equalsIgnoreCase(userRole)){
		para4="R";
	}
	if("Country Forecaster".equalsIgnoreCase(userRole)){
		para4="C";
	}
	if("Branch Forecaster".equalsIgnoreCase(userRole)){
		para4="B";
	}
	if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
		para4="SR";
	}
	sb.append("'"+para4+"',");
	if(input.getSourceType()!=null){
		{
			para5="'"+input.getSourceType()+"'";
		}
		
	}
	else{
		para5="''";
	}
	sb.append(para5+",");
	
	if(input.getRegion()!=null){
		{
			para6="'"+input.getRegion()+"'";
		}
	}
	else{
		para6="''";
	}
	sb.append(para6+",");
	
	if(input.getSubRegion()!=null ){
		{
			para7="'"+input.getSubRegion()+"'";
		}
		
	}
	else{
		para7="''";
	}
	sb.append(para7+",");
	
	if(input.getCountry()!=null){
		{
			para8="'"+input.getCountry()+"'";
		}
		
	}
	else{
		para8="''";
	}
	sb.append(para8+",");
	
	
	if(input.getBranch()!=null){
		{
			para9="'"+input.getBranch()+"'";
		}
	
	}
	else{
		para9="''";
	}
	sb.append(para9);
	
	sb.append(")");
	//System.out.println("FInal query for Branch Summary FUNCTION is "+sb);
	ForecastDao.callFunctionForEditSaveDelete(sb.toString());
	return null;
}

public ForecastOutVO functionCallForSubmitForecast(ForecastInputVO input,
		String globalId, String userRole,String isResubmit) {
	/*if("Branch Forecaster".equalsIgnoreCase(userRole)){
	String source=loginDao.getSorceTypeByGlobalId(globalId);
	List<ValueDTO> v=new ArrayList<ValueDTO>();
	ValueDTO sources=new ValueDTO();
	sources.setName(source);
	v.add(sources);
	input.getFetchJson().setTypeSources(v);
	}*/
	// TODO Auto-generated method stub
	/*SELECT webforecastdev.fn_update_forecast_summary(
			 para1= 'May-2017', --Month year 
			 para2='Edit', --Event 
			 para3='admin', --Global ID 
			 para4='B', ---user role code 
			 para5='OpenGlobe',  -- data source Required only if parent page has. e.g. for branch user it is required 
			 para6='South East Asia', --region
			 para7='', --subregion
			 para8='Malaysia Ymss',  --country
			 para9='MY - YMSS - KLHQ'
			)
	*/
	
	StringBuffer sb=new StringBuffer("SELECT webforecastdev.fn_submit_forecast_data(");
	String para1,para2,para3="";
	String para4="";
	String para5="";
	String para6="";
	String para7="";
	String para8="";
	String para9="";
	para1="'"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'";
	sb.append(para1+",");
	if(isResubmit !=null && "Y".equalsIgnoreCase(isResubmit)){
		para2="Resubmit";
	}
	else
	{
	para2="Submit";
	}
	sb.append("'"+para2+"',");
	para3=globalId;
	sb.append("'"+para3+"',");
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
		para4="A";
	}
	if("Regional Forecaster".equalsIgnoreCase(userRole)){
		para4="R";
	}
	if("Country Forecaster".equalsIgnoreCase(userRole)){
		para4="C";
	}
	if("Branch Forecaster".equalsIgnoreCase(userRole)){
		para4="B";
	}
	if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
		para4="SR";
	}
	sb.append("'"+para4+"',");
	if(input.getFetchJson().getTypeSources()!=null){
		if(input.getFetchJson().getTypeSources().get(0).getName()!=null){
			para5="'"+input.getFetchJson().getTypeSources().get(0).getName()+"'";
		}
		else{
			para5="''";
		}
	}
	else{
		para5="''";
	}
	sb.append(para5+",");
	
	if(input.getFetchJson().getRegions()!=null && !(input.getFetchJson().getRegions().isEmpty())){
		if(input.getFetchJson().getRegions().get(0).getName()!=null){
			para6="'"+input.getFetchJson().getRegions().get(0).getName()+"'";
		}
		else{
			para6="''";
		}
	}
	else{
		para6="''";
	}
	sb.append(para6+",");
	
	if(input.getFetchJson().getSubregions()!=null){
		//if(input.getFetchJson().getSubregions().get(0).getName()!=null && !("NA".equalsIgnoreCase(input.getFetchJson().getSubregions().get(0).getName())) ){
		if(input.getFetchJson().getSubregions().get(0).getName()!=null ){
			para7="'"+input.getFetchJson().getSubregions().get(0).getName()+"'";
		}
		else{
			para7="''";
		}
	}else{
		para7="''";
	}
	sb.append(para7+",");
	
	if(input.getFetchJson().getCountries()!=null){
		if(input.getFetchJson().getCountries().get(0).getName()!=null){
			para8="'"+input.getFetchJson().getCountries().get(0).getName()+"'";
		}
		else{
			para8="''";
		}
	}else{
		para8="''";
	}
	sb.append(para8+",");
	
	
	if(input.getFetchJson().getBranches()!=null){
		if(input.getFetchJson().getBranches().get(0).getName()!=null){
			para9="'"+input.getFetchJson().getBranches().get(0).getName()+"'";
		}
		else{
			para9="''";
		}
	}else{
		para9="''";
	}
	sb.append(para9);
	
	sb.append(")");
	//System.out.println("FInal query for Branch Summary FUNCTION is "+sb);
	ForecastDao.callFunctionForEditSaveDelete(sb.toString());
	return null;
}

public ForecastOutVO getRefreshParent(ReportGridInputMultipleVO dto,
		String globalId) {
	ForecastOutVO response=new ForecastOutVO();
	List<ValueDTO> typeSources=new ArrayList<ValueDTO>();
	ValueDTO d=new ValueDTO();
	d.setName(WFTConstant.SOURCE_OPENGLOAB);
	typeSources.add(d);
	System.out.println("FIRST call with sorce type START");
	dto.setTypeSources(typeSources);
	getRefresh(dto,globalId);
	System.out.println("FIRST call with sorce type END");
	//call second source  type
	List<ValueDTO> typeSources2=new ArrayList<ValueDTO>();
	ValueDTO d2=new ValueDTO();
	d2.setName(WFTConstant.SOURCE_WORMALD);
	typeSources2.add(d2);
	dto.setTypeSources(typeSources2);
	System.out.println("SECOND call with sorce type START");
	getRefresh(dto,globalId);
	System.out.println("SECOND call with sorce type END");
	//call third source  type
	List<ValueDTO> typeSources3=new ArrayList<ValueDTO>();
	ValueDTO d3=new ValueDTO();
	d3.setName(WFTConstant.SOURCE_WW);
	typeSources3.add(d3);
	dto.setTypeSources(typeSources3);
	System.out.println("THIRD call with sorce type START");
	getRefresh(dto,globalId);
	System.out.println("THIRD call with sorce type END");
	response=getDynamicOpportunity(dto);
	// TODO Auto-generated method stub
	return response;
}

public String callTempAutoSchedule(String queryString) {
	// TODO Auto-generated method stub
	ForecastDao.callTempAutoSchedule(queryString);
	
	return null;
}

public String isSubmittedForCountryFC(SubmitFCInputVO submitFCInputVO) {
	String country,region,subregion="";
	
	region=submitFCInputVO.getRegions().get(0).getName();
	country=submitFCInputVO.getCountries().get(0).getName();
	/*if(submitFCInputVO.getSubregions()!=null){
		subregion=submitFCInputVO.getSubregions().get(0).getName();	
	}
	else{
		subregion="NA";
	}*/
	
	String queryString="select count(1) from webforecastdev.forecast_branch_summary s where isactive = 'A' "
			+ " and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' "
			+ " and region ='"+region+"'"
			+ " and jci_reporting_country ='"+country+"' "
			/*+ " and sub_region ='"+subregion+"' "*/
			+ " and lower(forecast_status) <> 'submitted' ";
	
	BigInteger bd1=ForecastDao.isAllBranchSubmitted(queryString);
	BigInteger bd2=new BigInteger("0");
	int res=bd2.compareTo(bd1);
	if(res==0){
		return "Y";
	}
	else{
		return "N";
	}
	// TODO Auto-generated method stub
	
}

public String isSubmittedForSubRegionFC(SubmitFCInputVO submitFCInputVO) {
	String country,region,subregion="";
	
	region=submitFCInputVO.getRegions().get(0).getName();
	country=submitFCInputVO.getCountries().get(0).getName();
	if(submitFCInputVO.getSubregions()!=null){
		subregion=submitFCInputVO.getSubregions().get(0).getName();	
	}
	else{
		subregion="NA";
	}
	
	String queryString="select count(1) from webforecastdev.forecast_branch_summary s where isactive = 'A' "
			+ " and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' "
			+ " and region ='"+region+"'"
			+ " and jci_reporting_country ='"+country+"' "
			+ " and sub_region ='"+subregion+"' "
			+ " and lower(forecast_status) <> 'submitted' ";
	
	BigInteger bd1=ForecastDao.isAllBranchSubmitted(queryString);
	BigInteger bd2=new BigInteger("0");
	int res=bd2.compareTo(bd1);
	if(res==0){
		return "Y";
	}
	else{
		return "N";
	}
	// TODO Auto-generated method stub
	
}

public String isSubmittedForRegionalFC(SubmitFCInputVO submitFCInputVO) {
	String country,region,subregion="";
	
	region=submitFCInputVO.getRegions().get(0).getName();
	//country=submitFCInputVO.getCountries().get(0).getName();
	/*if(submitFCInputVO.getSubregions()!=null){
		subregion=submitFCInputVO.getSubregions().get(0).getName();	
	}
	else{
		subregion="NA";
	}*/
	
	String queryString="select count(1) from webforecastdev.forecast_country_summary s where isactive = 'A' "
			+ " and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' "
			+ " and region ='"+region+"'"
		/*	+ " and jci_reporting_country ='"+country+"' "
			+ " and sub_region ='"+subregion+"' "*/
			+ " and lower(forecast_status) <> 'submitted' ";
	
	BigInteger bd1=ForecastDao.isAllBranchSubmitted(queryString);
	BigInteger bd2=new BigInteger("0");
	int res=bd2.compareTo(bd1);
	if(res==0){
		return "Y";
	}
	else{
		return "N";
	}
	// TODO Auto-generated method stub
	
}
}
	
	
