/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveTrain;

public class DrivingCommand extends CommandBase {
  /**
   * Creates a new drivingCommant.
   */
  private final DriveTrain m_DriveTrain;  
  private final XboxController m_controller; //references a controller
  
  public  DrivingCommand(DriveTrain dt, XboxController Controller) {
    // Use addRequirements() here to declare subsystem dependencies.              //this part is all necessary, beats me why just do it 
    m_DriveTrain = dt;  
    m_controller = Controller; // copies reference from the actual controller -> now it's static and can be used in execute()

    addRequirements(m_DriveTrain);  //nothing else can use drivtrain while this command is running 
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double speedLimit = SmartDashboard.getNumber("Speed", 0.5);
    m_DriveTrain.arcadeDrive(-m_controller.getRawAxis(Constants.LeftJoystickYAxis)*speedLimit, -m_controller.getRawAxis(Constants.RightJoystickXAxis)*speedLimit); 
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {  
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}