/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */

  private WPI_TalonSRX frontLeft = new WPI_TalonSRX(Constants.frontLeftTalon);
  private WPI_TalonSRX backLeft = new WPI_TalonSRX(Constants.backLeftTalon);    //instantiates all the talons from CAN IDs in Constants, found in Phoenix Tuner 
  private WPI_TalonSRX frontRight = new WPI_TalonSRX(Constants.frontRightTalon);
  private WPI_TalonSRX backRight = new WPI_TalonSRX(Constants.backRightTalon);


  final DifferentialDrive diffDrive = new DifferentialDrive(backLeft, backRight); //onyl need to use back motors because fronts follow backs

  public DriveTrain() {
    // brake for best control
    backLeft.setNeutralMode(NeutralMode.Brake);
    frontLeft.setNeutralMode(NeutralMode.Brake);
    backRight.setNeutralMode(NeutralMode.Brake);
    frontRight.setNeutralMode(NeutralMode.Brake);
    
    // config motor direction    
    backLeft.setInverted(InvertType.None);
    frontLeft.setInverted(InvertType.FollowMaster);   //if motors are opposing each other change the invert type 
    backRight.setInverted(InvertType.None);
    frontRight.setInverted(InvertType.FollowMaster);
    
    // follow back motors
    frontLeft.follow(backLeft);
    frontRight.follow(backRight);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double forwardSpeed, double turnSpeed) {
    diffDrive.arcadeDrive(-forwardSpeed,turnSpeed); 
  }
}
