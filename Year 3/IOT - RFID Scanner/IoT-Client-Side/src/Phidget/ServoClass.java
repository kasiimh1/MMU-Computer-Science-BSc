package Phidget;

import com.phidget22.PhidgetException;
import Mqtt.Subscriber;

public class ServoClass {

	public static void main(String[] args) throws PhidgetException {
		new ServoClass();
	}

	Subscriber sub = new Subscriber();

	public ServoClass() throws PhidgetException {
		/* start the subscriber */
		sub.start();
	}
}