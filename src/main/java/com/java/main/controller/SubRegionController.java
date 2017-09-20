package com.java.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SubRegionInputVO;
import com.java.main.dto.SubRegionOutPutVO;
import com.java.main.dto.SubmitFCInputVO;
import com.java.main.service.ForecasteService;
import com.java.main.service.SubRegionService;

@Controller
public class SubRegionController {
	@Autowired
	SubRegionService subRegionService;	
	
	@Autowired
	ForecasteService forecasteService;
	
	@RequestMapping(value = "/getSubRegionDetails", method = RequestMethod.POST)
	@ResponseBody
	public SubRegionOutPutVO getSubRegionDetails(@RequestBody SubRegionInputVO input,HttpSession session) {
		
		System.out.println("-/getSubRegionDetails controller called  request input"+new Gson().toJson(input));
		RegionOutPutDTO response=subRegionService.getSubRegionDeatils(input, (String)session.getAttribute("user_role"));
		//RegionOutPutDTO response=subRegionService.getSubRegionDeatils(input, "ckhan58");
		SubRegionOutPutVO finalResponse=new SubRegionOutPutVO();
		finalResponse.setSubRegionData(response.getRegionData());
		finalResponse.setTotalBranches(response.getTotalBranches());
		finalResponse.setTotalCountries(response.getTotalCountries());
		finalResponse.setTotalfactAmt(response.getTotalfactAmt());
		finalResponse.setTotalOpportunites(response.getTotalOpportunites());
		finalResponse.setTotalUnfactAmt(response.getTotalUnfactAmt());
		finalResponse.setDynamicSummaryDTO(response.getDynamicSummaryDTO());
		finalResponse.setIsSubmitClickable(response.getIsSubmitClickable());
		//System.out.println("***getSubRegionDetails response="+new Gson().toJson(finalResponse));
		return finalResponse;
	}

	@RequestMapping(value = "/submitForecastOnSubRegion", method = RequestMethod.POST)
	@ResponseBody
	public RegionOutPutDTO submitForecastOnSubRegion(@RequestBody SubRegionInputVO input,HttpSession session) {
		
		System.out.println("-/submitForecastOnSubRegion controller called  request input"+new Gson().toJson(input));
		RegionOutPutDTO response=new RegionOutPutDTO();
		/*session.setAttribute("user_role","HQ/Admin Forecaster");
		session.setAttribute("globalId","cyashin");*/
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		SubmitFCInputVO submitFCInputVO=new SubmitFCInputVO();
		submitFCInputVO.setRegions(input.getRegions());
		submitFCInputVO.setCountries(input.getCountries());
		if(input.getSubRegions()!=null && (input.getSubRegions().get(0).getName()!=null))
		{
		submitFCInputVO.setSubregions(input.getSubRegions());
		}
		if("Y".equalsIgnoreCase(forecasteService.isSubmittedForSubRegionFC(submitFCInputVO)))
		{
		subRegionService.submitForecastOnSubRegion(input, globalId, userRole);
		
		ForecastInputVO vo=new ForecastInputVO();
		ReportGridInputMultipleVO child=new ReportGridInputMultipleVO();
		child.setRegions(input.getRegions());
		child.setCountries(input.getCountries());
		child.setSubregions(input.getSubRegions());
		vo.setFetchJson(child);
		forecasteService.functionCallForSubmitForecast(vo, globalId, userRole,new String());
		
		
		 response=subRegionService.getSubRegionDeatils(input, globalId);
		}
		else
		{
		 //RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
			//response=subRegionService.getSubRegionDeatils(input, globalId);
			response.setSubmitAllowed(false);
		
	
		}
		System.out.println("***submitForecastOnSubRegion response="+new Gson().toJson(response));
		return response;
	}
	@RequestMapping(value = "/getLobSubRegionDetails", method = RequestMethod.POST)
	@ResponseBody // dto
	public LOBOutDTO getLobSubRegionDetails(@RequestBody ReportGridInputMultipleVO input,HttpSession session) {
		
		System.out.println(" getLobSubRegionDetails controller called getLobRegionDetails request input"+new Gson().toJson(input));
		String user_role=(String) session.getAttribute("user_role");
		LOBOutDTO response=subRegionService.getLobSubRegionDetails(input, new String(), user_role);
		
		System.out.println("***getLobSubRegionDetails response="+new Gson().toJson(response));
			
		return response;
	}
	
}
