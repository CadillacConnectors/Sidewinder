package org.usfirst.frc.team5086.robot;

/**
 * Created by Tucker on 9/11/01.
 */

public class RobotMap {

	public static double gyroCorrection = .0;
	
	//motor ports
	public static int sweeper = 0;
	public static int frontLeft = 1;
	public static int backLeft = 2;
	public static int backRight = 3;
	public static int frontRight = 4;
	public static int launcher = 5;
	public static int ballpedal = 6;
	public static int winch = 7;
	
	//joystick ports
	public static int joystickDrive = 0;
	public static int joystickOther = 1;
	
	//compensating for motor controller variance
	//public static double frontRightReduction = .90;
	//public static double backRightReduction = .88;
	//public static double frontLeftReduction = .94;
	//public static double backLeftReduction = .96;
	
	//max speed variables
	public static double threshold = .3;
	public static double forwardSpeed = 1;
	public static double rightSpeed = 1;
	public static double launcherSpeed = 1;
	public static double sweeperSpeed = 1;
	public static double climberSpeed = 1;
	public static double ballpedalSpeed = 1;
	public static double winchspeed = 1;

	//Acceleration Constants
	public static double decelerationConstant = .25;
	public static double accelerationConstant = .1;
	
	public static int maxCurrent = 75;
}