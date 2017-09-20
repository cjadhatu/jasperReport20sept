package com.java.main.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.DropDownVO;
import com.java.main.dto.FieldhistoryTraRepDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportGridInputVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.SumDetailReportResDTO;
import com.java.main.dto.SummaryReportDTO;
import com.java.main.dto.SummaryReptDTO;

@Repository
public class ReportDaoImpl implements ReportDao {

	@PersistenceContext
	private EntityManager em;

	public List<String> getRegionList(String parameter) {
		// TODO Auto-generated method stub
		// String
		// queryString="select distinct region  from webforecastdev.opportunity_iec where opp_data_source="+"'"+input.getName()+"'";
		// String queryString =
		// "select distinct region  from webforecastdev.opportunity_iec where opp_data_source in "+
		// parameter;
		String queryString = "select region from webforecastdev.vw_region order by 1";

		List<String> objects = em.createNativeQuery(queryString)
				.getResultList();
		return objects;
	}

	public List<String> getSubRegionByRegion(String parameter) {
		// TODO Auto-generated method stub
		// String
		// queryString="select distinct sub_region from webforecastdev.opportunity_iec where 1=1  and  region in "+parameter;
		List<String> objects = em.createNativeQuery(parameter).getResultList();
		return objects;
	}

	public List<String> getcountry(String parameter) {
		// TODO Auto-generated method stub
		List<DropDownVO> resultList = new ArrayList<DropDownVO>();

		// String
		// querString="select distinct jci_reporting_country region  from webforecastdev.opportunity_iec where sub_region='"+reportInputVO.getName()+"'";
		// String
		// querString="select distinct jci_reporting_country region  from webforecastdev.opportunity_iec where sub_region in "+parameter;

		List<String> counrtyname = em.createNativeQuery(parameter)
				.getResultList();

		return counrtyname;

	}

	public List<Object[]> getGridReportsDetails(ReportGridInputVO input,
			String criteria) {
		/*
		 * String criteria="";
		 * 
		 * criteria=criteria+
		 * "where opp_data_source="+"'"+input.getSourceType()+"'";
		 * if(input.getRegion()!=null && input.getRegion()!=""){
		 * criteria=criteria+" and region="+"'"+input.getRegion()+"'"; }
		 * if(input.getSubRegion()!=null && input.getSubRegion()!=""){
		 * criteria=criteria+" and sub_region="+"'"+input.getSubRegion()+"'"; }
		 * if(input.getCountry()!=null && input.getCountry()!=""){
		 * criteria=criteria
		 * +" and jci_reporting_country="+"'"+input.getCountry()+"'"; }
		 * if(input.getBranch()!=null && input.getBranch()!=""){
		 * criteria=criteria+" and branch_code="+"'"+input.getBranch()+"'"; }
		 */
		String queryString = " select region,sub_region,jci_reporting_country,branch_code,opp_name, "
				+ " lead_source,unfactored_amount,account_name,close_date,stagename,probability,gross_margin,factored_amount, "
				+ " sales_person,sales_manager,opp_data_source,converted_factored_amt,converted_unfactored_amt,currency  from webforecastdev.opportunity_iec "
				+ criteria
				+ "and opp_status ='A' and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date()) + "'";
		List<Object[]> list = em.createNativeQuery(queryString).getResultList();
		return list;
	}

	public List<String> getbranches(String parameter) {
		// TODO Auto-generated method stub
		List<DropDownVO> resultList = new ArrayList<DropDownVO>();
		// String
		// querString="select distinct branch_code region  from webforecastdev.opportunity_iec where jci_reporting_country='"+reportInputVO.getName()+"'";
		// String
		// querString="select distinct branch_code region  from webforecastdev.opportunity_iec where jci_reporting_country in "+parameter;

		List<String> brancheList = em.createNativeQuery(parameter)
				.getResultList();

		return brancheList;
	}

