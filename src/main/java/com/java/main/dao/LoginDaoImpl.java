package com.java.main.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.ChangePasswordInput;
import com.java.main.dto.UserDetails;
@Repository
public class LoginDaoImpl implements LoginDao {
	@PersistenceContext
	private EntityManager em;
	
	
	
	public List<Object[]> getRoleByGlobalId(String globalId) {
		//String queryString="select role from webforecastdev.user where alias ='"+globalId+"'";
		try {
			
			String queryString="select a.datasource,a.globalid,a.user_name,a.region,a.country,a.subregion,a.branch_code,a.lob,b.forecast_rolename,a.apac_flag,a.password from webforecastdev.forecast_user_rolemap a,webforecastdev.forecast_rolemaster b where a.forecast_roleid = b.roleid and a.isActive='A' and a.globalid='"+globalId+"'";
			 /*List<String> val =em.createNativeQuery(queryString).getResultList();
			 String role="";
			 for(String obj:val){
				 role=obj;
				 System.out.println(" in DAO Role is "+role);
			 }*/
			
			
						
			return em.createNativeQuery(queryString).getResultList();
		} catch (Exception e) {
			
			System.out.println("**** USER is Not valid");
			return new ArrayList<Object[]>();
			// TODO: handle exception
		}
		
	}

	public UserDetails getUserDetailsByGlobalId(String globalId) {
		// TODO Auto-generated method stub
		//String  queryString="select role,region__c,country,branch_code__c from webforecastdev.user where alias ='"+globalId+"'";
		String  queryString="select aproleid.rolename,usrapp.globalid,usrapp.maproleid from webforecastdev.approlemaster aproleid INNER JOIN webforecastdev.userapprolemap usrapp ON aproleid.roleid=usrapp.approleid WHERE usrapp.globalid='"+globalId+"'";
		List<Object[]> objs=em.createNativeQuery(queryString).getResultList();
		List<UserDetails> us=new ArrayList<UserDetails>();
		for(Object[] obj:objs){
			UserDetails u=new UserDetails();
			u.setRoleName((String)obj[0]);
			u.setGlobleId((String)obj[1]);
			u.setMaproleid((Integer)obj[2]);
			
			us.add(u);	
			
		}
		
		
		
		
		
		return us.get(0);
	}

	public List<Object[]> getLandingPageTotals(String queryString,String globalId) {
		// TODO Auto-generated method stub
		String queryStr="";
		//String	queryStr="SELECT region, sum(last_month_forecast) as suml, sum(current_month_forecast) as sumc, sum(rolling_90_days) as sumr, sum(planned_sales_amount) as sump FROM webforecastdev.vw_landing_page  "+queryString+" group by region";
		String userId="";
		if("jpagar3".equalsIgnoreCase(globalId) || "ccutlema".equalsIgnoreCase(globalId)){
		/*if("gpark".equalsIgnoreCase(globalId) || "ccutlema".equalsIgnoreCase(globalId)){*/
			//gpark
			//
		 queryStr="SELECT region, sum(last_month_forecast) as suml, sum(current_month_forecast) as sumc, sum(rolling_90_days) as sumr, max(planned_sales_amount) as sump FROM webforecastdev.vw_landing_page where 1=1 and region in(select region from webforecastdev.forecast_user_mdm where globalid  ='"+globalId+"' and isactive ='A') group by region";
		}
		else{
		 queryStr="SELECT region, sum(last_month_forecast) as suml, sum(current_month_forecast) as sumc, sum(rolling_90_days) as sumr, max(planned_sales_amount) as sump FROM webforecastdev.vw_landing_page  "+queryString+" group by region";
		}
		Query query=em.createNativeQuery(queryStr);
		return query.getResultList();
	}
	public String getSorceTypeByGlobalId(String globalId) {
		//String queryString="select role from webforecastdev.user where alias ='"+globalId+"'";
		try {
			
			String queryString="select a.datasource from webforecastdev.forecast_user_rolemap a,webforecastdev.forecast_rolemaster b where a.forecast_roleid = b.roleid and a.user_name='"+globalId+"'";
			 /*List<String> val =em.createNativeQuery(queryString).getResultList();
			 String role="";
			 for(String obj:val){
				 role=obj;
				 System.out.println(" in DAO Role is "+role);
			 }*/
			
			List<String> types=em.createNativeQuery(queryString).getResultList();
						
			return types.get(0);
		} catch (Exception e) {
			
			System.out.println("**** USER is Not valid");
			return new String();
			// TODO: handle exception
		}
		
	}
	public String getBranchByGlobalId(String globalId) {
		//String queryString="select role from webforecastdev.user where alias ='"+globalId+"'";
		try {
			
			String queryString="select a.datasource from webforecastdev.forecast_user_rolemap a,webforecastdev.forecast_rolemaster b where a.forecast_roleid = b.roleid and a.user_name='"+globalId+"'";
			 /*List<String> val =em.createNativeQuery(queryString).getResultList();
			 String role="";
			 for(String obj:val){
				 role=obj;
				 System.out.println(" in DAO Role is "+role);
			 }*/
			
			List<String> types=em.createNativeQuery(queryString).getResultList();
						
			return types.get(0);
		} catch (Exception e) {
			
			System.out.println("**** USER is Not valid");
			return new String();
			// TODO: handle exception
		}
		
	}

	public String getOldPasswordByGlobalId(String globalId) {
		// TODO Auto-generated method stub
		try {
			String queryString="select  password from webforecastdev.forecast_user_rolemap where  globalid="+"'"+globalId+"'";
			List<String> ids=em.createNativeQuery(queryString).getResultList();
			return ids.get(0);	
		} catch (Exception e) {
			// TODO: handle exception
			return new String();
		}
		
	}
	@Transactional
	public String saveNewPasswordByGlobalId(ChangePasswordInput input) {
		try {
			String queryString="update    webforecastdev.forecast_user_rolemap  set password ='"+input.getNewPassword()+"' where  globalid="+"'"+input.getGlobalId()+"'";
			em.createNativeQuery(queryString).executeUpdate();
			
			return "Y";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Password chnaged failed in save password exception is "+e.getMessage());
			return "N";
		}
		
		
	}

}
