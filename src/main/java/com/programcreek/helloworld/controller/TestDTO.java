/**
 * 
 */
package com.programcreek.helloworld.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ckhan58 temporary DTO to check jdbc on heroku
 *
 */
public class TestDTO {

	private String regName;
	private String regTxt;
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	public String getRegTxt() {
		return regTxt;
	}
	public void setRegTxt(String regTxt) {
		this.regTxt = regTxt;
	}
	public static void main(String[] args) {
		Date today = new Date();               
	    SimpleDateFormat formattedDate = new SimpleDateFormat("yyyyMMdd");            
	    Calendar c = Calendar.getInstance();        
	    c.add(Calendar.DATE, 1);  // number of days to add      
	    String tomorrow = (String)(formattedDate.format(c.getTime()));
	    System.out.println("Tomorrows date is " + tomorrow);
	    
	    Date d=new Date(100);
	    System.out.println(d);
	}
}
