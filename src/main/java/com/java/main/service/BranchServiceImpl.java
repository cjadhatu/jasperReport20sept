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

import com.java.main.dao.BranchDao;
import com.java.main.dto.BranchDTO;
import com.java.main.dto.BranchInputVO;
import com.java.main.dto.BranchOutPutDTO;
import com.java.main.dto.BranchVO;
import com.java.main.dto.DynamicSummaryDTO;
import com.java.main.dto.LOBGridDTO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.DateWindowUtil;
import com.java.main.utility.GetStringFromList;

@Service
public class BranchServiceImpl implements BranchService {
	@Autowired
	BranchDao branchDao;
	@Autowired
	UserService userService; 
	public List<BranchVO> geBranchList(String regionCode) {

		List<BranchVO> resultList = new ArrayList<BranchVO>();
		List<String> branchCodes = branchDao.getBranchesByRegion(regionCode);
		for (String b : branchCodes) {
			BranchVO br = new BranchVO();
			br.setRegId(1);
			br.setBranchgeodesc(b);
			br.setBranchgeography(b);
			br.setUpdated("SYSTEM");
			br.setUpdatedBy("SYSTEM");
			br.setBranches("10");
			br.setBranchId(1);
			br.setOpps("20");
			br.setSumfactamt("1000");
			br.setForecaststatus("NOT Started");
			resultList.add(br);
		}
		return resultList;
	}

