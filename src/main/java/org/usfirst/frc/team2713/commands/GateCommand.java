package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.subsystems.GateSubsystem;

public class GateCommand extends Command {
	private GateSubsystem gateSubsystem;
	private Servo lowServo;
	private Servo highServo;
	
	private int openDegree = 108;
	private int closedDegree = 0;
	
	public GateCommand(GateSubsystem gateSubsystem, Servo lowServo, Servo highServo) {
		this.gateSubsystem = gateSubsystem;
		this.lowServo = lowServo;
		this.highServo = highServo;
	}
	
	@Override
	public void execute() {
		if (Robot.getOI().getController(OI.ControllerType.fight).getRawButton(1)){
			lowServo.setAngle(closedDegree);
		} else if (Robot.getOI().getController(OI.ControllerType.fight).getRawButton(5)){
			lowServo.setAngle(openDegree);
		}
		
		if (Robot.getOI().getController(OI.ControllerType.fight).getRawButton(2)){
			highServo.setAngle(closedDegree);
		} else if (Robot.getOI().getController(OI.ControllerType.fight).getRawButton(6)){
			highServo.setAngle(openDegree);
		}
	}
	
	@Override
	protected boolean isFinished() { return false; }
}
