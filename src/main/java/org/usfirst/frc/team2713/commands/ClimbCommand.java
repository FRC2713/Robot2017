package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command {
	private ClimbSubsystem climbSubsystem;
	private CANTalon motor;
	
	public ClimbCommand(ClimbSubsystem climbSubsystem) {
		this.climbSubsystem = climbSubsystem;
		this.motor = climbSubsystem.climbMotor;
	}
	
	@Override
	public void execute() {
		double upSpeed = Robot.getOI().getController().getTriggerAxis(GenericHID.Hand.kLeft);
		double downSpeed = -(Robot.getOI().getController().getTriggerAxis(GenericHID.Hand.kRight));
		if (upSpeed != 0 && downSpeed == 0) {
			motor.set(upSpeed);
		} else if (downSpeed != 0 && upSpeed == 0) {
			motor.set(downSpeed);
		} else {
			motor.set(0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
