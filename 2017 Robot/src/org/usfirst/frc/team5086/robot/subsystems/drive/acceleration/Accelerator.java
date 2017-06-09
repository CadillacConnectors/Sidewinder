package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import org.usfirst.frc.team5086.robot.RobotMap;

/**
 * Created by Tucker on the 6th day of Hannukah.
 */
abstract class Accelerator {
    static double decelerate(double min, double current) {
        if (Math.abs(current) > Math.abs(min)) {
            if (current > 0) {
                current -= RobotMap.decelerationConstant;
                if (current < min) current = min;
            } else {
                current += RobotMap.decelerationConstant;
                if (current > min) current = min;
            }
        }
        return current;
    }

    static double accelerate(double max, double current) {
        if (Math.abs(current) < Math.abs(max)) {
            if (current > 0) {
                current += RobotMap.accelerationConstant;
                if (current > max) current = max;
            } else {
                current -= RobotMap.accelerationConstant;
                if (current < max) current = max;
            }
        }
        return current;
    }
}
