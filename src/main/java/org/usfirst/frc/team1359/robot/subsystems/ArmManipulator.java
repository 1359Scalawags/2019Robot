package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.arm.CubeMove;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmManipulator extends Subsystem {

	DigitalInput bottomLimit, topLimit;
	Talon leftBeltMotor , rightBeltMotor , rotateArmMotor;

	private static final int rotateMotorMulitiplier = 1; // change to -1 to reverse direction for rotateMotor
	private static final int leftBeltMotorMultiplier = 1; // change to -1 to reverse direction for leftBeltMotor
	private static final int rightBeltMotorMultiplier = 1; // change to -1 to reverse direction for rightBeltMotor

	public enum ArmPosition {
		UP, DOWN
	}

	ArmPosition armposition;

	public ArmManipulator() {
		
		bottomLimit = new DigitalInput(RobotMap.grabLowerLimit);
		topLimit = new DigitalInput(RobotMap.grabUpperLimit);
		leftBeltMotor = new Talon(RobotMap.leftBeltMotor);
		rightBeltMotor = new Talon(RobotMap.rightBeltMotor);
		rotateArmMotor = new Talon(RobotMap.rotateArmMotor);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		//setDefaultCommand(new CubeMove());
	}
	
	public void moveBelts() {
			leftBeltMotor.set(Constants.moveBeltSpeed * leftBeltMotorMultiplier);
			rightBeltMotor.set(-(Constants.moveBeltSpeed * rightBeltMotorMultiplier));
	}
	
	public void rotateArms(ArmPosition pos) {
		if (pos == ArmPosition.DOWN) {
			goDown();
		}
		 else if (pos == ArmPosition.UP) {
				goUp();
			} 
			else {
				stopArmRotation();
			}
	}
	
	private void goDown() {
		if (!isDown()) {
			lower();
		} else {
			stopArmRotation();
			armposition = ArmPosition.DOWN;
		}
	}

	private void goUp() {
		if (!isUp()) {
			lift();
		} else {
			armposition = ArmPosition.UP;
			stopArmRotation();
		}
	}

	private void lift() {
		rotateArmMotor.set(Constants.rotateArmSpeed * rotateMotorMulitiplier);
	}

	private void lower() {
		rotateArmMotor.set(-(Constants.rotateArmSpeed * rotateMotorMulitiplier));
	}

	public void stopArmRotation(){
		rotateArmMotor.set(0);
	}

	public void stopBelts() {
		leftBeltMotor.set(0);
		rightBeltMotor.set(0);
	}

	public boolean isUp() {
		return (topLimit.get() == Constants.pressed);
	}

	public boolean isDown() {
		return (bottomLimit.get() == Constants.pressed);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
}
