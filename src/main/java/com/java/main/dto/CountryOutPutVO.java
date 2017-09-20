package com.java.main.dto;

import java.util.List;

public class CountryOutPutVO {
	


private List<RegionDTO> countryData;
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

public List<RegionDTO> getCountryData() {
	return countryData;
}

public void setCountryData(List<RegionDTO> countryData) {
	this.countryData = countryData;
}

public DynamicSummaryDTO getDynamicSummaryDTO() {
	return dynamicSummaryDTO;
}

public void setDynamicSummaryDTO(DynamicSummaryDTO dynamicSummaryDTO) {
	this.dynamicSummaryDTO = dynamicSummaryDTO;
}


}
