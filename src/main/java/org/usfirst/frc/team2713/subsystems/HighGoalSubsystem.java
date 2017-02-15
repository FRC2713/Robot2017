package org.usfirst.frc.team2713.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.HighGoalCommand;

public class HighGoalSubsystem extends Subsystem {
	public CANTalon flyWheel = new CANTalon(RobotMap.HIGH_GOAL_FLY_WHEEL);
	public CANTalon kicker = new CANTalon(RobotMap.HIGH_GOAL_KICKER);

	public void initHighGoal() {
		JoystickButton button = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 4);
		
		button.whenPressed(new HighGoalCommand(this));
	}

	@Override
	protected void initDefaultCommand() {
	}
}
