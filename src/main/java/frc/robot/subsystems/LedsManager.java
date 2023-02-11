// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.utils.IndividualLed;

public class LedsManager extends SubsystemBase {
    private static LedsManager instance;

    private final AddressableLED kaleidoscopeSystem;
    private AddressableLEDBuffer ledMask;
    private boolean hasLMChanged;
    
    private LedsManager() {
        kaleidoscopeSystem = new AddressableLED(Constants.LED_PORT);
        kaleidoscopeSystem.setLength(Constants.LED_NUM);

        hasLMChanged = false;
        setDefaultBuffer();
    }

    private void setDefaultBuffer() {
        ledMask = new AddressableLEDBuffer(Constants.LED_NUM);
        for (int i = 0; i < Constants.LED_NUM - 1; i++) {
            ledMask.setLED(i, Color.kBlue);
        }
        ledMask.setLED(Constants.LED_NUM - 1, Color.kBlack);
        hasLMChanged = true;
    }

    private static AddressableLEDBuffer copy(AddressableLEDBuffer buffer) {
        var newBuffer = new AddressableLEDBuffer(buffer.getLength());
        for (int i = 0; i < buffer.getLength(); i++) {
            newBuffer.setLED(i, buffer.getLED8Bit(i));
        }
        return newBuffer;
    }

    public static LedsManager getInstance() {
        if (instance == null) {
            instance = new LedsManager();
        }
        return instance;
    }

    public AddressableLEDBuffer update(IndividualLed... individualLeds) {
        for (var led : individualLeds) {
            if (!hasLMChanged && !led.color.equals(ledMask.getLED(led.index)))
                hasLMChanged = true;
            
            ledMask.setLED(led.index, led.color);
        }
        return copy(ledMask);
    }

    private void setChanges() {
        // TODO: set changes in Leds
    }

    @Override
    public void periodic() {
        if (hasLMChanged)
            setChanges();

        hasLMChanged = false;
    }
}
