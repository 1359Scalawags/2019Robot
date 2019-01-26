package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.commands.arm.StopBelts;
import org.usfirst.frc.team1359.robot.Constants;
import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.arm.BallIntake;
import org.usfirst.frc.team1359.robot.commands.arm.MoveArmsDown;
import org.usfirst.frc.team1359.robot.commands.autonomous.DriveStraightDistance;
//import org.usfirst.frc.team1359.robot.commands.shooter.ReleaseShooter;

import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutonomousRightPosition extends CommandGroup {

	private char switchPosNear;
	private char scalePos;

	public AutonomousRightPosition() {
		super("AutonomousRightPosition");
		requires(Robot.kPIDDriveSystem);
		requires(Robot.kArmManipulator);
		requires(Robot.kElevatorManipulator);

		DriverStation driverStation = DriverStation.getInstance();
		switchPosNear = driverStation.getGameSpecificMessage().charAt(0);
		scalePos = driverStation.getGameSpecificMessage().charAt(1);

		if (switchPosNear == 'R' && scalePos == 'L') { // drop cube in switch
			if(Robot.AutonomousLeftOrRightPriority.equals("Scale")) {
				shootOppositeScale();
				SmartDashboard.putString("Switch Close", "Right");
			}
			else {
			addSequential(new BallIntake());
			addSequential(new DriveStraightDistance(Constants.distanceToFrontCargoShip, Constants.autoDriveSpeed)); // random value in MoveForward()
			addSequential(new TurnByAngle(-90));
			addSequential(new DriveStraightDistance(Constants.approachSwitchEnd, Constants.autoDriveSpeed*.75));
			addSequential(new StopBelts());  
	//		addSequential(new MovesArmDown());
			SmartDashboard.putString("Switch Close", "Right");
			}
		} else if (scalePos == 'R' && switchPosNear == 'L') { // drop cube in scale
				shootRightScale();
		} else if (scalePos == 'R' && switchPosNear == 'R') { // determined by smartDashboard
			if (Robot.AutonomousLeftOrRightPriority.equals("Switch")) { // drop cube in switch
				addSequential(new BallIntake());
				addSequential(new DriveStraightDistance(Constants.distanceToFrontCargoShip, Constants.autoDriveSpeed)); // random value in MoveForward()
				addSequential(new TurnByAngle(-90));
				addSequential(new StopBelts()); // CHANGE THIS
		//		addSequential(new MovesArmDown());
				SmartDashboard.putString("Switch Close", "Right");
			} else if (Robot.AutonomousLeftOrRightPriority.equals("Scale")) { // drop cube in scale
				shootRightScale();
		} else { // don't drop cube, just cross line
//			addSequential(new DriveStraightDistance(Constants.distanceToSwitchCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
			SmartDashboard.putString("Neither Close", "Right");
			shootOppositeScale();
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
	}
	
	public void shootRightScale() {
		addSequential(new BallIntake());
		addSequential(new DriveStraightDistance(Constants.distanceToScaleCenterline, Constants.autoDriveSpeed)); // random value in MoveForward()
		addSequential(new TurnByAngle(115));
		addSequential(new DriveStraightDistance(Constants.approachScaleEnd, Constants.autoDriveSpeed)); // random value in MoveForward()
	//	addSequential(new MovesArmDown());
		addSequential(new Delay());
	//	addSequential(new ReleaseShooter()); // assuming PrepareToLaunchShooter was already ran
		addSequential(new Delay());
		addSequential(new DriveStraightDistance(2.5, Constants.autoDriveSpeed*.5));
		SmartDashboard.putString("Scale Close", "Right");
	}
	
	public void shootOppositeScale() {
		addSequential(new BallIntake());
		addSequential(new DriveStraightDistance(Constants.distanceToMiddle, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(-90));
		addSequential(new DriveStraightDistance(Constants.distanceAcrossScale, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(-90));
		addSequential(new DriveStraightDistance(Constants.approachScaleBox, Constants.autoDriveSpeed));
		addSequential(new TurnByAngle(45));
		addSequential(new TurnByAngle(1));
	//	addSequential(new MovesArmDown());
		addSequential(new Delay());
	//	addSequential(new ReleaseShooter());
	}
}
