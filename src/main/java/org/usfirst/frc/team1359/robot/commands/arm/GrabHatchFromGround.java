package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabHatchFromGround extends CommandGroup {

	public GrabHatchFromGround() {
       addSequential(new MoveElevatorToHeight(Constants.restingHeight));
       addSequential(new MoveElevatorToHeight(1));
       addSequential(new MoveArmsDown());
       addSequential(new Delay(2));
       addSequential(new GrabHatch());
       addSequential(new MoveArmsUp());
	}
}