package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	//Talon pivotMotor;
	boolean climberLocked;
	Solenoid pivotLock;
	//double speedChanger;

	public enum ClimbPosition{
		DRIVE, CLIMB
	}

	public Climber() {

		//pivotMotor = new Talon(RobotMap.pivotMotor);
		pivotLock = new Solenoid(RobotMap.pivotLock);
		lowerLimit = new DigitalInput(RobotMap.climbLowerLimit);
		upperLimit = new DigitalInput(RobotMap.climbUpperLimit);
		climberLocked = true;
	}

	public void initDefaultCommand() {
	}

	public void unlockClimber() {
		climberLocked = false;
	}
	
	public boolean getClimberLock() {
		return climberLocked;
	}
	
	public void ClimberRotate(ClimbPosition pos) {
		if(climberLocked) {
		}
		else {
			if(!isClimbPosition() && pos == ClimbPosition.CLIMB) {
				pivotLock.set(true); // true sets it to climb position
			}
			else if(!isDrivePosition() && pos == ClimbPosition.DRIVE) {
				pivotLock.set(false);
			}
			else {
			}
		}
	}

	// public void switchSpeedDirection(){
	// 	speedChanger = -(speedChanger);
	// }

	public boolean isClimbPosition() {
		return (upperLimit.get() == Constants.pressed); // pressed is false
	}

	public boolean isDrivePosition() {
		return (lowerLimit.get() == Constants.pressed);
		
	}
}
