package com.java.main.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.java.main.dao.AutoSubmissionDao;

public class SubRegionScheduler {

@Autowired
AutoSubmissionDao autoSubmissionDao;
	//intially   @Scheduled(cron="0 48 1 9 1/1 ?")

	//@Scheduled(cron="*/5 * * * * ?")
	//@Scheduled(cron="0 0 00-01 25 03 ?")
	
//	0 15 10 15 * ?    Fire at 10:15am on the 15th day of every month ss mm hrs day 
	//@Scheduled(cron="0 * 2 * * ?")
	//@Scheduled(cron="0 30 23 9 * ?") //fires at 9th day of every month at 11:15 PM
//@Scheduled(cron="0 03 17 10 * ?")      // July 10 @ 4PM UTC. Fires at 10th of every month at 5PM UTC
	//0 0 1 9 1/1 ? *
    public void demoServiceMethod()
    {
        System.out.println("SubRegionScheduler Method executed at Current time is :: "+ new Date());
        //autoSubmissionDao.SubRegionAutoSubmission(new String());
    }
	
}
