package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CubeShooter extends Subsystem {

	Talon shooterPull;
	Solenoid lockValve;
	DigitalInput shooterDownLimit;
	DigitalInput strapUnwoundLimit;
	boolean readyToFire;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public CubeShooter(){
		shooterPull = new Talon(RobotMap.shooterPull);
		lockValve = new Solenoid(RobotMap.shooterLock);
		shooterDownLimit = new DigitalInput(RobotMap.shooterDownLimit);
		strapUnwoundLimit = new DigitalInput(RobotMap.strapunwoundlimit);
		readyToFire = true;
	}

	public void initDefaultCommand() {
	
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void lockShooter() {
		lockValve.set(Constants.locked);
	}

	public void unlockShooter() {
			lockValve.set(Constants.unLocked);
	}

	public boolean isLocked() {
		return (lockValve.get() == Constants.locked);
	}

	public void pullShooter() {
		if (!isDown()) {
			shooterPull.set(Constants.shooterPullSpeed);
		} else {
			stopShooterMotor();
		}
	}

	public void unwindShooter() {
			shooterPull.set(-(Constants.shooterPullSpeed));
	}

	public boolean isReadyToFire() {
		return readyToFire;
	}  
	
	public void setReadyToFire(boolean isReady) {
		readyToFire = isReady;
	}

	public boolean isDown() {
		return shooterDownLimit.get() == Constants.pressed;
	}
	
	public boolean shooterIsUnwound() {
		return (strapUnwoundLimit.get() == Constants.pressed);

	}

	public void stopShooterMotor() {
		shooterPull.set(0);

	}

}
