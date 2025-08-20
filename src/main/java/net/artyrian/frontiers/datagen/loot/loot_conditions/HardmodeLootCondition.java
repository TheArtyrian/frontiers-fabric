package net.artyrian.frontiers.datagen.loot.loot_conditions;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.minecraft.loot.condition.*;
import net.minecraft.loot.context.LootContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;

public record HardmodeLootCondition(boolean is_hard) implements LootCondition
{
    public static final MapCodec<HardmodeLootCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codec.BOOL.fieldOf("predicate").forGetter(HardmodeLootCondition::is_hard)
                    )
                    .apply(instance, HardmodeLootCondition::new)
    );

    @Override
    public LootConditionType getType() {
        return ModLootConditions.HARDMODE_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext)
    {
        ServerWorld world = lootContext.getWorld();
        MinecraftServer server = world.getServer();
        StateSaveLoad loader = StateSaveLoad.getServerState(server);
        boolean hardmode = loader.isInHardmode;

        return hardmode == is_hard;
    }

    public static LootCondition.Builder builder(boolean is_in_hardmode)
    {
        return () -> new HardmodeLootCondition(is_in_hardmode);
    }
}