	public List<Object[]> getsummaryReport(ReportGridInputVO input,
			String criteria) {
		// TODO Auto-generated method stub
		List<SummaryReportDTO> resultList = new ArrayList<SummaryReportDTO>();

		String querString = "select count(distinct region) region_count,count(distinct sub_region) Subregion_count,count(distinct jci_reporting_country) country, count(distinct branch_code) branch_count, sum(count) opp_count,sum(factored_amount) total_factored_amount,sum(unfactored_amount) total_unfactored_amount,sum(converted_factored_amt) total_factored_amount_USD,sum(converted_unfactored_amt) total_unfactored_amount_USD  from webforecastdev.rep1_summary "
				+ criteria
				+ "and   opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date()) + "'";

		List<Object[]> SummaryReport = em.createNativeQuery(querString)
				.getResultList();
		/*
		 * Locale locale = new Locale("en", "US"); NumberFormat fmt =
		 * NumberFormat.getCurrencyInstance(locale); for(Object[]
		 * sumreport:SummaryReport) { SummaryReportDTO summaryReportDTO=new
		 * SummaryReportDTO();
		 * 
		 * Integer TotalRegion = ((BigInteger) sumreport[0]).intValue();
		 * summaryReportDTO.setTotalRegion(TotalRegion); Integer TotalSubRegion
		 * = ((BigInteger) sumreport[1]).intValue();
		 * summaryReportDTO.setTotalSubRegion(TotalSubRegion); Integer
		 * TotalCountry = ((BigInteger) sumreport[2]).intValue();
		 * summaryReportDTO.setTotalCountry(TotalCountry); Integer TotalBranch =
		 * ((BigInteger) sumreport[3]).intValue();
		 * summaryReportDTO.setTotalBranch(TotalBranch); BigDecimal bd=new
		 * BigDecimal(0); bd=(BigDecimal) sumreport[4]; Integer TotalOpp
		 * =bd.intValue(); summaryReportDTO.setTotalOpp(TotalOpp); // Integer
		 * TotalFactoredAmount = ((BigInteger) sumreport[5]).intValue();
		 * 
		 * BigDecimal totalFA=new BigDecimal(0); totalFA=(BigDecimal)
		 * sumreport[5]; Integer totalFAMT =totalFA.intValue();
		 * 
		 * summaryReportDTO.setTotalFactoredAmount(fmt.format((totalFAMT)));
		 * 
		 * // Integer TotalunfactoredAmount = ((BigInteger)
		 * sumreport[6]).intValue(); BigDecimal totalUFA=new BigDecimal(0);
		 * totalUFA=(BigDecimal) sumreport[6]; Integer totalUFAMT
		 * =totalUFA.intValue();
		 * 
		 * summaryReportDTO.setTotalunfactoredAmount(fmt.format((totalUFAMT)));
		 * 
		 * //add new BigDecimal totalCFA=new BigDecimal(0);
		 * totalCFA=(BigDecimal) sumreport[7]; Integer totalCFAMT
		 * =totalCFA.intValue();
		 * 
		 * summaryReportDTO.setConvertedFactoredAmt(fmt.format((totalCFAMT)));
		 * System
		 * .out.println("----------------------------------getConvertedFactoredAmt"
		 * +summaryReportDTO.getConvertedFactoredAmt()); BigDecimal
		 * totalCUFA=new BigDecimal(0); totalCUFA=(BigDecimal) sumreport[8];
		 * Integer totalCUFAMT =totalCUFA.intValue();
		 * 
		 * 
		 * summaryReportDTO.setConvertedunFactoredAmt(fmt.format((totalCUFAMT)));
		 * System
		 * .out.println("-------------------------------getConvertedFactoredAmt"
		 * +summaryReportDTO.getConvertedunFactoredAmt());
		 * 
		 * resultList.add(summaryReportDTO); }
		 */
		return SummaryReport;
	}

	public List<String> getDataSource() {
		// TODO Auto-generated method stub
		// String
		// queryString="select distinct opp_data_source  from webforecastdev.rep1_summary";
		String queryString = "select distinct opp_data_source from webforecastdev.opportunity_iec";
		List<String> objects = em.createNativeQuery(queryString)
				.getResultList();
		return objects;
	}

