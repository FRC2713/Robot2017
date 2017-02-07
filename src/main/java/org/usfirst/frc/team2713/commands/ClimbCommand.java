package org.usfirst.frc.team2713.commands;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.subsystems.ClimbSubsystem;

public class ClimbCommand extends Command {
	private ClimbSubsystem climbSubsystem;
	private CANTalon motor;
	private double tally = 0;
	
	public ClimbCommand(ClimbSubsystem climbSubsystem) {
		requires(climbSubsystem);
		this.climbSubsystem = climbSubsystem;
		this.motor = climbSubsystem.climbMotor;
	}
	
	@Override
	public void execute() {
		double increment = 0.01;
		double upSpeed = 0;
		double downSpeed = 0;
		boolean isUpFast = Robot.getOI().getController(OI.ControllerType.fight).getRawButton(8); // Climb Up
		boolean isDownFast = Robot.getOI().getController(OI.ControllerType.fight).getRawButton(4); // Climb Down
		boolean isUpSlow = Robot.getOI().getController(OI.ControllerType.fight).getRawButton(7);
		boolean isDownSlow = Robot.getOI().getController(OI.ControllerType.fight).getRawButton(3);
		
		if (isDownFast) {
			downSpeed = 1;
		} else if (isDownSlow) {
			downSpeed = .5;
		} else if (isUpFast) {
			upSpeed = 1;
		} else if (isUpSlow) {
			upSpeed = .5;
		} else {
			upSpeed = 0;
			downSpeed = 0;
		}
		
		if (upSpeed != 0 && downSpeed == 0) {
			motor.set(upSpeed);
		} else if (downSpeed != 0 && upSpeed == 0) {
			motor.set(-downSpeed);
		} else {
			motor.set(0);
		}
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
