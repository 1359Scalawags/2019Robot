package org.usfirst.frc.team1359.robot.commands.autonomous;

import org.usfirst.frc.team1359.robot.Robot;
import org.usfirst.frc.team1359.robot.commands.arm.CubeGrab;
import org.usfirst.frc.team1359.robot.commands.arm.MoveToMiddle;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TestGroup extends CommandGroup {

    public TestGroup() {
    	super("TestGroup");

        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	addSequential(new CubeGrab());
    	addSequential(new TurnByAngle(45));
    	addSequential(new MoveToMiddle());
    	
    }
}
