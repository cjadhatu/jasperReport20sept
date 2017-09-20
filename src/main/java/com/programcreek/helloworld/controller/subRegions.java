package com.programcreek.helloworld.controller;

public class subRegions {
	
	private Integer rId;
	private Integer regId;
	private String  regName;
	private String  regForcast;
	private String  regBranch;
	private String  regStatus;
	private Integer branches;
	private Integer opps;
	private String updated;
	private String updatedBy;
	private String sumfactamt;
	
	
	
	
	public Integer getrId() {
		return rId;
	}
	public void setrId(Integer rId) {
		this.rId = rId;
	}
	public Integer getRegId() {
		return regId;
	}
	public void setRegId(Integer regId) {
		this.regId = regId;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getRegForcast() {
		return regForcast;
	}
	public void setRegForcast(String regForcast) {
		this.regForcast = regForcast;
	}
	public String getRegBranch() {
		return regBranch;
	}
	public void setRegBranch(String regBranch) {
		this.regBranch = regBranch;
	}
	public String getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}
	public Integer getBranches() {
		return branches;
	}
	public void setBranches(Integer branches) {
		this.branches = branches;
	}
	public Integer getOpps() {
		return opps;
	}
	public void setOpps(Integer opps) {
		this.opps = opps;
	}
	public String getUpdated() {
		return updated;
	}
	public void setUpdated(String updated) {
		this.updated = updated;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getSumfactamt() {
		return sumfactamt;
	}
	public void setSumfactamt(String sumfactamt) {
		this.sumfactamt = sumfactamt;
	}
	@Override
	public String toString() {
		return "subRegions [rId=" + rId + ", regId=" + regId + ", regName="
				+ regName + ", regForcast=" + regForcast + ", regBranch="
				+ regBranch + ", regStatus=" + regStatus + ", branches="
				+ branches + ", opps=" + opps + ", updated=" + updated
				+ ", updatedBy=" + updatedBy + ", sumfactamt=" + sumfactamt
				+ "]";
	}
	
	
	
	
}
