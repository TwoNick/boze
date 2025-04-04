package dev.boze.client;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import dev.boze.api.BozeInstance;
import dev.boze.api.addon.Addon;
import dev.boze.api.event.EventGrim;
import dev.boze.client.api.APIEventHandler;
import dev.boze.client.api.BozeAPI;
import dev.boze.client.events.PacketBundleEvent;
import dev.boze.client.gui.renderer.IconLoader;
import dev.boze.client.instances.impl.ChatInstance;
import dev.boze.client.manager.*;
import dev.boze.client.renderer.QuadRenderer;
import dev.boze.client.settings.ShaderSetting;
import dev.boze.client.systems.modules.client.DiscordPresence;
import dev.boze.client.systems.modules.misc.autotool.qG;
import dev.boze.client.systems.modules.render.FreeCam;
import dev.boze.client.systems.modules.render.FreeLook;
import dev.boze.client.systems.modules.render.Zoom;
import dev.boze.client.utils.MovementHandler;
import dev.boze.client.utils.PiglinAggressiveness;
import dev.boze.client.utils.network.BozeExecutor;
import dev.boze.client.utils.render.PlayerHeadUtils;
import dev.boze.client.utils.trackers.BlockBreakingTracker;
import dev.boze.client.utils.trackers.InventoryTracker;
import dev.boze.client.utils.trackers.TargetTracker;
import dev.boze.client.utils.trackers.TickRateTracker;
import mapped.Class1201;
import mapped.Class28;
import mapped.Class3069;
import meteordevelopment.orbit.EventBus;
import meteordevelopment.orbit.EventHandler;
import meteordevelopment.orbit.IEventBus;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.tutorial.TutorialStep;
import net.minecraft.network.packet.s2c.play.PlayerPositionLookS2CPacket;
import net.minecraft.network.packet.s2c.play.WorldTimeUpdateS2CPacket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodHandles.Lookup;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Boze implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger();
    public static final IEventBus EVENT_BUS = new EventBus();
    private static final LinkedList<String> keys = new LinkedList<>();
    public static float prevLastYaw;
    public static float prevLastPitch;
    public static boolean isInventory = false;
    public static int lastTeleportId = 0;
    public static String BUILD = "";
    public static File FOLDER;
    private static AccountManager accounts;
    private static ModuleManager modules;
    private static CommandManager commands;
    private static MacroManager macros;
    private static PlayerManager playerManager;

    public static AccountManager getAccounts() {
        return accounts;
    }

    public static ModuleManager getModules() {
        return modules;
    }

    public static CommandManager getCommands() {
        return commands;
    }

    public static MacroManager getMacros() {
        return macros;
    }

    public static PlayerManager getPlayerManager() {
        return playerManager;
    }

    public static String getNextKey() {
        return keys.poll();
    }

    private static void lambda$initialize$3() {
        try {
            File var3 = new File(System.getProperty("user.home"), "Boze" + File.separator + "cache");
            String var4 = System.getProperty("os.name").toLowerCase();
            boolean var5 = var4.contains("nix") || var4.contains("nux") || var4.contains("aix");
            if (var5 && (!var3.exists() || !new File(var3, "lt").exists())) {
                var3 = new File(FabricLoader.getInstance().getGameDir().toString(), "cache");
            }

            File var6 = new File(var3, "lt");
            if (var6.exists()) {
                String var7 = Files.readString(var6.toPath());
                if (var7.length() == 92) {
                    Socket var8 = new Socket("auth.boze.dev", 3000);
                    DataOutputStream var9 = new DataOutputStream(var8.getOutputStream());
                    var9.writeUTF(Base64.getEncoder().encodeToString(MessageDigest.getInstance("MD5").digest("playtime".getBytes(StandardCharsets.UTF_8))));
                    var9.writeUTF(var7);
                    var8.close();
                }
            }
        } catch (NoSuchAlgorithmException | IOException ignored) {
        }
    }

    private static void lambda$initialize$2() {
        MinecraftClient.getInstance().getTutorialManager().setStep(TutorialStep.NONE);
    }

    private static void lambda$initialize$1() {
        ConfigManager.save();
        DiscordPresence.INSTANCE.onDisable();
        BozeInstance.INSTANCE.getAddons().forEach(Addon::shutdown);
        Zoom.INSTANCE.onDisable();
        FreeCam.INSTANCE.onDisable();
        FreeLook.INSTANCE.onDisable();
    }

    private static Lookup lambda$initialize$0(Method var0, Class<?> var1) throws InvocationTargetException, IllegalAccessException {
        return (Lookup) var0.invoke(null, var1, MethodHandles.lookup());
    }

    @Override
    public void onInitializeClient() {
        MinecraftClient.getInstance().execute(() -> {
            LOG.info("Initializing Boze");

            try {
                InputStream var4 = MinecraftClient.class.getClassLoader().getResourceAsStream("beta.build");

                try (InputStreamReader reader = var4 != null ? new InputStreamReader(var4, Charsets.UTF_8) : null) {
                    if (reader != null) {
                        BUILD = CharStreams.toString(reader);
                    } else {
                        throw new IllegalStateException("InputStream var4 is null");
                    }
                } catch (IOException e) {
                    throw new RuntimeException("Error reading input stream", e);
                }

                var4.close();
            } catch (Exception var14) {
                BUILD = "";
            }

            if (MinecraftClient.IS_SYSTEM_MAC) {
                LOG.info("Running on a Mac, initializing a Mac-compatible version of Boze");
            }

            ConfigManager.method1146();
            ConfigManager.linked();

            EventGrim.Interact.INSTANCE = new Class28();
            keys.addAll(List.of(
                    "position_color.vert", "position_color.frag",
                    "position_color_circle.vert", "position_color_circle.frag",
                    "position_gradient.vert", "position_gradient.frag",
                    "position_gradient_world.vert", "position_gradient.frag",
                    "position_gradient_circle.vert", "position_gradient_circle.frag",
                    "position_hsb.vert", "position_hsb.frag",
                    "position_hsb_circle.vert", "position_hsb_circle.frag",
                    "position_texture_color.vert", "position_texture_color.frag",
                    "position_rainbow.vert", "position_rainbow.frag",
                    "position_texture_color.vert", "text.frag",
                    "text_rainbow.vert", "text_rainbow.frag",
                    "text_gradient.vert", "text_gradient.frag",
                    "fb_gradient.vert", "fb_gradient.frag",
                    "position_texture_color.vert", "text_vanilla.frag",
                    "text_rainbow.vert", "text_vanilla_rainbow.frag",
                    "blit.vert", "blit.frag",
                    "blit.vert", "blit_opacity.frag",
                    "background.vert", "background.frag",
                    "blur.vert", "blur.frag",
                    "motion_blur.vert", "motion_blur.frag",
                    "rainbow_sample.vert", "rainbow_sample.frag",
                    "pass.vert", "pass.frag",
                    "fastblur.vert", "fastblur_plus.frag",
                    "fastblur.vert", "fastblur_minus.frag"
            ));

            LOG.info("registering lambda factory");
            EVENT_BUS.registerLambdaFactory("dev.boze.client", Boze::lambda$initialize$0);
            LOG.info("registered lambda factory");

            EVENT_BUS.subscribe(this);
            this.init();
            this.postInit();
            ConfigManager.load();
            Class1201.method2378();

            for (ShaderSetting var17 : ShaderSetting.field962) {
                if (var17.field963.getShaderProgram() == null) {
                    var17.field963.setShaderSetting(var17);
                }
            }

            Runtime.getRuntime().addShutdownHook(new Thread(Boze::lambda$initialize$1));
            MinecraftClient.getInstance().execute(Boze::lambda$initialize$2);
            this.initApi();
            ScheduledExecutorService playtimeExecutor = Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setDaemon(true).build());
            playtimeExecutor.scheduleAtFixedRate(Boze::lambda$initialize$3, 10L, 10L, TimeUnit.MINUTES);
            LOG.info("Initialized Boze");
        });
    }

    private void initApi() {
        BozeAPI.init();
        EVENT_BUS.subscribe(APIEventHandler.class);
    }

    private void init() {
        BozeExecutor.method2199();
        accounts = new AccountManager();
        TickRateTracker.reset();
        ConfigManager.createDirs();
        QuadRenderer.initialize();
    }

    private void postInit() {
        ChatInstance.method2142();
        EVENT_BUS.subscribe(Class3069.class);
        PlayerHeadUtils.method2229();
        IconLoader.method1121();
        modules = new ModuleManager();

        try {
            InputStream var4 = MinecraftClient.class.getClassLoader().getResourceAsStream("boze.mixins.json");

            var4 = null;

            if (var4 == null) {
                modules.init();
            }

            if (var4 != null) {
                var4.close();
            }
        } catch (Exception var10) {
            modules.init();
        }

        try {
            modules.field905 = new MovementHandler();
        } catch (NoSuchFieldException | IOException | NoSuchAlgorithmException | IllegalAccessException ignored) {
        }

        EVENT_BUS.subscribe(modules.field905);
        macros = new MacroManager();
        EVENT_BUS.subscribe(macros);
        commands = new CommandManager();
        commands.method1137();
        playerManager = new PlayerManager();
        EVENT_BUS.subscribe(playerManager);
        EVENT_BUS.subscribe(BlockBreakingTracker.field1511);
        EVENT_BUS.subscribe(TargetTracker.class);
        EVENT_BUS.subscribe(qG.class);
        EVENT_BUS.subscribe(InventoryTracker.class);
        EVENT_BUS.subscribe(PiglinAggressiveness.class);
    }

    @EventHandler
    public void onPacketReceive(PacketBundleEvent event) {
        if (event.packet instanceof WorldTimeUpdateS2CPacket) {
            TickRateTracker.update();
        }

        if (event.packet instanceof PlayerPositionLookS2CPacket) {
            lastTeleportId = ((PlayerPositionLookS2CPacket) event.packet).getTeleportId();
        }
    }

    private byte[] cipherBytes(byte[] var1, String var2) {
        byte[] var6 = new byte[var1.length];

        for (int var7 = 0; var7 < var1.length; var7++) {
            var6[var7] = (byte) (var1[var7] ^ var2.charAt(var7 % var2.length()));
        }

        return var6;
    }
}
