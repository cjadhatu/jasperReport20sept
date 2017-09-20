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

import com.java.main.dao.CountryDao;
import com.java.main.dto.CountryInputVO;
import com.java.main.dto.CountryOutPutVO;
import com.java.main.dto.DynamicSummaryDTO;
import com.java.main.dto.LOBGridDTO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionDTO;
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.DateWindowUtil;
import com.java.main.utility.GetStringFromList;
import com.programcreek.helloworld.controller.DateUtility;

@Service
public class CountryServiceImpl implements CountryService {

@Autowired
CountryDao countryDao;
@Autowired
UserService  userService;
public CountryOutPutVO getCountryDeatils(CountryInputVO countryInput,String userRole) {
	// TODO Auto-generated method stub
	
	CountryOutPutVO response=new CountryOutPutVO();
	List<RegionDTO> countryData=new ArrayList<RegionDTO>();
	String countries="";
	String regions="";
		
	String queryString="SELECT jci_reporting_country,region, sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, sum(converted_unfactored_amt) total_unfactored_amount,max(updated_by) upby,max(updated_date) updat, sum(rolling_90days_factamt) total_rolling_90days_factamt, sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt,forecast_status,sum( branch_count) branches FROM webforecastdev.forecast_country_summary where 1=1 and isactive='A'  ";
	StringBuffer sb=new StringBuffer(queryString);
	
	List<String> regionList=new ArrayList<String>();
	List<String> countryList=new ArrayList<String>();
	if(countryInput.getRegions()!=null &&!(countryInput.getRegions().isEmpty()) && (countryInput.getRegions().get(0).getName()!=null ))
	{
	for(ValueDTO valueDTO:countryInput.getRegions()){
		regionList.add(valueDTO.getName());
	}
	regions=GetStringFromList.getStringFromListComma(regionList);
	if("''".equalsIgnoreCase(regions)){
		
	}else{
	sb.append(" and region in "+regions);
	}
	}
	if(countryInput.getCountries()!=null &&!(countryInput.getCountries().isEmpty())){
		for(ValueDTO valueDTO:countryInput.getCountries()){
			countryList.add(valueDTO.getName());
		}
		countries=GetStringFromList.getStringFromListComma(countryList);
		if("''".equalsIgnoreCase(countries)){
			
		}
		else{
		sb.append(" and jci_reporting_country in "+countries);
		}
	}
	
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' ");
	
	List<Object[]> objects=countryDao.getCountryDetails(sb.toString());
	for(Object[] obj:objects){
		RegionDTO dto=new RegionDTO();
		if(obj[0]!=null)
		dto.setCountry((String)obj[0]);
		//dto.setBranchSum(((BigInteger)obj[1]).toString());
		if(obj[2]!=null)
		dto.setOpportunitiesSum(((BigInteger)obj[2]).toString());
		if(obj[3]!=null)
		dto.setFactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[3]));
		if(obj[4]!=null)
		dto.setUnfactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[4]));
		if(obj[1]!=null)
		dto.setRegion((String)obj[1]);
		if(obj[5]!=null)
		{
		dto.setUpdatedBy((String)obj[5]);
		}
		if(obj[6]!=null)
		{
			//dto.setUpdatedDate(((Date)obj[7]).toString());
			Date mydate=(Date)obj[6];
			dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
		}
		if(obj[7]!=null){
			dto.setRollingFact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7]));
		}
		if(obj[8]!=null){
			dto.setRollingUnfact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[8]));
		}
		if(obj[9]!=null)
		dto.setForecastStatus((String)obj[9]);
		if(obj[10]!=null){
			dto.setBranchSum(((BigInteger)obj[10]).toString());
		}
	
		countryData.add(dto);
		
	}
	
	response.setCountryData(countryData);
	DynamicSummaryDTO dynamicSummaryDTO=getcountrySummaryDetails(countryInput);
	response.setDynamicSummaryDTO(dynamicSummaryDTO);
	
	
	try {
		List<BigDecimal> userAllowedDates=userService.getUserWindowDetails(userRole);
		String isuserAllowed=DateWindowUtil.isINDateWindow(userAllowedDates);
		if("N".equalsIgnoreCase(isuserAllowed)){
			response.setIsSubmitClickable(false);
		}
		
	} catch (Exception e) {
		System.out.println("In catch block of country Lock permission srivce");
		// TODO: handle exception
	}
	
	return response;
}

