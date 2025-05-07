package net.artyrian.frontiers.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class BeaconBrimtanCriterion extends AbstractCriterion<BeaconBrimtanCriterion.Conditions>
{
    @Override
    public Codec<BeaconBrimtanCriterion.Conditions> getConditionsCodec()
    {
        return BeaconBrimtanCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player)
    {
        this.trigger(player, Conditions::trigger);
    }

    public static record Conditions(Optional<LootContextPredicate> player)
            implements AbstractCriterion.Conditions
    {
        public static final Codec<BeaconBrimtanCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(BeaconBrimtanCriterion.Conditions::player)
                        )
                        .apply(instance, BeaconBrimtanCriterion.Conditions::new)
        );

        public static AdvancementCriterion<BeaconBrimtanCriterion.Conditions> any()
        {
            return ModCriteria.BEACON_POWERED_WITH_BRIMTAN.create(new BeaconBrimtanCriterion.Conditions(Optional.empty()));
        }

        public boolean trigger()
        {
            return true;
        }
    }
}
