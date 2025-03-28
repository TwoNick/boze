package dev.boze.client.gui.components.scaled;

import dev.boze.client.font.IFontRender;
import dev.boze.client.gui.components.BaseComponent;
import dev.boze.client.gui.components.InputBaseComponent;
import dev.boze.client.gui.components.ScaledBaseComponent;
import dev.boze.client.gui.screens.ClickGUI;
import dev.boze.client.gui.screens.settings.HoldBindComponent;
import dev.boze.client.gui.screens.settings.SettingsScreenBindComponent;
import dev.boze.client.gui.screens.settings.ShowInArrayListComponent;
import dev.boze.client.gui.screens.settings.ToggleNotificationsComponent;
import dev.boze.client.systems.modules.Module;
import dev.boze.client.systems.modules.client.Theme;
import dev.boze.client.utils.RGBAColor;
import dev.boze.client.utils.render.RenderUtil;
import net.minecraft.client.gui.DrawContext;

import java.util.ArrayList;

public class ModuleComponent extends ScaledBaseComponent {
    private static final double field1411 = 6.0;
    private final ArrayList<InputBaseComponent> field1412 = new ArrayList();
    private final dev.boze.client.gui.components.BindComponent field1413;

    public ModuleComponent(Module module) {
        super(module.getName(), 150.0 * BaseComponent.scaleFactor, 30.0 + 80.0 * BaseComponent.scaleFactor, true);
        this.field1413 = new SettingsScreenBindComponent(
                this, "Bind", this.field1388 + 6.0, this.field1389 + 6.0, this.field1390 - 12.0, 20.0 * BaseComponent.scaleFactor, module
        );
        this.field1412.add(this.field1413);
        this.field1412
                .add(
                        new HoldBindComponent(
                                this,
                                "Hold Bind",
                                this.field1388 + 6.0,
                                this.field1389 + 12.0 + 20.0 * BaseComponent.scaleFactor,
                                this.field1390 - 12.0,
                                20.0 * BaseComponent.scaleFactor,
                                module
                        )
                );
        this.field1412
                .add(
                        new ShowInArrayListComponent(
                                this,
                                "Show in ArrayList",
                                this.field1388 + 6.0,
                                this.field1389 + 18.0 + 40.0 * BaseComponent.scaleFactor,
                                this.field1390 - 12.0,
                                20.0 * BaseComponent.scaleFactor,
                                module
                        )
                );
        this.field1412
                .add(
                        new ToggleNotificationsComponent(
                                this,
                                "Toggle Notifications",
                                this.field1388 + 6.0,
                                this.field1389 + 24.0 + 60.0 * BaseComponent.scaleFactor,
                                this.field1390 - 12.0,
                                20.0 * BaseComponent.scaleFactor,
                                module
                        )
                );
    }

    @Override
    public void render(DrawContext matrices, int mouseX, int mouseY, float delta) {
        super.render(matrices, mouseX, mouseY, delta);
        RenderUtil.field3963.method2233();
        IFontRender.method499().startBuilding(BaseComponent.scaleFactor * 0.5);
        RenderUtil.field3963
                .method2257(
                        this.field1388,
                        this.field1389,
                        this.field1390,
                        this.field1391,
                        15,
                        24,
                        Theme.method1387() ? BaseComponent.scaleFactor * 6.0 : 0.0,
                        Theme.method1349()
                );
        if (Theme.method1382()) {
            ClickGUI.field1335
                    .field1333
                    .method2257(
                            this.field1388,
                            this.field1389,
                            this.field1390,
                            this.field1391,
                            15,
                            24,
                            Theme.method1387() ? BaseComponent.scaleFactor * 6.0 : 0.0,
                            RGBAColor.field402
                    );
        }

        for (InputBaseComponent var9 : this.field1412) {
            var9.render(matrices, mouseX, mouseY, delta);
        }

        RenderUtil.field3963.method2235(matrices);
        IFontRender.method499().endBuilding();
    }

    @Override
    public boolean isMouseOver(double mouseX, double mouseY, int button) {
        if (!isMouseWithinBounds(mouseX, mouseY, this.field1388, this.field1389, this.field1390, this.field1391)) {
            if (this.field1413.method2114()) {
                this.field1413.method2142();
            }

            return false;
        } else {
            for (InputBaseComponent var10 : this.field1412) {
                if (var10.mouseClicked(mouseX, mouseY, button)) {
                    break;
                }
            }

            return true;
        }
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        for (InputBaseComponent var8 : this.field1412) {
            if (var8.keyPressed(keyCode, scanCode, modifiers)) {
                return true;
            }
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
