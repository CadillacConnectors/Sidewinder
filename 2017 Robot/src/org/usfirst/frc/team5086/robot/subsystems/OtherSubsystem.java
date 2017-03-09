package org.usfirst.frc.team5086.robot.subsystems;

import org.usfirst.frc.team5086.robot.Robot;
import org.usfirst.frc.team5086.robot.RobotMap;

import com.ctre.CANTalon;
import com.ctre.CANTalon.FeedbackDevice;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class OtherSubsystem extends Subsystem {

	//creating motor controllers
	static Victor launcherVictor = new Victor(RobotMap.launcher);
	static Victor sweeperVictor = new Victor(RobotMap.sweeper);
	static Victor ballpedalVictor = new Victor(RobotMap.ballpedal);
	static Victor winch = new Victor(RobotMap.winch);
	static CANTalon launcher = new CANTalon(0);
	
    public void initDefaultCommand() {
    }

    //launcher encoder
	public static void launcherMovement (double speed) {
		launcher.changeControlMode(TalonControlMode.Speed);
		launcher.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		/*launcher.setF(0.04833);
		launcher.setP(0.001);
		launcher.setI(0);
		launcher.setD(0);*/
		//launcher.configMaxOutputVoltage(12);
		//launcher.configPeakOutputVoltage(12.0f, -12.0f);
		//MotionProfileExample mp = new MotionProfileExample(launcher);
		//CANTalon.SetValueMotionProfile setOutput = new MotionProfileExample(launcher).getSetValue();
		//System.out.println(launcher.getEncVelocity());
		//System.out.println(launcher.get());
		launcher.set(speed);
	}
	
	//sweeper
	public static void sweeperMovement(double otheraxis2) {
		if (otheraxis2 > 0) {
			sweeperVictor.set(otheraxis2);
		} else {
			sweeperVictor.set(0);	
		}
	}

	//ball pedal
	public static void ballpedal (double speed) {
		ballpedalVictor.set(speed);
	}
	
	//winch
    public static void winchmovement(double otheraxis3){
    	if (otheraxis3 > 0) {
    		winch.set(otheraxis3);	
    	} else {
    		winch.set(0);
    	}
    }
}