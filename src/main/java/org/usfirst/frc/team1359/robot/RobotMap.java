/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1359.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// Motors and controllers and solenoids and relays
	public static final int frontleftMotor = 1;
	public static final int rearLeftMotor = 0;
	public static final int frontRightMotor = 2;
	public static final int rearRightMotor = 3;
	
	public static final int leftEncoderA = 10;
	public static final int leftEncoderB = 11;
	public static final int rightEncoderA = 12;
	public static final int rightEncoderB = 13;

	public static final int climbMotor = 4;
	public static final int shooterPull = 5;

	public static final int pivotMotor = 0;
	public static final int rightBeltMotor = 99; // random port 1/12/19
	public static final int leftBeltMotor = 6;
	public static final int rotateArmMotor = 99; //  random port 1/12/19 

	// Limit Switches
	public static final int grabLowerLimit = 9; // cube arm
	public static final int grabUpperLimit = 8; // cube arm
	public static final int shooterDownLimit = 7; // limit
	public static final int strapunwoundlimit = 6; // limit
	public static final int climbTopLimit = 5; // climb arm
	public static final int climbBottomLimit = 4; // climb arm
	
	
	

	// gameController
	public static final int mainController = 0;
	public static final int assistController = 1;

	public static final int xboxA = 1; // liftCubeMiddle
	public static final int xboxB = 2; // releaseCube
	public static final int xboxX = 3; // grabCube
	public static final int xboxY = 4; // liftCubeTop
	public static final int lBumber = 5; // pullBackShooter
	public static final int rBumber = 6; // releaseShooter
	public static final int backBtn = 7; // climberButton
	public static final int startBtn = 8; // lowerCube
	public static final int lStickBtn = 9; // NOT USED
	public static final int rStickBtn = 10; // NOT USED

	// JoyStick1
	public static final int joystick1 = 0;
	public static final int turnbutton = 3;

	// JoyStick2
	public static final int joystick2 = 1;
	public static final int lightbutton = 3;

	// JoyStick3
	public static final int joystick3 = 2;

	public static final int drawShooter = 2;
	public static final int releaseShooter = 3;
	public static final int grabcube = 4;
	public static final int releasecube = 5;
	public static final int extendbutton = 6;
	public static final int retractbutton = 7;
	public static final int lowercube = 8;
	public static final int climberbutton = 9;
	public static final int liftcube90 = 10;
	public static final int liftcube180 = 11;

	// Extra
	public static final int leonardo = 0;

	public static final float cubeloaderpot = 0; // potentiometer

	// Pneumatics
	public static final int compressor = 0;
	public static final int armValve = 0; // solenoid
	public static final int shooterLock = 1; // solenoid
	public static final int rocker = 3; // solenoid
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
}
