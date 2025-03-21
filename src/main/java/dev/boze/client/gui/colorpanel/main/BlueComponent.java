package dev.boze.client.gui.colorpanel.main;

import dev.boze.client.gui.colorpanel.ColorPanel;
import dev.boze.client.gui.components.IntSliderComponent;
import dev.boze.client.manager.ColorManager;
import dev.boze.client.utils.RGBAColor;

public class BlueComponent extends IntSliderComponent {
    final ColorManager field1229;
    final ColorPanel field1230;

    public BlueComponent(ColorPanel var1, String var2, double var3, double var5, double var7, double var9, ColorManager var11) {
        super(var2, var3, var5, var7, var9);
        this.field1230 = var1;
        this.field1229 = var11;
    }

    @Override
    protected void method1649(int value) {
        this.field1229.method1374().field410 = value;
    }

    @Override
    protected int method2010() {
        return this.field1229.method1374().field410;
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
        this.field1229.method1374().field410 = this.field1229.field414.field410;
    }

    @Override
    protected RGBAColor method1347() {
        return RGBAColor.field406;
    }
}
