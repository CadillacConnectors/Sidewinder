package org.usfirst.frc.team5086.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick drive = new Joystick(RobotMap.joystickDrive);
	Joystick other = new Joystick(RobotMap.joystickOther);
}