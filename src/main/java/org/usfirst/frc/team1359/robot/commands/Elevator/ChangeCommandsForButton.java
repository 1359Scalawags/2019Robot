package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Robot;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChangeCommandsForButton extends CommandGroup {

	public ChangeCommandsForButton() {
		super("ChangeCommandsForButton");
		requires(Robot.kElevatorManipulator);
		Robot.kElevatorManipulator.checkButtonForPress();
		addSequential(new MoveElevatorHatchBottom());
		Robot.kElevatorManipulator.checkButtonForPress();
		addSequential(new MoveElevatorHatchBase());
		Robot.kElevatorManipulator.checkButtonForPress();
		addSequential(new MoveElevatorHatchMiddle());
		Robot.kElevatorManipulator.checkButtonForPress();
		addSequential(new MoveElevatorHatchTop());
	}
}
