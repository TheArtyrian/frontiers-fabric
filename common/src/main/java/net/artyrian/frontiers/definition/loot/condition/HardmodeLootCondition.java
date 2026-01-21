package net.artyrian.frontiers.definition.loot.condition;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.misc.ModLootConditions;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record HardmodeLootCondition(boolean is_hard) implements LootItemCondition
{
    public static final MapCodec<HardmodeLootCondition> CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(
                            Codec.BOOL.fieldOf("predicate").forGetter(HardmodeLootCondition::is_hard)
                    )
                    .apply(instance, HardmodeLootCondition::new)
    );

    @Override
    public LootItemConditionType getType() {
        return ModLootConditions.HARDMODE_CHECK;
    }

    @Override
    public boolean test(LootContext lootContext)
    {
        ServerLevel world = lootContext.getLevel();
        MinecraftServer server = world.getServer();
        StateSaveLoad loader = StateSaveLoad.getServerState(server);
        boolean hardmode = loader.isInHardmode;

        return hardmode == is_hard;
    }

    public static LootItemCondition.Builder builder(boolean is_in_hardmode)
    {
        return () -> new HardmodeLootCondition(is_in_hardmode);
    }
}
