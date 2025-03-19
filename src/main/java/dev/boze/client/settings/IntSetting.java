package dev.boze.client.settings;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.nbt.NbtCompound;

import java.util.Locale;
import java.util.function.BooleanSupplier;

public class IntSetting extends Setting<Integer> {
   private int value;
   private final int defaultValue;
   public final int min;
   public final int max;
   public final int step;
   public final boolean field937;

   public IntSetting(String name, int value, int min, int max, int step, String description) {
      super(name, description);
      this.value = value;
      this.defaultValue = value;
      this.min = min;
      this.max = max;
      this.step = step;
      this.field937 = false;
   }

   public IntSetting(String name, int value, int min, int max, int step, String description, BooleanSupplier visibility) {
      super(name, description, visibility);
      this.value = value;
      this.defaultValue = value;
      this.min = min;
      this.max = max;
      this.step = step;
      this.field937 = false;
   }

   public IntSetting(String name, int value, int min, int max, int step, String description, Setting parent) {
      super(name, description, parent);
      this.value = value;
      this.defaultValue = value;
      this.min = min;
      this.max = max;
      this.step = step;
      this.field937 = false;
   }

   public IntSetting(String name, int value, int min, int max, int step, String description, BooleanSupplier visibility, Setting parent) {
      super(name, description, visibility, parent);
      this.value = value;
      this.defaultValue = value;
      this.min = min;
      this.max = max;
      this.step = step;
      this.field937 = false;
   }

   public IntSetting(String name, int value, String description) {
      super(name, description);
      this.value = value;
      this.defaultValue = value;
      this.min = Integer.MIN_VALUE;
      this.max = Integer.MAX_VALUE;
      this.step = 1;
      this.field937 = true;
   }

   public IntSetting(String name, int value, String description, BooleanSupplier visibility) {
      super(name, description, visibility);
      this.value = value;
      this.defaultValue = value;
      this.min = Integer.MIN_VALUE;
      this.max = Integer.MAX_VALUE;
      this.step = 1;
      this.field937 = true;
   }

   public IntSetting(String name, int value, String description, Setting parent) {
      super(name, description, parent);
      this.value = value;
      this.defaultValue = value;
      this.min = Integer.MIN_VALUE;
      this.max = Integer.MAX_VALUE;
      this.step = 1;
      this.field937 = true;
   }

   public IntSetting(String name, int value, String description, BooleanSupplier visibility, Setting parent) {
      super(name, description, visibility, parent);
      this.value = value;
      this.defaultValue = value;
      this.min = Integer.MIN_VALUE;
      this.max = Integer.MAX_VALUE;
      this.step = 1;
      this.field937 = true;
   }

   public Integer method434() {
      return this.value;
   }

   public Integer method435() {
      return this.value = this.defaultValue;
   }

   public Integer method436(Integer newVal) {
      this.value = newVal;
      if (this.callback != null) {
         this.callback.accept(this.value);
      }

      return this.value;
   }

   @Override
   public boolean buildCommand(LiteralArgumentBuilder<CommandSource> builder) {
      builder.then(method403(this.method210().toLowerCase(Locale.ROOT)).then(method402("value", IntegerArgumentType.integer()).executes(this::lambda$build$0)));
      return true;
   }

   @Override
   public NbtCompound save(NbtCompound tag) {
      tag.putInt("Value", this.value);
      return tag;
   }

   public Integer method437(NbtCompound tag) {
      if (tag.contains("Value")) {
         this.value = tag.getInt("Value");
      }

      return this.value;
   }

   // $VF: synthetic method
   // $VF: bridge method
   @Override
   public Object load(NbtCompound nbtCompound) {
      return this.method437(nbtCompound);
   }

   // $VF: synthetic method
   // $VF: bridge method
   @Override
   public Object setValue(Object object) {
      return this.method436((Integer)object);
   }

   // $VF: synthetic method
   // $VF: bridge method
   @Override
   public Object resetValue() {
      return this.method435();
   }

   // $VF: synthetic method
   // $VF: bridge method
   @Override
   public Object getValue() {
      return this.method434();
   }

   private int lambda$build$0(CommandContext var1) throws CommandSyntaxException {
      this.method436((Integer)var1.getArgument("value", Integer.class));
      return 1;
   }
}
