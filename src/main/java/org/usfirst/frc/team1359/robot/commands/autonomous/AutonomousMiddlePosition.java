package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendAndRetractArms;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team1359.robot.commands.Delay;

/**
 *
 */
public class AutonomousMiddlePosition extends CommandGroup {


	public AutonomousMiddlePosition() {
		super("AutonomousLeftPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		requires(Robot.kElevatorManipulator);

		addSequential(new DriveStraightDistance(Constants.distanceToFrontCargoShip, Constants.autoDriveShortSpeed));
		addSequential(new DriveToTarget());
		addSequential(new ExtendAndRetractArms()); //extend 
		addSequential(new Delay(1));
		addSequential(new ExtendAndRetractArms()); // retract
	}
}
		
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
