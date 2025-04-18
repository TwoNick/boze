package dev.boze.client.gui.components.text;

import dev.boze.client.gui.components.TextBaseComponent;
import dev.boze.client.gui.components.scaled.RGBASettingComponent;
import dev.boze.client.settings.RGBASetting;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class e6 extends TextBaseComponent {
    final RGBASetting field1167;
    final RGBASettingComponent field1168;

    public e6(RGBASettingComponent var1, String var2, double var3, double var5, double var7, double var9, RGBASetting var11) {
        super(var2, var3, var5, var7, var9);
        this.field1168 = var1;
        this.field1167 = var11;
    }

    @Override
    protected void method1649(int button) {
        if (button == 0 && RGBASettingComponent.field1482 != null) {
            mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
            this.field1167.method1348().set(RGBASettingComponent.field1482);
        }
    }
}
