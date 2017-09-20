package com.java.main.controller;



import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Calendar;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.java.main.model.LoginBean;
import com.java.main.utility.DataTypeConvert;



//import org.springframework.util.StringUtils;



public class Test {

	
	public int countRun( String s, String substring )
	  {
		//String input = "Today is ayMonday";
		 int count=StringUtils.countMatches(s,substring);
			
	  
	  return count;
	  }
	
	public static void main(String[] args) {
		
		/*LoginBean b=new LoginBean();
		b.setUsername("Arif");
		System.out.println("JSON "+new Gson().toJson(b));
		*/
		// TODO Auto-generated method stub

/*		List<String> regions=new ArrayList<String>();
		regions.add("India");
		regions.add("Japan");
		regions.add("Honkong");
		String list= StringUtils.join(regions, "','");
		list = "'" + list + "'";
		System.out.println(list);
		StringBuilder sb=new StringBuilder();
		sb.append('(');
		sb.append(list);
		sb.append(')');
		System.out.println(sb);
		ReportInputMultipleVO reportInputMultipleVO=new ReportInputMultipleVO();
		List<ValueDTO> vos=new ArrayList<ValueDTO>();
		ValueDTO v1=new ValueDTO();
		ValueDTO v2=new ValueDTO();
		ValueDTO v3=new ValueDTO();
		//v1.setValue("India");
		v1.setName("China");
		v2.setName("India");
		v3.setName("Japan");
		vos.add(v1);
		vos.add(v2);
		vos.add(v3);
		//reportInputMultipleVO.setValues(vos);
		//System.out.println(new Gson().toJson(reportInputMultipleVO));
		BigDecimal bd=new BigDecimal(0);
		bd=bd.add(new BigDecimal(3));
		System.out.println("BIGGGG "+bd);
		*/
		/*Calendar c = Calendar.getInstance();   // this takes current date
	    c.set(Calendar.DAY_OF_MONTH,1);
	    System.out.println(c.getTime()); 
	    
	   // LocalDate fromDate = new LocalDate().withDayOfMonth(1);
	    LocalDate fromd=new LocalDate(1, 2, 3).withDayOfMonth(1)*/
	    //LocalDate toDate = new LocalDate().minusDays(1);
		/*LocalDate today = LocalDate.now();
		LocalDate t	=LocalDate.MAX;
	    LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
	    LocalDate yesterday = tomorrow.minusDays(2);

	    System.out.println(today);
	    System.out.println(tomorrow);
	    System.out.println(yesterday);
	    System.out.println(t);		
*/	
		/*Date today = new Date();  

        Calendar calendar = Calendar.getInstance();  
        calendar.setTime(today);  

        calendar.add(Calendar.MONTH, 1);  
        calendar.set(Calendar.DAY_OF_MONTH, 1);  
        calendar.add(Calendar.DATE, -1);  

        Date lastDayOfMonth = calendar.getTime();  

        DateFormat sdf = new SimpleDateFormat("MM-dd--yyyy");  
        System.out.println("Today            : " + sdf.format(today));  
        System.out.println("Last Day of Month: " + sdf.format(lastDayOfMonth)); 	
*/	
		
		
		
		/*ZonedId zoneId = ZoneId.of( "America/Montreal" );
		YearMonth yearMonthNow = YearMonth.now( zoneId );
		YearMonth yearMonthPrevious = yearMonthNow.minusMonths( 1 );
		LocalDate firstOfMonth = yearMonthPrevious.atDay( 1 );
		LocalDate lastOfMonth = yearMonthPrevious.atEndOfMonth();*/	
		/*ZoneId zoneId=ZoneId.of("Europe/Paris");  //Europe/Paris
		YearMonth yearMonthNow = YearMonth.now( zoneId );
		YearMonth yearMonthPrevious = yearMonthNow.minusMonths( 1 );
		LocalDate firstOfMonth = yearMonthPrevious.atDay( 1 );
		LocalDate lastOfMonth = yearMonthPrevious.atEndOfMonth();
		System.out.println(" yearMonthNow "+yearMonthNow);
		System.out.println(" yearMonthPrevious "+yearMonthPrevious);
		System.out.println(" firstOfMonth "+firstOfMonth);
		System.out.println(" lastOfMonth "+lastOfMonth);*/
		
		
		//LocalDate today = LocalDate.now( DateTimeZone.forID( "Europe/Paris" ) );
		//firstOfThisMonth.
		//LocalDate today=LocalDate.now();
		//System.out.println(" firstOfMonth "+firstOfThisMonth);
		//System.out.println(" lastofThisMonth "+lastofThisMonth);
		//System.out.println(today.plus(1, null));
		
		
		
		
		
		
		/*LocalDate date = LocalDate.now();
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
	   */// System.out.println("parsedDate: " + parsedDate); // parsedDate: 2016-09-25
	    
	    
//Date window code
		
		Calendar cal1 = Calendar.getInstance();
		int currentDay=cal1.get(Calendar.DAY_OF_MONTH);
		BigDecimal dbDate=new BigDecimal("12");
		int dbDay1=dbDate.intValue();
		
		if(dbDay1==currentDay){
			System.out.println(" Equal day");
			
		}
		else{
			System.out.println(" Not a Equal day");
		}
		
		
		//BigDecimal bdf=new BigDecimal(12);
		//BigDecimal bdf1=new BigDecimal(bdf);		
		
	String amount="-200";
	BigDecimal amountBd=DataTypeConvert.getBigDecFromCommaSepString(amount);
	System.out.println("AMOUNT "+amountBd);
		 
	Locale locale = new Locale("en", "US");
	NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
	BigDecimal bd =new BigDecimal(-9);
	String secondStr="";
	/*if (bd.compareTo(BigDecimal.ZERO) < 0){
		bd=bd.negate();
	}*/
//	op.setUnfactAmt(fmt.format(bd));
	String strbd=fmt.format(bd);
	StringBuffer sb=new StringBuffer(strbd);
	
	
	String tr="-"+sb.substring(1, sb.length()-1);
	System.out.println("STRING BUffer is "+sb);
	System.out.println("STRING uffer length "+sb.length());
	System.out.println("Second STRING is "+tr);	
	
	
		
	}

}
