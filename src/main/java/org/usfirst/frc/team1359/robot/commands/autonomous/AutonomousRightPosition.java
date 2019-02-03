package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendAndRetractArms;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousRightPosition extends CommandGroup {

	public AutonomousRightPosition() {
		super("AutonomousRightPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		requires(Robot.kElevatorManipulator);

		
	addSequential(new DriveStraightDistance(Constants.distanceOffTier2, Constants.autoDriveShortSpeed));
	addSequential(new TurnByAngle(45));
	addSequential(new DriveStraightDistance(Constants.distanceToRocket, Constants.autoDriveSpeed));
	addSequential(new DriveToTarget());
	addSequential(new ExtendAndRetractArms()); // extend
	addSequential(new Delay(1));
	addSequential(new ExtendAndRetractArms()); // retract
	}
	
}
