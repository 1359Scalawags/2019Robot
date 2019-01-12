package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PneumaticsSystem extends Subsystem {

	Compressor compressor = new Compressor(RobotMap.compressor);

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

		compressor.setClosedLoopControl(true);

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public boolean isCharged() {
		return !compressor.getPressureSwitchValue();
	}

	public boolean isRunning() {
		return compressor.getCompressorCurrent() > 0.01;
	}

}
