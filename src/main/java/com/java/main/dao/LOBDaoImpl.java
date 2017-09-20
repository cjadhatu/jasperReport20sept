package com.java.main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class LOBDaoImpl implements LOBDao {
	
	@PersistenceContext
	private EntityManager em;

	public List<Object[]> getLOBDefination() {
		// TODO Auto-generated method stub
		
		
		List<Object[]> listLOBDefination=em.createNativeQuery("select lob_name, sublob_name,forecast_lobname from webforecastdev.forecast_lobmaster order by 1,2,3").getResultList();
		
		return listLOBDefination;
		
	}

}
