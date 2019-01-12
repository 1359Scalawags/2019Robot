package org.usfirst.frc.team1359.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class Camera extends Subsystem {

	private static boolean hasStarted = false;

	public void start() {
		hasStarted = false;
		if (hasStarted == false) {
			CameraServer.getInstance().startAutomaticCapture();
			hasStarted = true;
		}
	}

	public boolean isStarted() {
		return hasStarted;
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
