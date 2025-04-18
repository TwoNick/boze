package dev.boze.client.systems.modules.combat.automine;

import dev.boze.client.enums.AutoMineManualPriorityMode;
import dev.boze.client.settings.*;
import dev.boze.client.settings.generic.SettingsGroup;
import dev.boze.client.systems.modules.combat.AutoMine;
import dev.boze.client.utils.IMinecraft;
import net.minecraft.util.math.BlockPos;

import java.util.LinkedList;

public class Queue implements IMinecraft, SettingsGroup {
    public final IntSetting field88 = new IntSetting("MaxQueue", 0, 0, 10, 1, "The max queue size\nIf 0, forces instant mining of manually selected blocks");
    public final EnumSetting<AutoMineManualPriorityMode> field89 = new EnumSetting<AutoMineManualPriorityMode>(
            "ManualPriority",
            AutoMineManualPriorityMode.Off,
            "Prioritize manually selected blocks\n - Off: Don't prioritise\n - On: Prioritise\n - Force: LIFO instead of FIFO queue\nLIFO = Last in, first out\nFIFO = First in, first out\n"
    );
    public final BooleanSetting field90 = new BooleanSetting(
            "Repetitive", false, "Add blocks repetitively when holding down left click\nLets you mine several blocks without clicking several times\n"
    );
    final LinkedList<BlockDirectionInfo> field87 = new LinkedList();
    private final SettingBlock field91 = new SettingBlock("Queue", "Queue settings", this.field88, this.field89, this.field90);

    public Queue(AutoMine var1) {
        this.field91.setVisibility(var1.advanced::getValue);
    }

    private static void method1800(String var0) {
        if (AutoMine.field2518 && mc.player != null) {
            System.out.println("[AutoMine.Queue @" + mc.player.age + "] " + var0);
        }
    }

    @Override
    public Setting<?>[] get() {
        return this.field91.method472();
    }

    public void method2142() {
        this.field87.clear();
    }

    public boolean method71(BlockDirectionInfo var1) {
        method1800("Adding task at " + var1.field2523.toShortString());
        if (this.field87.contains(var1)) {
            method1800("Task with pos " + var1.field2523.toShortString() + " already in queue");
            return false;
        } else if (AutoMine.INSTANCE.miner.method2101(var1.field2523)) {
            method1800("Task with pos " + var1.field2523.toShortString() + " already being mined");
            return false;
        } else if (this.field87.size() < Math.max(1, this.field88.getValue())) {
            if (this.field88.getValue() == 0 && !AutoMine.INSTANCE.miner.method2114()) {
                method1800("Task " + var1.field2523.toShortString() + " was not added to queue");
                return false;
            } else {
                if (!this.field87.isEmpty() && this.field89.getValue() == AutoMineManualPriorityMode.Force) {
                    this.field87.addFirst(var1);
                } else {
                    this.field87.add(var1);
                }

                method1800("Task " + var1.field2523.toShortString() + " added to queue");
                return true;
            }
        } else {
            method1800("Task " + var1.field2523.toShortString() + " was not added to queue");
            return false;
        }
    }

    public BlockDirectionInfo method1462() {
        return this.field87.poll();
    }

    public boolean method2114() {
        return this.field87.isEmpty();
    }

    public int method2010() {
        return this.field87.size();
    }

    public void method1416() {
        this.field87.clear();
    }

    boolean method2101(BlockPos var1) {
        for (BlockDirectionInfo var6 : this.field87) {
            if (var6.field2523.equals(var1)) {
                return true;
            }
        }

        return false;
    }
}
