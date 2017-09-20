package com.java.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.java.main.dao.AutoSubmissionDao;
import com.java.main.dto.BranchInputVO;
import com.java.main.dto.BranchOutPutDTO;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.LOBOutDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ValueDTO;
import com.java.main.service.BranchService;
import com.java.main.service.ForecasteService;
import com.java.main.service.LoginService;
import com.java.main.service.UserService;

@Controller
public class BranchController {
	
	@Autowired
	BranchService branchService;	
	@Autowired
	ForecasteService forecasteService;
	@Autowired
	LoginService loginService;
	@Autowired
	AutoSubmissionDao autoSubmissionDao;
	
	@RequestMapping(value = "/getBranchDetails", method = RequestMethod.POST)
	@ResponseBody
	public BranchOutPutDTO getBranchDetails(@RequestBody BranchInputVO input,HttpSession session) {
		
		System.out.println("-/Branch Controller  called getBranchDetails request input"+new Gson().toJson(input));
		BranchOutPutDTO response=branchService.getBranchDetails(input, (String)session.getAttribute("user_role"));
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***getBranchDetails response="+new Gson().toJson(response));
		
		
		return response;
	}
	@RequestMapping(value = "/submitForecastOnBranch", method = RequestMethod.POST)
	@ResponseBody
	public BranchOutPutDTO submitForecastOnBranch(@RequestBody BranchInputVO input,HttpSession session) {
		
		System.out.println("-/submitForecastOnBranch controller called  request input"+new Gson().toJson(input));
		/*session.setAttribute("user_role","HQ/Admin Forecaster");
		session.setAttribute("globalId","cyashin");*/
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		String saveGlobalID=(String)session.getAttribute("saveGlobalID");
		System.out.println("userRole "+userRole+" globalId "+globalId);
		//loginService.getRoleByGlobalId(globalId)
		
		branchService.submitForecastOnBranch(input, globalId, userRole);
		
		ForecastInputVO vo=new ForecastInputVO();
		ReportGridInputMultipleVO child=new ReportGridInputMultipleVO();
		/*child.setRegions(input.getRegions());
		child.setCountries(input.getCountries());
		child.setSubregions(input.getSubregions());
		child.setBranches(input.getBranches());
		vo.setFetchJson(child);*/
		for(ValueDTO rdto:input.getRegions()){
			List<ValueDTO> rdtos=new ArrayList<ValueDTO>();
			rdtos.add(rdto);
			child.setRegions(rdtos);
			for(ValueDTO cdto:input.getCountries()){
				List<ValueDTO> cdtos=new ArrayList<ValueDTO>();
				cdtos.add(cdto);
				child.setCountries(cdtos);
				//Branch For Start
				if("Branch Forecaster".equalsIgnoreCase(userRole)){
				for(ValueDTO bdto:input.getBranches()){
					List<ValueDTO> bdtos=new ArrayList<ValueDTO>();
					bdtos.add(bdto);
					child.setBranches(bdtos);
					vo.setFetchJson(child);
					forecasteService.functionCallForSubmitForecast(vo, globalId, userRole,new String());
				}
				}
				else{
					forecasteService.functionCallForSubmitForecast(vo, globalId, userRole,new String());
				}
				//Branch for END
			}
		}
		
		
		
		
		BranchOutPutDTO response=branchService.getBranchDetails(input, globalId);
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***submitForecastOnBranch response="+new Gson().toJson(response));
		return response;
	}
	
	@RequestMapping(value = "/getLobBranchDetails", method = RequestMethod.POST)
	@ResponseBody // dto
	public LOBOutDTO getLobBranchDetails(@RequestBody ReportGridInputMultipleVO input,HttpSession session) {
		
		System.out.println(" getLobBranchDetails controller called  request input"+new Gson().toJson(input));
		String user_role=(String) session.getAttribute("user_role");
		LOBOutDTO response=branchService.getLobBranchDetails(input, new String(), user_role);
		//RegionOutPutDTO response=regionService.getRegionDeatils(input, "ckhan58");
		System.out.println("***getLobBranchDetails response="+new Gson().toJson(response));
			
		return response;
	}
	@RequestMapping(value = "/autoSubmissionBranch", method = RequestMethod.POST)
	@ResponseBody // dto
	public LOBOutDTO autoSubmissionBranch(/*@RequestBody ReportGridInputMultipleVO input,HttpSession session*/) {
		
		System.out.println(" autoSubmissionBranch controller called  ");
		autoSubmissionDao.BranchAutoSubmission(new String());
		System.out.println(" autoSubmissionBranch SUCCESS called  ");
			
		return null;
	}

}
