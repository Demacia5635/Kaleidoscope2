package frc.robot.subsystems.led_patches;

import edu.wpi.first.wpilibj.util.Color;
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
        for (IndividualLed led : leds) {
            led.index += offset;
        }
        LedsManager.getInstance().update(leds);
    }

    public void setColor(Color color) {
        IndividualLed[] leds = new IndividualLed[size];
        for (int i = 0; i < size; i++) {
            leds[i] = new IndividualLed(i + offset, color);
        }
        LedsManager.getInstance().update(leds);
    }

    public void turnOff() {
        setColor(Color.kBlack);
    }

    public Color[] getColors() {
        return LedsManager.getInstance().getColors(offset, size);
    }

    public void setColor(Color[] colors) {
        int length = Math.min(colors.length, size);
        IndividualLed[] leds = new IndividualLed[length];
        for (int i = 0; i < length; i++) {
            leds[i] = new IndividualLed(i + offset, colors[i]);
        }
        LedsManager.getInstance().update(leds);
    }
}
