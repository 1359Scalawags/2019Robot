//package org.usfirst.frc.team1359.deprecated;
//
//import org.usfirst.frc.team1359.robot.Robot;
//
//import edu.wpi.first.wpilibj.AnalogInput;
//import edu.wpi.first.wpilibj.AnalogPotentiometer;
//import edu.wpi.first.wpilibj.PIDController;
//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.Talon;
//import edu.wpi.first.wpilibj.command.PIDSubsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//
///**
// *
// */
//public class PIDSampleSystem extends PIDSubsystem {
//
//	SpeedController motor = new Talon(1);
//	AnalogPotentiometer pot = new AnalogPotentiometer(2);
//	
//	
//    // Initialize your subsystem here
//    public PIDSampleSystem() {
//    	super("PIDSampleSystem", 1.0, 0.01, 0.0);
//    	setAbsoluteTolerance(0.05);
//    	PIDController p = getPIDController();
//    	p.setContinuous(false);
//    	p.setInputRange(0.1, 0.5);
//    	p.setOutputRange(-.5, .5);
//    	SmartDashboard.putData("PID System", p);
//    	setSetpoint(.3);
//        // Use these to get going:
//        // setSetpoint() -  Sets where the PID controller should move the system
//        //                  to
//        // enable() - Enables the PID controller.
//    }
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//
//    //protected double returnPIDInput() {
//        // Return your input value for the PID loop
//        // e.g. a sensor, like a potentiometer:
//        // yourPot.getAverageVoltage() / kYourMaxVoltage;
////    	//if(Robot.kOI.getJoystick1().getX() > 0) {
////    		return pot.get();
////    	}
////    	else {
////    		return 0.0;
////    	}
////    }
//
//    protected void usePIDOutput(double output) {
//        // Use output to drive your system, like a motor
//        // e.g. yourMotor.set(output);
//    	motor.set(output);
//    }
//}
