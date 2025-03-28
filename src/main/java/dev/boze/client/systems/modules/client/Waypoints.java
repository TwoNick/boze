package dev.boze.client.systems.modules.client;

import dev.boze.client.api.BozeDrawColor;
import dev.boze.client.enums.ShaderMode;
import dev.boze.client.enums.ShapeMode;
import dev.boze.client.enums.WaypointShaderMode;
import dev.boze.client.events.Render2DEvent;
import dev.boze.client.events.Render3DEvent;
import dev.boze.client.gui.notification.Notification;
import dev.boze.client.gui.notification.NotificationPriority;
import dev.boze.client.manager.ConfigManager;
import dev.boze.client.manager.NotificationManager;
import dev.boze.client.mixin.WorldRendererAccessor;
import dev.boze.client.renderer.Renderer3D;
import dev.boze.client.renderer.packer.ByteTexturePacker;
import dev.boze.client.settings.*;
import dev.boze.client.settings.generic.ScalingSetting;
import dev.boze.client.shaders.ChamsShaderRenderer;
import dev.boze.client.systems.modules.Category;
import dev.boze.client.systems.modules.Module;
import dev.boze.client.systems.modules.render.NameTags;
import dev.boze.client.systems.waypoints.WayPoint;
import dev.boze.client.utils.EntityUtil;
import dev.boze.client.utils.RGBAColor;
import dev.boze.client.utils.render.ByteTexture;
import mapped.Class5922;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.client.render.Frustum;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.joml.Vector3d;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

public class Waypoints extends Module {
    public static final Waypoints INSTANCE = new Waypoints();
    public final WaypointSetting field2437 = new WaypointSetting("Waypoints", new ArrayList(), "Waypoints");
    public final BooleanSetting field2441 = new BooleanSetting("LimitRange", false, "Limit range for rendering");
    public final IntArraySetting field2442 = new IntArraySetting("Range", new int[]{100, 10000}, 0, 100000, 10, "Range for rendering", this.field2441);
    public final ColorSetting field2443 = new ColorSetting("Color", new BozeDrawColor(1687452627), "Color for fill");
    public final ColorSetting field2444 = new ColorSetting("Outline", new BozeDrawColor(-7046189), "Color for outline");
    private final BooleanSetting field2438 = new BooleanSetting("Nametags", true, "Show nametags for waypoints");
    private final ScalingSetting field2439 = new ScalingSetting();
    private final BooleanSetting field2440 = new BooleanSetting("Boxes", true, "Show boxes for waypoints");
    private final BooleanSetting field2445 = new BooleanSetting("Shader", false, "Use a shader");
    public final EnumSetting<WaypointShaderMode> field2446 = new EnumSetting<WaypointShaderMode>(
            "Shader", WaypointShaderMode.Normal, "Shader to use", this.field2445
    );
    public final StringSetting field2453 = new StringSetting("Fill", "", "Fill for image shader", this::lambda$new$1, this.field2445);
    public final BooleanSetting field2447 = new BooleanSetting("FastRender", true, "Make the shader render faster at the cost of quality", this.field2445);
    public final IntSetting field2448 = new IntSetting("Blur", 0, 0, 5, 1, "Glow for shader", this.field2445);
    public final FloatSetting field2449 = new FloatSetting("Glow", 0.0F, 0.0F, 5.0F, 0.1F, "Glow for shader", this.field2445);
    public final FloatSetting field2450 = new FloatSetting("Strength", 0.1F, 0.02F, 2.0F, 0.02F, "Glow strength for shader", this::lambda$new$0, this.field2445);
    private final IntSetting field2451 = new IntSetting("Radius", 1, 0, 10, 1, "Outline radius for shader", this.field2445);
    private final FloatSetting field2452 = new FloatSetting("Opacity", 0.3F, 0.0F, 1.0F, 0.01F, "Fill opacity for shader", this.field2445);
    private Renderer3D field2454;
    private ByteTexture field2455;
    private String field2456 = "";

    public Waypoints() {
        super("Waypoints", "Waypoints in the world", Category.Client);
        this.addSettings(
                this.field2438,
                this.field2439.field2234,
                this.field2439.field2235,
                this.field2439.field2236,
                this.field2439.field2237,
                this.field2439.field2238,
                this.field2440,
                this.field2443,
                this.field2444,
                this.field2445
        );
        this.field2439.field2234.setVisibility(this.field2438::getValue);
    }

