package com.java.main.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.java.main.dao.AutoSubmissionDao;

public class RegionScheduler {
	
	@Autowired
	AutoSubmissionDao autoSubmissionDao;
	//intially   @Scheduled(cron="0 48 1 9 1/1 ?")

	//@Scheduled(cron="*/5 * * * * ?")
	//@Scheduled(cron="0 0 00-01 25 03 ?")
	
//	0 15 10 15 * ?    Fire at 10:15am on the 15th day of every month ss mm hrs day 
	//@Scheduled(cron="0 * 2 * * ?")
	//@Scheduled(cron="0 30 23 12 * ?")    //Fires at 12th of every month at 23:30 PM
	//0 0 1 9 1/1 ? *
    public void demoServiceMethod()
    {
        System.out.println("RegionScheduler Method executed at  Current time is :: "+ new Date());
        //autoSubmissionDao.RegionAutoSubmission(new String());
    }
	
}
