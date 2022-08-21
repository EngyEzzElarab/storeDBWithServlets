package dbConnection;

import java.sql.*;
import java.sql.DriverManager;

//import com.mysql.jdbc.Driver;

import Functionalities.addQuantity;
import Functionalities.itemInsertion;
public class MySQLConn {
	
	
				public static Connection connectToDB() throws ClassNotFoundException {
					Class.forName("com.mysql.cj.jdbc.Driver"); 
					// load and register JDBC driver for MySQL
					System.out.println("Entered connectToDB");
					Connection connection = null;
							try 
							{
								System.out.println("aluuuu");
								//Driver driver = new Driver();
								//com.mysql.jdbc.Driver d = new com.mysql.jdbc.Driver();
								//Class.forName("com.mysql.cj.jdbc.Driver"); 
								System.out.println("after class for name");
								 connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/STORE", "root", "ya33");
								System.out.println("Connected With the database successfully");
							

							}
							catch(SQLException e)
							{
								System.out.println("Error while connecting to the database");
								System.out.println(e.toString());
							}
							return connection;
				}

// 	public static void main(String[] args) throws ClassNotFoundException {
// 					connectToDB();
// 	}
 	}
//			try 
//			{
//				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/STORE", "root", "ya33");
//				
//				System.out.println("Connected With the database successfully");
////				Statement st = connection.createStatement();
//				//itemInsertion.insertNewItem(connection,11,"bataa","hbbty",2.0,3.0,1);
//				addQuantity.addQuantityToStore(connection, 1, 2, 9);
//				//st.close();      
//				connection.close();
//
////				ResultSet result = st.executeQuery("SELECT * FROM Item;");
////				String id = "";
////				String name = "";
////				String location = "";
////				ResultSetMetaData rsMetaData = result.getMetaData();
////
////			      int count = rsMetaData.getColumnCount();
////			      for(int i = 1; i<=count; i++) {
////			         System.out.print(rsMetaData.getColumnName(i)+" ");
////			      }
////			      System.out.println();
////				while(result.next())
////				{
////					id = result.getString(1);
////					name = result.getString(2);
////					location = result.getString(3);
////					
////					 System.out.println( id + "   "+name+"   "+location);
////				}
////				result.close();                       
//			}
//			catch(SQLException e)
//			{
//				System.out.println("Error while connecting to the database");
//			}
//			
//		}
//}
