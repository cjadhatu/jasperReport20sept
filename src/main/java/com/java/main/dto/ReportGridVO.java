package com.java.main.dto;

public class ReportGridVO {
	private String region;
	private String subRegion;
	private String country;
	private String branch;
	private String opportunityName;
	private String leadSource;
	private String unfactoredAmount;
	private String accountName;
	private String forecastCloseDate;
	private String Stage;
	private String probability;
	private String grossMargin;
	private String factoredAmount;
	private String salesLead;
	private String salesManager;
	private String sourceType;
	private String currencyCode;
	
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	//newly add column 
	private String  convertedFactoredAmt;
	private String  convertedunFactoredAmt;
	
	public String getConvertedFactoredAmt() {
		return convertedFactoredAmt;
	}
	public void setConvertedFactoredAmt(String convertedFactoredAmt) {
		this.convertedFactoredAmt = convertedFactoredAmt;
	}
	public String getConvertedunFactoredAmt() {
		return convertedunFactoredAmt;
	}
	public void setConvertedunFactoredAmt(String convertedunFactoredAmt) {
		this.convertedunFactoredAmt = convertedunFactoredAmt;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getSubRegion() {
		return subRegion;
	}
	public void setSubRegion(String subRegion) {
		this.subRegion = subRegion;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getOpportunityName() {
		return opportunityName;
	}
	public void setOpportunityName(String opportunityName) {
		this.opportunityName = opportunityName;
	}
	public String getLeadSource() {
		return leadSource;
	}
	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}
	public String getUnfactoredAmount() {
		return unfactoredAmount;
	}
	public void setUnfactoredAmount(String unfactoredAmount) {
		this.unfactoredAmount = unfactoredAmount;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getForecastCloseDate() {
		return forecastCloseDate;
	}
	public void setForecastCloseDate(String forecastCloseDate) {
		this.forecastCloseDate = forecastCloseDate;
	}
	public String getStage() {
		return Stage;
	}
	public void setStage(String stage) {
		Stage = stage;
	}
	public String getProbability() {
		return probability;
	}
	public void setProbability(String probability) {
		this.probability = probability;
	}
	public String getGrossMargin() {
		return grossMargin;
	}
	public void setGrossMargin(String grossMargin) {
		this.grossMargin = grossMargin;
	}
	public String getFactoredAmount() {
		return factoredAmount;
	}
	public void setFactoredAmount(String factoredAmount) {
		this.factoredAmount = factoredAmount;
	}
	public String getSalesLead() {
		return salesLead;
	}
	public void setSalesLead(String salesLead) {
		this.salesLead = salesLead;
	}
	public String getSalesManager() {
		return salesManager;
	}
	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}
	
	
	
	
	
	
	
	

}
