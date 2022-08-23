package Functionalities;

import java.sql.*;
import java.util.Date;
import java.util.*;

public class DailyReports {

	public static void outputReports(Connection conn, String date) throws SQLException {
		String query = "SELECT * FROM Transactions WHERE transDateTime  LIKE '" + date + "%';";
		Statement st = conn.createStatement();
		ResultSet result = st.executeQuery(query);
		// Table Headers
		ResultSetMetaData rsMetaData = result.getMetaData();
		int count = rsMetaData.getColumnCount();
		for (int i = 2; i < count; i++) {
			System.out.print(rsMetaData.getColumnName(i) + " ");
		}
		System.out.print("Time ");
		System.out.println();

		String cID;
		String ItemID;
		String StoreID;
		String Amount;
		String time;
		// printing the rows
		while (result.next()) {
			cID = result.getString(2);
			ItemID = result.getString(3);
			StoreID = result.getString(4);
			Amount = result.getString(5);
			time = result.getString(6);
			System.out.println(cID + "          " + ItemID + "            " + StoreID + "           " + Amount
					+ "      " + time.split(" ")[1]);
		}

	}

}
