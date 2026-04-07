package net.artyrian.frontiers.definition.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.reg.misc.FRCriteria;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

public class BrewedItemCriterion extends SimpleCriterionTrigger<BrewedItemCriterion.Conditions>
{
    @Override
    public Codec<Conditions> codec()
    {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, ItemStack stack)
    {
        this.trigger(player, conditions -> conditions.matches(stack));
    }

    public record Conditions(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> item) implements SimpleInstance
    {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Conditions::player),
                                ItemPredicate.CODEC.optionalFieldOf("item").forGetter(Conditions::item)
                        )
                        .apply(instance, Conditions::new)
        );

        public static Criterion<Conditions> any()
        {
            return FRCriteria.BREWED_ITEM.get().createCriterion(new Conditions(Optional.empty(), Optional.empty()));
        }

        public static Criterion<Conditions> of(Item item)
        {
            return FRCriteria.BREWED_ITEM.get().createCriterion(new Conditions(Optional.empty(), Optional.of(ItemPredicate.Builder.item().of(item).build())));
        }

        public boolean matches(ItemStack stack)
        {
            return (this.item.isEmpty() || (this.item.get()).test(stack));
        }
    }
}
