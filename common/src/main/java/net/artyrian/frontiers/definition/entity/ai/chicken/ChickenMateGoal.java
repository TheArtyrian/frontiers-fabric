package net.artyrian.frontiers.definition.entity.ai.chicken;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;

public class ChickenMateGoal extends BreedGoal
{
    private static final TargetingConditions VALID_MATE_PREDICATE = TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight();
    private final Class<? extends Animal> entityClass;
    private final Class<? extends Animal> otherClass;

    public ChickenMateGoal(Animal animal, Class<? extends Animal> otherClass, double speed)
    {
        super(animal, speed);
        this.entityClass = animal.getClass();
        this.otherClass = otherClass;
    }

    @Override
    public boolean canUse()
    {
        if (!this.animal.isInLove())
        {
            return false;
        }
        else
        {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    @Nullable
    private Animal getFreePartner()
    {
        List<? extends Animal> list = Stream.concat(
                    this.level.getNearbyEntities(this.entityClass, VALID_MATE_PREDICATE, this.animal, this.animal.getBoundingBox().inflate(8.0)).stream(),
                    this.level.getNearbyEntities(this.otherClass, VALID_MATE_PREDICATE, this.animal, this.animal.getBoundingBox().inflate(8.0)).stream()
                )
                .toList();

        double d = Double.MAX_VALUE;
        Animal animalEntity = null;

        for (Animal animalEntity2 : list)
        {
            if (this.animal.canMate(animalEntity2) && !animalEntity2.isPanicking() && this.animal.distanceToSqr(animalEntity2) < d)
            {
                animalEntity = animalEntity2;
                d = this.animal.distanceToSqr(animalEntity2);
            }
        }

        return animalEntity;
    }
}
