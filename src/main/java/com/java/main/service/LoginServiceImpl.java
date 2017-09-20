package com.java.main.service;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dao.LoginDao;
import com.java.main.dto.ChangePasswordInput;
import com.java.main.dto.LandingComparator;
import com.java.main.dto.LoginSumVO;
import com.java.main.dto.LoginUserDetailsVO;
import com.java.main.dto.UserRoleVO;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.GetStringFromList;

@Service
public class LoginServiceImpl implements LoginService {
@Autowired
LoginDao loginDao;
	
	public List<UserRoleVO> getRoleByGlobalId(String globalId) {
		
		List<Object[]> objects=loginDao.getRoleByGlobalId(globalId);
		List<UserRoleVO> userRoles=new ArrayList<UserRoleVO>();
	//select a.datasource,a.globalid,a.user_name,a.region,a.country,a.subregion,a.branch_code,
		//a.lob,b.forecast_rolename from webforecastdev.forecast_user_rolemap a
		//,webforecastdev.forecast_rolemaster b where a.forecast_roleid = b.roleid and a.globalid='	
		for(Object[] obj:objects){
			UserRoleVO vo=new UserRoleVO();
			vo.setGlobalId((String)obj[1]);
			vo.setUserName((String)obj[2]);
			vo.setRegion((String)obj[3]);
			vo.setCountry((String)obj[4]);
			vo.setSubregion((String)obj[5]);
			vo.setBranch((String)obj[6]);
			vo.setRoleName((String)obj[8]);
			if(obj[9]!=null){
				vo.setIsApac((String)obj[9]);
			}
			vo.setPassword((String)obj[10]);
			userRoles.add(vo);
		}
		
		return userRoles;
	}
	
public LoginUserDetailsVO getUserDetailsByGlobalId(String globalId,String password) {
	LoginUserDetailsVO response=new LoginUserDetailsVO();
	String dbPassword="";
	String isPasswordVaild="Y";
	List<UserRoleVO> userRoles=getRoleByGlobalId(globalId);
	if(!userRoles.isEmpty())
	
	{
	
	
	
	
		dbPassword=userRoles.get(0).getPassword().trim();
		
		if(!dbPassword.equalsIgnoreCase(password)|| !(globalId.equalsIgnoreCase(userRoles.get(0).getGlobalId().trim()))){
			isPasswordVaild="N";
			response.setIsValidUser(isPasswordVaild);
			//response.setIsValidUser("N");
		}
		if(password==null){
			isPasswordVaild="Y";
			response.setIsValidUser(isPasswordVaild);
		}
	if("HQ/Admin Forecaster".equalsIgnoreCase(userRoles.get(0).getRoleName().trim())){
		
		response=getUserDetailsAdminQ(globalId);
		response.setIsApac(userRoles.get(0).getIsApac().trim());
		response.setIsValidUser(isPasswordVaild);
		
	}
	else{
		response=getNonAdminHQ(userRoles,globalId);
		response.setIsValidUser(isPasswordVaild);
	}
	response.setUserRegion(userRoles.get(0).getRegion());
	response.setUserCountry(userRoles.get(0).getCountry());
	response.setUserSubRegion(userRoles.get(0).getSubregion());
	response.setUserBranch(userRoles.get(0).getBranch());
	response.setUserName(userRoles.get(0).getUserName());
	response.setRole(userRoles.get(0).getRoleName());

	List<LoginSumVO> sortedList=getFinalSortedList(response.getListData());
	
	response.setListData(sortedList);
	
	return response;
	}
	else
	{
		response.setIsValidUser("N");
		
		return response;
	}
	//return loginDao.getUserDetailsByGlobalId(globalId);
	}
private List<LoginSumVO> getFinalSortedList(List<LoginSumVO> listData) {
	// TODO Auto-generated method stub
	List<LoginSumVO> finalist=new ArrayList<LoginSumVO>();
	List<LoginSumVO> sortedfinalist=new ArrayList<LoginSumVO>();
	for(LoginSumVO vo:listData){
		if("North East Asia".equalsIgnoreCase(vo.getName())){
			vo.setId("1");
			finalist.add(vo);
		}
		if("China".equalsIgnoreCase(vo.getName())){
			vo.setId("2");
			finalist.add(vo);
			
		}
		if("South East Asia".equalsIgnoreCase(vo.getName())){
			vo.setId("3");
			finalist.add(vo);
			
		}
		if("Pacific".equalsIgnoreCase(vo.getName())){
			vo.setId("4");
			finalist.add(vo);
			
		}
		if("India".equalsIgnoreCase(vo.getName())){
			vo.setId("5");
			finalist.add(vo);
			
		}
		if("Asia Products and Distribution".equalsIgnoreCase(vo.getName())){
			vo.setId("6");
			finalist.add(vo);
			
		}
	}
	Collections.sort(finalist, new LandingComparator());
	return finalist;
}

public LoginUserDetailsVO getUserDetailsAdminQ(String globalId) {
	System.out.println("------getUserDetailsAdminQ called------");
	LoginUserDetailsVO response=new LoginUserDetailsVO();
	List<LoginSumVO> totalAmouts=new ArrayList<LoginSumVO>();
	BigDecimal lastMonth=new BigDecimal(0);
	BigDecimal currentmonth=new BigDecimal(0);
	BigDecimal rollingDays=new BigDecimal(0);
	BigDecimal plannedsales=new BigDecimal(0);
	String queryString=" ";
	List<Object[]> objects=loginDao.getLandingPageTotals(queryString,globalId);
	for(Object[] obj:objects){
		LoginSumVO vo=new LoginSumVO();
		if(obj[0]!=null){
		
			vo.setName((String)obj[0]);
			vo.setIsClickable(true);
			vo.setLastMonth(DataTypeConvert.get$StringFromNumers(((BigDecimal)obj[1])));
			vo.setCurrentMonth(DataTypeConvert.get$StringFromNumers(((BigDecimal)obj[2])));
			vo.setRollingDays(DataTypeConvert.get$StringFromNumers(((BigDecimal)obj[3])));
			vo.setPlannedSale(DataTypeConvert.get$StringFromNumers(((BigDecimal)obj[4])));
			lastMonth=lastMonth.add((BigDecimal)obj[1]);
			currentmonth=currentmonth.add((BigDecimal)obj[2]);
			rollingDays=rollingDays.add((BigDecimal)obj[3]);
			plannedsales=plannedsales.add((BigDecimal)obj[4]);
			totalAmouts.add(vo);
		}
	}
		
			response.setSuperRegion("APAC");
			response.setCurrentForecast(DataTypeConvert.get$StringFromNumers(currentmonth));
			response.setLastMonthForecast(DataTypeConvert.get$StringFromNumers(lastMonth));
			response.setPlannedSalesAmt(DataTypeConvert.get$StringFromNumers(plannedsales));
			response.setRollingTotal(DataTypeConvert.get$StringFromNumers(rollingDays));
			
			
			response.setListData(totalAmouts);
			
		
		
	
	return response;
	//return loginDao.getUserDetailsByGlobalId(globalId);
	}

private List<LoginSumVO> getRemainingTotals() {
	List<LoginSumVO> childData=new ArrayList<LoginSumVO>();
	LoginSumVO china=new LoginSumVO("China","N/A", "N/A","N/A", "N/A", "N/A", false);
	LoginSumVO nea=new LoginSumVO("North East Asia","N/A", "N/A","N/A", "N/A", "N/A", false);
	LoginSumVO pacific=new LoginSumVO("Pacific","N/A", "N/A","N/A", "N/A", "N/A", false);
	LoginSumVO india=new LoginSumVO("India","N/A", "N/A","N/A", "N/A", "N/A", false);
	LoginSumVO sea=new LoginSumVO("South East Asia","N/A", "N/A","N/A", "N/A", "N/A", false);
	LoginSumVO apd=new LoginSumVO("Asia Products and Distribution","N/A", "N/A","N/A", "N/A", "N/A", false);
	
	childData.add(china);
	childData.add(india);
	childData.add(pacific);
	childData.add(nea);
	childData.add(sea);
	childData.add(apd);
	
	// TODO Auto-generated method stub
	return childData;
}

public LoginUserDetailsVO getNonAdminHQ(List<UserRoleVO> userRoles,String globalId){
System.out.println("****** getNonAdminHQ service called******");
	 LoginUserDetailsVO response=new LoginUserDetailsVO();
	 List<LoginSumVO> totalAmouts=new ArrayList<LoginSumVO>();
	 Set<String> region=new HashSet<String>();
	 Set<String> country=new HashSet<String>();
	 Set<String> subregion=new HashSet<String>();
	 Set<String> branch=new HashSet<String>();
	 StringBuffer sb=new StringBuffer(" where 1=1");
	 BigDecimal currentMonthForecast=new BigDecimal("0");
	 BigDecimal lastMonthForecast=new BigDecimal("0");
	 BigDecimal totalPlan=new BigDecimal("0");
	 BigDecimal totalRoledays=new BigDecimal("0");
	 for(UserRoleVO userRoleVO:userRoles)
	   {
		 country.add(userRoleVO.getCountry());
	   }
	 for(UserRoleVO userRoleVO:userRoles)
	   {
		 region.add(userRoleVO.getRegion());
	   }
	 for(UserRoleVO userRoleVO:userRoles)
	   {
		 if(!("NA".equalsIgnoreCase(userRoleVO.getSubregion())))
		 subregion.add(userRoleVO.getSubregion());
	   }
	 for(UserRoleVO userRoleVO:userRoles)
	   {
		 branch.add(userRoleVO.getBranch());
	   }
	
	 String countries=GetStringFromList.getStringFromSetComma(country);
	 String regions=GetStringFromList.getStringFromSetComma(region);
	 String subregions=GetStringFromList.getStringFromSetComma(subregion);
	 String branches=GetStringFromList.getStringFromSetComma(branch);
		if("Regional Forecaster".equalsIgnoreCase(userRoles.get(0).getRoleName().trim())){
			sb=sb.append(" and  region in "+regions);
		}
		if("Country Forecaster".equalsIgnoreCase(userRoles.get(0).getRoleName().trim())){
			sb=sb.append(" and  region in "+regions);
			sb=sb.append(" and jci_reporting_country in "+countries);
		}
		if("Sub Region Forecaster".equalsIgnoreCase(userRoles.get(0).getRoleName().trim())){
			sb=sb.append(" and  region in "+regions);
			//sb=sb.append(" and jci_reporting_country in "+countries);
			sb=sb.append(" and sub_region in "+subregions);
		}
		if("Branch Forecaster".equalsIgnoreCase(userRoles.get(0).getRoleName().trim())){
			sb=sb.append(" and  region in "+regions);
			if("China".equalsIgnoreCase(userRoles.get(0).getRegion())){
				sb=sb.append(" and sub_region in "+subregions);
			}
			else{
				sb=sb.append(" and jci_reporting_country in "+countries);
			}
			sb=sb.append(" and branch_code in "+branches);
		}
	 List<Object[]> objects=loginDao.getLandingPageTotals(sb.toString(),globalId);
	 for(Object[] obj:objects){
		LoginSumVO vo=new LoginSumVO();
		if(obj[0]!=null){
		
			vo.setName((String)obj[0]);
			vo.setIsClickable(true);
			vo.setLastMonth(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[1]));
			lastMonthForecast=lastMonthForecast.add(new BigDecimal(obj[1].toString()));
			vo.setCurrentMonth(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[2]));
			currentMonthForecast=currentMonthForecast.add(new BigDecimal(obj[2].toString()));
			vo.setRollingDays(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[3]));
			totalRoledays=totalRoledays.add(new BigDecimal(obj[3].toString()));
			vo.setPlannedSale(DataTypeConvert.get$StringFromNumers((BigDecimal)obj[4]));
			totalPlan=totalPlan.add(new BigDecimal(obj[4].toString()));
		}
		totalAmouts.add(vo);
	}
	 List<LoginSumVO> finalTotalAmouts=getRemainingTotals();
	  for(LoginSumVO vos:totalAmouts){
		 finalTotalAmouts.remove(vos);
	 }
	 finalTotalAmouts.addAll(totalAmouts);
	 
	 
	 response.setCurrentForecast(DataTypeConvert.get$StringFromNumers(currentMonthForecast));
	 response.setLastMonthForecast(DataTypeConvert.get$StringFromNumers(lastMonthForecast));
	 response.setPlannedSalesAmt(DataTypeConvert.get$StringFromNumers(totalPlan));
	 response.setRoleValue(countries);
	 response.setRollingTotal(DataTypeConvert.get$StringFromNumers(totalRoledays));
	 response.setSuperRegion("APAC");
	 response.setSuperRegionId("");
	 response.setListData(finalTotalAmouts);
		return response;
		//return loginDao.getUserDetailsByGlobalId(globalId);
		
}

public String getOldPasswordByGlobalId(String globalId) {
	// TODO Auto-generated method stub
	String oldPassword=loginDao.getOldPasswordByGlobalId(globalId);
	
	return null;
}

public String saveNewPasswordByGlobalId(String globalId) {
	// TODO Auto-generated method stub
	return null;
}

public String changePasswordByGlobalId(ChangePasswordInput input) {
	// TODO Auto-generated method stub
	String oldPasswrd=loginDao.getOldPasswordByGlobalId(input.getGlobalId());
	
	if(oldPasswrd==null|| oldPasswrd=="" ){
		return "FAILURE";
	}
	else if(oldPasswrd.equals(input.getOldPassword()))
	{
		if("Y".equalsIgnoreCase(loginDao.saveNewPasswordByGlobalId(input))){
			return "SUCCESS";
		}
		else{
			return "FAILURE";	
		}
	}
	else{
		return "FAILURE";
	}
}


}
