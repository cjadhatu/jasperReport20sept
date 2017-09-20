package com.programcreek.helloworld.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
@Controller
public class HelloWorldController {

	private Service service = new Service();
	private JDBCPostGres jdbcPostGresservice = new JDBCPostGres();
	
	private static List<salesForcast> salesForcasts = new ArrayList<salesForcast>();

	/*static {
		
		salesForcast forcast=new salesForcast();
		forcast.setRegion("BRZ-REF");
		forcast.setGoToRegionfor("Forecast");
		forcast.setGoTobraches("GoToBranches");
		forcast.setForcastStatus("System Submited");
		forcast.setBranches("1");
		forcast.setOpportunites("");
		forcast.setForecastAmount("USD");
		forcast.setUpdated("2-8-2017");
		forcast.setUpdatedBy("System");
		salesForcasts.add(forcast);
		
		salesForcast forcast1=new salesForcast();
		forcast1.setRegion("BRZ-SRV");
		forcast1.setGoToRegionfor("Forecast");
		forcast1.setGoTobraches("GoToBranches");
		forcast1.setForcastStatus("System Submited");
		forcast1.setBranches("30");
		forcast1.setOpportunites("");
		forcast1.setForecastAmount("USD");
		forcast1.setUpdated("2-8-2017");
		forcast1.setUpdatedBy("System");
		salesForcasts.add(forcast1);
		
		
		salesForcast forcast2=new salesForcast();
		forcast2.setRegion("BRZ-SYS");
		forcast2.setGoToRegionfor("Forecast");
		forcast2.setGoTobraches("GoToBranches");
		forcast2.setForcastStatus("System Submited");
		forcast2.setBranches("12");
		forcast2.setOpportunites("");
		forcast2.setForecastAmount("USD");
		forcast2.setUpdated("2-8-2017");
		forcast2.setUpdatedBy("System");
		salesForcasts.add(forcast2);
		
		
	}*/
	
	String message = "Welcome to Spring MVC!";

	@RequestMapping("/hello")
	public ModelAndView showMessage(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		System.out.println(" /hello in controller");

		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", message);
		mv.addObject("name", name);
		return mv;
	}

	@RequestMapping(value = "/india", method = RequestMethod.GET)
	public ModelAndView simpleform(@RequestParam("rId") Integer rId) {

		/*ModelAndView mv = new ModelAndView("latinAmr","salesForcasts",salesForcasts);*/
		RegionDTO dto=service.getSubRegions(rId);
		
		MonthDto feb=new MonthDto();
		MonthDto march=new MonthDto();
		feb.setMonth("Feb");
		feb.setMonthEst(10);
		feb.setMonthFact(20);
		march.setMonth("March");
		march.setMonthEst(30);
		march.setMonthFact(40);
		List<MonthDto> months=new ArrayList<MonthDto>();
		months.add(feb);
		months.add(march);	
	
		
		//String gson =new Gson().toJson(months).toString();
		dto.setGraphData(months);
		
		
		
		
		
		
		
		System.out.println(" /india in controller");
		ModelAndView mv = new ModelAndView("subRegions","salesForcasts",dto);
		System.out.println("/india controller reponse "+new Gson().toJson(dto));

		return mv;

	}

	@RequestMapping(value = "/china", method = RequestMethod.GET)
	public ModelAndView MiddleEast(@RequestParam("rId") Integer rId) {
		RegionDTO dto=service.getSubRegions(rId);
		//ModelAndView mv = new ModelAndView("subRegions","salesForcasts",service.getSubRegions(rId));
		ModelAndView mv = new ModelAndView("subRegions","salesForcasts",dto);
		System.out.println("/china controller reponse "+new Gson().toJson(dto));
		return mv;

	}

