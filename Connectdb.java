package starttojava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;	

public class Connectdb {

	public static Connection initializeDatabase() 
	
		throws SQLException, ClassNotFoundException
		{
			String dbURL = "jdbc:mysql://localhost:3306/";
			String dbName = "starttojava";
			String dbUsername = "root";
		    String dbPassword = "root";

		    Connection con = DriverManager.getConnection(dbURL + dbName,dbUsername, dbPassword);
			return con;
		
		}
}

