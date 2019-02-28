package org.usfirst.frc.team1359.robot.commands.climber;

import org.usfirst.frc.team1359.robot.Robot;
//import org.usfirst.frc.team1359.robot.subsystems.Climber.ClimbPosition;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PivotPosition extends Command {
    private pivotPosition pivot;
    public enum pivotPosition{
        CLIMB, DRIVE
    }

    public PivotPosition(pivotPosition pivotType) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	super("PivotPosition");
        requires(Robot.kClimber);
        pivot = pivotType;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(pivot == pivotPosition.CLIMB){
            Robot.kClimber.unlockPivot();
        }
    else{
        Robot.kClimber.lockPivot();
    }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}