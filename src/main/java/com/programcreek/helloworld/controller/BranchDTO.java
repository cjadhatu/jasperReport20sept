package com.programcreek.helloworld.controller;

import java.util.List;

public class BranchDTO {

	private Integer oppsTotal;
	private Integer brTotal;
	
	private List<Branches> subRegions;

	public Integer getOppsTotal() {
		return oppsTotal;
	}

	public void setOppsTotal(Integer oppsTotal) {
		this.oppsTotal = oppsTotal;
	}

	public Integer getBrTotal() {
		return brTotal;
	}

	public void setBrTotal(Integer brTotal) {
		this.brTotal = brTotal;
	}

	public List<Branches> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<Branches> subRegions) {
		this.subRegions = subRegions;
	}
	
	
}
