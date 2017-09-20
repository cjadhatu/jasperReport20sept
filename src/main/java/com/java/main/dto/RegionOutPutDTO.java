package com.java.main.dto;

import java.util.List;

public class RegionOutPutDTO {
	


private List<RegionDTO> regionData;
private String totalOpportunites;
private String totalfactAmt;
private String totalUnfactAmt;
private String totalCountries;
private String totalSubRegions;
private String totalBranches;
private DynamicSummaryDTO dynamicSummaryDTO;

//Added new field for Locking permission

	private Boolean isSubmitClickable=true;
	
	private Boolean submitAllowed=true;
	
	
	

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
public RegionOutPutDTO() {
	super();
	// TODO Auto-generated constructor stub
}
public RegionOutPutDTO(List<RegionDTO> regionData, String totalOpportunites,
		String totalfactAmt, String totalUnfactAmt, String totalCountries,
		String totalSubRegions, String totalBranches,
		DynamicSummaryDTO dynamicSummaryDTO) {
	super();
	this.regionData = regionData;
	this.totalOpportunites = totalOpportunites;
	this.totalfactAmt = totalfactAmt;
	this.totalUnfactAmt = totalUnfactAmt;
	this.totalCountries = totalCountries;
	this.totalSubRegions = totalSubRegions;
	this.totalBranches = totalBranches;
	this.dynamicSummaryDTO = dynamicSummaryDTO;
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
public String getTotalCountries() {
	return totalCountries;
}
public void setTotalCountries(String totalCountries) {
	this.totalCountries = totalCountries;
}
public String getTotalSubRegions() {
	return totalSubRegions;
}
public void setTotalSubRegions(String totalSubRegions) {
	this.totalSubRegions = totalSubRegions;
}
public String getTotalBranches() {
	return totalBranches;
}
public void setTotalBranches(String totalBranches) {
	this.totalBranches = totalBranches;
}
public List<RegionDTO> getRegionData() {
	return regionData;
}
public void setRegionData(List<RegionDTO> regionData) {
	this.regionData = regionData;
}
public DynamicSummaryDTO getDynamicSummaryDTO() {
	return dynamicSummaryDTO;
}
public void setDynamicSummaryDTO(DynamicSummaryDTO dynamicSummaryDTO) {
	this.dynamicSummaryDTO = dynamicSummaryDTO;
}


}
