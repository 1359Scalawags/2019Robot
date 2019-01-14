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
	Talon pivotMotor;
	boolean climberLocked;
	//double speedChanger;

	private final static int MotorMultiplier = 1; // change to -1 to reverse motor direction

	public Climber() {

		pivotMotor = new Talon(RobotMap.pivotMotor);
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
	
	public void ClimberRotate(double speed) {
		//speed = speedChanger;

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

	// public void switchSpeedDirection(){
	// 	speedChanger = -(speedChanger);
	// }

	public void stopClimber() {
		pivotMotor.set(0);
	}

	public boolean isClimbPosition() {
		return (upperLimit.get() == Constants.pressed); // pressed is false
	}

	public boolean isDrivePosition() {
		return (lowerLimit.get() == Constants.pressed);
		
	}
	
	
}
