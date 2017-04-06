package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.subsystems.DriveSubsystem;

public class VisionAlign extends Command {
	private NetworkTable table;
	private boolean withinThreshold;
	private long startTime;

	private PIDController pid;

	public VisionAlign(DriveSubsystem drive) {
		requires(drive);
		table = NetworkTable.getTable("VisionProcessing");
	}

	@Override
	protected void initialize() {
		pid = Robot.getRobot().getDrive().createGyroPidController(RobotMap.VISION_ANGLE_TOLERANCE);
		withinThreshold = false;
		startTime = System.currentTimeMillis();
	}

	@Override
	protected void interrupted() {
		withinThreshold = false;
		table.putNumber("status", 0);
		end();
	}

	@Override
	protected void end() {
		pid.disable();
	}

	@Override
	protected void execute() {
		//System.out.println("Current angle: " + Robot.getRobot().getDrive().getGyro().getAngle());
		if (!pid.isEnabled()) {
			switch ((int) table.getNumber("status", 1)) {
				case 0: // Doing nothing
					table.putNumber("status", 1); // Request angle
					break;
				case 2: // Angle found
					double correctionAngle = table.getNumber("correctionAngle", 0);
					table.putNumber("status", 0);

					if (Math.abs(correctionAngle) <= RobotMap.VISION_ANGLE_TOLERANCE) {
						withinThreshold = true;
						return;
					}

					pid.setSetpoint(Robot.getRobot().getDrive().getGyro().getAngle() + correctionAngle);
					System.out.println("Goal: " + (Robot.getRobot().getDrive().getGyro().getAngle() + correctionAngle) + " (correction " + correctionAngle + ")");
					pid.enable();
					break;
			}
		}
	}

	@Override
	protected boolean isFinished() {
		return pid.onTarget() || System.currentTimeMillis() - startTime >= 3000;
	}
}
