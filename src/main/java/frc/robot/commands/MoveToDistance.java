/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.limelight;

public class MoveToDistance extends CommandBase {
  /**
   * Creates a new MoveToDistance.
   */

  double m_target;
  limelight m_limelight; 
  public double forwardBackward = 0; 
  DriveTrain m_DriveTrain; 
  NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");

  public MoveToDistance(double target, DriveTrain dt, limelight lm) {
    m_DriveTrain = dt; 
    m_target = target; 
    m_limelight = lm; 
    
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_DriveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

      table.getEntry("ledMode").setNumber(0);
      if ((m_target - m_limelight.getDistanceToTarget()) > 0 ) {
        m_DriveTrain.arcadeDrive(-.5, 0);
        
      }
      else {
        m_DriveTrain.arcadeDrive(.5, 0);

      }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    table.getEntry("ledMode").setNumber(1);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    double distanceDifference = Math.abs(m_target - m_limelight.getDistanceToTarget()); 
    return distanceDifference <= 3;     //returns true once within an inch, might increase tolerance becuase limelight fluctuates
  }
}
