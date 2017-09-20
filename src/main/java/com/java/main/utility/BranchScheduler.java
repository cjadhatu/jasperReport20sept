package com.java.main.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.java.main.dao.AutoSubmissionDao;

public class BranchScheduler {

	@Autowired
	AutoSubmissionDao autoSubmissionDao;
	//intially   @Scheduled(cron="0 48 1 9 1/1 ?")

	//@Scheduled(cron="*/5 * * * * ?")
	//@Scheduled(cron="0 0 00-01 25 03 ?")
	
//	0 15 10 15 * ?    Fire at 10:15am on the 15th day of every month ss mm hrs day 
	//@Scheduled(cron="0 * 2 * * ?")                             0      15 10 15 * ?
	//@Scheduled(cron="0 05 16 10 * ?")      // July 10 @ 4PM UTC. Fires at 10th of every month at 4PM UTC  
	//@Scheduled(cron="0 15 23 7 * ?")      // Fires at 7th of every month at 23:15 PM
	//@Scheduled(cron="0 20 11 26 * ?")//Temp
	//@Scheduled(cron="0 10 8 26 * ?")
	//0 0 1 9 1/1 ? *
    public void demoServiceMethod()
    {
        System.out.println("BranchScheduler Method executed  Current time is :: "+ new Date());
       // autoSubmissionDao.BranchAutoSubmission(new String());
    }
	
}
