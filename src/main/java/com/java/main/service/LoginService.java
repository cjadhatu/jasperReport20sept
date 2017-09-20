package com.java.main.service;

import java.util.List;

import com.java.main.dto.ChangePasswordInput;
import com.java.main.dto.LoginUserDetailsVO;
import com.java.main.dto.UserRoleVO;

public interface LoginService {

	public List<UserRoleVO> getRoleByGlobalId(String globalId);
	//public UserDetails getUserDetailsByGlobalId(String globalId);
	public LoginUserDetailsVO getUserDetailsByGlobalId(String globalId,String password);
	public String getOldPasswordByGlobalId(String globalId);
	public String saveNewPasswordByGlobalId(String globalId);
	public String changePasswordByGlobalId(ChangePasswordInput globalId);
}
