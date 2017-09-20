package com.java.main.dao;

import java.math.BigDecimal;
import java.util.List;

import com.java.main.dto.UserDetails;
import com.java.main.model.UserMapping;


public interface UserDao {

	public List<Object[]>  getUserDetails(UserDetails dto);

	public String getUserDetails(UserMapping dto);
	
	public List<BigDecimal>  getUserWindowDetails(String userRole);
}
