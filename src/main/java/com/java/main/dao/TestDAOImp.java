package com.java.main.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.model.Region;

@Repository
public class TestDAOImp implements TestDAOImpl {

	
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	
	
	@Transactional
	public void selectQuery() {
		// TODO Auto-generated method stub
     System.out.println("--------------------------DONE");

  List<Object[]> val =em.createNativeQuery("SELECT r_id,r_name FROM sfdc.region").getResultList();
     
     System.out.println("------------"+val.size());
     
		
		
	}





	@Transactional
	public void save() {
		// TODO Auto-generated method stub
		
		Object  val =em.createNativeQuery("SELECT MAX(r_id) FROM sfdc.region").getResultList();
		System.out.println("-----------max"+val);
		
		Region r=new Region();
		r.setR_name("TYCOTEST");
		em.persist(r);
		
		
		
	}

}
