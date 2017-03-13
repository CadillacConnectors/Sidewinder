package org.usfirst.frc.team5086.robot.subsystems.drive.objects;

/**
 * Created by joshua on 3/11/17.
 */
public class WheelConfiguration {

    private double frontLeft;
    private double frontRight;
    private double backLeft;
    private double backRight;

    public WheelConfiguration() {
        this(0);
    }

    public WheelConfiguration(double speed) {
        frontLeft = speed;
        frontRight = speed;
        backLeft = speed;
        backRight = speed;
    }

    public double getBackLeft() {
        return backLeft;
    }

    public double getBackRight() {
        return backRight;
    }

    public double getFrontLeft() {
        return frontLeft;
    }

    public double getFrontRight() {
        return frontRight;
    }

    public void setBackLeft(double backLeft) {
        this.backLeft = backLeft;
    }

    public void setBackRight(double backRight) {
        this.backRight = backRight;
    }

    public void setFrontLeft(double frontLeft) {
        this.frontLeft = frontLeft;
    }

    public void setFrontRight(double frontRight) {
        this.frontRight = frontRight;
    }

    public void merge(WheelConfiguration configuration) {
        this.frontLeft = frontLeft * configuration.getFrontLeft();
        this.frontRight = frontRight * configuration.getFrontRight();
        this.backLeft = backLeft * configuration.getBackLeft();
        this.backRight = backRight * configuration.getBackRight();
    }

    public void reset(double speed) {
        this.setFrontRight(speed);
        this.setBackRight(speed);
        this.setBackLeft(speed);
        this.setFrontLeft(speed);
    }

}
