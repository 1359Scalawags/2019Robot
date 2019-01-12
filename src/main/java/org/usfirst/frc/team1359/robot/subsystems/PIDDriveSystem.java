package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.Utilities;
import org.usfirst.frc.team1359.robot.commands.drive.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class PIDDriveSystem extends Subsystem {

	// left side
	Talon m_frontLeft = new Talon(RobotMap.frontleftMotor);

	// right side
	Talon m_frontRight = new Talon(RobotMap.frontRightMotor);
	
	boolean reverse = false;
	
	DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);

	ADXRS450_Gyro m_Gyro = new ADXRS450_Gyro();
	Encoder leftEncoder = new Encoder(RobotMap.leftEncoderA, RobotMap.leftEncoderB, true, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(RobotMap.rightEncoderA, RobotMap.rightEncoderB, false, Encoder.EncodingType.k4X);

	PIDControl leftControl = new PIDControl(Constants.drivePID_P, Constants.drivePID_I, Constants.drivePID_D);
	PIDControl rightControl = new PIDControl(Constants.drivePID_P, Constants.drivePID_I, Constants.drivePID_D);
	PIDControl gyroControl = new PIDControl(Constants.gyroPID_P, Constants.gyroPID_I, Constants.gyroPID_D);

	public PIDDriveSystem() {
		leftEncoder.setDistancePerPulse(Constants.feetPerPulse);
		rightEncoder.setDistancePerPulse(Constants.feetPerPulse);
		leftEncoder.setSamplesToAverage(Constants.samplesToAverage);
		rightEncoder.setSamplesToAverage(Constants.samplesToAverage);
	}
	public void reverseDirection() {
		if(reverse) {
			reverse = false;
		}
		else {
			reverse = true;
		}
	}

	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
	
	public double getAngle() {
		return m_Gyro.getAngle();
	}

	public void resetGyro() {
		m_Gyro.reset();
	}

	public double getGyroRate() {
		return m_Gyro.getRate();
	}

	public double getDistanceLeft() {
		return leftEncoder.getDistance();
	}

	public double getRateLeft() {
		return -leftEncoder.getRate();
	}

	public double getDistanceRight() {
		return rightEncoder.getDistance();
	}

	public double getRateRight() {
		return -rightEncoder.getRate();
	}

	public double getAverageDistance() {

		return (getDistanceRight() + getDistanceLeft()) / 2;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoysticks());
	}
	
	public void driveForward(double speed, double targetHeading) {
		final double scale = .01;
		double leftSpeed;
		double rightSpeed;
		double headingError = getAngle() - targetHeading;
		
		leftSpeed =Utilities.Clamp(Math.abs(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
		rightSpeed = Utilities.Clamp(Math.abs(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
		tankDrive(leftSpeed, rightSpeed);		
	}
	public void driveBackward(double speed, double targetHeading) {
		final double scale = .01;
		double leftSpeed;
		double rightSpeed;
		double headingError = getAngle() - targetHeading;
		
		leftSpeed =Utilities.Clamp(-(speed) - headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
		rightSpeed = Utilities.Clamp(-(speed) + headingError * scale, -Constants.maxMotorSpeed, Constants.maxMotorSpeed);
		tankDrive(leftSpeed, rightSpeed);		
	}
	
	
	public void stop() {
		tankDrive(0,0);
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		if(reverse) {
		m_drive.tankDrive(-rightSpeed, -leftSpeed);
		}
		else {
			m_drive.tankDrive(leftSpeed, rightSpeed);
		}

	}

	public double convertToEncoderRate(double motorSpeed) {
		return Constants.fullDriveSpeed * motorSpeed; // feet per second
	}

	public void arcadeDrive(double moveSpeed, double turnSpeed) {
		m_drive.arcadeDrive(moveSpeed, turnSpeed);
	}

	public void arcadeDrive(double moveSpeed, double maxTurnSpeed, double targetAngle) {

		double angleInput = m_Gyro.getAngle();

		gyroControl.SetPoint(targetAngle);

		double angleOutput = Utilities.Clamp(gyroControl.Compute(angleInput), -maxTurnSpeed, maxTurnSpeed);

		m_drive.arcadeDrive(moveSpeed, angleOutput);

	}

}