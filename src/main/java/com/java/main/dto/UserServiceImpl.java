package com.java.main.dto;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.main.dao.UserDao;
import com.java.main.model.UserMapping;
import com.java.main.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public UserMappingVO getUserDetails(UserDetails dto) {
		// TODO Auto-generated method stub
		List<Object[]>  userdetails=userDao.getUserDetails(dto);
		UserMappingVO userMappingVO=new UserMappingVO();
		
		for(Object[] user:userdetails)
		{
			
			
			userMappingVO.setSourceType((String) user[0]);
			userMappingVO.setGlobelId((String) user[1]);
			
			userMappingVO.setUserId(((Integer) user[2]).toString());
			userMappingVO.setUserName((String) user[3]);
			userMappingVO.setRegion((String) user[4]);
			userMappingVO.setJciCountryCode((String) user[5]);
			
			userMappingVO.setSubRegion((String) user[6]);
			userMappingVO.setBranchCode((String) user[7]);
			userMappingVO.setLob((String) user[8]);
			userMappingVO.setSfid((String) user[9]);
			userMappingVO.setManagerid((String) user[10]);
			userMappingVO.setManagerName((String) user[11]);
			System.out.println(":"+((Boolean) user[12]));
			userMappingVO.setIsactive((Boolean) user[12]);
			userMappingVO.setCurrencyisocode((String) user[13]);

			
			
			
			
		}
		
		return userMappingVO;
		
	}

	public String saveUserDetails(UserMappingVO dto) {
		// TODO Auto-generated method stub
		
		UserMapping userMapping=new UserMapping();
	
		//userMapping.setMaproleid(dto.getm); 
		userMapping.setDatasource(dto.getSourceType()); 
		userMapping.setGlobalid(dto.getGlobelId()); 
		userMapping.setUserid(Integer.parseInt(dto.getUserId())); 
		userMapping.setUser_name(dto.getUserName()); 
		userMapping.setSfid(dto.getSfid()); 
		userMapping.setRegion(dto.getRegion()); 
		userMapping.setSubregion(dto.getSubRegion()); 
		userMapping.setCountry(dto.getJciCountryCode()); 
		userMapping.setLob(dto.getLob()); 
		userMapping.setManagerid(dto.getManagerid()); 
		userMapping.setManagerName(dto.getManagerName()); 
		userMapping.setCurrencyisocode(dto.getCurrencyisocode()); 
		userMapping.setForecastRoleid(0); 
		userMapping.setForecastRolecode("0"); 
		userMapping.setIsactive(dto.getIsactive().toString()); 
		userMapping.setBranchMapid(Integer.parseInt(dto.getBranchCode())); 
		userMapping.setLobMapid(0);
		userMapping.setCreatedby("tushar"); 
		userMapping.setCreateddate(new Date()); 
		userMapping.setModifiedby("tushar"); 
		userMapping.setModifieddate(new Date());
		
		
		
		return userDao.getUserDetails(userMapping);
	}

	public List<BigDecimal> getUserWindowDetails(String userRole) {
		// TODO Auto-generated method stub
		return userDao.getUserWindowDetails(userRole);
	}

}
