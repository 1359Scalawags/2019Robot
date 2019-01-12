package org.usfirst.frc.team1359.robot.commands.shooter;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PrepareToLaunchShooter extends CommandGroup {

	public PrepareToLaunchShooter() {
		super("PrepareToLaunchShooter");
		requires(Robot.kCubeShooter);
		addSequential(new PullShooter());
		// addSequential(new LockShooter()); Do not need this command
		addSequential(new ShooterUnwind());
		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
