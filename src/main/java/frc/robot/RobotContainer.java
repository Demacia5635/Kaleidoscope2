// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.commands.Rainbow;
import frc.robot.subsystems.led_patches.SubStrip;

public class RobotContainer {
    public RobotContainer() {
        SubStrip strip = new SubStrip(0, 5);
        new Rainbow(strip, 3).schedule();

        configureBindings();
    }

    private void configureBindings() {
    }

    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
}
