package org.usfirst.frc.team2713;

import com.ctre.phoenix.MotorControl.CAN.TalonSRX;
import edu.wpi.first.wpilibj.SpeedController;

/*
This a wrapper class for the new CTRE Pheonix TalonSRX. As of the 2018 beta, TalonSRX doesn't implement SpeedController
and so it isn't compatible with WPILib's RobotDrive. This class gets around this, but is a hack, and shouldn't be
used in the real 2018 season code.
 */
public class CANTalon extends TalonSRX implements SpeedController {
		public CANTalon(int deviceNumber) {
			super(deviceNumber);
	}
}
