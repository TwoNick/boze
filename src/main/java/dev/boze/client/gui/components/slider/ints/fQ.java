package dev.boze.client.gui.components.slider.ints;

import dev.boze.client.gui.components.IntSliderComponent;
import dev.boze.client.gui.components.scaled.SettingColorComponent;
import dev.boze.client.utils.RGBAColor;

public class fQ extends IntSliderComponent {
    final SettingColorComponent field2032;

    public fQ(SettingColorComponent var1, String var2, double var3, double var5, double var7, double var9) {
        super(var2, var3, var5, var7, var9);
        this.field2032 = var1;
    }

    @Override
    protected void method1649(int value) {
        this.field2032.method1362().field408 = value;
    }

    @Override
    protected int method2010() {
        return this.field2032.method1362().field408;
    }

    @Override
    protected int method1547() {
        return 0;
    }

    @Override
    protected int method1365() {
        return 255;
    }

    @Override
    protected void method2142() {
        this.field2032.method1362().field408 = 0;
    }

    @Override
    protected RGBAColor method1347() {
        return RGBAColor.field403;
    }
}
