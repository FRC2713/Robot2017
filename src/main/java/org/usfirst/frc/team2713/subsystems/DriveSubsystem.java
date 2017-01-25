package org.usfirst.frc.team2713.subsystems;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.OIDrive;

public class DriveSubsystem extends Subsystem {
	private Robot robot = Robot.getRobot();
	private CANTalon topLeft;
	private CANTalon topRight;
	private CANTalon bottomLeft;
	private CANTalon bottomRight;
	public RobotDrive roboDrive;

	private boolean reversed = false;

	private boolean lastButtonState = false; // true = pressed, false = not pressed
	private boolean isPressed = false;

	public DriveSubsystem() {
		topLeft = new CANTalon(RobotMap.TOP_LEFT);
		topRight = new CANTalon(RobotMap.TOP_RIGHT);
		bottomLeft = new CANTalon(RobotMap.BOTTOM_LEFT);
		bottomRight = new CANTalon(RobotMap.BOTTOM_RIGHT);

		bottomLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomLeft.set(RobotMap.TOP_LEFT);

		bottomRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomRight.set(RobotMap.TOP_RIGHT);

		roboDrive = new RobotDrive(topLeft, topRight);
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void startTeleop(){
		new OIDrive(this, roboDrive).start();
	}

	public void tankDrive(double left, double right, double deadband, boolean checkReverseButton) {
		int multiplier = 1;
		checkReverser();
		if (checkReverseButton && reversed){ multiplier = -1; }
		roboDrive.tankDrive(getDeadband(left, deadband) * multiplier, getDeadband(right, deadband) * multiplier);
	}

	private void checkReverser(){
		// WPILib wants you to use a command for button triggers, but nah
		if (Robot.getOI().getController().getBButton()){
			lastButtonState = isPressed;
			isPressed = true;
		} else if (!Robot.getOI().getController().getBButton()){
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

	public enum DriveModes{
		tank, arcade, rocketleague
	}
}
