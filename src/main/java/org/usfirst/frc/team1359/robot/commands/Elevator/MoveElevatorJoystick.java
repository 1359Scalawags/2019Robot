package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorJoystick extends Command {

	public MoveElevatorJoystick() {
		super("MoveElevatorJoystick");
		requires(Robot.kElevatorManipulator);
		//visionOverride = entry;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//setInterruptible(false);
	
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
        Robot.kElevatorManipulator.moveElevatorJoystick(Robot.kOI.getLiftStick());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	protected void interrupted() {
		// cannot be interrupted
	}
}