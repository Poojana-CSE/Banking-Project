package utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/bankingapplication"; //connection url
	private static final String userName = "root";
	private static final String password = "PoojanaSeceCse@27";
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,userName,password);
	}
	
	public static void closeConnection(Connection con) {
		if(con!=null) {
			try {
				con.close();
			} catch(SQLException e) {
				System.out.println("Error Closing Connection " + e.getMessage());
//				e.printStackTrace();
			}
		}
	}
	
	
	
}
