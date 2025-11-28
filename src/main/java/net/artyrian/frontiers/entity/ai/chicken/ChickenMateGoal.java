package net.artyrian.frontiers.entity.ai.chicken;

import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.AnimalMateGoal;
import net.minecraft.entity.passive.AnimalEntity;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Stream;

public class ChickenMateGoal extends AnimalMateGoal
{
    private static final TargetPredicate VALID_MATE_PREDICATE = TargetPredicate.createNonAttackable().setBaseMaxDistance(8.0).ignoreVisibility();
    private final Class<? extends AnimalEntity> entityClass;
    private final Class<? extends AnimalEntity> otherClass;

    public ChickenMateGoal(AnimalEntity animal, Class<? extends AnimalEntity> otherClass, double speed)
    {
        super(animal, speed);
        this.entityClass = animal.getClass();
        this.otherClass = otherClass;
    }

    @Override
    public boolean canStart()
    {
        if (!this.animal.isInLove())
        {
            return false;
        }
        else
        {
            this.mate = this.findMate();
            return this.mate != null;
        }
    }

    @Nullable
    private AnimalEntity findMate()
    {
        List<? extends AnimalEntity> list = Stream.concat(
                    this.world.getTargets(this.entityClass, VALID_MATE_PREDICATE, this.animal, this.animal.getBoundingBox().expand(8.0)).stream(),
                    this.world.getTargets(this.otherClass, VALID_MATE_PREDICATE, this.animal, this.animal.getBoundingBox().expand(8.0)).stream()
                )
                .toList();

        double d = Double.MAX_VALUE;
        AnimalEntity animalEntity = null;

        for (AnimalEntity animalEntity2 : list)
        {
            if (this.animal.canBreedWith(animalEntity2) && !animalEntity2.isPanicking() && this.animal.squaredDistanceTo(animalEntity2) < d)
            {
                animalEntity = animalEntity2;
                d = this.animal.squaredDistanceTo(animalEntity2);
            }
        }

        return animalEntity;
    }
}
