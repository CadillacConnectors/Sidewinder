package org.usfirst.frc.team5086.robot.autonomous;

import org.usfirst.frc.team5086.robot.subsystems.DriveSubsystem;
import org.usfirst.frc.team5086.robot.subsystems.OtherSubsystem;

/**
 * Created by joshua on 3/9/17.
 */
public class CommandInterface {

    private long start;
    public static final int ALLIANCE_BLUE = 0;
    public static final int ALLIANCE_RED = 1;

    public CommandInterface(long start) {
        this.start = start;
    }

    public void noAction(long time) {
        DriveSubsystem.stop();
    }

    public void forward(long time) {
        if (time < 1000 + start) {
            DriveSubsystem.axialMovement(-.95);
        } else if (time < 3700 + start) {
            DriveSubsystem.axialMovement(-.65);
        } else {
            DriveSubsystem.axialMovement(0);
        }
    }

    public void shoot(long time, int alliance) {
        assert (alliance == ALLIANCE_BLUE || alliance == ALLIANCE_RED);
        if (alliance == ALLIANCE_BLUE) {
            if (time < 1000 + start) {
                OtherSubsystem.launcherMovement(310);
            } else if (time < 11000 + start) {
                OtherSubsystem.launcherMovement(310);
                OtherSubsystem.ballpedal(-.5);
            } else if (time < 12000 + start) {
                OtherSubsystem.launcherMovement(0);
                OtherSubsystem.ballpedal(0);
                DriveSubsystem.lateralMovement(-.5);
            } else if (time < 13000 + start) {
                DriveSubsystem.axialMovement(.8);
            } else if (time < 14500 + start) {
                DriveSubsystem.lateralMovement(-.5);
            } else {
                DriveSubsystem.axialMovement(0);
            }
        } else if (alliance == ALLIANCE_RED) {
            if (time < 1000 + start) {
                OtherSubsystem.launcherMovement(310);
            } else if (time < 11000 + start) {
                OtherSubsystem.launcherMovement(310);
                OtherSubsystem.ballpedal(-.5);
            } else if (time < 12000 + start) {
                OtherSubsystem.launcherMovement(0);
                OtherSubsystem.ballpedal(0);
                DriveSubsystem.axialMovement(.8);
            } else if (time < 12500 + start) {
                DriveSubsystem.lateralMovement(.5);
            } else if (time < 14000 + start) {
                DriveSubsystem.axialMovement(.8);
            } else {
                DriveSubsystem.lateralMovement(0);
            }        }
    }

    public void right(long time) {
        if (time < 2000 + start) {
            DriveSubsystem.lateralMovement(.5);
        } else {
            DriveSubsystem.axialMovement(0);
        }
    }
}
