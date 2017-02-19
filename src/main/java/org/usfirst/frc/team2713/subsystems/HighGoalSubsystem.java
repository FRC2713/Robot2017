package org.usfirst.frc.team2713.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.MoveMotor;

public class HighGoalSubsystem extends Subsystem {
	private CANTalon kicker = new CANTalon(RobotMap.HIGH_GOAL_KICKER);
	private CANTalon vibrator = new CANTalon(RobotMap.HIGH_GOAL_VIBRATOR);

	public void initHighGoal() {
		JoystickButton launcher = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 4); // Y Button
		JoystickButton vibration = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 3); // X Button
		
		launcher.toggleWhenPressed(new MoveMotor(kicker, 1.0D));
		vibration.toggleWhenPressed(new MoveMotor(vibrator, 0.25D));
	}

	@Override
	protected void initDefaultCommand() {
	}
}
