package com.java.main.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.BranchInputVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.GetStringFromList;
@Repository
public class BranchDaoImpl implements BranchDao {
	@PersistenceContext
	private EntityManager em;
	
	public List<String> getBranchesByRegion(String regionCode) {
		String querString="select distinct branch_code__c from webforecastdev.user where region__c ='"+regionCode+"' and branch_code__c !='null'";
		List<String> branches=new ArrayList<String>();
		 List<String> objects =em.createNativeQuery(querString).getResultList();
		for(String obj:objects){
			branches.add(obj);
		}
		 
		
		return branches;
	}

	public List<Object[]> getBranchDetails(String queryString) {
		// TODO Auto-generated method stub
		
		StringBuffer sb=new StringBuffer(queryString);	
		//sb.append(" group by opp_data_source,jci_reporting_country,region,sub_region,branch_code ,forecast_status,updated_by,updated_date");
		sb.append(" group by opp_data_source,jci_reporting_country,region,sub_region,branch_code ,forecast_status");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	public List<Object[]> getBranchSummaryDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" Group by branch_code, forecast_status  ) x");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	
	@Transactional
	public void submitForecastOnBranch(BranchInputVO input, String attribute,
			String user_role, String forecastId) {
		List<String> regionList=new ArrayList<String>();
		String regions="";
		List<String> countryList=new ArrayList<String>();
		String countries="";
		List<String> subRegionList=new ArrayList<String>();
		String subRegions="";
		List<String> branchList=new ArrayList<String>();
		String branches="";
		 Calendar calendar = Calendar.getInstance();
		    Timestamp currentTimestamp = new java.sql.Timestamp(calendar.getTime().getTime());
		    //System.out.println("currentTimestamp"+currentTimestamp);
		   
		String querystring="update webforecastdev.opportunity_iec  set modified_by="+"'"+attribute+"'"
				+ ", modified_date='"+currentTimestamp+"'"
				+ " ,modified_timezonekey='"+currentTimestamp+"'"
				+ " ,forecast_status="+"'Submitted'"
			+ " ,forecast_date='"+currentTimestamp+"'"
				+ " ,forecast_by="+"'"+attribute+"'"
				+ " ,submit_by_role="+"'"+user_role+"'"
				+ " ,submit_flag='Y'"
				 + " ,submit_date='"+currentTimestamp+"'"
				 + " ,submit_timezonekey='"+currentTimestamp+"'"
				
				//+ " forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,"
				+ " ,forecast_id="+"'"+forecastId+"'";
		StringBuffer sb=new StringBuffer(querystring);
		

		if(input.getRegions()!=null && !(input.getRegions().isEmpty()) && input.getRegions().get(0).getName()!=null){
			for(ValueDTO valueDTO:input.getRegions()){
				regionList.add(valueDTO.getName());
			}
			 regions=GetStringFromList.getStringFromListComma(regionList);
			 sb.append(" where  region in "+regions);//WHERE name IN (:names)
		}
		if(input.getCountries()!=null && !(input.getCountries().isEmpty()) && input.getCountries().get(0).getName()!=null){
			for(ValueDTO valueDTO:input.getCountries()){
				countryList.add(valueDTO.getName());
			}
			 countries=GetStringFromList.getStringFromListComma(countryList);
			 //sb.append(" and  jci_reporting_country in "+countries);
			 sb.append(" and   jci_reporting_country in "+countries);//WHERE name IN (:names)
		}
		if(input.getSubregions()!=null && !(input.getSubregions().isEmpty()) && input.getSubregions().get(0).getName()!=null){
			for(ValueDTO valueDTO:input.getSubregions()){
				subRegionList.add(valueDTO.getName());
			}
			 subRegions=GetStringFromList.getStringFromListComma(subRegionList);
			 //sb.append(" and  jci_reporting_country in "+countries);
			 sb.append(" and   subRegion in (:subRegions)");//WHERE name IN (:names)
		}
		if(input.getBranches()!=null && !(input.getBranches().isEmpty()) ){
			for(ValueDTO valueDTO:input.getBranches()){
				
					branchList.add(valueDTO.getName());
				
			}
			branches=GetStringFromList.getStringFromListComma(branchList);
			 sb.append(" and branch_code in "+branches);
		}
System.out.println("regions "+regionList);
System.out.println("countries "+countryList);
System.out.println("sub regions "+subRegionList);
System.out.println("branches "+branchList);

//Query query=em.createQuery(sb.toString());
Query query=em.createNativeQuery(sb.toString());
		/*query.setParameter(1,attribute);
		System.out.println("query.setParameter(1,attribute) "+attribute);
		query.setParameter(2,new Date());
System.out.println("query.setParameter(2,new Date())"+new Date());
		query.setParameter(3,new Date());
		System.out.println("query.setParameter(3,new Date())"+new Date());		
		query.setParameter(4,"Submitted");
		System.out.println("query.setParameter(4,Submitted");
		query.setParameter(5,new Date());
		System.out.println("query.setParameter(5,new Date())"+new Date());
		query.setParameter(6,attribute);
System.out.println("query.setParameter(6,attribute); "+attribute);
		query.setParameter(7,user_role);
System.out.println("query.setParameter(7,user_role)"+user_role);
		query.setParameter(8,"Y");
System.out.println("query.setParameter(8,Y"+"Y");
		query.setParameter(9,new Date());
		System.out.println("query.setParameter(9,new Date());"+new Date());
		query.setParameter(10,new Date());
		query.setParameter(11,forecastId);
System.out.println("query.setParameter(11,forecastId);"+forecastId);
		*///query.setParameter(12,"India");
		/*if(input.getRegions()!=null && !(input.getRegions().isEmpty()) && input.getRegions().get(0).getName()!=null){
			query.setParameter("regions", regions);
		}
		if(input.getCountries()!=null && !(input.getCountries().isEmpty()) && input.getCountries().get(0).getName()!=null){
			query.setParameter("countries", countries);
		}
		if(input.getSubregions()!=null && !(input.getSubregions().isEmpty()) && input.getSubregions().get(0).getName()!=null){
			query.setParameter("subRegions", subRegions);
		}
		if(input.getBranches()!=null && !(input.getBranches().isEmpty()) ){
			query.setParameter("branches", branches);
			 
		}
		*/
		
		
	/*if(input.getSubregions()!=null && !(input.getSubregions().isEmpty()) ){
		for(ValueDTO valueDTO:input.getSubregions()){
			if(!("NA".equalsIgnoreCase(valueDTO.getName()))){
			subRegionList.add(valueDTO.getName());
			}
		}
		subRegions=GetStringFromList.getStringFromListComma(subRegionList);
		 sb.append(" and sub_region in "+subRegions);
	}*/
	
		
	int i=query.executeUpdate();
	
	System.out.println("Submit Forcast on branch  Success return from query i"+i);
		

			
		}

	public List<Object[]> getLOBBranchDetails(String string) {
		StringBuffer sb=new StringBuffer(string);
		//sb.append(" group by branch_code,lob,forecast_status ");  //Old query before sorting
		sb.append(" group by branch_code,lob,forecast_status order by total_factored_amount desc ");
		return em.createNativeQuery(sb.toString()).getResultList();
}

}
