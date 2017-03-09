package org.usfirst.frc.team5086.robot.subsystems;

import org.usfirst.frc.team5086.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSubsystem extends Subsystem {
	
	//creating motor controllers
	static Victor frontRight = new Victor(RobotMap.frontRight);
	static Victor backRight = new Victor(RobotMap.backRight);
	static Victor frontLeft = new Victor(RobotMap.frontLeft);
	static Victor backLeft = new Victor(RobotMap.backLeft);

    public void initDefaultCommand() {
    }
    
    //straight forward and backward
    public static void axialMovement(double speed) {	
    	frontRight.set(-speed*RobotMap.frontRightReduction);
    	backRight.set(-speed*RobotMap.backRightReduction);
    	frontLeft.set(speed*RobotMap.frontLeftReduction);
    	backLeft.set(speed*RobotMap.backLeftReduction);
    }
    
    //zero degree turning
    public static void  turnMovement(double speed) {
    	backRight.set(-speed*RobotMap.backRightReduction);
    	frontRight.set(-speed*RobotMap.frontRightReduction);
    	backLeft.set(-speed*RobotMap.backLeftReduction);
   		frontLeft.set(-speed*RobotMap.frontLeftReduction);
    }
    
    //strafing left and right
    public static void  lateralMovement(double speed) {
    	backRight.set(-speed*RobotMap.backRightReduction);
		frontRight.set(speed*RobotMap.frontRightReduction);
		backLeft.set(-speed*RobotMap.backLeftReduction);
		frontLeft.set(speed*RobotMap.frontLeftReduction);
    }
    
    //turning while moving
    public static void  axialturnMovement(double x,double y){
    	if (y>0) {//backwards
    		if (x>0) {//right
    			backRight.set(.5*x*y*RobotMap.backRightReduction);
    			frontRight.set(x*y*RobotMap.frontRightReduction);
    			backLeft.set(-x*y*RobotMap.backLeftReduction);
    			frontLeft.set(-x*y*RobotMap.frontLeftReduction);
    		} else {//left	
    			backRight.set(-x*y*RobotMap.backRightReduction);
    			frontRight.set(-x*y*RobotMap.frontRightReduction);
    			backLeft.set(.5*x*y*RobotMap.backLeftReduction);
    			frontLeft.set(x*y*RobotMap.frontLeftReduction);
    		}
    	} else {//forwards
    		if (x>0) {//right
    			backRight.set(.5*x*y*RobotMap.backRightReduction);
    			frontRight.set(x*y*RobotMap.frontRightReduction);
    			backLeft.set(-x*y*RobotMap.backLeftReduction);
    			frontLeft.set(-x*y*RobotMap.frontLeftReduction);
    		} else {//left
    			backRight.set(-x*y*RobotMap.backRightReduction);
    			frontRight.set(-x*y*RobotMap.frontRightReduction);
    			backLeft.set(.5*x*y*RobotMap.backLeftReduction);
    			frontLeft.set(x*y*RobotMap.frontLeftReduction);
    		}
    	}
    }
}