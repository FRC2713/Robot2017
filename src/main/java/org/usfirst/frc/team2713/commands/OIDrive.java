package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.subsystems.DriveSubsystem;

public class OIDrive extends Command {
	private RobotDrive roboDrive;
	DriveSubsystem drive;
	XboxController xbox = Robot.getOI().getController();
	private double scaler = 1.0;
	private double deadband = 0.1;
	private double polarity = -1;

	public OIDrive(DriveSubsystem drive, RobotDrive roboDrive) {
		this.drive = drive;
		this.roboDrive = roboDrive;
		requires(drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		if (RobotMap.OIDriveMode.getSelected() == DriveSubsystem.DriveModes.tank){
			drive.tankDrive(xbox.getY(Hand.kLeft)*scaler*polarity, xbox.getY(Hand.kRight)*scaler*polarity, deadband, true);
		} else if (RobotMap.OIDriveMode.getSelected() == DriveSubsystem.DriveModes.ryanDrive){
			double speed = drive.getDeadband(xbox.getY(Hand.kLeft));
			double rotation = drive.getDeadband(xbox.getX(Hand.kRight));
			drive.arcadeDrive(speed*scaler*polarity, rotation*scaler*polarity, deadband, true);
		} else if (RobotMap.OIDriveMode.getSelected() == DriveSubsystem.DriveModes.rocketleague){
			double speed;
			if(xbox.getTrigger(Hand.kRight) && !xbox.getTrigger(Hand.kLeft)){
				speed = drive.getDeadband(xbox.getTriggerAxis(Hand.kRight));
			}else if(xbox.getTrigger(Hand.kLeft) && !xbox.getTrigger(Hand.kRight)){
				speed = -drive.getDeadband(xbox.getTriggerAxis(Hand.kLeft));
			}else{
				speed = 0;
			}
			double rotation = drive.getDeadband(xbox.getX((Hand.kLeft)));
			drive.arcadeDrive(speed*scaler*polarity, rotation*scaler*polarity, deadband, false);
		}
	}

	@Override
	protected boolean isFinished(){ return false; }

	@Override
	protected void end(){}

	@Override
	protected void interrupted(){}
}
