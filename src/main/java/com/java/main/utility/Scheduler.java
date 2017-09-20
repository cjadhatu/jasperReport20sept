package com.java.main.utility;

import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.scheduling.annotation.Scheduled;




@Component
public class Scheduler {
	
	
	 @Scheduled(cron = "0 0 12 * * *")
	    public void branchScheduled(){
	       System.out.println("branch Scheduler is running"+new Date());
	    }
	 
	 @Scheduled(cron = "0 05 12 * * *")
	    public void countryScheduled(){
	       System.out.println("country Scheduler is running"+new Date());
	    }
	 
	 @Scheduled(cron = "0 10 12 * * *")
	    public void regionScheduled(){
	       System.out.println("region Scheduler is running"+new Date());
	    }
	 
	 @Scheduled(cron = "0 15 12 * * *")
	    public void subregionScheduled(){
	       System.out.println("subregion Scheduler is running"+new Date());
	    }
	 @Scheduled(cron = "0 20 12 * * *")
	    public void dataPolling(){
	       System.out.println("dataPolling Scheduler is running"+new Date());
	    }

	 
}


