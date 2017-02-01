package org.usfirst.frc.team2713;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

public class OI {
	private XboxController xBoxController;
	private XboxController fightController;

	public OI(){
		initControllers();
	}

	public XboxController getController() {
		return xBoxController;
	}

	public XboxController getController(ControllerType t) {
		switch (t) {
			case xbox: return xBoxController;
			case fight: return fightController;
			default: return xBoxController;
		}
	}

	private void initControllers() {
		for (int i = 0; i < 6; i++) {
			Joystick test = new Joystick(i);
			if (test.getName().equals(RobotMap.XBOX_NAME)) {
				xBoxController = new XboxController(i);
			} else if (test.getName().equals(RobotMap.FIGHT_NAME)){
				fightController = new XboxController(i);
			}
		}
		if (xBoxController == null) {
			xBoxController = new XboxController(RobotMap.BACKUP_XBOX_PORT);
		}
		if (fightController == null) {
			fightController = new XboxController(1);
		}
	}

	public enum ControllerType {
		xbox, fight
	}
}


