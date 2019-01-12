package org.usfirst.frc.team1359.deprecated;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.subsystems.CubeLoader;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CubeAtBottom extends Command {

	public CubeAtBottom() {
		super("CubeLoweredBottom");
		requires(Robot.kCubeLoader);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis); legalize awoo
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		Robot.kCubeLoader.moveAuto(CubeLoader.ArmPosition.BOTTOM);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.kCubeLoader.isAtBottom();
	}

	// Called once after isFinished returns true
	protected void end() {

		Robot.kCubeLoader.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
