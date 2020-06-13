package Mqtt;

import org.eclipse.paho.client.mqttv3.*;

public class Publisher {
	
    /* set unique values for MQTT Server Connection */
	private MqttClient client;
	public static final String clientID = "hussaink";
	public static final String brokerURL = "tcp://broker.hivemq.com:1883";
	public static final String Topic_Generic = clientID + "/door/";

	public Publisher() throws MqttException {
		try {
			client = new MqttClient(brokerURL, clientID);
			MqttConnectOptions ops = new MqttConnectOptions();
			ops.setCleanSession(false);
			ops.setWill(client.getTopic(clientID + "/LWT"), "I'm gone :( ".getBytes(), 0, false);
			this.client.connect(ops);
		} catch (MqttException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void publisher(String topic, String message) throws MqttException {
        /* only connect if not already connected */
		if (client.isConnected() == false) {
			client.connect();
		}
		final MqttTopic mqttTopic = client.getTopic(Topic_Generic + topic);
        /* publish message over MQTT */
		mqttTopic.publish(new MqttMessage(message.getBytes()));
        /* print to debug */
		System.out.println("Published Data... Topic is: " + mqttTopic.getName() + " , Message: " + message);
        /* disconnect form server */
		client.disconnect();
		System.out.println("Disconnecting from MQTT Server");
	}
}