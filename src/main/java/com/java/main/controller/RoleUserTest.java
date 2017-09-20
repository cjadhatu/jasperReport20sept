package com.java.main.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.java.main.dto.LoginSumVO;

public class RoleUserTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
List<String> reg=new ArrayList<String>();
reg.add("Nort East Asia");
reg.add("Nort East Asia");
List<LoginSumVO> childData=new ArrayList<LoginSumVO>();
List<LoginSumVO> finalchildData=new ArrayList<LoginSumVO>();
LoginSumVO apac=new LoginSumVO("APAC","1", "1000","2000", "3000", "40000", true);
LoginSumVO china=new LoginSumVO("China","1", "1000","2000", "3000", "40000", true);
LoginSumVO india=new LoginSumVO("India","2", "2000","21000", "31000", "410000", true);
LoginSumVO pacific=new LoginSumVO("Pacific","3", "3000","32000", "33000", "340000", true);
LoginSumVO nea=new LoginSumVO("Nort East Asia","4", "4000","42000", "43000", "440000", true);
LoginSumVO sea=new LoginSumVO("South East Asia","5", "5000","5000", "5000", "50000", true);
LoginSumVO apd=new LoginSumVO("Asia Product & Disribution","6", "6000","6000", "6000", "60000", true);
LoginSumVO apd1=new LoginSumVO("Asia Product & Disribution","", "","", "", "", false);
childData.add(china);
childData.add(india);
childData.add(pacific);
childData.add(nea);
childData.add(sea);
childData.add(apd);
childData.add(apac);
System.out.println("Child data size "+childData.size()+" data"+childData);
//childData.remove(sea);
childData.remove(apd1);
System.out.println("After remove Child data size "+childData.size()+" Data "+childData);

Map<String, LoginSumVO> map=new HashMap<String, LoginSumVO>();
for(LoginSumVO loginSumVO:childData){
	map.put(loginSumVO.getName(), loginSumVO);
}
for(Object k:map.keySet()){
	//String strKey=(String)k;
	if(reg.contains(k)){
		finalchildData.add(map.get(k));
		//System.out.println("Key exixst"+k);
	}
	else
	{
		LoginSumVO vo=map.get(k);
		vo.setCurrentMonth("");
		vo.setIsClickable(false);
		vo.setLastMonth("");
		vo.setPlannedSale("");
		vo.setRollingDays("");
		finalchildData.add(vo);
		//System.out.println("Key does not exixst"+k);
	}
	//String strKey=k.toString();
	
}
System.out.println("Final list"+new Gson().toJson(finalchildData));
}
}
