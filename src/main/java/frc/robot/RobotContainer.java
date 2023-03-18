// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.WaitUntilCommand;
import frc.robot.commands.Flicker;
import frc.robot.commands.RollyPolly;
import frc.robot.subsystems.led_patches.SubStrip;

public class RobotContainer {
  public static final PigeonIMU gyro = new PigeonIMU(14);
  private static final double startPitch = gyro.getPitch(), startRoll = gyro.getRoll();

  public static double getPitch() {
    return gyro.getPitch() - startPitch;
  }

  public static double getRoll() {
    return gyro.getRoll() - startRoll;
  }

  public RobotContainer() {
    SubStrip strip = new SubStrip(0, 17);
    Command command = new RepeatCommand(
        new WaitUntilCommand(() -> (Math.abs(getPitch())) > Constants.EPSILON).andThen(new RollyPolly(strip, RobotContainer::getPitch, Color.kBlue, Color.kYellow),
            new Flicker(strip)))
        .ignoringDisable(true);
    command.schedule();
    strip.setDefaultCommand(command);
    SubStrip strip2 = new SubStrip(17, 18);
    Command command2 = new RepeatCommand(
        new WaitUntilCommand(() -> (Math.abs(getRoll())) > Constants.EPSILON).andThen(new RollyPolly(strip2, RobotContainer::getRoll, Color.kGreen, Color.kRed),
            new Flicker(strip2)))
        .ignoringDisable(true);
    command2.schedule();
    strip2.setDefaultCommand(command2);
    SmartDashboard.putData(CommandScheduler.getInstance());
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
