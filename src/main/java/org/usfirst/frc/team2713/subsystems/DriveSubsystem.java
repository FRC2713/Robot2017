package org.usfirst.frc.team2713.subsystems;


import com.ctre.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team2713.Robot;
import org.usfirst.frc.team2713.RobotMap;
import org.usfirst.frc.team2713.commands.OIDrive;

public class DriveSubsystem extends Subsystem {
	private CANTalon topLeft;
	private CANTalon topRight;
	private CANTalon bottomLeft;
	private CANTalon bottomRight;
	private Robot robot = Robot.getRobot();
	public RobotDrive roboDrive;

	// Tank Drive > Arcade Drive
	public DriveSubsystem() {
		topLeft = new CANTalon(RobotMap.TOP_LEFT);
		topRight = new CANTalon(RobotMap.TOP_RIGHT);
		bottomLeft = new CANTalon(RobotMap.BOTTOM_LEFT);
		bottomRight = new CANTalon(RobotMap.BOTTOM_RIGHT);

		roboDrive = new RobotDrive(topLeft, bottomLeft, topRight, bottomRight);
	}

	@Override
	protected void initDefaultCommand() {}

	public void startTeleop(){
		new OIDrive(this, roboDrive).start();

	}

	public enum DriveModes{
		tank, arcade, rocketleague
	}
}
