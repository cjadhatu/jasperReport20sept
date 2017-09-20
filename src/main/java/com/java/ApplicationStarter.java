package com.java;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
//import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
//@EntityScan(basePackages ={"com.java.main.model"})
//@ImportResource("classpath:dispatcher-servlet.xml")
public class ApplicationStarter extends
SpringBootServletInitializer{


	public static void main(String[] args) {
		SpringApplication.run(ApplicationStarter.class, args);
		System.out.println("v2 supreet");
	}
 
	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(ApplicationStarter.class);
	}
}

/*
 * @SpringBootApplication public class SamlappApplication extends
 * SpringBootServletInitializer{
 * 
 * @Override protected SpringApplicationBuilder
 * configure(SpringApplicationBuilder application) { return
 * application.sources(SamlappApplication.class); } }
 */

//@RestController
//@RequestMapping("/okta")
/*class MyOkta {

	@RequestMapping("/okta/names")
	public ModelAndView hello() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("hello");
		String str = "Hello World!";
		mav.addObject("message", str);
		return mav;
		}
*/	/*public Map names(ModelMap map, Principal principal) {
		Authentication authentication = SecurityContextHolder.getContext()
				.getAuthentication();
		System.out.println("--------------- ***** ------------- Okta SSO ");
		SAMLCredential credential = (SAMLCredential) authentication
				.getCredentials();
		List<Attribute> attributes = credential.getAttributes();

		System.out.println("--------------- ***** ------------- Okta SSO ");

		String username = credential.getNameID().getValue();
		System.out.println(username);
		System.out.println(credential.getAdditionalData());
		System.out.println(authentication.getDetails());
		for (Attribute attribute : attributes) {
			String name = attribute.getName();
			String cre = credential.getAttributeAsString(name);

			System.out.println(String.format(" name : {0} : {1}", name, cre));
		}
		Map maped = new HashMap<String, String>();
		maped.put("username", username);
		return maped;
	}*/
/*}*/
