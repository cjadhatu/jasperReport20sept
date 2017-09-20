package com.java.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.SubRegionInputVO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.GetStringFromList;



@Repository
public class SubRegionDaoImpl implements SubRegionDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> getSubRegionDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" group by sub_region,region,forecast_status");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	public List<Object[]> getsubRegionSummaryDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" ) x");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	
	
	@Transactional
	public void submitForecastOnSubRegion(SubRegionInputVO input,
			String attribute, String user_role, String forecastId) {
		List<String> regionList=new ArrayList<String>();
		String regions="";
		List<String> countryList=new ArrayList<String>();
		String countries="";
		List<String> subRegionList=new ArrayList<String>();
		String subregions="";
		
	

		String querystring="update webforecastdev.opportunity_iec  set modified_by="+"'"+attribute+"'"
				//+ ", op.modified_date="+new Date()
				//+ " , op.modified_timezonekey="+new Date()
				+ " ,forecast_status="+"'Submitted'"
			//	+ " ,op.forecast_date="+new Date()
				+ " ,forecast_by="+"'"+attribute+"'"
				+ " ,submit_by_role="+"'"+user_role+"'"
				+ " ,submit_flag='Y'"
				// + " ,op.submit_date="+new Date()
				// + " ,op.submit_timezonekey="+new Date()
				
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
		if(input.getSubRegions()!=null && !(input.getSubRegions().isEmpty()) && input.getSubRegions().get(0).getName()!=null){
			for(ValueDTO valueDTO:input.getSubRegions()){
				subRegionList.add(valueDTO.getName());
			}
			 subregions=GetStringFromList.getStringFromListComma(subRegionList);
			 //sb.append(" and  jci_reporting_country in "+countries);
			 sb.append(" and   sub_region in "+subregions);//WHERE name IN (:names)
		}

		Query query=em.createNativeQuery(sb.toString());
		

	query.executeUpdate();
	System.out.println("Submit Forcast on Sub Region  Success");
		

			
		}

	public List<Object[]> getLOBSubRegionDetails(String string) {
		StringBuffer sb=new StringBuffer(string);
		//sb.append(" group by sub_region,lob,forecast_status"); // Old query before sorting
		sb.append(" group by sub_region,lob,forecast_status order by total_factored_amount desc");
		return em.createNativeQuery(sb.toString()).getResultList();
}
	
}
