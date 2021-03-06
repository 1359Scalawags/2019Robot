package org.usfirst.frc.team1359.robot.commands.Elevator;

import org.usfirst.frc.team1359.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveElevatorDown extends Command {

	public MoveElevatorDown() {
		super("MoveElevatorDown");
		requires(Robot.kElevatorManipulator);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	//	if(Robot.kOI.getDpadYValue() == false){
			Robot.kElevatorManipulator.abateHeight();
	//	}
		// else if(Robot.kOI.getDpadYValue() == true){
		// 	Robot.kElevatorManipulator.incrementHeight();
		// }
		// else{
		// }
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	//	if(Robot.kOI.getDpadYValue() == false){ // false is down
			Robot.kElevatorManipulator.moveElevator();
		// }
		// else{
		// }
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.kElevatorManipulator.isToHeight();
	}

	// Called once after isFinished returns true
	protected void end() {
		}

	protected void interrupted() {
	}
}
