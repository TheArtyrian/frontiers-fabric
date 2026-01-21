package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockState;

public class SpiritCandleItem extends BlockItem
{
    public SpiritCandleItem(Block block, Properties settings)
    {
        super(block, settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        BlockPos pos = context.getClickedPos();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        Level world = context.getLevel();
        BlockState state = world.getBlockState(pos);

        if (state.getBlock().equals(Blocks.CARVED_PUMPKIN))
        {
            if (!world.isClientSide)
            {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));

                PumpkinGolemEntity entity = ModEntity.PUMPKIN_GOLEM.get().create(world);
                if (entity != null)
                {
                    int ran = world.getRandom().nextIntBetweenInclusive(PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);
                    entity.setGolemStyle(ran);
                    entity.setGolemSleep(world.isDay());
                    entity.moveTo((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
                    world.addFreshEntity(entity);

                    for (ServerPlayer serverPlayerEntity : world.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0))) {
                        CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
                    }
                }
            }
            else
            {
                BlockState target = this.getBlock().defaultBlockState();
                SoundType blockSoundGroup = target.getSoundType();
                world.playSound(
                        player,
                        pos,
                        this.getPlaceSound(target),
                        SoundSource.BLOCKS,
                        (blockSoundGroup.getVolume() + 1.0F) / 2.0F,
                        blockSoundGroup.getPitch() * 0.8F
                );
            }

            stack.consume(1, player);
            return InteractionResult.sidedSuccess(world.isClientSide);
        }

        return super.useOn(context);
    }
}
