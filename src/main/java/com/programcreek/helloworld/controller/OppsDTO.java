package com.programcreek.helloworld.controller;

import java.util.List;

public class OppsDTO {

	private Integer oppsTotal;
	private Integer brTotal;
	
	private List<Opps> subRegions;

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

	public List<Opps> getSubRegions() {
		return subRegions;
	}

	public void setSubRegions(List<Opps> subRegions) {
		this.subRegions = subRegions;
	}
	
	
	
}
