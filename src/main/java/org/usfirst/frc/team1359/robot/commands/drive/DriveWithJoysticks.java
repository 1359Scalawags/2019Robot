package org.usfirst.frc.team1359.robot.commands.drive;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithJoysticks extends Command {

	public DriveWithJoysticks() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		super("DriveWithJoysticks");
		requires(Robot.kPIDDriveSystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.kPIDDriveSystem.resetGyro();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(Robot.kElevatorManipulator.getElevatorHeight() <= Constants.distanceFromPot){
			Robot.kPIDDriveSystem.tankDrive(Robot.kOI.getLStickY(), Robot.kOI.getRStickY());
		}
		else{
			Robot.kPIDDriveSystem.tankDrive(Robot.kOI.getLStickY()*(-.0064*Robot.kElevatorManipulator.getElevatorHeight()+1.064), Robot.kOI.getRStickY()*(-.0064*Robot.kElevatorManipulator.getElevatorHeight()+1.064));
		}
		// look at this code. needs work
		
	//	Robot.kPIDDriveSystem.tankDrive(Robot.kOI.getLStickY(), Robot.kOI.getRStickY());
		SmartDashboard.putNumber("Elevator Height",Robot.kElevatorManipulator.getElevatorHeight());
		SmartDashboard.putNumber("Gyro", Robot.kPIDDriveSystem.getAngle());
		SmartDashboard.putNumber("Encoder Distance", Robot.kPIDDriveSystem.getDistanceLeft());
		SmartDashboard.putNumber("Encoder Distance", Robot.kPIDDriveSystem.getDistanceRight());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
