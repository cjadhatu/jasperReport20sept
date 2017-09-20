package com.java.main.dto;

import java.util.List;

public class LoginUserDetailsVO {
	
	private List<LoginSumVO> listData;
private String role;
private String userName;
private String roleValue;
private String superRegion;
private String superRegionId;
private String lastMonthForecast;
private String currentForecast;
private String rollingTotal;
private String plannedSalesAmt;
private String forecastePeriod;
private String isValidUser="Y";
//new fields

private String userRegion;
private String userSubRegion;
private String userCountry;
private String userBranch;

//New fields for APAC
private String isApac="N";
private String globalId;

//private String ispasswordValid="Y";




/*public String getIspasswordValid() {
	return ispasswordValid;
}
public void setIspasswordValid(String ispasswordValid) {
	this.ispasswordValid = ispasswordValid;
}
*/
public String getGlobalId() {
	return globalId;
}
public void setGlobalId(String globalId) {
	this.globalId = globalId;
}
public String getIsApac() {
	return isApac;
}
public void setIsApac(String isApac) {
	this.isApac = isApac;
}
public String getUserRegion() {
	return userRegion;
}
public void setUserRegion(String userRegion) {
	this.userRegion = userRegion;
}
public String getUserSubRegion() {
	return userSubRegion;
}
public void setUserSubRegion(String userSubRegion) {
	this.userSubRegion = userSubRegion;
}
public String getUserCountry() {
	return userCountry;
}
public void setUserCountry(String userCountry) {
	this.userCountry = userCountry;
}
public String getUserBranch() {
	return userBranch;
}
public void setUserBranch(String userBranch) {
	this.userBranch = userBranch;
}
public String getIsValidUser() {
	return isValidUser;
}
public void setIsValidUser(String isValidUser) {
	this.isValidUser = isValidUser;
}
public String getForecastePeriod() {
	return forecastePeriod;
}
public void setForecastePeriod(String forecastePeriod) {
	this.forecastePeriod = forecastePeriod;
}
public List<LoginSumVO> getListData() {
	return listData;
}
public void setListData(List<LoginSumVO> listData) {
	this.listData = listData;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getRoleValue() {
	return roleValue;
}
public void setRoleValue(String roleValue) {
	this.roleValue = roleValue;
}
public String getSuperRegion() {
	return superRegion;
}
public void setSuperRegion(String superRegion) {
	this.superRegion = superRegion;
}
public String getSuperRegionId() {
	return superRegionId;
}
public void setSuperRegionId(String superRegionId) {
	this.superRegionId = superRegionId;
}
public String getLastMonthForecast() {
	return lastMonthForecast;
}
public void setLastMonthForecast(String lastMonthForecast) {
	this.lastMonthForecast = lastMonthForecast;
}
public String getCurrentForecast() {
	return currentForecast;
}
public void setCurrentForecast(String currentForecast) {
	this.currentForecast = currentForecast;
}
public String getRollingTotal() {
	return rollingTotal;
}
public void setRollingTotal(String rollingTotal) {
	this.rollingTotal = rollingTotal;
}
public String getPlannedSalesAmt() {
	return plannedSalesAmt;
}
public void setPlannedSalesAmt(String plannedSalesAmt) {
	this.plannedSalesAmt = plannedSalesAmt;
}


}
