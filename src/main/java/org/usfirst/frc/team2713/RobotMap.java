package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class RobotMap {
	// User Configurable Settings
	public static final boolean DEBUG = false;
	public static SendableChooser<Enum> OIDriveMode = new SendableChooser<>();

	// Talon Ports
	public static final int TOP_RIGHT = 1;
	public static final int TOP_LEFT = 3;
	public static final int BOTTOM_RIGHT = 2;
	public static final int BOTTOM_LEFT = 4;


}