	public List<String> getcountryByRegion(String parameter) {
		// TODO Auto-generated method stub
		List<DropDownVO> resultList = new ArrayList<DropDownVO>();

		// String
		// querString="select distinct jci_reporting_country region  from webforecastdev.opportunity_iec where sub_region='"+reportInputVO.getName()+"'";
		// String
		// querString="select distinct jci_reporting_country region  from webforecastdev.opportunity_iec where region in "+parameter;

		List<String> counrtyname = em.createNativeQuery(parameter)
				.getResultList();

		return counrtyname;

	}

	public void testquery() {
		// TODO Auto-generated method stub
		String queryString = "select region,sub_region,jci_reporting_country,branch_code,opp_name,  lead_source,unfactored_amount,account_name,close_date,stagename,probability,gross_margin,factored_amount,  sales_person,sales_manager,opp_data_source,converted_factored_amt,converted_unfactored_amt from webforecastdev.opportunity_iec  where 1=1 and opp_data_source in ('OpenGlobe')and region in ('China')and sub_region in ('China Heating')and jci_reporting_country in ('China')and branch_code in ('CHNHT - BEIJING')limit 10";
		// + criteria.toString() + "limit 10";

		String sumeryquerString = "select count(distinct region) region_count,count(distinct sub_region) Subregion_count,count(distinct jci_reporting_country) country, count(distinct branch_code) branch_count, count(opp_id) opp_count,sum(factored_amount) total_factored_amount,sum(unfactored_amount) total_unfactored_amount,sum(converted_factored_amt) total_factored_amount_USD,sum(converted_unfactored_amt) total_unfactored_amount_USD from webforecastdev.opportunity_iec  where 1=1 and opp_data_source in ('OpenGlobe')and region in ('China')and sub_region in ('China Heating')and jci_reporting_country in ('China')and branch_code in ('CHNHT - BEIJING')and opp_status ='A'";
		System.out.println("-----------------queryString" + queryString);
		System.out.println("-----------------queryString" + sumeryquerString);

		List<Object[]> objects = em.createNativeQuery(queryString)
				.getResultList();
		List<Object[]> objects1 = em.createNativeQuery(sumeryquerString)
				.getResultList();

		System.out.println(objects.size());
		System.out.println(objects1.size());

	}

