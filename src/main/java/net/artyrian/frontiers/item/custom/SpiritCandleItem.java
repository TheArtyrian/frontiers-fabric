package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.entity.passive.PumpkinGolemEntity;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.IronGolemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;

public class SpiritCandleItem extends BlockItem
{
    public SpiritCandleItem(Block block, Settings settings)
    {
        super(block, settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        BlockPos pos = context.getBlockPos();
        ItemStack stack = context.getStack();
        PlayerEntity player = context.getPlayer();
        World world = context.getWorld();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock().equals(Blocks.CARVED_PUMPKIN))
        {
            if (!world.isClient)
            {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_LISTENERS);
                world.syncWorldEvent(WorldEvents.BLOCK_BROKEN, pos, Block.getRawIdFromState(state));

                PumpkinGolemEntity entity = ModEntity.PUMPKIN_GOLEM.create(world);
                if (entity != null)
                {
                    int ran = world.getRandom().nextBetween(PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);
                    entity.setGolemStyle(ran);
                    entity.setGolemSleep(world.isDay());
                    entity.refreshPositionAndAngles((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
                    world.spawnEntity(entity);

                    for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, entity.getBoundingBox().expand(5.0))) {
                        Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
                    }
                }
            }
            else
            {
                BlockState target = this.getBlock().getDefaultState();
                BlockSoundGroup blockSoundGroup = target.getSoundGroup();
                world.playSound(
                        player,
                        pos,
                        this.getPlaceSound(target),
                        SoundCategory.BLOCKS,
                        (blockSoundGroup.getVolume() + 1.0F) / 2.0F,
                        blockSoundGroup.getPitch() * 0.8F
                );
            }

            stack.decrementUnlessCreative(1, player);
            return ActionResult.success(world.isClient);
        }

        return super.useOnBlock(context);
    }
}
