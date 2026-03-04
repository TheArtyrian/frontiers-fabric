package net.artyrian.frontiers.definition.advancement.criterion;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.artyrian.frontiers.reg.misc.ModCriteria;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.ContextAwarePredicate;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.SimpleCriterionTrigger;
import net.minecraft.server.level.ServerPlayer;

import java.util.Optional;

public class EnrageTowerSpawnerCriterion extends SimpleCriterionTrigger<EnrageTowerSpawnerCriterion.Conditions>
{
    @Override
    public Codec<Conditions> codec()
    {
        return Conditions.CODEC;
    }

    public void trigger(ServerPlayer player)
    {
        this.trigger(player, Conditions::trigger);
    }

    public record Conditions(Optional<ContextAwarePredicate> player) implements SimpleInstance
    {
        public static final Codec<Conditions> CODEC = RecordCodecBuilder.create(
                instance -> instance.group(
                                EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Conditions::player)
                        )
                        .apply(instance, Conditions::new)
        );

        public static Criterion<Conditions> any()
        {
            return ModCriteria.ENRAGE_TOWER_SPAWNER.get().createCriterion(new Conditions(Optional.empty()));
        }

        public boolean trigger()
        {
            return true;
        }
    }
}
