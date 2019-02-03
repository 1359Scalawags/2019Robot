package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DriveToTarget extends Command {

    private boolean isAtTarget;
    private float turnAngle;
    private double m_startAngle;
    private double m_angleRemaining;
   
   public DriveToTarget() {
    	super("DriveToTarget");
        requires(Robot.kPIDDriveSystem);
        requires(Robot.kNetwork);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        turnAngle = -(Robot.kNetwork.returnAngleFromTarget());
        m_startAngle = Robot.kPIDDriveSystem.getAngle();
        m_angleRemaining = turnAngle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(Robot.kNetwork.returnDistanceFromTarget() > Constants.withinDistanceToTarget && Math.abs(m_angleRemaining) > Constants.ROTATE_TOLERANCE){
		    Robot.kPIDDriveSystem.arcadeDrive(Constants.visionSpeed, Constants.maxTurnRate, m_startAngle + turnAngle);
            m_angleRemaining = (turnAngle - (Robot.kPIDDriveSystem.getAngle() - m_startAngle));
            isAtTarget = false;
        }
        else if(Robot.kNetwork.returnDistanceFromTarget() > Constants.withinDistanceToTarget && Math.abs(m_angleRemaining) < Constants.ROTATE_TOLERANCE){
            Robot.kPIDDriveSystem.arcadeDrive(Constants.visionSpeed, Constants.maxTurnRate, 0);
            isAtTarget = false;
        }
        else if(Robot.kNetwork.returnDistanceFromTarget() < Constants.withinDistanceToTarget && Math.abs(m_angleRemaining) > Constants.ROTATE_TOLERANCE){
            Robot.kPIDDriveSystem.arcadeDrive(0, Constants.maxTurnRate, m_startAngle + turnAngle);
            m_angleRemaining = (turnAngle - (Robot.kPIDDriveSystem.getAngle() - m_startAngle));
            isAtTarget = false;
        }
        else{
            Robot.kPIDDriveSystem.arcadeDrive(0, 0);
            isAtTarget = true;
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isAtTarget;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}