	public SumDetailReportResDTO getsummeryDetailReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {

		// TODO Auto-generated method stub
		/*
		 * String queryString =
		 * "select region,sub_region,jci_reporting_country,branch_code,opp_name,  lead_source,unfactored_amount,account_name,close_date,stagename,probability,gross_margin,factored_amount,  sales_person,sales_manager,opp_data_source,converted_factored_amt,converted_unfactored_amt from webforecastdev.opportunity_iec  where 1=1 and opp_data_source in ('OpenGlobe')and region in ('China')and sub_region in ('China Heating')and jci_reporting_country in ('China')and branch_code in ('CHNHT - BEIJING')"
		 * ; // + criteria.toString() + "limit 10";
		 * 
		 * 
		 * String sumeryquerString =
		 * "select count(distinct region) region_count,count(distinct sub_region) Subregion_count,count(distinct jci_reporting_country) country, count(distinct branch_code) branch_count, count(opp_id) opp_count,sum(factored_amount) total_factored_amount,sum(unfactored_amount) total_unfactored_amount,sum(converted_factored_amt) total_factored_amount_USD,sum(converted_unfactored_amt) total_unfactored_amount_USD from webforecastdev.opportunity_iec  where 1=1 and opp_data_source in ('OpenGlobe')and region in ('China')and sub_region in ('China Heating')and jci_reporting_country in ('China')and branch_code in ('CHNHT - BEIJING')and opp_status ='A'"
		 * ;
		 */

		SumDetailReportResDTO detailReportResDTO = new SumDetailReportResDTO();
		List<SummaryReptDTO> summaryReptList = new ArrayList<SummaryReptDTO>();
		String queryString = " select region,sub_region,jci_reporting_country,branch_code,opp_name, "
				+ " lead_source,unfactored_amount,account_name,close_date,stagename,probability,gross_margin,factored_amount, "
				+ " sales_person,sales_manager,opp_data_source,converted_factored_amt,converted_unfactored_amt from webforecastdev.opportunity_iec "
				+ criteria.toString() + "and opp_status ='A'";

		String sumeryquerString = "select count(distinct region) region_count,count(distinct sub_region) Subregion_count,"
				+ "count(distinct jci_reporting_country) country, count(distinct branch_code) branch_count, count(opp_id) opp_count,"
				+ "sum(factored_amount) total_factored_amount,sum(unfactored_amount) total_unfactored_amount,"
				+ "sum(converted_factored_amt) total_factored_amount_USD,sum(converted_unfactored_amt) total_unfactored_amount_USD "
				+ "from webforecastdev.opportunity_iec "
				+ criteria.toString()
				+ "and opp_status ='A'";

		System.out.println("-----------------queryString" + queryString);
		System.out.println("-----------------queryString" + sumeryquerString);

		List<Object[]> detailsList = em.createNativeQuery(queryString)
				.getResultList();
		List<Object[]> summaryDet = em.createNativeQuery(sumeryquerString)
				.getResultList();

		for (Object[] summarydetail : summaryDet) {
			SummaryReptDTO summaryReptDTO = new SummaryReptDTO();
			summaryReptDTO.setRegion_count(((BigInteger) summarydetail[0])
					.longValue());
			summaryReptDTO.setSubregion_count(((BigInteger) summarydetail[1])
					.longValue());
			summaryReptDTO.setCountry(((BigInteger) summarydetail[2])
					.longValue());
			summaryReptDTO.setBranch_count(((BigInteger) summarydetail[3])
					.longValue());
			summaryReptDTO.setOpp_count(new BigDecimal(
					(BigInteger) summarydetail[4]));
			summaryReptDTO
					.setTotal_factored_amount(((BigDecimal) summarydetail[5]));
			summaryReptDTO
					.setTotal_unfactored_amount(((BigDecimal) summarydetail[6]));
			summaryReptDTO
					.setTotal_factored_amount_usd(((BigDecimal) summarydetail[7]));
			summaryReptDTO
					.setTotal_unfactored_amount_usd(((BigDecimal) summarydetail[8]));
			summaryReptList.add(summaryReptDTO);
		}

		detailReportResDTO.setDetailList(detailsList);
		detailReportResDTO.setSummaryList(summaryReptList);

		return detailReportResDTO;

	}

	public List<Object[]> getDynamicReportData(String globalId) {
		// TODO Auto-generated method stub
		String queryString = "select datasource,region,subregion,country,branch_code from webforecastdev.forecast_user_rolemap s where s.globalid ='"
				+ globalId + "'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<FieldhistoryTraRepDTO> getFieldhistoryTrackingreport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub

		List<FieldhistoryTraRepDTO> fieldhistoryTraRepList = new ArrayList<FieldhistoryTraRepDTO>();

		String queryString = "select h.* from webforecastdev.opportunity_field_history h, webforecastdev.opportunity_iec o "+criteria +" and h.opp_id = o.opp_id  and h.opp_data_for_month ='"+ new SimpleDateFormat("MMM-yyyy").format(new Date())+"'";
		List<Object[]> fieldHistList = em.createNativeQuery(queryString)
				.getResultList();

		for (Object[] fieldHist : fieldHistList) {

			FieldhistoryTraRepDTO fieldhistoryTraRepDTO = new FieldhistoryTraRepDTO();
			fieldhistoryTraRepDTO.setOpportunityNumber((String) fieldHist[3]);
			fieldhistoryTraRepDTO.setFieldName((String) fieldHist[4]);
			fieldhistoryTraRepDTO.setOldvalue((String) fieldHist[5]);
			fieldhistoryTraRepDTO.setUpdatedValue((String) fieldHist[6]);
			fieldhistoryTraRepDTO.setUpdatedBy((String) fieldHist[7]);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	        String strDate = sdf.format((Timestamp) fieldHist[8]);
			fieldhistoryTraRepDTO.setUpdatedDate(strDate);
			
			fieldhistoryTraRepList.add(fieldhistoryTraRepDTO);

		}

		return fieldhistoryTraRepList;
	}

