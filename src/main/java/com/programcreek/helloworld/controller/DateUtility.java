package com.programcreek.helloworld.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {

	public static Date getDatefromStringMMMDDYYY(String date) {
		//SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateInString = "07/06/2013";
		Date date1 = null;
		try {

			date1 = formatter.parse(date);
			/*System.out.println(date1);
			System.out.println(formatter.format(date1));*/

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
	
	public static Date getDatefromStringDDMMMYYYY(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String dateInString = "07/06/2013";
		Date date1 = null;
		try {

			date1 = formatter.parse(date);
			/*System.out.println(date1);
			System.out.println(formatter.format(date1));
*/
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
	
	public static String getStringFrmDate(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("MMM-dd-yyyy");
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
	public static String getStringALLFrmDate(Date indate,String format) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat(format);
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
	
	
	public static String getStringFrmDatePattern(Date indate, String pattern) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat(pattern);
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}

	
	public static Date getDatefromString_MMMYYYY(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-yyyy");
		String dateInString = "07/06/2013";
		Date date1 = null;
		try {

			date1 = formatter.parse(date);
			/*System.out.println(date1);
			System.out.println(formatter.format(date1));*/

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
	public static String getStringFrmDateFinalDD_MMM_YYYY(Date indate) {
		String dateString = null;
		SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy");
		/*
		 * you can also use DateFormat reference instead of SimpleDateFormat
		 * like this: DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
		 */
		try {
			dateString = sdfr.format(indate);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return dateString;
	}
	public static String getStringFrmStringDD_MMM_YYYY(String indate){
		Date d=getDatefromString_MMMYYYY(indate);
		String out=getStringFrmDateFinalDD_MMM_YYYY(d);
		return out;
	}
	public static Date getDatefromStringDDMMMYY(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
		String dateInString = "07/06/13";
		Date date1 = null;
		try {

			date1 = formatter.parse(date);
			/*System.out.println(date1);
			System.out.println(formatter.format(date1));
*/
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return date1;
	}
}
