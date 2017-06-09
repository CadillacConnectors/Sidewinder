package org.usfirst.frc.team5086.robot;

/**
 * Created by Tucker on your birthday.
 */

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.Date;

import org.usfirst.frc.team5086.robot.autonomous.CommandInterface;
import org.usfirst.frc.team5086.robot.subsystems.drive.DriveSubsystem;
import org.usfirst.frc.team5086.robot.subsystems.drive.acceleration.AccelerationController;
import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;
import org.usfirst.frc.team5086.robot.subsystems.OtherSubsystem;

public class Robot extends IterativeRobot {

	private static OI oi;
    private long start = 0;
    private CommandInterface commandInterface;
	private Gyro gyro;
	private double gyroReduction = 0;
	private Ultrasonic ultra;
	private int teleops;
	//private CameraServer server;
	/*private UsbCamera cam1;
	private UsbCamera cam2;
	private boolean cameraSwitched = false;*/
    /*
     * 0 : Do Nothing
     * 1 : Forward for gear
     * 2 : Shoot for blue (trigger warning)mothaf***as
     * 3 : Right
     * 4 : Shoot for red (trigger warning)mothaf***as
     */
    private int auto = 2;

    public void robotInit() {
		oi = new OI();
		gyro = new ADXRS450_Gyro();
		ultra = new Ultrasonic(1, 0);
		//cam1 = CameraServer.getInstance().startAutomaticCapture(1);
		//cam2 = CameraServer.getInstance().startAutomaticCapture(2);
		//server = CameraServer.getInstance();
		//server.setSource(cam1);
		//server.startAutomaticCapture();
		//cameraSwitched = true;
		//CameraServer.getInstance().startAutomaticCapture();
		//CameraServer.getInstance().startAutomaticCapture();
		//CameraServer.getInstance().startAutomaticCapture();
    }
	
    public void disabledInit(){
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		gyroReduction += RobotMap.gyroCorrection;
	}

    public void autonomousInit() {
    	start = new Date().getTime();
    	assert(start!=0);
    	commandInterface = new CommandInterface(start);
		System.out.println("Auton Initializing");
    }
    
    public void autonomousPeriodic() {
		gyroReduction += RobotMap.gyroCorrection;
        Scheduler.getInstance().run();
    	long time = new Date().getTime();
    	System.out.println(time - start);
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
    	AccelerationController.axialAcceleration.clear();
    	AccelerationController.lateralAcceleration.clear();
    	teleops = 0;
    }
    
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    	
    	//System.out.println(AccelerationController.getAmps());
    	
    	double ultraInches = ultra.getRangeInches();

		gyroReduction += RobotMap.gyroCorrection;

    	//double angle = gyro.getAngle() - gyroReduction;
		if (teleops % 5 == 0) {
	    	System.out.println("ULTRA: " + ultraInches);
	    	System.out.println("ANGLE: " + gyro.getAngle());
		}
    	
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
        	boolean otherbutton1 = Robot.oi.other.getRawButton(1);
        	
        	/*if (!otherbutton1) {
        		cameraSwitched = false;
        	} else {
        		if (!cameraSwitched) {
        			cameraSwitched = true;
        			if (server.getSource() == cam1) {
        				server.setSource(cam2);
        			} else {
        				server.setSource(cam1);
        			}
        		}
        	}*/
        	
        	driveaxis3 *= -1;
        	
        //reverse controls
        	if (!drivebutton1) {
        		driveaxis1 *= -1;
        		driveaxis3 *= -1;
        		if (drivepov != -1) {
        			drivepov += 180;
        			if (drivepov >= 360) drivepov -= 360;
        		}
        	}

        
        //launcher and ball pedal
        if (otherbutton6){
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
    	  
        //strafing
        if (drivepov != -1) {
        	switch (drivepov) {
    	 		case 0:
    	 			DriveSubsystem.axialMovement(RobotMap.forwardSpeed, 0);
    	 			break;
    	 		case 90:
					DriveSubsystem.lateralMovement(-RobotMap.rightSpeed, 0);
    	 			break;
    	 		case 180:
    	 			DriveSubsystem.axialMovement(-RobotMap.forwardSpeed, 0);
    	 			break;
    	 		case 270:
					DriveSubsystem.lateralMovement(RobotMap.rightSpeed, 0);
    	 			break;
    	 		default:
    	 			break;
        	}
        	
        	//joystick driving	
        } else if (Math.abs(driveaxis1) > RobotMap.threshold && (Math.abs(driveaxis3) < RobotMap.threshold)){
        	DriveSubsystem.axialMovement(-driveaxis1, 0);
        } else if (Math.abs(driveaxis1) < RobotMap.threshold && (Math.abs(driveaxis3) > RobotMap.threshold)){
        	DriveSubsystem.turnMovement(-driveaxis3);
        } else if (Math.abs(driveaxis1) > RobotMap.threshold && (Math.abs(driveaxis3) > RobotMap.threshold)){
        	DriveSubsystem.axialMovement(-driveaxis1, 0);
        } else {
    	 	DriveSubsystem.stop();
     	}
        
        teleops++;
    }
    public void testPeriodic() {
    	LiveWindow.run();
    }
}