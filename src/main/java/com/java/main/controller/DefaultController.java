package com.java.main.controller;
import java.util.List;

import org.opensaml.saml2.core.Attribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.saml.SAMLCredential;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.java.main.service.BranchService;
import com.java.main.service.LoginService;
import com.java.main.service.RegionService;



@Controller
public class DefaultController {
	
	@Autowired
	LoginService loginService;

	@Autowired
	RegionService regionService;
	@Autowired
	BranchService branchService;	
	
	@RequestMapping(value = "/")
    public ModelAndView defaultController() {
      /* Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println("--------------- ***** ------------- Okta SSO ");
		SAMLCredential credential = (SAMLCredential) authentication
				.getCredentials();
		List<Attribute> attributes = credential.getAttributes();

		System.out.println("--------------- ***** ------------- Okta SSO ");

		String globalId = credential.getNameID().getValue();
		System.out.println("globalId is "+ globalId );
		System.out.println(credential.getAdditionalData());
		System.out.println(authentication.getDetails());
		for (Attribute attribute : attributes) {
			String name = attribute.getName();
			String cre = credential.getAttributeAsString(name);

			System.out.println(String.format(" name : {0} : {1}", name, cre));
		}
		Map maped = new HashMap<String, String>();
		maped.put("username", username);
		//return maped;
*/		 ModelAndView mv = new ModelAndView("wft","globelId", "");
		return mv;
		}    

}
