package Functionalities;

import java.sql.*;
import java.util.concurrent.CountDownLatch;

public class purchase extends Thread {
	public Connection connection;
	public int custID;
	public int itemID;
	public int amount;
	public CountDownLatch latch;

	public purchase(Connection connection, int custID, int itemID, int amount, CountDownLatch latch) {
		this.connection = connection;
		this.custID = custID;
		this.itemID = itemID;
		this.amount = amount;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Statement st = connection.createStatement();
			ResultSet result = st.executeQuery("SELECT * FROM ItemStoreRelation WHERE itemID=" + itemID + ";");
			Statement st2 = connection.createStatement();
			ResultSet res = st2
					.executeQuery("SELECT SUM(Quantity) FROM  ItemStoreRelation  WHERE ItemID=" + itemID + ";");
			res.next();
			String foundType = res.getString(1);
			int total = Integer.parseInt(foundType);
			int remainingAmount = amount;
			if (total < amount)
				System.out.println("Unfortunately,The amount you need is not available now in all stores");
			else {
				while (result.next() && remainingAmount > 0) {

					int quantityAtStore = Integer.parseInt(result.getString(4));
					int storeID = Integer.parseInt(result.getString(3));
					int transAmount = 0;

					if (quantityAtStore >= remainingAmount) {
						transAmount = remainingAmount;
						String query2 = "UPDATE ItemStoreRelation SET quantity = quantity - ?  where ItemID=? AND StoreID=?;";
						PreparedStatement preparedStatement = connection.prepareStatement(query2);
						preparedStatement.setInt(1, remainingAmount);
						preparedStatement.setInt(2, itemID);
						preparedStatement.setInt(3, storeID);
						preparedStatement.executeUpdate();
						query2 = "INSERT INTO Transactions(cID,ItemID,StoreID,Amount) VALUES (?,?,?,?);";
						preparedStatement = connection.prepareStatement(query2);
						preparedStatement.setInt(1, custID);
						preparedStatement.setInt(2, itemID);
						preparedStatement.setInt(3, storeID);
						preparedStatement.setInt(4, transAmount);
						preparedStatement.executeUpdate();
						break;
					} else {
						transAmount = quantityAtStore;
						String query3 = "UPDATE ItemStoreRelation SET quantity = 0  where ItemID=? AND StoreID=?;";
						PreparedStatement preparedStatement = connection.prepareStatement(query3);
						preparedStatement.setInt(1, itemID);
						preparedStatement.setInt(2, storeID);
						preparedStatement.executeUpdate();
						remainingAmount = remainingAmount - quantityAtStore;
					}
					String query2 = "INSERT INTO Transactions(cID,ItemID,StoreID,Amount,transTime,transDate) VALUES (?,?,?,?);";
					PreparedStatement preparedStatement = connection.prepareStatement(query2);
					preparedStatement.setInt(1, custID);
					preparedStatement.setInt(2, itemID);
					preparedStatement.setInt(3, storeID);
					preparedStatement.setInt(4, transAmount);
				}
			}
			System.out.println("DONEEE");
			latch.countDown();
		} catch (SQLException e) {
			System.out.println("An exception has occurred");
		}
	}

	// The following method is used without multithreading
	public static void makePurchase(Connection connection, int custID, int itemID, int amount) throws SQLException {
		Statement st = connection.createStatement();
		ResultSet result = st.executeQuery("SELECT * FROM ItemStoreRelation WHERE itemID=" + itemID + ";");

		Statement st2 = connection.createStatement();
		ResultSet res = st2.executeQuery("SELECT SUM(Quantity) FROM  ItemStoreRelation  WHERE ItemID=" + itemID + ";");
		res.next();
		String foundType = res.getString(1);
		int total = Integer.parseInt(foundType);
		int remainingAmount = amount;
		if (total < amount)
			System.out.println("Unfortunately,The amount you need is not available now in all stores");
		else {
			while (result.next() && remainingAmount > 0) {

				int quantityAtStore = Integer.parseInt(result.getString(4));
				int storeID = Integer.parseInt(result.getString(3));
				int transAmount = 0;

				if (quantityAtStore >= remainingAmount) {
					transAmount = remainingAmount;
					String query2 = "UPDATE ItemStoreRelation SET quantity = ? - ?  where ItemID=? AND StoreID=?;";
					PreparedStatement preparedStatement = connection.prepareStatement(query2);
					preparedStatement.setInt(1, quantityAtStore);
					preparedStatement.setInt(2, remainingAmount);
					preparedStatement.setInt(3, itemID);
					preparedStatement.setInt(4, storeID);
					preparedStatement.executeUpdate();
					query2 = "INSERT INTO Transactions(cID,ItemID,StoreID,Amount) VALUES (?,?,?,?);";
					preparedStatement = connection.prepareStatement(query2);
					preparedStatement.setInt(1, custID);
					preparedStatement.setInt(2, itemID);
					preparedStatement.setInt(3, storeID);
					preparedStatement.setInt(4, transAmount);
					preparedStatement.executeUpdate();
					break;
				}

				else {
					transAmount = quantityAtStore;
					String query3 = "UPDATE ItemStoreRelation SET quantity = ?  where ItemID=? AND StoreID=?;";
					PreparedStatement preparedStatement = connection.prepareStatement(query3);
					preparedStatement.setInt(1, 0);
					preparedStatement.setInt(2, itemID);
					preparedStatement.setInt(3, storeID);
					preparedStatement.executeUpdate();
					remainingAmount = remainingAmount - quantityAtStore;
				}

				String query2 = "INSERT INTO Transactions(cID,ItemID,StoreID,Amount,transTime,transDate) VALUES (?,?,?,?);";
				PreparedStatement preparedStatement = connection.prepareStatement(query2);
				preparedStatement.setInt(1, custID);
				preparedStatement.setInt(2, itemID);
				preparedStatement.setInt(3, storeID);
				preparedStatement.setInt(4, transAmount);
			}
		}
		System.out.println("DONEEE");
	}

}
