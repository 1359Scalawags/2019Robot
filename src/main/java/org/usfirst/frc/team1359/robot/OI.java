/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

import org.usfirst.frc.team1359.robot.commands.SwitchDriveDirection;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.CubeRelease;
import org.usfirst.frc.team1359.robot.commands.climber.RockClimberArmBackward;
import org.usfirst.frc.team1359.robot.commands.climber.RockClimberArmForward;
import org.usfirst.frc.team1359.robot.commands.climber.UnLockCLimber;
import org.usfirst.frc.team1359.robot.commands.drive.EnableDriveStraight;
import org.usfirst.frc.team1359.robot.commands.shooter.PrepareToLaunchShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.PullShooter;
import org.usfirst.frc.team1359.robot.commands.shooter.ReleaseShooter;

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

	Button grabCubeButton = new JoystickButton(assistPad, RobotMap.xboxX);
	Button releaseCubeButton = new JoystickButton(assistPad, RobotMap.xboxB);
	Button drawShooter = new JoystickButton(assistPad, RobotMap.lBumber);
	Button releaseShooter = new JoystickButton(assistPad, RobotMap.rBumber);
	Button enableClimberButton = new JoystickButton(assistPad, RobotMap.backBtn);
	Button rockForwardButton = new JoystickButton(assistPad, RobotMap.xboxY);
	Button rockBackwardButton = new JoystickButton(assistPad, RobotMap.xboxA);
	
	Button reverseDriverButton = new JoystickButton(mainPad, RobotMap.startBtn);
	Button driveStraightEnable = new JoystickButton(mainPad, RobotMap.xboxX);
	Button extendClimberButton = new JoystickButton(mainPad, RobotMap.xboxY);
	Button retractClimberButton = new JoystickButton(mainPad, RobotMap.xboxA);

	public OI() {
		drawShooter.whenPressed(new PrepareToLaunchShooter());
		grabCubeButton.whenPressed(new CubeGrab());
		releaseCubeButton.whenPressed(new CubeRelease());
		drawShooter.whenPressed(new PullShooter());
		releaseShooter.whenPressed(new ReleaseShooter());
		enableClimberButton.whenPressed(new UnLockCLimber());
		rockForwardButton.whenPressed(new RockClimberArmForward());
		rockBackwardButton.whenPressed(new RockClimberArmBackward());
		reverseDriverButton.whenPressed(new SwitchDriveDirection());
		
		driveStraightEnable.whenPressed(new EnableDriveStraight());
	}

	public double getMainTriggers() {
		return Math.max(mainPad.getTriggerAxis(Hand.kLeft), mainPad.getTriggerAxis(Hand.kRight));
	}
	public double getAssistTriggerLeft() { // move climber Strap
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
}
