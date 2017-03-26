package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

/**
 * Created by joshua on 3/11/17.
 */
public interface AccelerationMode {
    boolean decelerate(double min);
    boolean accelerate(double max);
    WheelConfiguration getVictorConfiguration();
    WheelConfiguration getTalonConfiguration(int max);
    int[] configuration = new int[4];
}
