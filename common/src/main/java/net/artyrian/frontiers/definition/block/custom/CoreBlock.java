package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.reg.misc.ModDamageType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class CoreBlock extends Block
{
    public CoreBlock(Properties settings)
    {
        super(settings);
    }


    @Override
    public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof LivingEntity)
        {
            if (!entity.isSteppingCarefully())
            {
                entity.hurt(ModDamageType.of(world, ModDamageType.CORE), 10.0F);
            }
            entity.igniteForSeconds(10);
        }

        super.stepOn(world, pos, state, entity);
    }

    @Override
    protected void attack(BlockState state, Level world, BlockPos pos, Player player)
    {
        if (!world.isClientSide && !player.isCreative())
        {
            player.igniteForSeconds(2);
        }
    }
}
