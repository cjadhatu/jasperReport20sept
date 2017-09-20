package com.java.main.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.java.main.utility.WFTConstant;

@Entity
@Table(name="opportunity_iec" ,schema=WFTConstant.WFTSchema)
public class Opportunity {
	private static final long serialVersionUID = 5518442643343528062L;
	@SequenceGenerator(name = "opp_seq", sequenceName = WFTConstant.WFTSchema+".opportunity_id_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opp_seq")

	
	 @Id
	 @Column(name="opp_id")
	 private BigDecimal oppId;
	
	 @Column(name="opp_data_source")
	 private String oppDataSource;
	 
	 @Column(name="region")
	 private String region;
	 
	 @Column(name="jci_reporting_country")
	 private String jciReportingCountry;
	 
	 @Column(name="sub_region")
	 private String subRegion;
	 
	 @Column(name="branch_code")
	 private String branchCode;
	 
	 @Column(name="lob")
	 private String lob;
	 
	 @Column(name="sub_lob")
	 private String subLob;
	 
	 @Column(name="account_name")
	 private String accountName;
	 
	 @Column(name="opp_number")
	 private String oppNumber;
	
	 
	 @Column(name="opp_name")
	 private String oppName;
	
	 
	 @Column(name="gross_margin")
	 private BigDecimal grossMargin;
	
	 
	 @Column(name="probability")
	 private BigDecimal probability;
	
	 
	 @Column(name="stagename")
	 private String stageName;
	
	 
	 @Column(name="unfactored_amount")
	 private BigDecimal unFactoredAmount;
	
	 @Column(name="factored_amount")
	 private BigDecimal factoredAmount;
	 
	 @Column(name="close_date")
	 private Date closeDate;
	
	 
	 @Column(name="currency")
	 private String currency;
	
	 
	 @Column(name="ownerid")
	 private String ownerId;
	
	 @Column(name="sales_person")
	 private String salesPerson;
	
	 @Column(name="sales_manager")
	 private String salesManager;
	
	 @Column(name="contract_type")
	 private String contractType;
	
	 @Column(name="lead_source")
	 private String leadSource;
	
	 @Column(name="opp_status")
	 private String oppStatus;
	
	 
	 
	 /*
	 //************** Branch ForeCaster Fields START********************
	 @Column(name="bforecast_status")
	 private String bforecastStatus;
	
	 @Column(name="bforecast_date")
	 private Date bforecastDate;
	
	 @Column(name="bforecast_by")
	 private String bforecastBy;
	
	 @Column(name="bsubmit_flag")
	 private String bsubmitflag;
	
	 @Column(name="bsubmit_date")
	 private Date bsubmitdate;
	 
	 @Column(name="bsubmit_timezonekey")
	 private Date bsubmittimezonekey;
	 
	 
	 @Column(name="breforecast_by")
	 private String breforecastBy;
	 
	 @Column(name="bresubmit_date")
	 private Date bresubmitDate;
	 
	 @Column(name="bresubmit_timezonekey")
	 private Date bresubmit_timezonekey;
	//************** Branch ForeCaster Fields END********************
	 /*@Column(name="regforecast_status")
	 private String regforecastStatus;
	 */
	//************** Region  ForeCaster Fields START********************
	 /*@Column(name="regforecast_status")
	 private String regforecastStatus;
	
	 @Column(name="regforecast_date")
	 private Date regionforecastDate;
	
	 @Column(name="regforecast_by")
	 private String regforecastBy;
	
	 @Column(name="regsubmit_flag")
	 private String regsubmitFlag;
	
	 @Column(name="regsubmit_date")
	 private Date regsubmitDate;
	 
	 @Column(name="regsubmit_timezonekey")
	 private Date regsubmitTimezonekey;
	 
	 
	 @Column(name="regreforecast_by")
	 private String regreforecastBy;
	 
	 @Column(name="regresubmit_date")
	 private Date regresubmitDate;
	 
	 @Column(name="regresubmit_timezonekey")
	 private Date regresubmitTimezonekey;
	//************** Region ForeCaster Fields END********************
	 
	//************** HQ  ForeCaster Fields START********************
	 @Column(name="hqforecast_status")
	 private String hqforecastStatus;
	
	 @Column(name="hqforecast_date")
	 private Date hqforecastDate;
	
	 @Column(name="hqforecast_by")
	 private String hqforecastBy;
	
	 @Column(name="hqsubmit_flag")
	 private String hqsubmitFlag;
	
	 @Column(name="hqsubmit_date")
	 private Date hqsubmitDate;
	 
	 @Column(name="hqsubmit_timezonekey")
	 private Date hqsubmitTimezonekey;
	 
	 
	 @Column(name="hqreforecast_by")
	 private String hqreforecastBy;
	 
	 @Column(name="hqresubmit_date")
	 private Date hqresubmitDate;
	 
	 @Column(name="hqresubmit_timezonekey")
	 private Date hqresubmitTimezonekey;*/
	//************** HQ  ForeCaster Fields END********************
	 
