package org.usfirst.frc.team5086.robot.subsystems.acceleration;

import org.usfirst.frc.team5086.robot.RobotMap;

/**
 * Created by joshua on 3/11/17.
 */
public abstract class Accelerator {
    public static double decelerate(double min, double current) {
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
}
