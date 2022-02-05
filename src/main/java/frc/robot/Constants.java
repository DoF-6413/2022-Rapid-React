// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    // defining joystick ports
    public static int initialJoystickPort = 0;
    public static int secondaryJoystickPort = 1;
    // defining axis
    public static int joystickAxis = 1;

    public static int[] leftDeviceID = new int[]{1, 2, 3}; //CAN ID configured using Spark MAX Client
    public static int[] rightDeviceID = new int[]{4, 5, 6}; 
    
}
