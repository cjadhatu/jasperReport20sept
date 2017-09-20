package com.java.main.utility;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class GetStringFromList {
public static String getStringFromListComma(List<String> strList){

	// TODO Auto-generated method stub

	String list= StringUtils.join(strList, "','");
	list = "'" + list + "'";
	//System.out.println(list);
	StringBuilder sb=new StringBuilder();
	sb.append('(');
	sb.append(list);
	sb.append(')');
	//System.out.println(sb);
	

	return sb.toString();
}
public static String getStringFromSetComma(Set<String> strList){

	// TODO Auto-generated method stub

	String list= StringUtils.join(strList, "','");
	list = "'" + list + "'";
	System.out.println(list);
	StringBuilder sb=new StringBuilder();
	sb.append('(');
	sb.append(list);
	sb.append(')');
	System.out.println(sb);
	

	return sb.toString();
}

}
