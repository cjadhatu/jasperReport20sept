package com.java.main.service;



import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dao.RegionDao;
import com.java.main.dto.DynamicSummaryDTO;
import com.java.main.dto.LOBGridDTO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionDTO;
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.DateWindowUtil;
import com.java.main.utility.GetStringFromList;

@Service
public class RegionServiceImpl implements RegionService {

@Autowired
RegionDao regionDao;

@Autowired
UserService  userService;

public RegionOutPutDTO getRegionDeatils(RegionInputDTO regionInputDTO,String globalId,String user_role) {
	// TODO Auto-generated method stub
	
	RegionOutPutDTO response=new RegionOutPutDTO();
	List<RegionDTO> regionData=new ArrayList<RegionDTO>();
	/*private String totalOpportunites;
	private String totalfactAmt;
	private String totalUnfactAmt;
	private String totalCountries;
	private String totalSubRegions;
	private String totalBranches; 
	*/
	BigDecimal totalfactAmt=new BigDecimal(0);
	BigDecimal totalUnfactAmt=new BigDecimal(0);
	BigDecimal totalOpportunites=new BigDecimal(0);
	BigInteger totalbranches=new BigInteger("0");
	BigDecimal totalRollingfactAmt=new BigDecimal(0);
	BigDecimal totalRollingUnfactAmt=new BigDecimal(0);
	List<String> regionList=new ArrayList<String>();
	String regions="";
	/*String queryString="select distinct region,"
			 + "case when max(status_rank) = 3 then 'In progress'"
			   +" when  max(status_rank) = 2 then 'Submitted'"
			   +" else 'Not Started' end forecast_status"
			+ ",count(distinct region) region_count,"
			+ " count(distinct branch_code) branch_count, "
			+ " sum(count) opp_count,sum(converted_factored_amt) "
			+ " total_factored_amount,sum(converted_unfactored_amt) "
			+ " total_unfactored_amount,string_agg(updated_by,',') updated_by,max(updated_date) updated_date,"
			+ " sum(rolling_90days_factamt)  rolling_90days_factamt,sum(rolling_90days_unfactamt)  rolling_90days_unfactamt "
			+ "  from webforecastdev.rep1_summary where 1=1 ";
	*/		
	String str="SELECT region, sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, sum(converted_unfactored_amt) total_unfactored_amount,max(updated_by) upby,max(updated_date) updat, sum(rolling_90days_factamt) total_rolling_90days_factamt, sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt, forecast_status,sum( branch_count) branches FROM webforecastdev.forecast_region_summary where 1=1 and isactive='A'";
	StringBuffer sb=new StringBuffer(str);
	if(regionInputDTO.getRegions()!=null && !(regionInputDTO.getRegions().isEmpty())){
		for(ValueDTO valueDTO:regionInputDTO.getRegions()){
			regionList.add(valueDTO.getName());
		}
		 regions=GetStringFromList.getStringFromListComma(regionList);	
		 sb.append(" and  region in "+regions);
	}
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	
	List<Object[]> objects=regionDao.getRegionDetails(sb.toString());
	for(Object[] obj:objects){
		RegionDTO dto=new RegionDTO();
		if(obj[0]!=null)
		dto.setRegion((String)obj[0]);
		if(obj[0]!=null)
		dto.setCountry((String)obj[0]);
		if(obj[8]!=null)
		dto.setForecastStatus((String)obj[8]);
		/*if(obj[3]!=null){
		dto.setBranchSum(((BigInteger)obj[3]).toString());
		totalbranches=totalbranches.add(new BigInteger(dto.getBranchSum()));
		}*/
		if(obj[1]!=null){
		dto.setOpportunitiesSum(((BigInteger)obj[1]).toString());
		totalOpportunites=totalOpportunites.add(new BigDecimal(obj[1].toString()));
		}
		if(obj[2]!=null){
			
		dto.setFactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2]));
		totalfactAmt=totalfactAmt.add(new BigDecimal(obj[2].toString()));
		}
		if(obj[3]!=null){
			dto.setUnfactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[3]));	
			totalUnfactAmt=totalUnfactAmt.add(new BigDecimal(obj[3].toString()));
			}
		/*if(obj[6]!=null){
			dto.setCountry((String)obj[6]);
			}
		*/
		if(obj[4]!=null)
		{
		dto.setUpdatedBy((String)obj[4]);
		}
		
		if(obj[5]!=null)
		{
			//dto.setUpdatedDate(((Date)obj[8]).toString());
			Date mydate=(Date)obj[5];
			dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
		}
		if(obj[6]!=null)
		{
			//dto.setUpdatedDate(((Date)obj[8]).toString());
			//dto.setUnfactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6]));
			dto.setRollingFact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6]));
			totalRollingfactAmt=totalRollingfactAmt.add(new BigDecimal(obj[6].toString()));
		}
		if(obj[7]!=null)
		{
			
			dto.setRollingUnfact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7]));
			totalRollingUnfactAmt=totalRollingUnfactAmt.add(new BigDecimal(obj[7].toString()));
		}
		if(obj[9]!=null){
			dto.setBranchSum(((BigInteger)obj[9]).toString());
		}
		
		//dto.setCountry((String)obj[9]);
		regionData.add(dto);
		
	}
	response.setTotalBranches(totalbranches+"");
	response.setTotalCountries("");
	response.setTotalfactAmt(DataTypeConvert.get$StringFromNumers(totalfactAmt));
	response.setTotalOpportunites(totalOpportunites+"");
	response.setTotalSubRegions("");
	response.setTotalUnfactAmt(DataTypeConvert.get$StringFromNumers(totalUnfactAmt));
	response.setRegionData(regionData);
	
	
	DynamicSummaryDTO dynamicSummaryDTO=getRegionSummaryDetails(user_role,regionInputDTO);
	response.setDynamicSummaryDTO(dynamicSummaryDTO);
	
	
	//Lock permission code START
	
	try {
		List<BigDecimal> userAllowedDates=userService.getUserWindowDetails(user_role);
		String isuserAllowed=DateWindowUtil.isINDateWindow(userAllowedDates);
		if("N".equalsIgnoreCase(isuserAllowed)){
			response.setIsSubmitClickable(false);
		}
		
	} catch (Exception e) {
		System.out.println("In catch block of country Lock permission srivce");
		// TODO: handle exception
	}
	
	//Lock permission code END
	
	
	return response;
}

