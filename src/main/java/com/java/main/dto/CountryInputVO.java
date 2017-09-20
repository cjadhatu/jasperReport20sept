/**
 * 
 */
package com.java.main.dto;

import java.util.List;

/**
 * @author ckhan58
 *
 */
public class CountryInputVO {

	
	private List<ValueDTO> regions;
	private List<ValueDTO> countries;
	
	
	
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
