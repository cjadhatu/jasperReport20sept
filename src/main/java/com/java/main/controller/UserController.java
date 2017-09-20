package com.java.main.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.java.main.dto.UserDetails;
import com.java.main.dto.UserMappingVO;
import com.java.main.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService; 
	
	@RequestMapping(value = { "/getUserDetails" }, method = RequestMethod.POST)
	@ResponseBody
	public UserMappingVO getUserDetails(@RequestBody final UserDetails dto,
			HttpSession session) {	
		
		return  userService.getUserDetails(dto);
	}
	
	@RequestMapping(value = { "/saveUserDetails" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveUserDetails(@RequestBody final UserMappingVO dto,
			HttpSession session) {	
		
		return  userService.saveUserDetails(dto);
	}

}
