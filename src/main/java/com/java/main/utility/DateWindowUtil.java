package com.java.main.utility;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class DateWindowUtil {
public static String isINDateWindow(List<BigDecimal> dbdateInput){
	Calendar cal1 = Calendar.getInstance();
	String result="";
	int currentDay=cal1.get(Calendar.DAY_OF_MONTH);
	for(BigDecimal bd :dbdateInput){
		BigDecimal dbDate=new BigDecimal(bd.toString());
		int dbDay1=dbDate.intValue();
		
		if(dbDay1==currentDay)
		{
			//System.out.println(" IN Date window date db date is "+dbDate);
			
			result="Y";
			break;
		}
		else
		{
			//System.out.println(" Out of date window db date is  "+dbDate);
			result="N";
		}
	
	}

	return result;
}
}
