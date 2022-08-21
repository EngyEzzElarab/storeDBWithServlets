package Functionalities;
import java.sql.*;
import java.util.Date;
import java.util.*;
public class DailyReports {
	
	
						public static void outputReports(Connection conn,String date) throws SQLException
						{
									//  SELECT * FROM Transactions WHERE transDateTime  LIKE '?%'
							 String query = "SELECT * FROM Transactions WHERE transDateTime  LIKE '"+date+"%';";
							 //System.out.println(query);
							 Statement st = conn.createStatement();
							 ResultSet result = st.executeQuery(query);
							 
							//headers
								ResultSetMetaData rsMetaData = result.getMetaData();
							      int count = rsMetaData.getColumnCount();
							      for(int i = 2; i<count; i++) {
							         System.out.print(rsMetaData.getColumnName(i)+" ");
							      }
							      System.out.print("Time ");
							      System.out.println();
							      
							      
							 String cID;
							 String ItemID;
							 String StoreID;
							 String Amount;
							 String time;
							 while(result.next())
							 {
								cID = result.getString(2);
								ItemID = result.getString(3);
								StoreID = result.getString(4);
								Amount = result.getString(5);
								time = result.getString(6);
								System.out.println( cID + "          "+ItemID+"            "+StoreID+"           "+Amount+"      "+time.split(" ")[1]);
							 }
							 
						}
						
			
}
