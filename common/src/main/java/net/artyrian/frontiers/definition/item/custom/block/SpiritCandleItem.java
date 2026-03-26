package net.artyrian.frontiers.definition.item.custom.block;

import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModBlocks;
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

        if (state.is(Blocks.CARVED_PUMPKIN) || state.is(ModBlocks.WHITE_PUMPKIN.get()))
        {
            boolean white = state.is(ModBlocks.WHITE_PUMPKIN.get());
            if (!world.isClientSide)
            {
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));

                createGolem(world, pos, white);
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

    private void createGolem(Level level, BlockPos pos, boolean secret)
    {
        PumpkinGolemEntity entity = ModEntity.PUMPKIN_GOLEM.get().create(level);
        if (entity != null)
        {
            int style = (secret) ? PumpkinGolemEntity.SECRET_STYLE : level.getRandom().nextIntBetweenInclusive(PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);
            entity.setGolemStyle(style);
            entity.setGolemSleep(level.isDay());
            entity.moveTo((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
            level.addFreshEntity(entity);

            for (ServerPlayer serverPlayerEntity : level.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0)))
            {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
            }
        }
    }
}
