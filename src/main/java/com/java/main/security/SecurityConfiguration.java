package com.java.main.security;

import static org.springframework.security.extensions.saml2.config.SAMLConfigurer.saml;
import jersey.repackaged.com.google.common.collect.ImmutableList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Value("${server.ssl.key-store}")
	String jksUrl;

	
	@Value("${security.saml2.test-url}")
	String metadataUrl;
//Local
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		//.cors().and()
		.cors().configurationSource(corsConfigurationSource()).and()
		
		.authorizeRequests().antMatchers("/saml/**").permitAll()
		//.authorizeRequests().antMatchers("/**").permitAll()
		.anyRequest().authenticated().and()
				.apply(saml()).serviceProvider()
			.keyStore()
		      //.entityId("JCI-BE-APAC-ForecastingTool.QA").keyStore()
			    //.entityId("JCI-BE-APAC-ForecastingTool").keyStore()
				.storeFilePath(jksUrl).password("secret").keyname("spring")
				.keyPassword("secret").and().protocol("https")
				//.hostname("forecastingtoolqa.herokuapp.com").basePath("/")
				//.hostname("forecastingtool.herokuapp.com").basePath("/")
				.hostname("localhost:8443").basePath("/")
				.and().identityProvider().metadataFilePath(metadataUrl).and();
		
		
	}
*/	// QA 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		//.cors().and()
		.cors().configurationSource(corsConfigurationSource()).and()
		
		//.authorizeRequests().antMatchers("/saml/**").permitAll()
		.authorizeRequests().antMatchers("/**").permitAll()
		.anyRequest().authenticated().and()
				.apply(saml()).serviceProvider()
			//.keyStore()
		      .entityId("JCI-BE-APAC-ForecastingTool.QA").keyStore()
			    //.entityId("JCI-BE-APAC-ForecastingTool").keyStore()
				.storeFilePath(jksUrl).password("secret").keyname("spring")
				.keyPassword("secret").and().protocol("https")
				.hostname("forecastingtoolqa.herokuapp.com").basePath("/")
				//.hostname("forecastingtool.herokuapp.com").basePath("/")
				//.hostname("localhost:8443").basePath("/")
				.and().identityProvider().metadataFilePath(metadataUrl).and();
		
		
	}
	//Production
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		//.cors().and()
		.cors().configurationSource(corsConfigurationSource()).and()
		
		.authorizeRequests().antMatchers("/saml/**").permitAll()
		//.authorizeRequests().antMatchers("/**").permitAll()
		.anyRequest().authenticated().and()
				.apply(saml()).serviceProvider()
			//.keyStore()
		      //.entityId("JCI-BE-APAC-ForecastingTool.QA").keyStore()
			    .entityId("JCI-BE-APAC-ForecastingTool").keyStore()
				.storeFilePath(jksUrl).password("secret").keyname("spring")
				.keyPassword("secret").and().protocol("https")
				//.hostname("forecastingtoolqa.herokuapp.com").basePath("/")
				.hostname("forecastingtool.herokuapp.com").basePath("/")
				//.hostname("localhost:8443").basePath("/")
				.and().identityProvider().metadataFilePath(metadataUrl).and();
		
		
	}
*/	
//	@Bean
//	CorsConfigurationSource corsConfigurationSource() {
//		CorsConfiguration configuration = new CorsConfiguration();
//		List<String> list = new ArrayList<String>();
//		list.add("*");
//		//configuration.setAllowedOrigins(Arrays.asList("https://localhost:8443/"));
//		//configuration.setAllowedOrigins(Arrays.asList("http://forecastingtoolqa.herokuapp.com/","https://forecastingtoolqa.herokuapp.com/"));
//		//configuration.setAllowedOrigins(Arrays.asList("*"));
//		configuration.setAllowedOrigins(list);
//		//configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	
//	CorsConfigurationSource configurationSource = new CorsConfigurationSource() {
//        @Override
//        public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//            //return new CorsConfiguration().applyPermitDefaultValues();
//        	
//        	CorsConfiguration corsConfiguration = new CorsConfiguration();
////        	corsConfiguration.addAllowedOrigin("http://forecastingtoolqa.herokuapp.com");
////        	corsConfiguration.addAllowedOrigin("https://forecastingtoolqa.herokuapp.com");
//        	corsConfiguration.addAllowedOrigin("*");
//        	corsConfiguration.addAllowedHeader("*");
//        	corsConfiguration.addAllowedMethod("*");
//        	//corsConfiguration.addAllowedMethod("*");
//        	corsConfiguration.addAllowedOrigin("*");
//        	//corsConfiguration.addExposedHeader("*");
//        	return corsConfiguration;
//        	
//        }
//    };

	 //@Bean
	    public CorsConfigurationSource corsConfigurationSource() {
	        final CorsConfiguration configuration = new CorsConfiguration();
	        configuration.setAllowedOrigins(ImmutableList.of("*"));
	        configuration.setAllowedMethods(ImmutableList.of("HEAD",
	                "GET", "POST", "PUT", "DELETE", "PATCH" , "OPTIONS" ));
	        // setAllowCredentials(true) is important, otherwise:
	        // The value of the 'Access-Control-Allow-Origin' header in the response must not be the wildcard '*' when the request's credentials mode is 'include'.
	        configuration.setAllowCredentials(true);
	        // setAllowedHeaders is important! Without it, OPTIONS preflight request
	        // will fail with 403 Invalid CORS request
	        configuration.setAllowedHeaders(ImmutableList.of("Origin","Authorization","X-Requested-With", "Content-Type","Accept","Cache-Control"));
	        configuration.setMaxAge(1800L);
	        System.out.println("max age set is " + configuration.getMaxAge());
	        
	        System.out.println("getAllowedHeaders ----> " + configuration.getAllowedHeaders());
	        System.out.println("getAllowedMethods ----> " + configuration.getAllowedMethods());
	        System.out.println("getAllowedOrigins ---->" + configuration.getAllowedOrigins());
	        System.out.println("getAllowCredentials ---->" + configuration.getAllowCredentials());
	        
	        
	        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	        source.registerCorsConfiguration("/**", configuration);
	        return source;
	    }

}