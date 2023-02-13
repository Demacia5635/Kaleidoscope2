// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.Arrays;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.IndividualLed;
import frc.robot.utils.LedsGeometry;

public class LedsManager extends SubsystemBase {
    private static LedsManager instance;

    private final LedsGeometry ledsGeometry;
    private Color[] leds;
    
    private LedsManager() {
        ledsGeometry = new LedsGeometry(Constants.LED_STRIPS);
        leds = new Color[ledsGeometry.totalLength];
    }

    public static LedsManager getInstance() {
        if (instance == null) {
            instance = new LedsManager();
        }
        return instance;
    }

    public Color[] update(IndividualLed... individualLeds) {
        Arrays.stream(individualLeds).forEach((led) -> leds[led.index] = led.color);
        return leds;
    }

    private void setChanges() {
        ledsGeometry.setColor(leds);
    }

    @Override
    public void periodic() {
        setChanges();
    }
}
