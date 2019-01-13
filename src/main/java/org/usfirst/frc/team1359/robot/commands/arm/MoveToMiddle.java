package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveToMiddle extends Command {

	Timer moveArmTimer;
	
    public MoveToMiddle() {
    	super("MoveToMiddle");
    	requires(Robot.kCubeLoader);
    	requires(Robot.kPIDDriveSystem);
    	moveArmTimer = new Timer();
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	moveArmTimer.reset();
    	moveArmTimer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.kPIDDriveSystem.arcadeDrive(Constants.approachSwitchSpeed, 0);
    //	Robot.kCubeLoader.moveBelts(Constants.moveToMiddleArmSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(moveArmTimer.get() > Constants.moveToMiddleArmTime) {
        return true;
    }
    	else {
    		return false;
    	}
    	}

    // Called once after isFinished returns true
    protected void end() {
    	Robot.kCubeLoader.stopBelts();
    	Robot.kPIDDriveSystem.arcadeDrive(0,0);
    	moveArmTimer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