public DynamicSummaryDTO getRegionSummaryDetails(String user_role,RegionInputDTO regionInputDTO) {
	
	DynamicSummaryDTO dto=new DynamicSummaryDTO();
	
	
	List<String> regionList=new ArrayList<String>();
	String regions="";
	String queryString="";
	//StringBuffer innnerQuery=new StringBuffer();
	/*String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
			+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
			+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
			+ "submitted_count "
			+ " from(select status_rank,region, branch_code, sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt from webforecastdev.rep1_summary where 1=1 ";*/
	/*if(regionInputDTO.getRegions().isEmpty())
	{
	 queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
			+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
			+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
			+ "submitted_count, "
			+ " sum(rolling_90days_factamt)  rolling_90days_factamt,sum(rolling_90days_unfactamt)  rolling_90days_unfactamt "
			+ " from(select region, max(status_rank) status_rank, sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt,"
			+ " sum(rolling_90days_factamt)  rolling_90days_factamt,sum(rolling_90days_unfactamt)  rolling_90days_unfactamt "
			+ " from webforecastdev.rep1_summary where 1=1 ";
	        innnerQuery=new StringBuffer(" group by region) x where 1=1 ");
	}
	else
	{
		
		queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
				+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
				+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
				+ "submitted_count, "
				+ " sum(rolling_90days_factamt)  rolling_90days_factamt,sum(rolling_90days_unfactamt)  rolling_90days_unfactamt "
				+ " from(select status_rank,region,jci_reporting_country,  sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt,"
				+ " sum(rolling_90days_factamt)  rolling_90days_factamt,sum(rolling_90days_unfactamt)  rolling_90days_unfactamt  "
				+ " from webforecastdev.rep1_summary where 1=1 ";
		 innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country) x where 1=1 ");
		
	}
	
	*/
	String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns, sum(In_progress) as ip,sum(submitted) as sub , sum(rolling_90days_factamt) as rfmt,sum(rolling_90days_unfactamt) as runfmt from(select opp_data_source,opp_data_for_month,region, jci_reporting_country, sub_region, forecast_status, opp_count,converted_factored_amt,converted_unfactored_amt, rolling_90days_factamt,rolling_90days_unfactamt, case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_region_summary where 1=1 and isactive='A' ";
	StringBuffer sb=new StringBuffer(str);
	if(regionInputDTO.getRegions()!=null && !(regionInputDTO.getRegions().isEmpty())){
		for(ValueDTO valueDTO:regionInputDTO.getRegions()){
			regionList.add(valueDTO.getName());
		}
		 regions=GetStringFromList.getStringFromListComma(regionList);	
		 //innnerQuery.append(" and  region in "+regions);
		 sb.append(" and  region in "+regions);
		 
		 
		 
		 
	}
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	//sb.append(innnerQuery);
	//System.out.println("final Query "+sb);
	
	//if((user_role!=null) && (user_role.equalsIgnoreCase("HQ/Admin Forecaster")))
	if((regionInputDTO.getRegions().isEmpty())|| regionInputDTO.getRegions().size()>1)
	{
		//region footer
		//sb=new StringBuffer(sb.toString().replaceAll("FROM webforecastdev.forecast_branch_summary", "FROM webforecastdev.forecast_subregion_summary"));
		sb=new StringBuffer(sb.toString().replaceAll("sub_region,", " "));
		sb=new StringBuffer(sb.toString().replaceAll("jci_reporting_country,", " "));
		List<Object[]> objects=regionDao.getRegionSummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");		
		if(obj[0]!=null)
		dto.setOpportunitesVal(((BigInteger)obj[0]).toString());
		dto.setTotalfactordLable("Total Factored Amt.");
		if(obj[1]!=null)
		dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
		dto.setTotalunfactordLable("Total Unfactored Amt.");
		if(obj[2]!=null)
		dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
		dto.setNotstartedLable("Regions Not Started");
		if(obj[3]!=null)
		dto.setNotstartedVal(((BigInteger)obj[3]).toString());
		dto.setInprogressLable("Regions In Progress");
		if(obj[4]!=null)
		dto.setInprogressVal(((BigInteger)obj[4]).toString());
		dto.setSubmittedLable("Regions Submitted");
		if(obj[5]!=null)
		dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
		dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
		dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
	}
	if(regionInputDTO.getRegions().size()>0)
	{
	if((regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("North East Asia")) || (regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("South East Asia")) )
	{
		sb=new StringBuffer(sb.toString().replaceAll("FROM webforecastdev.forecast_region_summary", "FROM webforecastdev.forecast_country_summary"));
		sb=new StringBuffer(sb.toString().replaceAll("sub_region,", " "));
		//sb=new StringBuffer(sb.toString().replaceAll("jci_reporting_country", " "));
		List<Object[]> objects=regionDao.getRegionSummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");	
		if(obj[0]!=null)
		dto.setOpportunitesVal(((BigInteger)obj[0]).toString());
		dto.setTotalfactordLable("Total Factored Amt.");
		if(obj[1]!=null)
		dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
		dto.setTotalunfactordLable("Total Unfactored Amt.");
		if(obj[2]!=null)
		dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
		dto.setNotstartedLable("Countries Not Started");
		if(obj[3]!=null)
			dto.setNotstartedVal(((BigInteger)obj[3]).toString());
		dto.setInprogressLable("Countries In Progress");
		if(obj[4]!=null)
			dto.setInprogressVal(((BigInteger)obj[4]).toString());
		dto.setSubmittedLable("Countries Submitted");
		if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
			dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
			dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
		
	}
	
	
	if((regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("China")) )
	{
		//subreguons footer
		sb=new StringBuffer(sb.toString().replaceAll("FROM webforecastdev.forecast_region_summary", "FROM webforecastdev.forecast_subregion_summary"));
		List<Object[]> objects=regionDao.getRegionSummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");
		if(obj[0]!=null)
		dto.setOpportunitesVal(((BigInteger)obj[0]).toString());
		dto.setTotalfactordLable("Total Factored Amt.");
		if(obj[1]!=null)
			dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
		dto.setTotalunfactordLable("Total Unfactored Amt.");
		if(obj[2]!=null) 
			dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
		dto.setNotstartedLable("Sub Region Not Started");
		
		if(obj[3]!=null)
			dto.setNotstartedVal(((BigInteger)obj[3]).toString());
		dto.setInprogressLable("Sub Region In Progress");
		if(obj[4]!=null)
			dto.setInprogressVal(((BigInteger)obj[4]).toString());
		dto.setSubmittedLable("Sub Region  Submitted");
		if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
			dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
			dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
		
	}
	
	if(!((regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("China")) ||
			(regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("North East Asia")) 
			|| (regionInputDTO.getRegions().get(0).getName().equalsIgnoreCase("South East Asia")))  )
	{
		
		System.out.println("----------------------------------not china,NEA,SEA");
		//subreguons footer
		sb=new StringBuffer(sb.toString().replaceAll("FROM webforecastdev.forecast_region_summary", "FROM webforecastdev.forecast_branch_summary"));
		List<Object[]> objects=regionDao.getRegionSummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");		
		if(obj[0]!=null)
			dto.setOpportunitesVal(((BigInteger)obj[0]).toString());
		dto.setTotalfactordLable("Total Factored Amt.");
		if(obj[1]!=null)
			dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
		dto.setTotalunfactordLable("Total Unfactored Amt.");
		if(obj[2]!=null)
			dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
		dto.setNotstartedLable("Branches Not Started");
		if(obj[3]!=null)
			dto.setNotstartedVal(((BigInteger)obj[3]).toString());
		dto.setInprogressLable("Branches In Progress");
		if(obj[4]!=null)
			dto.setInprogressVal(((BigInteger)obj[4]).toString());
		dto.setSubmittedLable("Branches  Submitted");
		if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
			dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
			dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
		
	}
	
	}
	/*else if()
	{
		dto
	}
	else
	{
		dto
	}*/
	// TODO Auto-generated method stub
	/*dto.setRollingDaysFactAmtVal("999");
	dto.setRollingDaysUnfactAmtVal("888");*/
	return dto;
}

public RegionOutPutDTO submitForecastOnRegion(RegionInputDTO input,
		String attribute, String user_role) {
	// TODO Auto-generated method stub
	Random rand = new Random();

	int  n1 = rand.nextInt() + 1;
	//System.out.println("n1"+n1);
	String st=""+n1;
	//System.out.println(st.substring(1, 6));
	//String str="BC"+st.substring(1, 6);
	String str="";
	str=st.substring(1, 6);
	
	
	if("HQ/Admin Forecaster".equalsIgnoreCase(user_role)){
		str="H"+st.substring(1, 6);
	}
	if("Regional Forecaster".equalsIgnoreCase(user_role)){
		str="R"+st.substring(1, 6);
	}
	
	regionDao.submitForecastOnRegion(input, attribute, user_role,str);

	return null;
}

public LOBOutDTO getLobRegionDetails(ReportGridInputMultipleVO regionInputDTO,String attribute, String user_role) {
	// TODO Auto-generated method stub
	//RegionOutPutDTO response=new RegionOutPutDTO();
	LOBOutDTO response=new LOBOutDTO();
	List<LOBGridDTO> lobData=new ArrayList<LOBGridDTO>();
	List<RegionDTO> regionData=new ArrayList<RegionDTO>();
	
	BigDecimal totalfactAmt=new BigDecimal(0);
	BigDecimal totalUnfactAmt=new BigDecimal(0);
	BigDecimal totalOpportunites=new BigDecimal(0);
	BigInteger totalbranches=new BigInteger("0");
	BigDecimal rolling_90days_factamt=new BigDecimal(0);
	BigDecimal totalLobs=new BigDecimal(0);
	BigDecimal rolling_90days_unfactamt=new BigDecimal(0);
	List<String> regionList=new ArrayList<String>();
	String regions="";
	String queryString="SELECT region, lob, forecast_status,"
			+ " sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, "
			+ " sum(converted_unfactored_amt) total_unfactored_amount,"
			+ " max(updated_by) as upby,max(updated_date) as updt, "
			+ " sum(rolling_90days_factamt) total_rolling_90days_factamt,"
			+ " sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt "
			+ " FROM webforecastdev.forecast_lob_summary "
			+ " where 1=1  and isactive='A' ";
	StringBuffer sb=new StringBuffer(queryString);
	if(regionInputDTO.getRegions()!=null && !(regionInputDTO.getRegions().isEmpty())){
		for(ValueDTO valueDTO:regionInputDTO.getRegions()){
			regionList.add(valueDTO.getName());
		}
		 regions=GetStringFromList.getStringFromListComma(regionList);	
		 sb.append(" and  region in "+regions);
	}
	sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	
	List<Object[]> objects=regionDao.getLobRegionDetails(sb.toString());
	for(Object[] obj:objects){
		LOBGridDTO dto=new LOBGridDTO();
		if(obj[0]!=null)
		dto.setRegion((String)obj[0]);
		if(obj[0]!=null)
			dto.setFirstColumn((String)obj[0]);
		if(obj[1]!=null)
		totalLobs=totalLobs.add(new BigDecimal(1));
		if(obj[1]!=null){
		dto.setLob((String)obj[1]);
				}
		//dto.setCountry((String)obj[0]);
		if(obj[2]!=null)
			dto.setForecastStatus((String)obj[2]);
		/*if(obj[3]!=null){
		dto.setOpportunitiesSum(((BigInteger)obj[4]).toString());
		totalOpportunites=totalOpportunites.add(new BigInteger(dto.getOpportunitiesSum()));
		}*/
		if(obj[3]!=null){
		dto.setOpportunitiesSum(((BigInteger)obj[3]).toString());
		totalOpportunites=totalOpportunites.add(new BigDecimal(obj[3].toString()));
		}
		if(obj[4]!=null){
			
		dto.setFactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[4]));
		totalfactAmt=totalfactAmt.add(new BigDecimal(obj[4].toString()));
		}
		if(obj[5]!=null){
			dto.setUnfactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[5]));	
			totalUnfactAmt=totalUnfactAmt.add(new BigDecimal(obj[5].toString()));
			
			}
		/*if(obj[6]!=null){
			dto.setCountry((String)obj[6]);
			}
		*/
		if(obj[6]!=null)
		{
		dto.setUpdatedBy((String)obj[6]);
		}
		
		if(obj[7]!=null)
		{
			//dto.setUpdatedDate(((Date)obj[8]).toString());
			Date mydate=(Date)obj[7];
			dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
		}
		if(obj[8]!=null){
			dto.setRolling90DaysFactamt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[8]));
			rolling_90days_factamt=rolling_90days_factamt.add(new BigDecimal(obj[8].toString()));
			}
		if(obj[9]!=null){
			dto.setRolling90DaysUnfactamt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[9]));
			rolling_90days_unfactamt=rolling_90days_unfactamt.add(new BigDecimal(obj[9].toString()));
			}
		
		lobData.add(dto);
		
	}
	
	response.setLobData(lobData);
