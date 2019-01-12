package org.usfirst.frc.team1359.robot.subsystems;

import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.RobotMap;
import org.usfirst.frc.team1359.robot.commands.arm.CubeMove;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

/**
 *
 */
public class CubeLoader extends Subsystem {

	DigitalInput bottomLimit, topLimit;
	Talon liftMotor;
	Solenoid armValve;

	Potentiometer pot = new AnalogPotentiometer(0, 180, 0);

	public enum ArmPosition {
		TOP, MIDDLE, BOTTOM
	}

	ArmPosition armposition;

	public CubeLoader() {
		
		bottomLimit = new DigitalInput(RobotMap.grabLowerLimit);
		topLimit = new DigitalInput(RobotMap.grabUpperLimit);
		liftMotor = new Talon(RobotMap.liftMotor);
		armValve = new Solenoid(RobotMap.armValve);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new CubeMove());
	}
	
	public void grab() {
		armValve.set(true);

	}

	public void release() {
		armValve.set(false);
	}

	public void move(double speed) {
		if (speed > 0 && !isAtBottom()) {
			liftMotor.set(speed * 0.5);
		}
		else if(speed < 0 && !isAtTop()) {
			liftMotor.set(speed * 0.7);
		}
		else {
			liftMotor.set(0);
		}
	}
	
	public void moveAuto(ArmPosition pos) {
		if (pos == ArmPosition.BOTTOM) {
			goToBottom();
		}
		 else if (pos == ArmPosition.TOP) {
				goToTop();
			} else {
				stop();
			}
	}
	
	private void goToBottom() {
		if (!isAtBottom()) {
			lower();
		} else {
			stop();
			armposition = ArmPosition.BOTTOM;
		}
	}

	private void goToTop() {
		if (!isAtTop()) {
			lift();
		} else {
			armposition = ArmPosition.TOP;
			stop();
		}
	}

	private void lift() {
		liftMotor.set(Constants.cubeArmSpeed);
	}

	private void lower() {
		liftMotor.set(-(Constants.cubeArmSpeed));
	}

	public void stop() {

		liftMotor.set(0);
	}

	public boolean isAtTop() {
		return (topLimit.get() == Constants.pressed);
	}

	public boolean isAtBottom() {
		return (bottomLimit.get() == Constants.pressed);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public boolean isGrabbed() {
		return armValve.get();
	}

}
