package models;

import java.sql.*;
import java.util.ArrayList;

/** 
 * @author Kasim Hussain - 15078165
 */

public class VehicleDAO {		

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

	//Select * From Vehicles
	public ArrayList <Vehicle> getAllVehicles() {	

		ArrayList<Vehicle> vehicleArray = new ArrayList<Vehicle>();
		try {
			connection = getDBConnection();
			preparedstatement = connection.prepareStatement("SELECT * FROM vehicles");			
			//prepared statement
			rs = preparedstatement.executeQuery();
			if (rs != null)
			{
				while (rs.next())
				{
					Vehicle v = new Vehicle();	
					//setting the the value of v with getters 
					try {
						v.setVehicle_id(rs.getInt("vehicle_id"));
						v.setMake(rs.getString("make"));
						v.setModel(rs.getString("model"));
						v.setYear(rs.getInt("year"));
						v.setPrice(rs.getInt("price"));
						v.setLicense_number(rs.getString("license_number"));
						v.setColour(rs.getString("colour"));
						v.setNumber_doors(rs.getInt("number_doors"));
						v.setTransmission(rs.getString("transmission"));
						v.setMileage(rs.getInt("mileage"));
						v.setFuel_type(rs.getString("fuel_type"));
						v.setEngine_size(rs.getInt("engine_size"));
						v.setBody_style(rs.getString("body_style"));
						v.setCondition(rs.getString("condition"));
						v.setNotes(rs.getString("notes"));
						v.setSold(rs.getString("sold"));
					}
					catch(SQLException s) {
						s.printStackTrace(); }
					vehicleArray.add(v);
				}
				rs.close();
			}}
		catch (SQLException s) {
		}
		closeConnection();		
		return vehicleArray;
	}
	//get specific vehicle 
	public Vehicle getVehicle(int Vehicle) {

		String query = ("SELECT * FROM vehicles WHERE vehicle_id = " + "?");	
		try {				
			connection = getDBConnection();				
			//prepared statement
			preparedstatement = connection.prepareStatement(query);				
			preparedstatement.setInt(1, Vehicle);
			rs = preparedstatement.executeQuery();
			Vehicle v = new Vehicle();
			//setting the the value of v with getters 
			while (rs.next())
			{	
				v.setVehicle_id(rs.getInt("vehicle_id"));
				v.setMake(rs.getString("make"));
				v.setModel(rs.getString("model"));
				v.setYear(rs.getInt("year"));
				v.setPrice(rs.getInt("price"));
				v.setLicense_number(rs.getString("license_number"));
				v.setColour(rs.getString("colour"));
				v.setNumber_doors(rs.getInt("number_doors"));
				v.setTransmission(rs.getString("transmission"));
				v.setMileage(rs.getInt("mileage"));
				v.setFuel_type(rs.getString("fuel_type"));
				v.setEngine_size(rs.getInt("engine_size"));
				v.setBody_style(rs.getString("body_style"));
				v.setCondition(rs.getString("condition"));
				v.setNotes(rs.getString("notes"));
				v.setSold(rs.getString("sold"));
				closeConnection();		
				return v;
			}						
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 		
		//close connection when successful
		return null;
	}
	//DONE	
	public Boolean deleteVehicle(int Vehicle) {

		//set a boolean to false, change it upon successful execution of the task
		boolean b = false;

		/**
		 * SQL query that uses prepared statements (to prevent SQL injection attacks) when manipulating data or talking to the database
		 */

		String query = ("DELETE FROM vehicles WHERE vehicle_id = " + "(?)");		
		try {				
			connection = getDBConnection();				
			//prepared statement
			preparedstatement = connection.prepareStatement(query);		
			preparedstatement.setInt(1, Vehicle);
			preparedstatement.executeUpdate();
			closeConnection();
			b = true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		//check if b is true or false, if false an error occurred 
		return b;
	}
	//DONE	
	public Boolean insertVehicle(Vehicle v) throws SQLException {	

		//set a boolean to false, change it upon successful execution of the task
		boolean b = false; 

		/**
		 * SQL query that uses prepared statements (to prevent SQL injection attacks) when manipulating data or talking to the database
		 */

		String query = "Insert INTO vehicles (vehicle_id,make,model,year,price,license_number,colour,"
				+ "number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes,sold)" 
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";	
		try {
			connection = getDBConnection();			
			//prepared statements setting the the value of v with getters 
			preparedstatement = connection.prepareStatement(query);		
			preparedstatement.setInt(1, v.getVehicle_id());
			preparedstatement.setString(2, v.getMake());
			preparedstatement.setString(3, v.getModel());
			preparedstatement.setInt(4, v.getYear());
			preparedstatement.setInt(5, v.getPrice());
			preparedstatement.setString(6, v.getLicense_number());
			preparedstatement.setString(7, v.getColour());
			preparedstatement.setInt(8, v.getNumber_doors());
			preparedstatement.setString(9, v.getTransmission());
			preparedstatement.setInt(10, v.getMileage());
			preparedstatement.setString(11, v.getFuel_type());;
			preparedstatement.setInt(12, v.getEngine_size());			
			preparedstatement.setString(13,v.getBody_style());
			preparedstatement.setString(14, v.getCondition());
			preparedstatement.setString(15, v.getNotes());
			preparedstatement.setString(16, v.getSold());
			preparedstatement.executeUpdate();			
			closeConnection();	
			b = true;
		}
		catch (SQLException s) {
			throw new SQLException("Vehicle Not Added");
		}		
		//check if b is true or false, if false an error occurred 
		return b;
	}

	public Boolean updateVehicle(Vehicle v, int veh_id) throws SQLException {		

		//set a boolean to false, change it upon successful execution of the task
		boolean b = false;			

		/**
		 * SQL query that uses prepared statements (to prevent SQL injection attacks) when manipulating data or talking to the database
		 */

		String query = "update vehicles set vehicle_id = ? , make = ? , model = ? , year = ? , price = ? , license_number = ? , colour = ? , number_doors = ? ,"
				+ "transmission = ? , mileage = ? , fuel_type = ?, engine_size = ? , body_style = ? , condition = ? , notes = ? , sold = ? where vehicle_id = ?"; 

		try {
			connection = getDBConnection();				
			preparedstatement = connection.prepareStatement(query);	
			preparedstatement.setInt(1, v.getVehicle_id());
			preparedstatement.setString(2, v.getMake());
			preparedstatement.setString(3, v.getModel());
			preparedstatement.setInt(4, v.getYear());
			preparedstatement.setInt(5, v.getPrice());
			preparedstatement.setString(6, v.getLicense_number());
			preparedstatement.setString(7, v.getColour());
			preparedstatement.setInt(8, v.getNumber_doors());
			preparedstatement.setString(9, v.getTransmission());
			preparedstatement.setInt(10, v.getMileage());
			preparedstatement.setString(11, v.getFuel_type());;
			preparedstatement.setInt(12, v.getEngine_size());			
			preparedstatement.setString(13,v.getBody_style());
			preparedstatement.setString(14, v.getCondition());
			preparedstatement.setString(15, v.getNotes());
			preparedstatement.setString(16, v.getSold());
			preparedstatement.setInt(17, veh_id);
			preparedstatement.executeUpdate();			
			closeConnection();	
			b = true;
		}
		catch (SQLException s) {
			throw new SQLException("Vehicle Not Updated");
		}		
		//check if b is true or false, if false an error occurred 
		return b;
	}
}