package org.usfirst.frc.team2713.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team2713.Robot;

public class AutonomousCommand extends CommandGroup {
	public AutonomousCommand(int startingPosition, boolean active) {
		if (active) {
			/*addSequential(new MoveForward(103D));

			switch (startingPosition) {
				case 1:
					addSequential(new Turn(60D, 2D));
					addSequential(new VisionAlign(Robot.getRobot().getDrive()));
					addSequential(new VisionMoveForward());
					break;
				case 3:
					addSequential(new Turn(-60D, 2D));
					addSequential(new VisionAlign(Robot.getRobot().getDrive()));
					addSequential(new VisionMoveForward());
					break;
			}*/
			addSequential(new MoveForward(7*18.8496D));
		}
	}
}
