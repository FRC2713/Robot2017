package org.usfirst.frc.team2713.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.ClimbCommand;

public class ClimbSubsystem extends Subsystem {
	public CANTalon climbMotor = new CANTalon(RobotMap.CLIMBER);
	
	public void startClimb() {
		new ClimbCommand(this).start();
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
