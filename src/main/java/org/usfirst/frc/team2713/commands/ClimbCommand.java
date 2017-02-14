package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command {
	private ClimbSubsystem climbSubsystem;
	private CANTalon motor;
	private double speed;
	private boolean isOI;
	
	public ClimbCommand(ClimbSubsystem climbSubsystem, double speed, boolean isOI) {
		this.climbSubsystem = climbSubsystem;
		this.motor = climbSubsystem.climbMotor;
		this.speed = speed;
		this.isOI = isOI;
		requires(climbSubsystem);
	}
	
	@Override
	protected void initialize() {
		motor.set(speed);
	}
	
	@Override
	protected void end() {
		motor.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		return !(isOI && climbSubsystem.isButtonPressed());
	}
}
