package net.artyrian.frontiers.definition.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.Optional;

import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.EntityTypePredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;

public class EntityKilledNearbyCriterion extends SimpleCriterionTrigger<EntityKilledNearbyCriterion.Conditions>
{
    @Override
    public Codec<Conditions> codec()
    {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayer player, EntityType<?> entity)
    {
        this.trigger(player, conditions -> conditions.matches(entity));
    }

    public record Conditions(Optional<EntityTypePredicate> entity) implements SimpleInstance
    {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityTypePredicate.CODEC.optionalFieldOf("entity").forGetter(Conditions::entity)
                        )
                        .apply(instance, Conditions::new)
        );

        public static Criterion<Conditions> any()
        {
            return ModCriteria.ENTITY_KILLED_NEARBY.get().createCriterion(new Conditions(Optional.empty()));
        }

        public static Criterion<Conditions> of(EntityPredicate predicate)
        {
            return ModCriteria.ENTITY_KILLED_NEARBY.get().createCriterion(new Conditions(predicate.entityType()));
        }

        public boolean matches(EntityType<?> type)
        {
            return (this.entity.isEmpty() || this.entity.get().matches(type));
        }

        @Override
        public Optional<ContextAwarePredicate> player()
        {
            return Optional.empty();
        }
    }
}
