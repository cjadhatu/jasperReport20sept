package com.java.main.service;

import java.util.List;











import com.java.main.dto.DropDownVO;
import com.java.main.dto.DynamicReportDataDTO;
import com.java.main.dto.FieldhistoryTraRepDTO;
import com.java.main.dto.ForecastDetailsReportDTO;
import com.java.main.dto.HQandRegionalReportsDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.ReportOutPutVO;
import com.java.main.dto.SubmissionHistoryTraReportDTO;
import com.java.main.dto.SumDetailReportResDTO;
import com.java.main.dto.SummaryReportDTO;


public interface ReportService {
	public List<DropDownVO> getRegionList(ReportInputMultipleVO input);
	public List<DropDownVO> getSubRegionbyRegion(ReportInputMultipleVO region);
	public List<DropDownVO> getcountry(ReportInputMultipleVO reportInputVO);
	public List<DropDownVO> getBranches(ReportInputMultipleVO reportInputVO);
	public SummaryReportDTO getsummaryReport(ReportGridInputMultipleVO dto);
	public ReportOutPutVO getGridReportsDetails(ReportGridInputMultipleVO input);
	public List<DropDownVO> getDataSource();
	public List<DropDownVO> getcountryByRegion(ReportInputMultipleVO reportInputVO);
	//getcountryByRegion
	public StringBuilder getReportsDetails(ReportGridInputMultipleVO input);
	public void testquery();
	public SumDetailReportResDTO getsummeryDetailReport(ReportGridInputMultipleVO dto,StringBuilder criteria);
	public DynamicReportDataDTO getDynamicReportData(String globalId, String userRole);
	public List<FieldhistoryTraRepDTO> getFieldhistoryTrackingreport(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public ForecastDetailsReportDTO getForecastDetailsreports(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public HQandRegionalReportsDTO getHQandRegionalReports(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public ForecastDetailsReportDTO getSalesForecastsummaryreport(ReportGridInputMultipleVO dto, StringBuilder criteria,String userRole);
	public List<SubmissionHistoryTraReportDTO> SubmissionHistoryTrackingReports(ReportGridInputMultipleVO dto, StringBuilder criteria);
	public StringBuilder getHQRigionReportsDetails(ReportGridInputMultipleVO input);
	
	
	
}
