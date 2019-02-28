package org.usfirst.frc.team1359.robot.commands.climber;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.climber.PivotPosition.pivotPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveIntoClimbPosition extends CommandGroup {

    public MoveIntoClimbPosition() {
    	super("MoveIntoClimbPosition");
    	requires(Robot.kClimber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);

      //  Robot.kClimber.unlockPivot();
        addSequential(new PivotPosition(pivotPosition.CLIMB)); // unlock pivot
        // addSequential(new Drive());
        // addSequential(new Delay(1f));
        addSequential(new Climb());
        
      //  Robot.kClimber.ClimberRotate(ClimbPosition.CLIMB);
    }

    // // Called just before this Command runs the first time
    // protected void initialize() {
    // }

    // // Called repeatedly when this Command is scheduled to run
    // protected void execute() {
    //     Robot.kClimber.unlockPivot();
    //     addSequential(new Delay(.1f));
    //     Robot.kClimber.ClimberRotate(ClimbPosition.CLIMB);
    // }

    // // Make this return true when this Command no longer needs to run execute()
    // protected boolean isFinished() {
    //     return true;
    // }

    // // Called once after isFinished returns true
    // protected void end() {
    // }

    // // Called when another command which requires one or more of the same
    // // subsystems is scheduled to run
    // protected void interrupted() {
    // }
}
