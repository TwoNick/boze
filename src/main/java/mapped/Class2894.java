package mapped;

import dev.boze.client.api.BozeDrawColor;
import dev.boze.client.font.IFontRender;
import dev.boze.client.systems.modules.client.HUD;
import dev.boze.client.systems.modules.hud.core.TextRadar;

public class Class2894 {
    private final String field117;
    private final BozeDrawColor field118;

    public Class2894(String text, BozeDrawColor color) {
        this.field117 = text;
        this.field118 = color;
    }

    public void method5661(double var1, double var3) {
        IFontRender.method499()
                .drawShadowedText(
                        this.field117,
                        var1,
                        var3,
                        this.field118,
                        TextRadar.INSTANCE.field634.getValue() ? TextRadar.INSTANCE.field639.getValue() : HUD.INSTANCE.field2384.getValue()
                );
    }

    public double method5662() {
        double var4 = IFontRender.method499()
                .measureTextHeight(
                        this.field117, TextRadar.INSTANCE.field634.getValue() ? TextRadar.INSTANCE.field639.getValue() : HUD.INSTANCE.field2384.getValue()
                );
        return var4
                + IFontRender.method499()
                .measureTextHeight(" ", TextRadar.INSTANCE.field634.getValue() ? TextRadar.INSTANCE.field639.getValue() : HUD.INSTANCE.field2384.getValue());
    }
}
