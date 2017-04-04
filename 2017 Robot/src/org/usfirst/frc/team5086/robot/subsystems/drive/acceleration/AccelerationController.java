package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import com.ctre.CANTalon;
import org.usfirst.frc.team5086.robot.RobotMap;
import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

/**
 * Created by joshua on 3/11/17.
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
    }

    public static void setTalonPercents(WheelConfiguration wheelConfiguration) {
        frontLeft.set(wheelConfiguration.getFrontLeft());
        frontRight.set(wheelConfiguration.getFrontRight());
        backLeft.set(wheelConfiguration.getBackLeft());
        backRight.set(wheelConfiguration.getBackRight());
    }

    public static boolean stopMode() {
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
