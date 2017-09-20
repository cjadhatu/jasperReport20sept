package com.java.main.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.java.main.dto.ChangePasswordInput;
import com.java.main.dto.LoginUserDetailsVO;
import com.java.main.dto.SaveOpportunityDTO;
import com.java.main.dto.ValueDTO;
import com.java.main.model.LoginBean;
import com.java.main.service.BranchService;
import com.java.main.service.LoginService;
import com.java.main.service.RegionService;
import com.java.main.utility.ForecastPeriod;



@Controller
public class LoginController {
	
	@Autowired
	LoginService loginService;

	@Autowired
	RegionService regionService;
	@Autowired
	BranchService branchService;	
	
@RequestMapping(value = "/logindemo", method = RequestMethod.POST)
	public ModelAndView logindemo(@ModelAttribute("loginBean") LoginBean loginBean) {
		
        ModelAndView mv = new ModelAndView("regiondemo");
		
		return mv;
		
		
	}
	
	@RequestMapping(value = "/getForecastPeriod", method = RequestMethod.POST)
	@ResponseBody
	public ValueDTO getForecastPeriod() {
		System.out.println("getForecastPeriod controller called");
        String response =ForecastPeriod.getForecastPeriod();
        ValueDTO value=new ValueDTO();
        value.setName(response);
        System.out.println("getForecastPeriod controller response "+response);		
		return value;
		
		
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public  LoginUserDetailsVO adminLogin(@RequestBody LoginBean loginBean,HttpSession session) {
		System.out.println("/login controller Called input "+new Gson().toJson(loginBean));
		//session.setAttribute("globalId", loginBean.getUsername());
		
		LoginUserDetailsVO response=loginService.getUserDetailsByGlobalId(loginBean.getUsername(),loginBean.getPassword());
		session.setAttribute("saveGlobalID",loginBean.getUsername());
		session.setAttribute("globalId", response.getUserName());
		session.setAttribute("user_role",response.getRole());
		session.setAttribute("user_name",response.getUserName());
		String userRole=(String)session.getAttribute("user_role");
	    String saveGlobalID=(String)session.getAttribute("saveGlobalID");
	    String globalId=(String)session.getAttribute("globalId");
	    /*System.out.println("/login controller session varibale saveGlobalID "+saveGlobalID);
	    System.out.println("/login controller session varibale userRole "+userRole);
	    System.out.println("/login controller session varibale globalId "+globalId);
	*/
	    response.setGlobalId(loginBean.getUsername());
		response.setForecastePeriod(ForecastPeriod.getForecastPeriod());
		
	return response;	
	}
	
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	@ResponseBody
	public  SaveOpportunityDTO changePassword(@RequestBody ChangePasswordInput changePasswordInput,HttpSession session) {
		System.out.println("/changePassword controller Called input "+new Gson().toJson(changePasswordInput));
		
		SaveOpportunityDTO response=new SaveOpportunityDTO();
		String responseService=loginService.changePasswordByGlobalId(changePasswordInput);
			if("SUCCESS".equalsIgnoreCase(responseService))	{
				response.setSaveMsg("Y");
				
			}
			else{
				response.setSaveMsg("N");
			}
	return response;	
	}
	@RequestMapping(value="/logoutwft",method = RequestMethod.POST)
	 @ResponseBody
	    public SaveOpportunityDTO signOut(HttpServletRequest request){
		 SaveOpportunityDTO response=new SaveOpportunityDTO();
	     
	     HttpSession session = request.getSession();
	     session.setAttribute("lollypop", "it's my party");
	     Object lollypop = session.getAttribute("lollypop");

	     // print session ID and attribute
	     System.out.println("logout controller called "+session.getId());
	     System.out.println(lollypop);
	     session.invalidate();
	     HttpSession existingSession = request.getSession(false);
	     if (existingSession != null){
	    	 Object lollypop2 = existingSession.getAttribute("lollypop");

		     // print session ID and attribute
		     System.out.println(existingSession.getId());
		     System.out.println(lollypop2);
	    	 
	    	 
	        //do something
	     }
	     else{
	    	 System.out.println("Sesson is NULL");
	     }
	 	 
	response.setSaveMsg("SUCCESS");
	return response;
	}

}
