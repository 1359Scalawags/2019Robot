package org.usfirst.frc.team1359.robot.commands.arm;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ExtendAndRetractArms extends CommandGroup {

    static ArmPos moveArms= ArmPos.IN;
    private enum ArmPos{
        OUT, IN
    }

	public ExtendAndRetractArms() {
        if(moveArms == ArmPos.IN){
        addSequential(new ExtendArms());
            moveArms = ArmPos.OUT;
        }
        else{
            addSequential(new RetractArms());
            moveArms = ArmPos.IN;
        }
	}
}
