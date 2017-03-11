package org.usfirst.frc.team5086.robot.subsystems.acceleration;

import org.usfirst.frc.team5086.robot.Robot;
import org.usfirst.frc.team5086.robot.RobotMap;

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
    private int[] correctionReduction = new int[4];

    public boolean decelerate(double min) {
        double lateral = this.lateral;
        this.lateral = decelerate(min, lateral);
        return (this.lateral == lateral);
    }

    @Override
    public boolean accelerate(double max) {
        if (Math.abs(lateral) < Math.abs(max)) {

        }
        return true;
    }

    @Override
    public int[] getTalonConfiguration(int max) {
        return new int[0];
    }

    @Override
    public int[] getVictorConfiguration() {
        return new int[0];
    }

    @Override
    public void addAngle(double angle) {

    }

    @Override
    public void calibrateDrive() {

    }

    @Override
    public void clear() {
        angles.clear();
        correcting.clear();
    }

    @Override
    public void setAngle(double angle) {

    }

}