	@RequestMapping(value = "/northeast", method = RequestMethod.GET)
	public ModelAndView NortAmerica(@RequestParam("rId") Integer rId) {
		RegionDTO dto=service.getSubRegions(rId);
		System.out.println("/northeast controller called for Tyco rId"+rId);
		/*ModelAndView mv = new ModelAndView("nortAmr");*/
		//ModelAndView mv = new ModelAndView("subRegions","salesForcasts",service.getSubRegions(rId));
		ModelAndView mv = new ModelAndView("subRegions","salesForcasts",dto);
		System.out.println("/northeast controller reponse "+new Gson().toJson(dto));
		return mv;

	}
	
	
	@RequestMapping(value = "/allOportunities", method = RequestMethod.GET)
	public ModelAndView allOportunities(@RequestParam("subRegionId") Integer subRegionId) {
		System.out.println(" /allOportunities in controller");
		System.out.println("Enter");
		List<Opportunity> opps = new ArrayList<Opportunity>();
		Opportunity opps1 = new Opportunity();
		Opportunity opps2 = new Opportunity();
		Opportunity opps3 = new Opportunity();
		
		opps1.setOppId(1);
		opps1.setOppName("Opportunity 1");
		opps1.setBranchName("Branch 1");
		
		opps2.setOppId(2);
		opps2.setOppName("Opportunity 2");
		opps2.setBranchName("Branch 2");
		
		opps3.setOppId(3);
		opps3.setOppName("Opportunity 3");
		opps3.setBranchName("Branch 3");
		
		opps.add(opps1);
		opps.add(opps2);
		opps.add(opps3);
		
		ModelAndView mv = new ModelAndView("allOpps","opps",opps);

		return mv;

	}
	
	
	@RequestMapping(value = "/getBranches", method = RequestMethod.POST)
	@ResponseBody
	public String getBranches(@RequestParam("subRegId") Integer regId) {
		System.out.println(" /getBranches in controller regId ="+regId);
		//System.out.println("Enter");
		BranchDTO dto=service.getBranches(regId);
		//BranchDTO dto=service.getBranches(3);
		//ModelAndView mv = new ModelAndView("branches","bracnhes",service.getBranches(regId));
		//ModelAndView mv = new ModelAndView("branches","bracnhes",dto);
		System.out.println(" /getBranches  controller response ="+new Gson().toJson(dto));
		String gson =new Gson().toJson(dto).toString();
	
		return gson;
		//return mv;

	}
	
	@RequestMapping(value = "/remove", method = RequestMethod.GET)
	@ResponseBody
	public String remove(@RequestParam("brId") Integer brId) {

		System.out.println("remove called brId="+brId);
		service.remove(brId);
		/*ModelAndView mv = new ModelAndView("oppsDt","oppsData",service.getBranches(brId));

		return mv;
*/
return "SUCCESS";
	}
	

	@RequestMapping(value = "/getOpps", method = RequestMethod.POST)
	@ResponseBody
	public String getOpps(@RequestParam("brId") Integer brId) {
		System.out.println(" /getOpps in controller");
		//System.out.println("Enter 1234");
		OppsDTO dto=service.getOpps(brId);
		//OppsDTO dto=service.getOpps(3);
		/*Date current=new Date();
		Date old=new Date(100);
		
		for(Opps opp: dto.getSubRegions()  ){
			if(opp.getOppsId()==5){
				opp.setPlanDate(old);
		}
		else
		{
			opp.setPlanDate(current);
		}
		}*/
		//ModelAndView mv = new ModelAndView("oppsDt","oppsData",service.getOpps(brId));
		//ModelAndView mv = new ModelAndView("oppsDt","oppsData",dto);
		System.out.println(" /getOpps  controller response "+new Gson().toJson(dto));
		String gson=new Gson().toJson(dto).toString();
		return gson;

	}
//temporary controller to check jdbc conn on heroku
	@RequestMapping(value = "/jdbcData", method = RequestMethod.GET)
	public ModelAndView getJdbcData() {
		System.out.println(" /jdbcData in controller");
		//ModelAndView mv = new ModelAndView("latinAmr","salesForcasts",salesForcasts);
		/*ModelAndView mv = new ModelAndView("test","data",jdbcPostGresservice.getjdbcData());*/
		ModelAndView mv = new ModelAndView();

		return mv;

	}
	
	@RequestMapping(value = "/getGraphData", method = RequestMethod.POST)
	@ResponseBody
	public String getGraphData(@RequestParam("subRegId") Integer regId) {
		//regId=3;
		System.out.println(" /getGraphData in controller regId ="+regId);
		//System.out.println("Enter");
	/*	MonthDto feb=new MonthDto();
		MonthDto march=new MonthDto();
		feb.setMonth("Feb");
		feb.setMonthEst(30);
		feb.setMonthFact(40);
		march.setMonth("March");
		march.setMonthEst(100);
		march.setMonthFact(200);
		List<MonthDto> months=new ArrayList<MonthDto>();
		months.add(feb);
		months.add(march);	
	*/
		List<MonthDto> months=new ArrayList<MonthDto>();
		months.add(service.getGraphDataFeb(regId));
		months.add(service.getGraphDataMarch(regId));
		String gson =new Gson().toJson(months).toString();
		System.out.println(" /getGraphData in controller response ="+new Gson().toJson(months));
		//dto.setGraphData(months);
		
		//String gson =new Gson().toJson(dto).toString();
	
		return gson;
		//return mv;

	}
}
