package com.java.main.dto;

import java.util.Comparator;

public class LandingComparator implements Comparator<LoginSumVO>{

	public int compare(LoginSumVO o1, LoginSumVO o2) {
		// TODO Auto-generated method stub
		return o1.getId().compareTo(o2.getId());
	}
	
	

}
