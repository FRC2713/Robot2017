package org.usfirst.frc.team2713;

import org.usfirst.frc.team2713.input.controller.XBoxController;

public class OI {
	private XBoxController xBoxController;

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

	public enum ControllerType {
		xbox
	}
}


