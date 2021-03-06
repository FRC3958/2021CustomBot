/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SingleMotorSpin;

public class ShootingFull extends CommandBase {
  /**
   * Creates a new ShootingFull.
   */
  SingleMotorSpin m_indexer; 
  Shooter m_shooter; 
  double shootingSpeed; 

  public ShootingFull(Shooter st, SingleMotorSpin smp2, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_indexer = smp2;
    m_shooter = st;
    shootingSpeed = speed;  

    addRequirements(m_indexer, m_shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_shooter.SetToTarget(shootingSpeed);            
    
    m_indexer.SpinningCommand(.6);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_shooter.SetToTarget(0);
    m_indexer.SpinningCommand(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
