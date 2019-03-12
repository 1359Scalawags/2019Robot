package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorSlider extends Command {

	private String visionOverride;
	private VisionOverride override;

	public enum VisionOverride{
		YES, NO
	}

	public MoveElevatorSlider(/*String entry*/) {
		super("MoveElevatorSlider");
		requires(Robot.kArmManipulator);
		//visionOverride = entry;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		//setInterruptible(false);
	
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		visionOverride = Robot.override.getSelected();
		if(visionOverride!= null){
			if(visionOverride.equals(Constants.overrideYes)){
				override = VisionOverride.YES;
			}
			if(!(override == VisionOverride.YES)){
				Robot.kArmManipulator.initializeMoveSlider();
			}
		}
		else{
			Robot.kArmManipulator.initializeMoveSlider();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(override == VisionOverride.YES){
			Robot.kArmManipulator.moveSliderJoystick(Robot.kOI.getSliderStick());
		}
		else{
			Robot.kArmManipulator.moveSlider();
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(override == VisionOverride.YES){
			return false;
		}
		else{
			return Robot.kArmManipulator.isAtCenterTarget();
		}
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	protected void interrupted() {
		// cannot be interrupted
	}
}
