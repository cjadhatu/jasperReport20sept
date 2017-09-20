package com.java.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.DropDownVO;
import com.java.main.dto.DynamicReportDataDTO;
import com.java.main.dto.FieldhistoryTraRepDTO;
import com.java.main.dto.ForecastDetailsReportDTO;
import com.java.main.dto.HQandRegionalReportsDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportGridInputVO;
import com.java.main.dto.ReportGridVO;
import com.java.main.dto.ReportInputMultipleVO;
import com.java.main.dto.ReportInputVO;
import com.java.main.dto.ReportOnLoadDTO;
import com.java.main.dto.ReportOutPutVO;
import com.java.main.dto.SubmissionHistoryTraReportDTO;
import com.java.main.dto.SumDetailReportResDTO;
import com.java.main.dto.SummaryGridDTO;
import com.java.main.dto.SummaryReportDTO;
import com.java.main.dto.ValueDTO;
import com.java.main.service.LoginService;
import com.java.main.service.ReportService;
import com.java.main.utility.GETJDBCPostGres;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Controller
public class ReportController {

	@Autowired
	LoginService loginService;

	@Autowired
	ReportService reportService;

	@RequestMapping(value = { "/getRegions" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getRegions(
			@RequestBody final ReportInputMultipleVO vo, HttpSession session) {
		ReportInputVO dto = new ReportInputVO();
		System.out.println("getRegions controller called");
		List<DropDownVO> value = reportService.getRegionList(vo);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		System.out.println("getRegions controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getSubRegions" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getSubRegionsByRegion(
			@RequestBody final ReportInputMultipleVO dto, HttpSession session) {

		List<DropDownVO> value = reportService.getSubRegionbyRegion(dto);
		List<DropDownVO> countriesChina=reportService.getcountry(dto);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		response.setCountriesChina(countriesChina);
		System.out.println("getSubRegions controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getcountry" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getcountry(
			@RequestBody final ReportInputMultipleVO dto, HttpSession session) {

		List<DropDownVO> value = reportService.getcountry(dto);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		System.out.println("getcountry controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getcountryByRegion" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getcountryByRegion(
			@RequestBody final ReportInputMultipleVO dto, HttpSession session) {

		List<DropDownVO> value = reportService.getcountryByRegion(dto);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		System.out.println("getcountryByRegion controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getbranches" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getbranches(
			@RequestBody final ReportInputMultipleVO dto, HttpSession session) {
		System.out.println("getbranches controller request "
				+ new Gson().toJson(dto));
		List<DropDownVO> value = reportService.getBranches(dto);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		System.out.println("getbranches controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getReportGrid" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOutPutVO getReportGrid(
			@RequestBody final ReportGridInputMultipleVO dto,
			HttpSession session) {
		System.out.println("getReportGrid controller called input "+new Gson().toJson(dto));
		// dto.setRegion("ANZ");
		// dto.setCountry("New Zealand");
		ReportOutPutVO response = new ReportOutPutVO();
		response = reportService.getGridReportsDetails(dto);
		// System.out.println("getReportGrid controller reponse "+new
		// Gson().toJson(response));
		return response;
	}

	/*
	 * @RequestMapping(value = { "/getsummaryReport" }, method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public SummaryGridDTO getsummaryReport(@RequestBody final
	 * ReportGridInputVO dto, HttpSession session) {
	 * 
	 * SummaryReportDTO value =reportService.getsummaryReport(dto);
	 * SummaryGridDTO response = new SummaryGridDTO();
	 * response.setSummaryReportDTO(value);
	 * System.out.println("getsummaryReport controller reponse "+new
	 * Gson().toJson(response)); return response; }
	 */

	@RequestMapping(value = { "/getsummaryReport" }, method = RequestMethod.POST)
	@ResponseBody
	public SummaryGridDTO getsummaryReport(
			@RequestBody final ReportGridInputMultipleVO dto,
			HttpSession session) {
		System.out.println("getsummaryReport controller input "+ new Gson().toJson(dto));
		SummaryReportDTO value = reportService.getsummaryReport(dto);
		SummaryGridDTO response = new SummaryGridDTO();
		response.setSummaryReportDTO(value);
		System.out.println("getsummaryReport controller reponse "+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(value = { "/getDataSource" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getDataSource(/* @RequestBody final ReportInputVO dto, */
	HttpSession session) {
		System.out
				.println("getDataSource Service dev START time " + new Date());
		List<DropDownVO> value = reportService.getDataSource();
		System.out.println("getDataSource Service END time " + new Date());
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);

		System.out.println("getDataSource controller reponse "
				+ new Gson().toJson(response));
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/generateDetailsXlsReport")
	@ResponseBody
	public void generateDetailsXlsReport(
			@RequestBody final ReportGridInputMultipleVO dto,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {

		StringBuilder criteria = reportService.getReportsDetails(dto);
		String queryString = " select region,sub_region,jci_reporting_country,branch_code,opp_name, "
				+ " lead_source,unfactored_amount,account_name,close_date,stagename,probability,gross_margin,factored_amount, "
				+ " sales_person,sales_manager,opp_data_source,converted_factored_amt,converted_unfactored_amt from webforecastdev.opportunity_iec "
				+ criteria.toString();

		String sumeryquerString = "select count(distinct region) region_count,count(distinct sub_region) Subregion_count,"
				+ "count(distinct jci_reporting_country) country, count(distinct branch_code) branch_count, sum(count) opp_count,"
				+ "sum(factored_amount) total_factored_amount,sum(unfactored_amount) total_unfactored_amount,"
				+ "sum(converted_factored_amt) total_factored_amount_USD,sum(converted_unfactored_amt) total_unfactored_amount_USD "
				+ "from webforecastdev.rep1_summary " + criteria.toString();
		System.out.println("-----------------queryString" + queryString);

		Connection con = GETJDBCPostGres.getConnection();
		String jrxmlName = "DetailSummaryReport.jrxml";
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(jrxmlName);
		System.out.println("***** is loaded " + is.getClass());
		JasperDesign jd = null;
		JasperReport jr = null;
		Map<String, Object> map = null;

		JasperPrint jp = null;
		JRXlsxExporter exporterXlsx = new JRXlsxExporter();

		try {
			jd = JRXmlLoader.load(is);
			jr = JasperCompileManager.compileReport(jd);
			map = new HashMap<String, Object>();
			map.put("detailsqlqueryString", queryString);
			map.put("subquerysqlString", sumeryquerString);
			jp = JasperFillManager.fillReport(jr, map, con);
			exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT, jp);
			// JasperExportManager
			String reportName = "Opportunities Detail 04-20-2017";
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			/*
			 * response.addHeader("Content-disposition",
			 * "attachment; filename=StatisticsrReport1.xlsx");
			 */response.addHeader("Content-disposition",
					"attachment; filename=" + reportName + ".xlsx");

			OutputStream out = response.getOutputStream();

			exporterXlsx
					.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
			exporterXlsx.exportReport();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(method = RequestMethod.POST, value = "/getsummeryDetailReport")
	@ResponseBody
	public void getsummeryDetailReport(
			@RequestBody final ReportGridInputMultipleVO dto,
			HttpSession session, HttpServletRequest request,
			HttpServletResponse response) {
		String reportName = "Opportunities Detail";
		StringBuilder criteria = reportService.getReportsDetails(dto);
		SumDetailReportResDTO reportReponse = reportService
				.getsummeryDetailReport(dto, criteria);

		String[] cols = { "region", "sub_region", "jci_reporting_country",
				"branch_code", "opp_name", "lead_source", "unfactored_amount",
				"account_name", "close_date", "stagename", "probability",
				"gross_margin", "factored_amount", "sales_person",
				"sales_manager", "opp_data_source", "converted_factored_amt",
				"converted_unfactored_amt" };
		String[] summarycols = { "region_count", "subregion_count", "country",
				"branch_count", "opp_count", "total_factored_amount",
				"total_unfactored_amount", "total_factored_amount_usd",
				"total_unfactored_amount_usd" };
		JRXlsxExporter exporterXlsx = new JRXlsxExporter();

		try {
			// ListOfArrayDataSource arrayDataSource=new
			// ListOfArrayDataSource(val, cols);
			/*
			 * SummeryReportDTO jasperReportBean = new SummeryReportDTO();
			 * jasperReportBean.setColNames(cols);
			 * jasperReportBean.setSubReportBeanList(val);
			 */
			String jrxmlName = "DetailSummaryReport.jrxml";
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = (JasperReport) JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();

			Map dataMap = new HashMap<String, Object[]>();
			dataMap.put("subReportBeanList", reportReponse.getDetailList());
			dataMap.put("colNames", cols);
			dataMap.put("summarySubReportBeanList",
					reportReponse.getSummaryList());
			dataMap.put("summarycolNames", summarycols);
			Collection<Map<String, ?>> coll = new ArrayList<Map<String, ?>>();
			coll.add(dataMap);

			JRMapCollectionDataSource jrMapColDataSource = new JRMapCollectionDataSource(
					coll);

			// parameters.put ("subReportBeanList",);

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, jrMapColDataSource);
			exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);

			// JasperViewer.viewReport(jasperPrint);
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-disposition", "attachment; filename="
					+ reportName + ".xlsx");
			OutputStream out = response.getOutputStream();

			exporterXlsx
					.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
			exporterXlsx.exportReport();

			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/getDynamicReportData", method = RequestMethod.POST)
	@ResponseBody
	public DynamicReportDataDTO getDynamicReportData(HttpSession session) {
	String userRole=(String)session.getAttribute("user_role");
    String globalId=(String)session.getAttribute("saveGlobalID");
// String userRole="Branch Forecaster";
   // String globalId="cwufu";
    //String userRole="Sub Region Forecaster";
    //String globalId="ckalvis";//ckalvis //cyashin
    //HQ/Admin Forecaster cyashin,
    System.out.println("/getDynamicReportData controller called username is "+globalId+"userRole"+userRole);
    
    
    DynamicReportDataDTO response=reportService.getDynamicReportData(globalId,userRole);
/*if("gpark".equalsIgnoreCase(globalId))
   {
    	
    }
*/    
    System.out.println("getDynamicReportData controller response "+new Gson().toJson(response));
    
    return response;
}

	@RequestMapping(method = RequestMethod.POST, value = "/getFieldhistoryTrackingreport")
	@ResponseBody
	public void getFieldhistoryTrackingreport(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session, HttpServletRequest request,
			HttpServletResponse response)
	{
		// ReportGridInputMultipleVO dto=new ReportGridInputMultipleVO();
		String reportName = "Field history Tracking Report";
		StringBuilder criteria = reportService.getReportsDetails(dto);
		List<FieldhistoryTraRepDTO> reportReponseList=reportService.getFieldhistoryTrackingreport(dto,criteria);
		System.out.println("----------reportReponse-"+reportReponseList.size());
         JRXlsxExporter exporterXlsx = new JRXlsxExporter();
        
 		//exporterXlsx.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetName); 
		
		try{
			
			String jrxmlName = "FieldhistoryTrackingreport.jrxml";
			
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			
		JasperDesign jasperDesign = JRXmlLoader.load(is);
        JasperReport jasperReport =(JasperReport)JasperCompileManager.compileReport(jasperDesign);
     //   Map<String,Object> parameters = new HashMap<String,Object>();
        
        JRBeanCollectionDataSource beanColDataSource = new 
                JRBeanCollectionDataSource(reportReponseList);
             Map parameters = new HashMap();
             /*try {
                JasperFillManager.fillReportToFile( 
                   sourceFileName, parameters, beanColDataSource);
             } catch (JRException e) {
                e.printStackTrace();
             }*/
        
     

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
        exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);

        //JasperViewer.viewReport(jasperPrint);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.addHeader("Content-disposition",
				"attachment; filename=" + reportName + ".xlsx");
		OutputStream out = response.getOutputStream();

		exporterXlsx
				.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
		exporterXlsx.exportReport();
		
		out.close();
		response.flushBuffer();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
		
	}

	
	
	@RequestMapping(method = RequestMethod.POST, value = "/getForecastDetailsreports")
	@ResponseBody
	public void getForecastDetailsreports(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session, HttpServletRequest request,
			HttpServletResponse response)
	{
		// ReportGridInputMultipleVO dto=new ReportGridInputMultipleVO();
		String reportName = "Forecast Details Report";
		StringBuilder criteria = reportService.getReportsDetails(dto);
		ForecastDetailsReportDTO reportReponseList=reportService.getForecastDetailsreports(dto,criteria);
		
		System.out.println("----------reportReponsen-1-"+reportReponseList.getForecastDetailsNminus1List().size());
		System.out.println("----------reportReponse-"+reportReponseList.getForecastDetailsReportCMList().size());
		System.out.println("----------reportReponsen+1"+reportReponseList.getForecastDetailsNPluse1List().size());
		System.out.println("----------reportReponsen+2"+reportReponseList.getForecastDetailsNPluse2List().size());
		System.out.println("----------lobwise"+reportReponseList.getForecastDetailsLobWiseList().size());
		JRXlsxExporter exporterXlsx = new JRXlsxExporter();
		/*exporterXlsx.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);		
	    exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
*/
		
		
		    exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
			exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
			exporterXlsx.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		try {
		
			String jrxmlName = "ForecastDetailsreports.jrxml";
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = (JasperReport) JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();

			Map dataMap = new HashMap<String, Object[]>();	
		   
		     dataMap.put("forecastDetailsReportNminus1List",reportReponseList.getForecastDetailsNminus1List());
		     dataMap.put("forecastDetailsReportCMList",reportReponseList.getForecastDetailsReportCMList());
		     dataMap.put("forecastDetailsReportNplus1List",reportReponseList.getForecastDetailsNPluse1List());
		     dataMap.put("forecastDetailsReportNplus2List",reportReponseList.getForecastDetailsNPluse2List());
		     dataMap.put("forecastDetailsReportLobWiseList",reportReponseList.getForecastDetailsLobWiseList());
		     
			Collection<Map<String, ?>> coll = new ArrayList<Map<String, ?>>();
			coll.add(dataMap);

			JRMapCollectionDataSource jrMapColDataSource = new JRMapCollectionDataSource(
					coll);

			

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, jrMapColDataSource);
			exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-disposition", "attachment; filename="
					+ reportName + ".xlsx");
			OutputStream out = response.getOutputStream();

			exporterXlsx
					.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
			exporterXlsx.exportReport();

			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	

	@RequestMapping(method = RequestMethod.POST, value = "/hqandRegionalReports")
	@ResponseBody
	public void getHQandRegionalReports(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session, HttpServletRequest request,
			HttpServletResponse response)
	{
		// ReportGridInputMultipleVO dto=new ReportGridInputMultipleVO();
		String reportName = "HQ and Regional Reports";
		StringBuilder criteria = reportService.getHQRigionReportsDetails(dto);
		HQandRegionalReportsDTO reportReponseList=reportService.getHQandRegionalReports(dto,criteria);
		
		/*System.out.println("----------reportReponsen-1-"+reportReponseList.getForecastDetailsNminus1List().size());
		System.out.println("----------reportReponse-"+reportReponseList.getForecastDetailsReportCMList().size());
		System.out.println("----------reportReponsen+1"+reportReponseList.getForecastDetailsNPluse1List().size());
		System.out.println("----------reportReponsen+2"+reportReponseList.getForecastDetailsNPluse2List().size());
		System.out.println("----------lobwise"+reportReponseList.getForecastDetailsLobWiseList().size());*/
		JRXlsxExporter exporterXlsx = new JRXlsxExporter();
		
		String[] sheetName = new String[7];
		sheetName[0] = "HQ Region Forecast Summary";
		sheetName[1] = "N-1(MM YYYY)";
		sheetName[2] = "N(MM YYYY)";
		sheetName[3] = "N+1(MM YYYY)";
		sheetName[4] = "N+2(MM YYYY)";
		sheetName[5] = "Top 50 Opportunities";
		sheetName[6] = "Opps-LOB breakdown";
		//exporterXlsx.setProperty("net.sf.jasperreports.export.xls.create.custom.palette", "false");
		//exporterXlsx.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		//exporterXlsx.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); 
		//exporterXlsx.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		/*exporterXlsx.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);	*/	
	    exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetName); 
		
		
		try {
		
			String jrxmlName = "HQ and Regional Reports.jrxml";
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = (JasperReport) JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();

			Map dataMap = new HashMap<String, Object[]>();	
		   
		     dataMap.put("hqRegionForecastSummaryList",reportReponseList.getHqRegionForecastSummaryList());
		     dataMap.put("hqRegionReportNminus1List",reportReponseList.getHqRegionForecastNminus1List());
		     dataMap.put("hqRegionReportCMList",reportReponseList.getHqRegionForecastCMList());
		     dataMap.put("hqRegionReportNpluse1List",reportReponseList.getHqRegionForecastNpluse1List());
		     dataMap.put("hqRegionReportNpluse2List",reportReponseList.getHqRegionForecastNpluse2List());
		     dataMap.put("hqRegionReportTop50",reportReponseList.getHqRegionForecastTop50List());
		     dataMap.put("hqRegionReportLobWiseList",reportReponseList.getHqRegionForecastTop20List());
		     
		     
		     
			Collection<Map<String, ?>> coll = new ArrayList<Map<String, ?>>();
			coll.add(dataMap);

			JRMapCollectionDataSource jrMapColDataSource = new JRMapCollectionDataSource(
					coll);

			

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, jrMapColDataSource);
			exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-disposition", "attachment; filename="
					+ reportName + ".xlsx");
			OutputStream out = response.getOutputStream();

			exporterXlsx
					.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
			exporterXlsx.exportReport();

			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	


	@RequestMapping(method = RequestMethod.POST, value = "/getSalesForecastsummaryreport")
	@ResponseBody
	public void getSalesForecastsummaryreport(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session, HttpServletRequest request,
			HttpServletResponse response)
	{
		// ReportGridInputMultipleVO dto=new ReportGridInputMultipleVO();
		
		String userRole=(String)session.getAttribute("user_role");
		
		
		String reportName = "Sales Forecast summary report";
		StringBuilder criteria = reportService.getReportsDetails(dto);
		ForecastDetailsReportDTO reportReponseList=reportService.getSalesForecastsummaryreport(dto,criteria,userRole);
		
		System.out.println("----------reportReponsen-1-"+reportReponseList.getForecastDetailsNminus1List().size());
		System.out.println("----------reportReponse-"+reportReponseList.getForecastDetailsReportCMList().size());
		System.out.println("----------reportReponsen+1"+reportReponseList.getForecastDetailsNPluse1List().size());
		System.out.println("----------reportReponsen+2"+reportReponseList.getForecastDetailsNPluse2List().size());
		System.out.println("----------lobwise"+reportReponseList.getForecastDetailsLobWiseList().size());
		JRXlsxExporter exporterXlsx = new JRXlsxExporter();
		/*exporterXlsx.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);		
	    exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		
*/
		String[] sheetName = new String[4];
		sheetName[0] = "N-2(MM YYYY)";
		sheetName[1] = "N-1(MM YYYY)";
		sheetName[2] = "N(MM YYYY)";
		sheetName[3] = "Top  opportunities LOB wise";
	/*	exporterXlsx.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);	*/	
	    exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.TRUE); 
		exporterXlsx.setParameter(JRXlsExporterParameter.SHEET_NAMES, sheetName); 
		
		
		try {
		
			String jrxmlName = "SalesForecastSummaryReport.jrxml";
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			JasperDesign jasperDesign = JRXmlLoader.load(is);
			JasperReport jasperReport = (JasperReport) JasperCompileManager
					.compileReport(jasperDesign);
			Map<String, Object> parameters = new HashMap<String, Object>();

			Map dataMap = new HashMap<String, Object[]>();	
		   
		     dataMap.put("SalesForecastSummaryReportNminus2List",reportReponseList.getForecastDetailsNPluse2List());
		     dataMap.put("SalesForecastSummaryReportNminus1List",reportReponseList.getForecastDetailsNminus1List());
		     dataMap.put("SalesForecastSummaryReportCMList",reportReponseList.getForecastDetailsReportCMList());
		    // dataMap.put("forecastDetailsReportNplus2List",reportReponseList.getForecastDetailsNPluse2List());
		     dataMap.put("SalesForecastSummaryReportLobWiseList",reportReponseList.getForecastDetailsLobWiseList());
		     
		     
		     
			Collection<Map<String, ?>> coll = new ArrayList<Map<String, ?>>();
			coll.add(dataMap);

			JRMapCollectionDataSource jrMapColDataSource = new JRMapCollectionDataSource(
					coll);

			

			JasperPrint jasperPrint = JasperFillManager.fillReport(
					jasperReport, parameters, jrMapColDataSource);
			exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT,
					jasperPrint);

			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("Content-disposition", "attachment; filename="
					+ reportName + ".xlsx");
			OutputStream out = response.getOutputStream();

			exporterXlsx
					.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
			exporterXlsx.exportReport();

			out.close();
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	@RequestMapping(method = RequestMethod.POST, value = "/submissionHistoryTrackingReports")
	@ResponseBody
	public void SubmissionHistoryTrackingReports(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session, HttpServletRequest request,
			HttpServletResponse response)
	{
		
		String reportName = "Submission History Tracking Report";
		StringBuilder criteria = reportService.getReportsDetails(dto);
		List<SubmissionHistoryTraReportDTO> reportReponseList=reportService.SubmissionHistoryTrackingReports(dto,criteria);
		System.out.println("----------reportReponse-"+reportReponseList.size());
         JRXlsxExporter exporterXlsx = new JRXlsxExporter();
        	
		try{
			
			String jrxmlName = "SubmissionHistoryTracking.jrxml";
			
			InputStream is = this.getClass().getClassLoader()
					.getResourceAsStream(jrxmlName);
			
		JasperDesign jasperDesign = JRXmlLoader.load(is);
        JasperReport jasperReport =(JasperReport)JasperCompileManager.compileReport(jasperDesign);
    
        
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(reportReponseList);
             Map parameters = new HashMap();
             
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,beanColDataSource);
        exporterXlsx.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);

       
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.addHeader("Content-disposition",
				"attachment; filename=" + reportName + ".xlsx");
		OutputStream out = response.getOutputStream();

		exporterXlsx
				.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, out);
		exporterXlsx.exportReport();
		
		out.close();
		response.flushBuffer();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
		
	}


	
}
