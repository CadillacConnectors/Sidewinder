package org.usfirst.frc.team5086.robot.subsystems.drive.acceleration;

import org.usfirst.frc.team5086.robot.subsystems.drive.objects.WheelConfiguration;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by Josh on 3/25/2017.
 */
public class AxialAcceleration extends Accelerator implements GyroDriver, AccelerationMode {
    private double axial = 0;
    private Queue<Double> angles = new ArrayBlockingQueue<Double>(20);
    private Queue<Boolean> correcting = new ArrayBlockingQueue<Boolean>(20);
    private WheelConfiguration correctionReduction = new WheelConfiguration(1);
    private double angle = 0;

    @Override
    public boolean decelerate(double min) {
        double axial = this.axial;
        this.axial = decelerate(min, axial);
        return (this.axial == axial);
    }

    @Override
    public boolean accelerate(double max) {
        double axial = this.axial;
        this.axial = accelerate(max, axial);
        return (this.axial == axial);
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

        values.setBackLeft(axial);
        values.setBackRight(-axial);
        values.setFrontLeft(axial);
        values.setFrontRight(-axial);

        values.merge(correctionReduction);

        return values;
    }

    @Override
    public void addAngle(double angle) {
        if (angles.size() == 20) angles.remove();
        angles.add(angle);
        if ((Math.abs(Math.abs(angles.peek()) - Math.abs(this.angle)) > .05 && !correcting.peek()) || ((Math.abs(Math.abs(angles.peek()) - Math.abs(this.angle)) > .2))) {
            calibrateDrive(angles.peek() - angle);
            if (correcting.size() == 20) correcting.remove();
            correcting.add(true);
            calibrateDrive(angles.peek() - this.angle);
        } else {
            correcting.add(true);
        }
    }

    private void calibrateDrive(double angle) {
        angle *= 5;
        if (angle > 0) {
            //To far right
            if (correctionReduction.getFrontRight() <= (1 - angle)) {
                correctionReduction.setFrontRight(correctionReduction.getFrontRight() + angle);
                correctionReduction.setFrontLeft(correctionReduction.getFrontLeft() + angle);
            } else {
                correctionReduction.setBackLeft(correctionReduction.getBackLeft() - angle);
                correctionReduction.setBackRight(correctionReduction.getBackRight() - angle);
            }
        } else {
            //To far left
            if (correctionReduction.getBackRight() <= (1 - angle)) {
                correctionReduction.setBackLeft(correctionReduction.getBackLeft() + angle);
                correctionReduction.setBackRight(correctionReduction.getBackRight() + angle);
            } else {
                correctionReduction.setFrontRight(correctionReduction.getFrontRight() - angle);
                correctionReduction.setFrontLeft(correctionReduction.getFrontLeft() - angle);
            }
        }
    }

    @Override
    public void clear() {
        angles.clear();
        correcting.clear();
    }

    @Override
    public void setAngle(double angle) {
        this.angle = angle;
    }
}
