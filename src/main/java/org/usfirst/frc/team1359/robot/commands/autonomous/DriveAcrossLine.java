package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.GrabHatch;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveAcrossLine extends CommandGroup {

	public DriveAcrossLine() {
		super("DriveAcrossLine");
        requires(Robot.kPIDDriveSystem);
        requires(Robot.kArmManipulator);
        addSequential(new GrabHatch());
        addSequential(new Delay(.5f));
        addSequential(new GrabHatch());
        addSequential(new DriveStraightDistance(Constants.distanceAcrossLine, Constants.autoDriveSpeed));
	}
}