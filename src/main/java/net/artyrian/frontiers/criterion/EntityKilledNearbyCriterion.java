package net.artyrian.frontiers.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.advancement.AdvancementCriterion;
import net.minecraft.advancement.criterion.AbstractCriterion;
import net.minecraft.entity.EntityType;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.predicate.entity.EntityTypePredicate;
import net.minecraft.predicate.entity.LootContextPredicate;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.Optional;

public class EntityKilledNearbyCriterion extends AbstractCriterion<EntityKilledNearbyCriterion.Conditions>
{
    @Override
    public Codec<EntityKilledNearbyCriterion.Conditions> getConditionsCodec()
    {
        return EntityKilledNearbyCriterion.Conditions.CODEC;
    }

    public void trigger(ServerPlayerEntity player, EntityType<?> entity)
    {
        this.trigger(player, conditions -> conditions.matches(entity));
    }

    public static record Conditions(Optional<EntityTypePredicate> entity) implements AbstractCriterion.Conditions
    {
        public static final Codec<EntityKilledNearbyCriterion.Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityTypePredicate.CODEC.optionalFieldOf("entity").forGetter(EntityKilledNearbyCriterion.Conditions::entity)
                        )
                        .apply(instance, EntityKilledNearbyCriterion.Conditions::new)
        );

        public static AdvancementCriterion<EntityKilledNearbyCriterion.Conditions> any()
        {
            return ModCriteria.ENTITY_KILLED_NEARBY.create(new EntityKilledNearbyCriterion.Conditions(Optional.empty()));
        }

        public static AdvancementCriterion<EntityKilledNearbyCriterion.Conditions> of(EntityPredicate predicate)
        {
            return ModCriteria.ENTITY_KILLED_NEARBY.create(new EntityKilledNearbyCriterion.Conditions(predicate.type()));
        }

        public boolean matches(EntityType<?> type)
        {
            return (this.entity.isEmpty() || this.entity.get().matches(type));
        }

        @Override
        public Optional<LootContextPredicate> player()
        {
            return Optional.empty();
        }
    }
}
