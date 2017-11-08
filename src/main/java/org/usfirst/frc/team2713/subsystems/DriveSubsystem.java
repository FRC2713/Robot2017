package org.usfirst.frc.team2713.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.CANTalon;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.OIDrive;
import org.usfirst.frc.team2713.commands.VisionAlign;

public class DriveSubsystem extends Subsystem {
	private CANTalon topLeft = new CANTalon(RobotMap.TOP_LEFT); // Has an e4p360250 encoder
	private CANTalon topRight = new CANTalon(RobotMap.TOP_RIGHT); // And me!
	private CANTalon bottomLeft = new CANTalon(RobotMap.BOTTOM_LEFT);
	private CANTalon bottomRight = new CANTalon(RobotMap.BOTTOM_RIGHT);
	public RobotDrive roboDrive;

	private ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	private boolean reversed = false;

	private boolean lastButtonState = false; // true = pressed, false = not pressed
	private boolean isPressed = false;

	public DriveSubsystem() {
		bottomLeft.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomLeft.set(RobotMap.TOP_LEFT);

		bottomRight.changeControlMode(CANTalon.TalonControlMode.Follower);
		bottomRight.set(RobotMap.TOP_RIGHT);

		JoystickButton driveStart = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 5); // LB
		JoystickButton visionAlign = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 6); // RB

		driveStart.whenPressed(new OIDrive(this));
		visionAlign.whenPressed(new VisionAlign(this));

		resetEncoders();
	}

	@Override
	protected void initDefaultCommand() {

	}

	public void startTeleop() {
		roboDrive = new RobotDrive(topLeft, topRight);
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

		topLeft.configEncoderCodesPerRev(360); // As per https://tinyurl.com/jt5u24u
		topRight.configEncoderCodesPerRev(360);
	}

	public CANTalon getLeftTalon() {
		return topLeft;
	}

	public CANTalon getRightTalon() {
		return topRight;
	}

	public CANTalon getBottomRightTalon() {
		return bottomRight;
	}
	
	public double getInchesPerSecond(CANTalon talon){
		// TODO What is EncVelocity measured in?
		// Gearbox: https://tinyurl.com/yb6l4o6n
		
		final int CYCLES_PER_REVOLUTION = 360;
		final int PULSES_PER_REVOLUTION = 1440;
		
		double origVelocity = talon.getEncVelocity();
		double origMillisecond = System.currentTimeMillis();
		
		//TODO SLEEP 10ms
		
		double newVelocity = talon.getEncVelocity();
		double newMillisecond = System.currentTimeMillis();
		
		double accel = (newVelocity - origVelocity) / (newMillisecond - origMillisecond);
		return accel / PULSES_PER_REVOLUTION * RobotMap.WHEEL_CIRCUMFERENCE;
	}

	public ADXRS450_Gyro getGyro() {
		return gyro;
	}

	public PIDController createGyroPidController(double tolerance) {
		PIDController pid;

		TalonOutput output = new TalonOutput(getLeftTalon(), getRightTalon());
		pid = new PIDController(0.025, 0, 0, getGyro(), output); // TODO: Tune PID
		pid.setOutputRange(-0.25D, 0.25D);
		pid.setAbsoluteTolerance(tolerance);

		return pid;
	}

	class TalonOutput implements PIDOutput {
		private CANTalon left;
		private CANTalon right;

		private TalonOutput(CANTalon left, CANTalon right) {
			this.left = left;
			this.right = right;
		}

		@Override
		public void pidWrite(double output) {
			left.pidWrite(output);
			right.pidWrite(output);
		}
	}

	public enum DriveModes {
		TANK, ARCADE, ROCKETLEAGUE, BRADFORD
	}
}
