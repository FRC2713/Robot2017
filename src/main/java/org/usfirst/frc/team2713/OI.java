package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team2713.input.controller.XBoxController;

public class OI {
	private XBoxController xBoxController;

	public OI(){
		initControllers();
	}

	public XBoxController getController() {
		return xBoxController;
	}

	public XBoxController getController(ControllerType t) {
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
				xBoxController = new XBoxController(i);
			}
		}
		if (xBoxController == null) {
			xBoxController = new XBoxController(RobotMap.BACKUP_XBOX_PORT);
		}
	}

	public enum ControllerType {
		xbox
	}
}


