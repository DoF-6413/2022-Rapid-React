// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.AutoCommand;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.IntakeSubsystem;


import edu.wpi.first.wpilibj2.command.*;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  // private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  private final AutoCommand m_autoCommand = new AutoCommand();
  public Joystick m_leftStick = new Joystick(Constants.initialJoystickPort);
  public Joystick m_rightStick = new Joystick (Constants.secondaryJoystickPort);

  public Joystick LeftStick;
  public Joystick RightStick;

  //Intake Subsystem
  private final IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

 

    // Configure the button bindings
    configureButtonBindings();
    m_drivetrainSubsystem.setDefaultCommand(
    new RunCommand(()-> 
    m_drivetrainSubsystem.setRaw(m_leftStick.getRawAxis(Constants.joystickAxis), m_rightStick.getRawAxis(Constants.joystickAxis)), m_drivetrainSubsystem
    )
    );
    

    JoystickButton intakeButton = new JoystickButton(m_rightStick, Constants.intakeButton);
    intakeButton.whenActive(()-> m_intakeSubsystem.print());
   
  }


  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() 
    
  {
   
    
  }



  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
