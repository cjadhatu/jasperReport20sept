/**
 * 
 */
package com.java.main.dto;

import java.util.List;

/**
 * @author ckhan58
 *
 */
public class SubRegionInputVO {

	
	private List<ValueDTO> regions;
	private List<ValueDTO> countries;
	private List<ValueDTO> subRegions;
	
	
	public List<ValueDTO> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<ValueDTO> subRegions) {
		this.subRegions = subRegions;
	}

	public List<ValueDTO> getCountries() {
		return countries;
	}

	public void setCountries(List<ValueDTO> countries) {
		this.countries = countries;
	}

	public List<ValueDTO> getRegions() {
		return regions;
	}

	public void setRegions(List<ValueDTO> regions) {
		this.regions = regions;
	}
	
}
