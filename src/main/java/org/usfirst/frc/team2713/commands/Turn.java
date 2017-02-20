package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
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
		TalonOutput output = new TalonOutput(robot.getDrive().getLeftTalon(), robot.getDrive().getRightTalon());
		pid = new PIDController(0.1, 0, 0, robot.getDrive().getGyro(), output); // TODO: Tune PID
		pid.setOutputRange(-0.25D, 0.25D);
		pid.setAbsoluteTolerance(tolerance);

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
			right.pidWrite(-output);
		}
	}
}
