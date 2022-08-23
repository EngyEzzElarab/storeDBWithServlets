package Functionalities;

import java.sql.*;
import java.util.*;

public class emptyStock {

	public static void displayZeroQuantity(Connection connection) throws SQLException {
		String query = "SELECT * FROM ItemStoreRelation WHERE Quantity=0;";
		System.out.println("Displaying items with zero quantity");
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery(query);
		// headers
		ResultSetMetaData rsMetaData = result.getMetaData();
		int count = rsMetaData.getColumnCount();
		for (int i = 1; i <= count; i++) {
			System.out.print(rsMetaData.getColumnName(i) + " ");
		}
		System.out.println();
		String id = "";
		String ItemID = "";
		String StoreID = "";
		String Quantity = "";
		while (result.next()) {
			id = result.getString(1);
			ItemID = result.getString(2);
			StoreID = result.getString(3);
			Quantity = result.getString(4);

			System.out.println(id + "           " + ItemID + "            " + StoreID + "           " + Quantity);
		}
	}
}
