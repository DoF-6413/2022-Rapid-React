// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import frc.robot.subsystems.*;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.*;
import frc.robot.commands.*;
import frc.robot.commands.AutoCommands.*;
import frc.robot.subsystems.IndexerSubsystem;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static DrivetrainSubsystem m_drivetrainSubsystem = new DrivetrainSubsystem();
  public static ClimberSubsystem m_climberSubsystem = new ClimberSubsystem();
  // private final AutoCommand m_autoCommand = new
  // AutoCommand(m_drivetrainSubsystem);
  public static IndexerSubsystem m_indexerSubsystem = new IndexerSubsystem();
  public static IntakeSubsystem m_intakeSubsystem = new IntakeSubsystem();
  public static GyroSubsystem m_gyroSubsystem = new GyroSubsystem();
  public static ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
  public static LimelightSubsystem m_LimelightSubsystem = new LimelightSubsystem();

  // public Joystick m_leftStick = new Joystick(Constants.initialJoystickPort);
  // public Joystick m_rightStick = new Joystick(Constants.secondaryJoystickPort);
  public static XboxController m_driverXbox = new XboxController(0);
  public static XboxController m_xbox = new XboxController(1);
  public Trigger driverLeftTrigger = new Trigger(() -> IntakeSubsystem.getLeftTriggerActive());
  public Trigger driverRightTrigger = new Trigger(() -> IntakeSubsystem.getRightTriggerActive());
  public Trigger leftTrigger = new Trigger(()-> ClimberSubsystem.getLeftTriggerActive());
  public Trigger rightTrigger = new Trigger(()-> ClimberSubsystem.getRightTriggerActive());
  // XBOX Contoller Defs (For intake and Climber)

  // different Autos
  private final Command m_autoHighIntake = new AutoHighIntake(m_drivetrainSubsystem, m_shooterSubsystem,
  m_indexerSubsystem, m_intakeSubsystem);

  private final Command m_wAutoLowIntake = new WAutoLowIntake(m_drivetrainSubsystem, m_shooterSubsystem,
  m_indexerSubsystem, m_intakeSubsystem);

  private final Command m_autoHighGoal = new AutoHighGoal(m_drivetrainSubsystem, m_shooterSubsystem,
  m_indexerSubsystem, m_intakeSubsystem);

  private final Command m_autoLowGoal = new AutoLowGoal(m_drivetrainSubsystem, m_shooterSubsystem,
  m_indexerSubsystem, m_intakeSubsystem);

  private final Command m_autoMove = new AutoMove(m_drivetrainSubsystem, m_intakeSubsystem);

  private final Command m_autoPush = new AutoPush(m_drivetrainSubsystem, m_intakeSubsystem);

  private final Command m_wHighIntake = new WAutoHighIntake(m_drivetrainSubsystem, m_shooterSubsystem, m_indexerSubsystem, m_intakeSubsystem);

  private final Command m_autoLowIntake = new AutoLowIntake(m_drivetrainSubsystem, m_shooterSubsystem, m_indexerSubsystem, m_intakeSubsystem);
  // private final Command m_demoAuto = new DemoAutoRoutine(m_drivetrainSubsystem, m_shooterSubsystem, m_indexerSubsystem);
   private final Command m_testAuto = new TestAuto(m_drivetrainSubsystem, m_shooterSubsystem, m_gyroSubsystem, m_intakeSubsystem);
  private final Command m_spin = new spin(m_drivetrainSubsystem, m_gyroSubsystem);
  // Intake Subsystem
  public SendableChooser<Command> m_chooser = new SendableChooser<>();