	public List<Object[]> ForecastDetailsCMReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype "
				+ " ,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec "
				+ criteria
				+" and extract(month from close_date) =  EXTRACT(MONTH FROM NOW() )  "
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'";

		return em.createNativeQuery(queryString).getResultList();

	}

	public List<Object[]> ForecastDetailsNminus1Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype "
				+ " ,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec "
				+ criteria
				+ " and extract(month from close_date) =  EXTRACT(MONTH FROM NOW() - INTERVAL '1 months')"
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> ForecastDetailsNPlus1Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype "
				+ ",stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec "
				+ criteria
				+ " and extract(month from close_date) =  EXTRACT(MONTH FROM NOW() + INTERVAL '1 months') "
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> ForecastDetailsNPlus2Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype "
				+ " ,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec "
				+ criteria
				+ " and extract(month from close_date) =  EXTRACT(MONTH FROM NOW() + INTERVAL '2 months') "
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> ForecastDetailsLobWiseReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " stagetype "
				+ " , stagename "
				+ ",  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " from( select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob, webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype,stagename, probability,close_date,currency,unfactored_amount,coalesce(converted_unfactored_amt,0.00) converted_unfactored_amt,factored_amount, coalesce(converted_factored_amt,0.00) converted_factored_amt,gross_margin,sales_person,sales_manager, row_number() over(partition by lob order by coalesce(converted_factored_amt,0.00) desc) as rn FROM webforecastdev.opportunity_iec "
				+ criteria
				+ ""
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'" + " ) as T where T.rn <= 20";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalforecastSummaryReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select * from webforecastdev.vw_hq_region_forecast_summary"
				+ criteria;
				
		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalNminus1Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select * from webforecastdev.vw_hq_region_monthwise_forecast_summary"
				+ criteria
				+ " and close_date_month=to_char(CURRENT_DATE - INTERVAL '1 months','Mon-YYYY') ";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalCMReport(ReportGridInputMultipleVO dto,
			StringBuilder criteria) {
		// TODO Auto-generated method stub

		String queryString = "select * from webforecastdev.vw_hq_region_monthwise_forecast_summary"
				+ criteria
				+ "  and close_date_month=to_char(current_date,'Mon-YYYY')";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalNPlus1Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select * from webforecastdev.vw_hq_region_monthwise_forecast_summary"
				+ criteria
				+ "  and close_date_month=to_char(CURRENT_DATE + INTERVAL '1 months','Mon-YYYY')";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalNPlus2Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select * from webforecastdev.vw_hq_region_monthwise_forecast_summary"
				+ criteria
				+ "  and close_date_month=to_char(CURRENT_DATE + INTERVAL '2 months','Mon-YYYY') ";
		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalTop50Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select account_name,opp_number,opp_name,sales_person,converted_unfactored_amt,gross_margin,probability,converted_factored_amt, region,jci_reporting_country,sub_region,branch_code from webforecastdev.opportunity_iec"
				+ criteria
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A' order by coalesce(converted_factored_amt,0.00) desc limit 50";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> HQandRegionalLobWiseReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		String queryString = "select account_name,opp_name,opp_number,sales_person,converted_unfactored_amt,gross_margin,probability,converted_factored_amt,"
				+ " region,jci_reporting_country,sub_region,branch_code,lob from ( select account_name,opp_number,opp_name,region,jci_reporting_country,"
				+ "sub_region,branch_code,lob, probability,coalesce(converted_unfactored_amt,0.00) converted_unfactored_amt,"
				+ "coalesce(converted_factored_amt,0.00) converted_factored_amt,gross_margin,sales_person, row_number() over(partition by lob order by coalesce(converted_factored_amt,0.00) desc) "
				+ "as rn FROM webforecastdev.opportunity_iec"+criteria +"and opp_data_for_month ='"+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'" + " ) as T where T.rn <= 20";

		return em.createNativeQuery(queryString).getResultList();
	}
	
	
	public List<Object[]> SalesForecastsummaryNMinus2Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole) {
		/*String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,contract_type,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec "+criteria+" and extract(month from close_date) =  EXTRACT(MONTH FROM NOW() - INTERVAL '2 months') and opp_status='A' and opp_data_for_month ='Jun-2017' limit 1000";*/
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,stagetype,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " from webforecastdev.vw_sales_forecast_summary "+criteria+" and opp_data_for_month = to_char(CURRENT_DATE - INTERVAL '2 months','Mon-YYYY') and to_char(close_date,'Mon-YYYY') = to_char(CURRENT_DATE - INTERVAL '2 months','Mon-YYYY') and lower(forecast_status) = 'submitted' and submit_category ='"+userRole+"'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> SalesForecastsummaryNminus1Report(
			ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,stagetype,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " from webforecastdev.vw_sales_forecast_summary "+criteria+" and opp_data_for_month = to_char(CURRENT_DATE - INTERVAL '1 months','Mon-YYYY') and to_char(close_date,'Mon-YYYY') = to_char(CURRENT_DATE - INTERVAL '1 months','Mon-YYYY') and lower(forecast_status) = 'submitted' and submit_category ='"+userRole+"'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> SalesForecastsummaryCMReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole) {
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,stagetype,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " from webforecastdev.vw_sales_forecast_summary "+criteria+" and opp_data_for_month = to_char(CURRENT_DATE ,'Mon-YYYY') and to_char(close_date,'Mon-YYYY') = to_char(CURRENT_DATE ,'Mon-YYYY') and submit_category ='"+userRole+"'";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> SalesForecastsummaryLobWiseReport(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		/*String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,contract_type,stagename,  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " FROM webforecastdev.opportunity_iec where opp_data_for_month ='Jun-2017' order by lob limit 1000";
		*/
		String queryString = "select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob,"
				+ " stagetype "
				+ " , stagename "
				+ ",  probability,close_date,currency,unfactored_amount,converted_unfactored_amt,factored_amount,converted_factored_amt,gross_margin,sales_person,sales_manager"
				+ " from( select account_name,opp_number,opp_name,region,jci_reporting_country,sub_region,branch_code,lob, webforecastdev.fn_get_stagetype(stagename,rec_status) stagetype,stagename, probability,close_date,currency,unfactored_amount,coalesce(converted_unfactored_amt,0.00) converted_unfactored_amt,factored_amount, coalesce(converted_factored_amt,0.00) converted_factored_amt,gross_margin,sales_person,sales_manager, row_number() over(partition by lob order by coalesce(converted_factored_amt,0.00) desc) as rn FROM webforecastdev.opportunity_iec "
				+ criteria
				+ ""
				+ " and opp_data_for_month ='"
				+ new SimpleDateFormat("MMM-yyyy").format(new Date())
				+ "'"
				+ " and opp_status ='A'  " + " ) as T where T.rn <= 20";

		return em.createNativeQuery(queryString).getResultList();
	}


	public List<Object[]> SubmissionHistoryTrackingReports(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		
		String queryString = " select * from webforecastdev.vw_submission_history_tracking where opp_data_for_month ='"+ new SimpleDateFormat("MMM-yyyy").format(new Date())+"' order by sortorder";

		return em.createNativeQuery(queryString).getResultList();
	}

	public List<Object[]> SubmissionHistoryTrackingReports1(
			ReportGridInputMultipleVO dto, StringBuilder criteria) {
		// TODO Auto-generated method stub
		//String queryString1 = "select * from webforecastdev.vw_submission_history_tracking";
		
		String queryString1 = "select opp_data_for_month,forecast_id,region,modified_by,modified_date   FROM webforecastdev.forecast_region_summary";
		
		return em.createNativeQuery(queryString1).getResultList();
	}

	public List<Object[]> getUserMultiple(String globalId) {
		// TODO Auto-generated method stub
		
		String queryString="select m.region,datasource from webforecastdev.forecast_user_rolemap s, webforecastdev.forecast_user_mdm m where s.globalid ='"+globalId+"' and s.globalid = m.globalid and s.isactive = m.isactive and m.isactive ='A'; ";
		return em.createNativeQuery(queryString).getResultList();
		
	}


}
