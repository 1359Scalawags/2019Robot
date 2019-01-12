/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.deprecated;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1359.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class ExampleCommand extends Command {
	
	Timer m_timer;
	public ExampleCommand() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.kExampleSubsystem);
		m_timer = new Timer();
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		SmartDashboard.putString("Status", "Ready to Launch");
		m_timer.start();
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		SmartDashboard.putString("Status", "Launched");
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		if(m_timer.get() > 5.0) {
			return true;
		}
		else {
			return false;
		}
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {

		SmartDashboard.putString("Status", "Ready to Launch");

		m_timer.stop();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
