package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class ElevatorManipulator extends Subsystem {

	Talon liftMotor , slideMotor;
	DigitalInput bottomLimit;
	DigitalInput topLimit;
	DigitalInput slideLeftLimit;
	DigitalInput slideRightLimit;
	boolean isHeight;
	Potentiometer elevatorHeight;

	private static final int slideMotorMultiplier = 1; // change to -1 to reverse Slide Motor
	private static final int liftMotorMulitplier  = 1;

	public enum elevatorHatchHeight{
		BOTTOM,	BASE, MIDDLE, TOP
	}

	public enum elevatorCargoHeight{
		LOWER, MIDDLE, TOP
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public ElevatorManipulator(){
		elevatorHeight = new AnalogPotentiometer(Constants.potentiometerChannel, Constants.potentiometerFullRange, Constants.potentiometerOffset);
		slideMotor = new Talon(RobotMap.elevatorSlideMotor);
		liftMotor = new Talon(RobotMap.elevatorLiftMotor);
		bottomLimit = new DigitalInput(RobotMap.elevatorBottomLimit);
		topLimit = new DigitalInput(RobotMap.elevatorToplimit);
		slideLeftLimit =  new DigitalInput(RobotMap.elevatorSlideLeftLimit);
		slideRightLimit =  new DigitalInput(RobotMap.elevatorSlideRightLimit);
		isHeight = false;
	}

	public void initDefaultCommand() {
	
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public double getElevatorHeight(){
		return elevatorHeight.get() ; // * Constants.valuePerAngle;
	}

	public void moveSlider(double speed) {
		speed = speed * slideMotorMultiplier;
		if(speed > 0 && !isRightMax()){
			slideMotor.set(speed);
		}
		else if(speed < 0 && !isLeftMax()){
			slideMotor.set(speed);
		}
		else{
			stopElevatorSlideMotor();
		}
	}

	public void moveElevatorHatch(elevatorHatchHeight pos){
		if(pos == elevatorHatchHeight.BASE){
			moveElevatorToHeight(Constants.hatchBaseHeight);
		}
		else if(pos == elevatorHatchHeight.MIDDLE){
			moveElevatorToHeight(Constants.hatchMiddleHeight);
		}
		else if(pos == elevatorHatchHeight.TOP){
			moveElevatorToHeight(Constants.hatchTopHeight);
		}
		else if(pos == elevatorHatchHeight.BOTTOM){
			moveElevatorToHeight(Constants.hatchBottomHeight);
		}
		else{
			stopElevatorLiftMotor();
		}
	}

	public void checkButtonForPress(){
		
	}

	public void moveElevatorCargo(elevatorCargoHeight pos){
		if(pos == elevatorCargoHeight.LOWER){
			moveElevatorToHeight(Constants.cargoLowerHeight);
		}
		else if(pos == elevatorCargoHeight.MIDDLE){
			moveElevatorToHeight(Constants.cargoMiddleHeight);
		}
		else if(pos == elevatorCargoHeight.TOP){
			moveElevatorToHeight(Constants.cargoTopHeight);
		}
		else{
			stopElevatorLiftMotor();
		}
	}

	public void moveElevatorToHeight(double height){
		double distanceFromTarget = height - getElevatorHeight();
		if(Math.abs(distanceFromTarget) > Constants.withinHeight && !isUpMax() && !isDownMax()){
			if(getElevatorHeight() > height){
				lowerElevator();
			}
			else{
				raiseElevator();
			}
		}
		else{
			isHeight = true;
			stopElevatorLiftMotor();
		}
	}

	public boolean isToHeight(){
		return isHeight;
	}

	public void lowerElevator(){
		liftMotor.set(-(Constants.elevatorLiftSpeed) * liftMotorMulitplier);
	}

	public void raiseElevator(){
		liftMotor.set(Constants.elevatorLiftSpeed * liftMotorMulitplier);
	}

	public boolean isDownMax() {
		return bottomLimit.get() == Constants.pressed;
	}
	
	public boolean isUpMax(){
		return topLimit.get() == Constants.pressed;
	}

	public boolean isLeftMax(){
		return slideLeftLimit.get() == Constants.pressed;
	}

	public boolean isRightMax(){
		return slideRightLimit.get() == Constants.pressed;
	}

	public void stopElevatorLiftMotor() {
		liftMotor.set(0);
	}

	public void stopElevatorSlideMotor(){
		slideMotor.set(0);
	}

}
