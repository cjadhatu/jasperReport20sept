package com.java.main.dto;

import java.util.List;

public class SumDetailReportResDTO {
	
	private List<SummaryReptDTO> summaryList;
	private List<Object[]> detailList;
	public List<SummaryReptDTO> getSummaryList() {
		return summaryList;
	}
	public void setSummaryList(List<SummaryReptDTO> summaryList) {
		this.summaryList = summaryList;
	}
	public List<Object[]> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<Object[]> detailList) {
		this.detailList = detailList;
	}
	
	

	

}
