package com.java.main.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.java.main.dto.CountryInputVO;
import com.java.main.dto.CountryOutPutVO;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.SubmitFCInputVO;
import com.java.main.service.CountryService;
import com.java.main.service.ForecasteService;

@Controller
public class CountryController {
	@Autowired
	CountryService countryService;	
	@Autowired
	ForecasteService forecasteService;
	
	
	@RequestMapping(value = "/getCountryDetails", method = RequestMethod.POST)
	@ResponseBody
	public CountryOutPutVO getCountry(@RequestBody CountryInputVO input,HttpSession session) {
		
		System.out.println("-/getCountryDetails controller called  request input"+new Gson().toJson(input));
		CountryOutPutVO response=countryService.getCountryDeatils(input, (String)session.getAttribute("user_role"));
		//RegionOutPutDTO response=countryService.getCountryDeatils(input, "ckhan58");
		//System.out.println("***getCountryDetails response="+new Gson().toJson(response));
		return response;
			
		
		
	
	}
	@RequestMapping(value = "/submitForecastOnCountry", method = RequestMethod.POST)
	@ResponseBody
	public CountryOutPutVO submitForecastOnCountry(@RequestBody CountryInputVO input,HttpSession session) {
		
		System.out.println("-/submitForecastOnCountry controller called  request input"+new Gson().toJson(input));
		
		CountryOutPutVO response=new CountryOutPutVO();
		/*session.setAttribute("user_role","HQ/Admin Forecaster");
		session.setAttribute("globalId","cyashin");*/
		String user_role=(String) session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		SubmitFCInputVO submitFCInputVO=new SubmitFCInputVO();
		submitFCInputVO.setCountries(input.getCountries());
		submitFCInputVO.setRegions(input.getRegions());
		if("Y".equalsIgnoreCase(forecasteService.isSubmittedForCountryFC(submitFCInputVO)))
		{
		countryService.submitForecastOnCountry(input, (String)session.getAttribute("globalId"),user_role);
		ForecastInputVO vo=new ForecastInputVO();
		ReportGridInputMultipleVO child=new ReportGridInputMultipleVO();
		child.setRegions(input.getRegions());
		child.setCountries(input.getCountries());
		vo.setFetchJson(child);
		forecasteService.functionCallForSubmitForecast(vo, globalId, user_role,new String());
		
		 response=countryService.getCountryDeatils(input, globalId);
		}
		else{
			//response=countryService.getCountryDeatils(input, globalId);
			
			response.setSubmitAllowed(false);
		}
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***submitForecastOnCountry response="+new Gson().toJson(response));
		return response;
	}
	@RequestMapping(value = "/getLobCountryDetails", method = RequestMethod.POST)
	@ResponseBody // dto
	public LOBOutDTO getLobRegionDetails(@RequestBody ReportGridInputMultipleVO input,HttpSession session) {
		
		System.out.println("-/Lob region controller called getLobRegionDetails request input"+new Gson().toJson(input));
		String user_role=(String) session.getAttribute("user_role");
		LOBOutDTO response=countryService.getLobCountryDetails(input, new String(),new String());
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***getLobRegionDetails response="+new Gson().toJson(response));
			
		return response;
	}

}
