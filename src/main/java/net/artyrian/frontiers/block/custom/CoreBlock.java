package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.misc.ModDamageType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CoreBlock extends Block
{
    public CoreBlock(Settings settings)
    {
        super(settings);
    }


    @Override
    public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
        if (entity instanceof LivingEntity) {
            if (!entity.bypassesSteppingEffects())
            {
                entity.damage(ModDamageType.of(world, ModDamageType.CORE), 10.0F);
            }
            entity.setOnFireFor(10);
        }

        super.onSteppedOn(world, pos, state, entity);
    }

    @Override
    protected void onBlockBreakStart(BlockState state, World world, BlockPos pos, PlayerEntity player)
    {
        if (!world.isClient && !player.isCreative())
        {
            player.setOnFireFor(2);
        }
    }
}
