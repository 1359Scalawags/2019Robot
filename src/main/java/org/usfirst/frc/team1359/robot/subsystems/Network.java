
package org.usfirst.frc.team1359.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Network extends Subsystem{
    NetworkTableEntry xEntry;
    NetworkTableEntry distance;
    double x;
    double distanceFromTarget;
    
    public Network(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("xTable");
        NetworkTable targetDistanceTable = inst.getTable("dataTable");
        xEntry = xTable.getEntry("X");
        distance =targetDistanceTable.getEntry("distance");
        x = 0; // random values
        distanceFromTarget = 20; // random values
    }

    public void initDefaultCommand(){

    }

    public void getTableValues(){
        double xValue = xEntry.getDouble(0);
        double distanceValue = distance.getDouble(0);
        System.out.println("##############################################"+xValue);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+distanceValue);
    }

    public void setTablePeriodic(){
        xEntry.setDouble(x);
        distance.setDouble(distanceFromTarget);
        if(x <= 1000){
            x += 1; // random values
        }
        else{
            x = 0;
        }
        if(distanceFromTarget <= 1000){
            distanceFromTarget += 1; // random values
        }
        else{
            distanceFromTarget = 0;
        }
    }

}