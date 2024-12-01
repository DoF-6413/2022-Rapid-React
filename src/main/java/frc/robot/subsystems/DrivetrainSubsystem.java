package frc.robot.subsystems;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


//CANSPark imports
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.RelativeEncoder;

public class DrivetrainSubsystem extends SubsystemBase {

    // Declare left motors
    private CANSparkMax leftLead;
    private CANSparkMax leftFollow1;
    private CANSparkMax leftFollow2;
    // Encoder left lead is used for values on the left side of the robot because
    // they are all connected
    // Declare right motors
    private CANSparkMax rightLead;
    private CANSparkMax rightFollow1;
    private CANSparkMax rightFollow2;
    // Encoder right lead is used for values on the right side of the robot because
    // they are all connected
    
    private RelativeEncoder encoderLeftLead;
    private RelativeEncoder encoderRightLead;
    
    double RightStick;
    double LeftStick;
    
    private DifferentialDrive diffDrive;
    
    /** 
     * This is the Drivetrain Subsystem 
    */
    public DrivetrainSubsystem() {
        // Initializes left motors in default constructor
        leftLead = new CANSparkMax(Constants.leftDeviceID[0], MotorType.kBrushless);
        leftFollow1 = new CANSparkMax(Constants.leftDeviceID[1], MotorType.kBrushless);
        leftFollow2 = new CANSparkMax(Constants.leftDeviceID[2], MotorType.kBrushless);

        encoderLeftLead = leftLead.getEncoder();

        // Initializes left motors in default constructor
        rightLead = new CANSparkMax(Constants.rightDeviceID[0], MotorType.kBrushless);
        rightFollow1 = new CANSparkMax(Constants.rightDeviceID[1], MotorType.kBrushless);
        rightFollow2 = new CANSparkMax(Constants.rightDeviceID[2], MotorType.kBrushless);

        encoderRightLead = rightLead.getEncoder();
        // Set left follow motors
        leftFollow1.follow(leftLead);
        leftFollow2.follow(leftLead);

        // WPI assumes that the left and right are opposite, this allows the motors to
        // both move forward when applying positive output
        rightLead.setInverted(true);
        // Set right follow motors
        rightFollow1.follow(rightLead);
        rightFollow2.follow(rightLead);
        diffDrive = new DifferentialDrive(leftLead, rightLead);

        // Coverts Tics to Feet for Encoder Readout
        encoderLeftLead.setPositionConversionFactor(Constants.tick2feet);
        encoderRightLead.setPositionConversionFactor(Constants.tick2feet);

        leftLead.setIdleMode(CANSparkMax.IdleMode.kBrake); // setIdleMode​(CANSparkMax.IdleMode.kBrake)
        leftFollow1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftFollow2.setIdleMode(CANSparkMax.IdleMode.kBrake);

        rightLead.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollow1.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollow2.setIdleMode(CANSparkMax.IdleMode.kBrake);
         
        leftLead.setSmartCurrentLimit(50);
        leftFollow1.setSmartCurrentLimit(50);
        leftFollow2.setSmartCurrentLimit(50);

        rightLead.setSmartCurrentLimit(50);
        rightFollow1.setSmartCurrentLimit(50);
        rightFollow2.setSmartCurrentLimit(50);
    }
    
    /**
     * Sets the raw speed of the drivetrain using an tank style.
     * 
     * @param leftStick  Value from -1.0 to 1.0 representing the left rate
     * @param rightStick Value from -1.0 to 1.0 representing the right rate
     */
    public void setRaw(double rightStick, double leftStick) {

        diffDrive.arcadeDrive(-(rightStick), (leftStick)*Constants.turningScale);
        updateDashboard();
    }
    


    
    public double getAvgEncocderDistance() {
        double averageEncoderDistance = (encoderLeftLead.getPosition() + encoderRightLead.getPosition())/2.0 ;
        System.out.println ("averageEncoderDistance" + averageEncoderDistance);
        return averageEncoderDistance;
    }

      public void resetEncoderValue() {
        encoderLeftLead.setPosition(Constants.k_drivetrainStart);
        encoderRightLead.setPosition(Constants.k_drivetrainStart);
    }
    
      
      public void updateDashboard(){
        // SmartDashboard.putNumber("Left Lead", this.leftLead.getOutputCurrent());
        // SmartDashboard.putNumber("Left Lead Faults", this.leftLead.getFaults());
        // SmartDashboard.putNumber("Left Follow1", this.leftFollow1.getOutputCurrent());
        // SmartDashboard.putNumber("Left Follow1 Faults", this.leftFollow1.getFaults());
        // SmartDashboard.putNumber("Left Follow2", this.leftFollow2.getOutputCurrent());
        // SmartDashboard.putNumber("Left Follow2 Faults", this.leftFollow2.getFaults());

        // SmartDashboard.putNumber("Right Lead", this.rightLead.getOutputCurrent());
        // SmartDashboard.putNumber("Right Lead Faults", this.rightLead.getFaults());
        // SmartDashboard.putNumber("Right Follow1", this.rightFollow1.getOutputCurrent());
        // SmartDashboard.putNumber("Right Follow1 Faults", this.rightFollow1.getFaults());
        // SmartDashboard.putNumber("Right Follow2", this.rightFollow2.getOutputCurrent());
        // SmartDashboard.putNumber("Right Follow2 Faults", this.rightFollow2.getFaults());

        // SmartDashboard.putNumber("Encoder Left Lead", encoderLeftLead.getPosition());
        // SmartDashboard.putNumber("Encoder Right Lead", encoderRightLead.getPosition());
      }

      
}
