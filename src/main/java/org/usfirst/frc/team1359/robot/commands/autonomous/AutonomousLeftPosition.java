package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.deprecated.CubeAtTop;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.BallIntake;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsDown;
import org.usfirst.frc.team1359.robot.commands.arm.StopBelts;
import org.usfirst.frc.team1359.robot.commands.drive.MoveDistance;
import org.usfirst.frc.team1359.robot.commands.Elevator.MoveElevatorCargoLower;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousLeftPosition extends CommandGroup {

	private char switchPosNear;
	private char scalePos;

	public AutonomousLeftPosition() {
		super("AutonomousLeftPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		requires(Robot.kElevatorManipulator);

		DriverStation driverStation = DriverStation.getInstance();
		switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
		scalePos = driverStation.getGameSpecificMessage().charAt(1);

		// Add Commands here:
		// e.g. addSequential(new Command1());
		// addSequential(new Command2());
		// these will run in order.
		if (switchPosNear == 'L' && scalePos == 'R') { // Drop cube in switch
			if(Robot.AutonomousLeftOrRightPriority.equals("Scale")){
				shootOppositeScale();
			SmartDashboard.putString("Close Switch", "Left");
			}
			else {
				addSequential(new BallIntake());
				addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
				addSequential(new TurnByAngle(90));
				addSequential(new DriveStraightDistance(Constants.approachSwitchEnd, Constants.autoDriveSpeed*.75));
				addSequential(new StopBelts());
				addSequential(new MoveArmsDown());
				SmartDashboard.putString("Close Switch", "Left");
			}
		} else if (scalePos == 'L' && switchPosNear == 'R') { // drop cube in scale
			shootLeftScale();
		} else if (scalePos == 'L' && switchPosNear == 'L') {
			if (Robot.AutonomousLeftOrRightPriority.equals("Switch")) {
				addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
				addSequential(new TurnByAngle(90));
				addSequential(new BallIntake());
				addSequential(new StopBelts()); // CHANGE THIS
				addSequential(new MoveArmsDown());
				SmartDashboard.putString("Close Switch", "Left");
			} else if (Robot.AutonomousLeftOrRightPriority.equals("Scale")) {
				shootLeftScale();
			}
		}

		 else { // cross line, don't drop cube
//		 addSequential(new CubeGrab());
//			addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); 
//			addSequential(new TurnByAngle(90));
			shootOppositeScale();
			SmartDashboard.putString("Neither Close", "Left");
		}
	}
	
	public void shootLeftScale() {
		addSequential(new BallIntake());
		addSequential(new DriveStraightDistance(Constants.distanceToScaleCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
		addSequential(new TurnByAngle(-115));
		addSequential(new DriveStraightDistance(Constants.approachScaleEnd, Constants.autoDriveSpeed)); // random value in MoveForward()
		//addSequential(new MovesArmDown());
		addSequential(new Delay());
		addSequential(new MoveElevatorCargoLower()); // assuming PrepareToLaunchShooter was already ran
		addSequential(new Delay());
		addSequential(new DriveStraightDistance(2.5, Constants.autoDriveSpeed*.5));
		SmartDashboard.putString("Close Scale", "Left");
		
	}
	
	public void shootOppositeScale() {
		addSequential(new BallIntake());
		addSequential(new DriveStraightDistance(Constants.distanceToMiddle, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(90));
		addSequential(new DriveStraightDistance(Constants.distanceAcrossScale, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(90));
		addSequential(new DriveStraightDistance(Constants.approachScaleBox, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(-45));
		addSequential(new TurnByAngle(1));
	//	addSequential(new MovesArmDown());
		addSequential(new Delay());
		addSequential(new MoveElevatorCargoLower());
	}
}
		
	// To run multiple commands at the same time,
	// use addParallel()
	// e.g. addParallel(new Command1());
	// addSequential(new Command2());
	// Command1 and Command2 will run in parallel.

	// A command group will require all of the subsystems that each member
	// would require.
	// e.g. if Command1 requires chassis, and Command2 requires arm,
	// a CommandGroup containing them would require both the chassis and the
	// arm.
