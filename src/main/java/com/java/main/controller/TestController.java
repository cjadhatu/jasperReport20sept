package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java.main.service.TestImp;

@Controller
public class TestController {
	
	
	@Autowired
	private TestImp testImp; 
	
	@RequestMapping("/hellojpa")
	public ModelAndView showMessage() {
		System.out.println(" /hello in controller");

		ModelAndView mv = new ModelAndView("helloworld");
	
		testImp.selectQuery(); 
		
		return mv;
		
	}
	
	
	@RequestMapping("/savejpa")
	public ModelAndView save() {
		System.out.println(" /hello in controller");

		ModelAndView mv = new ModelAndView("helloworld");
	
		testImp.save();
		
		return mv;
		
	}

}
