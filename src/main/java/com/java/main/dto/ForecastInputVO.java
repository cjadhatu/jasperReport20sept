package com.java.main.dto;

import java.math.BigDecimal;
import java.util.List;

public class ForecastInputVO {
	
	private List<OpportunityDTO> oppData;
	private ReportGridInputMultipleVO fetchJson;
	
	public ReportGridInputMultipleVO getFetchJson() {
		return fetchJson;
	}

	public void setFetchJson(ReportGridInputMultipleVO fetchJson) {
		this.fetchJson = fetchJson;
	}

	public List<OpportunityDTO> getOppData() {
		return oppData;
	}

	public void setOppData(List<OpportunityDTO> oppData) {
		this.oppData = oppData;
	}
	}
