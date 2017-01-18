package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.input.controller.XBoxController;
import org.usfirst.frc.team2713.subsystems.DriveSubsystem;

public class OIDrive extends Command {

	private RobotDrive roboDrive;
	DriveSubsystem drive;
	XBoxController xbox = Robot.getOI().getController();
	private double scaler = 1.0;
	private double deadband = 0.1;
	private double polarity = -1;
	private boolean reversed = false;

	private boolean lastButtonState = false; // true = pressed, false = not pressed
	private boolean isPressed = false;

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
		if (RobotMap.DRIVE_MODE == DriveSubsystem.DriveModes.tank){
			tankDrive(xbox.getLeftY()*scaler*polarity, xbox.getRightX()*scaler*polarity, deadband, true);
		} else if (RobotMap.DRIVE_MODE == DriveSubsystem.DriveModes.arcade){
			throw new UnsupportedOperationException();
			//TODO Satisfy Ryan's urges and implement an Arcade Drive
		}
	}

	private void tankDrive(double left, double right, double deadband, boolean checkReverseButton) {
		int multiplier = 1;
		checkReverser();
		if (checkReverseButton && reversed){ multiplier = -1; }
		roboDrive.tankDrive(getDeadband(left, deadband) * multiplier, getDeadband(right, deadband) * multiplier);
	}

	private void checkReverser(){
		// WPILib wants you to use a command for button triggers, but nah 
		if (Robot.getOI().getController().getButtonB()){
			lastButtonState = isPressed;
			isPressed = true;
		} else if (!Robot.getOI().getController().getButtonB()){
			lastButtonState = isPressed;
			isPressed = false;
		}
		if (isPressed && !lastButtonState){
			reversed = !reversed;
		}
	}

	private static double getDeadband(double value, double deadband) {
		int sign = (value > 0 ? 1 : -1); // Check if value is + or -
		value = Math.abs(value); // Change value to Positive
		if (value <= deadband) {
			return 0.0; // returns 0 if it is less than deadband
		} else {
			return (value - deadband) * sign; // returns value minus deadband
		}

	}
	@Override
	protected boolean isFinished(){ return false; }

	@Override
	protected void end(){}

	@Override
	protected void interrupted(){}
}
