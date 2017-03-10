package org.usfirst.frc.team5086.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.Date;

import org.usfirst.frc.team5086.robot.autonomous.CommandInterface;
import org.usfirst.frc.team5086.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5086.robot.subsystems.OtherSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends IterativeRobot {

	private static OI oi;
	private static double speed = 0;
	private static int strafe = 0;
    private long start = 0;
    private CommandInterface commandInterface;
	private Gyro gyro;
    /*
     * 0 : Do Nothing
     * 1 : Forward for gear
     * 2 : Shoot for blue mothaf***as
     * 3 : Right
     * 4 : Shoot for red mothaf***as
     */
    private int auto = 4;

    public void robotInit() {
		oi = new OI();
		gyro = new AnalogGyro(0);
    }
	
    public void disabledInit(){
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	start = new Date().getTime();
    	assert(start!=0);
    	commandInterface = new CommandInterface(start);
    }
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    	long time = new Date().getTime();
        switch (auto) {
        case 1:
        	commandInterface.forward(time);
        	break;
        case 2:
        	commandInterface.shoot(time, CommandInterface.ALLIANCE_BLUE);
        	break;
        case 3:
        	commandInterface.right(time);
        	break;
        case 4:
        	commandInterface.shoot(time, CommandInterface.ALLIANCE_RED);
        	break;
        case 0:
        default:
			commandInterface.noAction(time);
        	break;
        }
    }

    public void teleopInit() {

    }
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();

    	System.out.println(gyro.getAngle());
    	
    	//controller 1 inputs
        	double driveaxis1 = Robot.oi.drive.getRawAxis(1); //Left y
        	double driveaxis3 = Robot.oi.drive.getRawAxis(4); //Right trigger
        	int drivepov = Robot.oi.drive.getPOV(0); //D-pad 
        	boolean drivebutton1 = Robot.oi.drive.getRawButton(6);
      
        //controller 2 inputs
        	double otheraxis2 = Robot.oi.other.getRawAxis(2); //Left trigger
        	double otheraxis3 = Robot.oi.other.getRawAxis(3); //Right trigger
        	boolean otherbutton3 = Robot.oi.other.getRawButton(3); //X
        	boolean otherbutton4 = Robot.oi.other.getRawButton(4); //Y
        	boolean otherbutton6 = Robot.oi.other.getRawButton(6); //right bumper        	
        	
        //reverse controls
        	if (drivebutton1==true) {
        		driveaxis1 *= -1;
        		driveaxis3 *= -1;
        		if (drivepov != -1) {
        			drivepov += 180;
        		}
        	}
        	

        
        //launcher and ball pedal
        if (otherbutton6 == true){
        	OtherSubsystem.launcherMovement(310);
        	if (otherbutton4){
        		OtherSubsystem.ballpedal(.5);
        	} else if (otherbutton3){ 
        		OtherSubsystem.ballpedal(0);
        	} else {
        		OtherSubsystem.ballpedal(-.5);
        	}
        } else {
        	OtherSubsystem.launcherMovement(0);
        	OtherSubsystem.ballpedal(0);
        }
       
        // Winch
        OtherSubsystem.winchmovement(otheraxis3 * RobotMap.winchspeed);
       
        //intake
        OtherSubsystem.sweeperMovement(otheraxis2);
        
        if (speed > 0) speed -= .05;
        if (strafe < 0) strafe++;
        if (strafe > 0) strafe--;
    	  
        //strafing
        if (drivepov != -1) {
        	System.out.println("drivepov:" + drivepov);
        	switch (drivepov) {
    	 		case 0:
    	 			if (speed < RobotMap.forwardSpeed) speed += .1;
    	 			DriveSubsystem.axialMovement(speed);
    	 			break;
    	 		case 90:
    	 			if (speed < RobotMap.rightSpeed) speed += .1;
    	 			if (strafe >= 0) {
        	 			DriveSubsystem.lateralMovement(speed);
    	 				strafe = 25;
    	 			}
    	 			break;
    	 		case 180:
    	 			if (speed < RobotMap.forwardSpeed) speed += .1;
    	 			DriveSubsystem.axialMovement(-speed);
    	 			break;
    	 		case 270:
    	 			if (speed < RobotMap.rightSpeed) speed += .1;
    	 			if (strafe <= 0) {
        	 			DriveSubsystem.lateralMovement(-speed);
    	 				strafe = -25;
    	 			}
    	 			break;
    	 		default:
    	 			break;
        	}
        	
        	//joystick driving	
        } else if (Math.abs(driveaxis1) > RobotMap.threshold && (Math.abs(driveaxis3) < RobotMap.threshold)){
        	System.out.println("Driving:" + driveaxis1);
        	DriveSubsystem.axialMovement(-driveaxis1);
        } else if (Math.abs(driveaxis1) < RobotMap.threshold && (Math.abs(driveaxis3) > RobotMap.threshold)){
        	DriveSubsystem.turnMovement(-driveaxis3);
        	System.out.println("Turning:" + driveaxis3);
        } else if (Math.abs(driveaxis1) > RobotMap.threshold && (Math.abs(driveaxis3) > RobotMap.threshold)){
        	DriveSubsystem.axialturnMovement(driveaxis3,driveaxis1);
        } else {
    	 	DriveSubsystem.axialMovement(0);
     	}
    }
    public void testPeriodic() {
    	LiveWindow.run();
    }
}