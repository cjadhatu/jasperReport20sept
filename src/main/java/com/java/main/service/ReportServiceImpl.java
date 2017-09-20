package com.java.main.service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dao.ReportDao;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.DropDownVO;
import com.java.main.dto.DynamicReportDataDTO;
import com.java.main.dto.FieldhistoryTraRepDTO;
import com.java.main.dto.ForecastDetailsReportCMDTO;
import com.java.main.dto.ForecastDetailsReportDTO;
import com.java.main.dto.HQandRegionalLobSubRepDTO;
import com.java.main.dto.HQandRegionalReportsDTO;
import com.java.main.dto.HQandRegionalSubRepDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportGridInputVO;
import com.java.main.dto.ReportGridVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.ReportOutPutVO;
import com.java.main.dto.SubmissionHistoryTraReportDTO;
import com.java.main.dto.SumDetailReportResDTO;
import com.java.main.dto.SummaryReportDTO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.GetStringFromList;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	ReportDao reportDao;
@Autowired 
 ForecasteService forecasteService;
	public List<DropDownVO> getRegionList(ReportInputMultipleVO input) {
		// TODO Auto-generated method stub
		
		
		String values=getCommaSperatedStringByList(input.getTypeSources());

		List<String> resultSet = reportDao.getRegionList(values);
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();
			// dd.setId(Integer.parseInt(str));
			dd.setName(str);
			response.add(dd);
		}
		return response;
	}

	public List<DropDownVO> getSubRegionbyRegion(ReportInputMultipleVO region) {
		// TODO Auto-generated method stub
		String typeSource =getCommaSperatedStringByList(region.getTypeSources());
		String regions =getCommaSperatedStringByList(region.getRegions());
		//String queryString="select distinct sub_region from webforecastdev.opportunity_iec where 1=1  and opp_data_source in "+typeSource+" and region in "+regions;
		String queryString="select distinct subregion from webforecastdev.subregionmap  where 1=1 and country_name  in "+regions+" and isactive ='A' order by 1";
		List<String> resultSet = reportDao.getSubRegionByRegion(queryString);
		
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();
			// dd.setId(Integer.parseInt(str));
			dd.setName(str);
			response.add(dd);
		}
		
		
		return response;
	}

	public List<DropDownVO> getcountry(ReportInputMultipleVO reportInputVO) {
		// TODO Auto-generated method stub
		//String typeSource =getCommaSperatedStringByList(reportInputVO.getTypeSources());
		String regions =getCommaSperatedStringByList(reportInputVO.getRegions());
		//String subRegions =getCommaSperatedStringByList(reportInputVO.getSubregions());
		//String queryString="select distinct jci_reporting_country from webforecastdev.opportunity_iec where 1=1  and opp_data_source in "+typeSource+" and region in "+regions+" and sub_region in "+subRegions;
		String queryString="select country_name from webforecastdev.regioncountrymap where 1=1 and region in "+regions+" and isactive ='A' order by 1";
		//String values = getCommaSperatedStringByList(reportInputVO.getValues());
		
		List<String> resultSet = reportDao.getcountry(queryString);
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();

			dd.setName(str);
			response.add(dd);
		}
		return response;

	}

	public List<DropDownVO> getBranches(ReportInputMultipleVO reportInputVO) {
		// TODO Auto-generated method stub
		String subRegions="";
		String subRegionsQuery="";
		String countries="";
		String typeSource =getCommaSperatedStringByList(reportInputVO.getTypeSources());
		String regions =getCommaSperatedStringByList(reportInputVO.getRegions());
		
		if(reportInputVO.getSubregions()!=null && reportInputVO.getSubregions().size()>0 ){
		 subRegions =getCommaSperatedStringByList(reportInputVO.getSubregions());
		 
		 subRegionsQuery=" and subregion  in"; 
		}
		else
		{
		subRegions ="('NA')";
		 
		subRegionsQuery=" and subregion  in"; 
		}
/*		if(regions.contains("China"))
		{
			countries=regions;
		}
		else
		{
		 countries =getCommaSperatedStringByList(reportInputVO.getCountries());
		}*/
	
		
		if(reportInputVO.getRegions().get(0).getName().equalsIgnoreCase("China"))
		{
			countries="('China')";
		}
		else
		{
		 countries =getCommaSperatedStringByList(reportInputVO.getCountries());
		}
				
		//String queryString="select distinct branch_code from webforecastdev.opportunity_iec where 1=1  and opp_data_source in "+typeSource+" and region in "+regions+" "+subRegionsQuery+" "+subRegions+" and  jci_reporting_country in "+countries;
		
		String queryString="select forecast_branchname from webforecastdev.branchcountrymap  where 1=1 "+subRegionsQuery+" "+subRegions+" and  country_name  in "+countries+" and isactive ='A' order by 1";
		//String values = getCommaSperatedStringByList(reportInputVO.getValues());
		List<String> resultSet = reportDao.getbranches(queryString);
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();

			dd.setName(str);
			response.add(dd);
		}
		return response;
	}

	public SummaryReportDTO getsummaryReport(ReportGridInputMultipleVO input) {
		// TODO Auto-generated method stub
		String sources = "";
		String regions = "";
		String subRegions = "";
		String countries = "";
		String branches = "";
		String queryString = " where 1=1 ";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");
		if (input.getTypeSources() != null
				&& !(input.getTypeSources().isEmpty())) {
			sources = getCommaSperatedStringByList(input.getTypeSources());
			sb.append("and opp_data_source in ");
			sb.append(sources);
			queryString = queryString + " and opp_data_source in " + sources;
		}
		if (input.getRegions() != null && !(input.getRegions().isEmpty())) {
			regions = getCommaSperatedStringByList(input.getRegions());
			sb.append("and region in ");
			sb.append(regions);
		}
		if (input.getSubregions() != null && !(input.getSubregions().isEmpty())) {
			subRegions = getCommaSperatedStringByList(input.getSubregions());
			sb.append("and sub_region in ");
			sb.append(subRegions);
		}
		if (input.getCountries() != null && !(input.getCountries().isEmpty())) {
			countries = getCommaSperatedStringByList(input.getCountries());
			sb.append("and jci_reporting_country in ");
			sb.append(countries);
		}
		if (input.getBranches() != null && !(input.getBranches().isEmpty())) {
			branches = getCommaSperatedStringByList(input.getBranches());
			sb.append("and branch_code in ");
			sb.append(branches);
		}
		List<SummaryReportDTO> resultList = new ArrayList<SummaryReportDTO>();
		List<Object[]> SummaryReport = reportDao.getsummaryReport(
				new ReportGridInputVO(), sb.toString());
		Locale locale = new Locale("en", "US");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
		for (Object[] sumreport : SummaryReport) {
			SummaryReportDTO summaryReportDTO = new SummaryReportDTO();

			Integer TotalRegion = ((BigInteger) sumreport[0]).intValue();
			summaryReportDTO.setTotalRegion(TotalRegion);
			Integer TotalSubRegion = ((BigInteger) sumreport[1]).intValue();
			summaryReportDTO.setTotalSubRegion(TotalSubRegion);
			Integer TotalCountry = ((BigInteger) sumreport[2]).intValue();
			summaryReportDTO.setTotalCountry(TotalCountry);
			Integer TotalBranch = ((BigInteger) sumreport[3]).intValue();
			summaryReportDTO.setTotalBranch(TotalBranch);
			BigDecimal bd = new BigDecimal(0);
			bd = (BigDecimal) sumreport[4];
			if(bd!=null)
			{
			Integer TotalOpp = bd.intValue();
			summaryReportDTO.setTotalOpp(TotalOpp);
			}
			// Integer TotalFactoredAmount = ((BigInteger)
			// sumreport[5]).intValue();

			BigDecimal totalFA = new BigDecimal(0);
			totalFA = (BigDecimal) sumreport[5];
			if(totalFA!=null)
			{
			//Integer totalFAMT = totalFA.intValue();

			summaryReportDTO.setTotalFactoredAmount(fmt.format((totalFA)));
			}
			// Integer TotalunfactoredAmount = ((BigInteger)
			// sumreport[6]).intValue();
			BigDecimal totalUFA = new BigDecimal(0);
			totalUFA = (BigDecimal) sumreport[6];
			if(totalUFA!=null)
			{
			//Integer totalUFAMT = totalUFA.intValue();

			summaryReportDTO.setTotalunfactoredAmount(fmt.format((totalUFA)));
			}
			// add new
			BigDecimal totalCFA = new BigDecimal(0);
			totalCFA = (BigDecimal) sumreport[7];
			if(totalCFA!=null)
			{
			//Integer totalCFAMT = totalCFA.intValue();

			summaryReportDTO.setConvertedFactoredAmt(fmt.format((totalCFA)));
			}
			// System.out.println("----------------------------------getConvertedFactoredAmt"+summaryReportDTO.getConvertedFactoredAmt());
			BigDecimal totalCUFA = new BigDecimal(0);
			totalCUFA = (BigDecimal) sumreport[8];
			if(totalCUFA!=null)
			{
			//Integer totalCUFAMT = totalCUFA.intValue();

			summaryReportDTO.setConvertedunFactoredAmt(fmt
					.format((totalCUFA)));
			}
			System.out
					.println("-------------------------------getConvertedFactoredAmt"
							+ summaryReportDTO.getConvertedunFactoredAmt());

			resultList.add(summaryReportDTO);

		}

		return resultList.get(0);
	}

	public ReportOutPutVO getGridReportsDetails(
			ReportGridInputMultipleVO input) {
		// TODO Auto-generated method stub
		ReportOutPutVO response=new ReportOutPutVO();
		String sources = "";
		String regions = "";
		String subRegions = "";
		String countries = "";
		String branches = "";
		String queryString = " where 1=1 ";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");
		if (input.getTypeSources() != null
				&& !(input.getTypeSources().isEmpty())) {
			sources = getCommaSperatedStringByList(input.getTypeSources());
			sb.append(" and opp_data_source in ");
			sb.append(sources);
			queryString = queryString + " and opp_data_source in " + sources;
		}
		if (input.getRegions() != null && !(input.getRegions().isEmpty())) {
			regions = getCommaSperatedStringByList(input.getRegions());
			sb.append(" and region in ");
			sb.append(regions);
		}
		if (input.getSubregions() != null && !(input.getSubregions().isEmpty())) {
			subRegions = getCommaSperatedStringByList(input.getSubregions());
			sb.append(" and sub_region in ");
			sb.append(subRegions);
		}
		if (input.getCountries() != null && !(input.getCountries().isEmpty())) {
			countries = getCommaSperatedStringByList(input.getCountries());
			sb.append(" and jci_reporting_country in ");
			sb.append(countries);
		}
		if (input.getBranches() != null && !(input.getBranches().isEmpty())) {
			branches = getCommaSperatedStringByList(input.getBranches());
			sb.append(" and branch_code in ");
			sb.append(branches);
		}

		List<ReportGridVO> output = new ArrayList<ReportGridVO>();
		List<Object[]> objects = reportDao.getGridReportsDetails(
				new ReportGridInputVO(), sb.toString());

		BigDecimal totalFactoredAmtLocalCur=new BigDecimal(0);
		BigDecimal totalunFactoredAmtLocalCur=new BigDecimal(0);;
		BigDecimal totalFactoredAmt=new BigDecimal(0);;
		BigDecimal totalunfactoredAmt=new BigDecimal(0);
		
		BigDecimal totalFactoredAmtLocalCurTemp=new BigDecimal(0);
		BigDecimal totalunFactoredAmtLocalCurTemp=new BigDecimal(0);;
		BigDecimal totalFactoredAmtTemp=new BigDecimal(0);;
		BigDecimal totalunfactoredAmtTemp=new BigDecimal(0);
		
		Locale locale = new Locale("en", "US");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);

		for (Object[] obj : objects) {
			ReportGridVO vo = new ReportGridVO();
			if (obj[0] != null) {
				vo.setRegion((String) obj[0]);
			}
			if (obj[1] != null) {
				vo.setSubRegion((String) obj[1]);
			}
			if (obj[2] != null) {
				vo.setCountry((String) obj[2]);
			}
			if (obj[3] != null) {
				vo.setBranch((String) obj[3]);
			}
			if (obj[4] != null) {
				vo.setOpportunityName((String) obj[4]);
			}
			if (obj[5] != null) {
				vo.setLeadSource((String) obj[5]);
			}
			if (obj[6] != null) {
				vo.setUnfactoredAmount(fmt.format(((BigDecimal) obj[6])).substring(1));
				totalunFactoredAmtLocalCurTemp=(BigDecimal)obj[6];
				totalunFactoredAmtLocalCur=totalunFactoredAmtLocalCur.add(totalunFactoredAmtLocalCurTemp);
				// vo.setUnfactoredAmount(((BigDecimal)obj[6]).toString());
			}
			if (obj[7] != null) {
				vo.setAccountName((String) obj[7]);
			}
			if (obj[8] != null) {
				Date myDate = (Date) obj[8];
				String sdf = new SimpleDateFormat("MM-dd-yyyy").format(myDate);
				vo.setForecastCloseDate(sdf);

			}
			if (obj[9] != null) {
				vo.setStage((String) obj[9]);
			}
			if (obj[10] != null) {
				vo.setProbability(((BigDecimal) obj[10]).toString());
			}
			if (obj[11] != null) {
				vo.setGrossMargin(((BigDecimal) obj[11]).toString());
			}
			if (obj[12] != null) {
				vo.setFactoredAmount(fmt.format(((BigDecimal) obj[12])).substring(1));
				totalFactoredAmtLocalCurTemp=(BigDecimal)obj[12];
				totalFactoredAmtLocalCur=totalFactoredAmtLocalCur.add(totalFactoredAmtLocalCurTemp);
				// vo.setFactoredAmount(((BigDecimal)obj[12]).toString());
			}
			if (obj[13] != null) {
				vo.setSalesLead((String) obj[13]);
			}
			if (obj[13] != null) {
				vo.setSalesLead((String) obj[13]);
			}
			if (obj[14] != null) {
				vo.setSalesManager((String) obj[14]);
			}
			if (obj[15] != null) {
				vo.setSourceType((String) obj[15]);
				;
			}

			if (obj[16] != null) {
				vo.setConvertedFactoredAmt(fmt.format(((BigDecimal) obj[16])));
				totalFactoredAmtTemp=(BigDecimal)obj[16];
				totalFactoredAmt=totalFactoredAmt.add(totalFactoredAmtTemp);
				

			}
			if (obj[17] != null) {
				vo.setConvertedunFactoredAmt(fmt.format(((BigDecimal) obj[17])));
				totalunfactoredAmtTemp=(BigDecimal)obj[17];
				totalunfactoredAmt=totalunfactoredAmt.add(totalunfactoredAmtTemp);
				
			}
			if (obj[18] != null) {
				
				vo.setCurrencyCode((String)obj[18]);
				
			}

			output.add(vo);

		}
		response.setDetailGrid(output);
		response.setTotalFactoredAmt(fmt.format(((BigDecimal) totalFactoredAmt)));
		
		response.setTotalFactoredAmtLocalCur(fmt.format(((BigDecimal) totalFactoredAmtLocalCur)).substring(1));
		response.setTotalunfactoredAmt(fmt.format(((BigDecimal) totalunfactoredAmt)));
		response.setTotalunFactoredAmtLocalCur(fmt.format(((BigDecimal) totalunFactoredAmtLocalCur)).substring(1));
		return response;
	}

	public List<DropDownVO> getDataSource() {
		// TODO Auto-generated method stub
		List<String> resultSet = reportDao.getDataSource();
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();
			// dd.setId(Integer.parseInt(str));
			dd.setName(str);
			response.add(dd);
		}
		return response;
	}

	public String getCommaSperatedStringByList(List<ValueDTO> list) {
		List<String> strings = new ArrayList<String>();
		for (ValueDTO dto : list) {
			strings.add(dto.getName());
		}

		return GetStringFromList.getStringFromListComma(strings);
	}
	public List<DropDownVO> getcountryByRegion(ReportInputMultipleVO reportInputVO) {
		// TODO Auto-generated method stub
		
		String typeSource =getCommaSperatedStringByList(reportInputVO.getTypeSources());
		String regions =getCommaSperatedStringByList(reportInputVO.getRegions());
		//String subRegions =getCommaSperatedStringByList(reportInputVO.getSubregions());
		//String queryString="select distinct jci_reporting_country from webforecastdev.rep1_summary where 1=1  and opp_data_source in "+typeSource+" and region in "+regions;
		String queryString="select country_name from webforecastdev.regioncountrymap where 1=1 and region in "+regions+" and isactive ='A' order by 1";
		List<String> resultSet = reportDao.getcountryByRegion(queryString);
		List<DropDownVO> response = new ArrayList<DropDownVO>();
		for (String str : resultSet) {
			DropDownVO dd = new DropDownVO();

			dd.setName(str);
			response.add(dd);
		}
		return response;

}
	
	
	public StringBuilder getReportsDetails(
			ReportGridInputMultipleVO input) {
		// TODO Auto-generated method stub
		ReportOutPutVO response=new ReportOutPutVO();
		String sources = "";
		String regions = "";
		String subRegions = "";
		String countries = "";
		String branches = "";
		String lobs = "";
		String queryString = " where 1=1 ";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");
		if (input.getTypeSources() != null
				&& !(input.getTypeSources().isEmpty())) {
			sources = getCommaSperatedStringByList(input.getTypeSources());
			sb.append("and opp_data_source in ");
			sb.append(sources);
			queryString = queryString + " and opp_data_source in " + sources;
		}
		if (input.getRegions() != null && !(input.getRegions().isEmpty())) {
			regions = getCommaSperatedStringByList(input.getRegions());
			sb.append("and region in ");
			sb.append(regions);
		}
		if (input.getSubregions() != null && !(input.getSubregions().isEmpty())) {
			subRegions = getCommaSperatedStringByList(input.getSubregions());
			sb.append("and sub_region in ");
			sb.append(subRegions);
		}
		if (input.getCountries() != null && !(input.getCountries().isEmpty())) {
			countries = getCommaSperatedStringByList(input.getCountries());
			sb.append("and jci_reporting_country in ");
			sb.append(countries);
		}
		if (input.getBranches() != null && !(input.getBranches().isEmpty())) {
			branches = getCommaSperatedStringByList(input.getBranches());
			sb.append("and branch_code in ");
			sb.append(branches);
		}
		

		if (input.getLobs() != null && !(input.getLobs().isEmpty())) {
			lobs = getCommaSperatedStringByList(input.getLobs());
			sb.append("and lob in ");
			sb.append(lobs);
		}
		
		return sb;
		
	}
	
	
	public StringBuilder getHQRigionReportsDetails(
			ReportGridInputMultipleVO input) {
		// TODO Auto-generated method stub
		
		
		String regions = "";
		String lobs = "";
		//String queryString = " where 1=1 ";
		StringBuilder sb = new StringBuilder();
		sb.append(" where 1=1 ");
	
		if (input.getRegions() != null && !(input.getRegions().isEmpty())) {
			regions = getCommaSperatedStringByList(input.getRegions());
			sb.append(" and region in ");
			sb.append(regions);
		}
/*
		if (input.getLobs() != null && !(input.getLobs().isEmpty())) {
			lobs = getCommaSperatedStringByList(input.getLobs());
			sb.append(" and lob in ");
			sb.append(lobs);
		}*/
		
		return sb;
		
	}


	public void testquery() {
		// TODO Auto-generated method stub
		
		reportDao.testquery();
		
	}

	public SumDetailReportResDTO getsummeryDetailReport(ReportGridInputMultipleVO dto,StringBuilder criteria) {
		// TODO Auto-generated method stub
		return  reportDao.getsummeryDetailReport(dto,criteria);
	}

	public DynamicReportDataDTO getDynamicReportData(String globalId,String userRole) {/*
		// TODO Auto-generated method stub
		String sources = "";
		String regions = "";
		String subRegions = "";
		String countries = "";
		String branches = "";
		
		DynamicReportDataDTO dynamicReportDataDTO=new DynamicReportDataDTO();
		
		ReportInputMultipleVO  reportInputMultipleVO=new   ReportInputMultipleVO();
		
	     List<ValueDTO> regionsList=new ArrayList<ValueDTO>();
		 List<ValueDTO> countriesList=new ArrayList<ValueDTO>();
		 List<ValueDTO> subregionsList=new ArrayList<ValueDTO>();
		 List<ValueDTO> typeSourcesList=new ArrayList<ValueDTO>();
		 List<ValueDTO> branchesList=new ArrayList<ValueDTO>();
		 
		 List<Object[]> getDynReportList=reportDao.getDynamicReportData(globalId);		
			if("Branch Forecaster".equalsIgnoreCase(userRole)){
				
				
				for(Object[] value:getDynReportList)		
				   {
					sources=(String)value[0];
					regions=(String)value[1];
					subRegions=(String)value[2];
					countries=(String)value[3];
					branches=(String)value[4];
					dynamicReportDataDTO.setSelectedSourceType(sources);
					
					dynamicReportDataDTO.setSelectedregion(regions);
					dynamicReportDataDTO.setSelectedsubRegion(subRegions);
					dynamicReportDataDTO.setSelectedcountry(countries);
					dynamicReportDataDTO.setSelectedbranch(branches);
					
											
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
				
				dynamicReportDataDTO.setSourceType(typeSourcesList);
				dynamicReportDataDTO.setRegion(regionsList);
				dynamicReportDataDTO.setSubRegion(subregionsList);
				dynamicReportDataDTO.setCountry(countriesList);
				dynamicReportDataDTO.setBranch(branchesList);
				
				
				
			}
		
		
		return dynamicReportDataDTO;
	*/
		DynamicReportDataDTO dynamicReportDataDTO=new DynamicReportDataDTO();
		if("jpagar3".equalsIgnoreCase(globalId)|| "ccutlema".equalsIgnoreCase(globalId)){
			dynamicReportDataDTO=getMultipleUserGpark(globalId);
			
		}
		else
		{
		AddNewOpportunity	addNewOpportunity=forecasteService.getAddNewOpportunity(globalId, userRole);
		
		dynamicReportDataDTO.setBranch(addNewOpportunity.getBranch());
		dynamicReportDataDTO.setCountry(addNewOpportunity.getCountry());
		dynamicReportDataDTO.setRegion(addNewOpportunity.getRegion());
		dynamicReportDataDTO.setSubRegion(addNewOpportunity.getSubRegion());
		dynamicReportDataDTO.setSourceType(addNewOpportunity.getSourceType());
		dynamicReportDataDTO.setSelectedbranch(addNewOpportunity.getSelectedbranch());
		dynamicReportDataDTO.setSelectedcountry(addNewOpportunity.getSelectedcountry());
		dynamicReportDataDTO.setSelectedregion(addNewOpportunity.getSelectedregion());
		dynamicReportDataDTO.setSelectedSourceType(addNewOpportunity.getSelectedSourceType());
		dynamicReportDataDTO.setSelectedsubRegion(addNewOpportunity.getSelectedsubRegion());
}
		List<DropDownVO> lobs=forecasteService.getLob();
		List<ValueDTO> lobResponse=new ArrayList<ValueDTO>();
		for(DropDownVO lobobj :lobs){
			ValueDTO d=new ValueDTO();
			d.setName(lobobj.getName());
			lobResponse.add(d);
		}
		dynamicReportDataDTO.setLob(lobResponse);
		if("Sub Region Forecaster".equalsIgnoreCase(userRole) && "China".equalsIgnoreCase(dynamicReportDataDTO.getSelectedregion())){
			dynamicReportDataDTO.setSelectedcountry("China");
		}
		return dynamicReportDataDTO;
	
	}

	public List<FieldhistoryTraRepDTO> getFieldhistoryTrackingreport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		return  reportDao.getFieldhistoryTrackingreport(dto,criteria);
	}

	public ForecastDetailsReportDTO getForecastDetailsreports(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		ForecastDetailsReportDTO detailsReportDTO=new ForecastDetailsReportDTO();
		
		
		
		List<ForecastDetailsReportCMDTO> nminus1listval=new ArrayList<ForecastDetailsReportCMDTO>();
		
		List<ForecastDetailsReportCMDTO> cmlistval=new ArrayList<ForecastDetailsReportCMDTO>();
		
		List<ForecastDetailsReportCMDTO> npluse1listval=new ArrayList<ForecastDetailsReportCMDTO>();
		
		List<ForecastDetailsReportCMDTO> npluse2listval=new ArrayList<ForecastDetailsReportCMDTO>();
		
		List<ForecastDetailsReportCMDTO> lobWiselistval=new ArrayList<ForecastDetailsReportCMDTO>();
		
		 
		 List<Object[]>  nminus1List=reportDao.ForecastDetailsNminus1Report(dto,criteria);
		 
		 List<Object[]>  cMList=reportDao.ForecastDetailsCMReport(dto,criteria);
		 
		 List<Object[]>  nPlus1List=reportDao.ForecastDetailsNPlus1Report(dto,criteria);
		 
		 List<Object[]>  nPlus2List=reportDao.ForecastDetailsNPlus2Report(dto,criteria);
		 
		 
		 List<Object[]>  lobWiseList=reportDao.ForecastDetailsLobWiseReport(dto,criteria);
		 
		 
		 
		 for(Object[] cmval:cMList)
		 {
			 ForecastDetailsReportCMDTO cmdto=new ForecastDetailsReportCMDTO();
			 
			 cmdto.setAccount((String) cmval[0]);
			 cmdto.setOpportunityNumber((String) cmval[1]);
			 cmdto.setName((String) cmval[2]);
			 cmdto.setRegion((String) cmval[3]);
			 cmdto.setCountry((String) cmval[4]);
			 cmdto.setSubregion((String) cmval[5]);
			 cmdto.setBranch((String) cmval[6]);
			 cmdto.setlOB((String) cmval[7]);
			 cmdto.setType((String) cmval[8]);
			 cmdto.setStage((String) cmval[9]);
			 cmdto.setProbability(((BigDecimal) cmval[10]).toString() );
			 cmdto.setCloseDate(((Date) cmval[11]).toString());
			 cmdto.setCurrency((String) cmval[12]);
			 cmdto.setUnfactoredAmount(((BigDecimal) cmval[13]));
			 cmdto.setUnfactoredAmountConverted(((BigDecimal) cmval[14]));
			 cmdto.setFactoredAmount(((BigDecimal) cmval[15]));
			 cmdto.setFactoredAmountConverted(((BigDecimal) cmval[16]));
			 cmdto.setGrossMargin(((BigDecimal) cmval[17]).toString());
			 cmdto.setSalesLead((String) cmval[18]);
			 cmdto.setSalesManager((String) cmval[19]);
			 
			 cmlistval.add(cmdto);
			 
		 }
		 
		 for(Object[] Nm1val:nminus1List)
		 {
			 ForecastDetailsReportCMDTO nm1dto=new ForecastDetailsReportCMDTO();
			 
			 nm1dto.setAccount((String) Nm1val[0]);
			 nm1dto.setOpportunityNumber((String) Nm1val[1]);
			 nm1dto.setName((String) Nm1val[2]);
			 nm1dto.setRegion((String) Nm1val[3]);
			 nm1dto.setCountry((String) Nm1val[4]);
			 nm1dto.setSubregion((String) Nm1val[5]);
			 nm1dto.setBranch((String) Nm1val[6]);
			 nm1dto.setlOB((String) Nm1val[7]);
			 nm1dto.setType((String) Nm1val[8]);
			 nm1dto.setStage((String) Nm1val[9]);
			 nm1dto.setProbability(((BigDecimal) Nm1val[10]).toString() );
			 nm1dto.setCloseDate(((Date) Nm1val[11]).toString());
			 nm1dto.setCurrency((String) Nm1val[12]);
			 nm1dto.setUnfactoredAmount(((BigDecimal) Nm1val[13]));
			 nm1dto.setUnfactoredAmountConverted(((BigDecimal) Nm1val[14]));
			 nm1dto.setFactoredAmount(((BigDecimal) Nm1val[15]));
			 nm1dto.setFactoredAmountConverted(((BigDecimal) Nm1val[16]));
			 nm1dto.setGrossMargin(((BigDecimal) Nm1val[17]).toString());
			 nm1dto.setSalesLead((String) Nm1val[18]);
			 nm1dto.setSalesManager((String) Nm1val[19]);
			 
			 nminus1listval.add(nm1dto);
			 
		 }
		 
		 
		 for(Object[] Np1val:nPlus1List)
		 {
			 ForecastDetailsReportCMDTO np1dto=new ForecastDetailsReportCMDTO();
			 
			 np1dto.setAccount((String) Np1val[0]);
			 np1dto.setOpportunityNumber((String) Np1val[1]);
			 np1dto.setName((String) Np1val[2]);
			 np1dto.setRegion((String) Np1val[3]);
			 np1dto.setCountry((String) Np1val[4]);
			 np1dto.setSubregion((String) Np1val[5]);
			 np1dto.setBranch((String) Np1val[6]);
			 np1dto.setlOB((String) Np1val[7]);
			 np1dto.setType((String) Np1val[8]);
			 np1dto.setStage((String) Np1val[9]);
			 np1dto.setProbability(((BigDecimal) Np1val[10]).toString() );
			 np1dto.setCloseDate(((Date) Np1val[11]).toString());
			 np1dto.setCurrency((String) Np1val[12]);
			 np1dto.setUnfactoredAmount(((BigDecimal) Np1val[13]));
			 np1dto.setUnfactoredAmountConverted(((BigDecimal) Np1val[14]));
			 np1dto.setFactoredAmount(((BigDecimal) Np1val[15]));
			 np1dto.setFactoredAmountConverted(((BigDecimal) Np1val[16]));
			 np1dto.setGrossMargin(((BigDecimal) Np1val[17]).toString());
			 np1dto.setSalesLead((String) Np1val[18]);
			 np1dto.setSalesManager((String) Np1val[19]);
			 
			 npluse1listval.add(np1dto);
			 
		 }
		 
		 
		 for(Object[] Np2val:nPlus2List)
		 {
			 ForecastDetailsReportCMDTO np2dto=new ForecastDetailsReportCMDTO();
			 
			 np2dto.setAccount((String) Np2val[0]);
			 np2dto.setOpportunityNumber((String) Np2val[1]);
			 np2dto.setName((String) Np2val[2]);
			 np2dto.setRegion((String) Np2val[3]);
			 np2dto.setCountry((String) Np2val[4]);
			 np2dto.setSubregion((String) Np2val[5]);
			 np2dto.setBranch((String) Np2val[6]);
			 np2dto.setlOB((String) Np2val[7]);
			 np2dto.setType((String) Np2val[8]);
			 np2dto.setStage((String) Np2val[9]);
			 np2dto.setProbability(((BigDecimal) Np2val[10]).toString() );
			 np2dto.setCloseDate(((Date) Np2val[11]).toString());
			 np2dto.setCurrency((String) Np2val[12]);
			 np2dto.setUnfactoredAmount(((BigDecimal) Np2val[13]));
			 np2dto.setUnfactoredAmountConverted(((BigDecimal) Np2val[14]));
			 np2dto.setFactoredAmount(((BigDecimal) Np2val[15]));
			 np2dto.setFactoredAmountConverted(((BigDecimal) Np2val[16]));
			 np2dto.setGrossMargin(((BigDecimal) Np2val[17]).toString());
			 np2dto.setSalesLead((String) Np2val[18]);
			 np2dto.setSalesManager((String) Np2val[19]);
			 
			 npluse2listval.add(np2dto);
			 
		 }
		 
		 for(Object[] lobWiseval:lobWiseList)
		 {
			 ForecastDetailsReportCMDTO np2dto=new ForecastDetailsReportCMDTO();
			 
			 np2dto.setAccount((String) lobWiseval[0]);
			 np2dto.setOpportunityNumber((String) lobWiseval[1]);
			 np2dto.setName((String) lobWiseval[2]);
			 np2dto.setRegion((String) lobWiseval[3]);
			 np2dto.setCountry((String) lobWiseval[4]);
			 np2dto.setSubregion((String) lobWiseval[5]);
			 np2dto.setBranch((String) lobWiseval[6]);
			 np2dto.setlOB((String) lobWiseval[7]);
			 np2dto.setType((String) lobWiseval[8]);
			 np2dto.setStage((String) lobWiseval[9]);
			 np2dto.setProbability(((BigDecimal) lobWiseval[10]).toString() );
			 np2dto.setCloseDate(((Date) lobWiseval[11]).toString());
			 np2dto.setCurrency((String) lobWiseval[12]);
			 np2dto.setUnfactoredAmount(((BigDecimal) lobWiseval[13]));
			 np2dto.setUnfactoredAmountConverted(((BigDecimal) lobWiseval[14]));
			 np2dto.setFactoredAmount(((BigDecimal) lobWiseval[15]));
			 np2dto.setFactoredAmountConverted(((BigDecimal) lobWiseval[16]));
			 np2dto.setGrossMargin(((BigDecimal) lobWiseval[17]).toString());
			 np2dto.setSalesLead((String) lobWiseval[18]);
			 np2dto.setSalesManager((String) lobWiseval[19]);
			 
			 lobWiselistval.add(np2dto);
			 
		 }
		 
		 detailsReportDTO.setForecastDetailsNminus1List(nminus1listval);
		 detailsReportDTO.setForecastDetailsReportCMList(cmlistval);
		 detailsReportDTO.setForecastDetailsNPluse1List(npluse1listval);
		 detailsReportDTO.setForecastDetailsNPluse2List(npluse2listval);
		 detailsReportDTO.setForecastDetailsLobWiseList(lobWiselistval);
		 
		
		return detailsReportDTO;
	}

	public HQandRegionalReportsDTO getHQandRegionalReports(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		 HQandRegionalReportsDTO regionalReportsDTO=new HQandRegionalReportsDTO();
		 
        List<HQandRegionalSubRepDTO>  forecastSummaryList=new ArrayList<HQandRegionalSubRepDTO>();
        
        List<HQandRegionalSubRepDTO> nminus1listval=new ArrayList<HQandRegionalSubRepDTO>();
		
        List<HQandRegionalSubRepDTO> cmlistval=new ArrayList<HQandRegionalSubRepDTO>();
        
        List<HQandRegionalSubRepDTO> nplus1listval=new ArrayList<HQandRegionalSubRepDTO>();
        
        List<HQandRegionalSubRepDTO> nplus2listval=new ArrayList<HQandRegionalSubRepDTO>();
        
        List<HQandRegionalLobSubRepDTO> top50listval=new ArrayList<HQandRegionalLobSubRepDTO>();
        
        List<HQandRegionalLobSubRepDTO> top20lobwiselistval=new ArrayList<HQandRegionalLobSubRepDTO>();
        
        
        List<Object[]>  forecastSumList=reportDao.HQandRegionalforecastSummaryReport(dto,criteria); 
        
		 List<Object[]>  nminus1List=reportDao.HQandRegionalNminus1Report(dto,criteria);
		 
		 List<Object[]>  cMList=reportDao.HQandRegionalCMReport(dto,criteria);
		 
		 List<Object[]>  nPlus1List=reportDao.HQandRegionalNPlus1Report(dto,criteria);
		 
		 List<Object[]>  nPlus2List=reportDao.HQandRegionalNPlus2Report(dto,criteria);
		 
		 List<Object[]>  to50List=reportDao.HQandRegionalTop50Report(dto,criteria);
		 
		 List<Object[]>  lobWiseList=reportDao.HQandRegionalLobWiseReport(dto,criteria);
		 
		 
		 for(Object[] forecastSum:forecastSumList)
		 {
			 HQandRegionalSubRepDTO cmdto = new HQandRegionalSubRepDTO();
			 
			 
			 cmdto.setRegion((String)forecastSum[0]);
			 cmdto.setOpportunityCount(((BigInteger)forecastSum[1]).toString());
			 cmdto.setAllunfactored(((BigDecimal) forecastSum[2]));
			 cmdto.setAllfactored(((BigDecimal) forecastSum[3]));
			 
			 cmdto.setUcontrols((BigDecimal)forecastSum[4]);
			 cmdto.setUhvac((BigDecimal)forecastSum[5]);
			 cmdto.setUsecurity((BigDecimal)forecastSum[6]);
			 
			 cmdto.setUindustrialrefrigeration((BigDecimal)forecastSum[7]);
			 
			 cmdto.setUmarine((BigDecimal)forecastSum[8]);
			 cmdto.setUfire((BigDecimal)forecastSum[9]);
			 cmdto.setuIntegration((BigDecimal)forecastSum[10]);
			 cmdto.setUtraffic((BigDecimal)forecastSum[11]);
			 cmdto.setUservice((BigDecimal)forecastSum[12]);
			 cmdto.setUretails((BigDecimal)forecastSum[13]);
					 
			 
			 cmdto.setFcontrols((BigDecimal)forecastSum[14]);
			 cmdto.setFhvac((BigDecimal)forecastSum[15]);
			 cmdto.setFsecurity((BigDecimal)forecastSum[16]);
			
			 cmdto.setFindustrialrefrigeration((BigDecimal)forecastSum[17]);	
			 cmdto.setFmarine((BigDecimal)forecastSum[18]);
			 cmdto.setFfire((BigDecimal)forecastSum[19]);
			 cmdto.setfIntegration((BigDecimal)forecastSum[20]);
			 cmdto.setFtraffic((BigDecimal)forecastSum[21]);			
			 cmdto.setFservice((BigDecimal)forecastSum[22]);			
			 cmdto.setFretails((BigDecimal)forecastSum[23]);
			 cmdto.setUna((BigDecimal)forecastSum[24]);
			 cmdto.setFna((BigDecimal)forecastSum[25]);
			 forecastSummaryList.add(cmdto);
			 
		 }
		 
		 for(Object[] nm1:nminus1List)
		 {
			 HQandRegionalSubRepDTO nm1dto = new HQandRegionalSubRepDTO();
			 
			 
			 nm1dto.setRegion((String)nm1[0]);
			 nm1dto.setOpportunityCount(((BigInteger)nm1[2]).toString());
			 nm1dto.setAllunfactored(((BigDecimal) nm1[3]));
			 nm1dto.setAllfactored(((BigDecimal) nm1[4]));
			 
			 nm1dto.setUcontrols((BigDecimal)nm1[5]);
			 nm1dto.setUhvac((BigDecimal)nm1[6]);
			 nm1dto.setUsecurity((BigDecimal)nm1[7]);
			 
			 nm1dto.setUindustrialrefrigeration((BigDecimal)nm1[8]);
			 
			 nm1dto.setUmarine((BigDecimal)nm1[9]);
			 nm1dto.setUfire((BigDecimal)nm1[10]);
			 nm1dto.setuIntegration((BigDecimal)nm1[11]);
			 nm1dto.setUtraffic((BigDecimal)nm1[12]);
			 nm1dto.setUservice((BigDecimal)nm1[13]);
			 nm1dto.setUretails((BigDecimal)nm1[14]);
					 
			 
			 nm1dto.setFcontrols((BigDecimal)nm1[15]);
			 nm1dto.setFhvac((BigDecimal)nm1[16]);
			 nm1dto.setFsecurity((BigDecimal)nm1[17]);
			
			 nm1dto.setFindustrialrefrigeration((BigDecimal)nm1[18]);	
			 nm1dto.setFmarine((BigDecimal)nm1[19]);
			 nm1dto.setFfire((BigDecimal)nm1[20]);
			 nm1dto.setfIntegration((BigDecimal)nm1[21]);
			 nm1dto.setFtraffic((BigDecimal)nm1[22]);			
			 nm1dto.setFservice((BigDecimal)nm1[23]);			
			 nm1dto.setFretails((BigDecimal)nm1[24]);
			 nm1dto.setUna((BigDecimal)nm1[25]);
			 nm1dto.setFna((BigDecimal)nm1[26]);
			
			 nminus1listval.add(nm1dto);
			 
		 }
		 
		 for(Object[] cm:cMList)
		 {
			 HQandRegionalSubRepDTO cmdto = new HQandRegionalSubRepDTO();
			 
			 
			 cmdto.setRegion((String)cm[0]);
			 cmdto.setOpportunityCount(((BigInteger)cm[2]).toString());
			 cmdto.setAllunfactored(((BigDecimal) cm[3]));
			 cmdto.setAllfactored(((BigDecimal) cm[4]));
			 
			 cmdto.setUcontrols((BigDecimal)cm[5]);
			 cmdto.setUhvac((BigDecimal)cm[6]);
			 cmdto.setUsecurity((BigDecimal)cm[7]);
			 
			 cmdto.setUindustrialrefrigeration((BigDecimal)cm[8]);
			 
			 cmdto.setUmarine((BigDecimal)cm[9]);
			 cmdto.setUfire((BigDecimal)cm[10]);
			 cmdto.setuIntegration((BigDecimal)cm[11]);
			 cmdto.setUtraffic((BigDecimal)cm[12]);
			 cmdto.setUservice((BigDecimal)cm[13]);
			 cmdto.setUretails((BigDecimal)cm[14]);
					 
			 
			 cmdto.setFcontrols((BigDecimal)cm[15]);
			 cmdto.setFhvac((BigDecimal)cm[16]);
			 cmdto.setFsecurity((BigDecimal)cm[17]);
			
			 cmdto.setFindustrialrefrigeration((BigDecimal)cm[18]);	
			 cmdto.setFmarine((BigDecimal)cm[19]);
			 cmdto.setFfire((BigDecimal)cm[20]);
			 cmdto.setfIntegration((BigDecimal)cm[21]);
			 cmdto.setFtraffic((BigDecimal)cm[22]);			
			 cmdto.setFservice((BigDecimal)cm[23]);			
			 cmdto.setFretails((BigDecimal)cm[24]);
			 cmdto.setUna((BigDecimal)cm[25]);
			 cmdto.setFna((BigDecimal)cm[26]);
			 cmlistval.add(cmdto);
			 
		 }
		 
		 
		 for(Object[] np1:nPlus1List)
		 {
			 HQandRegionalSubRepDTO np1dto = new HQandRegionalSubRepDTO();
			 
			 
			 np1dto.setRegion((String)np1[0]);
			 np1dto.setOpportunityCount(((BigInteger)np1[2]).toString());
			 np1dto.setAllunfactored(((BigDecimal) np1[3]));
			 np1dto.setAllfactored(((BigDecimal) np1[4]));
			 
			 np1dto.setUcontrols((BigDecimal)np1[5]);
			 np1dto.setUhvac((BigDecimal)np1[6]);
			 np1dto.setUsecurity((BigDecimal)np1[7]);
			 
			 np1dto.setUindustrialrefrigeration((BigDecimal)np1[8]);
			 
			 np1dto.setUmarine((BigDecimal)np1[9]);
			 np1dto.setUfire((BigDecimal)np1[10]);
			 np1dto.setuIntegration((BigDecimal)np1[11]);
			 np1dto.setUtraffic((BigDecimal)np1[12]);
			 np1dto.setUservice((BigDecimal)np1[13]);
			 np1dto.setUretails((BigDecimal)np1[14]);
					 
			 
			 np1dto.setFcontrols((BigDecimal)np1[15]);
			 np1dto.setFhvac((BigDecimal)np1[16]);
			 np1dto.setFsecurity((BigDecimal)np1[17]);
			
			 np1dto.setFindustrialrefrigeration((BigDecimal)np1[18]);	
			 np1dto.setFmarine((BigDecimal)np1[19]);
			 np1dto.setFfire((BigDecimal)np1[20]);
			 np1dto.setfIntegration((BigDecimal)np1[21]);
			 np1dto.setFtraffic((BigDecimal)np1[22]);			
			 np1dto.setFservice((BigDecimal)np1[23]);			
			 np1dto.setFretails((BigDecimal)np1[24]);
			 np1dto.setUna((BigDecimal)np1[25]);
			 np1dto.setFna((BigDecimal)np1[26]);
			
			 nplus1listval.add(np1dto);
			 
		 }
		 
		 
		/* for(Object[] np1:nPlus1List)
		 {
			 HQandRegionalSubRepDTO np1dto = new HQandRegionalSubRepDTO();
			 
			 
			 np1dto.setRegion((String)np1[0]);
			 np1dto.setOpportunityCount(((BigInteger)np1[2]);
			 np1dto.setAllunfactored(((BigDecimal) np1[3]));
			 np1dto.setAllfactored(((BigDecimal) np1[4]));
			 
			 np1dto.setUcontrols(((BigDecimal)np1[5]);
			 np1dto.setUhvac(((BigDecimal)np1[6]);
			 np1dto.setUsecurity(((BigDecimal)np1[7]);
			 
			 np1dto.setUindustrialrefrigeration(((BigDecimal)np1[8]);
			 
			 np1dto.setUmarine(((BigDecimal)np1[9]);
			 np1dto.setUfire(((BigDecimal)np1[10]);
			 np1dto.setuIntegration(((BigDecimal)np1[11]);
			 np1dto.setUtraffic(((BigDecimal)np1[12]);
			 np1dto.setUservice(((BigDecimal)np1[13]);
			 np1dto.setUretails(((BigDecimal)np1[14]);
					 
			 
			 np1dto.setFcontrols(((BigDecimal)np1[15]);
			 np1dto.setFhvac(((BigDecimal)np1[16]);
			 np1dto.setFsecurity(((BigDecimal)np1[17]);
			
			 np1dto.setFindustrialrefrigeration(((BigDecimal)np1[18]);	
			 np1dto.setFmarine(((BigDecimal)np1[19]);
			 np1dto.setFfire(((BigDecimal)np1[20]);
			 np1dto.setfIntegration(((BigDecimal)np1[21]);
			 np1dto.setFtraffic(((BigDecimal)np1[22]);			
			 np1dto.setFservice(((BigDecimal)np1[23]);			
			 np1dto.setFretails(((BigDecimal)np1[24]);
			
			 nplus1listval.add(np1dto);
			 
		 }
		 */
		 
		 for(Object[] np2:nPlus2List)
		 {
			 HQandRegionalSubRepDTO np2dto = new HQandRegionalSubRepDTO();
			 
			 
			 np2dto.setRegion((String)np2[0]);
			 np2dto.setOpportunityCount(((BigInteger)np2[2]).toString());
			 np2dto.setAllunfactored(((BigDecimal) np2[3]));
			 np2dto.setAllfactored(((BigDecimal) np2[4]));
			 
			 np2dto.setUcontrols((BigDecimal)np2[5]);
			 np2dto.setUhvac((BigDecimal)np2[6]);
			 np2dto.setUsecurity((BigDecimal)np2[7]);
			 
			 np2dto.setUindustrialrefrigeration((BigDecimal)np2[8]);
			 
			 np2dto.setUmarine((BigDecimal)np2[9]);
			 np2dto.setUfire((BigDecimal)np2[10]);
			 np2dto.setuIntegration((BigDecimal)np2[11]);
			 np2dto.setUtraffic((BigDecimal)np2[12]);
			 np2dto.setUservice((BigDecimal)np2[13]);
			 np2dto.setUretails((BigDecimal)np2[14]);
					 
			 
			 np2dto.setFcontrols((BigDecimal)np2[15]);
			 np2dto.setFhvac((BigDecimal)np2[16]);
			 np2dto.setFsecurity((BigDecimal)np2[17]);
			
			 np2dto.setFindustrialrefrigeration((BigDecimal)np2[18]);	
			 np2dto.setFmarine((BigDecimal)np2[19]);
			 np2dto.setFfire((BigDecimal)np2[20]);
			 np2dto.setfIntegration((BigDecimal)np2[21]);
			 np2dto.setFtraffic((BigDecimal)np2[22]);			
			 np2dto.setFservice((BigDecimal)np2[23]);			
			 np2dto.setFretails((BigDecimal)np2[24]);
			 np2dto.setUna((BigDecimal)np2[25]);
			 np2dto.setFna((BigDecimal)np2[26]);
			
			 nplus2listval.add(np2dto);
			 
		 }
		// account_name,opp_number,opp_name,sales_person,converted_unfactored_amt,gross_margin,probability,converted_factored_amt, region,jci_reporting_country,sub_region,branch_code	 
		 
		 for(Object[] t50:to50List)
		 {
			 HQandRegionalLobSubRepDTO t50dto = new HQandRegionalLobSubRepDTO();
			 
			 t50dto.setAccount((String) t50[0]);
			 t50dto.setOpportunityNumber((String) t50[1]);
			 t50dto.setName((String) t50[2]);
			 t50dto.setLeadSource((String) t50[3]);
			 t50dto.setUnfactoredAmountConverted((BigDecimal) t50[4]);
			 t50dto.setGrossMargin(((BigDecimal) t50[5]).toString());
			 t50dto.setProbability(((BigDecimal) t50[6]).toString());
			 t50dto.setFactoredAmountConverted((BigDecimal) t50[7]);
			 t50dto.setRegion((String) t50[8]);
			 t50dto.setCountry((String) t50[9]);
			 t50dto.setSubregion((String) t50[10]);
			 t50dto.setBranch((String) t50[11]);

			 top50listval.add(t50dto);
			 
		 }
		 
		 
		 
		 for(Object[] t20:lobWiseList)
		 {
			 HQandRegionalLobSubRepDTO t20dto = new HQandRegionalLobSubRepDTO();
			 
			 t20dto.setAccount((String) t20[0]);
			 t20dto.setOpportunityNumber((String) t20[1]);
			 t20dto.setName((String) t20[2]);
			 t20dto.setLeadSource((String) t20[3]);
			 t20dto.setUnfactoredAmountConverted((BigDecimal) t20[4]);
			 t20dto.setGrossMargin(((BigDecimal) t20[5]).toString());
			 t20dto.setProbability(((BigDecimal) t20[6]).toString());
			 t20dto.setFactoredAmountConverted((BigDecimal) t20[7]);
			 t20dto.setRegion((String) t20[8]);
			 t20dto.setCountry((String) t20[9]);
			 t20dto.setSubregion((String) t20[10]);
			 t20dto.setBranch((String) t20[11]);
			 t20dto.setlOB((String) t20[12]);
			 top20lobwiselistval.add(t20dto);
			 
		 }
		 
		 regionalReportsDTO.setHqRegionForecastSummaryList(forecastSummaryList);
		 regionalReportsDTO.setHqRegionForecastNminus1List(nminus1listval);
		 regionalReportsDTO.setHqRegionForecastCMList(cmlistval);
		 regionalReportsDTO.setHqRegionForecastNpluse1List(nplus1listval);
		 regionalReportsDTO.setHqRegionForecastNpluse2List(nplus2listval);
		 regionalReportsDTO.setHqRegionForecastTop50List(top50listval);
		 regionalReportsDTO.setHqRegionForecastTop20List(top20lobwiselistval);
		 
		 
		 
		 
		
		return regionalReportsDTO;
	}
	
	public ForecastDetailsReportDTO getSalesForecastsummaryreport(ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				ForecastDetailsReportDTO detailsReportDTO=new ForecastDetailsReportDTO();
				
				
				
				List<ForecastDetailsReportCMDTO> nminus1listval=new ArrayList<ForecastDetailsReportCMDTO>();
				
				List<ForecastDetailsReportCMDTO> cmlistval=new ArrayList<ForecastDetailsReportCMDTO>();
				
				List<ForecastDetailsReportCMDTO> npluse1listval=new ArrayList<ForecastDetailsReportCMDTO>();
				
				List<ForecastDetailsReportCMDTO> npluse2listval=new ArrayList<ForecastDetailsReportCMDTO>();
				
				List<ForecastDetailsReportCMDTO> lobWiselistval=new ArrayList<ForecastDetailsReportCMDTO>();
				
				
				String str="";
				
				if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
					str="HQ";
				}
				if("Regional Forecaster".equalsIgnoreCase(userRole)){
					str="R";
				}
				if("Country Forecaster".equalsIgnoreCase(userRole)){
					str="C";
				}
				if("Branch Forecaster".equalsIgnoreCase(userRole)){
					str="B";
				}
				if("Sub Region Forecaster".equalsIgnoreCase(userRole)){
					str="SR";
				}
				
				
				 List<Object[]>  nMinus2List=reportDao.SalesForecastsummaryNMinus2Report(dto,criteria,str);
				 
				 List<Object[]>  nminus1List=reportDao.SalesForecastsummaryNminus1Report(dto,criteria,str);
				 
				 List<Object[]>  cMList=reportDao.SalesForecastsummaryCMReport(dto,criteria,str);				 				
				 
				 List<Object[]>  lobWiseList=reportDao.SalesForecastsummaryLobWiseReport(dto,criteria);
				 
				 
				 
				 for(Object[] cmval:cMList)
				 {
					 ForecastDetailsReportCMDTO cmdto=new ForecastDetailsReportCMDTO();
					 
					 cmdto.setAccount((String) cmval[0]);
					 cmdto.setOpportunityNumber((String) cmval[1]);
					 cmdto.setName((String) cmval[2]);
					 cmdto.setRegion((String) cmval[3]);
					 cmdto.setCountry((String) cmval[4]);
					 cmdto.setSubregion((String) cmval[5]);
					 cmdto.setBranch((String) cmval[6]);
					 cmdto.setlOB((String) cmval[7]);
					 cmdto.setType((String) cmval[8]);
					 cmdto.setStage((String) cmval[9]);
					 cmdto.setProbability(((BigDecimal) cmval[10]).toString() );
					 if(cmval[11]!=null)
					 {
					 cmdto.setCloseDate(((Date) cmval[11]).toString());
					 }
					 else
					 {
						 cmdto.setCloseDate(""); 
					 }
					 cmdto.setCurrency((String) cmval[12]);
					 cmdto.setUnfactoredAmount(((BigDecimal) cmval[13]));
					 cmdto.setUnfactoredAmountConverted(((BigDecimal) cmval[14]));
					 cmdto.setFactoredAmount(((BigDecimal) cmval[15]));
					 cmdto.setFactoredAmountConverted(((BigDecimal) cmval[16]));
					 cmdto.setGrossMargin(((BigDecimal) cmval[17]).toString());
					 cmdto.setSalesLead((String) cmval[18]);
					 cmdto.setSalesManager((String) cmval[19]);
					 
					 cmlistval.add(cmdto);
					 
				 }
				 
				 for(Object[] Nm1val:nminus1List)
				 {
					 ForecastDetailsReportCMDTO nm1dto=new ForecastDetailsReportCMDTO();
					 
					 nm1dto.setAccount((String) Nm1val[0]);
					 nm1dto.setOpportunityNumber((String) Nm1val[1]);
					 nm1dto.setName((String) Nm1val[2]);
					 nm1dto.setRegion((String) Nm1val[3]);
					 nm1dto.setCountry((String) Nm1val[4]);
					 nm1dto.setSubregion((String) Nm1val[5]);
					 nm1dto.setBranch((String) Nm1val[6]);
					 nm1dto.setlOB((String) Nm1val[7]);
					 nm1dto.setType((String) Nm1val[8]);
					 nm1dto.setStage((String) Nm1val[9]);
					 nm1dto.setProbability(((BigDecimal) Nm1val[10]).toString() );
					 if(Nm1val[11]!=null)
					 {
					 nm1dto.setCloseDate(((Date) Nm1val[11]).toString());
					 }
					 else
					 {
						 nm1dto.setCloseDate(""); 
					 }
					 nm1dto.setCurrency((String) Nm1val[12]);
					 nm1dto.setUnfactoredAmount(((BigDecimal) Nm1val[13]));
					 nm1dto.setUnfactoredAmountConverted(((BigDecimal) Nm1val[14]));
					 nm1dto.setFactoredAmount(((BigDecimal) Nm1val[15]));
					 nm1dto.setFactoredAmountConverted(((BigDecimal) Nm1val[16]));
					 nm1dto.setGrossMargin(((BigDecimal) Nm1val[17]).toString());
					 nm1dto.setSalesLead((String) Nm1val[18]);
					 nm1dto.setSalesManager((String) Nm1val[19]);
					 
					 nminus1listval.add(nm1dto);
					 
				 }
				 
				 
				
				 
				 
				 for(Object[] Np2val:nMinus2List)
				 {
					 ForecastDetailsReportCMDTO np2dto=new ForecastDetailsReportCMDTO();
					 
					 np2dto.setAccount((String) Np2val[0]);
					 np2dto.setOpportunityNumber((String) Np2val[1]);
					 np2dto.setName((String) Np2val[2]);
					 np2dto.setRegion((String) Np2val[3]);
					 np2dto.setCountry((String) Np2val[4]);
					 np2dto.setSubregion((String) Np2val[5]);
					 np2dto.setBranch((String) Np2val[6]);
					 np2dto.setlOB((String) Np2val[7]);
					 np2dto.setType((String) Np2val[8]);
					 np2dto.setStage((String) Np2val[9]);
					 np2dto.setProbability(((BigDecimal) Np2val[10]).toString() );
					 if(Np2val[11]!=null)
					 {
					 np2dto.setCloseDate(((Date) Np2val[11]).toString());
					 }
					 else
					 {
						 np2dto.setCloseDate("");
					 }
					 np2dto.setCurrency((String) Np2val[12]);
					 np2dto.setUnfactoredAmount(((BigDecimal) Np2val[13]));
					 np2dto.setUnfactoredAmountConverted(((BigDecimal) Np2val[14]));
					 np2dto.setFactoredAmount(((BigDecimal) Np2val[15]));
					 np2dto.setFactoredAmountConverted(((BigDecimal) Np2val[16]));
					 np2dto.setGrossMargin(((BigDecimal) Np2val[17]).toString());
					 np2dto.setSalesLead((String) Np2val[18]);
					 np2dto.setSalesManager((String) Np2val[19]);
					 
					 npluse2listval.add(np2dto);
					 
				 }
				 
				 for(Object[] lobWiseval:lobWiseList)
				 {
					 ForecastDetailsReportCMDTO np2dto=new ForecastDetailsReportCMDTO();
					 
					 np2dto.setAccount((String) lobWiseval[0]);
					 np2dto.setOpportunityNumber((String) lobWiseval[1]);
					 np2dto.setName((String) lobWiseval[2]);
					 np2dto.setRegion((String) lobWiseval[3]);
					 np2dto.setCountry((String) lobWiseval[4]);
					 np2dto.setSubregion((String) lobWiseval[5]);
					 np2dto.setBranch((String) lobWiseval[6]);
					 np2dto.setlOB((String) lobWiseval[7]);
					 np2dto.setType((String) lobWiseval[8]);
					 np2dto.setStage((String) lobWiseval[9]);
					 np2dto.setProbability(((BigDecimal) lobWiseval[10]).toString() );
					 if(lobWiseval[11]!=null)
					 {
					 np2dto.setCloseDate(((Date) lobWiseval[11]).toString());
					 }
					 else
					 {
				      np2dto.setCloseDate("");	 
					 }
					 np2dto.setCurrency((String) lobWiseval[12]);
					 np2dto.setUnfactoredAmount(((BigDecimal) lobWiseval[13]));
					 np2dto.setUnfactoredAmountConverted(((BigDecimal) lobWiseval[14]));
					 np2dto.setFactoredAmount(((BigDecimal) lobWiseval[15]));
					 np2dto.setFactoredAmountConverted(((BigDecimal) lobWiseval[16]));
					 np2dto.setGrossMargin(((BigDecimal) lobWiseval[17]).toString());
					 np2dto.setSalesLead((String) lobWiseval[18]);
					 np2dto.setSalesManager((String) lobWiseval[19]);
					 
					 lobWiselistval.add(np2dto);
					 
				 }
				 
				 detailsReportDTO.setForecastDetailsNminus1List(nminus1listval);
				 detailsReportDTO.setForecastDetailsReportCMList(cmlistval);
				 detailsReportDTO.setForecastDetailsNPluse1List(npluse1listval);
				 detailsReportDTO.setForecastDetailsNPluse2List(npluse2listval);
				 detailsReportDTO.setForecastDetailsLobWiseList(lobWiselistval);
				 
				
				return detailsReportDTO;
	}

	public List<SubmissionHistoryTraReportDTO> SubmissionHistoryTrackingReports(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		
		List<SubmissionHistoryTraReportDTO> historyTraReportList=new ArrayList<SubmissionHistoryTraReportDTO>();
		
		List<Object[]>  historyTraReportDTOList=reportDao.SubmissionHistoryTrackingReports(dto,criteria);
		System.out.println("---------historyTraReportDTOList------"+historyTraReportDTOList.size());
		for(Object[] histralist:historyTraReportDTOList)
		{
			SubmissionHistoryTraReportDTO historyTraReportDTO=new SubmissionHistoryTraReportDTO();
			historyTraReportDTO.setSubmitId((String) histralist[1]);
			historyTraReportDTO.setForecastLevel((String) histralist[2]);
			historyTraReportDTO.setForecastGeoName((String) histralist[3]);
			if(histralist[4] !=null)
			{
			historyTraReportDTO.setSubmittedBy((String) histralist[4]);
			}
			else
			{
				historyTraReportDTO.setSubmittedBy("");
			}
			if(histralist[5] !=null)
			{
				SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		        String strDate = sdf.format((Timestamp) histralist[5]);
		        System.out.println("Current date in String Format: " + strDate);
				
			historyTraReportDTO.setSubmittedDate(strDate);
			}
			else
			{
				historyTraReportDTO.setSubmittedDate("");
			}
			
			historyTraReportDTO.setFactoredAmountC((BigDecimal) histralist[7]);
			historyTraReportDTO.setUnfactoredAmountC((BigDecimal) histralist[8]);
			
			historyTraReportList.add(historyTraReportDTO);
		
			
		}
		
		
		
		
		return historyTraReportList;
	}
public	DynamicReportDataDTO getMultipleUserGpark(String globalId){
	DynamicReportDataDTO response= new DynamicReportDataDTO();
List<Object[]>	objects=reportDao.getUserMultiple(globalId);
List<ValueDTO> region=new ArrayList<ValueDTO>();
List<ValueDTO> source=new ArrayList<ValueDTO>();
String selectedRegion="";
for (Object[] objects2 : objects) {
	ValueDTO d=new ValueDTO();
	d.setName((String)objects2[0]);
	region.add(d);
	ValueDTO d1=new ValueDTO();
	d1.setName((String)objects2[1]);
	source.add(d1);
	
}

//selectedRegion=StringUtils.join(region, ",");
response.setRegion(region);
response.setSelectedSourceType(source.get(0).getName());
response.setSelectedregion(selectedRegion);
		return response;
	}
}