	/*public BranchOutPutDTO getBranchDetails(BranchInputVO branchinput,
			String globalId) {
		// TODO Auto-generated method stub

		BranchOutPutDTO response = new BranchOutPutDTO();
		List<BranchDTO> branchData = new ArrayList<BranchDTO>();
		String countries = "";
		String regions = "";
		String subregions = "";
		String branches="";
		String queryString ="select distinct branch_code,opp_data_source,"
				+ " case when max(status_rank) = 3 then 'In progress' "
                + " when  max(status_rank) = 2 then 'Submitted' "
                +"  else 'Not Started' end  forecast_status,jci_reporting_country,region,sub_region, count(distinct region) region_count, "
				+ "count(distinct branch_code) branch_count,sum(count) opp_count,sum(converted_factored_amt)  total_factored_amount,"
				+ "sum(converted_unfactored_amt)  total_unfactored_amount,string_agg(updated_by,',') updated_by,max(updated_date) updated_date from webforecastdev.rep1_summary  where 1=1";
		
		String queryString = "select distinct branch_code,opp_data_source, count(distinct region) region_count, "
				+ "count(distinct branch_code) branch_count,sum(count) opp_count,sum(factored_amount)  "
				+ "total_factored_amount,sum(unfactored_amount)  total_unfactored_amount "
				+ "from webforecastdev.rep1_summary where 1=1";

		StringBuffer sb = new StringBuffer(queryString);

		List<String> regionList = new ArrayList<String>();
		List<String> countryList = new ArrayList<String>();
		List<String> subregionList = new ArrayList<String>();
		List<String> branchList = new ArrayList<String>();
		
		if (branchinput.getRegions() != null
				&& !(branchinput.getRegions().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getRegions()) {
				regionList.add(valueDTO.getName());
			}

			regions = GetStringFromList.getStringFromListComma(regionList);
			sb.append(" and region in " + regions);
		}

		if (branchinput.getCountries() != null
				&& !(branchinput.getCountries().isEmpty())) {
			for (ValueDTO valueDTO : branchinput.getCountries()) {
				countryList.add(valueDTO.getName());
			}
			countries = GetStringFromList.getStringFromListComma(countryList);
			sb.append(" and jci_reporting_country in " + countries);
		}

		if (branchinput.getSubregions() != null
				&& !(branchinput.getSubregions().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getSubregions()) {
				if(!("NA".equalsIgnoreCase(valueDTO.getName())))
				subregionList.add(valueDTO.getName());
			}

			subregions = GetStringFromList.getStringFromListComma(subregionList);
			sb.append(" and sub_region in " + subregions);
		}
		
		if (branchinput.getBranches() != null
				&& !(branchinput.getBranches().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getBranches()) {
				branchList.add(valueDTO.getName());
			}

			branches = GetStringFromList.getStringFromListComma(branchList);
			sb.append(" and branch_code in " + branches);
		}
		sb.append("and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
		
		List<Object[]> objects = branchDao.getBranchDetails(sb.toString());
		for (Object[] obj : objects) {
			BranchDTO dto = new BranchDTO();

			dto.setBrName((String) obj[0]);
			dto.setType((String) obj[1]);
			dto.setBrFcStatus((String) obj[2]);
			dto.setCountry((String) obj[3]);
			dto.setRegion((String) obj[4]);
			dto.setSubRegion((String) obj[5]);
			
			dto.setBranchesCount(((BigInteger) obj[7]).toString());
			dto.setBrOpps(((BigDecimal) obj[8]).toString());
			dto.setFcAmt(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[9]));
			dto.setUnfactoredAmt(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[10]));
			if(obj[11]!=null)
			{
            dto.setUpdatedBy((String) obj[11]);
			}
			try
			{
			if(obj[12]!=null)
			{
            //dto.setUpdatedDate(((Date)obj[12]).toString());
				Date mydate=(Date)obj[12];
				dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
           
			}
			}
			catch(Exception e)
			{
				 dto.setUpdatedDate("");	
			}
			branchData.add(dto);

		}

		response.setBranchData(branchData);
		DynamicSummaryDTO dynamicSummaryDTO=getBranchSummaryDetails(branchinput);
		response.setDynamicSummaryDTO(dynamicSummaryDTO);

		return response;

	}
	
	public DynamicSummaryDTO getBranchSummaryDetails(BranchInputVO branchinput) {
		
		DynamicSummaryDTO dto=new DynamicSummaryDTO();
		
		
		List<BranchDTO> branchData = new ArrayList<BranchDTO>();
		String countries = "";
		String regions = "";
		String subregions = "";
		String branches="";
		
	
		
		String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
				+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when forecast_status = 'Not Started' then 1 else 0 end) not_stated_count, "
				+ "sum(case when forecast_status = 'In Progress' then 1 else 0 end) in_progress_count, sum(case when forecast_status = 'Submitted' then 1 else 0 end) "
				+ "submitted_count "
				+ " from webforecastdev.rep1_summary where 1=1";
				
		
		String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
				+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when status_rank = 1 then 1 else 0 end) not_stated_count, "
				+ "sum(case when status_rank = 3 then 1 else 0 end) in_progress_count, sum(case when status_rank = 2 then 1 else 0 end) "
				+ "submitted_count "
				+ " from(select status_rank,region,jci_reporting_country,sub_region, branch_code, sum(count) count, sum(converted_factored_amt) converted_factored_amt, sum(converted_unfactored_amt) converted_unfactored_amt from webforecastdev.rep1_summary where 1=1 ";
				StringBuffer innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country, sub_region,branch_code) x where 1=1 ");
				StringBuffer sb = new StringBuffer(queryString);
		List<String> regionList = new ArrayList<String>();
		List<String> countryList = new ArrayList<String>();
		List<String> subregionList = new ArrayList<String>();
		List<String> branchList = new ArrayList<String>();
		
		if (branchinput.getRegions() != null
				&& !(branchinput.getRegions().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getRegions()) {
				regionList.add(valueDTO.getName());
			}

			regions = GetStringFromList.getStringFromListComma(regionList);
			innnerQuery.append(" and  region in "+regions);
			sb.append(" and region in " + regions);
		}

		if (branchinput.getCountries() != null
				&& !(branchinput.getCountries().isEmpty())) {
			for (ValueDTO valueDTO : branchinput.getCountries()) {
				countryList.add(valueDTO.getName());
			}
			countries = GetStringFromList.getStringFromListComma(countryList);
			innnerQuery.append(" and jci_reporting_country in "+countries);
			sb.append(" and jci_reporting_country in " + countries);
		}

		if (branchinput.getSubregions() != null
				&& !(branchinput.getSubregions().isEmpty()) && branchinput.getSubregions().get(0).getName()!=null && !("NA".equalsIgnoreCase(branchinput.getSubregions().get(0).getName()))) {

			for (ValueDTO valueDTO : branchinput.getSubregions()) {
				subregionList.add(valueDTO.getName());
			}

			subregions = GetStringFromList.getStringFromListComma(subregionList);
			innnerQuery.append(" and sub_region in "+subregions);
			sb.append(" and sub_region in " + subregions);
		}
		
		if (branchinput.getBranches() != null
				&& !(branchinput.getBranches().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getBranches()) {
				branchList.add(valueDTO.getName());
			}

			branches = GetStringFromList.getStringFromListComma(branchList);
			innnerQuery.append(" and branch_code in " + branches);
			sb.append(" and branch_code in " + branches);
		}

		sb.append(innnerQuery);
			//region footer
			List<Object[]> objects=branchDao.getBranchSummaryDetails(sb.toString());
			for(Object[] obj:objects){
			dto.setOpportunitesLable("Opportunites");
			if(obj[0]!=null)
			{
			dto.setOpportunitesVal(((BigDecimal)obj[0]).toString());
			}
			
			dto.setTotalfactordLable("Total Factored");
			if(obj[1]!=null){
			dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
			}
			dto.setTotalunfactordLable("Total Unfactored");
			if(obj[2]!=null){
			dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
			}
			dto.setNotstartedLable("Branches Not Started");
			if(obj[3]!=null)
			dto.setNotstartedVal(((BigInteger)obj[3]).toString());
			dto.setInprogressLable("Branches In Progress");
			if(obj[4]!=null)
			dto.setInprogressVal(((BigInteger)obj[4]).toString());
			dto.setSubmittedLable("Branches Submitted");
			if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
			}
		
		
		
		

		return dto;
	}
*/
	
	
	public BranchOutPutDTO getBranchDetails(BranchInputVO branchinput,
			String userRole) {
		// TODO Auto-generated method stub

		BranchOutPutDTO response = new BranchOutPutDTO();
		List<BranchDTO> branchData = new ArrayList<BranchDTO>();
		String countries = "";
		String regions = "";
		String subregions = "";
		String branches="";
		/*String queryString ="select distinct branch_code,opp_data_source,"
				+ " case when max(status_rank) = 3 then 'In progress' "
                + " when  max(status_rank) = 2 then 'Submitted' "
                +"  else 'Not Started' end  forecast_status,jci_reporting_country,region,sub_region, count(distinct region) region_count, "
				+ "count(distinct branch_code) branch_count,sum(count) opp_count,sum(converted_factored_amt)  total_factored_amount,"
				+ "sum(converted_unfactored_amt)  total_unfactored_amount,string_agg(updated_by,',') updated_by,max(updated_date) updated_date from webforecastdev.rep1_summary  where 1=1";
		*/
		//String str="SELECT branch_code,opp_data_source, forecast_status , jci_reporting_country,region, sub_region, sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, sum(converted_unfactored_amt) total_unfactored_amount,updated_by,updated_date, sum(rolling_90days_factamt) total_rolling_90days_factamt, sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt FROM webforecastdev.forecast_branch_summary where 1=1";
	
		String str="SELECT branch_code,opp_data_source, forecast_status , jci_reporting_country,region, sub_region, sum(opp_count) opp_count, sum(converted_factored_amt) total_factored_amount, sum(converted_unfactored_amt) total_unfactored_amount,max(updated_by) upby,max(updated_date) updat,  sum(rolling_90days_factamt) total_rolling_90days_factamt, sum(rolling_90days_unfactamt) total_rolling_90days_unfactamt FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A' ";
		/*	String queryString = "select distinct branch_code,opp_data_source, count(distinct region) region_count, "
				+ "count(distinct branch_code) branch_count,sum(count) opp_count,sum(factored_amount)  "
				+ "total_factored_amount,sum(unfactored_amount)  total_unfactored_amount "
				+ "from webforecastdev.rep1_summary where 1=1";*/

		StringBuffer sb = new StringBuffer(str);

		List<String> regionList = new ArrayList<String>();
		List<String> countryList = new ArrayList<String>();
		List<String> subregionList = new ArrayList<String>();
		List<String> branchList = new ArrayList<String>();
		
		if (branchinput.getRegions() != null
				&& !(branchinput.getRegions().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getRegions()) {
				regionList.add(valueDTO.getName());
			}

			regions = GetStringFromList.getStringFromListComma(regionList);
			sb.append(" and region in " + regions);
		}

		if (branchinput.getCountries() != null
				&& !(branchinput.getCountries().isEmpty()) && (branchinput.getCountries().get(0).getName()!=null)) {
			for (ValueDTO valueDTO : branchinput.getCountries()) {
				countryList.add(valueDTO.getName());
			}
			countries = GetStringFromList.getStringFromListComma(countryList);
			if("''".equalsIgnoreCase(countries)){
				
			}
			else{
			sb.append(" and jci_reporting_country in " + countries);
		}
		}

		if (branchinput.getSubregions() != null	&& !(branchinput.getSubregions().isEmpty()) && (branchinput.getSubregions().get(0).getName()!=null)) {

			for (ValueDTO valueDTO : branchinput.getSubregions()) {
				if(!("NA".equalsIgnoreCase(valueDTO.getName())) && (branchinput.getSubregions().get(0).getName()!=null)){
				subregionList.add(valueDTO.getName());
				}
			}

			subregions = GetStringFromList.getStringFromListComma(subregionList);
			if("''".equalsIgnoreCase(subregions)){
				
			}
			else
			{
			sb.append(" and sub_region in " + subregions);
			}
		}
		
		if (branchinput.getBranches() != null
				&& !(branchinput.getBranches().isEmpty()) && (branchinput.getBranches().get(0).getName()!=null)) {

			for (ValueDTO valueDTO : branchinput.getBranches()) {
				branchList.add(valueDTO.getName());
			}

			branches = GetStringFromList.getStringFromListComma(branchList);
			if("''".equalsIgnoreCase(branches)){
				
			}
			else{
				sb.append(" and branch_code in " + branches);
			}
			
		}
		sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
		
		List<Object[]> objects = branchDao.getBranchDetails(sb.toString());
		for (Object[] obj : objects) {
			BranchDTO dto = new BranchDTO();
			if(obj[0]!=null)
			dto.setBrName((String) obj[0]);
			if(obj[1]!=null)
			dto.setType((String) obj[1]);
			if(obj[2]!=null)
			dto.setBrFcStatus((String) obj[2]);
			if(obj[3]!=null)
			dto.setCountry((String) obj[3]);
			if(obj[4]!=null)
			dto.setRegion((String) obj[4]);
			if(obj[5]!=null)
			dto.setSubRegion((String) obj[5]);
			
			//dto.setBranchesCount(((BigInteger) obj[7]).toString());
			if(obj[6]!=null)
			{
			dto.setBrOpps(((BigInteger) obj[6]).toString());
			}
			
			if(obj[7]!=null)
			dto.setFcAmt(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[7]));
			if(obj[8]!=null)
			dto.setUnfactoredAmt(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[8]));
			if(obj[9]!=null)
			{
            dto.setUpdatedBy((String) obj[9]);
			}
			try
			{
			if(obj[10]!=null)
			{
            //dto.setUpdatedDate(((Date)obj[12]).toString());
				Date mydate=(Date)obj[10];
				dto.setUpdatedDate(new SimpleDateFormat("MM-dd-yyyy").format(mydate));
           
			}
			}
			catch(Exception e)
			{
				 dto.setUpdatedDate("");	
			}
			if(obj[11]!=null){
				dto.setRollingFact(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[11]));	
			}
			if(obj[12]!=null){
				dto.setRollingUnfact(DataTypeConvert
					.get$StringFromNumers((BigDecimal) obj[12]));
			}
			branchData.add(dto);

		}

		response.setBranchData(branchData);
		DynamicSummaryDTO dynamicSummaryDTO=getBranchSummaryDetails(branchinput);
		response.setDynamicSummaryDTO(dynamicSummaryDTO);
		try {
			List<BigDecimal> userAllowedDates=userService.getUserWindowDetails(userRole);
			String isSubmitClickable=DateWindowUtil.isINDateWindow(userAllowedDates);
			if("N".equalsIgnoreCase(isSubmitClickable)){
				response.setIsSubmitClickable(false);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("In catch branch Lock service ");
		}
		return response;

	}
	
	public DynamicSummaryDTO getBranchSummaryDetails(BranchInputVO branchinput) {
		
		DynamicSummaryDTO dto=new DynamicSummaryDTO();
		
		
		List<BranchDTO> branchData = new ArrayList<BranchDTO>();
		String countries = "";
		String regions = "";
		String subregions = "";
		String branches="";
		
	
		/*
		String queryString="select sum(count) opp_count, sum(converted_factored_amt)  total_factored_amount, "
				+ "sum(converted_unfactored_amt)  total_unfactored_amount, sum(case when forecast_status = 'Not Started' then 1 else 0 end) not_stated_count, "
				+ "sum(case when forecast_status = 'In Progress' then 1 else 0 end) in_progress_count, sum(case when forecast_status = 'Submitted' then 1 else 0 end) "
				+ "submitted_count "
				+ " from webforecastdev.rep1_summary where 1=1";
				*/
		
		
		/*String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns,sum(In_progress) as ip,sum(submitted) as sub "
				+ " , sum(rolling_90days_factamt) as  rfmt,sum(rolling_90days_unfactamt) as runfmt  "
				+ " from(select opp_data_source,opp_data_for_month,region, jci_reporting_country, sub_region, branch_code, forecast_status, opp_count,converted_factored_amt,converted_unfactored_amt,"
				+ "  rolling_90days_factamt,rolling_90days_unfactamt,  case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A' ";		
		*/
		//StringBuffer innnerQuery=new StringBuffer(" group by status_rank,region,jci_reporting_country, sub_region,branch_code) x where 1=1 ");
		String str="SELECT sum(opp_count) as opc , sum(converted_factored_amt) as fmt, sum(converted_unfactored_amt) as unfmt, sum(not_started) as ns,sum(In_progress) as ip,sum(submitted) as sub "
				+ " , sum(rolling_90days_factamt) as  rfmt,sum(rolling_90days_unfactamt) as runfmt  "
			+ "	from(select branch_code, sum(opp_count) opp_count, sum(converted_factored_amt) converted_factored_amt , sum(converted_unfactored_amt)  converted_unfactored_amt,"  
		+"	sum(rolling_90days_factamt) rolling_90days_factamt "
			+" ,sum(rolling_90days_unfactamt) rolling_90days_unfactamt, "
				+ " case when forecast_status = 'In Progress' then 1 else 0 end In_progress, case when forecast_status = 'Not Started' then 1 else 0 end not_started, case when forecast_status = 'Submitted' then 1 else 0 end submitted, case when forecast_status = 'System Submitted' then 1 else 0 end system_submitted, case when forecast_status = 'No Forecast' then 1 else 0 end no_forecast FROM webforecastdev.forecast_branch_summary where 1=1 and isactive='A' ";		
		
		StringBuffer sb = new StringBuffer(str);
		List<String> regionList = new ArrayList<String>();
		List<String> countryList = new ArrayList<String>();
		List<String> subregionList = new ArrayList<String>();
		List<String> branchList = new ArrayList<String>();
		
		if (branchinput.getRegions() != null
				&& !(branchinput.getRegions().isEmpty())) {

			for (ValueDTO valueDTO : branchinput.getRegions()) {
				regionList.add(valueDTO.getName());
			}

			regions = GetStringFromList.getStringFromListComma(regionList);
			
			sb.append(" and region in " + regions);
		}

		if (branchinput.getCountries() != null
				&& !(branchinput.getCountries().isEmpty()) && (branchinput.getCountries().get(0).getName()!=null)) {
			for (ValueDTO valueDTO : branchinput.getCountries()) {
				countryList.add(valueDTO.getName());
			}
			
			countries = GetStringFromList.getStringFromListComma(countryList);
			if("''".equalsIgnoreCase(countries)){
				
			}
			else{
				sb.append(" and jci_reporting_country in " + countries);	
			}
			
		}

		if (branchinput.getSubregions() != null
				&& !(branchinput.getSubregions().isEmpty()) && branchinput.getSubregions().get(0).getName()!=null && !("NA".equalsIgnoreCase(branchinput.getSubregions().get(0).getName()))) {

			for (ValueDTO valueDTO : branchinput.getSubregions()) {
				subregionList.add(valueDTO.getName());
			}

			subregions = GetStringFromList.getStringFromListComma(subregionList);
			if("''".equalsIgnoreCase(subregions)){
				
			}
			else{
			sb.append(" and sub_region in " + subregions);
		}}
		
		if (branchinput.getBranches() != null
				&& !(branchinput.getBranches().isEmpty()) && (branchinput.getBranches().get(0).getName()!=null)) {

			for (ValueDTO valueDTO : branchinput.getBranches()) {
				branchList.add(valueDTO.getName());
			}

			branches = GetStringFromList.getStringFromListComma(branchList);
			if("''".equalsIgnoreCase(branches)){
				
			}
			else{
			sb.append(" and branch_code in " + branches);
		}
			}

		sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
			//region footer
			List<Object[]> objects=branchDao.getBranchSummaryDetails(sb.toString());
			for(Object[] obj:objects){
			dto.setOpportunitesLable("Opportunities");
			if(obj[0]!=null)
			{
			dto.setOpportunitesVal(((BigDecimal)obj[0]).toString());
			}
			
			dto.setTotalfactordLable("Total Factored Amt.");
			if(obj[1]!=null){
			dto.setTotalfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1])).substring(1));
			}
			dto.setTotalunfactordLable("Total Unfactored Amt.");
			if(obj[2]!=null){
			dto.setTotalunfactoredVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2])).substring(1));
			}
			dto.setNotstartedLable("Branches Not Started");
			if(obj[3]!=null)
			dto.setNotstartedVal(((BigInteger)obj[3]).toString());
			dto.setInprogressLable("Branches In Progress");
			if(obj[4]!=null)
			dto.setInprogressVal(((BigInteger)obj[4]).toString());
			dto.setSubmittedLable("Branches Submitted");
			if(obj[5]!=null)
			dto.setSubmittedVal(((BigInteger)obj[5]).toString());
			if(obj[6]!=null){
				dto.setRollingDaysFactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[6])).substring(1));
			}
			if(obj[7]!=null){
				dto.setRollingDaysUnfactAmtVal((DataTypeConvert.get$StringFromNumers((BigDecimal)obj[7])).substring(1));
			}
			}
		
		
		
		

		return dto;
	}

	
	public BranchOutPutDTO submitForecastOnBranch(BranchInputVO input,
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
		if("Branch Forecaster".equalsIgnoreCase(user_role)){
			str="B"+st.substring(1, 6);
		}
		if("Sub Region Forecaster".equalsIgnoreCase(user_role)){
			str="S"+st.substring(1, 6);
		}
		branchDao.submitForecastOnBranch(input, attribute, user_role, str);
		

		return null;
}

	public LOBOutDTO getLobBranchDetails(ReportGridInputMultipleVO regionInputDTO,
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
		List<String> subRegionList=new ArrayList<String>();
		List<String> branchList=new ArrayList<String>();
		String regions="";
		String subRegions="";
		String branches="";
		String countries="";
		String queryString="SELECT branch_code, lob, forecast_status,"
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
		if(regionInputDTO.getCountries()!=null && !(regionInputDTO.getCountries().isEmpty())){
			for(ValueDTO valueDTO:regionInputDTO.getCountries()){
				countryList.add(valueDTO.getName());
			}
			 countries=GetStringFromList.getStringFromListComma(countryList);
			 if("''".equalsIgnoreCase(countries)){
				 
			 }
			 else{
				 sb.append(" and  jci_reporting_country in "+countries);
			 }
			 
		}
		if(regionInputDTO.getSubregions()!=null && !(regionInputDTO.getSubregions().isEmpty()) &&  (regionInputDTO.getSubregions().get(0).getName()!=null)){
			for(ValueDTO valueDTO:regionInputDTO.getSubregions()){
				subRegionList.add(valueDTO.getName());
			}
			 subRegions=GetStringFromList.getStringFromListComma(subRegionList);
			 if("''".equalsIgnoreCase(subRegions)){
				 
			 }
			 else{
				 sb.append(" and  sub_region in "+subRegions);
			 }
			 
		}
		if(regionInputDTO.getBranches()!=null && !(regionInputDTO.getBranches().isEmpty())){
			for(ValueDTO valueDTO:regionInputDTO.getBranches()){
				branchList.add(valueDTO.getName());
			}
			 branches=GetStringFromList.getStringFromListComma(branchList);	
			 if("''".equalsIgnoreCase(branches)){
				 
			 }
			 else{
				 sb.append(" and  branch_code in "+branches);
			 }
			 
		}
		
		sb.append(" and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'");
		
		List<Object[]> objects=branchDao.getLOBBranchDetails(sb.toString());
		for(Object[] obj:objects){
			LOBGridDTO dto=new LOBGridDTO();
			//dto.setRegion((String)obj[0]);
			dto.setCountry((String)obj[0]);
			dto.setFirstColumn((String)obj[0]);
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
