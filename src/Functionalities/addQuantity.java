package Functionalities;
import java.sql.*;

public class addQuantity {
				static int id=3;
	
				public static void addQuantityToStore(Connection connection, int itemID , int storeID , int quantity ) throws SQLException
				{
								//check if exisits first
								Statement st = connection.createStatement(); 
								String s = "SELECT * FROM ItemStoreRelation WHERE ItemID="+itemID+" AND StoreID="+storeID+";";
								System.out.println(s);
								ResultSet result = st.executeQuery(s);
								System.out.println("GOING TO IF");
								if(result.next())
								{
					
											System.out.println("TRUE");
											String query = "UPDATE ItemStoreRelation SET quantity = quantity + ?  where ItemID=? AND StoreID=?;";
											PreparedStatement preparedStatement = connection.prepareStatement(query);
										    preparedStatement.setInt(1, quantity);
										    preparedStatement.setInt(2, itemID);
										    preparedStatement.setInt(3, storeID);
										    preparedStatement.executeUpdate(); 
											System.out.println("The quantity has been updated successfully");
											
												
								}
								else
								{
										System.out.println("FALSE");
										String query = "INSERT INTO ItemStoreRelation(ItemID,StoreID,Quantity) VALUES( ?, ?, ?)";
									    PreparedStatement preparedStatement = connection.prepareStatement(query);
//									    preparedStatement.setInt(1, id);
									    preparedStatement.setInt(1, itemID);
									    preparedStatement.setInt(2, storeID);
									    preparedStatement.setInt(3, quantity);
									    System.out.println(preparedStatement);
									    preparedStatement.executeUpdate(); 
										System.out.println("The quantity has been added successfully");
										id++;
								}
			
			
				}
}
