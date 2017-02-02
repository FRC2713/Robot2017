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
	
	public Turn(double angle) {
		requires(robot.getDrive());
		
		angle %= 360D;
		this.angle = angle;
	}
	
	@Override
	protected void initialize() {
		TalonOutput output = new TalonOutput(robot.getDrive().getLeftTalon(), robot.getDrive().getRightTalon());
		pid = new PIDController(0.1, 0.0001, 0.01, robot.getDrive().getIMU(), output); // TODO: Tune PID
		pid.setContinuous();
		pid.setInputRange(-180D, 180D);
		pid.setOutputRange(-1D, 1D);
		pid.setAbsoluteTolerance(0.5D);
		
		double newAngle = (robot.getDrive().getIMU().getAngle() + 180D + angle) % 360D - 180D;
		pid.setSetpoint(newAngle);
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
			right.pidWrite(output);
		}
	}
}
