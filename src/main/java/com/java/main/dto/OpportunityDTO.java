package com.java.main.dto;

import java.math.BigDecimal;

public class OpportunityDTO {

	private BigDecimal oppId;
	private String accName;
	private String oppName;
	private String planDate;
	/*private BigDecimal estAmt;
	private BigDecimal factAmt;
	private BigDecimal unfactAmt;*/
	private String factAmt="$0.0";
	private String unfactAmt="$0.0";
	private String margin;
	private String stage;
	private String isRemoved = "N";
	private String isNew = "N";
	private String oppNumber;
	private String probability;
	private String salesLeadRep;
	//new fields 19 April
	private String branch;
	private String lob;
	private String currency;
	private String factAmtLocalCur="0.0";
	private String unfactAmtLocalCur="0.0";
	private String salesManager;
	private String leadsource;
	private String mustWinflag;
	private String oppStatus;
	private String oppSubmitStatus;	
	
	// this id reefers to BFC=B12345,CFC=C12345,SRFC=S12345,RFC=R12345,HQ=H12345
	private String forecastId;
	//add new field to calculate USD amt on basis of source types
	
	private String sourceType;
	//add new field to redirect user when click on Opp_Number START
	private String sfdcUrl;
	private String sfdcId;
	
	//add new field to redirect user when click on Opp_Number END
	
	//add new variable 10 May 2017 for new value on forecast screen to make on-clikcable  href on manually added opps
		private Boolean isRedirect=false;
		
	//add new variable 1 Jul 
		private String country;
		private String region;
		private String subRegion;
		
		
	
	public String getRegion() {
			return region;
		}

		public void setRegion(String region) {
			this.region = region;
		}

		public String getSubRegion() {
			return subRegion;
		}

		public void setSubRegion(String subRegion) {
			this.subRegion = subRegion;
		}

	public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

	public Boolean getIsRedirect() {
			return isRedirect;
		}

		public void setIsRedirect(Boolean isRedirect) {
			this.isRedirect = isRedirect;
		}

	public String getSfdcUrl() {
		return sfdcUrl;
	}

	public String getSfdcId() {
		return sfdcId;
	}

	public void setSfdcId(String sfdcId) {
		this.sfdcId = sfdcId;
	}

	public void setSfdcUrl(String sfdcUrl) {
		this.sfdcUrl = sfdcUrl;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getForecastId() {
		return forecastId;
	}

	public void setForecastId(String forecastId) {
		this.forecastId = forecastId;
	}

	public String getOppSubmitStatus() {
		return oppSubmitStatus;
	}

	public void setOppSubmitStatus(String oppSubmitStatus) {
		this.oppSubmitStatus = oppSubmitStatus;
	}

	public String getOppStatus() {
		return oppStatus;
	}

	public void setOppStatus(String oppStatus) {
		this.oppStatus = oppStatus;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getLeadsource() {
		return leadsource;
	}

	public void setLeadsource(String leadsource) {
		this.leadsource = leadsource;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	

	public String getUnfactAmtLocalCur() {
		return unfactAmtLocalCur;
	}

	public void setUnfactAmtLocalCur(String unfactAmtLocalCur) {
		this.unfactAmtLocalCur = unfactAmtLocalCur;
	}

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	
	public String getMustWinflag() {
		return mustWinflag;
	}

	public void setMustWinflag(String mustWinflag) {
		this.mustWinflag = mustWinflag;
	}

	public String getFactAmtLocalCur() {
		return factAmtLocalCur;
	}

	public void setFactAmtLocalCur(String factAmtLocalCur) {
		this.factAmtLocalCur = factAmtLocalCur;
	}

	public String getSalesLeadRep() {
		return salesLeadRep;
	}

	public void setSalesLeadRep(String salesLeadRep) {
		this.salesLeadRep = salesLeadRep;
	}

	public String getOppNumber() {
		return oppNumber;
	}

	public void setOppNumber(String oppNumber) {
		this.oppNumber = oppNumber;
	}

	public String getProbability() {
		return probability;
	}

	public void setProbability(String probability) {
		this.probability = probability;
	}

	public BigDecimal getOppId() {
		return oppId;
	}

	public void setOppId(BigDecimal oppId) {
		this.oppId = oppId;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	public String getPlanDate() {
		return planDate;
	}

	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}

	/*public BigDecimal getEstAmt() {
		return estAmt;
	}

	public void setEstAmt(BigDecimal estAmt) {
		this.estAmt = estAmt;
	}

	public BigDecimal getFactAmt() {
		return factAmt;
	}

	public void setFactAmt(BigDecimal factAmt) {
		this.factAmt = factAmt;
	}

	public BigDecimal getUnfactAmt() {
		return unfactAmt;
	}

	public void setUnfactAmt(BigDecimal unfactAmt) {
		this.unfactAmt = unfactAmt;
	}*/
	

	public String getMargin() {
		return margin;
	}

	
	public String getFactAmt() {
		return factAmt;
	}

	public void setFactAmt(String factAmt) {
		this.factAmt = factAmt;
	}

	public String getUnfactAmt() {
		return unfactAmt;
	}

	public void setUnfactAmt(String unfactAmt) {
		this.unfactAmt = unfactAmt;
	}

	public void setMargin(String margin) {
		this.margin = margin;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public String getIsRemoved() {
		return isRemoved;
	}

	public void setIsRemoved(String isRemoved) {
		this.isRemoved = isRemoved;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}

}
