package com.java.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.GetStringFromList;



@Repository
public class RegionDaoImpl implements RegionDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> getRegionDetails(String queryString) {
		// TODO Auto-generated method stub
		//+ " group by region ";
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" group by region,forecast_status ");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	public List<Object[]> getRegionSummaryDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" ) x");
		return em.createNativeQuery(sb.toString()).getResultList();
	}
@Transactional
	public void submitForecastOnRegion(RegionInputDTO input,
			String attribute, String user_role,String forecastId) {
	List<String> regionList=new ArrayList<String>();
	String regions="";
	
/*String querystring="update webforecastdev.opportunity_iec  set modified_by="+"'"+new String(attribute)+"'"
		+ ", modified_date="+new Date()
		+ " , modified_timezonekey="+new Date()
		+ " ,forecast_status="+"'"+new String("Submitted")+"'"
		+ " ,forecast_date="+new Date()
		+ " ,forecast_by="+"'"+new String(attribute)+"'"
		+ " ,submit_by_role="+"'"+new String(user_role)+"'"
		+ " ,submit_flag="+"'"+new String("Y")+"'"
		+ " ,submit_date="+new Date()
		+ " ,submit_timezonekey="+new Date()
		
		//+ " forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,"
		+ " ,forecast_id="+"'"+new String(forecastId)+"'";*/
		//+ "  where  1=1 ";

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
	 //sb.append(" where  op.region =?");
	 sb.append(" where  region in "+regions);//WHERE name IN (:names)
	 //sb.append(" where  region in :resgns");
}
Query query=em.createNativeQuery(sb.toString());
int i=query.executeUpdate();
System.out.println("Submit Forcast on region  Success");
	

		
	}
public List<Object[]> getLobRegionDetails(String queryString) {
	// TODO Auto-generated method stub
	StringBuffer sb=new StringBuffer(queryString);
	//sb.append(" group by region,lob,forecast_status"); // Old query before sorting
	sb.append(" group by region,lob,forecast_status order by total_factored_amount desc");
	return em.createNativeQuery(sb.toString()).getResultList();
}

public List<Object[]> getLOBRegionSummaryDetails(String queryString) {
	// TODO Auto-generated method stub
	return em.createNativeQuery(queryString).getResultList();
}

	
}
