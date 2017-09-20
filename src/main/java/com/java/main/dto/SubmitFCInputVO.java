package com.java.main.dto;

import java.util.List;

public class SubmitFCInputVO {
private List<ValueDTO> regions;
private List<ValueDTO> countries;
private List<ValueDTO> subregions;
private List<ValueDTO> branches;
private List<OpportunityDTO> oppData;


public List<OpportunityDTO> getOppData() {
	return oppData;
}
public void setOppData(List<OpportunityDTO> oppData) {
	this.oppData = oppData;
}
public List<ValueDTO> getBranches() {
	return branches;
}
public void setBranches(List<ValueDTO> branches) {
	this.branches = branches;
}
public List<ValueDTO> getRegions() {
	return regions;
}
public void setRegions(List<ValueDTO> regions) {
	this.regions = regions;
}
public List<ValueDTO> getCountries() {
	return countries;
}
public void setCountries(List<ValueDTO> countries) {
	this.countries = countries;
}
public List<ValueDTO> getSubregions() {
	return subregions;
}
public void setSubregions(List<ValueDTO> subregions) {
	this.subregions = subregions;
}


}
