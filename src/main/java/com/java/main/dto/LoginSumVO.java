package com.java.main.dto;

public class LoginSumVO {

private String name;
private String id;
private String lastMonth;
private String currentMonth;
private String rollingDays;
private String plannedSale;
private Boolean isClickable=true;



@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((name == null) ? 0 : name.hashCode());
	return result;
}
@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	LoginSumVO other = (LoginSumVO) obj;
	if (name == null) {
		if (other.name != null)
			return false;
	} else if (!name.equals(other.name))
		return false;
	return true;
}
@Override
public String toString() {
	return "LoginSumVO [name=" + name + ", id=" + id + ", lastMonth="
			+ lastMonth + ", currentMonth=" + currentMonth + ", rollingDays="
			+ rollingDays + ", plannedSale=" + plannedSale + ", isClickable="
			+ isClickable + "]";
}
public LoginSumVO() {
	//super();
	// TODO Auto-generated constructor stub
}
public LoginSumVO(String name, String id, String lastMonth,
		String currentMonth, String rollingDays, String plannedSale,
		Boolean isClickable) {
	super();
	this.name = name;
	this.id = id;
	this.lastMonth = lastMonth;
	this.currentMonth = currentMonth;
	this.rollingDays = rollingDays;
	this.plannedSale = plannedSale;
	this.isClickable = isClickable;
}
public Boolean getIsClickable() {
	return isClickable;
}
public void setIsClickable(Boolean isClickable) {
	this.isClickable = isClickable;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getLastMonth() {
	return lastMonth;
}
public void setLastMonth(String lastMonth) {
	this.lastMonth = lastMonth;
}
public String getCurrentMonth() {
	return currentMonth;
}
public void setCurrentMonth(String currentMonth) {
	this.currentMonth = currentMonth;
}
public String getRollingDays() {
	return rollingDays;
}
public void setRollingDays(String rollingDays) {
	this.rollingDays = rollingDays;
}
public String getPlannedSale() {
	return plannedSale;
}
public void setPlannedSale(String plannedSale) {
	this.plannedSale = plannedSale;
}

}