//   m_chooser.setDefaultOption("Simple Auto", m_autoIntake);
//     m_chooser.addOption("Complex Auto", m_autoHighGoal);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer(
   ) {
     

    // Configure the button bindings
    configureButtonBindings();
    // Sets deafault Drivetrain for Subsystem
    m_drivetrainSubsystem.setDefaultCommand(
        new RunCommand(() -> m_drivetrainSubsystem.setRaw(m_driverXbox.getLeftY(), 
        m_driverXbox.getRightX()), m_drivetrainSubsystem));
//Left Trigger on Joystick = Make the climber go up

//        .whenReleased(new RunCommand(() -> m_climberSubsystem.stop(), m_climberSubsystem));
//Right Trigger on Joystick = Make the climber go down
//Manual Down so after match can bring down without relying on encoder values


driverRightTrigger
.whenActive(new InstantCommand(() -> m_intakeSubsystem.spinMotor(), m_intakeSubsystem))
.whenActive(new InstantCommand(() -> m_indexerSubsystem.spinMotor(), m_indexerSubsystem))
.whenInactive(new InstantCommand(() -> m_intakeSubsystem.stopMotor(), m_intakeSubsystem))
.whenInactive(new InstantCommand(() -> m_indexerSubsystem.stopMotor(), m_indexerSubsystem));

  driverLeftTrigger
  .whenActive(new InstantCommand(() -> m_intakeSubsystem.reverseMotor(), m_intakeSubsystem))
  .whenActive(new InstantCommand(() -> m_indexerSubsystem.spinBack(), m_indexerSubsystem))
  .whenInactive(new InstantCommand(() -> m_intakeSubsystem.stopMotor(), m_intakeSubsystem))
  .whenInactive(new  InstantCommand(() -> m_indexerSubsystem.stopMotor(), m_indexerSubsystem));

  new JoystickButton(m_xbox, XboxController.Button.kA.value)
  .whenPressed(new RunCommand(() -> m_climberSubsystem.goUpManual(Constants.manualClimberSpeedUp), m_climberSubsystem)).
  whenReleased(new RunCommand(() -> m_climberSubsystem.stop(), m_climberSubsystem));
  
  new JoystickButton(m_xbox, XboxController.Button.kB.value)
    .whenPressed(new RunCommand(() -> m_climberSubsystem.goDownManual(Constants.manualClimberSpeedDown), m_climberSubsystem)).
    whenReleased(new RunCommand(() -> m_climberSubsystem.stop(), m_climberSubsystem));
    
    new JoystickButton(m_driverXbox, XboxController.Button.kY.value)
    .whenPressed(new RunCommand(() -> m_intakeSubsystem.setAllActuatorsUp(Constants.actuatorsSpeed), m_intakeSubsystem))
    .whenReleased(new RunCommand(() -> m_intakeSubsystem.stopActuators(), m_intakeSubsystem));

new JoystickButton(m_driverXbox, XboxController.Button.kX.value)
   .whenPressed(new RunCommand(() -> m_intakeSubsystem.setAllActuatorsDown(Constants.actuatorsSpeed), m_intakeSubsystem))
    .whenReleased(new RunCommand(() -> m_intakeSubsystem.stopActuators(), m_intakeSubsystem));


//Left Middle Button on Aux Control Resets CLimber (Brings climber allthe way down and sets encoder value to 0)
  new JoystickButton(m_xbox, XboxController.Button.kStart.value)
  .whenPressed(new ClimberReset());

  //Right Middle Button on Driver Control Sets Intake Actuator to Brake
  new JoystickButton(m_xbox, XboxController.Button.kBack.value)
  .whenPressed(new EndGameClimbTraverse());

    // Trigger ButtonA = Spins Intake and Indexer forwards (Towards Shooter)
    new JoystickButton(m_driverXbox, XboxController.Button.kA.value)
    .whenPressed(new ClimberGoTo(47));
        
    // Trigger ButtonB = Spins Intake and Indexer backwards (Away from Shooter)
    new JoystickButton(m_driverXbox, XboxController.Button.kB.value)
    .whenPressed(new ClimberGoTo(0));
        
    // Trigger ButtonX = Brings Actuator Up and Will Stop When Released or When
    // Limit Switch Get Hits
    new JoystickButton(m_xbox, XboxController.Button.kY.value)
        .whenPressed(new RunCommand(() -> m_intakeSubsystem.setAllActuatorsUp(Constants.actuatorsSpeed), m_intakeSubsystem))
        .whenReleased(new RunCommand(() -> m_intakeSubsystem.stopActuators(), m_intakeSubsystem));
    // Trigger ButtonY = Bring Actuator Down and Will Stop When Released or When    
    
// individual actuators up and down    
    // Limit Switches Get Hits
    new JoystickButton(m_xbox, XboxController.Button.kX.value)
       .whenPressed(new RunCommand(() -> m_intakeSubsystem.setAllActuatorsDown(Constants.actuatorsSpeed), m_intakeSubsystem))
        .whenReleased(new RunCommand(() -> m_intakeSubsystem.stopActuators(), m_intakeSubsystem));
        

    // Trigger Button Left Bumper (L1) = Runs Shooter Subsystem at 2000 R P M
    new JoystickButton(m_xbox, XboxController.Button.kLeftBumper.value)
    .whenHeld(new LimelightShootLow(m_LimelightSubsystem, m_indexerSubsystem, m_shooterSubsystem, m_drivetrainSubsystem))
    .whenReleased((new InstantCommand(() -> m_shooterSubsystem.disable(), m_shooterSubsystem)))
    .whenReleased((new InstantCommand(() -> m_indexerSubsystem.stopMotor(), m_indexerSubsystem)));
  
    
    // Trigger Button Right Bumper (R1) = Runs Shooter Subsystem at 5000 R P M
    
    new JoystickButton(m_xbox, XboxController.Button.kRightBumper.value)
    .whenHeld(new LimelightShootHigh(m_LimelightSubsystem, m_indexerSubsystem, m_shooterSubsystem, m_drivetrainSubsystem))
    .whenReleased((new InstantCommand(() -> m_shooterSubsystem.disable(), m_shooterSubsystem)))
    .whenReleased((new InstantCommand(() -> m_indexerSubsystem.stopMotor(), m_indexerSubsystem)));

    //Runs the endgame climb mid routine, used to hook onto mid bar
    rightTrigger
    .whenActive(new EndGameClimbMid());
    
    //runs the endgame high routine, used to hook onto high and traverse bar
    leftTrigger
    .whenActive(new EndGameClimbHigh());
  
  

    SmartDashboard.putData(m_shooterSubsystem);
    m_chooser.setDefaultOption("Mid High Intake", m_autoHighIntake);
    m_chooser.addOption("Mid Low Intake", m_autoLowIntake);
    m_chooser.addOption("Wall Low Intake", m_wAutoLowIntake);
    m_chooser.addOption("Wall High Intake", m_wHighIntake);
    m_chooser.addOption("High Goal", m_autoHighGoal);
    m_chooser.addOption("Low Goal", m_autoLowGoal);
    m_chooser.addOption("Just Move", m_autoMove);
    m_chooser.addOption("Push Away", m_autoPush);
    m_chooser.addOption("Spin", m_spin);

    // m_chooser.addOption("Demo Auto", m_demoAuto);
    m_chooser.addOption("Test", m_testAuto);
      SmartDashboard.putData(m_chooser);
        m_LimelightSubsystem.getTx();
        m_LimelightSubsystem.getTy();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
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
    // new AutoCommand(DrivetrainSubsystem, 5);
    return m_chooser.getSelected();
  }
}
