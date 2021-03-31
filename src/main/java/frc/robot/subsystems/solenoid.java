/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class solenoid extends SubsystemBase {
  /**
   * Creates a new solenoid.
   */
  DoubleSolenoid m_doubleSolenoid = new DoubleSolenoid(15, 0, 1); 
  

  public solenoid() {
    m_doubleSolenoid.set(Value.kReverse);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void airIn() {
    m_doubleSolenoid.set(Value.kForward); 
    SmartDashboard.putNumber("Elevated", 1);
  }
  
  public void airOut() {
    m_doubleSolenoid.set(Value.kReverse); 
    SmartDashboard.putNumber("Elevated", 0);
  }


  public void airOff() {
    m_doubleSolenoid.set(Value.kOff); 
  }
}
