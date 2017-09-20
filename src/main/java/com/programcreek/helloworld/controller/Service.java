package com.programcreek.helloworld.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

public class Service {
	
	private static Connection conection = JDBCPostGres.getConnection();
	private  PreparedStatement statement = null;

	public RegionDTO getSubRegions(Integer regId)
	{
		RegionDTO rg = new RegionDTO();
		Integer branchTotal=0;
		Integer oppTotal=0;
		List<subRegions> subRegs = createSubRegions(regId);
		List<subRegions> finalSubRegs = new ArrayList<subRegions>();
		//old code Start
		/*for(subRegions sub: subRegs)
		{
			if(sub.getrId()== regId)
			{
				finalSubRegs.add(sub);
				rg.setBrTotal(sub.getBranches());
				rg.setOppsTotal(sub.getOpps());
				System.out.println(sub.getRegName());
			}
		}*/
		//old code END
		for(subRegions sub: subRegs)
		{
			if(sub.getrId()== regId)
			{
				finalSubRegs.add(sub);
				branchTotal=branchTotal+sub.getBranches();
				oppTotal=oppTotal+sub.getOpps();
				//rg.setBrTotal(sub.getBranches());
				//rg.setOppsTotal(sub.getOpps());
				System.out.println(sub.getRegName());
			}
		}
		rg.setBrTotal(branchTotal);
		rg.setOppsTotal(oppTotal);
		rg.setSubRegions(finalSubRegs);
		/*rg.setBrTotal(30);
		rg.setOppsTotal(20);*/
		return rg;
	}

	
	public BranchDTO getBranches(Integer regId)
	{
		
		BranchDTO brDTO = new BranchDTO();
		List<Branches> subBranches = createBranches(regId);
		List<Branches> finalBranches = new ArrayList<Branches>();
		
		for(Branches br: subBranches)
		{
			if(br.getRegId() == regId)
			{
				finalBranches.add(br);
				brDTO.setBrTotal(br.getBrOpps());
			}
		}
		
		/*brDTO.setBrTotal(20);
		brDTO.setOppsTotal(50);*/
		brDTO.setSubRegions(finalBranches);
		return brDTO;
	}
	
	public OppsDTO getOpps(Integer brId)
	{
		OppsDTO ops = new OppsDTO();
		List<Opps> subOpps = createOpps(brId);
		List<Opps> finalsubOpps = new ArrayList<Opps>();
		
		for(Opps Opp: subOpps)
		{
			if(Opp.getBrId() == brId)
			{
				finalsubOpps.add(Opp);
			}
		}
		
		/*ops.setBrTotal(60);
		ops.setOppsTotal(70);*/
		ops.setSubRegions(finalsubOpps);
		System.out.println(finalsubOpps.size());
		return ops;
	}
	
