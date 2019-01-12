package org.usfirst.frc.team1359.robot.commands.drive;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class EnableDriveStraight extends Command {

    public EnableDriveStraight() {
    	super("EnableDriveStraight");
    	requires(Robot.kPIDDriveSystem);	
    }

    private double target;
    	
    // Called just before this Command runs the first time
    protected void initialize() {
    	target = Robot.kPIDDriveSystem.getAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kPIDDriveSystem.arcadeDrive(Constants.driveStraightSpeed, Constants.maxTurnRate, target);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.kOI.getDriveStraightButton();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kPIDDriveSystem.arcadeDrive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
