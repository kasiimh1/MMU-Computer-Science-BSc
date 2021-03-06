package Server;

import java.io.*;
import java.net.*;

public class ServerConnection {

	// this connects to the localhost server allowing it to send door state and post
	// the values.

	String address = "http://localhost:8080/IoT-Server-Side/SensorServer";

	public String sendToServer(String oneSensorJson) {
		URL url;
		HttpURLConnection conn;
		BufferedReader rd;
		// Replace invalid URL characters from json string
		try {
			oneSensorJson = URLEncoder.encode(oneSensorJson, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String fullURL = address + "?sensordata=" + oneSensorJson;
		System.out.println("Sending data to: " + fullURL); // DEBUG confirmation message
		String line;
		String result = "";
		try {
			url = new URL(fullURL);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			// Request response from server to enable URL to be opened
			while ((line = rd.readLine()) != null) {
				result += line;
			}
			rd.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}