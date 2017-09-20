package com.java.main.dto;

import java.math.BigDecimal;

public class SubmissionHistoryTraReportDTO {
	
	private String submitId;
	private String forecastLevel;
	private String forecastGeoName;
	private String submittedBy;
	private String submittedDate;
	private BigDecimal factoredAmountC;
	private BigDecimal unfactoredAmountC;
	
	public String getSubmitId() {
		return submitId;
	}
	public void setSubmitId(String submitId) {
		this.submitId = submitId;
	}
	public String getForecastLevel() {
		return forecastLevel;
	}
	public void setForecastLevel(String forecastLevel) {
		this.forecastLevel = forecastLevel;
	}
	public String getForecastGeoName() {
		return forecastGeoName;
	}
	public void setForecastGeoName(String forecastGeoName) {
		this.forecastGeoName = forecastGeoName;
	}
	public String getSubmittedBy() {
		return submittedBy;
	}
	public void setSubmittedBy(String submittedBy) {
		this.submittedBy = submittedBy;
	}
	public String getSubmittedDate() {
		return submittedDate;
	}
	public void setSubmittedDate(String submittedDate) {
		this.submittedDate = submittedDate;
	}
	public BigDecimal getFactoredAmountC() {
		return factoredAmountC;
	}
	public void setFactoredAmountC(BigDecimal factoredAmountC) {
		this.factoredAmountC = factoredAmountC;
	}
	public BigDecimal getUnfactoredAmountC() {
		return unfactoredAmountC;
	}
	public void setUnfactoredAmountC(BigDecimal unfactoredAmountC) {
		this.unfactoredAmountC = unfactoredAmountC;
	}
	
	
	
	
	
	

}
