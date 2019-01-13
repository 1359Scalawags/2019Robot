package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.commands.arm.StopBelts;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.arm.BallIntake;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsDown;
import org.usfirst.frc.team1359.robot.commands.autonomous.DriveStraightDistance;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousMiddlePosition extends CommandGroup {

	private char switchPos;

	public AutonomousMiddlePosition() {
		super("AutonomousMiddlePosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		DriverStation driverStation = DriverStation.getInstance();

		switchPos = driverStation.getGameSpecificMessage().charAt(0);

		if (switchPos == 'L') { // left switch
			addSequential(new BallIntake());
			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceShort, Constants.autoDriveShortSpeed));
			addSequential(new TurnByAngle(-90));
			addSequential(new DriveStraightDistance(Constants.avoidCubesMiddle, Constants.autoDriveShortSpeed));
			addSequential(new TurnByAngle(90));
			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceLong, Constants.autoDriveShortSpeed));
			if (Robot.AutonomousMiddlePriority == "Yes") {
				addSequential(new StopBelts()); 
		//		addSequential(new MovesArmDown());
			} else {

			}

		} else { // right switch
			addSequential(new BallIntake());
			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceShort, Constants.autoDriveShortSpeed));
			addSequential(new TurnByAngle(90));
			addSequential(new DriveStraightDistance(Constants.avoidCubesMiddle, Constants.autoDriveShortSpeed));
			addSequential(new TurnByAngle(-90));
			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceLong, Constants.autoDriveShortSpeed));
//			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceShort, Constants.autoDriveShortSpeed));
//			addSequential(new TurnByAngle(90));
//			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceLong, Constants.autoDriveShortSpeed));
//			addSequential(new TurnByAngle(-90));
//			addSequential(new DriveStraightDistance(Constants.avoidSwitchDistanceShort, Constants.autoDriveShortSpeed));
			if (Robot.AutonomousMiddlePriority == "Yes") {
				addSequential(new StopBelts()); 
		//		addSequential(new MovesArmDown());
			} else {

			}

		}

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
