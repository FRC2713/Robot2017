package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.subsystems.HighGoalSubsystem;

public class HighGoalCommand extends Command {
	private HighGoalSubsystem highGoalSubsystem;
	
	public HighGoalCommand(HighGoalSubsystem highGoalSubsystem){
		this.highGoalSubsystem = highGoalSubsystem;
		requires(highGoalSubsystem);
	}
	
	@Override
	protected void initialize() {
		highGoalSubsystem.flyWheel.set(1);
		highGoalSubsystem.kicker.set(1);
	}
	
	@Override
	protected void end() {
		highGoalSubsystem.flyWheel.set(0);
		highGoalSubsystem.kicker.set(0);
	}
	
	@Override
	protected boolean isFinished() { return !Robot.getOI().getController(OI.ControllerType.xbox).getYButton(); }
}
