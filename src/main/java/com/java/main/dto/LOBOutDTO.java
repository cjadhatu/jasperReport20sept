package com.java.main.dto;

import java.util.List;

public class LOBOutDTO {
private List<LOBGridDTO> lobData;
private String totalLobs;
private String totalfactAmt;
private String totalUnfactAmt;
private String totalOpportunities;
private String totalRollingDaysFactAmt;
private String totalRollingDaysUnfactAmt;
public List<LOBGridDTO> getLobData() {
	return lobData;
}
public void setLobData(List<LOBGridDTO> lobData) {
	this.lobData = lobData;
}
public String getTotalLobs() {
	return totalLobs;
}
public void setTotalLobs(String totalLobs) {
	this.totalLobs = totalLobs;
}
public String getTotalfactAmt() {
	return totalfactAmt;
}
public void setTotalfactAmt(String totalfactAmt) {
	this.totalfactAmt = totalfactAmt;
}
public String getTotalUnfactAmt() {
	return totalUnfactAmt;
}
public void setTotalUnfactAmt(String totalUnfactAmt) {
	this.totalUnfactAmt = totalUnfactAmt;
}
public String getTotalOpportunities() {
	return totalOpportunities;
}
public void setTotalOpportunities(String totalOpportunities) {
	this.totalOpportunities = totalOpportunities;
}
public String getTotalRollingDaysFactAmt() {
	return totalRollingDaysFactAmt;
}
public void setTotalRollingDaysFactAmt(String totalRollingDaysFactAmt) {
	this.totalRollingDaysFactAmt = totalRollingDaysFactAmt;
}
public String getTotalRollingDaysUnfactAmt() {
	return totalRollingDaysUnfactAmt;
}
public void setTotalRollingDaysUnfactAmt(String totalRollingDaysUnfactAmt) {
	this.totalRollingDaysUnfactAmt = totalRollingDaysUnfactAmt;
}


}
