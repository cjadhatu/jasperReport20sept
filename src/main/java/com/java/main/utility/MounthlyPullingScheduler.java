package com.java.main.utility;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class MounthlyPullingScheduler {

	//intially   @Scheduled(cron="0 48 1 9 1/1 ?")

	
	
//	0 15 10 15 * ?    Fire at 10:15am on the 15th day of every month ss mm hrs day 
	
	//@Scheduled(cron="0 15 23 5 * ?")   //Fires on the 5th of every month at 23:15 PM Final on live
	
	//@Scheduled(cron="0 15 02 15 * ?")   //Fires on the 15th of every month at 02:15  AM For DEV
    public void demoServiceMethod()
    {
        System.out.println("BranchScheduler Method executed at every 5 seconds. Current time is :: "+ new Date());
    }
	
}