public DynamicSummaryDTO getcountrySummaryDetails(CountryInputVO countryInput) {
	
	DynamicSummaryDTO dto=new DynamicSummaryDTO();
	
	
	List<String> regionList=new ArrayList<String>();
	List<String> countryList=new ArrayList<String>();
	String countries="";
	String regions="";
	
/*	String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
			+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
			+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
			+ "submitted_count "
			+ " from(select status_rank,region,jci_reporting_country, branch_code, sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt from webforecastdev.rep1_summary where 1=1 ";
	StringBuffer innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country, branch_code) x where 1=1 ");*/
	/*String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns,sum(In_progress) as ip,sum(submitted) as sub "
			+ " , sum(rolling_90days_factamt) as  rfmt,sum(rolling_90days_unfactamt) as runfmt  "
			+ " from(select opp_data_source,opp_data_for_month,region, jci_reporting_country, sub_region, branch_code, forecast_status, opp_count,converted_factored_amt,converted_unfactored_amt,"
			+ "  rolling_90days_factamt,rolling_90days_unfactamt,  case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A'  ";		
	*/
	
	String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns,sum(In_progress) as ip,sum(submitted) as sub "
			+ " , sum(rolling_90days_factamt) as  rfmt,sum(rolling_90days_unfactamt) as runfmt  "
			+ " from(select  branch_code, sum(opp_count) as opp_count,sum(converted_factored_amt) as converted_factored_amt,sum(converted_unfactored_amt) as converted_unfactored_amt ,"
			+ " sum(rolling_90days_factamt) as rolling_90days_factamt,sum(rolling_90days_unfactamt) as rolling_90days_unfactamt,  case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A'  ";		
	
	//StringBuffer innnerQuery=new StringBuffer(" group by sub_region) x where 1=1 ");
	
	StringBuffer sb=new StringBuffer(str);
	
	if(countryInput.getRegions()!=null &&!(countryInput.getRegions().isEmpty())&& (countryInput.getRegions().get(0).getName()!=null ))
	{
	for(ValueDTO valueDTO:countryInput.getRegions()){
		regionList.add(valueDTO.getName());
	}
	regions=GetStringFromList.getStringFromListComma(regionList);
	/*innnerQuery.append(" and  region in "+regions);*/
	if("''".equalsIgnoreCase(regions)){
		
	}
	else{
	sb.append(" and  region in "+regions);
	}
	}
	if(countryInput.getCountries()!=null &&!(countryInput.getCountries().isEmpty())){
		for(ValueDTO valueDTO:countryInput.getCountries()){
			countryList.add(valueDTO.getName());
		}
		countries=GetStringFromList.getStringFromListComma(countryList);
		if("''".equalsIgnoreCase(countries)){
			
		}
		else{
		//innnerQuery.append(" and jci_reporting_country in "+countries);
		sb.append(" and jci_reporting_country in "+countries);
		}
	}
	//sb.append(innnerQuery);
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' ");
	System.out.println("COuntry summary fink query "+sb);
	//if((user_role!=null) && (user_role.equalsIgnoreCase("HQ/Admin Forecaster")))
	if(countryInput.getRegions()!=null && countryInput.getRegions().get(0).getName()!=null )
	if( countryInput.getRegions().size()==1 && countryInput.getRegions().get(0).getName().equalsIgnoreCase("china"))
	{
		//region footer
		sb=new StringBuffer(sb.toString().replaceAll("FROM webforecastdev.forecast_branch_summary", "FROM webforecastdev.forecast_subregion_summary"));
		List<Object[]> objects=countryDao.getCountrySummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");	
		if(obj[0]!=null)
		dto.setOpportunitesVal(((BigDecimal)obj[0]).toString());
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
		dto.setSubmittedLable("Sub Region Submitted");
		if(obj[5]!=null)
		dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
		dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
		dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
	}
	else
	{
		List<Object[]> objects=countryDao.getCountrySummaryDetails(sb.toString());
		for(Object[] obj:objects){
		dto.setOpportunitesLable("Opportunities");
		if(obj[0]!=null)
		dto.setOpportunitesVal(((BigDecimal)obj[0]).toString());
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
		dto.setSubmittedLable("Branches Submitted");
		if(obj[5]!=null)
		dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
		dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		if(obj[7]!=null)
		dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
		
	
	
	
	
	}

	return dto;
}

public CountryOutPutVO submitForecastOnCountry(CountryInputVO input,
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
	if("Country Forecaster".equalsIgnoreCase(user_role)){
		str="C"+st.substring(1, 6);
	}
	
	/*regionDao.submitForecastOnRegion(input, attribute, user_role,str);*/
	countryDao.submitForecastOnCountry(input, attribute, user_role, str);

	return null;
}

public LOBOutDTO getLobCountryDetails(ReportGridInputMultipleVO regionInputDTO,
		String attribute, String user_role) {
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
	BigDecimal rolling_90days_unfactamt=new BigDecimal(0);
	BigDecimal totalLobs=new BigDecimal(0);
	List<String> regionList=new ArrayList<String>();
	List<String> countryList=new ArrayList<String>();
	String regions="";
	String countries="";
	String queryString="SELECT jci_reporting_country, lob, forecast_status,"
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
		 if("''".equalsIgnoreCase(regions)){
			 
		 }
		 else{
		 sb.append(" and  region in "+regions);
	}
		 }
	if(regionInputDTO.getCountries()!=null && !(regionInputDTO.getCountries().isEmpty())){
		for(ValueDTO valueDTO:regionInputDTO.getCountries()){
			countryList.add(valueDTO.getName());
		}
		 countries=GetStringFromList.getStringFromListComma(countryList);	
		 if("''".equalsIgnoreCase(countries)){
			 
		 }else{
			 sb.append(" and  jci_reporting_country in "+countries);
		 }
		 
	}
	sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	
	List<Object[]> objects=countryDao.getLOBCountryDetails(sb.toString());
	for(Object[] obj:objects){
		LOBGridDTO dto=new LOBGridDTO();
		//dto.setRegion((String)obj[0]);
		if(obj[0]!=null)
		dto.setCountry((String)obj[0]);
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

}
