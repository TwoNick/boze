package dev.boze.client.systems.modules.hud.core;

import dev.boze.client.api.BozeDrawColor;
import dev.boze.client.mixin.MinecraftClientAccessor;
import dev.boze.client.settings.BooleanSetting;
import dev.boze.client.settings.ColorSetting;
import dev.boze.client.systems.modules.HUDModule;
import dev.boze.client.systems.modules.client.HUD;
import net.minecraft.client.gui.DrawContext;

public class Framerate extends HUDModule {
    public static final Framerate INSTANCE = new Framerate();
    private final BooleanSetting field2620 = new BooleanSetting("ShowPrefix", false, "Show prefix (FrameRate)");
    private final BooleanSetting field2621 = new BooleanSetting("FastUpdate", false, "Fast update");
    private final BooleanSetting field2622 = new BooleanSetting("Custom", false, "Use custom theme settings");
    private final ColorSetting field2623 = new ColorSetting(
            "Prefix", new BozeDrawColor(100, 35, 250, 255, true, 0.3, 0.0, new double[]{0.0, -0.065}, new double[]{0.5, 0.6}), "Prefix color", this.field2622
    );
    private final ColorSetting field2624 = new ColorSetting(
            "Number", new BozeDrawColor(100, 35, 250, 255, true, 0.3, 0.0, new double[]{0.0, -0.065}, new double[]{0.5, 0.6}), "Number color", this.field2622
    );
    private final ColorSetting field2625 = new ColorSetting(
            "Suffix", new BozeDrawColor(100, 35, 250, 255, true, 0.3, 0.0, new double[]{0.0, -0.065}, new double[]{0.5, 0.6}), "Suffix color", this.field2622
    );
    private final BooleanSetting field2626 = new BooleanSetting("Shadow", false, "Text shadow", this.field2622);
    private long field2627;
    private long field2628;

    public Framerate() {
        super("FPS", "Shows your current FrameRate", 40.0, 40.0);
    }

    @Override
    public void method295(DrawContext context) {
        String var5 = this.field2621.getValue() ? Long.toString(1000000000L / this.field2628) : Integer.toString(((MinecraftClientAccessor) mc).getCurrentFps());
        if (this.field2620.getValue()) {
            this.method298(
                    "FrameRate",
                    var5,
                    "fps",
                    this.field2622.getValue() ? this.field2623.getValue() : HUD.INSTANCE.field2383.getValue(),
                    this.field2622.getValue() ? this.field2624.getValue() : HUD.INSTANCE.field2383.getValue(),
                    this.field2622.getValue() ? this.field2625.getValue() : HUD.INSTANCE.field2383.getValue(),
                    this.field2622.getValue() ? this.field2626.getValue() : HUD.INSTANCE.field2384.getValue()
            );
        } else {
            this.method297(
                    var5,
                    "fps",
                    this.field2622.getValue() ? this.field2624.getValue() : HUD.INSTANCE.field2383.getValue(),
                    this.field2622.getValue() ? this.field2625.getValue() : HUD.INSTANCE.field2383.getValue(),
                    this.field2622.getValue() ? this.field2626.getValue() : HUD.INSTANCE.field2384.getValue()
            );
        }
    }

    public void method1555() {
        this.field2628 = System.nanoTime() - this.field2627;
        this.field2627 = System.nanoTime();
    }
}
