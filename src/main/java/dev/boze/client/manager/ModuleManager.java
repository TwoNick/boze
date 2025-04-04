package dev.boze.client.manager;

import com.google.gson.JsonObject;
import dev.boze.api.BozeInstance;
import dev.boze.api.addon.module.ToggleableModule;
import dev.boze.client.Boze;
import dev.boze.client.command.commands.DebugCommand;
import dev.boze.client.core.ErrorLogger;
import dev.boze.client.enums.KeyAction;
import dev.boze.client.events.KeyEvent;
import dev.boze.client.events.MouseButtonEvent;
import dev.boze.client.gui.notification.NotificationRenderer;
import dev.boze.client.settings.Setting;
import dev.boze.client.settings.SettingBlock;
import dev.boze.client.settings.generic.SettingsGroup;
import dev.boze.client.systems.modules.Category;
import dev.boze.client.systems.modules.GraphHUDModule;
import dev.boze.client.systems.modules.HUDModule;
import dev.boze.client.systems.modules.Module;
import dev.boze.client.systems.modules.client.*;
import dev.boze.client.systems.modules.combat.*;
import dev.boze.client.systems.modules.hud.ColorHUDModule;
import dev.boze.client.systems.modules.hud.TextHUDModule;
import dev.boze.client.systems.modules.hud.color.*;
import dev.boze.client.systems.modules.hud.core.*;
import dev.boze.client.systems.modules.hud.graph.*;
import dev.boze.client.systems.modules.hud.graph.Framerate;
import dev.boze.client.systems.modules.hud.text.Clock;
import dev.boze.client.systems.modules.hud.text.Lag;
import dev.boze.client.systems.modules.legit.*;
import dev.boze.client.systems.modules.misc.*;
import dev.boze.client.systems.modules.movement.*;
import dev.boze.client.systems.modules.render.*;
import dev.boze.client.utils.IMinecraft;
import dev.boze.client.utils.MovementHandler;
import dev.boze.client.utils.PacketHandler;
import dev.boze.client.utils.ServerConnectionHandler;
import dev.boze.client.utils.misc.IJsonSerializable2;
import dev.boze.client.utils.misc.ISerializable;
import dev.boze.client.utils.trackers.LatencyTracker;
import mapped.Class2779;
import mapped.Class2782;
import mapped.Class5925;
import mapped.Class5928;
import meteordevelopment.orbit.EventHandler;
import net.minecraft.nbt.NbtCompound;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ModuleManager implements Class5925, ISerializable, IMinecraft, IJsonSerializable2<ModuleManager> {
    public final List<Module> modules = new ArrayList<>();
    public MovementHandler field905;
    public PacketHandler field906;
    public ServerConnectionHandler field907;

    public void init() {
        Boze.LOG.debug("Initializing Modules");
        this.method396(NewRenderTest.INSTANCE);
        this.method396(Aura.INSTANCE);
        this.method396(AutoAnchor.INSTANCE);
        this.method396(AutoBed.INSTANCE);
        this.method396(AutoMine.INSTANCE);
        this.method396(AutoCrystal.INSTANCE);
        this.method396(PearlPhase.INSTANCE);
        this.method396(AutoWeapon.INSTANCE);
        this.method396(AutoWeb.INSTANCE);
        this.method396(BackTrack.INSTANCE);
        this.method396(AutoArmor.INSTANCE);
        this.method396(AntiBed.INSTANCE);
        this.method396(OffHand.INSTANCE);
        this.method396(AutoTrap.INSTANCE);
        this.method396(AutoMend.INSTANCE);
        this.method396(BowAim.INSTANCE);
        this.method396(BowSpam.INSTANCE);
        this.method396(ChorusPostpone.INSTANCE);
        this.method396(Criticals.INSTANCE);
        this.method396(FakeLag.INSTANCE);
        this.method396(FastProjectile.INSTANCE);
        this.method396(HoleFill.INSTANCE);
        this.method396(PistonPush.INSTANCE);
        this.method396(SelfBow.INSTANCE);
        this.method396(SelfFill.INSTANCE);
        this.method396(SelfTrap.INSTANCE);
        this.method396(Surround.INSTANCE);
        this.field906 = new PacketHandler();
        Boze.EVENT_BUS.subscribe(this.field906);
        this.method396(AntiLevitation.INSTANCE);
        this.method396(AntiVoid.INSTANCE);
        this.method396(AutoFirework.INSTANCE);
        this.method396(ElytraAutoPilot.INSTANCE);
        this.method396(AutoWalk.INSTANCE);
        this.method396(BoatFly.INSTANCE);
        this.method396(GrimDisabler.INSTANCE);
        this.method396(ClickFly.INSTANCE);
        this.method396(ClickTP.INSTANCE);
        this.method396(ElytraBoost.INSTANCE);
        this.method396(ElytraFly.INSTANCE);
        this.method396(ElytraRecast.INSTANCE);
        this.method396(EntityControl.INSTANCE);
        this.method396(EntitySpeed.INSTANCE);
        this.method396(RocketTweaks.INSTANCE);
        this.method396(FastFall.INSTANCE);
        this.method396(FastSwim.INSTANCE);
        this.method396(Flight.INSTANCE);
        this.method396(HoleTP.INSTANCE);
        this.method396(IceSpeed.INSTANCE);
        this.method396(Jesus.INSTANCE);
        this.method396(LongJump.INSTANCE);
        this.method396(NoFall.INSTANCE);
        this.method396(NoSlow.INSTANCE);
        this.method396(PacketFly.INSTANCE);
        this.method396(Parkour.INSTANCE);
        this.method396(SafeWalk.INSTANCE);
        this.method396(Speed.INSTANCE);
        this.method396(Sprint.INSTANCE);
        this.method396(Step.INSTANCE);
        this.method396(TickShift.INSTANCE);
        this.method396(TridentPlus.INSTANCE);
        this.method396(Velocity.INSTANCE);
        this.method396(WebTP.INSTANCE);
        this.method396(AirPlace.INSTANCE);
        this.method396(AntiAFK.INSTANCE);
        this.method396(AntiAim.INSTANCE);
        this.method396(AntiHunger.INSTANCE);
        this.method396(AntiSpam.INSTANCE);
        this.method396(AutoCraft.INSTANCE);
        this.method396(AutoDisconnect.INSTANCE);
        this.method396(AutoEat.INSTANCE);
        this.method396(AutoFish.INSTANCE);
        this.method396(AutoMount.INSTANCE);
        this.method396(AutoReconnect.INSTANCE);
        this.method396(Regear.INSTANCE);
        this.field907 = new ServerConnectionHandler();
        Boze.EVENT_BUS.subscribe(this.field907);
        this.method396(AutoRespawn.INSTANCE);
        this.method396(AutoScoreboard.INSTANCE);
        this.method396(AutoTool.INSTANCE);
        this.method396(AutoUpgrade.INSTANCE);
        this.method396(BeaconSelector.INSTANCE);
        this.method396(BetterTab.INSTANCE);
        this.method396(Extinguish.INSTANCE);
        this.method396(FakePlayer.INSTANCE);
        this.method396(FastLatency.INSTANCE);
        this.method396(FastUse.INSTANCE);
        this.method396(InventoryTweaks.INSTANCE);
        this.method396(KeyClickAction.INSTANCE);
        this.method396(MiddleClickAction.INSTANCE);
        this.method396(MultiTask.INSTANCE);
        this.method396(NoEntityTrace.INSTANCE);
        this.method396(NoGhostBlocks.INSTANCE);
        this.method396(NoJumpDelay.INSTANCE);
        this.method396(MiningTweaks.INSTANCE);
        this.method396(NoRotate.INSTANCE);
        this.method396(Nuker.INSTANCE);
        this.method396(Octopus.INSTANCE);
        this.method396(OreScanner.INSTANCE);
        this.method396(PauseBaritone.INSTANCE);
        this.method396(PingSpoof.INSTANCE);
        this.method396(PortalGodMode.INSTANCE);
        this.method396(Replenish.INSTANCE);
        this.method396(RotationLock.INSTANCE);
        this.method396(Scaffold.INSTANCE);
        this.method396(SkinBlinker.INSTANCE);
        this.method396(SmartMiner.INSTANCE);
        this.method396(SoundFX.INSTANCE);
        this.method396(Spammer.INSTANCE);
        this.method396(Swing.INSTANCE);
        this.method396(Timer.INSTANCE);
        this.method396(WallInteract.INSTANCE);
        this.method396(XCarry.INSTANCE);
        this.method396(AspectRatio.INSTANCE);
        this.method396(Background.INSTANCE);
        this.method396(BlockHighlight.INSTANCE);
        this.method396(BlockHighlightDev.INSTANCE);
        this.method396(BreakHighlight.INSTANCE);
        this.method396(CameraClip.INSTANCE);
        this.method396(BossStack.INSTANCE);
        this.method396(Breadcrumbs.INSTANCE);
        this.method396(Chams.INSTANCE);
        this.method396(Crosshair.INSTANCE);
        this.method396(ExtraChat.INSTANCE);
        this.method396(ESP.INSTANCE);
        this.method396(FOV.INSTANCE);
        this.method396(FreeCam.INSTANCE);
        this.method396(FreeLook.INSTANCE);
        this.method396(FullBright.INSTANCE);
        this.method396(HandTweaks.INSTANCE);
        this.method396(HoleESP.INSTANCE);
        this.method396(LogoutSpots.INSTANCE);
        this.method396(MotionBlur.INSTANCE);
        this.method396(NameTags.INSTANCE);
        this.method396(NewChunks.INSTANCE);
        this.method396(NoRender.INSTANCE);
        this.method396(OldSigns.INSTANCE);
        this.method396(PhaseESP.INSTANCE);
        this.method396(PlaceRender.INSTANCE);
        this.method396(PopChams.INSTANCE);
        this.method396(ReachCircles.INSTANCE);
        this.method396(Search.INSTANCE);
        this.method396(SpawnESP.INSTANCE);
        this.method396(StorageESP.INSTANCE);
        this.method396(Tint.INSTANCE);
        this.method396(Tooltips.INSTANCE);
        this.method396(Tracers.INSTANCE);
        this.method396(Trails.INSTANCE);
        this.method396(Trajectories.INSTANCE);
        this.method396(TunnelESP.INSTANCE);
        this.method396(ViewModel.INSTANCE);
        this.method396(XRay.INSTANCE);
        this.method396(Zoom.INSTANCE);
        this.method396(Accounts.INSTANCE);
        this.method396(AntiCheat.INSTANCE);
        this.method396(Capes.INSTANCE);
        Capes.method1904();
        this.method396(Chat.INSTANCE);
        this.method396(ClientSpoof.INSTANCE);
        this.method396(Colors.INSTANCE);
        this.method396(OldColors.INSTANCE);
        this.method396(Fonts.INSTANCE);
        this.method396(DiscordPresence.INSTANCE);
        this.method396(Friends.INSTANCE);
        this.method396(GhostRotations.INSTANCE);
        this.method396(Gui.INSTANCE);
        this.method396(HUD.INSTANCE);
        this.method396(Macros.INSTANCE);
        this.method396(Media.INSTANCE);
        this.method396(Notifications.INSTANCE);
        this.method396(Options.INSTANCE);
        Boze.EVENT_BUS.subscribe(Options.INSTANCE);
        this.method396(Playtime.INSTANCE);
        this.method396(Profiles.INSTANCE);
        this.method396(Theme.INSTANCE);
        this.method396(Waypoints.INSTANCE);
        this.method396(AimAssist.INSTANCE);
        this.method396(AnchorTrigger.INSTANCE);
        this.method396(AntiBots.INSTANCE);
        this.method396(AutoBridge.INSTANCE);
        this.method396(AutoClicker.INSTANCE);
        this.method396(AutoHop.INSTANCE);
        this.method396(AutoLoot.INSTANCE);
        this.method396(ChestStealer.INSTANCE);
        this.method396(CrystalAssist.INSTANCE);
        this.method396(CrystalOptimizer.INSTANCE);
        this.method396(CrystalTrigger.INSTANCE);
        this.method396(Hitboxes.INSTANCE);
        this.method396(HotbarTotem.INSTANCE);
        this.method396(JumpReset.INSTANCE);
        this.method396(MurderMystery.INSTANCE);
        this.method396(NoMissDelay.INSTANCE);
        this.method396(Reach.INSTANCE);
        this.method396(ShieldTrigger.INSTANCE);
        this.method396(Teams.INSTANCE);
        this.method396(ThrowPot.INSTANCE);
        this.method396(Trigger.INSTANCE);
        this.method396(WTap.INSTANCE);
        this.method396(Durability.INSTANCE);
        this.method396(ClientPackets.INSTANCE);
        this.method396(CrystalSpeed.INSTANCE);
        this.method396(Framerate.INSTANCE);
        this.method396(Health.INSTANCE);
        this.method396(Motion.INSTANCE);
        this.method396(ServerPackets.INSTANCE);
        this.method396(Armor.INSTANCE);
        this.method396(dev.boze.client.systems.modules.hud.core.ArrayList.INSTANCE);
        this.method396(Binds.INSTANCE);
        this.method396(ChestCount.INSTANCE);
        this.method396(Clock.INSTANCE);
        this.method396(Coordinates.INSTANCE);
        this.method396(Crystals.INSTANCE);
        this.method396(Direction.INSTANCE);
        this.method396(Effects.INSTANCE);
        this.method396(Experience.INSTANCE);
        this.method396(dev.boze.client.systems.modules.hud.core.Framerate.INSTANCE);
        this.method396(GoldenApples.INSTANCE);
        this.method396(Inventory.INSTANCE);
        this.method396(ItemRadar.INSTANCE);
        this.method396(Lag.INSTANCE);
        this.method396(Ping.INSTANCE);
        this.method396(Pops.INSTANCE);
        this.method396(SpeedMeter.INSTANCE);
        this.method396(Target.INSTANCE);
        this.method396(TextRadar.INSTANCE);
        this.method396(Totems.INSTANCE);
        this.method396(Ticks.INSTANCE);
        this.method396(Watermark.INSTANCE);
        this.method396(Welcomer.INSTANCE);
        Boze.EVENT_BUS.subscribe(LatencyTracker.INSTANCE);
        this.method1416();
        Boze.EVENT_BUS.subscribe(this);
    }

    public Module method395(String name) {
        for (Module var6 : this.modules) {
            if (var6.getName().equalsIgnoreCase(name) || var6.internalName.equalsIgnoreCase(name)) {
                return var6;
            }
        }

        return null;
    }

    public void method1416() {
        this.modules.sort(Comparator.comparing(Module::getName));
    }

    public ArrayList<String> method2120() {
        ArrayList<String> var4 = new ArrayList<>();

        for (Module var6 : this.modules) {
            var4.add(var6.getName());
            if (!var4.contains(var6.internalName)) {
                var4.add(var6.internalName);
            }
        }

        return var4;
    }

    public void method396(Module module) {
        if (module instanceof HUDModule) {
            module.method1144().add(((HUDModule) module).field595);
        }

        if (module instanceof TextHUDModule) {
            module.method1144().add(((TextHUDModule) module).field2581);
            module.method1144().add(((TextHUDModule) module).field2582);
            module.method1144().add(((TextHUDModule) module).field2583);
        }

        if (module instanceof ColorHUDModule) {
            module.method1144().add(((ColorHUDModule) module).field606);
            module.method1144().add(((ColorHUDModule) module).field607);
            module.method1144().add(((ColorHUDModule) module).field608);
            module.method1144().add(((ColorHUDModule) module).field609);
        }

        if (module instanceof GraphHUDModule) {
            module.method1144().add(((GraphHUDModule) module).field2300);
            module.method1144().add(((GraphHUDModule) module).field2301);
            module.method1144().add(((GraphHUDModule) module).field2302);
            module.method1144().add(((GraphHUDModule) module).field2303);
            module.method1144().add(((GraphHUDModule) module).field2304);
            module.method1144().add(((GraphHUDModule) module).field2305);
            module.method1144().add(((GraphHUDModule) module).field2306);
            module.method1144().add(((GraphHUDModule) module).field2307);
            module.method1144().add(((GraphHUDModule) module).field2308);
        }

        for (Field var8 : module.getClass().getDeclaredFields()) {
            try {
                if (Setting.class.isAssignableFrom(var8.getType())) {
                    var8.setAccessible(true);
                    Setting<?> var9 = (Setting<?>) var8.get(module);
                    if (var9 instanceof SettingBlock) {
                        for (Setting<?> var13 : ((SettingBlock) var9).method472()) {
                            module.method1144().add(var13);
                            if (module.method221() != null) {
                                var13.method404(module.method221(), false);
                            }
                        }
                    } else {
                        module.method1144().add(var9);
                        if (module.method221() != null && !var9.name.equals("Mode")) {
                            var9.method404(module.method221(), false);
                        }
                    }
                } else if (SettingsGroup.class.isAssignableFrom(var8.getType())) {
                    var8.setAccessible(true);
                    SettingsGroup var15 = (SettingsGroup) var8.get(module);

                    for (Setting<?> var19 : var15.get()) {
                        module.method1144().add(var19);
                        if (module.method221() != null) {
                            var19.method404(module.method221(), false);
                        }
                    }
                }
            } catch (Exception var14) {
                ErrorLogger.log(var14);
                Boze.LOG.error("Unable to register a setting for module " + module.internalName + ", this can lead to instability and crashes");
            }
        }

        if (module.method221() != null) {
            module.method1144().addAll(module.method221().getSettings());
        }

        this.modules.add(module);
    }

    @EventHandler(
            priority = 100
    )
    private void method1944(KeyEvent var1) {
        if (var1.action != KeyAction.Repeat) {
            this.method397(true, var1.key, var1.action == KeyAction.Press);
        }
    }

    @EventHandler(
            priority = 100
    )
    private void method1812(MouseButtonEvent var1) {
        if (var1.action != KeyAction.Repeat) {
            this.method397(false, var1.button, var1.action == KeyAction.Press);
        }
    }

    private void method397(boolean var1, int var2, boolean var3) {
        if (mc.currentScreen == null && !Class5928.method159(292)) {
            for (Module var8 : this.modules) {
                if ((var8.field435 || var8.method222() || !Options.INSTANCE.method1971())
                        && var8.bind.matches(var1, var2)
                        && (var3 || var8.getHoldBind())
                        && (var8.getHoldBind() ? var8.setEnabled(var3) : var8.toggle())
                        && var8.getNotify()) {
                    if (var8.isEnabled()) {
                        NotificationManager.method1151(new NotificationRenderer(var8.getName(), true));
                    } else {
                        NotificationManager.method1151(new NotificationRenderer(var8.getName(), false));
                    }
                }
            }

            if (var3) {
                for (ToggleableModule var10 : BozeInstance.INSTANCE.getModules()) {
                    if (var10.getBind() != null && var10.getBind().getBind() == var2 && var10.getBind().isButton() != var1) {
                        var10.setState(!var10.getState());
                    }
                }
            }
        }
    }

    @Override
    public NbtCompound method225() {
        NbtCompound var4 = new NbtCompound();

        for (Module var6 : this.modules) {
            NbtCompound var7 = var6.method225();
            if (var7 != null && !var7.isEmpty()) {
                var4.put(var6.internalName, var7);
            }
        }

        return var4;
    }

    @Override
    public NbtCompound method226() {
        NbtCompound var4 = new NbtCompound();

        for (Module var6 : this.modules) {
            NbtCompound var7 = var6.method226();
            if (var7 != null && !var7.isEmpty()) {
                var4.put(var6.internalName, var7);
            }
        }

        return var4;
    }

    @Override
    public NbtCompound method227() {
        NbtCompound var4 = new NbtCompound();

        for (Module var6 : this.modules) {
            NbtCompound var7 = var6.method227();
            if (var7 != null && !var7.isEmpty()) {
                var4.put(var6.internalName, var7);
            }
        }

        return var4;
    }

    @Override
    public NbtCompound method228() {
        NbtCompound var4 = new NbtCompound();

        for (Module var6 : this.modules) {
            NbtCompound var7 = var6.method228();
            if (var7 != null && !var7.isEmpty()) {
                var4.put(var6.internalName, var7);
            }
        }

        for (Category var8 : Category.values()) {
            NbtCompound var9 = new NbtCompound();
            var9.putBoolean("Extended", var8.extended);
            var9.putDouble("x", var8.field42);
            var9.putDouble("y", var8.field43);
            var9.putBoolean("Locked", var8.locked);
            var9.putDouble("s", var8.scrollOffset);
            var4.put(var8.name(), var9);
        }

        var4.putDouble("CS_x", Class2782.field91);
        var4.putDouble("CS_y", Class2782.field92);
        var4.putDouble("CS_s", Class2782.field93);
        var4.putBoolean("SB_l", Class2782.field94);
        var4.putDouble("SB_x", Class2782.field95);
        var4.putDouble("SB_y", Class2782.field96);
        var4.putDouble("AS_x", Class2779.field86);
        var4.putDouble("AS_y", Class2779.field87);
        var4.putBoolean("AS_l", Class2779.field88);
        var4.putDouble("AS_s", Class2779.field89);
        var4.putBoolean("AS_e", Class2779.field90);
        if (DebugCommand.field1378) {
            var4.putBoolean("Magic", true);
        }

        return var4;
    }

    @Override
    public void method232(NbtCompound tag) {
        for (Module var6 : this.modules) {
            if (tag.contains(var6.internalName)) {
                var6.method232(tag.getCompound(var6.internalName));
            }
        }
    }

    @Override
    public void method394(NbtCompound tag) {
        for (Module var6 : this.modules) {
            if (tag.contains(var6.internalName)) {
                var6.method394(tag.getCompound(var6.internalName));
            }
        }
    }

    @Override
    public void method233(NbtCompound tag) {
        for (Module var6 : this.modules) {
            if (tag.contains(var6.internalName)) {
                var6.method233(tag.getCompound(var6.internalName));
            }
        }
    }

    @Override
    public void method234(NbtCompound tag) {
        for (Module var6 : this.modules) {
            if (tag.contains(var6.internalName)) {
                var6.method234(tag.getCompound(var6.internalName));
            }
        }

        for (Category var8 : Category.values()) {
            if (tag.contains(var8.name())) {
                NbtCompound var9 = tag.getCompound(var8.name());
                if (var9.contains("Extended")) {
                    var8.extended = var9.getBoolean("Extended");
                }

                if (var9.contains("x")) {
                    var8.field42 = var9.getDouble("x");
                }

                if (var9.contains("y")) {
                    var8.field43 = var9.getDouble("y");
                }

                if (var9.contains("Locked")) {
                    var8.locked = var9.getBoolean("Locked");
                }

                if (var9.contains("s")) {
                    var8.scrollOffset = var9.getDouble("s");
                }
            }
        }

        if (tag.contains("CS_x")) {
            Class2782.field91 = tag.getDouble("CS_x");
        }

        if (tag.contains("CS_y")) {
            Class2782.field92 = tag.getDouble("CS_y");
        }

        if (tag.contains("CS_s")) {
            Class2782.field93 = tag.getDouble("CS_s");
        }

        if (tag.contains("SB_l")) {
            Class2782.field94 = tag.getBoolean("SB_l");
        }

        if (tag.contains("SB_x")) {
            Class2782.field95 = tag.getDouble("SB_x");
        }

        if (tag.contains("SB_y")) {
            Class2782.field96 = tag.getDouble("SB_y");
        }

        if (tag.contains("AS_x")) {
            Class2779.field86 = tag.getDouble("AS_x");
        }

        if (tag.contains("AS_y")) {
            Class2779.field87 = tag.getDouble("AS_y");
        }

        if (tag.contains("AS_l")) {
            Class2779.field88 = tag.getBoolean("AS_l");
        }

        if (tag.contains("AS_s")) {
            Class2779.field89 = tag.getDouble("AS_s");
        }

        if (tag.contains("AS_e")) {
            Class2779.field90 = tag.getBoolean("AS_e");
        }

        if (tag.contains("Magic")) {
            DebugCommand.field1378 = tag.getBoolean("Magic");
        }
    }

    @Override
    public NbtCompound toTag() {
        NbtCompound var4 = new NbtCompound();

        for (Module var6 : this.modules) {
            if (var6 != Options.INSTANCE) {
                var4.put(var6.internalName, var6.toTag());
            }
        }

        var4.put(Options.INSTANCE.internalName, Options.INSTANCE.toTag());

        for (Category var8 : Category.values()) {
            NbtCompound var9 = new NbtCompound();
            var9.putBoolean("Extended", var8.extended);
            var9.putDouble("x", var8.field42);
            var9.putDouble("y", var8.field43);
            var9.putBoolean("Locked", var8.locked);
            var9.putDouble("s", var8.scrollOffset);
            var4.put(var8.name(), var9);
        }

        var4.putDouble("CS_x", Class2782.field91);
        var4.putDouble("CS_y", Class2782.field92);
        var4.putDouble("CS_s", Class2782.field93);
        var4.putBoolean("SB_l", Class2782.field94);
        var4.putDouble("SB_x", Class2782.field95);
        var4.putDouble("SB_y", Class2782.field96);
        var4.putDouble("AS_x", Class2779.field86);
        var4.putDouble("AS_y", Class2779.field87);
        var4.putBoolean("AS_l", Class2779.field88);
        var4.putDouble("AS_s", Class2779.field89);
        var4.putBoolean("AS_e", Class2779.field90);
        if (DebugCommand.field1378) {
            var4.putBoolean("Magic", true);
        }

        return var4;
    }

    public ModuleManager method398(NbtCompound tag, boolean profile) {
        if (tag.isEmpty()) {
            return this;
        } else {
            Boze.LOG.debug("Loading modules config");
            if (tag.contains("Magic")) {
                DebugCommand.field1378 = tag.getBoolean("Magic");
            }

            if (tag.contains("Colors")) {
                Colors.INSTANCE.fromTag(tag.getCompound("Colors"));
            }

            for (Module var7 : this.modules) {
                if ((!profile || var7 != Profiles.INSTANCE && var7 != Friends.INSTANCE)
                        && var7 != Options.INSTANCE
                        && var7 != Colors.INSTANCE
                        && tag.contains(var7.internalName)) {
                    Boze.LOG.debug("Loading config for module " + var7.internalName);

                    try {
                        var7.fromTag(tag.getCompound(var7.internalName));

                        try {
                            var7.bind.fromTag(tag.getCompound(var7.internalName).getCompound("Keybind"));
                        } catch (Exception var12) {
                            ErrorLogger.log(var12);
                            Boze.LOG.warn("Unable to load module " + var7.internalName + "'s keybind from config");
                        }
                    } catch (Exception var13) {
                        ErrorLogger.log(var13);
                        Boze.LOG.warn("Unknown error loading config for module " + var7.internalName);
                    }
                }
            }

            if (tag.contains(Options.INSTANCE.internalName)) {
                Options.INSTANCE.fromTag(tag.getCompound(Options.INSTANCE.internalName));
            }

            for (Category var9 : Category.values()) {
                if (tag.contains(var9.name())) {
                    try {
                        NbtCompound var10 = tag.getCompound(var9.name());
                        var9.extended = var10.getBoolean("Extended");
                        var9.field42 = var10.getDouble("x");
                        var9.field43 = var10.getDouble("y");
                        if (var10.contains("Locked")) {
                            var9.locked = var10.getBoolean("Locked");
                        }

                        if (var10.contains("s")) {
                            var9.scrollOffset = var10.getDouble("s");
                        }
                    } catch (Exception var11) {
                        ErrorLogger.log(var11);
                        Boze.LOG.warn("Unable to load state for category " + var9.name());
                    }
                }
            }

            if (tag.contains("CS_x") && tag.contains("CS_y")) {
                Class2782.field91 = tag.getDouble("CS_x");
                Class2782.field92 = tag.getDouble("CS_y");
            }

            if (tag.contains("CS_s")) {
                Class2782.field93 = tag.getDouble("CS_s");
            }

            if (tag.contains("SB_l")) {
                Class2782.field94 = tag.getBoolean("SB_l");
            }

            if (tag.contains("SB_x") && tag.contains("SB_y")) {
                Class2782.field95 = tag.getDouble("SB_x");
                Class2782.field96 = tag.getDouble("SB_y");
            }

            if (tag.contains("AS_x") && tag.contains("AS_y") && tag.contains("AS_e")) {
                Class2779.field86 = tag.getDouble("AS_x");
                Class2779.field87 = tag.getDouble("AS_y");
                Class2779.field90 = tag.getBoolean("AS_e");
            }

            if (tag.contains("AS_l")) {
                Class2779.field88 = tag.getBoolean("AS_l");
            }

            if (tag.contains("AS_s")) {
                Class2779.field89 = tag.getDouble("AS_s");
            }

            return this;
        }
    }

    @Override
    public ModuleManager fromTag(NbtCompound tag) {
        return this.method398(tag, false);
    }

    @Override
    public JsonObject serialize() {
        JsonObject var4 = new JsonObject();

        for (Module var6 : this.modules) {
            var4.add(var6.internalName, var6.serialize());
        }

        return var4;
    }

    @Override
    public ModuleManager deserialize(JsonObject data) {
        if (data == null) {
            return this;
        } else {
            Boze.LOG.debug("Loading local data");

            for (Module var6 : this.modules) {
                if (data.has(var6.internalName)) {
                    try {
                        var6.deserialize(data.getAsJsonObject(var6.internalName));
                    } catch (Exception var8) {
                        ErrorLogger.log(var8);
                    }
                }
            }

            return null;
        }
    }

    // $VF: synthetic method
    // $VF: bridge method
    //@//Override
    //public Object fromTag(NbtCompound nbtCompound) {
    //   return this.method399(nbtCompound);
    //}

    // $VF: synthetic method
    // $VF: bridge method
    //@Override
    //public Object deserialize(JsonObject jsonObject) {
    //   return this.method400(jsonObject);
    //}
}
