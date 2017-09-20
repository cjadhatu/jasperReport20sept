package com.java.main.dto;

import java.util.List;

public class BranchInputVO {
	
	private List<ValueDTO> regions;
	private List<ValueDTO> subregions;
	private List<ValueDTO> countries;
	private List<ValueDTO> branches;
	
	public List<ValueDTO> getRegions() {
		return regions;
	}
	public void setRegions(List<ValueDTO> regions) {
		this.regions = regions;
	}
	public List<ValueDTO> getSubregions() {
		return subregions;
	}
	public void setSubregions(List<ValueDTO> subregions) {
		this.subregions = subregions;
	}
	public List<ValueDTO> getCountries() {
		return countries;
	}
	public void setCountries(List<ValueDTO> countries) {
		this.countries = countries;
	}
	public List<ValueDTO> getBranches() {
		return branches;
	}
	public void setBranches(List<ValueDTO> branches) {
		this.branches = branches;
	}
	
	
	
	
	
	

}
