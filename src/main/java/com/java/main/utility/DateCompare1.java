package com.java.main.utility;

import java.util.Calendar;
import java.util.Date;

public class DateCompare1 {
	public  static String isValidDate(Date dbDate) {

		// TODO Auto-generated method stub
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();


		//set the given date in one of the instance and current date in another
		
		cal2.setTime(new Date());
		//String strDate="04-27-2016";
		//Date givenDate=DateUtility.getDatefromStringMMDDYYYY(strDate);
		cal1.setTime(dbDate);
		//now compare the dates using functions
		if(cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)) 
		{
		    if(cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)) 
		    {
		        // the date falls in current month
		    	//System.out.println("in if ");
		    	return "Y";
		    }
		    
		    else
		    {
		    	return "N";
		    }
		    
		}
		else
		{
			//System.out.println("______outer else");
			return "N";
		}
		
	
	}
	

}
