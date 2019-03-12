package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.commands.StartCamera;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Camera extends Subsystem {

	private static boolean hasStarted;

	public Camera(){
		super("Camera");
		hasStarted = false;
	}

	public void start() {
		//hasStarted = false;
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
		setDefaultCommand(new StartCamera());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
