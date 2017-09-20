package com.java.main.dto;

import java.util.List;

public class BranchOutPutDTO {
	
	
	private List<BranchDTO> BranchData;
	private String totalOpportunites;
	private String totalfactAmt;
	private String totalUnfactAmt;
	private String branchNotStarted;
	private String branchInProgress;
	private String totalBranches;
	private DynamicSummaryDTO dynamicSummaryDTO;
	//Added new field for Locking permission
	
	private Boolean isSubmitClickable=true;
	
	private Boolean submitAllowed=false;
	
	
	
	public Boolean getSubmitAllowed() {
		return submitAllowed;
	}
	public void setSubmitAllowed(Boolean submitAllowed) {
		this.submitAllowed = submitAllowed;
	}
	public Boolean getIsSubmitClickable() {
		return isSubmitClickable;
	}
	public void setIsSubmitClickable(Boolean isSubmitClickable) {
		this.isSubmitClickable = isSubmitClickable;
	}
	public List<BranchDTO> getBranchData() {
		return BranchData;
	}
	public void setBranchData(List<BranchDTO> branchData) {
		BranchData = branchData;
	}
	public String getTotalOpportunites() {
		return totalOpportunites;
	}
	public void setTotalOpportunites(String totalOpportunites) {
		this.totalOpportunites = totalOpportunites;
	}
	public String getTotalfactAmt() {
		return totalfactAmt;
	}
	public void setTotalfactAmt(String totalfactAmt) {
		this.totalfactAmt = totalfactAmt;
	}
	public String getTotalUnfactAmt() {
		return totalUnfactAmt;
	}
	public void setTotalUnfactAmt(String totalUnfactAmt) {
		this.totalUnfactAmt = totalUnfactAmt;
	}
	public String getBranchNotStarted() {
		return branchNotStarted;
	}
	public void setBranchNotStarted(String branchNotStarted) {
		this.branchNotStarted = branchNotStarted;
	}
	public String getBranchInProgress() {
		return branchInProgress;
	}
	public void setBranchInProgress(String branchInProgress) {
		this.branchInProgress = branchInProgress;
	}
	public String getTotalBranches() {
		return totalBranches;
	}
	public void setTotalBranches(String totalBranches) {
		this.totalBranches = totalBranches;
	}
	public DynamicSummaryDTO getDynamicSummaryDTO() {
		return dynamicSummaryDTO;
	}
	public void setDynamicSummaryDTO(DynamicSummaryDTO dynamicSummaryDTO) {
		this.dynamicSummaryDTO = dynamicSummaryDTO;
	}
	
	
	
	

}
