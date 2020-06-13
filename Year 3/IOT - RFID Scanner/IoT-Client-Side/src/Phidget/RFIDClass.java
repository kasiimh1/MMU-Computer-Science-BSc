package Phidget;

import org.eclipse.paho.client.mqttv3.MqttException;

import com.google.gson.Gson;
import com.phidget22.DigitalOutput;
import com.phidget22.PhidgetException;
import com.phidget22.RFID;
import com.phidget22.RFIDTagEvent;
import com.phidget22.RFIDTagListener;
import Server.ServerConnection;
import Server.SensorData;
import Mqtt.Publisher;

public class RFIDClass {

	DigitalOutput grant = new DigitalOutput(); // Green light showing access granted
	DigitalOutput deny = new DigitalOutput(); // Red light showing access denied
	RFID phid = new RFID();
	int scanTime = 900000; // define the time we will scan for tags using the RFID Reader
	ServerConnection serverconn = new ServerConnection();
	Publisher mqttconn = new Publisher();
	Gson gson = new Gson();
	String resp;
	String oneSensorJson = new String();
	SensorData sensor = new SensorData(0, "", "unknown", "unknown", "unknown");
	SensorData reply;

	public static void main(String[] args) throws PhidgetException, MqttException {
		new RFIDClass();
	}

	public RFIDClass() throws PhidgetException, MqttException {
		System.out.println("Scanning for RFID Tags Now.....");

		// set the output indicators to channel 0 and 1 on RFID board
		deny.setChannel(1); // 0 is denied need to add a LED 
		grant.setChannel(2); // 1 is granted using built in LED

		// open for writing
		deny.open(scanTime);
		grant.open(scanTime);

		// Make the RFID Phidget able to detect loss or gain of an rfid card
		phid.addTagListener(new RFIDTagListener() {
			public void onTag(RFIDTagEvent e) {
				System.out.println("Checking against the DB");
				/* send a request to find out if the tag is present in the valid tags table */
				String tag = e.getTag();
				int readerid;
				System.out.println("[DEBUG] Tag value = " + tag);
				String valid = null;
				String roomid = null;
				String motorid = null;
				try {
	                /* setup object values */
					readerid = phid.getDeviceSerialNumber();
					sensor.setTagvalue(tag);
					sensor.setReaderserial(readerid);
					/* debug object values */
					System.out.println("[DEBUG] SensorData = " + sensor.toString());
	                /* convert to object */
					oneSensorJson = gson.toJson(sensor);
					/* send req to server */
					resp = serverconn.sendToServer(oneSensorJson);
	                /* convert received reply from server */
					reply = gson.fromJson(resp, SensorData.class);
					System.out.println("REPLY FROM SERVER " + reply);
					
	                /*get resp values from server reply */		
					roomid = reply.getRoomid();
					valid = reply.getValid();
					motorid = reply.getMotorid();

				} catch (PhidgetException e2) {
					e2.printStackTrace();
				}

				if (valid.equalsIgnoreCase("true")) {
					// display red light because access is not granted!
					try {
						System.out.println("[RFID] Authorization granted");
						/* publish the message over mqtt */
						mqttconn.publisher(roomid, resp);    
                        mqttconn.publisher("access", resp);
						grant.setState(true);
						/* display light for 4.5 seconds */
						Thread.sleep(4500);
						grant.setState(false);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				} else {
					// display red light because access is not granted!
					System.out.println("[RFID] Authorization failed");
					try {
                        mqttconn.publisher("access", resp);
						/* display light for 2 seconds */
						deny.setState(true);
						Thread.sleep(2000);
						deny.setState(false);
					} catch (PhidgetException | InterruptedException | MqttException e1) {
						e1.printStackTrace();
					}
				}				
			}
		});
		// Open and start detecting rfid cards
		phid.open(scanTime / 3); // wait for device to respond
		phid.setAntennaEnabled(true);

		System.out.println("Scanning RFID space for " + (scanTime / 1000) + " seconds");
		try {
			Thread.sleep(scanTime);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}	
		phid.close();
		System.out.println("\nClosed RFID");
	}
}