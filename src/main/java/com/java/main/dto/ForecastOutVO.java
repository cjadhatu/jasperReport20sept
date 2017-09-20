package com.java.main.dto;

import java.util.List;

public class ForecastOutVO {
	
	private List<OpportunityDTO> oppData;
	private String totalEstAmt="";
	private String totalFactAmt;
	private String totalUnfactAmt;
	private Boolean saved=true;
	//add new variable 3 May 2017 for new value on footer on Forecast screen
	private String totalCurMonthsUnfactAmt;
	private String totalCurMonthsfactAmt;
	
	
	
	private Boolean submitAllowed=false;
	private Boolean lowerSubmit=false;
	
	public Boolean getSaved() {
		return saved;
	}
	public void setSaved(Boolean saved) {
		this.saved = saved;
	}
	public Boolean getSubmitAllowed() {
		return submitAllowed;
	}
	public void setSubmitAllowed(Boolean submitAllowed) {
		this.submitAllowed = submitAllowed;
	}
	public Boolean getLowerSubmit() {
		return lowerSubmit;
	}
	public void setLowerSubmit(Boolean lowerSubmit) {
		this.lowerSubmit = lowerSubmit;
	}
	public String getTotalCurMonthsUnfactAmt() {
		return totalCurMonthsUnfactAmt;
	}
	public void setTotalCurMonthsUnfactAmt(String totalCurMonthsUnfactAmt) {
		this.totalCurMonthsUnfactAmt = totalCurMonthsUnfactAmt;
	}
	public String getTotalCurMonthsfactAmt() {
		return totalCurMonthsfactAmt;
	}
	public void setTotalCurMonthsfactAmt(String totalCurMonthsfactAmt) {
		this.totalCurMonthsfactAmt = totalCurMonthsfactAmt;
	}
	
	public List<OpportunityDTO> getOppData() {
		return oppData;
	}
	public void setOppData(List<OpportunityDTO> oppData) {
		this.oppData = oppData;
	}
	public String getTotalEstAmt() {
		return totalEstAmt;
	}
	public void setTotalEstAmt(String totalEstAmt) {
		this.totalEstAmt = totalEstAmt;
	}
	public String getTotalFactAmt() {
		return totalFactAmt;
	}
	public void setTotalFactAmt(String totalFactAmt) {
		this.totalFactAmt = totalFactAmt;
	}
	public String getTotalUnfactAmt() {
		return totalUnfactAmt;
	}
	public void setTotalUnfactAmt(String totalUnfactAmt) {
		this.totalUnfactAmt = totalUnfactAmt;
	}
		
}
