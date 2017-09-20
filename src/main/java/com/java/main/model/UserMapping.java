package com.java.main.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "forecast_user_rolemap")
public class UserMapping {

	@Id
	@Column(name = "maproleid")
	private Integer maproleid;
	
	@Column(name="datasource")
	private String datasource;
	
	@Column(name="globalid")
	private String globalid;
	
	@Column(name="userid")
	private Integer userid;
	
	@Column(name="user_name")
	private String user_name;
	
	@Column(name="sfid")
	private String sfid;
	
	@Column(name="region")
	private String region;
	
	@Column(name="subregion")
	private String subregion;
	
	@Column(name="country")
	private String country;
	
	@Column(name="lob")
	private String lob;
	
	@Column(name="managerid")
	private String managerid;
	
	@Column(name="manager_name")
	private String managerName;
	
	@Column(name="currencyisocode")
	private String currencyisocode;
	
	@Column(name="forecast_roleid")
	private Integer forecastRoleid;
	
	@Column(name="forecast_rolecode")
	private String forecastRolecode;
	
	@Column(name="isactive")
	private String isactive;
	
	@Column(name="branch_mapid")
	private Integer branchMapid;
	
	@Column(name="lob_mapid")
	private Integer lobMapid;
	
	@Column(name="createdby")
	private String createdby;
	
	@Column(name="createddate")
	private Date createddate;
	
	@Column(name="modifiedby")
	private String modifiedby;
	
	@Column(name="modifieddate")
	private Date modifieddate;

	public Integer getMaproleid() {
		return maproleid;
	}

	public void setMaproleid(Integer maproleid) {
		this.maproleid = maproleid;
	}

	public String getDatasource() {
		return datasource;
	}

	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}

	public String getGlobalid() {
		return globalid;
	}

	public void setGlobalid(String globalid) {
		this.globalid = globalid;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getSfid() {
		return sfid;
	}

	public void setSfid(String sfid) {
		this.sfid = sfid;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getSubregion() {
		return subregion;
	}

	public void setSubregion(String subregion) {
		this.subregion = subregion;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}

	public String getManagerid() {
		return managerid;
	}

	public void setManagerid(String managerid) {
		this.managerid = managerid;
	}

	public String getManagerName() {
		return managerName;
	}

	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}

	public String getCurrencyisocode() {
		return currencyisocode;
	}

	public void setCurrencyisocode(String currencyisocode) {
		this.currencyisocode = currencyisocode;
	}

	public Integer getForecastRoleid() {
		return forecastRoleid;
	}

	public void setForecastRoleid(Integer forecastRoleid) {
		this.forecastRoleid = forecastRoleid;
	}

	public String getForecastRolecode() {
		return forecastRolecode;
	}

	public void setForecastRolecode(String forecastRolecode) {
		this.forecastRolecode = forecastRolecode;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public Integer getBranchMapid() {
		return branchMapid;
	}

	public void setBranchMapid(Integer branchMapid) {
		this.branchMapid = branchMapid;
	}

	public Integer getLobMapid() {
		return lobMapid;
	}

	public void setLobMapid(Integer lobMapid) {
		this.lobMapid = lobMapid;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public Date getCreateddate() {
		return createddate;
	}

	public void setCreateddate(Date createddate) {
		this.createddate = createddate;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Date getModifieddate() {
		return modifieddate;
	}

	public void setModifieddate(Date modifieddate) {
		this.modifieddate = modifieddate;
	}
	
	
	

}
