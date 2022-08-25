// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.Constants;

public class LimelightLeftRight extends CommandBase {
  /** Creates a new LimelightLeftRight. */
  public double beginningXPosition;
  public LimelightLeftRight() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    RobotContainer.m_drivetrainSubsystem.setRaw(0.0, 0.0);
    beginningXPosition = RobotContainer.m_LimelightSubsystem.getTx();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(RobotContainer.m_LimelightSubsystem.hasTarget()) {
        if(RobotContainer.m_LimelightSubsystem.getTx() > Constants.limelightXMax){
            RobotContainer.m_drivetrainSubsystem.setRaw(0, 0.5);
        } else if(RobotContainer.m_LimelightSubsystem.getTx() < Constants.limelightXMin){
            RobotContainer.m_drivetrainSubsystem.setRaw(0, -0.5);
        } else { 
            RobotContainer.m_drivetrainSubsystem.setRaw(0.0, 0.0);
        }
        } else { 
          RobotContainer.m_drivetrainSubsystem.setRaw(0.0, 0.0);
      }
      }

  // Called once the command ends or is interrupted
  @Override
  public void end(boolean interrupted) {
    RobotContainer.m_drivetrainSubsystem.setRaw(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished(
  ) {
    
    return (beginningXPosition > Constants.limelightXMax ) ? RobotContainer.m_LimelightSubsystem.getTx() <= Constants.limelightXMax : RobotContainer.m_LimelightSubsystem.getTx() >= Constants.limelightXMin;
  }
}
