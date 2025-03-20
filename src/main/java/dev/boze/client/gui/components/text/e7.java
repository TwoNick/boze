package dev.boze.client.gui.components.text;

import dev.boze.client.gui.components.TextBaseComponent;
import dev.boze.client.gui.components.scaled.RGBASettingComponent;
import dev.boze.client.settings.RGBASetting;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.sound.SoundEvents;

public class e7 extends TextBaseComponent {
   final RGBASetting field1169;
   final RGBASettingComponent field1170;

   public e7(RGBASettingComponent var1, String var2, double var3, double var5, double var7, double var9, RGBASetting var11) {
      super(var2, var3, var5, var7, var9);
      this.field1170 = var1;
      this.field1169 = var11;
   }

   @Override
   protected void method1649(int button) {
      if (button == 0) {
         mc.getSoundManager().play(PositionedSoundInstance.master(SoundEvents.UI_BUTTON_CLICK, 1.0F));
         this.field1169.resetValue();
      }
   }
}
