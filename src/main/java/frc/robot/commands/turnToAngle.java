/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.limelight;

public class turnToAngle extends CommandBase {
  /**
   * Creates a new turnToAngle.
   */

  limelight m_limelight;
  DriveTrain m_driveTrain;

  public turnToAngle(limelight lm, DriveTrain dt) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_limelight=lm;
    m_driveTrain=dt;
    addRequirements(m_driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_limelight.getAngleOffset() > 0) {
      m_driveTrain.arcadeDrive(0, -0.4);
    }
    else {
      m_driveTrain.arcadeDrive(0, 0.4);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(m_limelight.getAngleOffset()) <= 0.5);
  }
}
