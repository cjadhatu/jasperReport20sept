package com.java.main.dto;

import java.util.List;

public class AddNewOpportunity {
	
	
    private String selectedSourceType;
    private String selectedregion;
    private String selectedsubRegion;
    private String selectedcountry;
    private String selectedbranch;
    private String sfid;
    private String globalid;
    private String userid;
    private String user_name;
    private String manager_name;
    private String forecast_rolecode;
    private String lob;
    private String OpportunityNumber="OPP-TEST";
	private List<ValueDTO> sourceType;
	private List<ValueDTO> region;
	private List<ValueDTO> subRegion;
	private List<ValueDTO> country;
	private List<ValueDTO> branch;
	private List<ValueDTO> accountName;
	private ReportOnLoadDTO getlob;
	private ReportOnLoadDTO getCurrency;
	private ReportOnLoadDTO stageName;
	
	
	public String getSelectedSourceType() {
		return selectedSourceType;
	}
	public void setSelectedSourceType(String selectedSourceType) {
		this.selectedSourceType = selectedSourceType;
	}
	public String getSelectedregion() {
		return selectedregion;
	}
	public void setSelectedregion(String selectedregion) {
		this.selectedregion = selectedregion;
	}
	public String getSelectedsubRegion() {
		return selectedsubRegion;
	}
	public void setSelectedsubRegion(String selectedsubRegion) {
		this.selectedsubRegion = selectedsubRegion;
	}
	public String getSelectedcountry() {
		return selectedcountry;
	}
	public void setSelectedcountry(String selectedcountry) {
		this.selectedcountry = selectedcountry;
	}
	public String getSelectedbranch() {
		return selectedbranch;
	}
	public void setSelectedbranch(String selectedbranch) {
		this.selectedbranch = selectedbranch;
	}
	public String getSfid() {
		return sfid;
	}
	public void setSfid(String sfid) {
		this.sfid = sfid;
	}
	public String getGlobalid() {
		return globalid;
	}
	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getForecast_rolecode() {
		return forecast_rolecode;
	}
	public void setForecast_rolecode(String forecast_rolecode) {
		this.forecast_rolecode = forecast_rolecode;
	}
	public String getLob() {
		return lob;
	}
	public void setLob(String lob) {
		this.lob = lob;
	}
	public List<ValueDTO> getSourceType() {
		return sourceType;
	}
	public void setSourceType(List<ValueDTO> sourceType) {
		this.sourceType = sourceType;
	}
	public List<ValueDTO> getRegion() {
		return region;
	}
	public void setRegion(List<ValueDTO> region) {
		this.region = region;
	}
	public List<ValueDTO> getSubRegion() {
		return subRegion;
	}
	public void setSubRegion(List<ValueDTO> subRegion) {
		this.subRegion = subRegion;
	}
	public List<ValueDTO> getCountry() {
		return country;
	}
	public void setCountry(List<ValueDTO> country) {
		this.country = country;
	}
	public List<ValueDTO> getBranch() {
		return branch;
	}
	public void setBranch(List<ValueDTO> branch) {
		this.branch = branch;
	}
	public List<ValueDTO> getAccountName() {
		return accountName;
	}
	public void setAccountName(List<ValueDTO> accountName) {
		this.accountName = accountName;
	}
	public ReportOnLoadDTO getGetlob() {
		return getlob;
	}
	public void setGetlob(ReportOnLoadDTO getlob) {
		this.getlob = getlob;
	}
	public ReportOnLoadDTO getGetCurrency() {
		return getCurrency;
	}
	public void setGetCurrency(ReportOnLoadDTO getCurrency) {
		this.getCurrency = getCurrency;
	}
	public ReportOnLoadDTO getStageName() {
		return stageName;
	}
	public void setStageName(ReportOnLoadDTO stageName) {
		this.stageName = stageName;
	}
	public String getOpportunityNumber() {
		return OpportunityNumber;
	}
	public void setOpportunityNumber(String opportunityNumber) {
		OpportunityNumber = opportunityNumber;
	}
	
	
	
	

}
