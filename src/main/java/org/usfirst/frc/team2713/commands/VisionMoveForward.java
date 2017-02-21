package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/*
 * Quick hack to get around sequential commands not
 * being able to pass values from one to another.
 *
 * ONLY to be used after VisionAlign has been run.
 */
public class VisionMoveForward extends Command {
	private NetworkTable table;

	private MoveForward moveCommand;

	public VisionMoveForward() {
		table = NetworkTable.getTable("VisionProcessing");
	}

	@Override
	protected void initialize() {
		moveCommand = new MoveForward(table.getNumber("approxDistance", 0));
		moveCommand.start();
	}

	@Override
	protected boolean isFinished() {
		return moveCommand != null && moveCommand.isFinished();
	}
}
