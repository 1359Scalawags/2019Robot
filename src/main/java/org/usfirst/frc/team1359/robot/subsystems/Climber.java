package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.climber.ClimberStrap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * Author: Rebecca Munk
 */
public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	DigitalInput lowerLimit, upperLimit;
	Talon climbMotorStrap;
	Talon pivotMotor;
	boolean climberLocked;

	public Climber() {

		climbMotorStrap = new Talon(RobotMap.climbMotor);
		pivotMotor = new Talon(RobotMap.pivotMotor);
		lowerLimit = new DigitalInput(RobotMap.climbBottomLimit);
		upperLimit = new DigitalInput(RobotMap.climbTopLimit);
		climberLocked = true;
		//Talon elevatorMotor2 = pivotMotor;
		//LiveWindow.add(elevatorMotor2);
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
	
	public void ClimberArm(double speed) {
		//if speed is positive and top limit is not pressed...go up
		
		//if speed is negative and bottom limit is not pressed...go down
		
		//otherwise, stop
		if(climberLocked) {
			pivotMotor.set(0);
		}
		else {
			if(speed > 0 && !isElevated()) {
				pivotMotor.set(speed);
			}
			else if(speed < 0 && !isRetracted()) {
				pivotMotor.set(speed);
			}
			else {
				pivotMotor.set(0);
			}
		}
	}
	
	// public boolean isRockedForward( ) {
	// 	return rocker.get();
	// }

	// public void rockForward() {
		
	// 	rocker.set(true);
	// }

	// public void rockBackward() {
	// 	rocker.set(false);
	// }

	// public void climberStrap(double speed) {
	// 	// warning: this motor is under user control...no protections at this point
	// 	if(climberLocked) {
	// 		climbMotorStrap.set(0);
	// 	}else {
	// 		climbMotorStrap.set(speed);
	// 	}
	// }

	public void stopArm() {
		pivotMotor.set(0);
	}

	public boolean isElevated() {
		boolean value = (upperLimit.get() == Constants.pressed); // pressed is false
		return value;
	}

	public boolean isRetracted() {
		boolean value = (lowerLimit.get() == Constants.pressed);
		return value;
	}
	
	
}