	 @Column(name="created_by")
	 private String createdBy;
	 
	 @Column(name="created_date")
	 private Date createdDate;
	 
	 @Column(name="created_timezonekey")
	 private Date createdTimezonekey;
	 
	 @Column(name="modified_by")
	 private String modifiedBy;
	 
	 @Column(name="modified_date")
	 private Date modifiedDate;
	 
	 @Column(name="modified_timezonekey")
	 private Date modifiedTimezonekey;
	 
	 @Column(name="converted_factored_amt")
	 private BigDecimal convertedFactoredAmt;
	 
	 @Column(name="converted_unfactored_amt")
	 private BigDecimal convertedUnfactoredAmt;
	 
	 @Column(name="sfid")
	 private String sfid;
	 
	 
	 
	 @Column(name="rec_status")
	 private String recStatus;
	 
	 //New coloumn added on  25 April
	 //change status on add opps START
	 
	 @Column(name="forecast_status")
	 private String forecastStatus;
	 
	 @Column(name="forecast_date")
	 private Date forecastDate;
	 
	 @Column(name="forecast_by")
	 private String forecastBy;
	 
	//change status on add opps END
	 
	//New coloumn added on  25 April
	//change status on Submit opps START
	 
	 
	//New coloumn added on  25 April
	
	 
	 //forecast_id with append sum code like
	 //Branch Level===B12345  ,Sub-Region===SR1287, Country===C32888 , Region==R39388
	 @Column(name="forecast_id")
	 private String forecastId;
	 
	 
	 @Column(name="submit_by_role")
	 private String submitByRole;
	 
	 @Column(name="submit_flag")
	 private String submitFlag;
	 
	 @Column(name="submit_date")
	 private Date submitDate;
	 
	 @Column(name="submit_timezonekey")
	 private Date submitTimezonekey;
	 
	 @Column(name="forecast_factored_amt")
	 private BigDecimal forecastFactored_amt;
	 
	 
	 @Column(name="forecast_unfactored_amt")
	 private BigDecimal forecastUnfactored_amt;
	 
	 @Column(name="forecast_factored_usdamt")
	 private BigDecimal forecastFactoredUsdamt;
	 
	 @Column(name="forecast_unfactored_usdamt")
	 private BigDecimal forecastUnfactoredUsdamt;
	 
	 
	 @Column(name="opp_data_for_month")
	 private String oppDataForMonth;
	 
	//change status on Submit opps END
	
	 
	 
	 
	public BigDecimal getOppId() {
		return oppId;
	}

	public String getForecastStatus() {
		return forecastStatus;
	}

	public void setForecastStatus(String forecastStatus) {
		this.forecastStatus = forecastStatus;
	}

	public Date getForecastDate() {
		return forecastDate;
	}

	public void setForecastDate(Date forecastDate) {
		this.forecastDate = forecastDate;
	}

	public String getForecastBy() {
		return forecastBy;
	}

	public void setForecastBy(String forecastBy) {
		this.forecastBy = forecastBy;
	}

	public String getSubmitByRole() {
		return submitByRole;
	}

	public void setSubmitByRole(String submitByRole) {
		this.submitByRole = submitByRole;
	}

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public Date getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(Date submitDate) {
		this.submitDate = submitDate;
	}

	public Date getSubmitTimezonekey() {
		return submitTimezonekey;
	}

	public void setSubmitTimezonekey(Date submitTimezonekey) {
		this.submitTimezonekey = submitTimezonekey;
	}

	public BigDecimal getForecastFactored_amt() {
		return forecastFactored_amt;
	}

	public void setForecastFactored_amt(BigDecimal forecastFactored_amt) {
		this.forecastFactored_amt = forecastFactored_amt;
	}

	public BigDecimal getForecastUnfactored_amt() {
		return forecastUnfactored_amt;
	}

	public void setForecastUnfactored_amt(BigDecimal forecastUnfactored_amt) {
		this.forecastUnfactored_amt = forecastUnfactored_amt;
	}

	public BigDecimal getForecastFactoredUsdamt() {
		return forecastFactoredUsdamt;
	}

	public void setForecastFactoredUsdamt(BigDecimal forecastFactoredUsdamt) {
		this.forecastFactoredUsdamt = forecastFactoredUsdamt;
	}

	public BigDecimal getForecastUnfactoredUsdamt() {
		return forecastUnfactoredUsdamt;
	}

