package com.java.main.dto;

import java.util.List;

public class ReportGridInputMultipleVO {
//ValueDTO
	private List<ValueDTO> typeSources;
	private List<ValueDTO> regions;
	private List<ValueDTO> countries;
	private List<ValueDTO> subregions;
	private List<ValueDTO> branches;
	private List<ValueDTO> lobs;
	
	
	public List<ValueDTO> getLobs() {
		return lobs;
	}
	public void setLobs(List<ValueDTO> lobs) {
		this.lobs = lobs;
	}
	public List<ValueDTO> getTypeSources() {
		return typeSources;
	}
	public void setTypeSources(List<ValueDTO> typeSources) {
		this.typeSources = typeSources;
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
	public List<ValueDTO> getBranches() {
		return branches;
	}
	public void setBranches(List<ValueDTO> branches) {
		this.branches = branches;
	}
	
}
