package org.usfirst.frc.team1359.robot.commands.shooter;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ReleaseShooter extends Command {

//	private boolean failed = false;
//	private boolean hasfired = false;
	//Timer lockTimer;

	public ReleaseShooter() {
		super("ReleaseShooter");
		requires(Robot.kCubeShooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
//		lockTimer = new Timer();
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		lockTimer.reset();
//		lockTimer.start();	
//		hasfired = false;
//		failed = false;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
//		if (Robot.kCubeShooter.isReadyToFire() && Robot.kCubeShooter.shooterIsUnwound()) {
//			
////			failed = false;
//			hasfired = true;
////		} else {
////			failed = true;
//		}
//		else {
//			failed = true;
	//	}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
//		if(hasfired) {
//			if(lockTimer.get() > 2000) {
//				return true;
//			}
//			else {
//				Robot.kCubeShooter.unlockShooter();
//				
//				return false;
//			}	
//		}
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
		
		if (Robot.kCubeShooter.isReadyToFire() && Robot.kCubeShooter.shooterIsUnwound()) {
			Robot.kCubeShooter.unlockShooter();
			Robot.kCubeShooter.setReadyToFire(false);
		}
		}
		
//		Robot.kCubeShooter.lockShooter();
//		lockTimer.stop();
//		System.out.println("Release shooter: " + !failed + "  Relock delay: " + lockTimer.get());
//		if(failed) {
//			SmartDashboard.putString("Shooter", "Failed");
//		} else {
//			SmartDashboard.putString("Shooter", "Fired");
//			Robot.kCubeShooter.setReadyToFire(false);
//		}


//	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//end();
	}
}
