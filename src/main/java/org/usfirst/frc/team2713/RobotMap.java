package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotMap {
	// User Configurable Settings
	public static final boolean DEBUG = false;
	public static SendableChooser<Enum> OIDriveMode = new SendableChooser<>();
	public static SendableChooser<Integer> startingPosition = new SendableChooser<>();

	// Controller Ports
	public static final int BACKUP_XBOX_PORT = 0;
	public static final int BACKUP_ATTACK_PORT = 1;
	public static final String XBOX_NAME = "Controller (XBOX 360 For Windows)"; // Used for dynamically finding the controller
	public static final String FIGHT_NAME = "Mayflash Arcade Stick";

	// Talon Ports
	public static final int TOP_RIGHT = 1;
	public static final int TOP_LEFT = 3;
	public static final int BOTTOM_RIGHT = 2;
	public static final int BOTTOM_LEFT = 4;
	public static final int CLIMBER = 5;
	public static final int HIGH_GOAL_FLY_WHEEL = 6;
	public static final int HIGH_GOAL_KICKER = 7;

	// Servo Ports
	public static final int LOW_GATE_SERVO = 0;
	public static final int HIGH_GATE_SERVO = 1;
}
