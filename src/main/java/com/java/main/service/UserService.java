package com.java.main.service;

import java.math.BigDecimal;
import java.util.List;

import com.java.main.dto.UserDetails;
import com.java.main.dto.UserMappingVO;


public interface UserService {
	
	public UserMappingVO getUserDetails(UserDetails dto);

	public String saveUserDetails(UserMappingVO dto);
	
	public List<BigDecimal>  getUserWindowDetails(String userRole);

}
