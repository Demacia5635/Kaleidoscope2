// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.IndividualLed;
import frc.robot.utils.LedsGeometry;

public final class LedsManager extends SubsystemBase {

    private final List<IndividualLed> off;
    
    private static LedsManager instance;

    private final LedsGeometry ledsGeometry;
    private List<IndividualLed> leds;

    private LedsManager() {
        ledsGeometry = new LedsGeometry(Constants.LED_STRIPS);
        off = IntStream.range(0, ledsGeometry.totalLength).mapToObj((i) -> new IndividualLed(i, Color.kBlack)).toList();
        leds = new ArrayList<>();
        setDefaultColor();
    }

    private void setDefaultColor() {
        leds = off;
    }

    public static LedsManager getInstance() {
        if (instance == null) {
            instance = new LedsManager();
        }
        return instance;
    }

    public void update(IndividualLed... individualLeds) {
        leds.addAll(Arrays.asList(individualLeds));
    }

    private void setChanges() {
        ledsGeometry.setColor(leds);
        leds = new ArrayList<>();
    }

    public Color[] getColors(int startIndex, int size) {
        Color[] colors = new Color[size];
        int end = startIndex + size;
        for (int i = startIndex; i < end; i++) {
            colors[i] = ledsGeometry.getColor(i);
        }
        return colors;
    }

    @Override
    public void periodic() {
        setChanges();
    }
}
