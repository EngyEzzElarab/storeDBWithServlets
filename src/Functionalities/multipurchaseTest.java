//package Functionalities;
//import static org.junit.Assert.assertTrue;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import org.junit.Test;
//import dbConnection.MySQLConn;
//
//public class multipurchaseTest {
//	
//	private static int calculateQuantity(Connection conn, int itemID) throws SQLException
//	{
//		int quantity;
//		
//		String query = "SELECT SUM(Quantity)  FROM ItemStoreRelation GROUP BY ItemID HAVING ItemID="+itemID+";";
//		 Statement st = conn.createStatement();
//		 ResultSet result = st.executeQuery(query);
//		 result.next();
//		 String amount = result.getString(1);
//		quantity = Integer.parseInt(amount);
//		
//		return quantity;
//	}
//
//	
//	private static boolean checkQuantity(int allQuantityOfItem , int  allQuantityAfterPurchase)
//	{
//					if(allQuantityOfItem>8)
//					{
//						if(allQuantityAfterPurchase==allQuantityOfItem-8)
//						{
//							return  true;
//						}
//					}
//					else
//					{
//						if(allQuantityAfterPurchase==0)
//						{
//							return true;
//						}
//					}
//					
//					return false;
//	}
//	
//
//	private int getTransCount(Connection conn) throws SQLException {
//		String query = "SELECT COUNT(*) FROM Transactions;";
//		 Statement st = conn.createStatement();
//		 ResultSet result = st.executeQuery(query);
//		 result.next();
//		 String amount = result.getString(1);
//		return Integer.parseInt(amount);
//		
//	}
//	@Test
//	public void testBatchPurchaseWIthFourCustomersQuantityTwoItemTwo() throws SQLException {
//						boolean firstCondition=false;
//						boolean secondCondition=false;
//						boolean thirdCondition=false;
//						
//						Connection connection = MySQLConn.connectToDB();
//						
//						int allQuantityOfItem = calculateQuantity(connection,2);
//						int numOfTrans = getTransCount(connection);
//						
//						multipurchase.batchPurchase(connection, 4, 2, 2);
//						
//						int allQuantityAfterPurchase = calculateQuantity(connection,2);
//						int numOfTransAfter = getTransCount(connection);
//
//						
//						secondCondition = checkQuantity(allQuantityOfItem, allQuantityAfterPurchase);
//						
//						int customers = multipurchase.getNumberOfCustomers(connection);
//						if(customers>=4)
//						{
//							firstCondition = true;
//						}
//						System.out.println();
//						if(numOfTransAfter-numOfTrans==4)
//						{
//							thirdCondition = true;
//						}
//						
//						
//						assertTrue(firstCondition&&secondCondition&&thirdCondition);
//						
//						
//	}
//
//	
//	@Test
//	public void testBatchPurchaseWIthHundredCustomersQuantityFiveItemOne() throws SQLException {
//						boolean firstCondition=false;
//						boolean secondCondition=false;
//						boolean thirdCondition=false;
//						
//						Connection connection = MySQLConn.connectToDB();
//						
//						int allQuantityOfItem = calculateQuantity(connection,1);
//						int numOfTrans = getTransCount(connection);
//						
//						multipurchase.batchPurchase(connection, 100, 1, 5);
//						
//						int allQuantityAfterPurchase = calculateQuantity(connection,1);
//						int numOfTransAfter = getTransCount(connection);
//
//						
//						secondCondition = checkQuantity(allQuantityOfItem, allQuantityAfterPurchase);
//						
//						int customers = multipurchase.getNumberOfCustomers(connection);
//						if(customers>=100)
//						{
//							firstCondition = true;
//						}
//						System.out.println();
//						if(numOfTransAfter-numOfTrans==100)
//						{
//							thirdCondition = true;
//						}
//						//System.out.println(firstCondition+" "+secondCondition+" "+thirdCondition);
//						
//						assertTrue(firstCondition&&secondCondition&&thirdCondition);
//						
//						
//	}
//
//	
//	@Test
//	public void testBatchPurchaseWIthOneCustomerQuantityTwoItemThree() throws SQLException {
//						boolean firstCondition=false;
//						boolean secondCondition=false;
//						boolean thirdCondition=false;
//						
//						Connection connection = MySQLConn.connectToDB();
//						
//						int allQuantityOfItem = calculateQuantity(connection,3);
//						int numOfTrans = getTransCount(connection);
//						
//						multipurchase.batchPurchase(connection, 1, 3, 2);
//						
//						int allQuantityAfterPurchase = calculateQuantity(connection,3);
//						int numOfTransAfter = getTransCount(connection);
//
//						
//						secondCondition = checkQuantity(allQuantityOfItem, allQuantityAfterPurchase);
//						
//						int customers = multipurchase.getNumberOfCustomers(connection);
//						if(customers>=1)
//						{
//							firstCondition = true;
//						}
//						System.out.println();
//						if(numOfTransAfter-numOfTrans==1)
//						{
//							thirdCondition = true;
//						}
//						
//						
//						assertTrue(firstCondition&&secondCondition&&thirdCondition);
//						
//						
//	}
//
//
//
//}
