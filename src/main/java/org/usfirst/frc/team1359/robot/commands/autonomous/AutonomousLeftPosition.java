package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.deprecated.CubeAtTop;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.BallIntake;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsDown;
import org.usfirst.frc.team1359.robot.commands.arm.StopBelts;
import org.usfirst.frc.team1359.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorDown;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousLeftPosition extends CommandGroup {


	public AutonomousLeftPosition() {
		super("AutonomousLeftPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		requires(Robot.kElevatorManipulator);

		addSequential(new DriveStraightDistance(Constants.distanceToFrontCargoShip, Constants.autoDriveShortSpeed));
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
