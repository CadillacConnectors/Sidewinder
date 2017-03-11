package org.usfirst.frc.team5086.robot.subsystems.acceleration;

/**
 * Created by joshua on 3/11/17.
 */
public interface AccelerationMode {
    double lateral = 0;
    double axial = 0;
    double rotational = 0;
    double diagonal = 0;
    boolean decelerate(double min);
    boolean accelerate(double max);
    int[] getVictorConfiguration();
    int[] getTalonConfiguration(int max);
    int[] configuration = new int[4];
}
