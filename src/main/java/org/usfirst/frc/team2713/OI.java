package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	private XboxController xBoxController;

	public OI(){
		initControllers();
	}

	public XboxController getController() {
		return xBoxController;
	}

	public XboxController getController(ControllerType t) {
		switch (t) {
			case xbox: return xBoxController;
			default: return xBoxController;
		}
		//TODO Implement other controllers besides XBox
	}

	private void initControllers() {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xBoxController = new XboxController(i);
			}
		}
		if (xBoxController == null) {
			xBoxController = new XboxController(RobotMap.BACKUP_XBOX_PORT);
		}
	}

	public enum ControllerType {
		xbox
	}
}


