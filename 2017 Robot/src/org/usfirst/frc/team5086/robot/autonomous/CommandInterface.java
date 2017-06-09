package org.usfirst.frc.team5086.robot.autonomous;

import org.usfirst.frc.team5086.robot.subsystems.drive.DriveSubsystem;
import org.usfirst.frc.team5086.robot.subsystems.OtherSubsystem;

/**
 * Created by Tucker on 6/21/02.
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
        if (time < 500 + start) {
            DriveSubsystem.axialMovement(.95, 0);
        } else if (time < 2600 + start) {
            DriveSubsystem.axialMovement(.55, 0);
        } else {
            DriveSubsystem.stop();
        }
    }

    public void shoot(long time, int alliance) {
        assert (alliance == ALLIANCE_BLUE || alliance == ALLIANCE_RED);
        if (alliance == ALLIANCE_BLUE) {
            if (time < 1000 + start) {
                OtherSubsystem.launcherMovement(310);
            } else if (time < 10500 + start) {
                OtherSubsystem.launcherMovement(310);
                OtherSubsystem.ballpedal(-.5);
            } else if (time < 11500 + start) {
                OtherSubsystem.launcherMovement(0);
                OtherSubsystem.ballpedal(0);
                DriveSubsystem.axialMovement(-.8, 0);
            } else if (time < 12500 + start) {
                DriveSubsystem.lateralMovement(-.5, 0);
            } else if (time < 14000 + start) {
                DriveSubsystem.axialMovement(-.8, 0);
            } else if (time < 14800 + start) {
            	DriveSubsystem.turnMovement(.75);
            } else {
                DriveSubsystem.stop();
            }
        } else if (alliance == ALLIANCE_RED) {
            if (time < 1000 + start) {
                OtherSubsystem.launcherMovement(310);
            } else if (time < 10500 + start) {
                OtherSubsystem.launcherMovement(310);
                OtherSubsystem.ballpedal(-.5);
            } else if (time < 11500 + start) {
                OtherSubsystem.launcherMovement(0);
                OtherSubsystem.ballpedal(0);
                DriveSubsystem.axialMovement(-.8, 0);
            } else if (time < 12500 + start) {
                DriveSubsystem.lateralMovement(.5, 0);
            } else if (time < 14000 + start) {
                DriveSubsystem.axialMovement(-.8, 0);
            } else if (time < 14800 + start) {
            	DriveSubsystem.turnMovement(.75);
            } else {
                DriveSubsystem.stop();
            }        }
    }

    public void right(long time) {
        if (time < 2000 + start) {
            DriveSubsystem.lateralMovement(-.5);
        } else {
            DriveSubsystem.axialMovement(0);
        }
    }
}
