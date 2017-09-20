package com.java.main.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.java.main.dto.AccountNameInputVO;
import com.java.main.dto.AddNewOpportunity;
import com.java.main.dto.ConvertedAmountDTO;
import com.java.main.dto.DropDownVO;
import com.java.main.dto.ForecastInputVO;
import com.java.main.dto.ForecastLockDTO;
import com.java.main.dto.ForecastOutVO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportOnLoadDTO;
import com.java.main.dto.SaveOpportunityDTO;
import com.java.main.dto.SaveOpportunityInputVO;
import com.java.main.dto.SubmitFCInputVO;
import com.java.main.service.ForecasteService;
import com.java.main.service.UserService;
import com.java.main.utility.DateWindowUtil;



@Controller
public class ForecastController {
	
	@Autowired
	ForecasteService forecasteService;
	
	@Autowired
	UserService userService; 
	
	@RequestMapping(value = "/getOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO adminLogin(/*@RequestBody LoginBean loginBean*/) {
		System.out.println("/getOpportunity controller called username is ");
		ForecastOutVO response=forecasteService.getopportunities(new String());
		System.out.println("getOpportunity controller response "+new Gson().toJson(response));	
		return response;

	}
	
	@RequestMapping(value = "/updateOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO updateOpportunity(@RequestBody ForecastInputVO input,HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		System.out.println("/updateOpportunity controller called input is "+new Gson().toJson(input));
		ForecastOutVO response=forecasteService.updateOpportunity(input,globalId,userRole);
		/*ReportGridInputMultipleVO fetchInput=new ReportGridInputMultipleVO();
		fetchInput.setBranches(input.getFetchJson().getBranches());
		fetchInput.setCountries(input.getFetchJson().getCountries());
		fetchInput.setRegions(input.getFetchJson().getRegions());
		fetchInput.setSubregions(input.getFetchJson().getSubregions());
		fetchInput.setTypeSources(input.getFetchJson().getTypeSources());
		response=forecasteService.getDynamicOpportunity(fetchInput);*/
		//System.out.println("updateOpportunity controller response "+new Gson().toJson(response));	
		return response;

	}

