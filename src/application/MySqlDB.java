package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlDB{
	protected Connection connection;
	public MySqlDB() {
		 try {
			String url      ="jdbc:mysql://localhost:3306/oversea";   /* databaseName*/
	        String username = "root";                              /* username */
	        String password = "12345678";                     /* pass */ 
			Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection(url,username,password);   
			 
//			 String url      ="jdbc:mysql://localhost:3306/site";   /* databaseName*/
//		        String username = "root";                              /* username */
//		        String password = "12345678";                     /* pass */ 
//				Class.forName("com.mysql.jdbc.Driver");
//			    connection = DriverManager.getConnection(url,username,password);   

		} catch (ClassNotFoundException | SQLException ex) {

			System.out.println(ex);
		}
	}
	
	 public void closeConnection() {
	    	try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} 
	    }

}
