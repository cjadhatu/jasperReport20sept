package com.java.main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
@Repository
public class AddNewOpportunityDaoImpl implements AddNewOpportunityDao  {
	
	
	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> getAddNewOpportunity(String globalId) {
		// TODO Auto-generated method stub
		
		
		String queryString="select datasource, sfid,globalid, userid, user_name, manager_name,forecast_rolecode, region,subregion,country,branch_code,lob from webforecastdev.forecast_user_rolemap s where s.globalid ='"+globalId+"'";
		
		
		return em.createNativeQuery(queryString).getResultList();
		
		
	}


}