    @EventHandler
    private void onRender2D(Render2DEvent var1) {
        if (mc.getCurrentServerEntry() != null && mc.getCurrentServerEntry().address != null && !mc.getCurrentServerEntry().address.isEmpty() && mc.world != null
        ) {
            Vec3d var5 = EntityUtil.method2144(mc.player);
            if (this.field2438.getValue()) {
                for (WayPoint var7 : this.field2437.getValue()) {
                    if (var7.field912.equals(mc.world.getRegistryKey().getValue().getPath()) && var7.field913.equalsIgnoreCase(mc.getCurrentServerEntry().address)) {
                        Vec3d var8 = new Vec3d(var7.field909, var7.field910, var7.field911);
                        double var9 = var8.distanceTo(var5);
                        if (!this.field2441.getValue()
                                || !(var9 < (double) this.field2442.getValue()[this.field2442.method1365()])
                                && !(var9 > (double) this.field2442.getValue()[this.field2442.method1366()])) {
                            Vec3d var11 = var8.subtract(var5).normalize();
                            Vec3d var12 = var9 > 255.0 ? var5.add(var11.multiply(255.0)) : var8;
                            Frustum var13 = ((WorldRendererAccessor) mc.worldRenderer).getFrustum();
                            if (var13 != null) {
                                double var14 = 0.35;
                                if (!var13.isVisible(new Box(var12.x - var14, var12.y, var12.z - var14, var12.x + var14, var12.y + 1.8, var12.z + var14))) {
                                    continue;
                                }
                            }

                            Vector3d var16 = new Vector3d(var12.x, var12.y + 2.0, var12.z);
                            boolean var15 = Class5922.method59(var16, this.field2439);
                            if (var15) {
                                NameTags.INSTANCE.method375(var7, var16);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    private void onRender3D(Render3DEvent var1) {
        if (this.field2440.getValue() && !this.field2437.getValue().isEmpty()) {
            if (mc.getCurrentServerEntry() != null
                    && mc.getCurrentServerEntry().address != null
                    && !mc.getCurrentServerEntry().address.isEmpty()
                    && mc.world != null) {
                if (this.field2445.getValue()) {
                    if (this.field2454 == null) {
                        this.field2454 = new Renderer3D(false, true);
                    }

                    this.field2454.method1217();
                }

                Vec3d var5 = EntityUtil.method2144(mc.player);

                for (WayPoint var7 : this.field2437.getValue()) {
                    if (var7.field912.equals(mc.world.getRegistryKey().getValue().getPath()) && var7.field913.equalsIgnoreCase(mc.getCurrentServerEntry().address)) {
                        Vec3d var8 = new Vec3d(var7.field909, var7.field910, var7.field911);
                        double var9 = var8.distanceTo(var5);
                        if (!this.field2441.getValue()
                                || !(var9 < (double) this.field2442.getValue()[this.field2442.method1365()])
                                && !(var9 > (double) this.field2442.getValue()[this.field2442.method1366()])) {
                            Vec3d var13 = var8.subtract(var5).normalize();
                            Vec3d var14 = var9 > 255.0 ? var5.add(var13.multiply(255.0)) : var8;
                            if (this.field2445.getValue()) {
                                this.field2454
                                        .method1261(var14.x - 0.35, var14.y, var14.z - 0.35, var14.x + 0.35, var14.y + 1.8, var14.z + 0.35, RGBAColor.field402, 0);
                            } else {
                                var1.field1950
                                        .method1271(
                                                var14.x - 0.35,
                                                var14.y,
                                                var14.z - 0.35,
                                                var14.x + 0.35,
                                                var14.y + 1.8,
                                                var14.z + 0.35,
                                                this.field2443.getValue(),
                                                this.field2444.getValue(),
                                                ShapeMode.Full,
                                                0
                                        );
                            }
                        }
                    }
                }

                if (this.field2445.getValue()) {
                    ChamsShaderRenderer.method1310(
                            () -> lambda$onRender3D$2(var1),
                            this.method1393(),
                            this.field2447.getValue(),
                            this.field2443,
                            this.field2444,
                            this.field2451.getValue(),
                            this.field2452.getValue(),
                            this.field2449.getValue(),
                            this.field2450.getValue(),
                            this.field2448.getValue(),
                            this.field2455
                    );
                }
            }
        }
    }

    private ShaderMode method1393() {
        if (this.field2446.getValue() == WaypointShaderMode.Image) {
            if (!this.field2453.getValue().isEmpty() && (!this.field2453.getValue().equals(this.field2456) || this.field2455 == null)) {
                File var4 = new File(ConfigManager.images, this.field2453.getValue() + ".png");

                try {
                    FileInputStream var5 = new FileInputStream(var4);
                    this.field2455 = ByteTexturePacker.method493(var5);
                    if (this.field2455 != null) {
                        this.field2456 = this.field2453.getValue();
                    } else {
                        this.field2456 = "";
                    }
                } catch (Exception var6) {
                    NotificationManager.method1151(
                            new Notification(this.getName(), "Couldn't load image", dev.boze.client.gui.notification.Notifications.WARNING, NotificationPriority.Yellow)
                    );
                    this.field2453.setValue("");
                    this.field2456 = "";
                }
            }

            if (this.field2455 != null) {
                return ShaderMode.Image;
            }
        }

        return ShaderMode.Rainbow;
    }

    private void lambda$onRender3D$2(Render3DEvent var1) {
        this.field2454.method1219(var1.matrix);
    }

    private boolean lambda$new$1() {
        return this.field2446.getValue() == WaypointShaderMode.Image;
    }

    private boolean lambda$new$0() {
        return this.field2449.getValue() > 0.0F;
    }
}
