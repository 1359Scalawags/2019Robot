/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import org.usfirst.frc.team1359.robot.commands.SwitchDriveDirection;
import org.usfirst.frc.team1359.robot.commands.Elevator.PutHatchToHeight;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendArms;
import org.usfirst.frc.team1359.robot.commands.arm.GetHatchFromPortal;
import org.usfirst.frc.team1359.robot.commands.arm.GrabHatch;
import org.usfirst.frc.team1359.robot.commands.arm.ReleaseHatch;
import org.usfirst.frc.team1359.robot.commands.arm.RetractArms;
import org.usfirst.frc.team1359.robot.commands.climber.ExtendClimberBack;
import org.usfirst.frc.team1359.robot.commands.climber.MoveIntoClimbPosition;
import org.usfirst.frc.team1359.robot.commands.climber.MoveIntoDrivePosition;
import org.usfirst.frc.team1359.robot.commands.climber.RetractClimberBack;
import org.usfirst.frc.team1359.robot.commands.climber.UnLockCLimber;
import org.usfirst.frc.team1359.robot.commands.drive.EnableDriveStraight;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	XboxController mainPad = new XboxController(RobotMap.mainController);
	XboxController assistPad = new XboxController(RobotMap.assistController);

//	/*new things
	Button putHatchBottom = new JoystickButton(assistPad, RobotMap.xboxA);
	Button puthatchMiddle = new JoystickButton(assistPad, RobotMap.xboxB);
	Button putHatchTop = new JoystickButton(assistPad, RobotMap.xboxY);
	Button putHatchCargoShip = new JoystickButton(assistPad, RobotMap.xboxX);
	//*/

	//Button changeElevatorMode = new JoystickButton(assistPad, RobotMap.backBtn);
//	Button releaseHatch = new JoystickButton(assistPad, RobotMap.xboxA);
//	Button retractArms = new JoystickButton(assistPad, RobotMap.rBumber);
//	Button grabHatch = new JoystickButton(assistPad, RobotMap.xboxY);
	//Button stopBelts = new JoystickButton(assistPad, RobotMap.startBtn);
	//Button IntakeBall = new JoystickButton(assistPad, RobotMap.lBumber);
	//Button releaseBall = new JoystickButton(assistPad, RobotMap.rBumber);
	// Button moveArmsUp = new JoystickButton(assistPad, RobotMap.xboxY);
	// Button moveArmsDown = new JoystickButton(assistPad, RobotMap.xboxA);
	//Button moveArmsUpAndDown = new JoystickButton(assistPad, RobotMap.xboxY);
	Button getHatchFromPortal = new JoystickButton(assistPad, RobotMap.backBtn);
//	Button extendAndRetractArms = new JoystickButton(assistPad, RobotMap.xboxB);
	//Button moveElevatorSlider = new JoystickButton(assistPad, RobotMap.xboxX);
