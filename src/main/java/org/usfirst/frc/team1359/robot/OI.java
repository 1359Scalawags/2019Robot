/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import org.usfirst.frc.team1359.robot.commands.SwitchDriveDirection;
import org.usfirst.frc.team1359.robot.commands.arm.BallIntake;
import org.usfirst.frc.team1359.robot.commands.arm.BallOutake;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendArms;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsDown;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsUp;
import org.usfirst.frc.team1359.robot.commands.arm.RetractArms;
import org.usfirst.frc.team1359.robot.commands.arm.StopBelts;
import org.usfirst.frc.team1359.robot.commands.climber.MoveIntoClimbPosition;
import org.usfirst.frc.team1359.robot.commands.climber.MoveIntoDrivePosition;
import org.usfirst.frc.team1359.robot.commands.climber.UnLockCLimber;
import org.usfirst.frc.team1359.robot.commands.drive.EnableDriveStraight;
import org.usfirst.frc.team1359.robot.commands.Elevator.ChangeHeightModes;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorDown;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorUp;
import org.usfirst.frc.team1359.robot.commands.arm.ExtendAndRetractArms;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	XboxController mainPad = new XboxController(RobotMap.mainController);
	XboxController assistPad = new XboxController(RobotMap.assistController);

	Button changeElevatorMode = new JoystickButton(assistPad, RobotMap.backBtn);
	Button stopBelts = new JoystickButton(assistPad, RobotMap.startBtn);
	Button IntakeBall = new JoystickButton(assistPad, RobotMap.lBumber);
	Button releaseBall = new JoystickButton(assistPad, RobotMap.rBumber);
	Button moveArmsUp = new JoystickButton(assistPad, RobotMap.xboxY);
	Button moveArmsDown = new JoystickButton(assistPad, RobotMap.xboxA);
	//Button extendArms = new JoystickButton(assistPad, RobotMap.xboxB);
	//Button retractArms = new JoystickButton(assistPad, RobotMap.xboxX);
	Button extendAndRetractArms = new JoystickButton(assistPad, RobotMap.xboxB);
	Button raiseElevator = new JoystickButton(assistPad, RobotMap.upArrow);
	Button lowerElevator = new JoystickButton(assistPad, RobotMap.downArrow);
	
	Button enableClimbing = new JoystickButton(mainPad, RobotMap.startBtn);
	Button reverseDriverButton = new JoystickButton(mainPad, RobotMap.startBtn);
	Button drivePosition = new JoystickButton(mainPad, RobotMap.lBumber);
	Button climbPosition = new JoystickButton(mainPad, RobotMap.rBumber);
	Button driveStraightEnable = new JoystickButton(mainPad, RobotMap.xboxX);

	public OI() {
		moveArmsDown.whenPressed(new MoveArmsDown());
		moveArmsUp.whenPressed(new MoveArmsUp());
		//extendArms.whenPressed(new ExtendArms());
		//retractArms.whenPressed(new RetractArms());
		extendAndRetractArms.whenPressed(new ExtendAndRetractArms());
		stopBelts.whenPressed(new StopBelts());
		IntakeBall.whenPressed(new BallIntake());
		enableClimbing.whenPressed(new UnLockCLimber());
		changeElevatorMode.whenPressed(new ChangeHeightModes());
		lowerElevator.whenPressed(new MoveElevatorDown());
		raiseElevator.whenPressed(new MoveElevatorUp());
		releaseBall.whenPressed(new BallOutake());
		drivePosition.whenPressed(new MoveIntoDrivePosition());
		climbPosition.whenPressed(new MoveIntoClimbPosition());
		reverseDriverButton.whenPressed(new SwitchDriveDirection());
		driveStraightEnable.whenPressed(new EnableDriveStraight());
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
	
	public double getLStickY() {
		if (Math.abs(mainPad.getY(Hand.kLeft)) > Constants.controllerDeadZone) {
			return -(mainPad.getY(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		} else {
			return 0;
		}
	}
	//2019
	public double getLStickX(){
		if(Math.abs(mainPad.getX(Hand.kLeft)) > Constants.controllerDeadZone){
			return -(mainPad.getX(Hand.kLeft) * (.3 * getMainTriggers() + .7));
		}
		else{
			return 0;
		}
	}
	//2019
	public double getSliderStick(){ // move elevator slider
		return assistPad.getX(Hand.kLeft);
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

	public boolean getRaiseElevator(){
		return raiseElevator.get();
	}
}
