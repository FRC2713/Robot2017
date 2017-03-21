package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousCommand extends CommandGroup {
	public AutonomousCommand(int startingPosition, boolean active) {
		if (active) {
			addSequential(new MoveForward(103D));

			switch (startingPosition) {
				case 1:
					addSequential(new Turn(60D, 2D));
					addSequential(new VisionAlign());
					addSequential(new VisionMoveForward());
					break;
				case 3:
					addSequential(new Turn(-60D, 2D));
					addSequential(new VisionAlign());
					addSequential(new VisionMoveForward());
					break;
			}
		}
	}
}
