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
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.RegionOutPutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SubmitFCInputVO;
import com.java.main.service.ForecasteService;
import com.java.main.service.RegionService;

@Controller
public class RegionController {
	@Autowired
	RegionService regionService;	
	@Autowired
	ForecasteService forecasteService;	
	
	
	
	@RequestMapping(value = "/getRegionDetails", method = RequestMethod.POST)
	@ResponseBody
	public RegionOutPutDTO getRegions(@RequestBody RegionInputDTO input,HttpSession session) {
		
		//System.out.println("-/region controller called getRegions request input"+new Gson().toJson(input));
		String user_role=(String) session.getAttribute("user_role");
		RegionOutPutDTO response=regionService.getRegionDeatils(input, (String)session.getAttribute("user_role"),user_role);
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		//System.out.println("***getRegions response="+new Gson().toJson(response));
		return response;
	}
	
	@RequestMapping(value = "/submitForecastOnRegion", method = RequestMethod.POST)
	@ResponseBody
	public RegionOutPutDTO submitForecastOnRegion(@RequestBody RegionInputDTO input,HttpSession session) {
		
		System.out.println("-/submitForecastOnRegion controller called  request input"+new Gson().toJson(input));
		RegionOutPutDTO response=new RegionOutPutDTO();
		/*session.setAttribute("user_role","Regional Forecaster");
		session.setAttribute("globalId","cyashin");*/
		String user_role=(String) session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		SubmitFCInputVO submitFCInputVO=new SubmitFCInputVO();
		submitFCInputVO.setRegions(input.getRegions());
		if("Y".equalsIgnoreCase(forecasteService.isSubmittedForRegionalFC(submitFCInputVO)))
		{
		regionService.submitForecastOnRegion(input, (String)session.getAttribute("globalId"),user_role);
		ForecastInputVO vo=new ForecastInputVO();
		ReportGridInputMultipleVO child=new ReportGridInputMultipleVO();
		child.setRegions(input.getRegions());
		vo.setFetchJson(child);
		forecasteService.functionCallForSubmitForecast(vo, globalId, user_role,new String());
		 response=regionService.getRegionDeatils(input, globalId, user_role);
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		}
		else
		{
			//response=regionService.getRegionDeatils(input, globalId, user_role);
			response.setSubmitAllowed(false);
		}
		 
		 System.out.println("***getRegions response="+new Gson().toJson(response));
		return response;
	}
	
	/*@RequestMapping(value = "/getLobRegionDetails", method = RequestMethod.POST)
	@ResponseBody //ReportGridInputMultipleVO dto
	public RegionOutPutDTO getLobRegionDetails(@RequestBody RegionInputDTO input,HttpSession session) {
		
		System.out.println("-/Lob region controller called getLobRegionDetails request input"+new Gson().toJson(input));
		String user_role=(String) session.getAttribute("user_role");
		RegionOutPutDTO response=regionService.getLobRegionDetails(input, (String)session.getAttribute("globalId"),user_role);
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***getLobRegionDetails response="+new Gson().toJson(response));
		
		RegionOutPutDTO dto=new RegionOutPutDTO();
		
		List<RegionDTO> regionDatalist=new ArrayList<RegionDTO>();
		RegionDTO rdto=new RegionDTO();
		rdto.setBranch("abc");
		rdto.setCountry("xyz");
		rdto.setRegion("jjj");
		rdto.setForecastStatus("inp");
		rdto.setFactoredAmt("1111");
		rdto.setUnfactoredAmt("8888");
		rdto.setRolling90DaysFactamt("00078");
		rdto.setRolling90DaysUnfactamt("1114456");
		regionDatalist.add(rdto);
		
		DynamicSummaryDTO dynamicSummaryDTO=new DynamicSummaryDTO();
		dynamicSummaryDTO.setLob("hhhh");
		dynamicSummaryDTO.setTotalfactoredVal("8768909");
		dynamicSummaryDTO.setTotalunfactoredVal("9090");
		dynamicSummaryDTO.setRolling90DaysFactamt("99999076");
		dynamicSummaryDTO.setRolling90DaysUnfactamt("77754");
		
		dto.setDynamicSummaryDTO(dynamicSummaryDTO);
		dto.setRegionData(regionDatalist);
		
		return dto;
	}*/
	
	/*@RequestMapping(value = "/getRegionSummaryDetails", method = RequestMethod.POST)
	@ResponseBody
	public DynamicSummaryDTO getRegionSummaryDetails(@RequestBody RegionInputDTO input,HttpSession session) {
		
		System.out.println("-/region SummaryDetails controller called getRegionSummaryDetails request input");
	 
			 
		String user_role=(String) session.getAttribute("user_role");
		DynamicSummaryDTO response=regionService.getRegionSummaryDetails(input,user_role);
		
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***getRegionSummaryDetails response=");
		return response;
	}*/
	@RequestMapping(value = "/getLobRegionDetails", method = RequestMethod.POST)
	@ResponseBody // dto
	public LOBOutDTO getLobRegionDetails(@RequestBody ReportGridInputMultipleVO input,HttpSession session) {
		
		System.out.println("-/Lob region controller called getLobRegionDetails request input"+new Gson().toJson(input));
		//String user_role=(String) session.getAttribute("user_role");
		LOBOutDTO response=regionService.getLobRegionDetails(input, new String(),new String());
		System.out.println("-/Lob region controller Response "+new Gson().toJson(response));
		return response;
	}
}
