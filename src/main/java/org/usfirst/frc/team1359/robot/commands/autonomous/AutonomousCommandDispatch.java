package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousCommandDispatch extends CommandGroup {

	private DriverStation.Alliance alliance;
	private String autonOverride;
	private AutoModes autoMode;

	public enum AutoModes {
		left,
		right,
		middle
	}

	public AutonomousCommandDispatch(String override) {
		super("AutonomousCommandDispatch");
		requires(Robot.kPIDDriveSystem);
		DriverStation driverStation = DriverStation.getInstance();

		alliance = driverStation.getAlliance();
		autonOverride = override;
		if(autonOverride.equals(Constants.autoModeLeft)) {
			autoMode = AutoModes.left;
		}else if(autonOverride.equals(Constants.autoModeMiddle)) {
			autoMode = AutoModes.middle;
		}else if(autonOverride.equals(Constants.autoModeRight)) {
			autoMode = AutoModes.right;
		}else {
			if(driverStation.getLocation() == 1) {
				autoMode = AutoModes.left;
			}else if(driverStation.getLocation() == 2) {
				autoMode = AutoModes.middle;
			}else if(driverStation.getLocation() == 3) {
				autoMode = AutoModes.right;
			}else {
				
			}
		}

		SmartDashboard.putString("FMS", alliance.toString() + "\n" + driverStation.getGameSpecificMessage());

		
		if (autoMode == AutoModes.left) { // left
			addSequential(new AutonomousLeftPosition()); // get into position
			SmartDashboard.putString("Location", "Left");

		} else if (autoMode == AutoModes.middle) {// middle
			addSequential(new AutonomousMiddlePosition());// get into position
			SmartDashboard.putString("Location", "Middle");

		} else if (autoMode == AutoModes.right) {// right
			addSequential(new AutonomousRightPosition());// get into position
			SmartDashboard.putString("Location", "Right");

		} else {

		}
	}
}
