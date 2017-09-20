package com.java.main.utility;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class GETJDBCPostGres {
	
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
		/*
			private static final String DB_DRIVER = "org.postgresql.Driver";
			private static final String DB_CONNECTION = "jdbc:postgresql://ec2-34-230-241-132.compute-1.amazonaws.com:5432/d99qbjplusk6ss?sslmode=require";
			private static final String DB_USER = "uc3d7fkbkcr02m";
			private static final String DB_PASSWORD = "pff09ba2dbb843179e0ea3cb7030e244c5353e1776bb602cc9646060db56a16a9";*/

		
		
		//DB PROD NEW END

		
		
		
		// DB Prod Start
	/*	private static final String DB_DRIVER = "org.postgresql.Driver";
		private static final String DB_CONNECTION = "jdbc:postgresql://ec2-34-204-159-194.compute-1.amazonaws.com:5432/d99qbjplusk6ss?sslmode=require";
		private static final String DB_USER = "u2k71berfe7uj5";
		private static final String DB_PASSWORD = "p763b55efc9de27c21ffd390e4e4202601df3f1f31fc8907d07857051d4a3060c";

*/		
	//DB Prod END 
	public static Connection getConnection()
	{
		Connection connection = null;
		PreparedStatement statement = null;
			
		try {

			//Class.forName("org.postgresql.Driver");
			Class.forName(DB_DRIVER);
			//connection = DriverManager.getConnection("jdbc:postgresql://ec2-54-163-236-33.compute-1.amazonaws.com:5432/d16g9mvi06kecr?sslmode=require", "kwxishotevzmfy","1d11b86ebd719ce4887dbcb2848f8e5276cde7b26075c855dfff2c28fc2d37d3");
			connection = DriverManager.getConnection(
                    DB_CONNECTION, DB_USER,DB_PASSWORD);
	System.out.println("Connection Testing SUCCESS");
		} catch (Exception e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			

		}
		return connection;
	} 

	public static void main(String[] args) {
			
		
		
	}

}
