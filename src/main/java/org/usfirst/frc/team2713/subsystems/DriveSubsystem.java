package org.usfirst.frc.team2713.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.OIDrive;

public class DriveSubsystem extends Subsystem {
	private CANTalon topLeft = new CANTalon(RobotMap.TOP_LEFT); // Has an e4p360250 encoder
	private CANTalon topRight = new CANTalon(RobotMap.TOP_RIGHT); // And me!
	private CANTalon bottomLeft = new CANTalon(RobotMap.BOTTOM_LEFT);
	private CANTalon bottomRight = new CANTalon(RobotMap.BOTTOM_RIGHT);
	private RobotDrive roboDrive;

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	private boolean reversed = false;

	private boolean lastButtonState = false; // true = pressed, false = not pressed
	private boolean isPressed = false;

	public DriveSubsystem() {
		bottomLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomLeft.set(RobotMap.TOP_LEFT);
		
		bottomRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomRight.set(RobotMap.TOP_RIGHT);

		resetEncoders();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void startTeleop() {
		roboDrive = new RobotDrive(topLeft, topRight);
		//roboDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true); // This is actually the front.
		new OIDrive(this).start();
	}

	public void tankDrive(double left, double right, double deadband, boolean checkReverseButton) {
		int multiplier = getMultiplier(checkReverseButton);
		roboDrive.tankDrive(getDeadband(left, deadband) * multiplier, getDeadband(right, deadband) * multiplier);
	}

	public void arcadeDrive(double speed, double rotation, double deadband, boolean checkReverseButton) {
		int multiplier = getMultiplier(checkReverseButton);
		roboDrive.arcadeDrive(getDeadband(speed, deadband) * multiplier, getDeadband(rotation, deadband));
	}

	private int getMultiplier(boolean checkReverse) {
		checkReverser();
		return checkReverse && reversed ? -1 : 1;
	}

	private void checkReverser() {
		// WPILib wants you to use a command for button triggers, but nah
		if (Robot.getOI().getController().getBButton()) {
			lastButtonState = isPressed;
			isPressed = true;
		} else if (!Robot.getOI().getController().getBButton()) {
			lastButtonState = isPressed;
			isPressed = false;
		}
		if (isPressed && !lastButtonState) {
			reversed = !reversed;
			new Thread(() -> {
				Robot.getOI().getController().setRumble(GenericHID.RumbleType.kLeftRumble, 1);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// Nuthin'
				}
				Robot.getOI().getController().setRumble(GenericHID.RumbleType.kLeftRumble, 0);
			}).start();
		}
	}

	public double getDeadband(double value, double deadband) {
		int sign = (value > 0 ? 1 : -1); // Check if value is + or -
		value = Math.abs(value); // Change value to Positive
		if (value <= deadband) {
			return 0.0; // returns 0 if it is less than deadband
		} else {
			return (value - deadband) * sign; // returns value minus deadband
		}
	}

	public double getDeadband(double value) {
		return getDeadband(value, 0.01);
	}

	public void resetEncoders() {
		// 1 (c=6) rotation, ~1440 ticks/rotation, ~18.8496in
		topLeft.setEncPosition(0);
		topRight.setEncPosition(0);

		topLeft.configEncoderCodesPerRev(1440); // As per https://tinyurl.com/jt5u24u
		topRight.configEncoderCodesPerRev(1440);
	}

	public CANTalon getLeftTalon() {
		return topLeft;
	}

	public CANTalon getRightTalon() {
		return topRight;
	}

	public ADXRS450_Gyro getGyro() {
		return gyro;
	}

	public enum DriveModes {
		tank, arcade, rocketleague, therian
	}
}
