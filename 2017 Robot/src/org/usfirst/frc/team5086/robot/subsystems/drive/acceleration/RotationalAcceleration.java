package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Josh on 3/26/2017.
 */
public class RotationalAcceleration extends Accelerator implements AccelerationMode {
    private double rotational = 0;

    @Override
    public boolean decelerate(double min) {
        double rotational = this.rotational;
        this.rotational = decelerate(min, rotational);
        return (this.rotational == rotational);
    }

    @Override
    public boolean accelerate(double max) {
        double rotational = this.rotational;
        this.rotational = accelerate(max, rotational);
        return (this.rotational == rotational);
    }

    @Override
    public WheelConfiguration getTalonConfiguration(int max) {
        WheelConfiguration values = getVictorConfiguration();
        values.merge(new WheelConfiguration(max));
        return values;
    }

    @Override
    public WheelConfiguration getVictorConfiguration() {
        WheelConfiguration values = new WheelConfiguration();

        values.setBackLeft(rotational);
        values.setBackRight(rotational);
        values.setFrontLeft(rotational);
        values.setFrontRight(rotational);

        return values;
    }
}
