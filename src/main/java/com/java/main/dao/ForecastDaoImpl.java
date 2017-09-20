package com.java.main.dao;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.java.main.dto.AccountNameInputVO;
import com.java.main.dto.OpportunityDTO;
import com.java.main.dto.ReportGridInputMultipleVO;
import com.java.main.dto.ReportOutPutVO;
import com.java.main.dto.SaveOpportunityInputVO;
import com.java.main.dto.ValueDTO;
import com.java.main.model.Opportunity;
import com.java.main.service.DateUtility;
import com.java.main.utility.DataTypeConvert;
import com.java.main.utility.GetStringFromList;
@Repository
public class ForecastDaoImpl implements ForecastDao {
	@PersistenceContext
	private EntityManager em;
	
	
//DB DEV Start
	
	/*private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-54-163-236-33.compute-1.amazonaws.com:5432/d16g9mvi06kecr?sslmode=require";
	private static final String DB_USER = "kwxishotevzmfy";
	private static final String DB_PASSWORD = "1d11b86ebd719ce4887dbcb2848f8e5276cde7b26075c855dfff2c28fc2d37d3";
	*/
	
	//DB DEV END
	
	//DB QA START
	
	private static final String DB_DRIVER = "org.postgresql.Driver";
	private static final String DB_CONNECTION = "jdbc:postgresql://ec2-34-224-179-91.compute-1.amazonaws.com:5432/dbicmk941kpdb6?sslmode=require";
	private static final String DB_USER = "u7f7mara8qlsu5";
	private static final String DB_PASSWORD = "ped4485b3eed009a59aae25f87121b83641e7bc14168632c78adeb88fb239e7a8";

	//DB QA END

	
	

	//DB PROD NEW Start
	
	/*	private static final String DB_DRIVER = "org.postgresql.Driver";
		private static final String DB_CONNECTION = "jdbc:postgresql://ec2-34-230-241-132.compute-1.amazonaws.com:5432/d99qbjplusk6ss?sslmode=require";
		private static final String DB_USER = "uc3d7fkbkcr02m";
		private static final String DB_PASSWORD = "pff09ba2dbb843179e0ea3cb7030e244c5353e1776bb602cc9646060db56a16a9";
*/
	
	
	//DB PROD NEW END

	
	
	//DB PROD OLD Start
	
