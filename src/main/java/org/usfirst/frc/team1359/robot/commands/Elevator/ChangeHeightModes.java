package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChangeHeightModes extends CommandGroup {

	static boolean initElevator = false;

	public ChangeHeightModes() {
		super("ChangeHeightModes");
		requires(Robot.kElevatorManipulator);
		if(!initElevator){
		addSequential(new InitializeElevator());
		initElevator = true;
		}
		else{
		addSequential(new SwitchHeightModes());
		}
	}
}
