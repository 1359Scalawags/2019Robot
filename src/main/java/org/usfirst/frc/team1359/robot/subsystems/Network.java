
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
    NetworkTableEntry angle;
    // not needed in final build
    private double x;
    private double distancevalue;
    // needed in final build
    float xPercentage;
    int angleAtTarget;
    double distanceFromTarget;
    
    public Network(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("XTable");
        NetworkTable targetDistanceTable = inst.getTable("DistanceTable");
        xEntry = xTable.getEntry("X");
        distance = targetDistanceTable.getEntry("DistanceFromTarget");
        angle = targetDistanceTable.getEntry("AngleFromTarget");
        //  x = 0; // random values
        // distanceFromTarget = 20; // random values
    }

    public void initDefaultCommand(){

    }

    public double returnDistanceFromTarget(){
        return distanceFromTarget;
    }

    public int returnAngleFromTarget(){
        return angleAtTarget;
    }

    public void getTableValues(){
        xPercentage = (float)xEntry.getDouble(0);
        distanceFromTarget = distance.getDouble(0);
        angleAtTarget = (int)angle.getDouble(0);
        System.out.println("##############################################"+xPercentage);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+distancevalue);
    }

    // not needed in final build
    public void setTablePeriodic(){
        xEntry.setDouble(x);
        distance.setDouble(distancevalue);
        if(x <= 1000){
            x += 1; // random values
        }
        else{
            x = 0;
        }
        if(distancevalue <= 1000){
            distancevalue += 1; // random values
        }
        else{
            distancevalue = 0;
        }
    }

}