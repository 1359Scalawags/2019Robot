package org.usfirst.frc.team1359.robot.commands.climber;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.Delay;
import org.usfirst.frc.team1359.robot.commands.climber.PivotPosition.pivotPosition;
//import org.usfirst.frc.team1359.robot.subsystems.Climber.ClimbPosition;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class MoveIntoDrivePosition extends CommandGroup {

    public MoveIntoDrivePosition() {
    	super("MoveIntoDrivePosition");
    	requires(Robot.kClimber);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
       // Robot.kClimber.ClimberRotate(ClimbPosition.DRIVE);
       addSequential(new Drive());
        addSequential(new Delay(.1f));
        addSequential(new PivotPosition(pivotPosition.DRIVE)); // lock pivot
       // Robot.kClimber.lockPivot();
    }



    // // Called just before this Command runs the first time
    // protected void initialize() {
    //    // Robot.kClimber.switchSpeedDirection();
    // }

    // // Called repeatedly when this Command is scheduled to run
    // protected void execute() {
    //     Robot.kClimber.ClimberRotate(ClimbPosition.DRIVE);
    //     addSequential(new Delay(.1f));
    //     Robot.kClimber.lockPivot();
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
