package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import org.usfirst.frc.team2713.Robot;

public class VisionAlign extends Command {
	private Robot robot = Robot.getRobot();
	private NetworkTable table;
	private boolean withinThreshold;

	private Turn turnCommand;

	public VisionAlign() {
		table = NetworkTable.getTable("VisionProcessing");
	}

	@Override
	protected void interrupted() {
		withinThreshold = false;
		table.putNumber("status", 1);
	}

	@Override
	protected void execute() {
		if (!turnCommand.isFinished()) {
			return;
		}

		switch ((int) table.getNumber("status", 1)) {
			case 0: // Doing nothing.
				table.putNumber("status", 1); // Request angle
				break;
			case 2: // Angle found.
				double correctionAngle = table.getNumber("correctionAngle", 0);
				table.putNumber("status", 0);

				if (Math.abs(correctionAngle) <= 0.1) {
					withinThreshold = true;
					return;
				}

				turnCommand = new Turn(correctionAngle, 0.1);
				turnCommand.start();
				break;
		}
	}

	@Override
	protected boolean isFinished() {
		return withinThreshold;
	}
}
