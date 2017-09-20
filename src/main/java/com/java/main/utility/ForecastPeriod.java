package com.java.main.utility;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ForecastPeriod {
public static String getForecastPeriod(){
	
	LocalDate date = LocalDate.now();
    LocalDate today=LocalDate.now();
    LocalDate firstOfThisMonth = today.withDayOfMonth( 1 );
   long i=firstOfThisMonth.lengthOfMonth();
   LocalDate lastofThisMonth=firstOfThisMonth.plusDays(i-1);
	DateTimeFormatter formatters = DateTimeFormatter.ofPattern("MM-dd-yyyy");
    String textfirstday = firstOfThisMonth.format(formatters);
    String textsecondday = lastofThisMonth.format(formatters);
   // LocalDate parsedDate = LocalDate.parse(text, formatters);
    System.out.println("date: " + date); // date: 2016-09-25
    System.out.println("textfirstday format " + textfirstday); // Text format 25/09/2016
    System.out.println("textsecondday format " + textsecondday); // Text format 25/09/2016
   // System.out.println("parsedDate: " + parsedDate); // parsedDate: 2016-09-25
    
	return "Forecast Period "+textfirstday+" to "+textsecondday;
}
}
