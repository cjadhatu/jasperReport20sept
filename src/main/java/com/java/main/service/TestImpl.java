package com.java.main.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dao.TestDAOImpl;

@Service
public class TestImpl implements TestImp {
	
	@Autowired
	
	private TestDAOImpl testDAOImpl;

	public void selectQuery() {
		// TODO Auto-generated method stub
		
		testDAOImpl.selectQuery();

	}

	public void save() {
		// TODO Auto-generated method stub\
		testDAOImpl.save();
		
	}

}
