package dbConnection;

import java.sql.*;
import java.sql.DriverManager;

//import com.mysql.jdbc.Driver;

import Functionalities.addQuantity;
import Functionalities.itemInsertion;

public class MySQLConn {

	public static Connection connectToDB() throws ClassNotFoundException {
		// load and register JDBC driver for MySQL
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/STORE", "root", "ya33");
			System.out.println("Connected With the database successfully");

		} catch (SQLException e) {
			System.out.println("Error while connecting to the database");
			System.out.println(e.toString());
		}
		return connection;
	}
}
