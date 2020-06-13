package Mqtt;

import com.phidget22.PhidgetException;
import com.phidget22.RCServo;

public class PhidgetMotorMover {
	// Singleton implementation to allow multiple callbacks to the code
	static RCServo servo = null;
	@SuppressWarnings("unused")
	private static PhidgetMotorMover instance = null;

	public static RCServo getInstance() {
		if (servo == null) {
			servo = PhidgetMotorMover();
		}
		return servo;
	}

	private static RCServo PhidgetMotorMover() {
		// Create new instance of servo board and start listening for motor changes
		// This method should only be called once when first constructing a servo
		// instance
		try {
			System.out.println("Constructing MotorMover");
			servo = new RCServo();
			// Start listening for motor interaction
			servo.open(2000);
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
		return servo;
	}

	 static void openDoor() {
		try {
			// Get the servo that is available
			PhidgetMotorMover.getInstance();
			System.out.println("Opening Door for 3 seconds");
			/* open door */
			servo.setTargetPosition(180);
			servo.setEngaged(true);
			try {
				/* wait 3 seconds */
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/* close door */
			System.out.println("Closing Door");
			servo.setTargetPosition(0);
			try {
				/* wait 1.5 seconds for action to complete */
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			/* check the door is in the locked position */
			if (servo.getPosition() == 0)
				System.out.println("Door Closed");
			/* if door position isn't 0 try to relock the door again */
			if (servo.getPosition() > 0) {
				System.out.println("Door not closed at pos: " + servo.getPosition());
				servo.setTargetPosition(0);
			}
			
		} catch (PhidgetException e) {
			e.printStackTrace();
		}
	}
}
