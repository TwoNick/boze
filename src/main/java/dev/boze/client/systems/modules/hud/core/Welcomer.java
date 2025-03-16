package dev.boze.client.systems.modules.hud.core;

import dev.boze.client.api.BozeDrawColor;
import dev.boze.client.settings.BooleanSetting;
import dev.boze.client.settings.ColorSetting;
import dev.boze.client.settings.WeirdSettingString;
import dev.boze.client.systems.modules.HUDModule;
import dev.boze.client.systems.modules.client.HUD;
import dev.boze.client.systems.modules.client.Media;
import java.util.Calendar;
import net.minecraft.client.gui.DrawContext;

public class Welcomer extends HUDModule {
   public static final Welcomer INSTANCE = new Welcomer();
   private final BooleanSetting field2662 = new BooleanSetting(
      "CustomText",
      false,
      "Use custom text for greeting\nUse .welcomer text <text> to set custom text\nPut {name} as placeholder for your name\nExample: .welcomer text Hello, {name}!\n"
   );
   public final WeirdSettingString field2663 = new WeirdSettingString("Text", "Hello, {name}", "Custom text for greeting");
   public final BooleanSetting field2664 = new BooleanSetting("Custom", false, "Use custom theme settings");
   public final ColorSetting field2665 = new ColorSetting(
      "Text", new BozeDrawColor(100, 35, 250, 255, true, 0.3, 0.0, new double[]{0.0, -0.065}, new double[]{0.5, 0.6}), "Text color", this.field2664
   );
   public final ColorSetting field2666 = new ColorSetting(
      "Name", new BozeDrawColor(100, 35, 250, 255, true, 0.3, 0.0, new double[]{0.0, -0.065}, new double[]{0.5, 0.6}), "Name color", this.field2664
   );
   public final BooleanSetting field2667 = new BooleanSetting("Shadow", false, "Text shadow", this.field2664);

   public Welcomer() {
      super("Welcomer", "Greets you", 20.0, 20.0);
   }

   @Override
   public void method295(DrawContext context) {
      String var5 = this.method1562();
      if (var5.contains("{name}")) {
         String var6 = var5.substring(0, var5.indexOf("{name}"));
         String var7 = Media.method1341(mc.player.getName().getString());
         String var8 = var5.substring(var5.indexOf("{name}") + 6);
         this.method299(
            var6,
            var7,
            var8,
            this.field2664.method419() ? this.field2665.method1362() : HUD.INSTANCE.field2383.method1362(),
            this.field2664.method419() ? this.field2666.method1362() : HUD.INSTANCE.field2383.method1362(),
            this.field2664.method419() ? this.field2665.method1362() : HUD.INSTANCE.field2383.method1362(),
            this.field2664.method419() ? this.field2667.method419() : HUD.INSTANCE.field2384.method419()
         );
      } else {
         this.method296(
            var5,
            this.field2664.method419() ? this.field2665.method1362() : HUD.INSTANCE.field2383.method1362(),
            this.field2664.method419() ? this.field2667.method419() : HUD.INSTANCE.field2384.method419()
         );
      }
   }

   private String method1562() {
      return this.field2662.method419() ? this.field2663.method1322() : this.method1563() + "{name}";
   }

   private String method1563() {
      int var4 = Calendar.getInstance().get(11);
      if (var4 < 3) {
         return "Good night, ";
      } else if (var4 < 6) {
         return "Stop staying up late, ";
      } else if (var4 < 12) {
         return "Good morning, ";
      } else if (var4 < 17) {
         return "Good afternoon, ";
      } else {
         return var4 < 21 ? "Good evening, " : "Good night, ";
      }
   }
}
