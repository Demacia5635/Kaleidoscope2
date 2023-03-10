package frc.robot.subsystems.led_patches;

import java.util.Arrays;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LedsManager;
import frc.robot.utils.IndividualLed;

public class SubStrip extends SubsystemBase {
    public final int size;
    private final int offset;

    public SubStrip(int offset, int size) {
        this.offset = offset;
        this.size = size;
    }

    public void setColor(IndividualLed... leds) {
        LedsManager.getInstance().update(Arrays.stream(leds)
                .map((led) -> new IndividualLed(offset + led.index, led.color)).toArray(IndividualLed[]::new));
    }
}
