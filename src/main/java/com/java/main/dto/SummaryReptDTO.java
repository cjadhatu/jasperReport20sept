package com.java.main.dto;

import java.math.BigDecimal;

public class SummaryReptDTO {
	private Long region_count;
	private Long subregion_count;
	private Long country;
	private Long branch_count;
	private BigDecimal opp_count;
	private BigDecimal total_factored_amount;
	private BigDecimal total_unfactored_amount;
	private BigDecimal total_factored_amount_usd;
	private BigDecimal total_unfactored_amount_usd;
	public Long getRegion_count() {
		return region_count;
	}
	public void setRegion_count(Long region_count) {
		this.region_count = region_count;
	}
	public Long getSubregion_count() {
		return subregion_count;
	}
	public void setSubregion_count(Long subregion_count) {
		this.subregion_count = subregion_count;
	}
	public Long getCountry() {
		return country;
	}
	public void setCountry(Long country) {
		this.country = country;
	}
	public Long getBranch_count() {
		return branch_count;
	}
	public void setBranch_count(Long branch_count) {
		this.branch_count = branch_count;
	}
	public BigDecimal getOpp_count() {
		return opp_count;
	}
	public void setOpp_count(BigDecimal opp_count) {
		this.opp_count = opp_count;
	}
	public BigDecimal getTotal_factored_amount() {
		return total_factored_amount;
	}
	public void setTotal_factored_amount(BigDecimal total_factored_amount) {
		this.total_factored_amount = total_factored_amount;
	}
	public BigDecimal getTotal_unfactored_amount() {
		return total_unfactored_amount;
	}
	public void setTotal_unfactored_amount(BigDecimal total_unfactored_amount) {
		this.total_unfactored_amount = total_unfactored_amount;
	}
	public BigDecimal getTotal_factored_amount_usd() {
		return total_factored_amount_usd;
	}
	public void setTotal_factored_amount_usd(BigDecimal total_factored_amount_usd) {
		this.total_factored_amount_usd = total_factored_amount_usd;
	}
	public BigDecimal getTotal_unfactored_amount_usd() {
		return total_unfactored_amount_usd;
	}
	public void setTotal_unfactored_amount_usd(
			BigDecimal total_unfactored_amount_usd) {
		this.total_unfactored_amount_usd = total_unfactored_amount_usd;
	}
	
	
	
}
