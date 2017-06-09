package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import com.ctre.CANTalon;
import org.usfirst.frc.team5086.robot.RobotMap;
import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

/**
 * Created by Tucker on St. Patty's Day.
 */
public final class AccelerationController {
    public static final int MODE_LATERAL = 1;
    public static final int MODE_AXIAL = 2;
    public static final int MODE_ROTATIONAL = 3;
    public static final int MODE_LINEAL = 4;
    public static final int MODE_NONE = 0;
    public static int mode = MODE_NONE;

    private static CANTalon frontLeft = new CANTalon(RobotMap.frontLeft);
    private static CANTalon frontRight = new CANTalon(RobotMap.frontRight);
    private static CANTalon backLeft = new CANTalon(RobotMap.backLeft);
    private static CANTalon backRight = new CANTalon(RobotMap.backRight);

    public static AxialAcceleration axialAcceleration = new AxialAcceleration();
    public static LateralAcceleration lateralAcceleration = new LateralAcceleration();
    public static RotationalAcceleration rotationalAcceleration = new RotationalAcceleration();

    public static void initializeTalons() {
        frontLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
        frontRight.changeControlMode(CANTalon.TalonControlMode.Speed);
        backLeft.changeControlMode(CANTalon.TalonControlMode.Speed);
        backRight.changeControlMode(CANTalon.TalonControlMode.Speed);
        
        frontLeft.setCurrentLimit(RobotMap.maxCurrent);
        frontLeft.EnableCurrentLimit(true);
        
        frontRight.setCurrentLimit(RobotMap.maxCurrent);
        frontRight.EnableCurrentLimit(true);
        
        backLeft.setCurrentLimit(RobotMap.maxCurrent);
        backLeft.EnableCurrentLimit(true);
        
        backRight.setCurrentLimit(RobotMap.maxCurrent);
        backRight.EnableCurrentLimit(true);
    }
    
    public static double getAmps() {
    	return frontRight.getOutputCurrent();
    }

    public static void setTalonPercents(WheelConfiguration wheelConfiguration) {
        frontLeft.set(wheelConfiguration.getFrontLeft());
        frontRight.set(wheelConfiguration.getFrontRight());
        backLeft.set(wheelConfiguration.getBackLeft());
        backRight.set(wheelConfiguration.getBackRight());
    }

    public static void sendAngle(double angle) {
        switch (mode) {
            case MODE_AXIAL:
            	try {
                    axialAcceleration.addAngle(angle);
            	} catch (Exception e) {
            		
            	}
                break;
            case MODE_LATERAL:
            	try {
                    lateralAcceleration.accelerate(angle);
            	} catch (Exception e) {
            		
            	}
                break;
            default:
                break;
        }
    }

    public static boolean stopMode() {
        boolean stop = stop();
        if (stop) {
            axialAcceleration.clear();
            lateralAcceleration.clear();
        }
        return stop;
    }

    private static boolean stop() {
        switch (mode) {
            case MODE_AXIAL:
                return axialAcceleration.decelerate(0);
            case MODE_LATERAL:
                return lateralAcceleration.decelerate(0);
            case MODE_ROTATIONAL:
                return rotationalAcceleration.decelerate(0);
            default:
                return true;
        }
    }
}
