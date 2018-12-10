package doctordatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class actions {

	private static Connection connect() {
		Connection conn = null;
		
		try {
			String url = "H:\\git\\DoctorDatabase\\DoctorDatabase";
			conn = DriverManager.getConnection(url);
			System.out.println("Connection to SQLite has been established.");
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	
	
	
}
