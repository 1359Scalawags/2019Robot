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
	int index;
	//int cargoIndex;
	boolean heightMode;
	boolean isInitialized;
	boolean setCargoHeight;

	private static final int slideMotorMultiplier = 1; // change to -1 to reverse Slide Motor
	private static final int liftMotorMulitplier  = 1;

	public enum ElevatorHatchHeight{
		BOTTOM,	BASE, MIDDLE, TOP
	}

	public enum ElevatorCargoHeight{
		LOWER, MIDDLE, TOP
	}

	float cargoHeights[] = {Constants.cargoLowerHeight, Constants.cargoLowerHeight, Constants.cargoMiddleHeight, Constants.cargoTopHeight};

	float hatchHeights[] = {Constants.hatchBottomHeight, Constants.hatchBaseHeight, Constants.hatchMiddleHeight, Constants.hatchTopHeight};

	public ElevatorManipulator(){
		elevatorHeight = new AnalogPotentiometer(Constants.potentiometerChannel, Constants.potentiometerFullRange, Constants.potentiometerOffset);
		slideMotor = new Talon(RobotMap.elevatorSlideMotor);
		liftMotor = new Talon(RobotMap.elevatorLiftMotor);
		bottomLimit = new DigitalInput(RobotMap.elevatorBottomLimit);
		topLimit = new DigitalInput(RobotMap.elevatorToplimit);
		slideLeftLimit =  new DigitalInput(RobotMap.elevatorSlideLeftLimit);
		slideRightLimit =  new DigitalInput(RobotMap.elevatorSlideRightLimit);
		isHeight = false;
		index = -1;
		//cargoIndex = -1;
		isInitialized = false;
		setCargoHeight = false;
		heightMode = false; // false = Hatch, true = cargo
	}

	public void initDefaultCommand() {
	
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void switchmodes(){
		heightMode = !heightMode;
	}

	public void setIndex(){
		index = 1;
	}

	// public void setIndexToHeight(){
	// 	if(Math.abs(Constants.hatchBaseHeight - getElevatorHeight()) < Constants.withinHeight){
	// 		index = 1;
	// 	}
	// 	else if(Math.abs(Constants.hatchBottomHeight - getElevatorHeight()) < Constants.withinHeight){
	// 		index = 0;
	// 	}
	// 	else if(Math.abs(Constants.hatchMiddleHeight - getElevatorHeight()) < Constants.withinHeight){
	// 		index = 2;
	// 	}
	// 	else if(Math.abs(Constants.hatchTopHeight - getElevatorHeight()) < Constants.withinHeight){
	// 		index = 3;
	// 	}
	// 	else{
	// 		moveElevatorToHeight(Constants.hatchBaseHeight);
	// 	}
	// }

	// public void setCargoIndexToHeight(){
	// 	if(!setCargoHeight){
	// 	setCargoHeight = true;
	// 	}
	// 	else{
	// 	}
	// }

	// public boolean isCargoSetToHeight(){
	// 	return setCargoHeight;
	// }

	public float getCurrentHatchHeight(){
		return hatchHeights[index];
	}

	public float getCurrentCargoHeight(){
		return cargoHeights[index];
	}

	public void incrementHeight(){
		index ++;
		if(index > 3){
			index = 3;
		}
	}

	public void abateHeight(){
		index --;
		if(index < 0){
			index = 0;
		}
	}

	// public void incrementCargoHeight(){
	// 	cargoIndex ++;
	// 	if(cargoIndex > 2){
	// 		cargoIndex = 2;
	// 	}
	// }

	// public void abatecargoHeight(){
	// 	cargoIndex --;
	// 	if(cargoIndex < 0){
	// 		cargoIndex = 0;
	// 	}
	// }

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

	// public void moveElevatorHatch(elevatorHatchHeight pos){
	// 	if(pos == elevatorHatchHeight.BASE){
	// 		moveElevatorToHeight(Constants.hatchBaseHeight);
	// 	}
	// 	else if(pos == elevatorHatchHeight.MIDDLE){
	// 		moveElevatorToHeight(Constants.hatchMiddleHeight);
	// 	}
	// 	else if(pos == elevatorHatchHeight.TOP){
	// 		moveElevatorToHeight(Constants.hatchTopHeight);
	// 	}
	// 	else if(pos == elevatorHatchHeight.BOTTOM){
	// 		moveElevatorToHeight(Constants.hatchBottomHeight);
	// 	}
	// 	else{
	// 		stopElevatorLiftMotor();
	// 	}
	// }

	public void moveElevator(){
		if(heightMode == false){
			moveElevatorHatch();
		}
		else{
			moveElevatorCargo();
		}
	}

	private void moveElevatorHatch(){
		if(index == 1){
			moveElevatorToHeight(Constants.hatchBaseHeight);
		}
		else if(index == 2){
			moveElevatorToHeight(Constants.hatchMiddleHeight);
		}
		else if(index == 3){
			moveElevatorToHeight(Constants.hatchTopHeight);
		}
		else if(index == 0){
			moveElevatorToHeight(Constants.hatchBottomHeight);
		}
		else{
			stopElevatorLiftMotor();
		}
	}

	private void moveElevatorCargo(){
		if(index == 0){
			moveElevatorToHeight(Constants.cargoLowerHeight);
		}
		else if(index == 1){
			moveElevatorToHeight(Constants.cargoLowerHeight);
		}
		else if(index == 2){
			moveElevatorToHeight(Constants.cargoMiddleHeight);
		}
		else if(index == 3){
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

	private void lowerElevator(){
		liftMotor.set(-(Constants.elevatorLiftSpeed) * liftMotorMulitplier);
	}

	private void raiseElevator(){
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
