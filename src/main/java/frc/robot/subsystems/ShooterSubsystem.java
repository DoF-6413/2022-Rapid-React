// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

// Talon FX Imports
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.drive.*;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;



public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  // Example usage of a TalonFX motor controller
 TalonFX motor = new TalonFX(Constants.shooterID); // creates a new TalonFX with ID 0





//  System.out.println(motor.getSelectedSensorPosition()); // prints the position of the selected sensor
//  System.out.println(motor.getSelectedSensorVelocity()); // prints the velocity recorded by the selected sensor
//  System.out.println(motor.getMotorOutputPercent()); // prints the percent output of the motor (0.5)
//  System.out.println(motor.getStatorCurrent()); // prints the output current of the motor

//  ErrorCode error = motor.getLastError(); // gets the last error generated by the motor controller
//  Faults faults = new Faults();
//  ErrorCode faultsError = motor.getFaults(faults); // fills faults with the current motor controller faults; returns the last error generated

//  motor.setStatusFramePeriod(StatusFrame.Status_2_Feedback0, 10); // changes the period of the Status 2 frame (getSelectedSensor*()) to 10ms
  
 public ShooterSubsystem() {}

  @Override
  public void periodic() {
  
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  public void spinMotor(){
    motor.set(TalonFXControlMode.PercentOutput, 0.1); // runs the motor at 10% power
    System.out.println("BUTTON PRESSED");
  }
}
