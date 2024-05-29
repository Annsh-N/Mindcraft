package shopping.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static Connection cn;
	
	public static Connection openConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/ShoppingDB";
		
		cn = DriverManager.getConnection(url, "root", "rootroot");
		
		return cn;
	}
	
	public static void closeConnection() throws SQLException {
		if (cn != null) {
			cn.close();
		}
	}
}
