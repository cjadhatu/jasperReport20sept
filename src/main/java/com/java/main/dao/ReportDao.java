package com.java.main.dao;

import java.util.List;

import com.java.main.dto.FieldhistoryTraRepDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportGridInputVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.SumDetailReportResDTO;
import com.java.main.dto.SummaryReportDTO;

public interface ReportDao {
	public List<String> getRegionList(String parameter);
	public List<String> getSubRegionByRegion(String parameter);
	public List<String>  getcountry(String parameter);
	public List<String>  getbranches(String parameter);
	public List<String> getDataSource();
	public List<Object[]> getGridReportsDetails(ReportGridInputVO input,String criteria);
	//public SummaryReportDTO getsummaryReport(ReportGridInputVO dto);//getcountryByRegion
	public List<Object[]> getsummaryReport(ReportGridInputVO dto,String query);
	public List<String>  getcountryByRegion(String parameter);
	public void testquery();
	public SumDetailReportResDTO getsummeryDetailReport(ReportGridInputMultipleVO dto,StringBuilder criteria);
	public List<Object[]> getDynamicReportData(String globalId);
	public List<FieldhistoryTraRepDTO> getFieldhistoryTrackingreport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> ForecastDetailsCMReport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> ForecastDetailsNminus1Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> ForecastDetailsNPlus1Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> ForecastDetailsNPlus2Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> ForecastDetailsLobWiseReport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	
	public List<Object[]> HQandRegionalforecastSummaryReport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> HQandRegionalNminus1Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> HQandRegionalCMReport(ReportGridInputMultipleVO dto,StringBuilder criteria);
	public List<Object[]> HQandRegionalNPlus1Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> HQandRegionalNPlus2Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> HQandRegionalTop50Report(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public List<Object[]> HQandRegionalLobWiseReport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	
	public List<Object[]> SalesForecastsummaryNMinus2Report(ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole);
	public List<Object[]> SalesForecastsummaryNminus1Report(ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole);
	public List<Object[]> SalesForecastsummaryCMReport(ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole);
	public List<Object[]> SalesForecastsummaryLobWiseReport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	
	public List<Object[]> SubmissionHistoryTrackingReports(ReportGridInputMultipleVO dto, StringBuilder criteria);
	
	public List<Object[]> SubmissionHistoryTrackingReports1(ReportGridInputMultipleVO dto, StringBuilder criteria);
	
	public List<Object[]> getUserMultiple(String globalId);
	
	
}
