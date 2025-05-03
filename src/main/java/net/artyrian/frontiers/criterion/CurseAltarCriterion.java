package net.artyrian.frontiers.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.advancement.criterion.EnchantedItemCriterion;
import net.minecraft.item.ItemStack;
import net.minecraft.predicate.NumberRange;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.predicate.item.ItemPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class CurseAltarCriterion extends AbstractCriterion<CurseAltarCriterion.Conditions>
{
    @Override
    public Codec<CurseAltarCriterion.Conditions> getConditionsCodec()
    {
        return CurseAltarCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, ItemStack stack)
    {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public static record Conditions(Optional<LootContextPredicate> player, Optional<ItemPredicate> item)
            implements AbstractCriterion.Conditions
    {
        public static final Codec<CurseAltarCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.LOOT_CONTEXT_PREDICATE_CODEC.optionalFieldOf("player").forGetter(CurseAltarCriterion.Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(CurseAltarCriterion.Conditions::item)
                        )
                        .apply(instance, CurseAltarCriterion.Conditions::new)
        );

        public static AdvancementCriterion<CurseAltarCriterion.Conditions> any()
        {
            return ModCriteria.USED_CURSE_ALTAR.create(new CurseAltarCriterion.Conditions(Optional.empty(), Optional.empty()));
        }

        public boolean matches(ItemStack stack)
        {
            return (this.item.isEmpty() || ((ItemPredicate) this.item.get()).test(stack));
        }
    }
}
