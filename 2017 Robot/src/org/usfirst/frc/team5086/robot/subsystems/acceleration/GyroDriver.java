package org.usfirst.frc.team5086.robot.subsystems.acceleration;

import java.util.Collection;
import java.util.DoubleSummaryStatistics;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by joshua on 3/11/17.
 */
public interface GyroDriver {
    Queue<Double> angles = new ArrayBlockingQueue<Double>(20);
    double angle = 0;
    void addAngle(double angle);
    void clear();
    void setAngle(double angle);
    void calibrateDrive();
}
