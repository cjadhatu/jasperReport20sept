package com.java.main.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtility {
	public static Date getDatefromStringMMMDDYYY(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MMM-dd-yyyy");
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
	public static Date getDatefromStringMMDDYYYY(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
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
	public static Date getDatefromStringYYYY_MM_DD(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
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
public static Date getDateForEditOpportunity(String inputString){
	//String inputString="04-23-2017";
    String dd=inputString.substring(3,5);
    String mm=inputString.substring(0,2);
    String yy=inputString.substring(6,10);
    //System.out.println("dd "+dd);
    //System.out.println("mm "+mm);
    //System.out.println("yy "+yy);
    String finalInputStr=yy+"-"+mm+"-"+dd;
    //System.out.println("finalInputStr ="+finalInputStr);
    SimpleDateFormat sdfFinal = new SimpleDateFormat("yyyy-MM-dd");
    Date finalDate=null;
	try {
		finalDate = sdfFinal.parse(finalInputStr);
	} catch (ParseException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    //System.out.println("finalDate ="+finalDate);
    return finalDate;
}
public static Date getDateForEditOpportunityIfDateEdit(String inputString){
	//String inputString="04-23-2017";2017-04-02T18:30:00.000Z
	//String inDate=
   


    String yyyy=inputString.substring(0,4);
    String mm=inputString.substring(5,7);
    String dd=inputString.substring(8,10);
    String str=mm+"-"+dd+"-"+yyyy;
    
        return getDateForEditOpportunity(str);
}
}
