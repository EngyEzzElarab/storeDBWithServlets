package Functionalities;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class bestSellingItem {

	public static void getMostPurchased(Connection conn) throws SQLException {
		String query = "SELECT ItemID,SUM(Amount) as sum FROM Transactions GROUP BY ItemID ORDER BY sum DESC;";
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery(query);
		result.next();
		String itemID = result.getString(1);
		String totalAmount = result.getString(2);
		System.out.println("The most purchased item is " + itemID + " with quantity of " + totalAmount);
	}
}
