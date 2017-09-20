package com.java.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.main.dto.LOBDefinationDTO;
import com.java.main.service.LOBService;

@Controller
public class LOBController {

	@Autowired
	LOBService lobService;

	@RequestMapping(value = "/getLOBDefination", method = RequestMethod.POST)
	@ResponseBody
	public LOBDefinationDTO getLOBDefination() {

		return lobService.getLOBDefination();

	}

}
