package org.usfirst.frc.team2713.subsystems;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.CANTalon;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.MoveMotor;

public class HighGoalSubsystem extends Subsystem {
	private CANTalon kicker = new CANTalon(RobotMap.HIGH_GOAL_KICKER);
	private CANTalon vibrator = new CANTalon(RobotMap.HIGH_GOAL_VIBRATOR);

	public void initHighGoal() {
		kicker.setInverted(true);
		JoystickButton launcher = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 4); // Y Button
		JoystickButton vibration = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 3); // X Button
		JoystickButton backwardLauncher = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 1); // A Button

		launcher.toggleWhenPressed(new MoveMotor(kicker, "Launcher Speed", false));
		vibration.toggleWhenPressed(new MoveMotor(vibrator, 0.1D));
		backwardLauncher.toggleWhenPressed(new MoveMotor(kicker, "Launcher Speed", true));
	}

	@Override
	protected void initDefaultCommand() {
	}
}
