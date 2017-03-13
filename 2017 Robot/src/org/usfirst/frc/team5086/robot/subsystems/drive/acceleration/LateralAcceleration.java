package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Acceleration-based Chassis control system for lateral (Left / Right) movements
 *
 * Also uses a gyrometer to control orientation
 *
 * Created: 3/11/2017
 *
 * @author Joshua Jacobson (joshuthomasjacobson@gmail.com)
 * @version 1.0
 */
public class LateralAcceleration extends Accelerator implements AccelerationMode, GyroDriver {
    private double lateral = 0;
    private Queue<Double> angles = new ArrayBlockingQueue<Double>(20);
    private Queue<Boolean> correcting = new ArrayBlockingQueue<Boolean>(20);
    private WheelConfiguration correctionReduction = new WheelConfiguration(1);

    @Override
    public boolean decelerate(double min) {
        double lateral = this.lateral;
        this.lateral = decelerate(min, lateral);
        return (this.lateral == lateral);
    }

    @Override
    public boolean accelerate(double max) {
        double lateral = this.lateral;
        this.lateral = accelerate(max, lateral);
        return (this.lateral == lateral);
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

        values.setBackLeft(-lateral);
        values.setBackRight(-lateral);
        values.setFrontLeft(lateral);
        values.setFrontRight(lateral);

        values.merge(correctionReduction);

        return values;
    }

    @Override
    public void addAngle(double angle) {
        if (angles.size() == 20) angles.remove();
        angles.add(angle);
    }

    @Override
    public void calibrateDrive() {

    }

    @Override
    public void clear() {
        angles.clear();
        correcting.clear();
        correctionReduction.reset(1);
    }

    @Override
    public void setAngle(double angle) {

    }

}
