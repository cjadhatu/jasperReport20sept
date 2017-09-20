package com.java.main.dto;

import java.util.List;

public class ReportOnLoadDTO {
private List<DropDownVO> dropdownValues;
private List<DropDownVO> countriesChina;


public List<DropDownVO> getCountriesChina() {
	return countriesChina;
}

public void setCountriesChina(List<DropDownVO> countriesChina) {
	this.countriesChina = countriesChina;
}

public List<DropDownVO> getDropdownValues() {
	return dropdownValues;
}

public void setDropdownValues(List<DropDownVO> dropdownValues) {
	this.dropdownValues = dropdownValues;
}

}
