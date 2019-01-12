package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.deprecated.LeonardoControll;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Aesthetics extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new LeonardoControll());
	}
}
