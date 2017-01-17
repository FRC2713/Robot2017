package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team2713.commands.OIDrive;
import org.usfirst.frc.team2713.subsystems.DriveSubsystem;

public class Robot extends IterativeRobot {
    private static Robot robotInstance;
    private static OI oi;

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    @Override
    public void robotInit() {
        robotInstance = this;
        oi = new OI();

        DriveSubsystem ds = new DriveSubsystem();
        chooser.addDefault("Operator Drive Mode", new OIDrive(ds, ds.roboDrive));
        SmartDashboard.putData("Auto mode", chooser);
    }

    @Override
    public void disabledInit() {
        robotInstance = null;
        oi = null;
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        if (autonomousCommand != null) autonomousCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }

    public static Robot getRobot(){ return robotInstance; }

    public static OI getOI(){ return oi; }
}
