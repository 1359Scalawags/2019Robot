package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GetHatchFromPortal extends CommandGroup {

	public GetHatchFromPortal() {
       super("GetHatchFromPortal");
       requires(Robot.kArmManipulator);
       requires(Robot.kElevatorManipulator);
       
       addSequential(new MoveElevatorToHeight(Constants.liftToHatchPortal));
       addSequential(new Delay(.25f));
       addSequential(new ExtendArms());
       addSequential(new GrabHatch());
       addSequential(new Delay(.25f));
       addSequential(new MoveElevatorToHeight(Constants.liftAboveBrushHeight));
       addSequential(new RetractArms());
     //  addSequential(new MoveElevatorToHeight(Constants.restingHeight));
	}
}