package com.java.main.utility;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

public class DataTypeConvert {

	
	//below method return a bigdeciaml value from comma seperated $ string value 
	// ex input= $42,505,667.02 output=42505667.02
	public static BigDecimal getBigDecFromCommaSepString(String value){
		//String st="$42,505,667.02";
		//Double d=new Double(st)
		//String st1=st.split(",");
		String t=Character.toString(value.charAt(0));
		BigDecimal d=new BigDecimal(0);
		if("$".equalsIgnoreCase(t)){
		 d=new BigDecimal(value.substring(1).replaceAll(",", ""));
		}
		else{
		d=	new BigDecimal(value.replaceAll(",", ""));
		}
		//System.out.println("double "+d);
		return d;
	}
	public static String get$StringFromNumers(BigDecimal value){
		Locale locale = new Locale("en", "US");
		NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
		
		
		return fmt.format(value);
	}
}
