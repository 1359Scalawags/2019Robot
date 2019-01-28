
package org.usfirst.frc.team1359.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.command.Subsystem;

public class Vision extends TimedRobot{
    NetworkTableEntry xEntry;
    NetworkTableEntry distance;
    
    public void robotInit(){
        NetworkTableInstance inst = NetworkTableInstance.getDefault();
        NetworkTable xTable = inst.getTable("datatable");
        NetworkTable targetDistanceTable = inst.getTable("dataTable");
        xEntry = xTable.getEntry("X");
        distance =targetDistanceTable.getEntry("distance");
    }

    double x = 0; // random values
    double distanceFromTarget = 20; // random values

    public void teleopPeriodic(){
        xEntry.setDouble(x);
        distance.setDouble(distanceFromTarget);
        x += .5; // random values
        distanceFromTarget += 1; // random values
    }

}