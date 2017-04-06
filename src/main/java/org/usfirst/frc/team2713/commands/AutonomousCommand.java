package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2713.Robot;

public class AutonomousCommand extends CommandGroup {
	public AutonomousCommand(int startingPosition, boolean active) {
		if (active) {
			switch (startingPosition) {
				case 1:
				case 6:
					addSequential(new MoveForward(95D));
					addSequential(new Turn(startingPosition == 1 ? 60D : -60D, 5D));
					addSequential(new VisionAlign(Robot.getRobot().getDrive()));
					addSequential(new VisionMoveForward());
					break;
				case 2:
				case 5:
					addSequential(new MoveForward(103D));
					break;
				case 3:
				case 4:
					addSequential(new MoveForward(95D));
					addSequential(new Turn(startingPosition == 3 ? -60D : 60D, 5D));
					addSequential(new VisionAlign(Robot.getRobot().getDrive()));
					addSequential(new VisionMoveForward());
					break;
			}
		}
	}
}
