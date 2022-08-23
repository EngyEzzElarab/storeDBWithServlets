package Functionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class itemInsertion {

	public static void insertNewItem(Connection connection, int id, String Author, String Manufacturer, Double Width,
			Double Height, int KItId) throws SQLException {
		String query = "INSERT INTO Item VALUES( ?, ?, ?, ?, ?, ?)";

		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, id);
		preparedStatement.setString(2, Author);
		preparedStatement.setString(3, Manufacturer);
		preparedStatement.setDouble(4, Width);
		preparedStatement.setDouble(5, Height);
		preparedStatement.setInt(6, KItId);

		preparedStatement.executeUpdate();
		System.out.println("The item has been inserted successfully");

	}

}
