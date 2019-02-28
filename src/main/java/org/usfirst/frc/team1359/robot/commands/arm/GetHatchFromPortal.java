package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GetHatchFromPortal extends CommandGroup {

	public GetHatchFromPortal() {
       addSequential(new MoveElevatorToHeight(Constants.liftToHatchPortal));
       addSequential(new ExtendArms());
       addSequential(new Delay(1));
       addSequential(new GrabHatch());
       addSequential(new MoveElevatorToHeight(Constants.liftAboveBrushHeight));
       addSequential(new RetractArms());
       addSequential(new MoveElevatorToHeight(Constants.restingHeight));
	}
}