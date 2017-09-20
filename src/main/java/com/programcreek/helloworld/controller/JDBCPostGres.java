package com.programcreek.helloworld.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCPostGres {
	
	public static Connection getConnection()
	{
		Connection connection = null;
		PreparedStatement statement = null;
			
		try {

			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://ec2-174-129-41-23.compute-1.amazonaws.com:5432/dj2l3cgm5kq93?sslmode=require", "fgledaspoyzyjx","zovUMXdthsF7fSISgzZPmtv7Ed");
		} catch (Exception e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			

		}
		return connection;
	} 

	public static void main(String[] args) {
			
		
		Service ser = new Service();
		//ser.getSubRegions(1);
		//ser.getBranches(2);
		ser.getOpps(1);
		
				String selectTableSQL = "SELECT opp_id, \"subRegion_iec\", \"accountName_iec\" FROM sfdc.opportunity_iec where \"subRegion_iec\"='Northern Regionn'";
		//String updateTableSQL="UPDATE sfdc.opportunity_iec SET  \"subRegion_iec\"=?,\"accountName_iec\"=? WHERE opp_id=8";
		//String insertTableSQL="INSERT INTO sfdc.opportunity_iec(opp_id, \"subRegion_iec\", \"accountName_iec\", \"estimatedAmount_iec\", \"marginPct_iec\", opp_number, lob, \"branchCode\", region_iec, \"StageName\", \"estProbability_iec\", \"factoredAmount_iec\", country, \"leadSalesRep_iec\", sub_lob, \"salesManager_iec\", br_id, delete_flg) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		String deleteTableSQL="DELETE FROM sfdc.opportunity_iec WHERE opp_id=13";
		
		//	System.out.println(selectTableSQL);
		try {
			
				 
			Connection connection = getConnection();
			PreparedStatement statement = null;
			
			
			statement = connection.prepareStatement(deleteTableSQL);
			
			
			statement.setInt(1, 13);
			statement.setString(2, "JCI-AECC");
			statement.setString(3, "JCI-AECD");
			statement.setString(4, "JCI-AEC");
			statement.setString(5, "854700.85");
			statement.setString(6, "24");
			statement.setString(7, "OPP-0099623");
			statement.setString(8, "Service");
			statement.setString(9, "CHN - Suzhouu");
			statement.setString(10, "China");
			statement.setString(11, "Target");
			statement.setString(12, "11");
			statement.setString(13, "65554.8");
			statement.setString(14, "China");
			statement.setString(15, "Haining Wang");
			statement.setString(16, "L&M");
			statement.setInt(17, 4);
			statement.setString(18, "");
			
			ResultSet rs = statement.executeQuery();
			statement.executeUpdate();
			while (rs.next()) {

				String nameTxt = rs.getString(1);
				String locationtxt = rs.getString(2);
				String accountName_iec = rs.getString(3);
				System.out.println("nameTxt : " + nameTxt);
				System.out.println("locationtxt : " + locationtxt);
				System.out.println("accountName_iec : " + accountName_iec);

			}
			

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		/*if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}*/

	}

}
