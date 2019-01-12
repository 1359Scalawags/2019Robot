package org.usfirst.frc.team1359.robot.commands.shooter;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PullShooter extends Command {

	public PullShooter() {
		super("PullShooter");
		requires(Robot.kCubeShooter);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//setInterruptible(false);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.kCubeShooter.lockShooter();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(!Robot.kCubeShooter.isDown()) {
			Robot.kCubeShooter.pullShooter();
		}
		else {
			Robot.kCubeShooter.stopShooterMotor();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.kCubeShooter.isDown();
	}

	// Called once after isFinished returns true
	protected void end() {
	//	SmartDashboard.putString("Shooter", "Pulled Down");
		Robot.kCubeShooter.stopShooterMotor();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// this command is not interruptible
	}
}
