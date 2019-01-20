package org.usfirst.frc.team1359.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ChangeHeightModes extends CommandGroup {

	boolean initElevator = false;

	public ChangeHeightModes() {
		super("ChangeHeightModes");
		if(!initElevator){
		addSequential(new InitializeElevator());
		initElevator = true;
		}
		else{
		addSequential(new SwitchHeightModes());
		}
	}
}
