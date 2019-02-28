package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmManipulator extends Subsystem {

	DigitalInput bottomLimit, topLimit;
	DigitalInput ballIn;
	Talon leftBeltMotor , rightBeltMotor , rotateArmMotor;
	//Solenoid rotateArm;
	//Solenoid hatchPuncher;
	Solenoid armExtender;
	Solenoid hatchGrabber;
	//boolean beltsLocked; //still need to map the button

	private static final int leftBeltMotorMultiplier = 1; // change to -1 to reverse direction for leftBeltMotor
	private static final int rightBeltMotorMultiplier = 1;
	private static final int armRotateMotorMultiplier = 1;

	public enum ArmPosition {
		UP, DOWN
	}

	ArmPosition armposition;

	public ArmManipulator() {
		
		bottomLimit = new DigitalInput(RobotMap.armBottomLimit);
		topLimit = new DigitalInput(RobotMap.armTopLimit);
		ballIn = new DigitalInput(RobotMap.ballInLimit);
		leftBeltMotor = new Talon(RobotMap.leftBeltMotor);
		rightBeltMotor = new Talon(RobotMap.rightBeltMotor);
		//rotateArm = new Solenoid(RobotMap.armRotator);
	//	hatchPuncher = new Solenoid(RobotMap.hatchPuncher);
		armExtender = new Solenoid(RobotMap.armExtender);
		hatchGrabber = new Solenoid(RobotMap.hatchGrabber);
		rotateArmMotor = new Talon(RobotMap.rotateArmMotor);
		//beltsLocked = true;
		rotateArmMotor.setName("rotateArmMotor");
		rightBeltMotor.setName("rightBeltMotor");
		leftBeltMotor.setName("leftBeltMotor");
		armExtender.setName("armExtender");
		SmartDashboard.putData(rightBeltMotor);
		SmartDashboard.putData(leftBeltMotor);
		SmartDashboard.putData(armExtender);
		SmartDashboard.putData(rotateArmMotor);
		//SmartDashboard.putData(rotateArm);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
	}
	
	public void moveBeltsIn() {
		if(!isBallIn()){
			leftBeltMotor.set(Constants.moveBeltSpeed * leftBeltMotorMultiplier);
			rightBeltMotor.set(-(Constants.moveBeltSpeed * rightBeltMotorMultiplier));
		}
		else{
			stopBelts();
		}
	}

	public void moveBeltsOut(){
		 	leftBeltMotor.set(-(Constants.moveBeltSpeed * leftBeltMotorMultiplier));
		 	rightBeltMotor.set(Constants.moveBeltSpeed * rightBeltMotorMultiplier);
	}

	public double getLeftBeltSpeed(){
		return leftBeltMotor.getSpeed();
	}

	public double getRightBeltSpeed(){
		return rightBeltMotor.getSpeed();
	}
	// public void extendHatchPuncher(){
	// 	hatchPuncher.set(true);
	// }

	// public void retractHatchPuncher(){
	// 	hatchPuncher.set(false);
	// }

	public void extendArms(){
		armExtender.set(false); // false extends the arm
	}

	public void retractArms(){
		armExtender.set(true);
	}

	public void grabHatch(){
		hatchGrabber.set(false); //false grabs
	}
	public void releaseHatch(){
		hatchGrabber.set(true);
	}
	
	// public void rotateArms(ArmPosition pos) {
	// 	if (pos == ArmPosition.DOWN) {
	// 		goDown();
	// 	}
	// 	 else if (pos == ArmPosition.UP) {
	// 			goUp();
	// 		} 
	// 		else {
	// 		}
	// }
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
		if(!isDown()){
			rotateArmMotor.set(Constants.rotateArmSpeed*armRotateMotorMultiplier); 
		}
		else{
			rotateArmMotor.set(0);
		}
	}

	private void goUp() {
		if(!isUp()){
			rotateArmMotor.set((-Constants.rotateArmSpeed)*armRotateMotorMultiplier);
		}
		else{
		rotateArmMotor.set(0);
		}
	}
	
	// private void goDown() {
	// 	rotateArm.set(false); 
	// }

	// private void goUp() {
	// 	rotateArm.set(true); // true is moving the arms up
	// }

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

	public boolean isUp() {
		return (topLimit.get() == Constants.pressed);
	}

	public boolean isDown() {
		return (bottomLimit.get() == Constants.pressed);
	}
}
