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

import com.java.main.dao.SubRegionDao;
import com.java.main.dto.DynamicSummaryDTO;
import com.java.main.dto.LOBGridDTO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SubRegionInputVO;
import com.java.main.dto.SubRegionOutPutVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.DateWindowUtil;
import com.java.main.utility.GetStringFromList;

@Service
public class SubRegionServiceImpl implements SubRegionService {

@Autowired
SubRegionDao subRegionDao;
@Autowired
UserService  userService;

public RegionOutPutDTO getSubRegionDeatils(SubRegionInputVO input,String userRole) {
	// TODO Auto-generated method stub
	
	RegionOutPutDTO response=new RegionOutPutDTO();
	List<RegionDTO> regionData=new ArrayList<RegionDTO>();
	List<String> regionList=new ArrayList<String>();
	List<String> countryList=new ArrayList<String>();
	List<String> subRegionList=new ArrayList<String>();
	BigDecimal totalfactAmt=new BigDecimal(0);
	BigDecimal totalUnfactAmt=new BigDecimal(0);
	BigDecimal totalOpportunites=new BigDecimal(0);
	BigInteger totalbranches=new BigInteger("0");
	String regions="";
	String countries="";
	String subRegions="";
	/*String queryString="select distinct sub_region,"
			+ " count(distinct branch_code) branch_count, "
			+ " sum(count) opp_count,sum(factored_amount) "
			+ " total_factored_amount,sum(unfactored_amount) "
			+ " total_unfactored_amount ,"
			+ " region rg,"
			+ " jci_reporting_country country,string_agg(updated_by,',') updated_by,max(updated_date) updated_date,forecast_status "
			+ " from webforecastdev.rep1_summary where 1=1  ";
	*/		
	String str="SELECT sub_region,region, sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, sum(converted_unfactored_amt) total_unfactored_amount,max(updated_by) upby,max(updated_date) updat, sum(rolling_90days_factamt) total_rolling_90days_factamt, sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt,forecast_status,sum( branch_count) branches FROM webforecastdev.forecast_subregion_summary where 1=1 and isactive='A' ";
	
	StringBuffer sb=new StringBuffer(str);
	if(input.getRegions()!=null && !(input.getRegions().isEmpty())){
		for(ValueDTO valueDTO:input.getRegions()){
			regionList.add(valueDTO.getName());
		}
		 regions=GetStringFromList.getStringFromListComma(regionList);
		 sb.append(" and region in "+regions);
	}
	/*if(input.getCountries()!=null && !(input.getCountries().isEmpty())){
		for(ValueDTO valueDTO:input.getCountries()){
			countryList.add(valueDTO.getName());
		}
		 countries=GetStringFromList.getStringFromListComma(countryList);
		 sb.append(" and jci_reporting_country in "+countries);
	}
*/	
	if(input.getSubRegions()!=null && !(input.getSubRegions().isEmpty())){
		for(ValueDTO valueDTO:input.getSubRegions()){
			subRegionList.add(valueDTO.getName());
		}
		 subRegions=GetStringFromList.getStringFromListComma(subRegionList);
		 sb.append(" and sub_region in "+subRegions);
	}
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	
	List<Object[]> objects=subRegionDao.getSubRegionDetails(sb.toString());
	for(Object[] obj:objects){
		RegionDTO dto=new RegionDTO();
		if(obj[0]!=null)
		dto.setSubRegion((String)obj[0]);
		/*if(obj[1]!=null){
			dto.setBranchSum(((BigInteger)obj[1]).toString());
			totalbranches=totalbranches.add(new BigInteger(dto.getBranchSum()));
			}*/
		
		if(obj[2]!=null){
			dto.setOpportunitiesSum(((BigInteger)obj[2]).toString());
			totalOpportunites=totalOpportunites.add(new BigDecimal(obj[2].toString()));
			}
		if(obj[3]!=null){
			
			dto.setFactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[3]));
			totalfactAmt=totalfactAmt.add(new BigDecimal(obj[3].toString()));
			}
		if(obj[4]!=null){
			dto.setUnfactoredAmt(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[4]));	
			totalUnfactAmt=totalUnfactAmt.add(new BigDecimal(obj[4].toString()));
			}
		if(obj[7]!=null){
			dto.setRollingFact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7]));
		}
		if(obj[8]!=null){
			dto.setRollingUnfact(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[8]));
		}
		
		dto.setRegion((String)obj[1]);
		/*dto.setCountry((String)obj[6]);*/
		if(obj[5]!=null)
		{
		dto.setUpdatedBy((String)obj[5]);
		}
		if(obj[6]!=null)
		{
			//dto.setUpdatedDate(((Date)obj[8]).toString());
			Date mydate=(Date)obj[6];
			dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
		}
		
		dto.setForecastStatus((String)obj[9]);
		if(obj[10]!=null){
			dto.setBranchSum(((BigInteger)obj[10]).toString());
		}
		regionData.add(dto);
		
	}
	response.setTotalBranches(totalbranches+"");
	response.setTotalCountries("");
	response.setTotalfactAmt(DataTypeConvert.get$StringFromNumers(totalfactAmt));
	response.setTotalOpportunites(totalOpportunites+"");
	response.setTotalSubRegions("");
	response.setTotalUnfactAmt(DataTypeConvert.get$StringFromNumers(totalUnfactAmt));
	
	
	response.setRegionData(regionData);
	DynamicSummaryDTO dynamicSummaryDTO=getsubRegionSummaryDetails(input);
	response.setDynamicSummaryDTO(dynamicSummaryDTO);
	
	//Lock permission code START
	
	
	try {
		List<BigDecimal> userAllowedDates=userService.getUserWindowDetails(userRole);
		String isuserAllowed=DateWindowUtil.isINDateWindow(userAllowedDates);
		if("N".equalsIgnoreCase(isuserAllowed)){
			response.setIsSubmitClickable(false);
		}
		
	} catch (Exception e) {
		System.out.println("In catch block of subregion  Lock permission srivce");
		// TODO: handle exception
	}

	//Lock permission code END
	
	
	
	return response;
}

