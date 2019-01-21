package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmManipulator extends Subsystem {

	//DigitalInput bottomLimit, topLimit;
	DigitalInput ballIn;
	Talon leftBeltMotor , rightBeltMotor; // , rotateArmMotor;
	Solenoid rotateArm;
	Solenoid hatchPuncher;
	Solenoid armExtender;
	//boolean beltsLocked; //still need to map the button

	private static final int leftBeltMotorMultiplier = 1; // change to -1 to reverse direction for leftBeltMotor
	private static final int rightBeltMotorMultiplier = 1; // change to -1 to reverse direction for rightBeltMotor

	public enum ArmPosition {
		UP, DOWN
	}

	ArmPosition armposition;

	public ArmManipulator() {
		
	//	bottomLimit = new DigitalInput(RobotMap.armBottomLimit);
	//	topLimit = new DigitalInput(RobotMap.armTopLimit);
		ballIn = new DigitalInput(RobotMap.ballInLimit);
		leftBeltMotor = new Talon(RobotMap.leftBeltMotor);
		rightBeltMotor = new Talon(RobotMap.rightBeltMotor);
		rotateArm = new Solenoid(RobotMap.armRotator);
		hatchPuncher = new Solenoid(RobotMap.hatchPuncher);
		armExtender = new Solenoid(RobotMap.armExtender);
		//rotateArmMotor = new Talon(RobotMap.rotateArmMotor);
		//beltsLocked = true;
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}
	
	public void moveBeltsIn() {
		 	leftBeltMotor.set(Constants.moveBeltSpeed * leftBeltMotorMultiplier);
		 	rightBeltMotor.set(-(Constants.moveBeltSpeed * rightBeltMotorMultiplier));
	}

	public void moveBeltsOut(){
		 	leftBeltMotor.set(-(Constants.moveBeltSpeed * leftBeltMotorMultiplier));
		 	rightBeltMotor.set(Constants.moveBeltSpeed * rightBeltMotorMultiplier);
	}

	public void extendHatchPuncher(){
		hatchPuncher.set(true);
	}

	public void retractHatchPuncher(){
		hatchPuncher.set(false);
	}

	public void extendArms(){
		armExtender.set(true); // true extends the arm
	}

	public void retractArms(){
		armExtender.set(false);
	}
	
	public void rotateArms(ArmPosition pos) {
		if (pos == ArmPosition.DOWN) {
			goDown();
		}
		 else if (pos == ArmPosition.UP) {
				goUp();
			} 
			else {
			}
	}
	
	private void goDown() {
		rotateArm.set(false); 
	}

	private void goUp() {
		rotateArm.set(true); // true is moving the arms up
	}

	public void stopBelts() {
		leftBeltMotor.set(0);
		rightBeltMotor.set(0);
	}

	public boolean isBallIn(){
		return ballIn.get() == Constants.pressed;
	}

	// public void unlockBelts(){
	// 	beltsLocked = false;
	// }

	// public boolean getBeltsLocked(){
	// 	return beltsLocked;
	// }

	// public boolean isUp() {
	// 	return (topLimit.get() == Constants.pressed);
	// }

	// public boolean isDown() {
	// 	return (bottomLimit.get() == Constants.pressed);
	// }
}
