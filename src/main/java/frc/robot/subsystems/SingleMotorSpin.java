/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SingleMotorSpin extends SubsystemBase {
  /**
   * Creates a new SingleMotorSpin. spins a single motor, as the name might suggest 
   */
  public WPI_TalonSRX m_singleMotor; 
  
  public SingleMotorSpin(int port) {
    m_singleMotor = new WPI_TalonSRX(port);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void SpinningCommand(double speed) {
    m_singleMotor.set(speed); 
  }
}
