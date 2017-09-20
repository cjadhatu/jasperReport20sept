package com.java.main.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.CountryInputVO;
import com.java.main.dto.RegionInputDTO;
import com.java.main.dto.ValueDTO;
import com.java.main.utility.GetStringFromList;



@Repository
public class CountryDaoImpl implements CountryDao {

	@PersistenceContext
	private EntityManager em;
	
	public List<Object[]> getCountryDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);	
		sb.append(" group by jci_reporting_country,region,forecast_status");
		return em.createNativeQuery(sb.toString()).getResultList();
	}

	public List<Object[]> getCountrySummaryDetails(String queryString) {
		// TODO Auto-generated method stub
		StringBuffer sb=new StringBuffer(queryString);
		sb.append(" group by branch_code, forecast_status) x");
		return em.createNativeQuery(sb.toString()).getResultList();
	}
	
	
	
	
	@Transactional
	public void submitForecastOnCountry(CountryInputVO input,
			String attribute, String user_role,String forecastId) {
	List<String> regionList=new ArrayList<String>();
	String regions="";
	List<String> countryList=new ArrayList<String>();
	String countries="";

	//old code start
	/*String querystring="update Opportunity op  set op.modifiedBy=?"
			+ ", op.modifiedDate=?"
			+ " , op.modifiedTimezonekey=?"
			+ " ,op.forecastStatus=?"
			+ " ,op.forecastDate=?"
			+ " ,op.forecastBy=?"
			+ " ,op.submitByRole=?"
			+ " ,op.submitFlag=?"
			+ " ,op.submitDate=?"
			+ " ,op.submitTimezonekey=?"
			
			//+ " forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,"
			+ " ,op.forecastId=?";
	StringBuffer sb=new StringBuffer(querystring);
*/	
// old code end
	
	
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
	
//OLD code start
	
	/*


if(input.getRegions()!=null && !(input.getRegions().isEmpty()) && input.getRegions().get(0).getName()!=null){
	for(ValueDTO valueDTO:input.getRegions()){
		regionList.add(valueDTO.getName());
	}
	 regions=GetStringFromList.getStringFromListComma(regionList);
	 sb.append(" where  op.region in (:regions)");//WHERE name IN (:names)
}
if(input.getCountries()!=null && !(input.getCountries().isEmpty()) && input.getCountries().get(0).getName()!=null){
	for(ValueDTO valueDTO:input.getCountries()){
		countryList.add(valueDTO.getName());
	}
	 countries=GetStringFromList.getStringFromListComma(regionList);
	 //sb.append(" and  jci_reporting_country in "+countries);
	 sb.append(" and   op.jciReportingCountry in (:countries)");//WHERE name IN (:names)
}
Query query=em.createQuery(sb.toString());
query.setParameter(1,attribute);
query.setParameter(2,new Date());
query.setParameter(3,new Date());
query.setParameter(4,"Submitted");
query.setParameter(5,new Date());
query.setParameter(6,attribute);
query.setParameter(7,user_role);
query.setParameter(8,"Y");
query.setParameter(9,new Date());
query.setParameter(10,new Date());
query.setParameter(11,forecastId);
//query.setParameter(12,"India");
if(input.getRegions()!=null && !(input.getRegions().isEmpty()) && input.getRegions().get(0).getName()!=null){
	query.setParameter("regions", regionList);
}
if(input.getCountries()!=null && !(input.getCountries().isEmpty()) && input.getCountries().get(0).getName()!=null){
	query.setParameter("countries", countryList);
}
*/
//OLD code End
	
	
	

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
	


Query query=em.createNativeQuery(sb.toString());

	
query.executeUpdate();
System.out.println("Submit Forcast on countries  Success");
	

		
	}

	public List<Object[]> getLOBCountryDetails(String string) {
		StringBuffer sb=new StringBuffer(string);
		//sb.append(" group by jci_reporting_country,lob,forecast_status");//Old query before sorting
		sb.append(" group by jci_reporting_country,lob,forecast_status order by total_factored_amount desc");
		return em.createNativeQuery(sb.toString()).getResultList();
}

}
