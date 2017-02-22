package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MoveMotor extends Command {
	private CANTalon motor;
	private double speed;
	private String smartKey;
	
	public MoveMotor(CANTalon talon, double speed){
		this.motor = talon;
		this.speed = speed;
	}
	
	public MoveMotor(CANTalon talon, String smartKey){
		this.motor = talon;
		this.smartKey = smartKey;
	}
	
	@Override
	protected void execute() {
		if (smartKey != null) {
			speed = SmartDashboard.getNumber(smartKey, 0D);
		}
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
