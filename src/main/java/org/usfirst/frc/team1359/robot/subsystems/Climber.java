package org.usfirst.frc.team1359.robot.subsystems;

//import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

//import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Climber extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	//DigitalInput lowerLimit, upperLimit;
	//Talon pivotMotor;
	boolean climberLocked;
	Solenoid climbLock;
	Solenoid pivotLock;
	Solenoid backExtender;
	//double speedChanger;

	public enum ClimbPosition{
		DRIVE, CLIMB
	}

	public Climber() {

		//pivotMotor = new Talon(RobotMap.pivotMotor);
		climbLock = new Solenoid(RobotMap.climbLock);
		pivotLock = new Solenoid(RobotMap.pivotLock);
		backExtender = new Solenoid(RobotMap.climbBackExtender);
		//lowerLimit = new DigitalInput(RobotMap.climbLowerLimit);
		//upperLimit = new DigitalInput(RobotMap.climbUpperLimit);
		climberLocked = true;
		climbLock.setName("climbLocker");
		pivotLock.setName("pivotLock");
		backExtender.setName("ClimbBackExtender");
		SmartDashboard.putData(backExtender);
		SmartDashboard.putData(climbLock);
		SmartDashboard.putData(pivotLock);
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
			if(/*!isClimbPosition() &&*/ pos == ClimbPosition.CLIMB) {
				climbLock.set(false); // true sets it to climb position
			}
			else if(/*!isDrivePosition() &&*/ pos == ClimbPosition.DRIVE) {
				climbLock.set(true);
			}
			else {
			}
		}
	}

	public void unlockPivot(){
		pivotLock.set(true);
	}
	public void lockPivot(){
		pivotLock.set(false);
	}

	public void extendBack(){
		if(climberLocked){}
		else{
			backExtender.set(true);
		}
	}

	public void retractBack(){
		if(climberLocked){}
		else{
			backExtender.set(false);
		}
	}
	// public void switchSpeedDirection(){
	// 	speedChanger = -(speedChanger);
	// }

	// public boolean isClimbPosition() {
	// 	return (upperLimit.get() == Constants.pressed); // pressed is false
	// }

	// public boolean isDrivePosition() {
	// 	return (lowerLimit.get() == Constants.pressed);
		
	// }
}
