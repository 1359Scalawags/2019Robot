package org.usfirst.frc.team1359.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtendAndRetractArms extends CommandGroup {

    static ArmPos moveArms= ArmPos.OUT; // CHANGE TO IN AFTER ROBOT IS COMPLETE
    private enum ArmPos{
        OUT, IN
    }

	public ExtendAndRetractArms() {
        if(moveArms == ArmPos.IN){
        addSequential(new ExtendArms());
            moveArms = ArmPos.OUT;
        }
        else{
            addSequential(new ReleaseHatch());
            addSequential(new RetractArms());
            moveArms = ArmPos.IN;
        }
	}
}
