package com.java.main.dto;

import java.math.BigDecimal;
import java.util.List;

public class ReportOutPutVO {
private List<ReportGridVO> detailGrid;

private String totalFactoredAmtLocalCur;
private String totalunFactoredAmtLocalCur;
private String totalFactoredAmt;
private String totalunfactoredAmt;
public List<ReportGridVO> getDetailGrid() {
	return detailGrid;
}
public void setDetailGrid(List<ReportGridVO> detailGrid) {
	this.detailGrid = detailGrid;
}
public String getTotalFactoredAmtLocalCur() {
	return totalFactoredAmtLocalCur;
}
public void setTotalFactoredAmtLocalCur(String totalFactoredAmtLocalCur) {
	this.totalFactoredAmtLocalCur = totalFactoredAmtLocalCur;
}
public String getTotalunFactoredAmtLocalCur() {
	return totalunFactoredAmtLocalCur;
}
public void setTotalunFactoredAmtLocalCur(String totalunFactoredAmtLocalCur) {
	this.totalunFactoredAmtLocalCur = totalunFactoredAmtLocalCur;
}
public String getTotalFactoredAmt() {
	return totalFactoredAmt;
}
public void setTotalFactoredAmt(String totalFactoredAmt) {
	this.totalFactoredAmt = totalFactoredAmt;
}
public String getTotalunfactoredAmt() {
	return totalunfactoredAmt;
}
public void setTotalunfactoredAmt(String totalunfactoredAmt) {
	this.totalunfactoredAmt = totalunfactoredAmt;
}


}
