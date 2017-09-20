package com.java.main.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.UserDetails;
import com.java.main.model.UserMapping;

@Repository
public class UserDaoImpl implements UserDao {
	
	@PersistenceContext
	private EntityManager em;
	
	
	
    @Transactional
	public List<Object[]>  getUserDetails(UserDetails dto) {
		// TODO Auto-generated method stub
    	String queryString="select * from webforecastdev.vw_usersource  where globalid='"+dto.getGlobleId()+"' and datasource='OpenGlobe'";
		List<Object[]>  userdetails=em.createNativeQuery(queryString).getResultList();
		
		return userdetails;
	}


	@Transactional
	public String getUserDetails(UserMapping dto) {
		// TODO Auto-generated method stub
		em.persist(dto);
		return null;
	}


	public List<BigDecimal> getUserWindowDetails(String userRole) {
		// TODO Auto-generated method stub
		
		List<BigDecimal> datesBd=new ArrayList<BigDecimal>();
		String queryString="select access_date from webforecastdev.vw_forecast_role_access where role_name='"+userRole+"'";
		
		List<Integer> datesInt=em.createNativeQuery(queryString).getResultList();
		for(Integer d:datesInt){
			BigDecimal bd=new BigDecimal(d);
			datesBd.add(bd);
		}
		
		return datesBd;
		
	}

}
