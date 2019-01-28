
package org.usfirst.frc.team1359.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.cameraserver.CameraServer; // changed from edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team1359.deprecated.ExampleSubsystem;
import org.usfirst.frc.team1359.robot.commands.autonomous.AutonomousCommandDispatch;
import org.usfirst.frc.team1359.robot.commands.autonomous.TurnByAngle;
import org.usfirst.frc.team1359.robot.subsystems.Aesthetics;
import org.usfirst.frc.team1359.robot.subsystems.Camera;
import org.usfirst.frc.team1359.robot.subsystems.Climber;
import org.usfirst.frc.team1359.robot.subsystems.ArmManipulator;
import org.usfirst.frc.team1359.robot.subsystems.ElevatorManipulator;
import org.usfirst.frc.team1359.robot.subsystems.PIDDriveSystem;
import org.usfirst.frc.team1359.robot.Vision;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Robot extends TimedRobot {
	public static final ExampleSubsystem kExampleSubsystem = new ExampleSubsystem();
	public static final PIDDriveSystem kPIDDriveSystem = new PIDDriveSystem();
	public static final Aesthetics kAesthetics = new Aesthetics();
	public static OI kOI;
	public static final Climber kClimber = new Climber();
	public static final ArmManipulator kArmManipulator = new ArmManipulator();
	public static final ElevatorManipulator kElevatorManipulator = new ElevatorManipulator();
	public static final Camera kcamera = new Camera();
	public static final Vision kVision = new Vision();
	public static String AutonomousLeftOrRightPriority = "None";
	public static String AutonomousMiddlePriority = "None";
	Command m_autonomousCommand;
	SendableChooser<String> m_priority = new SendableChooser<String>();
	SendableChooser<String> m_priorityMiddle = new SendableChooser<String>();
	SendableChooser<String> m_override = new SendableChooser<String>();

	// vision
    NetworkTableEntry xEntry;
	NetworkTableEntry distance;
	double x = 0; // random values
    double distanceFromTarget = 20; // random values

	DriverStation driverStation;

	public Robot() {
		super(.025);
	}

	@Override
	public void robotInit() {
		kOI = new OI();

		// vision
		NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("datatable");
        NetworkTable targetDistanceTable = inst.getTable("dataTable");
        xEntry = xTable.getEntry("X");
        distance =targetDistanceTable.getEntry("distance");
		
		/*
		 * setDefaultOption replaced addDefault
		 * 
		 * addOption replaced addOject
		 */
		m_priority.setDefaultOption("Switch", "Switch");
		m_priority.addOption("Scale", "Scale");
		
		m_priority.addOption("Cross Line Only", "Neither");
		
		m_priorityMiddle.setDefaultOption("Yes", "Yes");
		m_priorityMiddle.addOption("No", "No");
		
		m_override.addOption("Auto", Constants.autoModeAuto);
		m_override.addOption("Override Left", Constants.autoModeLeft);
		m_override.addOption("Override Middle", Constants.autoModeMiddle);
		m_override.addOption("Override Right", Constants.autoModeRight);
		SmartDashboard.putData("Auto priority", m_priority);
		SmartDashboard.putData("Auto Middle", m_priorityMiddle);
		SmartDashboard.putData("Auton Override", m_override);
		

		System.out.println("====The 1359 Scalawags are ready to set sail!====");
		System.out.println("The 1359 Scalawags can win this match!  ");
		CameraServer.getInstance().startAutomaticCapture();
		driverStation = DriverStation.getInstance();
		
       
	}

	@Override
	public void robotPeriodic() {

	}

	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable chooser
	 * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
	 * remove all of the chooser code and uncomment the getString code to get the
	 * auto name from the text box below the Gyro
	 *
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons to
	 * the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		this.AutonomousLeftOrRightPriority = m_priority.getSelected();
		this.AutonomousMiddlePriority = m_priorityMiddle.getSelected();
		m_autonomousCommand = new AutonomousCommandDispatch(m_override.getSelected());
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
		 * switch(autoSelected) { case "My Auto": autonomousCommand = new
		 * MyAutoCommand(); break; case "Default Auto": default: autonomousCommand = new
		 * ExampleCommand(); break; }
		 */

		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Oh No It's Match Time!", driverStation.getMatchTime());
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		SmartDashboard.putString("Shooter", "Ready to Fire");
		SmartDashboard.putBoolean("Climber Locked", true);
		kPIDDriveSystem.resetEncoders();
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */ 
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Oh No It's Match Time!", driverStation.getMatchTime());

		// vision
		xEntry.setDouble(x);
        distance.setDouble(distanceFromTarget);
        x += .5; // random values
        distanceFromTarget += 1; // random values
	}

	/**
	 * This function is called once at the beginning of test mode.
	 */ 
	
	@Override
	public void testInit() {
		Scheduler.getInstance().add(new TurnByAngle(90));
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
		Scheduler.getInstance().run();
		
	}
}
