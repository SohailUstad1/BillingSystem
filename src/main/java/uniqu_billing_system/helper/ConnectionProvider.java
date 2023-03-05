package uniqu_billing_system.helper;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
	private Connection con;
	private String username="root";
	private String password="0000";
	private String url="jdbc:mysql://localhost:3306/umsbill";
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
}
