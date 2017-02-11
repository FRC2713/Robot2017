package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;

public class MoveForward extends Command {
	private Robot robot = Robot.getRobot();
	private double distance;

	public MoveForward(double distance) {
		requires(robot.getDrive());
		this.distance = distance;
	}

	@Override
	protected void initialize() {
		robot.getDrive().resetEncoders();

		CANTalon leftTalon = robot.getDrive().getLeftTalon();
		leftTalon.changeControlMode(CANTalon.TalonControlMode.Follower);
		leftTalon.set(RobotMap.TOP_RIGHT);

		CANTalon rightTalon = robot.getDrive().getRightTalon();
		rightTalon.setPID(0.1, 0, 0); // TODO: Tune PID
		rightTalon.changeControlMode(CANTalon.TalonControlMode.Position);
		rightTalon.configMaxOutputVoltage(3D);
		rightTalon.setSetpoint(distance);
	}

	@Override
	protected void end() {
		CANTalon leftTalon = robot.getDrive().getLeftTalon();
		leftTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		leftTalon.set(0);

		CANTalon rightTalon = robot.getDrive().getRightTalon();
		rightTalon.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		rightTalon.configMaxOutputVoltage(12D);
		rightTalon.set(0);
	}

	@Override
	protected boolean isFinished() {
		return robot.getDrive().getRightTalon().getError() < 0.5D;
	}
}
