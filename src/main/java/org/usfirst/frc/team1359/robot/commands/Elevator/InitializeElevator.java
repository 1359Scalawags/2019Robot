package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class InitializeElevator extends Command {

	public InitializeElevator() {
		super("InitializeElevator");
		requires(Robot.kElevatorManipulator);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.kElevatorManipulator.moveElevatorToHeight(Constants.restingHeight);
		Robot.kElevatorManipulator.setIndex();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.kElevatorManipulator.isToHeight();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
