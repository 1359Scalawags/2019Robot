package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendArms;
import org.usfirst.frc.team1359.robot.commands.arm.ReleaseHatch;
import org.usfirst.frc.team1359.robot.commands.arm.RetractArms;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class PutHatchToHeight extends CommandGroup {

	public PutHatchToHeight(double height) {
       super("PutHatchToHeight");
       requires(Robot.kArmManipulator);
       requires(Robot.kElevatorManipulator);
       addSequential(new MoveElevatorToHeight(height));
       addSequential(new Delay(.25f));// only if vision works
    //   addSequential(new MoveElevatorSlider()); 
       addSequential(new ExtendArms());
       addSequential(new ReleaseHatch());
       addSequential(new Delay(.25f));
       addSequential(new RetractArms());
     //  addSequential(new MoveElevatorToHeight(Constants.liftToHatchPortal));
	}
}