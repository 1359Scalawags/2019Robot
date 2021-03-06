package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallIntake extends Command {

	public BallIntake() {
		super("BallIntake");
		requires(Robot.kArmManipulator);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.kArmManipulator.moveBeltsIn();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
	return Robot.kArmManipulator.isBallIn();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.kArmManipulator.stopBelts();
	}
}
