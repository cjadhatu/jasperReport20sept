package com.java.main.utility;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;

public class AdminHQScheduler {

	//intially   @Scheduled(cron="0 48 1 9 1/1 ?")

	//@Scheduled(cron="*/5 * * * * ?")
	//@Scheduled(cron="0 0 00-01 25 03 ?")
	
//	0 15 10 15 * ?    Fire at 10:15am on the 15th day of every month ss mm hrs day 
	//@Scheduled(cron="0 * 2 * * ?")
	//@Scheduled(cron="0 44 10 30 * ?")
	//0 0 1 9 1/1 ? *
    public void demoServiceMethod()
    {
        System.out.println("BranchScheduler Method executed at every 5 seconds. Current time is :: "+ new Date());
    }
	
}
