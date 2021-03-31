/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

import java.lang.Math; 

public class limelight extends SubsystemBase {
  /**
   * Creates a new limelight.
   */


  NetworkTable limetable = NetworkTableInstance.getDefault().getTable("limelight");

  public limelight() {

  }

  double distanceToTarget;
  double xDistanceDouble; 
  double targetRPM; 

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    limetable.getEntry("ledMode").setNumber(0);   //turn limelight led on 
    final NetworkTableEntry distance = limetable.getEntry("ty"); //find angle from limelight as a NetworkTableEntry
    final double doubleAngle = distance.getDouble(0.0);         //convert angle to a double
    //SmartDashboard.putNumber("LimelightAngle", doubleAngle);    //puts on SmartDashboard for testing purposes, not really necessary 

    final double finalAngle = (Math.PI * (Constants.angleDefault + doubleAngle))/180; //find angle above horizon that the target is, adds in 17 because limelight is tilted up
    //SmartDashboard.putNumber("final Angle", finalAngle);                              //also converts to radians for tan function, puts on SmartDashboard for testing 

    final double tanAngle = Math.tan(finalAngle);       //finds tan of angle, represents height of target/distance to target
    //SmartDashboard.putNumber("TanAngle", tanAngle);     //derived from tan(angle above horizon) = height to target/distance to target, visualize as a triangle
    
    distanceToTarget = Constants.heightDifferenceInches/tanAngle;   //self explanatory
    SmartDashboard.putNumber("final distance", distanceToTarget);

    final NetworkTableEntry xDistance = limetable.getEntry("tx"); 
    xDistanceDouble = xDistance.getDouble(0.0); 

    //TODO add targetRPM thingy
  }

  public double getDistanceToTarget() {
    return distanceToTarget; 
  }

  public double getAngleOffset() {
    return xDistanceDouble; 
  }

  public double getTargetRPM() {
    return targetRPM;
  }



}