public DynamicSummaryDTO getsubRegionSummaryDetails(SubRegionInputVO input) {
	
	DynamicSummaryDTO dto=new DynamicSummaryDTO();
	
	
	List<RegionDTO> regionData=new ArrayList<RegionDTO>();
	List<String> regionList=new ArrayList<String>();
	List<String> countryList=new ArrayList<String>();
	List<String> subRegionList=new ArrayList<String>();
	
	String regions="";
	String countries="";
	String subRegions="";
	
	/*String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
			+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
			+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
			+ "submitted_count "
			+ " from(select status_rank,region,jci_reporting_country,sub_region, branch_code, sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt from webforecastdev.rep1_summary where 1=1 ";
	*/
	String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns, sum(In_progress) as ip,sum(submitted) as sub , sum(rolling_90days_factamt) as rfmt,sum(rolling_90days_unfactamt) as runfmt from(select opp_data_source,opp_data_for_month,region, jci_reporting_country, sub_region, forecast_status, opp_count,converted_factored_amt,converted_unfactored_amt, rolling_90days_factamt,rolling_90days_unfactamt, case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A' ";
	//StringBuffer innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country, sub_region,branch_code) x where 1=1 ");
	StringBuffer sb=new StringBuffer(str);
	if(input.getRegions()!=null && !(input.getRegions().isEmpty())){
		for(ValueDTO valueDTO:input.getRegions()){
			regionList.add(valueDTO.getName());
		}
		 regions=GetStringFromList.getStringFromListComma(regionList);
		 //innnerQuery.append(" and  region in "+regions);
		 sb.append(" and  region in "+regions);
	}
	/*if(input.getCountries()!=null && !(input.getCountries().isEmpty())){
		for(ValueDTO valueDTO:input.getCountries()){
			countryList.add(valueDTO.getName());
		}
		 countries=GetStringFromList.getStringFromListComma(countryList);
		// innnerQuery.append(" and jci_reporting_country in "+countries);
		 sb.append(" and jci_reporting_country in "+countries);
	}*/
	
	if(input.getSubRegions()!=null && !(input.getSubRegions().isEmpty())){
		for(ValueDTO valueDTO:input.getSubRegions()){
			subRegionList.add(valueDTO.getName());
		}
		 subRegions=GetStringFromList.getStringFromListComma(subRegionList);
		 //innnerQuery.append(" and sub_region in "+subRegions);
		 sb.append(" and sub_region in "+subRegions);
	}
	sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	//sb.append(innnerQuery);
	
	
		//region footer
		List<Object[]> objects=subRegionDao.getsubRegionSummaryDetails(sb.toString());
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
		dto.setSubmittedLable("Branches Submitted");
		if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
		if(obj[6]!=null)
		{
			dto.setRolling90DaysFactamt((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
		}
		if(obj[7]!=null){
			dto.setRolling90DaysUnfactamt((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
		}
		}
		
			
	
	
	
	

	return dto;
}

public SubRegionOutPutVO submitForecastOnSubRegion(SubRegionInputVO input,
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
	if("Sub Region Forecaster".equalsIgnoreCase(user_role)){
		str="S"+st.substring(1, 6);
	}
	subRegionDao.submitForecastOnSubRegion(input, attribute, user_role, str);
	return null;
}

public LOBOutDTO getLobSubRegionDetails(ReportGridInputMultipleVO regionInputDTO,
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
	List<String> subRegionList=new ArrayList<String>();
	String regions="";
	String subRegions="";
	String queryString="SELECT sub_region, lob, forecast_status,"
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
	if(regionInputDTO.getSubregions()!=null && !(regionInputDTO.getSubregions().isEmpty()) && (regionInputDTO.getSubregions().get(0).getName()!=null)){
		for(ValueDTO valueDTO:regionInputDTO.getSubregions()){
			subRegionList.add(valueDTO.getName());
		}
		 subRegions=GetStringFromList.getStringFromListComma(subRegionList);	
		 sb.append(" and  sub_region in "+subRegions);
	}
	sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
	
	List<Object[]> objects=subRegionDao.getLOBSubRegionDetails(sb.toString());
	for(Object[] obj:objects){
		LOBGridDTO dto=new LOBGridDTO();
		//dto.setRegion((String)obj[0]);
		if(obj[0]!=null)
		dto.setSubRegion((String)obj[0]);
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
