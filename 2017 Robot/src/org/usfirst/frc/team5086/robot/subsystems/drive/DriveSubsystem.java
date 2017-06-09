package org.usfirst.frc.team5086.robot.subsystems.drive;

/**
 * Created by Tucker on you know what day.
 */

import org.usfirst.frc.team5086.robot.RobotMap;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team5086.robot.subsystems.drive.acceleration.AccelerationController;
import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

public class DriveSubsystem extends Subsystem {

    public void initDefaultCommand() {
    }

    public static void stop() {
    	if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_NONE;
			AccelerationController.setTalonPercents(new WheelConfiguration(0));
		}
	}

    //straight forward and backward
    public static void lateralMovement(double speed, double angle) {
    	if (AccelerationController.mode == AccelerationController.MODE_AXIAL) {
			AccelerationController.axialAcceleration.accelerate(speed);
			//AccelerationController.sendAngle(angle);
			AccelerationController.setTalonPercents(AccelerationController.axialAcceleration.getVictorConfiguration());
		} else if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_AXIAL;
			//AccelerationController.axialAcceleration.setAngle(angle);
		}
	}
    
    public static void lateralMovement(double speed) {
    	if (AccelerationController.mode == AccelerationController.MODE_AXIAL) {
			AccelerationController.setTalonPercents(new WheelConfiguration(speed));
		} else if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_AXIAL;
		}
    }
    
    //zero degree turning
    public static void  turnMovement(double speed) {
    	if (AccelerationController.mode == AccelerationController.MODE_ROTATIONAL) {
			AccelerationController.rotationalAcceleration.accelerate(speed);
			AccelerationController.setTalonPercents(AccelerationController.rotationalAcceleration.getVictorConfiguration());
		} else if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_ROTATIONAL;
		}
	}
    
    //strafing left and right
    public static void  axialMovement(double speed, double angle) {
    	if (AccelerationController.mode == AccelerationController.MODE_LATERAL) {
			AccelerationController.lateralAcceleration.accelerate(speed);
			//AccelerationController.sendAngle(angle);
			AccelerationController.setTalonPercents(AccelerationController.lateralAcceleration.getVictorConfiguration());
		} else if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_LATERAL;
			//AccelerationController.lateralAcceleration.setAngle(angle);
		}
    }
    
    public static void  axialMovement(double speed) {
    	if (AccelerationController.mode == AccelerationController.MODE_LATERAL) {
			AccelerationController.setTalonPercents(new WheelConfiguration(speed));
		} else if (AccelerationController.stopMode()) {
			AccelerationController.mode = AccelerationController.MODE_LATERAL;
		}
    }

	/*
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
    }*/
}