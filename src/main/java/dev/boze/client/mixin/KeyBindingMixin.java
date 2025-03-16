package dev.boze.client.mixin;

import dev.boze.client.events.KeyPressedEvent;
import mapped.Class27;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({KeyBinding.class})
public abstract class KeyBindingMixin {
   @Shadow
   public int timesPressed;
   @Unique
   private boolean prevState = true;

   @Inject(
      method = {"isPressed"},
      at = {@At("RETURN")},
      cancellable = true
   )
   public void hookEventKeyBindingIsPressed(CallbackInfoReturnable<Boolean> cir) {
      boolean var2 = (Boolean)cir.getReturnValue();
      KeyPressedEvent var3 = KeyPressedEvent.method1066((KeyBinding)this, (Boolean)cir.getReturnValue());
      Class27.EVENT_BUS.post(var3);
      if (var3.method1069() && !this.prevState && !(Boolean)cir.getReturnValue() && var3.method1068()) {
         this.timesPressed++;
      }

      this.prevState = var3.method1068();
      if (this.prevState != var2) {
         cir.setReturnValue(this.prevState);
      }
   }
}
