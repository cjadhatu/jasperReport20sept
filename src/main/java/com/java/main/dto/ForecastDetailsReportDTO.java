package com.java.main.dto;

import java.util.List;

public class ForecastDetailsReportDTO {
	
	
	
	private List<ForecastDetailsReportCMDTO> ForecastDetailsNminus1List;
	private List<ForecastDetailsReportCMDTO> forecastDetailsReportCMList;
	private List<ForecastDetailsReportCMDTO> forecastDetailsNPluse1List;
	private List<ForecastDetailsReportCMDTO> forecastDetailsNPluse2List;
	private List<ForecastDetailsReportCMDTO> forecastDetailsLobWiseList;
	
	public List<ForecastDetailsReportCMDTO> getForecastDetailsNminus1List() {
		return ForecastDetailsNminus1List;
	}
	public void setForecastDetailsNminus1List(
			List<ForecastDetailsReportCMDTO> forecastDetailsNminus1List) {
		ForecastDetailsNminus1List = forecastDetailsNminus1List;
	}
	public List<ForecastDetailsReportCMDTO> getForecastDetailsReportCMList() {
		return forecastDetailsReportCMList;
	}
	public void setForecastDetailsReportCMList(
			List<ForecastDetailsReportCMDTO> forecastDetailsReportCMList) {
		this.forecastDetailsReportCMList = forecastDetailsReportCMList;
	}
	public List<ForecastDetailsReportCMDTO> getForecastDetailsNPluse1List() {
		return forecastDetailsNPluse1List;
	}
	public void setForecastDetailsNPluse1List(
			List<ForecastDetailsReportCMDTO> forecastDetailsNPluse1List) {
		this.forecastDetailsNPluse1List = forecastDetailsNPluse1List;
	}
	public List<ForecastDetailsReportCMDTO> getForecastDetailsNPluse2List() {
		return forecastDetailsNPluse2List;
	}
	public void setForecastDetailsNPluse2List(
			List<ForecastDetailsReportCMDTO> forecastDetailsNPluse2List) {
		this.forecastDetailsNPluse2List = forecastDetailsNPluse2List;
	}
	public List<ForecastDetailsReportCMDTO> getForecastDetailsLobWiseList() {
		return forecastDetailsLobWiseList;
	}
	public void setForecastDetailsLobWiseList(
			List<ForecastDetailsReportCMDTO> forecastDetailsLobWiseList) {
		this.forecastDetailsLobWiseList = forecastDetailsLobWiseList;
	}
	
	
	
	
	
	
	

	
	
	

}
