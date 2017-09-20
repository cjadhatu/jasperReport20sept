package com.java.main.dto;

public class SummaryReportDTO {
	
	private Integer totalRegion;
	private Integer totalSubRegion;
	private Integer totalCountry;
	private Integer totalBranch;
	private Integer totalOpp;
	private String totalunfactoredAmount;
	private String totalFactoredAmount;
	//new field  add 
	private String  convertedFactoredAmt;
	private String  convertedunFactoredAmt; 
	
	
	public Integer getTotalRegion() {
		return totalRegion;
	}
	public void setTotalRegion(Integer totalRegion) {
		this.totalRegion = totalRegion;
	}
	public Integer getTotalSubRegion() {
		return totalSubRegion;
	}
	public void setTotalSubRegion(Integer totalSubRegion) {
		this.totalSubRegion = totalSubRegion;
	}
	public Integer getTotalCountry() {
		return totalCountry;
	}
	public void setTotalCountry(Integer totalCountry) {
		this.totalCountry = totalCountry;
	}
	public Integer getTotalBranch() {
		return totalBranch;
	}
	public void setTotalBranch(Integer totalBranch) {
		this.totalBranch = totalBranch;
	}
	public Integer getTotalOpp() {
		return totalOpp;
	}
	public void setTotalOpp(Integer totalOpp) {
		this.totalOpp = totalOpp;
	}
	public String getTotalunfactoredAmount() {
		return totalunfactoredAmount;
	}
	public void setTotalunfactoredAmount(String totalunfactoredAmount) {
		this.totalunfactoredAmount = totalunfactoredAmount;
	}
	public String getTotalFactoredAmount() {
		return totalFactoredAmount;
	}
	public void setTotalFactoredAmount(String totalFactoredAmount) {
		this.totalFactoredAmount = totalFactoredAmount;
	}
	public String getConvertedFactoredAmt() {
		return convertedFactoredAmt;
	}
	public void setConvertedFactoredAmt(String convertedFactoredAmt) {
		this.convertedFactoredAmt = convertedFactoredAmt;
	}
	public String getConvertedunFactoredAmt() {
		return convertedunFactoredAmt;
	}
	public void setConvertedunFactoredAmt(String convertedunFactoredAmt) {
		this.convertedunFactoredAmt = convertedunFactoredAmt;
	}
	
	
	
    
    

}
