package org.usfirst.frc.team1359.robot.commands.drive;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveDistance extends Command {

	private double m_startDistance;
	private double m_remainingDistance;
	private double m_deltaDistance;
	private double m_motorSpeed = 0;
	private boolean m_direction;

	public MoveDistance(double distance, boolean direction) {

		super("MoveForward");
		requires(Robot.kPIDDriveSystem);
		m_deltaDistance = distance;
		m_direction = direction;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		m_startDistance = Robot.kPIDDriveSystem.getAverageDistance();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (m_direction) {
			Robot.kPIDDriveSystem.arcadeDrive(Constants.autoDriveSpeed, 0);
		} else {
			Robot.kPIDDriveSystem.arcadeDrive(-(Constants.autoDriveSpeed), 0);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (m_direction) {
			return (Robot.kPIDDriveSystem.getAverageDistance() - m_startDistance) >= m_deltaDistance;
		} else {
			return (Robot.kPIDDriveSystem.getAverageDistance() - m_startDistance) <= -m_deltaDistance;
		}
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.kPIDDriveSystem.arcadeDrive(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
