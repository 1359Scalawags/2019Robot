package org.usfirst.frc.team1359.deprecated;

import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.drive.DriveWithJoysticks;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveSystem extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Talon m_frontLeft = new Talon(RobotMap.frontleftMotor);
	//Talon m_rearLeft = new Talon(RobotMap.rearLeftMotor);
	//SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

	Talon m_frontRight = new Talon(RobotMap.frontRightMotor);
	//Talon m_rearRight = new Talon(RobotMap.rearRightMotor);
	//SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	//DifferentialDrive m_drive = new DifferentialDrive(m_left, m_right);
	DifferentialDrive m_drive = new DifferentialDrive(m_frontLeft, m_frontRight);
	
	ADXRS450_Gyro m_Gyro = new ADXRS450_Gyro();
	Encoder leftEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);

	public ADXRS450_Gyro getGyro() {
		return m_Gyro;
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

	public double getLeftEncoder() {
		return leftEncoder.getDistance();
	}

	public double getRightEncoder() {
		return rightEncoder.getDistance();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new DriveWithJoysticks());
	}

	public void tankDrive(double leftSpeed, double rightSpeed) {
		m_drive.tankDrive(leftSpeed, rightSpeed);
	}

	public void arcadeDrive(double moveSpeed, double turnSpeed) {
		m_drive.arcadeDrive(moveSpeed, turnSpeed);
	}
	
	public float getAverageDistance() {
		return 0;
	}

	public void arcadeDrive(int one, int two, double three) {
		
	}
}