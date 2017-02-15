package org.usfirst.frc.team2713.subsystems;

import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.OI;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.ClimbCommand;

public class ClimbSubsystem extends Subsystem {
	public CANTalon climbMotor = new CANTalon(RobotMap.CLIMBER);
	private XboxController fight = Robot.getOI().getController(OI.ControllerType.fight);
	double fastSpeed = 1;
	double slowSpeed = .5;
	
	
	public void startClimb() {
		JoystickButton upFast = new JoystickButton(fight, 8); // Climb Up Fast
		JoystickButton downFast = new JoystickButton(fight, 4); // Climb Down Fast
		JoystickButton upSlow = new JoystickButton(fight, 7);
		JoystickButton downSlow = new JoystickButton(fight, 3);
		
		upFast.whenPressed(new ClimbCommand(this, fastSpeed, true));
		downFast.whenPressed(new ClimbCommand(this, -fastSpeed, true));
		upSlow.whenPressed(new ClimbCommand(this, slowSpeed, true));
		downSlow.whenPressed(new ClimbCommand(this, -slowSpeed, true));
	}
	
	public boolean isButtonPressed(){
		return fight.getRawButton(8) || fight.getRawButton(4) || fight.getRawButton(7) || fight.getRawButton(3);
	}
	
	@Override
	protected void initDefaultCommand() {
	}
}
