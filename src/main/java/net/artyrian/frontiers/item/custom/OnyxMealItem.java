package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.event.GameEvent;

public class OnyxMealItem extends Item
{
    public OnyxMealItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World world = context.getWorld();
        BlockPos blockPos = context.getBlockPos();
        BlockState state = world.getBlockState(blockPos);
        PlayerEntity player = context.getPlayer();
        ItemStack stack = context.getStack();

        if ((state.isOf(Blocks.NETHER_WART) || state.isOf(ModBlocks.WARPED_WART)) && player instanceof PlayerEntity)
        {
            int i = state.get(NetherWartBlock.AGE);
            if (i < 3)
            {
                if (!world.isClient)
                {
                    state = state.with(NetherWartBlock.AGE, i + 1);
                    world.setBlockState(blockPos, state, Block.NOTIFY_LISTENERS);

                    player.emitGameEvent(GameEvent.ITEM_INTERACT_FINISH);
                    world.syncWorldEvent(WorldEvents.BONE_MEAL_USED, blockPos, 15);

                    stack.decrementUnlessCreative(1, player);
                }
                createParticles(world, blockPos, 15);
                return ActionResult.success(world.isClient);
            }
        }

        return ActionResult.PASS;
    }

    public static void createParticles(WorldAccess world, BlockPos pos, int count) {
        BlockState blockState = world.getBlockState(pos);
        ParticleUtil.spawnParticlesAround(world, pos, count, 1.0, 1.0, false, ParticleTypes.HAPPY_VILLAGER);
    }
}
