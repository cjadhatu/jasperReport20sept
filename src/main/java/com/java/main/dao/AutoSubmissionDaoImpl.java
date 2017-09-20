package com.java.main.dao;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public class AutoSubmissionDaoImpl implements AutoSubmissionDao {
	@PersistenceContext
	private EntityManager em;
	
	
	@Transactional
	public String BranchAutoSubmission(String branchAutoQuery) {
		// TODO Auto-generated method stub
		
		System.out.println(" **************** Schedular BranchAutoSubmission called date and time is "+new Date());
		/*String queryString="update webforecastdev.forecast_branch_summary set forecast_status ='System Submitted' "
				+ " where opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'"
				+ "  and forecast_status = 'Submitted' "
				+ " and opp_status = 'A' ";
		*/
		
		String queryBranch="select webforecastdev.fn_autosubmit_forecast_data('"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"','B')";
		
		em.createNativeQuery(queryBranch).getSingleResult();
		System.out.println(" **************** Schedular BranchAutoSubmission Success "+new Date());
		return null;
	}
	@Transactional
	public String CountryAutoSubmission(String branchAutoQuery) {
		// TODO Auto-generated method stub
		System.out.println(" **************** Schedular CountryAutoSubmission called date and time is "+new Date());
		try {
			String queryBranch="select webforecastdev.fn_autosubmit_forecast_data('"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"','C')";
			
			em.createNativeQuery(queryBranch).getSingleResult();
			
		} catch (Exception e) {
			System.out.println(" &&&&&&&& Exception in  CountryAutoSubmission exception is "+e.getMessage());
			// TODO: handle exception
			
		}
		System.out.println(" **************** Schedular CountryAutoSubmission Success "+new Date());
		return null;
	}
	@Transactional
	public String SubRegionAutoSubmission(String branchAutoQuery) {
		// TODO Auto-generated method stub
		
		System.out.println(" **************** Schedular SubRegionAutoSubmission called date and time is "+new Date());
		/*String queryString="update webforecastdev.forecast_subregion_summary set forecast_status ='System Submitted' "
				+ " where opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'"
				+ "  and forecast_status = 'Submitted' "
				+ " and opp_status = 'A' ";
		*/try {
			String queryBranch="select webforecastdev.fn_autosubmit_forecast_data('"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"','SR')";
			
			em.createNativeQuery(queryBranch).getSingleResult();
			
			
		} catch (Exception e) {
			System.out.println(" &&&&&&&& Exception in  SubRegionAutoSubmission exception is "+e.getMessage());
			// TODO: handle exception
		}
		System.out.println(" **************** Schedular SubRegionAutoSubmission Success "+new Date());
		
		return null;
	}
	@Transactional
	public String RegionAutoSubmission(String branchAutoQuery) {
		// TODO Auto-generated method stub
		
		System.out.println(" **************** Schedular RegionAutoSubmission called date and time is "+new Date());
		/*String queryString="update webforecastdev.forecast_region_summary set forecast_status ='System Submitted' "
				+ " where opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'"
				+ "  and forecast_status = 'Submitted' "
				+ " and opp_status = 'A' ";
		*/
		try {
			String queryBranch="select webforecastdev.fn_autosubmit_forecast_data('"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"','R')";
			
			em.createNativeQuery(queryBranch).getSingleResult();
			
			
		} catch (Exception e) {
			System.out.println(" &&&&&&&& Exception in  RegionAutoSubmission exception is "+e.getMessage());
			// TODO: handle exception
		}
		System.out.println(" **************** Schedular RegionAutoSubmission Success "+new Date());
		return null;
	}
	@Transactional
	public String MounthlyDataPulling(String branchAutoQuery) {
		// TODO Auto-generated method stub
	String	queryString="Select webforecastdev.fn_execute_opp_data_routine()";

	System.out.println("callDataPullFunction Schedular called  date and time is "+new Date());
	BigDecimal bd=(BigDecimal) em.createNativeQuery(queryString).getSingleResult();
	System.out.println("callDataPullFunction  Schedular dao  SUCCESS");
	
	return null;
	}
	@Transactional
	public String AdminHQAutoSubmission(String branchAutoQuery) {
		// TODO Auto-generated method stub
		
		String queryString="update webforecastdev.forecast_country_summary set forecast_status ='System Submitted' "
				+ " where opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'"
				+ "  and forecast_status = 'Submitted' "
				+ " and opp_status = 'A' ";
		em.createNativeQuery(queryString).executeUpdate();
		return null;
	}
	public String BranchAutoSubmissionTemp(String branchAutoQuery) {
		// TODO Auto-generated method stub
		String	queryString="select webforecastdev.fn_autosubmit_forecast_data('May-2017','B')";

		System.out.println("BranchAutoSubmissionTemp Schedular called  date and time is "+new Date());
		BigDecimal bd=(BigDecimal) em.createNativeQuery(queryString).getSingleResult();
		System.out.println("BranchAutoSubmissionTemp  Schedular dao  SUCCESS");

		return null;
	}
	
}
