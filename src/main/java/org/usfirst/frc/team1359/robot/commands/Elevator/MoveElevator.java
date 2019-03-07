package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.OI;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveElevator extends CommandGroup {

	private OI.DPadState dpadState;
	public MoveElevator() {
        super("MoveElevator");
        dpadState = Robot.kOI.getDpadYValue();
        addSequential(new Delay(.25f));
        if(dpadState == OI.DPadState.DOWN){
            addSequential(new MoveElevatorDown());
        }
        else if(dpadState == OI.DPadState.UP){
            addSequential(new MoveElevatorUp());
        }
        else{
        }
	}
}