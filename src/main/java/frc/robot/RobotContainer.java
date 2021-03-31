/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.DrivingCommand;
import frc.robot.commands.SingleSpinCommand;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SingleMotorSpin;
import frc.robot.subsystems.limelight;
import frc.robot.subsystems.solenoid;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final XboxController driverController = new XboxController(Constants.XboxPort);

  public DriveTrain drivingTrain = new DriveTrain();
  public DrivingCommand drivingCommand = new DrivingCommand(drivingTrain, driverController);

  public SingleMotorSpin Indexer = new SingleMotorSpin(Constants.IndexerTalon);
  public SingleMotorSpin Trigger = new SingleMotorSpin(Constants.TriggerTalonPort);

  public Compressor m_Compressor = new Compressor(Constants.CompressorPort); 

  public Shooter m_Shooter = new Shooter();

  public solenoid m_solenoid = new solenoid(); 
  
  public limelight m_limelight = new limelight(); 
  


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    m_Compressor.stop(); 
    SmartDashboard.putNumber("Elevated", 0); 
    SmartDashboard.putString("Limelight IP", "http://10.39.58.11:5801/");
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    drivingTrain.setDefaultCommand(drivingCommand);


    new JoystickButton(driverController, Constants.BumperPortLeft)
      .whenHeld(new SingleSpinCommand(Indexer, -0.4));

    new JoystickButton(driverController, Constants.BumperPortRight)
      .whenHeld(new SingleSpinCommand(Indexer, 0.4));

    new JoystickButton(driverController, Constants.YButtonController)
      .whenHeld(new SingleSpinCommand(Trigger, 0.6));

    new JoystickButton(driverController, Constants.BButtonController)
      .whenPressed(() -> m_Compressor.start())
      .whenReleased(() -> m_Compressor.stop());

    new JoystickButton(driverController, Constants.AButtonController)   //just for testing needs to be changed 
      .whenPressed(() -> m_Shooter.SetToTarget(0.7))
      .whenReleased(() -> m_Shooter.SetToTarget(0))
      ;

    new JoystickButton(driverController, Constants.NextButtonController)   //just for testing needs to be changed 
      .whenPressed(() -> m_solenoid.airIn())
      .whenReleased(() -> m_solenoid.airOff())
      ;

    new JoystickButton(driverController, Constants.BackButtonController)   //just for testing needs to be changed 
      .whenPressed(() -> m_solenoid.airOut())
      .whenReleased(() -> m_solenoid.airOff())
      ;

    new JoystickButton(driverController, buttonNumber);
                              //TODO create Limelight to find distance, use that to find speed, must consider if pistons are raised/lowered
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  //public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    //return m_autoCommand;
  //}
}