	public void setForecastUnfactoredUsdamt(BigDecimal forecastUnfactoredUsdamt) {
		this.forecastUnfactoredUsdamt = forecastUnfactoredUsdamt;
	}

	public void setOppId(BigDecimal oppId) {
		this.oppId = oppId;
	}

	public String getOppDataSource() {
		return oppDataSource;
	}

	public void setOppDataSource(String oppDataSource) {
		this.oppDataSource = oppDataSource;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getJciReportingCountry() {
		return jciReportingCountry;
	}

	public void setJciReportingCountry(String jciReportingCountry) {
		this.jciReportingCountry = jciReportingCountry;
	}

	public String getSubRegion() {
		return subRegion;
	}

	public void setSubRegion(String subRegion) {
		this.subRegion = subRegion;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getSubLob() {
		return subLob;
	}

	public void setSubLob(String subLob) {
		this.subLob = subLob;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getOppNumber() {
		return oppNumber;
	}

	public void setOppNumber(String oppNumber) {
		this.oppNumber = oppNumber;
	}

	public String getOppName() {
		return oppName;
	}

	public void setOppName(String oppName) {
		this.oppName = oppName;
	}

	
	public BigDecimal getGrossMargin() {
		return grossMargin;
	}

	public void setGrossMargin(BigDecimal grossMargin) {
		this.grossMargin = grossMargin;
	}

	public BigDecimal getProbability() {
		return probability;
	}

	public void setProbability(BigDecimal probability) {
		this.probability = probability;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public BigDecimal getUnFactoredAmount() {
		return unFactoredAmount;
	}

	public void setUnFactoredAmount(BigDecimal unFactoredAmount) {
		this.unFactoredAmount = unFactoredAmount;
	}

	public BigDecimal getFactoredAmount() {
		return factoredAmount;
	}

	public void setFactoredAmount(BigDecimal factoredAmount) {
		this.factoredAmount = factoredAmount;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	public String getSalesPerson() {
		return salesPerson;
	}

	public void setSalesPerson(String salesPerson) {
		this.salesPerson = salesPerson;
	}

	public String getSalesManager() {
		return salesManager;
	}

	public void setSalesManager(String salesManager) {
		this.salesManager = salesManager;
	}

	public String getContractType() {
		return contractType;
	}

	public void setContractType(String contractType) {
		this.contractType = contractType;
	}

	public String getLeadSource() {
		return leadSource;
	}

	public void setLeadSource(String leadSource) {
		this.leadSource = leadSource;
	}

	public String getOppStatus() {
		return oppStatus;
	}

	public void setOppStatus(String oppStatus) {
		this.oppStatus = oppStatus;
	}

	/*
	public String getBforecastStatus() {
		return bforecastStatus;
	}

	public void setBforecastStatus(String bforecastStatus) {
		this.bforecastStatus = bforecastStatus;
	}

	public Date getBforecastDate() {
		return bforecastDate;
	}

	public void setBforecastDate(Date bforecastDate) {
		this.bforecastDate = bforecastDate;
	}

	public String getBforecastBy() {
		return bforecastBy;
	}

	public void setBforecastBy(String bforecastBy) {
		this.bforecastBy = bforecastBy;
	}

	public String getBsubmitflag() {
		return bsubmitflag;
	}

	public void setBsubmitflag(String bsubmitflag) {
		this.bsubmitflag = bsubmitflag;
	}

	public Date getBsubmitdate() {
		return bsubmitdate;
	}

	public void setBsubmitdate(Date bsubmitdate) {
		this.bsubmitdate = bsubmitdate;
	}

	public Date getBsubmittimezonekey() {
		return bsubmittimezonekey;
	}

	public void setBsubmittimezonekey(Date bsubmittimezonekey) {
		this.bsubmittimezonekey = bsubmittimezonekey;
	}

	public String getBreforecastBy() {
		return breforecastBy;
	}

	public void setBreforecastBy(String breforecastBy) {
		this.breforecastBy = breforecastBy;
	}

	public Date getBresubmitDate() {
		return bresubmitDate;
	}

	public void setBresubmitDate(Date bresubmitDate) {
		this.bresubmitDate = bresubmitDate;
	}

	public Date getBresubmit_timezonekey() {
		return bresubmit_timezonekey;
	}

	public void setBresubmit_timezonekey(Date bresubmit_timezonekey) {
		this.bresubmit_timezonekey = bresubmit_timezonekey;
	}

	public String getRegforecastStatus() {
		return regforecastStatus;
	}

	public void setRegforecastStatus(String regforecastStatus) {
		this.regforecastStatus = regforecastStatus;
	}

	public Date getRegionforecastDate() {
		return regionforecastDate;
	}

	public void setRegionforecastDate(Date regionforecastDate) {
		this.regionforecastDate = regionforecastDate;
	}

	public String getRegforecastBy() {
		return regforecastBy;
	}

	public void setRegforecastBy(String regforecastBy) {
		this.regforecastBy = regforecastBy;
	}

	public String getRegsubmitFlag() {
		return regsubmitFlag;
	}

	public void setRegsubmitFlag(String regsubmitFlag) {
		this.regsubmitFlag = regsubmitFlag;
	}

	public Date getRegsubmitDate() {
		return regsubmitDate;
	}

	public void setRegsubmitDate(Date regsubmitDate) {
		this.regsubmitDate = regsubmitDate;
	}

	public Date getRegsubmitTimezonekey() {
		return regsubmitTimezonekey;
	}

	public void setRegsubmitTimezonekey(Date regsubmitTimezonekey) {
		this.regsubmitTimezonekey = regsubmitTimezonekey;
	}

	public String getRegreforecastBy() {
		return regreforecastBy;
	}

	public void setRegreforecastBy(String regreforecastBy) {
		this.regreforecastBy = regreforecastBy;
	}

	public Date getRegresubmitDate() {
		return regresubmitDate;
	}

	public void setRegresubmitDate(Date regresubmitDate) {
		this.regresubmitDate = regresubmitDate;
	}

	public Date getRegresubmitTimezonekey() {
		return regresubmitTimezonekey;
	}

	public void setRegresubmitTimezonekey(Date regresubmitTimezonekey) {
		this.regresubmitTimezonekey = regresubmitTimezonekey;
	}

	public String getHqforecastStatus() {
		return hqforecastStatus;
	}

	public void setHqforecastStatus(String hqforecastStatus) {
		this.hqforecastStatus = hqforecastStatus;
	}

	public Date getHqforecastDate() {
		return hqforecastDate;
	}

	public void setHqforecastDate(Date hqforecastDate) {
		this.hqforecastDate = hqforecastDate;
	}

	public String getHqforecastBy() {
		return hqforecastBy;
	}

	public void setHqforecastBy(String hqforecastBy) {
		this.hqforecastBy = hqforecastBy;
	}

	public String getHqsubmitFlag() {
		return hqsubmitFlag;
	}

	public void setHqsubmitFlag(String hqsubmitFlag) {
		this.hqsubmitFlag = hqsubmitFlag;
	}

	public Date getHqsubmitDate() {
		return hqsubmitDate;
	}

	public void setHqsubmitDate(Date hqsubmitDate) {
		this.hqsubmitDate = hqsubmitDate;
	}

	public Date getHqsubmitTimezonekey() {
		return hqsubmitTimezonekey;
	}

	public void setHqsubmitTimezonekey(Date hqsubmitTimezonekey) {
		this.hqsubmitTimezonekey = hqsubmitTimezonekey;
	}

	public String getHqreforecastBy() {
		return hqreforecastBy;
	}

	public void setHqreforecastBy(String hqreforecastBy) {
		this.hqreforecastBy = hqreforecastBy;
	}

	public Date getHqresubmitDate() {
		return hqresubmitDate;
	}

	public void setHqresubmitDate(Date hqresubmitDate) {
		this.hqresubmitDate = hqresubmitDate;
	}

	public Date getHqresubmitTimezonekey() {
		return hqresubmitTimezonekey;
	}

	public void setHqresubmitTimezonekey(Date hqresubmitTimezonekey) {
		this.hqresubmitTimezonekey = hqresubmitTimezonekey;
	}
*/
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getCreatedTimezonekey() {
		return createdTimezonekey;
	}

	public void setCreatedTimezonekey(Date createdTimezonekey) {
		this.createdTimezonekey = createdTimezonekey;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public Date getModifiedTimezonekey() {
		return modifiedTimezonekey;
	}

	public void setModifiedTimezonekey(Date modifiedTimezonekey) {
		this.modifiedTimezonekey = modifiedTimezonekey;
	}

	public BigDecimal getConvertedFactoredAmt() {
		return convertedFactoredAmt;
	}

	public void setConvertedFactoredAmt(BigDecimal convertedFactoredAmt) {
		this.convertedFactoredAmt = convertedFactoredAmt;
	}

	
	public BigDecimal getConvertedUnfactoredAmt() {
		return convertedUnfactoredAmt;
	}

	public void setConvertedUnfactoredAmt(BigDecimal convertedUnfactoredAmt) {
		this.convertedUnfactoredAmt = convertedUnfactoredAmt;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}

	public String getOppDataForMonth() {
		return oppDataForMonth;
	}

	public void setOppDataForMonth(String oppDataForMonth) {
		this.oppDataForMonth = oppDataForMonth;
	}
	 
	 	 
	 

}
