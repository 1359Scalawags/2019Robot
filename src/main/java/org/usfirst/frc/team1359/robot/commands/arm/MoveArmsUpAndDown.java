package org.usfirst.frc.team1359.robot.commands.arm;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorToHeight;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class MoveArmsUpAndDown extends CommandGroup {
    static ArmPos moveArms = ArmPos.UP;
    private enum ArmPos{
        UP, DOWN
    }
	public MoveArmsUpAndDown() {
        if(moveArms == ArmPos.UP){
            addSequential(new MoveArmsDown());
            addSequential(new MoveElevatorToHeight(Constants.liftAboveBrushHeight));
            moveArms = ArmPos.DOWN;
        }
        else{
            addSequential(new MoveArmsUp());
            moveArms = ArmPos.UP;
        }
	}
}