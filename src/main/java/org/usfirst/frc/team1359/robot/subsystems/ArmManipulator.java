package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorSlider;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

public class ArmManipulator extends Subsystem {

	DigitalInput bottomLimit, topLimit;
	DigitalInput ballIn;
	//Talon leftBeltMotor , rightBeltMotor , rotateArmMotor;
	Talon rotateArmMotor;
	float averagePercentageFromCenter;
	//Solenoid rotateArm;
	//Solenoid hatchPuncher;
	Solenoid armExtender;
	Solenoid hatchGrabber;
	private double sliderTime;
	private double sliderSpeed;
	DigitalInput slideLeftLimit;
	DigitalInput slideRightLimit;
	Talon liftMotor, slideMotor;
	private Timer timer;
	private boolean isAtTarget;

	//boolean beltsLocked; //still need to map the button

	private static final int leftBeltMotorMultiplier = 1; // change to -1 to reverse direction for leftBeltMotor
	private static final int rightBeltMotorMultiplier = 1;
	private static final int armRotateMotorMultiplier = 1;
	private static final int slideMotorMultiplier = 1; // change to -1 to reverse Slide Motor

	public enum ArmPosition {
		UP, DOWN
	}

	ArmPosition armposition;

	public ArmManipulator() {
		
		bottomLimit = new DigitalInput(RobotMap.armBottomLimit);
		topLimit = new DigitalInput(RobotMap.armTopLimit);
		ballIn = new DigitalInput(RobotMap.ballInLimit);
		//leftBeltMotor = new Talon(RobotMap.leftBeltMotor);
		//rightBeltMotor = new Talon(RobotMap.rightBeltMotor);
		armExtender = new Solenoid(RobotMap.armExtender);
		hatchGrabber = new Solenoid(RobotMap.hatchGrabber);
		rotateArmMotor = new Talon(RobotMap.rotateArmMotor);
		sliderSpeed = Constants.slideMotorSpeed * slideMotorMultiplier;
		slideLeftLimit =  new DigitalInput(RobotMap.elevatorSlideLeftLimit);
		slideRightLimit =  new DigitalInput(RobotMap.elevatorSlideRightLimit);
		slideMotor = new Talon(RobotMap.elevatorSlideMotor);
		isAtTarget = false;
		timer = new Timer();

		rotateArmMotor.setName("rotateArmMotor");
	//	rightBeltMotor.setName("rightBeltMotor");
	//	leftBeltMotor.setName("leftBeltMotor");
		armExtender.setName("armExtender");
		slideMotor.setName("elevatorSlideMotor");
	//	SmartDashboard.putData(rightBeltMotor);
	//	SmartDashboard.putData(leftBeltMotor);
		SmartDashboard.putData(armExtender);
		SmartDashboard.putData(rotateArmMotor);
		SmartDashboard.putData(slideMotor);
		//SmartDashboard.putData(rotateArm);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new MoveElevatorSlider());
		// Set the default command for a subsystem here.
	}

	public void initializeMoveSlider(){
		averagePercentageFromCenter = (Robot.kNetwork.xPercentage + Robot.kNetwork.xPercentage) / 2;
		//moveSliderJoystick(.5);
		sliderTime = (Constants.sliderTimeToFarRight/*/100*//Constants.percentageFromCameraToFarRight)*averagePercentageFromCenter;
		timer.reset();
	}
	
	public void moveSlider() {
	//	moveSliderJoystick(.5);	
		if(averagePercentageFromCenter == -1){
			stopElevatorSlideMotor();
			isAtTarget = true;
		}
		else{
			if(averagePercentageFromCenter > Constants.percentageFromCameraToFarRight){
				stopElevatorSlideMotor();
				isAtTarget = true;
			}
			else{
				timer.start();
				if((double)timer.get() < sliderTime){
					slideMotor.set(sliderSpeed);
				}
				else{
					stopElevatorSlideMotor();
					isAtTarget = true;
				}
			}
		}
	}
		public boolean isAtCenterTarget(){
			return isAtTarget;
		}

	public void moveSliderJoystick(double speed){
		speed = speed * slideMotorMultiplier;
		if(speed < 0 && !isRightMax()){
			slideMotor.set(speed);
		}
		else if(speed > 0 && !isLeftMax()){
			slideMotor.set(speed);
		}
		else{
			stopElevatorSlideMotor();
		}
	}
	public boolean isLeftMax(){
		return slideLeftLimit.get() == Constants.pressed;
	}

	public boolean isRightMax(){
		return slideRightLimit.get() == Constants.pressed;
	}
	public void stopElevatorSlideMotor(){
	slideMotor.set(0);
	}
	public double getSlideMotorSpeed(){
			return slideMotor.getSpeed();
		}
	
	// public void moveBeltsIn() {
	// 	if(!isBallIn()){
	// 		leftBeltMotor.set(Constants.moveBeltSpeed * leftBeltMotorMultiplier);
	// 		rightBeltMotor.set(-(Constants.moveBeltSpeed * rightBeltMotorMultiplier));
	// 	}
	// 	else{
	// 		stopBelts(true);
	// 	}
	// }

	// public void moveBeltsOut(){
	// 	 	leftBeltMotor.set(-(Constants.moveBeltSpeed * leftBeltMotorMultiplier));
	// 	 	rightBeltMotor.set(Constants.moveBeltSpeed * rightBeltMotorMultiplier);
	// }

	// public double getLeftBeltSpeed(){
	// 	return leftBeltMotor.getSpeed();
	// }

	// public double getRightBeltSpeed(){
	// 	return rightBeltMotor.getSpeed();
	// }
	// public void extendHatchPuncher(){
	// 	hatchPuncher.set(true);
	// }

	// public void retractHatchPuncher(){
	// 	hatchPuncher.set(false);
	// }

	public void extendArms(){
		armExtender.set(true); // false extends the arm
	}

	public void retractArms(){
		armExtender.set(false);
	}

	public void grabHatch(){
		hatchGrabber.set(true);
	}
	public void releaseHatch(){
		hatchGrabber.set(false);
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
		
	public void goDown() {
		if(!isDown()){ 
			rotateArmMotor.set(Constants.rotateArmSpeed*armRotateMotorMultiplier); 
		}
		else{
			rotateArmMotor.set(0);
		}
	}

	public void stopRotateMotor(){
		rotateArmMotor.set(0);
	}

	public void goUp() {
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

	// public void stopBelts(boolean frictionIn) {
	// 	if(frictionIn){
	// 		leftBeltMotor.set(.15);
	// 		rightBeltMotor.set(-.15);
	// 	}
	// 	else{
	// 		leftBeltMotor.set(0);
	// 		rightBeltMotor.set(0);
	// 	}
	// }

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
