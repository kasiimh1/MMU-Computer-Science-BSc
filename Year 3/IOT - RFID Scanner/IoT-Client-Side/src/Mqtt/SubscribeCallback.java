package Mqtt;

import org.eclipse.paho.client.mqttv3.*;
import com.google.gson.Gson;
import Server.SensorData;
import com.phidget22.PhidgetException;

public class SubscribeCallback implements MqttCallback {

	Gson gson = new Gson();
	String resp;
	SensorData sensor = new SensorData(0, "", "", "", "");
	SensorData conversion;
	Subscriber sub = new Subscriber();

	@Override
	public void connectionLost(Throwable cause) {
		// This is called when the connection is lost. We could reconnect here.
		sub.start();
	}

	@Override
	public void messageArrived(String topic, MqttMessage message) throws InterruptedException, PhidgetException {
		resp = message.toString();
		/* convert message object and extract motorid */
		conversion = gson.fromJson(resp, SensorData.class);
		String replymotorid = conversion.getMotorid();
		String tag = conversion.getTagvalue();
		int deviceid = conversion.getReaderserial();
		/* get motorid of currently connected servo */
		String motorid = String.valueOf(PhidgetMotorMover.getInstance().getDeviceSerialNumber());
		System.out.println("Topic: " + topic + "  TagID: " + tag + "  DeviceID: " + deviceid);
		System.out.println("Waiting until motor at position 180");
		/* check that the current motor id that is connected is the same as in the server reply */
		if (replymotorid.equals(motorid) == true) {
			/* open door for 4.5 seconds and close */
			PhidgetMotorMover.openDoor();
			Thread.sleep(4500);
		} else {			
			System.out.println("Doorid doesn't match with motorid in DB");
		}
	}

	@Override
	public void deliveryComplete(IMqttDeliveryToken token) {
		// no-op
	}
}