	@RequestMapping(value = "/deleteOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO deleteOpportunity(@RequestBody ForecastInputVO input,HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		//System.out.println("/deleteOpportunity controller called input is "+new Gson().toJson(input));
		ForecastOutVO response=forecasteService.deleteOpportunity(input,globalId,userRole);
		//System.out.println("deleteOpportunity controller response "+new Gson().toJson(response));	
		return response;

	}
	
	
	@RequestMapping(value = "/getDynamicOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO getDynamicOpportunity(@RequestBody final ReportGridInputMultipleVO dto) {
		//System.out.println("/getDynamicOpportunity controller called username is "+new Gson().toJson(dto));
		ForecastOutVO response=forecasteService.getDynamicOpportunity(dto);
		//System.out.println("getDynamicOpportunity controller response "+new Gson().toJson(response));	
		System.out.println("-response success");
		return response;

	}
	
	
	@RequestMapping(value = "/submitForecast", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO submitForecast(@RequestBody final SubmitFCInputVO dto,HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		ForecastOutVO response =new ForecastOutVO();
		/*String userRole="cyashin";
		String globalId="Branch Forecaster";
		*/
		/*System.out.println("/submitForecast controller called Request JSON "+new Gson().toJson(dto));
		System.out.println("/submitForecast controller global ID "+globalId+" user role "+userRole);
		System.out.println("mmmm");*/
		
		String isLowerSubmit="Y";
		String isSubmitAllowed="Y";
		
		
		
		if("Country Forecaster".equalsIgnoreCase(userRole) && !(dto.getBranches().isEmpty()) && (dto.getBranches().get(0).getName()!=null) ){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="N";
			
		}
		if("Country Forecaster".equalsIgnoreCase(userRole)  && ("China".equalsIgnoreCase(dto.getRegions().get(0).getName())) ){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="N";
			
		}
		if("Sub Region Forecaster".equalsIgnoreCase(userRole) && !(dto.getBranches().isEmpty())  && (dto.getBranches().get(0).getName()!=null)){
		
			isSubmitAllowed="N";
			//response.setSubmitAllowed(false);
			
			
		}
		
		if("Regional Forecaster".equalsIgnoreCase(userRole) && !(dto.getCountries().isEmpty())  && (dto.getCountries().get(0).getName()!=null) ){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="N";
			
		}
		
		if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="N";
			
		}
		
		if("Branch Forecaster".equalsIgnoreCase(userRole)){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="Y";
			
		}
		
		// check condition for lower level forecast submission START 
		
		if("Country Forecaster".equalsIgnoreCase(userRole) && ("Y".equalsIgnoreCase(isSubmitAllowed))){
			isLowerSubmit=forecasteService.isSubmittedForCountryFC(dto);
			
		}
		
		if("Sub Region Forecaster".equalsIgnoreCase(userRole) && ("Y".equalsIgnoreCase(isSubmitAllowed))){
		
			isLowerSubmit=forecasteService.isSubmittedForSubRegionFC(dto);
			
			
		}
		
		if("Regional Forecaster".equalsIgnoreCase(userRole) && ("Y".equalsIgnoreCase(isSubmitAllowed)) ){
			
			isLowerSubmit=forecasteService.isSubmittedForRegionalFC(dto);
			
		}
		
		if("HQ/Admin Forecaster".equalsIgnoreCase(userRole)){
			//response.setSubmitAllowed(false);
			isSubmitAllowed="N";
			
		}
      //check for level forecast submission END
		
		
	  //call Submit forecast service START
		if("Y".equalsIgnoreCase(isSubmitAllowed) && ("Y".equalsIgnoreCase(isLowerSubmit)))
		{
			response=forecasteService.submitForecastALL(dto,globalId,userRole);	
			response.setLowerSubmit(true);
			//response.setIsLowerSubmit(true);
			response.setSaved(true);
			response.setSubmitAllowed(true);
		}
		 //call Submit forecast service END
		
		
		if("Y".equalsIgnoreCase(isSubmitAllowed) && ("N".equalsIgnoreCase(isLowerSubmit)))
 
		{
			
			response.setLowerSubmit(false);
			//response.setIsLowerSubmit(false);
			response.setSaved(false);
			response.setSubmitAllowed(true);
			
		}
		if("N".equalsIgnoreCase(isSubmitAllowed))

			{
				response.setLowerSubmit(false);
			//response.setIsLowerSubmit(false);
				response.setSaved(false);
				response.setSubmitAllowed(false);
			}
		System.out.println("** Submit forecast RESPONSE "+new Gson().toJson(response));		
		return response;

	}

//*************Re-Submit Forecast START
	//reSubmitForecast
	@RequestMapping(value = "/reSubmitForecast", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO reSubmitForecast(@RequestBody final SubmitFCInputVO dto,HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("globalId");
		
		/*String userRole="cyashin";
		String globalId="Branch Forecaster";
		*/
		System.out.println("/reSubmitForecast controller called Request JSON "+new Gson().toJson(dto));
		System.out.println("/reSubmitForecast controller global ID "+globalId+" user role "+userRole);
		
		ForecastOutVO response=forecasteService.reSubmitForecastALL(dto,globalId,userRole);
		System.out.println("reSubmitForecast controller response "+new Gson().toJson(response));	
		return response;

	}
	//*************Re-Submit Forecast END
	@RequestMapping(value = "/getAddNewOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  AddNewOpportunity getAddNewOpportunity(HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("saveGlobalID");
	  /* String userRole="Regional Forecaster";
		String globalId="mepang";*/
		//System.out.println("/getAddOpportunity controller called username is "+globalId+"userRole"+userRole);
		AddNewOpportunity response=forecasteService.getAddNewOpportunity(globalId,userRole);
		//System.out.println("getAddOpportunity controller response "+new Gson().toJson(response));	
		return response;

	}
	
	
	@RequestMapping(value = "/SaveAddNewOpportunity", method = RequestMethod.POST)
	@ResponseBody
	public  SaveOpportunityDTO SaveAddNewOpportunity(@RequestBody SaveOpportunityInputVO saveOpportunityInputVO,HttpSession session) {
		String userRole=(String)session.getAttribute("user_role");
		String globalId=(String)session.getAttribute("saveGlobalID");
		String userName=(String)session.getAttribute("globalId");
		//System.out.println("/SaveAddNewOpportunity controller called username is "+globalId+"userRole"+userRole+new Gson().toJson(saveOpportunityInputVO));
		String response=forecasteService.SaveAddNewOpportunity(saveOpportunityInputVO,globalId,userRole,userName);
		
		SaveOpportunityDTO saveOpportunityDTO=new SaveOpportunityDTO(); 
		saveOpportunityDTO.setSaveMsg(response);
		//System.out.println("SaveAddNewOpportunity controller response "+new Gson().toJson(response));	
		return saveOpportunityDTO;

	}
	
	
	@RequestMapping(value = { "/getLob" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getLob() {
		
		List<DropDownVO> value =forecasteService.getLob();
		
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		//System.out.println("getLob controller reponse "+new Gson().toJson(response));
		return response;
	}
	
	

	@RequestMapping(value = { "/getCurrency" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getCurrency() {
		
		List<DropDownVO> value =forecasteService.getCurrency();
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		//System.out.println("getCurrency controller reponse "+new Gson().toJson(response));
		return response;
	}
	
	@RequestMapping(value = { "/getAccountName" }, method = RequestMethod.POST)
	@ResponseBody
	public ReportOnLoadDTO getAccountName(@RequestBody AccountNameInputVO accountNameInputVO) {
		
		List<DropDownVO> value =forecasteService.getAccountName(accountNameInputVO);
		ReportOnLoadDTO response = new ReportOnLoadDTO();
		response.setDropdownValues(value);
		//System.out.println("getAccountName controller reponse "+new Gson().toJson(response));
		return response;
	}
	@RequestMapping(value = "/getRefresh", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO getRefresh(@RequestBody final ReportGridInputMultipleVO dto,HttpSession session) {
		String globalId=(String)session.getAttribute("globalId");
		//System.out.println("/getRefresh controller called INPUT is "+new Gson().toJson(dto));
		ForecastOutVO response=new ForecastOutVO();
		//call First source  type
		/*	List<ValueDTO> typeSources=new ArrayList<ValueDTO>();
			ValueDTO d=new ValueDTO();
			d.setName(WFTConstant.SOURCE_OPENGLOAB);
			typeSources.add(d);
			dto.setTypeSources(typeSources);
			response=forecasteService.getRefresh(dto,globalId);
			System.out.println("FIRST call with sorce type END");
			//call second source  type
			List<ValueDTO> typeSources2=new ArrayList<ValueDTO>();
			ValueDTO d2=new ValueDTO();
			d2.setName(WFTConstant.SOURCE_WORMALD);
			typeSources2.add(d2);
			dto.setTypeSources(typeSources2);
			response=forecasteService.getRefresh(dto,globalId);
			System.out.println("SECOND call with sorce type END");
			//call third source  type
			List<ValueDTO> typeSources3=new ArrayList<ValueDTO>();
			ValueDTO d3=new ValueDTO();
			d3.setName(WFTConstant.SOURCE_WW);
			typeSources3.add(d3);
			dto.setTypeSources(typeSources3);
			response=forecasteService.getRefresh(dto,globalId);
			System.out.println("THIRD call with sorce type END");
		*/
		response=forecasteService.getRefreshParent(dto, globalId);
		//System.out.println("getRefresh controller response "+new Gson().toJson(response));	
		return response;

	}
	
	@RequestMapping(value = { "/getRate" }, method = RequestMethod.POST)
	@ResponseBody
	public String getRate(@RequestBody ConvertedAmountDTO convertedAmountDTO) {
		//System.out.println("getRate controller request"+convertedAmountDTO);
		String value =forecasteService.getRate(convertedAmountDTO);
		//System.out.println("getRate controller reponse "+new Gson().toJson(value));
		return value;
	}
	
	@RequestMapping(value = "/callDataPullFundtion", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO callDataPullFundtion(/*@RequestBody LoginBean loginBean*/) {
		System.out.println("/callDataPullFundtion controller called username is ");
		forecasteService.callDataPullFunction(new String());
		//System.out.println("getOpportunity controller response "+new Gson().toJson(response));	
		return new ForecastOutVO();

	}
	@RequestMapping(value = "/callTempAutoSubmission", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastOutVO callTempAutoSubmission(/*@RequestBody LoginBean loginBean*/) {
		System.out.println("/callDataPullFundtion controller called username is ");
		forecasteService.callTempAutoSchedule(new String());
		//System.out.println("getOpportunity controller response "+new Gson().toJson(response));	
		return new ForecastOutVO();

	}
	
	
	@RequestMapping(value = "/getForecastLock", method = RequestMethod.POST)
	@ResponseBody
	public  ForecastLockDTO getForecastLock(HttpSession session) {
		//System.out.println("/getForecastLock controller  ");
		String userRole=(String)session.getAttribute("user_role");
		
		ForecastLockDTO response=new ForecastLockDTO();
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
		
		//System.out.println("getForecastLock controller response "+new Gson().toJson(response));	
		return response;

	}

}
