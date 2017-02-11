package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.subsystems.GateSubsystem;

public class GateCommand extends Command {
	private GateSubsystem gateSubsystem;
	private Servo servo;
	private double degree;

	public GateCommand(GateSubsystem gateSubsystem, Servo servo, double degree) {
		this.gateSubsystem = gateSubsystem;
		this.servo = servo;
		this.degree = degree;

		requires(gateSubsystem);
	}

	@Override
	protected void initialize() {
		servo.set(degree);
	}

	@Override
	protected boolean isFinished() {
		double currentAngle = servo.getAngle();
		return currentAngle == degree - 2 || currentAngle == degree + 2;
	}
}
