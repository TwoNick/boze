package dev.boze.client.mixin;

import com.mojang.authlib.Environment;
import com.mojang.authlib.yggdrasil.ServicesKeySet;
import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import java.net.Proxy;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin({YggdrasilMinecraftSessionService.class})
public interface YggdrasilMinecraftSessionServiceAccessor {
   @Invoker("<init>")
   static YggdrasilMinecraftSessionService initYggdrasilMinecraftSessionService(ServicesKeySet servicesKeySet, Proxy proxy, Environment env) {
      throw new UnsupportedOperationException();
   }
}
