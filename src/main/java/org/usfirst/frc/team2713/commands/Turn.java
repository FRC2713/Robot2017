package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;

public class Turn extends Command {
	private Robot robot = Robot.getRobot();

	private PIDController pid;
	private double angle;
	private double tolerance;

	public Turn(double angle, double tolerance) {
		requires(robot.getDrive());

		angle %= 360D;
		this.angle = angle;
		this.tolerance = tolerance;
	}

	@Override
	protected void initialize() {
		pid = Robot.getRobot().getDrive().createGyroPidController(tolerance);

		pid.setSetpoint(robot.getDrive().getGyro().getAngle() + angle);
		pid.enable();
	}

	@Override
	protected void end() {
		pid.disable();
	}

	@Override
	protected boolean isFinished() {
		return pid.onTarget();
	}
}