	public List<subRegions> createSubRegions(Integer regId)
	{
		List<subRegions> subList = new ArrayList<subRegions>();
		
		
		String selectTableSQL = "SELECT sb_id , sb_name, r_id, forecast_status, (select count(*) FROM sfdc.branch_iec where b_sb_id = sb_id and delete_flg = 'N' ) as branches, "
				+ " (select count(*) FROM sfdc.opportunity_iec where o_br_id in (select br_id from sfdc.branch_iec where b_sb_id = sb_id ) and delete_flg = 'N' ) as opps ,"
				+ "(select sum(facamt) FROM sfdc.opportunity_iec where o_br_id in (select br_id from sfdc.branch_iec where b_sb_id = sb_id ) and delete_flg = 'N'  ) as oppsSum FROM sfdc.subregion_iec where r_id = "+regId;
		
		ResultSet rs = null;
		try {
			
			statement = conection.prepareStatement(selectTableSQL);
			rs= statement.executeQuery();
			
			while (rs.next()) {
				subRegions sub = new subRegions();
				sub.setrId(regId);
				sub.setRegId(rs.getInt(1));
				sub.setRegName(rs.getString(2));
				sub.setRegStatus(rs.getString(4));
				sub.setBranches(Integer.parseInt(rs.getString(5)));
				sub.setOpps(Integer.parseInt(rs.getString(6)));
				sub.setSumfactamt(rs.getString(7));
				sub.setUpdatedBy("SYSTEM");
				sub.setUpdated(new Date().toString());
				//System.out.println("rId = "+regId+ "sub.getRegId() = "+sub.getRegId()+"sub.getRegName() = "+ sub.getRegName() +"sub.getRegSttus() = "+ sub.getRegStatus()+"sub.getbranch() = "+ sub.getBranches());
				
				
				subList.add(sub);
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		System.out.println("subList size = "+subList.size());
		return subList; 
		
		
		
		
		
	}
	public List<Branches> createBranches(Integer regId)
	{
		List<Branches> brList = new ArrayList<Branches>();
				
		
		String selectTableSQL = "SELECT br_id , branch_name, forecast_status, "
				+ " (select count(*) FROM sfdc.opportunity_iec where o_br_id = br_id and delete_flg = 'N' ) as opps,"
				+ " (select sum(facamt) FROM sfdc.opportunity_iec where o_br_id = br_id and delete_flg = 'N' ) as oppsSum,"
				+ " b_sb_id, delete_flg FROM sfdc.branch_iec where delete_flg = 'N' and b_sb_id = "+regId ;
		
		ResultSet rs = null;
		
		try {
			
			statement = conection.prepareStatement(selectTableSQL);
			rs= statement.executeQuery();
			
			while (rs.next()) {
				Branches br = new Branches();
				br.setBrId(rs.getInt(1));
				br.setRegId(regId);
				br.setBrName(rs.getString(2));
				br.setBrFcStatus(rs.getString(3));
				if(rs.getString(4)!=null){
				br.setBrOpps(Integer.parseInt(rs.getString(4)));
				}
				else{
					br.setBrOpps(0);
				}
				if(rs.getString(5)!=null){
				br.setFcAmt(Integer.parseInt(rs.getString(5)));
				}
				else{
					br.setFcAmt(0);
				}
				brList.add(br);
				System.out.println(rs.getString(2)+" "+br.getBrOpps());
				//System.out.println("rId = "+regId+ "sub.getRegId() = "+sub.getRegId()+"sub.getRegName() = "+ sub.getRegName() +"sub.getRegSttus() = "+ sub.getRegStatus()+"sub.getbranch() = "+ sub.getBranches());
				
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return brList; 
		
		
		
		
		
	}
	
	public List<Opps> createOpps(Integer brId)
	{
		List<Opps> brList = new ArrayList<Opps>();
		
		//String selectTableSQL = "SELECT opp_id,opp_name,accnam_iec,estamt_iec,facamt,leadsrp_iec, delete_flg FROM sfdc.opportunity_iec where delete_flg = 'N' and o_br_id = "+brId ;
		//String selectTableSQL = "SELECT opp_id,opp_name,accnam_iec,estamt_iec,facamt,leadsrp_iec, delete_flg FROM sfdc.opportunity_iec where  o_br_id = "+brId ;
		String selectTableSQL = "SELECT opp_id,opp_name,accnam_iec,estamt_iec,facamt,leadsrp_iec, delete_flg,plan_date,marginpct_iec,stagename,opp_number,lob,country FROM sfdc.opportunity_iec where  o_br_id = "+brId ; 
		ResultSet rs = null;
		
		try {
			
			statement = conection.prepareStatement(selectTableSQL);
			rs= statement.executeQuery();
			
			while (rs.next()) {
				Opps opp  = new Opps();
				opp.setBrId(brId);
				opp.setOppsId(rs.getInt(1));
				opp.setOppsName(rs.getString(2));
				opp.setAccName(rs.getString(3));
				opp.setEstAmt(rs.getString(4));
				opp.setLeadRep(rs.getString(6));
				opp.setDeleteFlg(rs.getString(7));
				//opp.setPlanDt(rs.getString(8));
				opp.setPlanDt(rs.getDate(8).toString());
				opp.setMarginPct(rs.getString(9));
				opp.setStage(rs.getString(10));
				opp.setFactoredAmt(rs.getString(5));
				opp.setPlanDate(DateUtility.getDatefromStringMMMDDYYY(rs.getString(8)));
				opp.setOppNumber(rs.getString(11));
				opp.setLob(rs.getString(12));
				opp.setCountry(rs.getString(13));
				//rs.getd
				brList.add(opp);
				System.out.println("DATE From DB "+rs.getString(8));
				System.out.println("DATE setPlanDt "+opp.getPlanDt());
				System.out.println("DATE in Date  "+opp.getPlanDate());
				/*System.out.println(rs.getString(2)+" "+br.getBrOpps());*/
				//System.out.println("rId = "+regId+ "sub.getRegId() = "+sub.getRegId()+"sub.getRegName() = "+ sub.getRegName() +"sub.getRegSttus() = "+ sub.getRegStatus()+"sub.getbranch() = "+ sub.getBranches());
				
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return brList; 
		
		
		
		
		
	}
	
	public void remove(Integer brId)
	{
		
		try {
			/*String updateTableSQL="UPDATE sfdc.branch_iec SET  forecast_status = 'In Progress' WHERE br_id = "+brId;
			statement = conection.prepareStatement(updateTableSQL);
			statement.executeUpdate();
			
			updateTableSQL="UPDATE sfdc.subregion_iec SET  forecast_status = 'In Progress' WHERE sb_id = (select b_sb_id from sfdc.branch_iec where br_id = "+brId+") ";
			statement = conection.prepareStatement(updateTableSQL);
			statement.executeUpdate();
			*/
			String updateTableSQL="update  sfdc.opportunity_iec set delete_flg ='Y' where opp_id ="+brId;
			statement = conection.prepareStatement(updateTableSQL);
			int i=statement.executeUpdate();
			System.out.println(" remove excecuted update SUCCESS executeUpdate() ="+i);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public MonthDto getGraphDataFeb(Integer regId) {
			
		String selectTableSQL = "select sum(estamt_iec) feb_estimated_amt, sum(facamt) feb_factor_amt , count(distinct br_id) feb_branchcnt, count(distinct (opp_id)) feb_oppcnt "
				+ "  from sfdc.vw_opportunity_data  where  r_id ="+regId+" and plan_date <= '10-mar-2017'"; 
		ResultSet rs = null;
		MonthDto feb=new MonthDto();
		try {
			statement = conection.prepareStatement(selectTableSQL);
			rs= statement.executeQuery();
			
			while (rs.next()) {
				
				feb.setMonth("Feb");
				feb.setMonthEst(rs.getInt(1));
				feb.setMonthFact(rs.getInt(2));
				
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return feb;

	}
	public MonthDto getGraphDataMarch(Integer regId) {
		
		String selectTableSQL = "select sum(estamt_iec) mar_estimated_amt, sum(facamt) mar_factor_amt, count(distinct br_id) mar_branchcnt, count(distinct (opp_id)) mar_oppcnt"
				+ "  from sfdc.vw_opportunity_data  where delete_flg ='N' and r_id ="+regId+"and plan_date >= '1-mar-2017'"; 
		ResultSet rs = null;
		MonthDto mar=new MonthDto();
		try {
			statement = conection.prepareStatement(selectTableSQL);
			rs= statement.executeQuery();
			
			while (rs.next()) {
				
				mar.setMonth("March");
				mar.setMonthEst(rs.getInt(1));
				mar.setMonthFact(rs.getInt(2));
				
				}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
			return mar;
		

	}

	
}