//	Button extendArms = new JoystickButton(assistPad, RobotMap.lBumber);
	Button extendClimberBack = new JoystickButton(mainPad, RobotMap.xboxA);
	Button retractClimberBack = new JoystickButton(mainPad, RobotMap.xboxY);
	Button enableClimbing = new JoystickButton(mainPad, RobotMap.backBtn);
	Button reverseDriverButton = new JoystickButton(mainPad, RobotMap.startBtn);
	Button drivePosition = new JoystickButton(mainPad, RobotMap.lBumber);
	Button climbPosition = new JoystickButton(mainPad, RobotMap.rBumber);
	Button driveStraightEnable = new JoystickButton(mainPad, RobotMap.xboxX);
	POVButton upArrow = new POVButton(assistPad, 0);
	POVButton rightArrow = new POVButton(assistPad, 90);
	POVButton downArrow = new POVButton(assistPad, 180);
	POVButton leftArrow = new POVButton(assistPad, 270);


	public OI() {

	//	/*new things.
		putHatchBottom.whenPressed(new PutHatchToHeight(Constants.hatchBaseHeight));
		puthatchMiddle.whenPressed(new PutHatchToHeight(Constants.hatchMiddleHeight));
		putHatchTop.whenPressed(new PutHatchToHeight(Constants.hatchTopHeight));
		putHatchCargoShip.whenPressed(new PutHatchToHeight(Constants.liftToHatchPortal));
		//*/

		rightArrow.whenPressed(new RetractArms());
		leftArrow.whenPressed(new ExtendArms());
		upArrow.whenPressed(new GrabHatch());
		downArrow.whenPressed(new ReleaseHatch());

	//	moveArmsDown.whenPressed(new MoveArmsDown());
	//	moveArmsUp.whenPressed(new MoveArmsUp());
		getHatchFromPortal.whenPressed(new GetHatchFromPortal());
	//	moveArmsUpAndDown.whenPressed(new MoveArmsUpAndDown());
	//	moveElevatorSlider.whenPressed(new MoveElevatorSlider());
	//	extendAndRetractArms.whenPressed(new ExtendAndRetractArms());
	//	extendArms.whenPressed(new ExtendArms());
	//	retractArms.whenPressed(new RetractArms());
	//	stopBelts.whenPressed(new StopBelts());
	//	IntakeBall.whenPressed(new BallIntake());
		enableClimbing.whenPressed(new UnLockCLimber());
		//changeElevatorMode.whenPressed(new ChangeHeightModes());
	//	releaseHatch.whenPressed(new ReleaseHatch());
	//	grabHatch.whenPressed(new GrabHatch());
	//	releaseBall.whenPressed(new BallOutake());
		drivePosition.whenPressed(new MoveIntoDrivePosition());
		climbPosition.whenPressed(new MoveIntoClimbPosition());
		reverseDriverButton.whenPressed(new SwitchDriveDirection());
		driveStraightEnable.whenPressed(new EnableDriveStraight());
		extendClimberBack.whenPressed(new ExtendClimberBack());
		retractClimberBack.whenPressed(new RetractClimberBack());
	}


	public double getMainTriggers() {
		return Math.max(mainPad.getTriggerAxis(Hand.kLeft), mainPad.getTriggerAxis(Hand.kRight));
	}
	public double getAssistTriggerLeft() { 
		return assistPad.getTriggerAxis(Hand.kLeft);
	}
	public double getAssistTriggerRight() {
		return  assistPad.getTriggerAxis(Hand.kRight);
	}
	
	// public double getArrowPadX(){
	// 	return assistPad.getRawAxis(5);
	// }
	private double getDPadY(){
		return assistPad.getRawAxis(RobotMap.yPadAxis);
	}
	public enum DPadState {
		UP, DOWN, NONE
	}
	public OI.DPadState getDpadYValue(){
		double y = getDPadY();
		if(y >= .5){
			return(DPadState.UP);
		}
		else if(y <= -.5){
			return(DPadState.DOWN);
		}
		else{
			return(DPadState.NONE);
		}
	}
	
	//2019 drive
	public double getLStickX(){
		if(Math.abs(mainPad.getX(Hand.kLeft)) > Constants.controllerDeadZone){
			return -(mainPad.getX(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		}
		else{
			return 0;
		}
	}

	public double getLStickY() {
		if (Math.abs(mainPad.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
			return -(mainPad.getY(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}
	public double getSliderStick(){ // move elevator slider
		return assistPad.getX(Hand.kLeft);
	}

	public double getLiftStick(){
		return assistPad.getY(Hand.kRight);
	}

	public double getRStickY() {
		if (Math.abs(mainPad.getY(Hand.kRight)) > Constants.controllerDeadZone) {
			return -(mainPad.getY(Hand.kRight) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}

	public double getArmStick() { // move climber Arm
		return assistPad.getY(Hand.kLeft);
	}
	
	public double getGrabberStick() {
		return assistPad.getY(Hand.kRight);
	}
	
	public boolean getDriveStraightButton() {
		return driveStraightEnable.get();
	}

	// public boolean getRaiseElevator(){
	// 	return raiseElevator.get();
	// }

	// public enum DpadState {
	// 	none,
	// 	up,
	// 	down,
	// 	handled
	// }
	// DpadState state = DpadState.none;
	// public void getDpadPressed() {
	// 	if(state == none && getDpadUp()) {
	// 		state = DpadState.up;
	// 	} else if(state == none && getDpadDown()) {
	// 		state = DpadState.down;
	// 	}
	// }
}
