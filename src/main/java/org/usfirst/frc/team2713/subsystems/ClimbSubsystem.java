package org.usfirst.frc.team2713.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.CANTalon;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.ClimbCommand;

public class ClimbSubsystem extends Subsystem {
	public CANTalon climbMotor = new CANTalon(RobotMap.CLIMBER);
	private XboxController fight = Robot.getOI().getController(OI.ControllerType.fight);
	private double fastSpeed = 1;
	private double slowSpeed = .5;

	public ClimbSubsystem() {
		JoystickButton upFast = new JoystickButton(fight, 8); // Climb Up Fast
		JoystickButton downFast = new JoystickButton(fight, 4); // Climb Down Fast
		JoystickButton upSlow = new JoystickButton(fight, 7);
		JoystickButton downSlow = new JoystickButton(fight, 3);
		
		// May not be able to use DPad right now, needs more code than expected. Have any idea what you want instead?
		
		
		JoystickButton xboxUpFast = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 4); // Y
		JoystickButton xboxDownFast = new JoystickButton(Robot.getOI().getController(OI.ControllerType.xbox), 1); // A
		xboxUpFast.whileActive(new ClimbCommand(this, fastSpeed));
		xboxDownFast.whileActive(new ClimbCommand(this, -fastSpeed));
		
		upFast.whileActive(new ClimbCommand(this, fastSpeed));
		downFast.whileActive(new ClimbCommand(this, -fastSpeed));
		upSlow.whileActive(new ClimbCommand(this, slowSpeed));
		downSlow.whileActive(new ClimbCommand(this, -slowSpeed));
	}

	public void initClimb() {

	}

	@Override
	protected void initDefaultCommand() {
	}
}
