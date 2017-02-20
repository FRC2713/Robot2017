package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.subsystems.DriveSubsystem;

public class OIDrive extends Command {
	DriveSubsystem drive;
	XboxController xbox = Robot.getOI().getController();
	private double scaler = 1.0;
	private double deadband = 0.1;
	private double polarity = -1;

	public OIDrive(DriveSubsystem drive) {
		this.drive = drive;
		requires(drive);
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		switch ((DriveSubsystem.DriveModes) RobotMap.OIDriveMode.getSelected()) {
			case tank:
				drive.tankDrive(xbox.getY(Hand.kLeft) * scaler * polarity, xbox.getY(Hand.kRight) * scaler * polarity, deadband, true);
				break;
			case therian:
				double speed = drive.getDeadband(xbox.getY(Hand.kLeft));
				double rotation = drive.getDeadband(xbox.getX(Hand.kRight));
				drive.arcadeDrive(speed * scaler * polarity, rotation * scaler * polarity, deadband, true);
				break;
			case arcade:
				speed = drive.getDeadband(xbox.getY(Hand.kLeft));
				rotation = drive.getDeadband(xbox.getX(Hand.kLeft));
				drive.arcadeDrive(speed * scaler * polarity, rotation * scaler * polarity, deadband, true);
				break;
			case rocketleague:
				if (xbox.getTriggerAxis(Hand.kRight) != 0 && xbox.getTriggerAxis(Hand.kLeft) == 0) {
					speed = -drive.getDeadband(xbox.getTriggerAxis(Hand.kRight));
				} else if (xbox.getTriggerAxis(Hand.kLeft) != 0 && xbox.getTriggerAxis(Hand.kRight) == 0) {
					speed = drive.getDeadband(xbox.getTriggerAxis(Hand.kLeft));
				} else {
					speed = 0;
				}
				rotation = drive.getDeadband(xbox.getX(Hand.kLeft));
				drive.arcadeDrive(speed * scaler * polarity, (rotation * -speed) * scaler * polarity, deadband, true);
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}
}
