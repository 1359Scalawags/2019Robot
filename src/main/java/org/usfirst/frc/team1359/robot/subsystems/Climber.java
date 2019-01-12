package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	Talon climbMotorStrap;
	Talon pivotMotor;
	boolean climberLocked;

	private final static int MotorMultiplier = 1; // change to -1 if motor is reversed

	public Climber() {

		climbMotorStrap = new Talon(RobotMap.climbMotor);
		pivotMotor = new Talon(RobotMap.pivotMotor);
		lowerLimit = new DigitalInput(RobotMap.climbBottomLimit);
		upperLimit = new DigitalInput(RobotMap.climbTopLimit);
		climberLocked = true;
	}

	public void initDefaultCommand() {
		//setDefaultCommand(new ClimberStrap());
	}

	public void unlockClimber() {
		climberLocked = false;
	}
	
	public boolean getClimberLock() {
		return climberLocked;
	}
	
	public void ClimberRotate(double speed) {
		//if speed is positive and top limit is not pressed...go up
		
		//if speed is negative and bottom limit is not pressed...go down
		
		//otherwise, stop
		if(climberLocked) {
			pivotMotor.set(0);
		}
		else {
			if(speed > 0 && !isClimbPosition()) {
				pivotMotor.set(speed * MotorMultiplier);
			}
			else if(speed < 0 && !isDrivePosition()) {
				pivotMotor.set(speed * MotorMultiplier);
			}
			else {
				pivotMotor.set(0);
			}
		}
	}

	public void stopClimber() {
		pivotMotor.set(0);
	}

	public boolean isClimbPosition() {
		boolean value = (upperLimit.get() == Constants.pressed); // pressed is false
		return value;
	}

	public boolean isDrivePosition() {
		boolean value = (lowerLimit.get() == Constants.pressed);
		return value;
	}
	
	
}
