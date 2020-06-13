package models;
import java.sql.*;
import java.util.ArrayList;

/** 
 * @author Kasim Hussain - 15078165
 */

public class UserDAO {		
	
	/**
	 * Global variables 
	 */
	
	Connection connection = null;
	PreparedStatement preparedstatement = null;
	ResultSet rs = null;

	//close connection
	public void closeConnection() {	
		try {
			if(preparedstatement!=null) {
				preparedstatement.close();
			}
			if(connection!=null) {
				connection.close();
			}
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//Setup Database Connection
	private static Connection getDBConnection() {
		Connection connection = null;
		try {		
			//Driver name 
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			//Database name and path
			String dbURL = "jdbc:sqlite:vehicles.sqlite";
			connection = DriverManager.getConnection(dbURL);
			return connection;				

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return connection;
	}
	
	//get specific user 
	public User getUser(String username) {
		
		/**
		 * SQL query that uses prepared statements (to prevent SQL injection attacks) when manipulating data or talking to the database
		 */
		
		String query = ("SELECT password FROM users WHERE username = ? ");	
		try {				
			connection = getDBConnection();				
			//prepared statement setting values with setters
			preparedstatement = connection.prepareStatement(query);				
			preparedstatement.setString(1, username);
			rs = preparedstatement.executeQuery();
			User v = new User();
			while (rs.next())
			{	
				v.setPassword(rs.getString("password"));
				return v;
			}						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 		
		//close connection when successful
		closeConnection();		
		return null;
	}

	public Boolean updateUser(User v, String user) throws SQLException {		
		
		//set a boolean to false, change it upon successful execution of the task		
		boolean b = false;		
		
		/**
		 * SQL query that uses prepared statements (to prevent SQL injection attacks) when manipulating data or talking to the database
		 */
		
		String query = "update users set password = ? where username = ?"; 
					 
		try {
			connection = getDBConnection();	
			//prepared statement setting values with setters
			preparedstatement = connection.prepareStatement(query);	
			preparedstatement.setString(1, v.getPassword());
			preparedstatement.setString(2, user);
			preparedstatement.executeUpdate();	
			b = true;
		}
		catch (SQLException s) {
			throw new SQLException("User Password Not Updated");
		}
		//check if b is true or false, if false an error occurred 
		return b;
	}
}
