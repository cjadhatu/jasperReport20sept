package com.java.main.dao;

import java.util.List;

import com.java.main.dto.ChangePasswordInput;
import com.java.main.dto.UserDetails;

public interface LoginDao {
	public List<Object[]> getRoleByGlobalId(String globalId);
	public UserDetails getUserDetailsByGlobalId(String globalId);
	public List<Object[]> getLandingPageTotals(String queryString,String globalId);
	public String getSorceTypeByGlobalId(String globalId);
	public String getOldPasswordByGlobalId(String globalId);
	public String saveNewPasswordByGlobalId(ChangePasswordInput changePasswordInput);
}
