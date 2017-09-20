package com.java.main.dto;

import java.util.List;

public class DynamicReportDataDTO {
	
	private List<ValueDTO> sourceType;
	private List<ValueDTO> region;
	private List<ValueDTO> subRegion;
	private List<ValueDTO> country;
	private List<ValueDTO> branch;
	private List<ValueDTO> lob;
	
	 private String selectedSourceType;
	 private String selectedregion;
	 private String selectedsubRegion;
	 private String selectedcountry;
	 private String selectedbranch;
	
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
	public List<ValueDTO> getLob() {
		return lob;
	}
	public void setLob(List<ValueDTO> lob) {
		this.lob = lob;
	}
	
	
	

}
