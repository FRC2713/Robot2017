package org.usfirst.frc.team2713.subsystems;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.GateCommand;

public class GateSubsystem extends Subsystem {
	Servo lowServo = new Servo(RobotMap.LOW_GATE_SERVO);
	Servo highServo = new Servo(RobotMap.HIGH_GATE_SERVO);
	
	public void startGate() {
		new GateCommand(this, lowServo, highServo);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
