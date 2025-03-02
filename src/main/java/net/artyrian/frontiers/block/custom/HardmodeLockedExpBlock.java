package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class HardmodeLockedExpBlock extends ExperienceDroppingBlock
{
    public HardmodeLockedExpBlock(IntProvider experienceDropped, Settings settings)
    {
        super(experienceDropped, settings);
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player)
    {
        if (!world.isClient)
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(player.getWorld().getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode)
            {

            }
        }

        return super.onBreak(world, pos, state, player);
    }

    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
        boolean hardmode = loader.isInHardmode;

        if (hardmode) super.onStacksDropped(state, world, pos, tool, dropExperience);
    }
}