response.setTotalfactAmt((DataTypeConvert.get$StringFromNumers(totalfactAmt).substring(1)));
response.setTotalLobs(totalLobs+"");
response.setTotalOpportunities(totalOpportunites+"");
response.setTotalRollingDaysFactAmt((DataTypeConvert.get$StringFromNumers(rolling_90days_factamt).substring(1)));
response.setTotalRollingDaysUnfactAmt((DataTypeConvert.get$StringFromNumers(rolling_90days_unfactamt).substring(1)));
response.setTotalUnfactAmt((DataTypeConvert.get$StringFromNumers(totalUnfactAmt).substring(1)));
	/*response.setTotalBranches(totalbranches+"");
	response.setTotalCountries("");
	response.setTotalfactAmt(DataTypeConvert.get$StringFromNumers(totalfactAmt));
	response.setTotalOpportunites(totalOpportunites+"");
	response.setTotalSubRegions("");
	response.setTotalUnfactAmt(DataTypeConvert.get$StringFromNumers(totalUnfactAmt));
	response.setRegionData(regionData);
	*/
	//DynamicSummaryDTO dynamicSummaryDTO=getLOBRegionSummaryDetails(user_role,regionInputDTO);
	//response.setDynamicSummaryDTO(dynamicSummaryDTO);
	
	
	
	return response;
}