	/*	private static final String DB_DRIVER = "org.postgresql.Driver";
		private static final String DB_CONNECTION = "jdbc:postgresql://ec2-34-204-159-194.compute-1.amazonaws.com:5432/d99qbjplusk6ss?sslmode=require";
		private static final String DB_USER = "u2k71berfe7uj5";
		private static final String DB_PASSWORD = "p763b55efc9de27c21ffd390e4e4202601df3f1f31fc8907d07857051d4a3060c";

	
*/	
	//DB PROD OLD END
	
	
	public List<Object[]> getOpportunities(String globalId) {
		/*branch;
		private String lob;
		private String Currency;
		private String factAmtLocalCur;
		private String unfactAmtLocalCur;
		private String salesManager;
		private String Leadsource;
		private String mustWinflag;
		*/
		
		/*String queryString="select opp_id,account_name,opp_name,close_date,converted_unfactored_amt,converted_factored_amt,gross_margin,stagename,opp_number,probability,sales_person,opp_status, "
		+ " branch_code,lob,currency,factored_amount,unfactored_amount,sales_manager,lead_source,must_win  "
		+ " from webforecastdev.opportunity_iec ";
*/
		String queryString="select opp_id,account_name,opp_name,close_date,converted_unfactored_amt,converted_factored_amt,gross_margin,stagename,opp_number,probability,sales_person,opp_status, "
				+ " branch_code,lob,currency,factored_amount,unfactored_amount,sales_manager,lead_source,must_win ,forecast_status "
				+ " from webforecastdev.opportunity_iec where region in ('India')  and opp_status='A'";
		
		/*String queryString="select opp_id,account_name,opp_name,close_date,unfactored_amount,factored_amount,gross_margin,stagename,opp_number,probability "
				+ " from webforecastdev.opportunity_iec where region in ('China','India') and opp_id in (4605106,4605391,4605592) ";
		*/
		
		return em.createNativeQuery(queryString).getResultList();
	}
@Transactional
	public Opportunity updateOpportunity(Opportunity op, String globalId, String userRole) {
	
		// TODO Auto-generated method stub
	//String queryString="update Opportunity op set op.accountName=?, op.oppNumber=?,  op.oppName=?, op.grossMargin=?, op.probability=? where op.oppId=?";
	//String queryString="update Opportunity op set op.grossMargin=?, op.unFactoredAmount=?, op.factoredAmount=?,op.closeDate=? where op.oppId=?";
	String queryString="update Opportunity op set op.grossMargin=?, op.unFactoredAmount=?,"
			+ "  op.factoredAmount=?,op.closeDate=?, "
			+ " op.modifiedBy=?,op.modifiedDate=?,op.modifiedTimezonekey=?, "
			+ " op.forecastStatus=?,op.forecastDate=?,op.forecastBy=?,op.stageName=?,op.probability=?,op.currency=?,op.convertedFactoredAmt=?,op.convertedUnfactoredAmt=? where op.oppId=?";
		
	//ordering of update fields marginpct_iec,Unfactored Amount,Factored Amount,close_Date
	Query query=em.createQuery(queryString);
	query.setParameter(1,op.getGrossMargin());
	query.setParameter(2,op.getUnFactoredAmount());
	query.setParameter(3,op.getFactoredAmount());
	query.setParameter(4,op.getCloseDate());
	query.setParameter(5,globalId);
	query.setParameter(6,new Date());
	query.setParameter(7,new Date());
	query.setParameter(8,"In Progress");
	query.setParameter(9,new Date());
	query.setParameter(10,userRole);
	query.setParameter(11,op.getStageName());
	query.setParameter(12,op.getProbability());
	query.setParameter(13,op.getCurrency());
	query.setParameter(14,op.getConvertedFactoredAmt());
	query.setParameter(15,op.getConvertedUnfactoredAmt());
	query.setParameter(16,op.getOppId());
	query.executeUpdate();
	System.out.println("Update Success");
		return null;
	}
	
	
	/*public UserDetails getUserDetailsByGlobalId(String globalId) {
		// TODO Auto-generated method stub
		//String  queryString="select role,region__c,country,branch_code__c from webforecastdev.user where alias ='"+globalId+"'";
		String  queryString="select aproleid.rolename,usrapp.globalid,usrapp.maproleid from webforecastdev.approlemaster aproleid INNER JOIN webforecastdev.userapprolemap usrapp ON aproleid.roleid=usrapp.approleid WHERE usrapp.globalid='"+globalId+"'";
		List<Object[]> objs=em.createNativeQuery(queryString).getResultList();
		List<UserDetails> us=new ArrayList<UserDetails>();
		for(Object[] obj:objs){
			UserDetails u=new UserDetails();
			u.setRoleName((String)obj[0]);
			u.setGlobleId((String)obj[1]);
			u.setMaproleid((Integer)obj[2]);
			
			us.add(u);	
			
		}
		return us.get(0);
	}
*/
@Transactional
public Opportunity deleteOpportunity(Opportunity op, String globalId, String userRole) {

	// TODO Auto-generated method stub
//String queryString="update Opportunity op set op.accountName=?, op.oppNumber=?,  op.oppName=?, op.grossMargin=?, op.probability=? where op.oppId=?";
String queryString="update Opportunity op set op.oppStatus=? "
		+ " ,op.modifiedBy=?,op.modifiedDate=?,op.modifiedTimezonekey=?, "
		+ "  op.forecastStatus=?,op.forecastDate=?,op.forecastBy=?"
		  + "  where op.oppId=?";

//ordering of update fields marginpct_iec,Unfactored Amount,Factored Amount,close_Date
Query query=em.createQuery(queryString);
query.setParameter(1,"I");
query.setParameter(2,globalId);
query.setParameter(3,new Date());
query.setParameter(4,new Date());
query.setParameter(5,"In Progress");
query.setParameter(6,new Date());
query.setParameter(7,userRole);
query.setParameter(8,op.getOppId());
query.executeUpdate();
System.out.println("Delete Success");
	return null;
}
public List<Object[]> getDynamicOpportunity(ReportGridInputMultipleVO input) {
	// TODO Auto-generated method stub
	
	ReportOutPutVO response=new ReportOutPutVO();
	String sources = "";
	String regions = "";
	String subRegions = "";
	String countries = "";
	String branches = "";
	String queryString = " where 1=1 ";
	StringBuilder sb = new StringBuilder();
	sb.append(" where 1=1 ");
	/*if (input.getTypeSources() != null
			&& !(input.getTypeSources().isEmpty()) && input.getTypeSources().get(0).getName()!=null) {
		sources = getCommaSperatedStringByList(input.getTypeSources());
		sb.append(" and opp_data_source in ");
		sb.append(sources);
		queryString = queryString + " and opp_data_source in " + sources;
	}*/
	if (input.getRegions() != null && !(input.getRegions().isEmpty())) {
		regions = getCommaSperatedStringByList(input.getRegions());
		sb.append(" and region in ");
		sb.append(regions);
	}
	if (input.getSubregions() != null && !(input.getSubregions().isEmpty())) {
		
		if(input.getSubregions().get(0).getName()!=null && !("NA".equalsIgnoreCase(input.getSubregions().get(0).getName()))  && !("".equalsIgnoreCase(input.getSubregions().get(0).getName()))){
		subRegions = getCommaSperatedStringByList(input.getSubregions());
		sb.append(" and sub_region in ");
		sb.append(subRegions);
		}
		else{
			
		}
	}
	if (input.getCountries() != null && !(input.getCountries().isEmpty())) {
		countries = getCommaSperatedStringByList(input.getCountries());
		if(input.getCountries().get(0).getName()!=null){
		sb.append(" and jci_reporting_country in ");
		sb.append(countries);
		}
		else{
			
		}
	}
	if (input.getBranches() != null && !(input.getBranches().isEmpty())) {
		branches = getCommaSperatedStringByList(input.getBranches());
		if(input.getBranches().get(0).getName()!=null)
		{
		sb.append(" and branch_code in ");
		sb.append(branches);
		}
		else{
			
		}
	}
//sb.append("and opp_data_for_month ='Apr-2017' ");
	String finalqueryString="select opp_id,account_name,opp_name,close_date,converted_unfactored_amt,converted_factored_amt,gross_margin,stagename,opp_number,probability,sales_person,opp_status, "
			+ " branch_code,lob,currency,factored_amount,unfactored_amount,sales_manager,lead_source,must_win,forecast_status,opp_data_source,sfid ,jci_reporting_country,sub_region,region "
			+ " from webforecastdev.opportunity_iec"+sb.toString()+" and opp_status='A' and opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"' order by converted_factored_amt desc";
	//System.out.println(finalqueryString);
		
	return em.createNativeQuery(finalqueryString).getResultList();
}
public String getCommaSperatedStringByList(List<ValueDTO> list) {
	List<String> strings = new ArrayList<String>();
	for (ValueDTO dto : list) {
		strings.add(dto.getName());
	}

	return GetStringFromList.getStringFromListComma(strings);
}

@Transactional
public void submitForecastBFC(OpportunityDTO op,String globalId,String userRole) {

	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFC="update webforecastdev.opportunity_iec  set modified_by=?, modified_date=?, modified_timezonekey=?,forecast_status=?,"
		+ " forecast_date=?,forecast_by=?,submit_by_role=?,submit_flag=?,submit_date=?,"
		+ " submit_timezonekey=?,forecast_factored_amt=?,forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,forecast_id=? "
		+ "  where opp_id=?";

Query query=em.createNativeQuery(queryBranchFC);
query.setParameter(1,globalId);
query.setParameter(2,new Date());
query.setParameter(3,new Date());
query.setParameter(4,"Submitted");
query.setParameter(5,new Date());

query.setParameter(6,globalId);
query.setParameter(7,userRole);
query.setParameter(8,"Y");
query.setParameter(9,new Date());
query.setParameter(10,new Date());

if(op.getFactAmtLocalCur()!=null){
query.setParameter(11,DataTypeConvert.getBigDecFromCommaSepString(op.getFactAmtLocalCur()));
}
else{
	query.setParameter(11,new BigDecimal(0));
	
}
if(op.getUnfactAmtLocalCur()!=null){
	query.setParameter(12,DataTypeConvert.getBigDecFromCommaSepString(op.getUnfactAmtLocalCur()));
}
else{
	query.setParameter(12,new BigDecimal(0));
	
}
if(op.getFactAmt()!=null){
	query.setParameter(13,DataTypeConvert.getBigDecFromCommaSepString(op.getFactAmt()));
}
else{
	query.setParameter(13,new BigDecimal(0));
	
}
if(op.getUnfactAmt()!=null){
	query.setParameter(14,DataTypeConvert.getBigDecFromCommaSepString(op.getUnfactAmt()));
}
else{
	query.setParameter(14,new BigDecimal(0));
	
}

//DataTypeConvert.getBigDecFromCommaSepString(op.getFactAmt());



query.setParameter(15,op.getForecastId());
query.setParameter(16,op.getOppId());


query.executeUpdate();
System.out.println("Submit FC  Success");
	
}



@Transactional
public void submitForecastSRFC(OpportunityDTO op,String userName,String userRole) {

	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFC="update opportunity_iec op set op.modified_by=?, op.modified_date=?, op.modified_timezonekey=?,op.forecast_status=?,"
		+ " op.forecast_date=?,op.forecast_by=?,op.submit_by_role=?,op.submit_flag=?,op.submit_date=?,"
		+ " op.submit_timezonekey=?,op.forecast_factored_amt=?,op.forecast_unfactored_amt=?,op.forecast_factored_usdamt=?,op.forecast_unfactored_usdamt=? "
		+ "  where op.oppId=?";

Query query=em.createQuery(queryBranchFC);
query.setParameter(1,userName);
query.setParameter(2,new Date());
query.setParameter(3,new Date());
query.setParameter(4,"Submitted");
query.setParameter(5,new Date());

query.setParameter(6,userName);
query.setParameter(7,userRole);
query.setParameter(8,"Y");
query.setParameter(9,new Date());
query.setParameter(10,new Date());
query.setParameter(11,new BigDecimal(op.getFactAmtLocalCur()));
query.setParameter(12,new BigDecimal(op.getUnfactAmtLocalCur()));
query.setParameter(13,new BigDecimal(op.getFactAmt().substring(1)));
query.setParameter(14,new BigDecimal(op.getUnfactAmt().substring(1)));
query.setParameter(15,op.getOppId());


query.executeUpdate();
System.out.println("Submit FC  Success");
	
}



@Transactional
public void submitForecastCFC(OpportunityDTO op,String userName,String userRole) {

	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFC="update opportunity_iec  set modified_by=?, modified_date=?, modified_timezonekey=?,forecast_status=?,"
		+ " forecast_date=?,forecast_by=?,submit_by_role=?,submit_flag=?,submit_date=?,"
		+ " submit_timezonekey=?,forecast_factored_amt=?,forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=? "
		+ "  where oppId=?";

Query query=em.createQuery(queryBranchFC);
query.setParameter(1,userName);
query.setParameter(2,new Date());
query.setParameter(3,new Date());
query.setParameter(4,"Submitted");
query.setParameter(5,new Date());

query.setParameter(6,userName);
query.setParameter(7,userRole);
query.setParameter(8,"Y");
query.setParameter(9,new Date());
query.setParameter(10,new Date());
query.setParameter(11,new BigDecimal(op.getFactAmtLocalCur()));
query.setParameter(12,new BigDecimal(op.getUnfactAmtLocalCur()));
query.setParameter(13,new BigDecimal(op.getFactAmt().substring(1)));
query.setParameter(14,new BigDecimal(op.getUnfactAmt().substring(1)));
query.setParameter(15,op.getOppId());


query.executeUpdate();
System.out.println("Submit FC  Success");
	
}



@Transactional
public void submitForecastRFC(OpportunityDTO op,String userName,String userRole) {

	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFC="update opportunity_iec op set op.modified_by=?, op.modified_date=?, op.modified_timezonekey=?,op.forecast_status=?,"
		+ " op.forecast_date=?,op.forecast_by=?,op.submit_by_role=?,op.submit_flag=?,op.submit_date=?,"
		+ " op.submit_timezonekey=?,op.forecast_factored_amt=?,op.forecast_unfactored_amt=?,op.forecast_factored_usdamt=?,op.forecast_unfactored_usdamt=? "
		+ "  where op.oppId=?";

Query query=em.createQuery(queryBranchFC);
query.setParameter(1,userName);
query.setParameter(2,new Date());
query.setParameter(3,new Date());
query.setParameter(4,"Submitted");
query.setParameter(5,new Date());

query.setParameter(6,userName);
query.setParameter(7,userRole);
query.setParameter(8,"Y");
query.setParameter(9,new Date());
query.setParameter(10,new Date());
query.setParameter(11,new BigDecimal(op.getFactAmtLocalCur()));
query.setParameter(12,new BigDecimal(op.getUnfactAmtLocalCur()));
query.setParameter(13,new BigDecimal(op.getFactAmt().substring(1)));
query.setParameter(14,new BigDecimal(op.getUnfactAmt().substring(1)));
query.setParameter(15,op.getOppId());


query.executeUpdate();
System.out.println("Submit FC  Success");
	
}
public BigDecimal getConvertAmount(BigDecimal localCurAmt,String isFactAmt,String curCode,String sourceType) {
	// TODO Auto-generated method stub
	String queryString="";
	String type="";
	if("Wormald".equalsIgnoreCase(sourceType.trim())){
		type="tycowormald";
	}
	if("WW".equalsIgnoreCase(sourceType.trim())){
		type="tycoww";
	}
	if("OpenGlobe".equalsIgnoreCase(sourceType.trim())){
		type="legacybe";
		
	}
	if("Y".equalsIgnoreCase(isFactAmt))
	{
		if("OpenGlobe".equalsIgnoreCase(sourceType.trim()))
		{
			queryString="select " +localCurAmt+"/ webforecastdev.fn_conv_rate_"+type+"('"+curCode+"','"+new Date()+"')";	
		}
		else
		{
			queryString="select " +localCurAmt+"/ webforecastdev.fn_conv_rate_"+type+"('"+curCode+"')";
		}
	 
	}
	else
	{
		if("OpenGlobe".equalsIgnoreCase(sourceType.trim()))
		{
			queryString="select " +localCurAmt+"/ webforecastdev.fn_conv_rate_"+type+"('"+curCode+"','"+new Date()+"')";	
		}
		else
		{
			queryString="select " +localCurAmt+"/ webforecastdev.fn_conv_rate_"+type+"('"+curCode+"')";
		}
	 
	}
	BigDecimal bd=(BigDecimal) em.createNativeQuery(queryString).getSingleResult();
	return bd.setScale(2, BigDecimal.ROUND_UP);
}



//***************Batch Update using JDBC


@Transactional
public void submitForecastBFCJdbcBatchUpdate(List<OpportunityDTO> op,String globalId,String userRole)throws SQLException {

	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFCBatchUpdate="update webforecastdev.opportunity_iec  set modified_by=?, modified_date=?, modified_timezonekey=?,forecast_status=?,"
		+ " forecast_date=?,forecast_by=?,submit_by_role=?,submit_flag=?,submit_date=?,"
		+ " submit_timezonekey=?,forecast_factored_amt=?,forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,forecast_id=? "
		+ "  where opp_id=?";

Connection dbConnection = null;
PreparedStatement preparedStatement = null;

try
{
dbConnection = getDBConnection();
preparedStatement = dbConnection.prepareStatement(queryBranchFCBatchUpdate);

dbConnection.setAutoCommit(false);

for(OpportunityDTO op1:op){
	preparedStatement.setString(1, globalId);
	preparedStatement.setDate(2,new java.sql.Date(0));
	preparedStatement.setDate(3,new java.sql.Date(0));
	preparedStatement.setString(4, "Submitted");
	preparedStatement.setDate(5,new java.sql.Date(0));
	preparedStatement.setString(6, globalId);
	preparedStatement.setString(7, userRole);
	preparedStatement.setString(8, "Y");
	preparedStatement.setDate(9,new java.sql.Date(0));
	preparedStatement.setDate(10,new java.sql.Date(0));
	
	if(op1.getFactAmtLocalCur()!=null){
		preparedStatement.setBigDecimal(11, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmtLocalCur()));
		
		}
		else{
			preparedStatement.setBigDecimal(11, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmtLocalCur()!=null){
			preparedStatement.setBigDecimal(12, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmtLocalCur()));
		}
		else{
			preparedStatement.setBigDecimal(12, new BigDecimal(0));
			
		}
		if(op1.getFactAmt()!=null){
			preparedStatement.setBigDecimal(13, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(13, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmt()!=null){
			preparedStatement.setBigDecimal(14, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(14, new BigDecimal(0));
			
		}	
	preparedStatement.setString(15, op1.getForecastId());
	preparedStatement.setBigDecimal(16, op1.getOppId());
	preparedStatement.addBatch();
}

preparedStatement.executeBatch();

dbConnection.commit();


}
catch (SQLException e) {
    e.printStackTrace();
	System.out.println(e.getMessage());
	dbConnection.rollback();

} finally {

	if (preparedStatement != null) {
		preparedStatement.close();
	}

	if (dbConnection != null) {
		dbConnection.close();
	}

}






/*dbConnection=JDBCPostGres.getConnection();
try{
preparedStatement = dbConnection.prepareStatement(queryBranchFCBatchUpdate);

dbConnection.setAutoCommit(false);
int count=1;
for(OpportunityDTO op1:op){
	if(count==1)
	{
	preparedStatement.setString(1, globalId);
	preparedStatement.setDate(2,new java.sql.Date(0));
	preparedStatement.setDate(3,new java.sql.Date(0));
	preparedStatement.setString(4, "Submitted");
	preparedStatement.setDate(5,new java.sql.Date(0));
	preparedStatement.setString(6, globalId);
	preparedStatement.setString(7, userRole);
	preparedStatement.setString(8, "Y");
	preparedStatement.setDate(9,new java.sql.Date(0));
	preparedStatement.setDate(10,new java.sql.Date(0));
	preparedStatement.setBigDecimal(11, new BigDecimal(0));
	preparedStatement.setBigDecimal(12, new BigDecimal(0));
	preparedStatement.setBigDecimal(13, new BigDecimal(0));
	preparedStatement.setBigDecimal(14, new BigDecimal(0));
	if(op1.getFactAmtLocalCur()!=null){
		preparedStatement.setBigDecimal(11, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmtLocalCur()));
		
		}
		else{
			preparedStatement.setBigDecimal(11, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmtLocalCur()!=null){
			preparedStatement.setBigDecimal(12, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmtLocalCur()));
		}
		else{
			preparedStatement.setBigDecimal(12, new BigDecimal(0));
			
		}
		if(op1.getFactAmt()!=null){
			preparedStatement.setBigDecimal(13, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(13, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmt()!=null){
			preparedStatement.setBigDecimal(14, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(14, new BigDecimal(0));
			
		}
		
	preparedStatement.setString(15, "aa");
	preparedStatement.setBigDecimal(16, new BigDecimal(0));
	preparedStatement.setString(15, op1.getForecastId());
	preparedStatement.setBigDecimal(16, op1.getOppId());
	preparedStatement.addBatch();
	break;
	}
}
*/
/*int[] countt=preparedStatement.executeBatch();

System.out.println("-----------batch count record"+countt);

dbConnection.commit();
}
 catch (SQLException e) {
	// TODO Auto-generated catch block
	//e.printStackTrace();
	e.printStackTrace();
	e.getNextException();
}
finally
{
	try {
		dbConnection.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}*/
System.out.println("Submit FC JDBC  Success");
	
}

//*************Re-Submit Forecast Start****************
public void reSubmitForecastBFCJdbcBatchUpdate(List<OpportunityDTO> op,String globalId,String userRole)throws SQLException {


	// TODO Auto-generated method stub
//String userName="cyasin";
String queryBranchFCBatchUpdate="update webforecastdev.opportunity_iec  set modified_by=?, modified_date=?, modified_timezonekey=?,forecast_status=?,"
		+ " forecast_date=?,forecast_by=?,resubmit_by_role=?,resubmit_flag=?,resubmit_date=?,"
		+ " resubmit_timezonekey=?,forecast_factored_amt=?,forecast_unfactored_amt=?,forecast_factored_usdamt=?,forecast_unfactored_usdamt=?,forecast_id=? "
		+ "  where opp_id=?";

Connection dbConnection = null;
PreparedStatement preparedStatement = null;

try
{
dbConnection = getDBConnection();
preparedStatement = dbConnection.prepareStatement(queryBranchFCBatchUpdate);

dbConnection.setAutoCommit(false);

for(OpportunityDTO op1:op){
	preparedStatement.setString(1, globalId);
	preparedStatement.setDate(2,new java.sql.Date(0));
	preparedStatement.setDate(3,new java.sql.Date(0));
	preparedStatement.setString(4, "Submitted");
	preparedStatement.setDate(5,new java.sql.Date(0));
	preparedStatement.setString(6, globalId);
	preparedStatement.setString(7, userRole);
	preparedStatement.setString(8, "Y");
	preparedStatement.setDate(9,new java.sql.Date(0));
	preparedStatement.setDate(10,new java.sql.Date(0));
	
	if(op1.getFactAmtLocalCur()!=null){
		preparedStatement.setBigDecimal(11, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmtLocalCur()));
		
		}
		else{
			preparedStatement.setBigDecimal(11, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmtLocalCur()!=null){
			preparedStatement.setBigDecimal(12, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmtLocalCur()));
		}
		else{
			preparedStatement.setBigDecimal(12, new BigDecimal(0));
			
		}
		if(op1.getFactAmt()!=null){
			preparedStatement.setBigDecimal(13, DataTypeConvert.getBigDecFromCommaSepString(op1.getFactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(13, new BigDecimal(0));
			
		}
		if(op1.getUnfactAmt()!=null){
			preparedStatement.setBigDecimal(14, DataTypeConvert.getBigDecFromCommaSepString(op1.getUnfactAmt()));
		}
		else{
			preparedStatement.setBigDecimal(14, new BigDecimal(0));
			
		}	
	preparedStatement.setString(15, op1.getForecastId());
	preparedStatement.setBigDecimal(16, op1.getOppId());
	preparedStatement.addBatch();
}

preparedStatement.executeBatch();

dbConnection.commit();


}
catch (SQLException e) {
    e.printStackTrace();
	System.out.println(e.getMessage());
	dbConnection.rollback();

} finally {

	if (preparedStatement != null) {
		preparedStatement.close();
	}

	if (dbConnection != null) {
		dbConnection.close();
	}

}


System.out.println("Submit FC JDBC  Success");
	

	
	
}


//Re-Submit Forecast END





private static Connection getDBConnection() {

	Connection dbConnection = null;

	try {

		Class.forName(DB_DRIVER);

	} catch (ClassNotFoundException e) {

		System.out.println(e.getMessage());

	}

	try {

		dbConnection = DriverManager.getConnection(
                          DB_CONNECTION, DB_USER,DB_PASSWORD);
		return dbConnection;

	} catch (SQLException e) {

		System.out.println(e.getMessage());

	}

	return dbConnection;

}
public List<Object[]> getAddNewOpportunity(String globalId) {
	// TODO Auto-generated method stub
	
	
	String queryString="select datasource, sfid,globalid, userid, user_name, manager_name, forecast_rolecode, region,subregion,country,branch_code,lob from webforecastdev.forecast_user_rolemap  where s.globalid ='"+globalId+"'";
	
	
	return em.createNativeQuery(queryString).getResultList();
	
	
}

@Transactional
public String SaveAddNewOpportunity(SaveOpportunityInputVO saveOpportunityInputVO, String globalId,String userRole,String userName) {
	
	String msg="";
	
	try
	{
		getPermissionFunctionCalled(new String());
	Opportunity opportunity=OpportunitysaveOpportunity(saveOpportunityInputVO,globalId,userName);
	  em.persist(opportunity);
	  msg="Success";
	}
	catch(Exception e)
	{
		msg="Failed";
		e.printStackTrace();
	}
	// TODO Auto-generated method stub
	return msg;
}
private Opportunity OpportunitysaveOpportunity(SaveOpportunityInputVO saveOpportunityInputVO,String globalId,String userName) {
	String probabilityVal="";
	Opportunity opportunity=new Opportunity();
	opportunity.setOppDataSource(saveOpportunityInputVO.getOppDataSource());
	opportunity.setRegion(saveOpportunityInputVO.getRegion());
	opportunity.setSubRegion(saveOpportunityInputVO.getSubRegion());
	opportunity.setJciReportingCountry(saveOpportunityInputVO.getJciReportingCountry());
	opportunity.setBranchCode(saveOpportunityInputVO.getBranchCode());
	opportunity.setOppNumber(saveOpportunityInputVO.getOppNumber());
	opportunity.setOppName(saveOpportunityInputVO.getOppName());
	opportunity.setLeadSource(saveOpportunityInputVO.getLeadSource());
	if(saveOpportunityInputVO.getForcastCloseDate()!=null && !(saveOpportunityInputVO.getForcastCloseDate().equals("")))
	{
		System.out.println("-----saveOpportunityInputVO.getForcastCloseDate()"+saveOpportunityInputVO.getForcastCloseDate());
	opportunity.setCloseDate(DateUtility.getDateForEditOpportunity(saveOpportunityInputVO.getForcastCloseDate()));
	}
	opportunity.setStageName(saveOpportunityInputVO.getStageName());
/*	if( saveOpportunityInputVO.getProbability() != null &&  saveOpportunityInputVO.getProbability().length() > 0)
	{
	 probabilityVal = saveOpportunityInputVO.getProbability().substring(0, saveOpportunityInputVO.getProbability().length()-1);
	 System.out.println("--------------getProbability"+probabilityVal);
	}*/
	Character ch=saveOpportunityInputVO.getProbability().charAt(saveOpportunityInputVO.getProbability().length()-1);
	if("%".equalsIgnoreCase(ch.toString())){
		saveOpportunityInputVO.setProbability(saveOpportunityInputVO.getProbability().replace(saveOpportunityInputVO.getProbability().substring(saveOpportunityInputVO.getProbability().length()-1),"")) ;
		opportunity.setProbability(new BigDecimal(saveOpportunityInputVO.getProbability().trim()));
	}
	else{
		opportunity.setProbability(new BigDecimal(saveOpportunityInputVO.getProbability().trim()));
	}
	
	Character chmargin=saveOpportunityInputVO.getGrossMargin().charAt(saveOpportunityInputVO.getGrossMargin().length()-1);
	if("%".equalsIgnoreCase(chmargin.toString())){
		saveOpportunityInputVO.setGrossMargin(saveOpportunityInputVO.getGrossMargin().replace(saveOpportunityInputVO.getGrossMargin().substring(saveOpportunityInputVO.getGrossMargin().length()-1),"")) ;
		opportunity.setProbability(new BigDecimal(saveOpportunityInputVO.getGrossMargin().trim()));
	}
	else{
		opportunity.setGrossMargin(new BigDecimal(saveOpportunityInputVO.getGrossMargin()));
	}
	
	//opportunity.setGrossMargin(new BigDecimal(saveOpportunityInputVO.getGrossMargin()));
	if(saveOpportunityInputVO.getAccountName()!=null)
	{
	opportunity.setAccountName(saveOpportunityInputVO.getAccountName());
	}
	else
	{
		opportunity.setAccountName("");
	}
	opportunity.setFactoredAmount(new BigDecimal(saveOpportunityInputVO.getFactoredAmount()));
	opportunity.setUnFactoredAmount(new BigDecimal(saveOpportunityInputVO.getUnFactoredAmount()));
	opportunity.setSalesPerson(saveOpportunityInputVO.getSaleslead());
	opportunity.setSalesManager(saveOpportunityInputVO.getSalesManager());
	
	opportunity.setConvertedFactoredAmt(new BigDecimal(saveOpportunityInputVO.getFactAmtUsd()));
	opportunity.setConvertedUnfactoredAmt(new BigDecimal(saveOpportunityInputVO.getUnFactAmtUsd()));
	
	opportunity.setLob(saveOpportunityInputVO.getLob());
	opportunity.setCurrency(saveOpportunityInputVO.getCurrency());
	opportunity.setOppStatus("A");
	opportunity.setForecastStatus("In Progress");
	opportunity.setForecastDate(new Date());
	opportunity.setForecastBy(userName);
	opportunity.setModifiedBy(userName);
	opportunity.setModifiedDate(new Date());
	opportunity.setModifiedTimezonekey(new Date());
	opportunity.setCreatedBy(userName);
	opportunity.setCreatedDate(new Date());
	opportunity.setCreatedTimezonekey(new Date());
	opportunity.setRecStatus("Added");
	SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
	String oppdatadate = sdf.format(new Date());
	opportunity.setOppDataForMonth(oppdatadate);
	opportunity.setSalesManager(getManagerNameByGlobalId(globalId));
	// TODO Auto-generated method stub
	return opportunity;
}
public List<String> getLob() {
	// TODO Auto-generated method stub
	List<String> objects =em.createNativeQuery("select distinct forecast_lobname from webforecastdev.forecast_lobmaster order by 1").getResultList();
	return objects;
}
public List<String> getCurrency() {
	// TODO Auto-generated method stub
	List<String> objects =em.createNativeQuery("select currency from webforecastdev.vw_currency ORDER BY currency ASC").getResultList();
	return objects;
}
public List<Object[]> getAccountName(AccountNameInputVO accountNameInputVO) {
	// TODO Auto-generated method stub
	List<Object[]> objects =em.createNativeQuery("select sfid, name from webforecastdev.vw_sfdc_account_details where datasource ='" +accountNameInputVO.getSourceType()+"' limit 10").getResultList();
	return objects;
}
public String getRate(String string, String currency, String sourceType) {
	// TODO Auto-generated method stub
	String queryString="";
	String type="";
	if("Wormald".equalsIgnoreCase(sourceType.trim())){
		type="tycowormald";
	}
	if("WW".equalsIgnoreCase(sourceType.trim())){
		type="tycoww";
	}
	if("OpenGlobe".equalsIgnoreCase(sourceType.trim())){
		type="legacybe";
		
	}
	
		if("OpenGlobe".equalsIgnoreCase(sourceType.trim()))
		{
			queryString="select  webforecastdev.fn_conv_rate_"+type+"('"+currency+"','"+new Date()+"')";	
		}
		else
		{
			queryString="select  webforecastdev.fn_conv_rate_"+type+"('"+currency+"')";
		}
	 

	BigDecimal bd=(BigDecimal) em.createNativeQuery(queryString).getSingleResult();
	return bd.setScale(2, BigDecimal.ROUND_UP).toString();
}
public String getRefresh(String queryString) {
	// TODO Auto-generated method stub
	//em.createNativeQuery(queryString).getResultList();
	System.out.println("calling getRefresh dao  query "+queryString);
	String bd=(String) em.createNativeQuery(queryString).getSingleResult();
	System.out.println("calling getRefresh dao  SUCCESS");
	return null;
}

public String callDataPullFunction(String queryString) {
	// TODO Auto-generated method stub
	System.out.println("callDataPullFunction dao called querys is "+queryString);
	BigDecimal bd=(BigDecimal) em.createNativeQuery(queryString).getSingleResult();
	System.out.println("callDataPullFunction dao  SUCCESS");
	return null;
}
public List<String> getStage() {
	// TODO Auto-generated method stub
	//List<String> objects =em.createNativeQuery("select distinct stagename from webforecastdev.opportunity_iec").getResultList();
	List<String> objects =em.createNativeQuery("select distinct stage_name from webforecastdev.forecast_stage_master where isactive='A'").getResultList();
	
	return objects;
}
public String callFunctionForEditSaveDelete(String queryString) {
	// TODO Auto-generated method stub
	try {
		System.out.println("callFunctionForEditSaveDelete try dao called querys is "+queryString);
		String bd=(String) em.createNativeQuery(queryString).getSingleResult();
		System.out.println("callFunctionForEditSaveDelete dao  SUCCESS");
		return "SUCCESS";
	} catch (Exception e) {
		System.out.println("exception in callFunctionForEditSaveDelete  dao exception is "+e.getMessage());
		// TODO: handle exception
		return "FAILURE";
	}
	
}
public String callFunctionForSubmitForecast(String queryString) {
	// TODO Auto-generated method stub
	try {
		System.out.println("callFunctionForSubmitForecast try dao called querys is "+queryString);
		String bd=(String) em.createNativeQuery(queryString).getSingleResult();
		System.out.println("callFunctionForSubmitForecast dao  SUCCESS");
		return "SUCCESS";
	} catch (Exception e) {
		System.out.println("exception in callFunctionForEditSaveDelete  dao exception is "+e.getMessage());
		// TODO: handle exception
		return "FAILURE";
	}
	
}
public String getPermissionFunctionCalled(String queryString)
{
	// TODO Auto-generated method stub
	try {
		System.out.println("getPermissionFunctionCalled try dao called");
		em.createNativeQuery("SELECT webforecastdev.fn_provide_grants_to_opp()").getSingleResult();
		System.out.println("getPermissionFunctionCalled dao  SUCCESS");
		return "SUCCESS";
	} catch (Exception e) {
		System.out.println("exception in getPermissionFunctionCalled  dao exception is "+e.getMessage());
		// TODO: handle exception
		return "FAILURE";
	}
}
public String getManagerNameByGlobalId(String globalId) {
	// TODO Auto-generated method stub
	
	String rseponse="";
	try {
		System.out.println();
		List<String> objects =em.createNativeQuery("select  manager_name from webforecastdev.forecast_user_rolemap s where s.globalid = '"+globalId+"'").getResultList();
		if(!(objects.isEmpty())){
			rseponse=objects.get(0);
		}
		else{
			
		}
		return rseponse;
	} catch (Exception e) {
		System.out.println("Exception in getManagerNameByGlobalId dao exception is "+e.getMessage());
		return rseponse;
		// TODO: handle exception
	}
	
}
public String callTempAutoSchedule(String queryString) {
	// TODO Auto-generated method stub
	
	System.out.println(" **************** Schedular BranchAutoSubmission called date and time is "+new Date());
	/*String queryString="update webforecastdev.forecast_branch_summary set forecast_status ='System Submitted' "
			+ " where opp_data_for_month ='"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"'"
			+ "  and forecast_status = 'Submitted' "
			+ " and opp_status = 'A' ";
	*/
	
	String queryBranch="select webforecastdev.fn_autosubmit_forecast_data('"+new SimpleDateFormat("MMM-yyyy").format(new Date())+"','SR')";
	
	em.createNativeQuery(queryBranch).getSingleResult();
	System.out.println(" **************** Schedular BranchAutoSubmission Success "+new Date());
	return null;
}
public BigInteger isAllBranchSubmitted(String queryString) {
	// TODO Auto-generated method stub
	
	
	return (BigInteger)em.createNativeQuery(queryString).getSingleResult();
}
public BigInteger isAllCountrySubmitted(String queryString) {
	// TODO Auto-generated method stub
	return (BigInteger)em.createNativeQuery(queryString).getSingleResult();
}
public BigInteger isAllSubRegionSubmitted(String queryString) {
	// TODO Auto-generated method stub
	return (BigInteger)em.createNativeQuery(queryString).getSingleResult();
}


}
