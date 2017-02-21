package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command {
	private ClimbSubsystem climbSubsystem;
	private CANTalon motor;
	private double speed;
	
	public ClimbCommand(ClimbSubsystem climbSubsystem, double speed) {
		this.climbSubsystem = climbSubsystem;
		this.motor = climbSubsystem.climbMotor;
		this.speed = speed;
		requires(climbSubsystem);
	}
	
	@Override
	protected void initialize() {
		motor.set(speed);
	}
	
	@Override
	protected void interrupted() {
		motor.set(0);
	}
	
	@Override
	protected void end() {
		motor.set(0);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
