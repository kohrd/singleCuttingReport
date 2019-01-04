package pl.singleCutting.report;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtilities {

	
	
	public static void dbConnection(String dbPath) {
		
		Connection con = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			System.out.println("polaczono do bazy danych!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
}