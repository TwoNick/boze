package dev.boze.client.manager;

import dev.boze.client.events.PacketBundleEvent;
import dev.boze.client.utils.IMinecraft;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ScheduledExecutorService;
import java.util.regex.Pattern;
import meteordevelopment.orbit.EventHandler;

public class PlayerManager implements IMinecraft {
   private ScheduledExecutorService field1297;
   private ScheduledExecutorService field1298;
   private Set<UUID> field1299 = new HashSet();
   private List<UUID> field1300 = new ArrayList();
   private static final Pattern field1301 = Pattern.compile("(\\p{XDigit}{8})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}{4})(\\p{XDigit}+)");

   public Set<UUID> method560() {
      return this.field1299;
   }

   public void method2142() {
   }

   @EventHandler
   public void method2042(PacketBundleEvent event) {
   }

   public static String method1341(String uuid) {
      return uuid.contains("-") ? uuid : field1301.matcher(uuid).replaceFirst("$1-$2-$3-$4-$5");
   }
}