private DynamicSummaryDTO getLOBRegionSummaryDetails(String user_role,RegionInputDTO regionInputDTO) {
	// TODO Auto-generated method stub
	DynamicSummaryDTO dto=new DynamicSummaryDTO();
	
	
	List<String> regionList=new ArrayList<String>();
	String regions="";
	String queryString="";
	StringBuffer innnerQuery=new StringBuffer();
	
	queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
			+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
			+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
			+ "submitted_count "
			+ " from(select status_rank,region,jci_reporting_country,  sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt from webforecastdev.rep1_summary where 1=1 ";
	 innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country) x where 1=1 ");
		StringBuffer sb=new StringBuffer(queryString);
		if(regionInputDTO.getRegions()!=null && !(regionInputDTO.getRegions().isEmpty())){
			for(ValueDTO valueDTO:regionInputDTO.getRegions()){
				regionList.add(valueDTO.getName());
			}
			 regions=GetStringFromList.getStringFromListComma(regionList);	
			 innnerQuery.append(" and  region in "+regions);
			 sb.append(" and  region in "+regions);
			 
			 
			 
			 
		}
		sb.append(innnerQuery);
		//System.out.println("final Query "+sb);
		
		List<Object[]> objects=regionDao.getLOBRegionSummaryDetails(sb.toString());
		for(Object[] obj:objects){
		
		dto.setOpportunitesVal(((BigDecimal)obj[0]).toString());
	
		dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
	
		dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
		
		dto.setRolling90DaysFactamt(((BigInteger)obj[3]).toString());
		
		dto.setRolling90DaysUnfactamt(((BigInteger)obj[4]).toString());

		
	
	
}


		return dto;
		
}


}
