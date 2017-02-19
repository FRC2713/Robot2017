package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;

public class MoveMotor extends Command {
	private CANTalon motor;
	private double speed;
	
	public MoveMotor(CANTalon talon, double speed){
		this.motor = talon;
		this.speed = speed;
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
		return false;
	}
}
