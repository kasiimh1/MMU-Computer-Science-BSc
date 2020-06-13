package Mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

/**
 * @author Dominik Obermaier
 * @author Christian Götz
 */
public class Subscriber {

	public static final String BROKER_URL = "tcp://broker.hivemq.com:1883";
	public static final String userid = "hussaink"; // change this to be your student-id

	// We have to generate a unique Client id.
	String clientId = userid + "-sub";
	private MqttClient mqttClient;

	public Subscriber() {
		try {
			mqttClient = new MqttClient(BROKER_URL, clientId);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void start() {
		try {
			mqttClient.setCallback(new SubscribeCallback());
			/* connect to MQTT */ 
			mqttClient.connect();
			final String topic = userid + "/door/C204";
			/* subscribe to topic */
			mqttClient.subscribe(topic);
			System.out.println("Subscriber is now listening to " + topic);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public static void main(String... args) {
		final Subscriber subscriber = new Subscriber();
		subscriber.start();
	}
}
