package com.java.main;

import javax.annotation.Resource;
import javax.sql.DataSource;

import nz.net.ultraq.thymeleaf.LayoutDialect;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.dialect.SpringStandardDialect;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
/*@ComponentScan({"com.java.main","com.programcreek"})*/
@EntityScan(basePackages ={"com.java.main.model","com.programcreek"})
public class MvcConfig extends WebMvcConfigurerAdapter {
/*
	private static final String PROPERTY_NAME_DATABASE_DRIVER = "org.postgresql.Driver";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "1d11b86ebd719ce4887dbcb2848f8e5276cde7b26075c855dfff2c28fc2d37d3";
	private static final String PROPERTY_NAME_DATABASE_URL = "jdbc:postgresql://ec2-54-163-236-33.compute-1.amazonaws.com:5432/d16g9mvi06kecr?sslmode=require";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "kwxishotevzmfy";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";

	*//** Added by Sujatha*//*
	private static final String PROPERTY_HIBERNATE_DEFAULT_SCHEMA = "hibernate.default_schema";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.java.main,com.programcreek.helloworld.controller";
*/
	@Resource
	private Environment env;	
	
	@Bean
	public InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		//resolver.setPrefix("WEB-INF/views/");
		resolver.setPrefix("/");
		resolver.setSuffix(".jsp");
		return resolver;
	}

	 @Override
	  public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
	    configurer.defaultContentType(MediaType.APPLICATION_XML);
	  }
	
		@Bean
		public ServletContextTemplateResolver templateResolver() {
			ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
			resolver.setPrefix("/WEB-INF/views/");
			/*resolver.setSuffix(".html");*/
			resolver.setTemplateMode("HTML5");
			resolver.setOrder(1);
			return resolver;
		}

		@Bean
		public SpringTemplateEngine templateEngine() {
			SpringTemplateEngine engine = new SpringTemplateEngine();
			engine.setTemplateResolver(templateResolver());
			engine.addDialect(new LayoutDialect());
			/* engine.addDialect(new SpringSecurityDialect()); */
			engine.addDialect(new SpringStandardDialect());
			return engine;
		}

		@Bean
		public ThymeleafViewResolver thymeleafViewResolver() {
			ThymeleafViewResolver resolver = new ThymeleafViewResolver();
			resolver.setTemplateEngine(templateEngine());
			return resolver;
		}
		
		@ConfigurationProperties(prefix = "spring.datasource")
		@Bean
		@Primary
		public DataSource dataSource() {
		    return DataSourceBuilder
		        .create()
		        .build();
		}
		
		/*@ConfigurationProperties(prefix = "spring.datasource")
		@Bean
		@Primary
		public DataSource dataSource() {
			//DriverManagerDataSource dataSource = new DriverManagerDataSource();
			//System.out.println("PROPERTY_NAME_DATABASE_DRIVER --> " +  PROPERTY_NAME_DATABASE_DRIVER);
			//dataSource.setDriverClassName(env.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER));
			System.out.println("PROPERTY_NAME_DATABASE_URL --> " +  PROPERTY_NAME_DATABASE_URL);
			dataSource.setUrl(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
			dataSource.setUsername(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
			System.out.println("PROPERTY_NAME_DATABASE_USERNAME --> " +  PROPERTY_NAME_DATABASE_USERNAME);
			dataSource.setPassword(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
			System.out.println("PROPERTY_NAME_DATABASE_PASSWORD --> " +  PROPERTY_NAME_DATABASE_PASSWORD);
			
			 DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
		        dataSourceBuilder.url(env.getRequiredProperty(PROPERTY_NAME_DATABASE_URL));
		        dataSourceBuilder.username(env.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		        dataSourceBuilder.password(env.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		        return dataSourceBuilder.build();
			      return DataSourceBuilder
			                .create()
//			                .url("jdbc:h2:D:/work/workspace/fork/gs-serving-web-content/initial/data/fdata;DATABASE_TO_UPPER=false")
//			                .username("h2")
//			                .password("h2")
//			                .driverClassName("org.h2.Driver")
			                .build();
			    }
			*/
			/*try {
				//System.out.println("DataSource --> " +  dataSource.getConnection()  );
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			//return dataSource;
		
		
		/*@Bean
		public DataSource dataSource() throws NamingException {
			Context intialContext = new InitialContext();
			DataSource dataSource = (DataSource) intialContext
					.lookup("jdbc/BBP_ALLOC");
			
			// dataSource.setJndiName("java:comp/env/jdbc/BBP_ALLOC");
			// dataSource.setJndiName("java:comp/env/jdbc/SYSTEM");*/
			// dataSource.setJndiName("java:jboss/CHIRAG");
			/*JndiObjectFactoryBean dataSource = new JndiObjectFactoryBean();
			dataSource.setJndiName("java:comp/env/jdbc/equ_testing");
			
			try {
				// dataSource.afterPropertiesSet();
				System.out.println("****JNDI sucess****");
				System.out.println("JNDI Object "+ dataSource);
			} catch (IllegalArgumentException e) {
				// rethrow
				throw new RuntimeException(e);
			}
			return (DataSource) dataSource;
		}*/
		

		/*@Bean
		@ConfigurationProperties(prefix = "spring.datasource")
		public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
			LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource());
			entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistence.class);
			//entityManagerFactoryBean.setPackagesToScan(env.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));
			//System.out.println("PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN --> " +  PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN);
			
			entityManagerFactoryBean.setJpaProperties(hibProperties());

			return entityManagerFactoryBean;
		}
		@ConfigurationProperties(prefix = "spring.datasource")
		private Properties hibProperties() {
			Properties properties = new Properties();
			properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,	env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
			System.out.println("PROPERTY_NAME_HIBERNATE_DIALECT --> " +  PROPERTY_NAME_HIBERNATE_DIALECT);
			properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, env.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
			
			*//** Added by Sujatha*//*
			properties.put(PROPERTY_HIBERNATE_DEFAULT_SCHEMA, env.getRequiredProperty(PROPERTY_HIBERNATE_DEFAULT_SCHEMA));
			
			System.out.println("PROPERTY_NAME_HIBERNATE_SHOW_SQL --> " +  PROPERTY_NAME_HIBERNATE_SHOW_SQL);
			return properties;
		}

		@Bean
		public JpaTransactionManager transactionManager() throws NamingException {
			JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
			return transactionManager;
		}
*/
		//@Bean
		/*public InternalResourceViewResolver setupViewResolver() {
			InternalResourceViewResolver resolver = new InternalResourceViewResolver();
			resolver.setPrefix("/WEB-INF/pages/");
			resolver.setSuffix(".jsp");
			resolver.setViewClass(JstlView.class);
			return resolver;
		}*/
			/*public UrlBasedViewResolver setupViewResolver() {
			System.out.println("in viewresolver");
			UrlBasedViewResolver resolver = new UrlBasedViewResolver();
			
			resolver.setPrefix("/WEB-INF/pages/");
			resolver.setSuffix(".jsp");
			resolver.setViewClass(JstlView.class);
			return resolver;
		}*/
		
		
		/*@Bean
		public ResourceBundleMessageSource messageSource() {
			ResourceBundleMessageSource source = new ResourceBundleMessageSource();
			source.setBasename(env.getRequiredProperty("message.source.basename"));
			source.setUseCodeAsDefaultMessage(true);
			return source;
		}*/
		@Bean
		public CommonsMultipartResolver multipartResolver(){
			CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
			multipartResolver.setMaxUploadSize(100000);
			return multipartResolver;
		}
		//@Bean
		/*@Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
			
			registry.addResourceHandler("/css/**").addResourceLocations("/resources/theme1/css").setCachePeriod(31556926);
			registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/").setCachePeriod(31556926);
			registry.addResourceHandler("/images/**").addResourceLocations("/resources/theme1/images/").setCachePeriod(31556926);
			registry.addResourceHandler("/js/**").addResourceLocations("/resources/theme1/js/").setCachePeriod(31556926);
			registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/theme1/fonts/").setCachePeriod(31556926);
			 
	    registry.addResourceHandler("/resources/**").addResourceLocations("/resources/theme1/").setCachePeriod(31556926);
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/statics/").setCachePeriod(31556926);
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/views/").setCachePeriod(31556926);
        registry.addResourceHandler("/static/**").addResourceLocations("/resources/theme1/htmlfile").setCachePeriod(31556926);
        registry.addResourceHandler("/images/**").addResourceLocations("WEB-INF/resources/images/").setCachePeriod(31556926);   
	  System.out.println("Supreet Resource handler");
        super.addResourceHandlers(registry);
			
		}*/

		 public void addResourceHandlers(ResourceHandlerRegistry registry) {
			 registry.addResourceHandler("/resources/**").addResourceLocations("/resources/theme1/").setCachePeriod(31556926);	
			 registry.addResourceHandler("/css/**").addResourceLocations("/resources/theme1/css").setCachePeriod(31556926);
				registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/").setCachePeriod(31556926);
				registry.addResourceHandler("/images/**").addResourceLocations("/resources/theme1/images/").setCachePeriod(31556926);
				registry.addResourceHandler("/js/**").addResourceLocations("/resources/theme1/js/").setCachePeriod(31556926);
				registry.addResourceHandler("/fonts/**").addResourceLocations("/resources/theme1/fonts/").setCachePeriod(31556926);
			/*	 registry.addResourceHandler("/css/**").addResourceLocations("/resources/theme1/").setCachePeriod(31556926);
		        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/static/").setCachePeriod(31556926);
		        registry.addResourceHandler("/static/**").addResourceLocations("/WEB-INF/views/").setCachePeriod(31556926);
		        registry.addResourceHandler("/static/**").addResourceLocations("/resources/theme1/htmlfile").setCachePeriod(31556926);
		        registry.addResourceHandler("/images/**").addResourceLocations("WEB-INF/resources/images/").setCachePeriod(31556926);*/   
		  super.addResourceHandlers(registry);
				
			}
		/*addResourceHandlers HAS BEEN OVERRIDDEN TO LOAD CSS (STATIC DATA INSIDE) FROM WWEBAPP*/
		 
		    /*// equivalent for <mvc:default-servlet-handler/> tag
		    @Override
		    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		        configurer.enable();
		    }
  
*/
		@Override
		public void configureDefaultServletHandling(
				DefaultServletHandlerConfigurer configurer) {
			configurer.enable();
			
		}
}

