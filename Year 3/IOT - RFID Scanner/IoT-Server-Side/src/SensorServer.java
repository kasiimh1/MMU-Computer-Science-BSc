import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.*;
import java.sql.*;

@WebServlet("/SensorServer")
public class SensorServer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Gson gson = new Gson();
	Connection connection = null;
	PreparedStatement prepared = null;
	ResultSet rs = null;
	SensorData sens = new SensorData(0, null, null, null, null);
	
	public void init(ServletConfig config) throws ServletException {
		// init method is run once at the start of the servlet loading
		super.init(config);
		System.out.println("Sensor to DB server is up and running\n");
		System.out.println("Server Running at http://localhost:8080/IoT-Server-Side/SensorServer");
	} // init()

	@SuppressWarnings("deprecation")
	private void getConnection() {
		// This will load the driver and establish a connection
		String user = "hussaink";
		String password = "Rimsdarl3";

		// Note none default port used, 6306 not 3306
		String url = "jdbc:mysql://mudfoot.doc.stu.mmu.ac.uk:6306/" + user;

		// Load the database driver
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}
		// get a connection with the user/pass
		try {
			connection = DriverManager.getConnection(url, user, password);
			// System.out.println("DEBUG: Connection to database successful.");			
		} catch (SQLException se) {
			System.out.println(se);
			System.out.println("\nDid you alter the lines to set user/password in the sensor server code?");
		}
	}

	private void closeConnection() {
		// get a connection with the user/pass
		try {
			connection.close();
		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void destroy() {
		try { // conn.close(); // should have no need to close connection
				// add anything extra to do when servlet closes
		} catch (Exception e) {
			System.out.println(e);
		}
	} // destroy()

	public SensorServer() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setStatus(HttpServletResponse.SC_OK);
		// Declare a SensorData object to hold the incoming data
		SensorData oneSensor = new SensorData(0, "blank", "blank", "blank","blank");
		// Extract the parameter data holding the sensordata
		String sensorJsonString = request.getParameter("sensordata");
		// Problem if sensordata parameter not sent, or is invalid json
		if (sensorJsonString != null) {
			// Convert the json string to an object of type SensorData
			oneSensor = gson.fromJson(sensorJsonString, SensorData.class);
			/* prints out correct resps locally */
			try {
				checkIfTagIsValid(oneSensor);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// Update sensor values and send back response
			PrintWriter out = response.getWriter();
			
			String allSensorsJson;
			allSensorsJson = gson.toJson(sens);
			System.out.println("AUTH DATA, sensordata= " + allSensorsJson);								
			out.print(allSensorsJson);
			out.close();
		
		} // end if getdata is null
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Post is same as Get, so pass on parameters and do same
		doGet(request, response);
	}

	public boolean updateSensorTable(SensorData oneSensor) throws SQLException {
		boolean b = false;
		String updateSQL = "insert into sensorAttempts(readerserial, tagvalue, valid, timeinserted) values (?,?,?, NOW())";
		try {
			getConnection();
			prepared = connection.prepareStatement(updateSQL);
			prepared.setInt(1, oneSensor.getReaderserial());
			prepared.setString(2, oneSensor.getTagvalue());
			prepared.setString(3, oneSensor.getValid());
			System.out.println("[DEBUG][STATEMENT] " + prepared);
			System.out.println("[DEBUG][QUERY] " + updateSQL);			
			prepared.executeUpdate();
			closeConnection();
			b = true;
			if (b)
				System.out.println("[SUCCESS] SensorTable Updated");
		} catch (SQLException s) {
			throw new SQLException("[ERROR] SensorTable Not Updated");
		}
		// check if b is true or false, if false an error occurred
		return b;
	}

	private String checkIfTagIsValid(SensorData oneSensor) throws SQLException {
		String selectSQL = "select * from sensorDoorLookup inner join sensorValidTags on sensorValidTags.roomid = sensorDoorLookup.roomid WHERE (tagvalue= ? AND readervalue = ?)";
//		String selectSQL = "select * from sensorValidTags where tagvalue = ? and readervalue = ? order by roomid asc";
		ResultSet rs;
		//checkValidTag validCheck = new checkValidTag(String.valueOf(oneSensor.getReaderserial()), oneSensor.getTagvalue(), oneSensor.getRoomid()); // fill in statement
		SensorData sensorData = new SensorData(oneSensor.getReaderserial(), oneSensor.getTagvalue(), "false", oneSensor.getRoomid(), "unknown");
		
		try {
			// create a result set of selected values
			getConnection();
			prepared = connection.prepareStatement(selectSQL);
			prepared.setString(1, sensorData.getTagvalue());
			prepared.setInt(2,  sensorData.getReaderserial());
			//prepared.setString(3, sensorData.getMotorid());
			rs = prepared.executeQuery();			            
			// iterate over the result set
			while (rs.next()) {
				// Declare a SensorData object to hold individual values,
				// initialise to defaults
											 
				System.out.println("Object contains.. " + sensorData);				
				sensorData.setTagvalue(rs.getString("tagvalue"));
				sensorData.setRoomid(rs.getString("roomid"));	
				sensorData.setReaderserial(rs.getInt("readervalue"));
				sensorData.setMotorid(rs.getString("motorid"));				

				if(sensorData.getRoomid() != null && sensorData.getTagvalue() != null && sensorData.getMotorid() != null)										
					sensorData.setValid("true");					
			}
		} catch (SQLException ex) {
			System.out.println("Error in SQL " + ex.getMessage());
		}
		sens = sensorData;
		updateSensorTable(sensorData);	
		// close connection to database
		closeConnection();			
		return null;
	}
}