package dev.boze.client.command.arguments;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import dev.boze.client.settings.BlockEntitySetting;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.command.CommandSource;
import net.minecraft.registry.Registries;

class BlockEntityTypeArgument implements ArgumentType<BlockEntityType<?>> {
   private BlockEntityTypeArgument() {
   }

   public static BlockEntityTypeArgument method978() {
      return new BlockEntityTypeArgument();
   }

   public BlockEntityType<?> method979(StringReader reader) {
      String var2 = reader.getRemaining();
      reader.setCursor(reader.getTotalLength());
      return BlockEntitySetting.method440(var2);
   }

   public static BlockEntityType<?> method980(CommandContext<?> context, String name) {
      return (BlockEntityType<?>)context.getArgument(name, BlockEntityType.class);
   }

   public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
      return CommandSource.suggestMatching(
         (Iterable)Registries.BLOCK_ENTITY_TYPE.stream().map(BlockEntityTypeArgument::lambda$listSuggestions$0).collect(Collectors.toList()), builder
      );
   }

   public Collection<String> getExamples() {
      return Arrays.asList(
         Registries.BLOCK_ENTITY_TYPE.getId((BlockEntityType)Registries.BLOCK_ENTITY_TYPE.get(0)).getPath(),
         Registries.BLOCK_ENTITY_TYPE.getId((BlockEntityType)Registries.BLOCK_ENTITY_TYPE.get(1)).getPath(),
         Registries.BLOCK_ENTITY_TYPE.getId((BlockEntityType)Registries.BLOCK_ENTITY_TYPE.get(2)).getPath()
      );
   }

   // $VF: synthetic method
   // $VF: bridge method
   public Object parse(StringReader stringReader) throws CommandSyntaxException {
      return this.method979(stringReader);
   }

   private static String lambda$listSuggestions$0(BlockEntityType var0) {
      return Registries.BLOCK_ENTITY_TYPE.getId(var0).getPath();
   }
}
