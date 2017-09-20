package com.java.main.utility;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ckhan58 this class will be used as scheduler methods related to MDM
 *         updates will be kept inside this class
 */
@Component("masterChangePropogate")
public class ScheduleTask {

	@Autowired
	//private MDMUpdateService mdmUpdateService;

	public void updatePD2s() {
		
		System.out.println(" $$$$$$$$$$$$   Test scheduler   running time is " + new Date());
		/*mdmUpdateService.callMDMUpdateProc();
		mdmUpdateService.sendMailForFailedPD2s();*/

	}

}
