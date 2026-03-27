package net.artyrian.frontiers.definition.item.custom.block;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.passive.PumpkinGolemEntity;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

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

        int variant = validBlockOrdinal(state);
        if (variant > 0)
        {
            if (!world.isClientSide)
            {
                Optional<Direction> dir = state.getOptionalValue(CarvedPumpkinBlock.FACING);
                world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_CLIENTS);
                world.levelEvent(LevelEvent.PARTICLES_DESTROY_BLOCK, pos, Block.getId(state));

                createGolem(world, pos, variant, dir);
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

    private int validBlockOrdinal(BlockState state)
    {
        Block block = state.getBlock();
        if (block.equals(Blocks.CARVED_PUMPKIN)) return 1;
        else if (block.equals(ModBlocks.WHITE_PUMPKIN.get())) return 2;
        else if (block.equals(ModBlocks.CARVED_MELON.get())) return 3;
        else if (block.equals(ModBlocks.CARVED_GLISTERING_MELON.get())) return 4;
        else return 0;
    }

    private void createGolem(Level level, BlockPos pos, int variant, Optional<Direction> dir)
    {
        PumpkinGolemEntity entity = ModEntity.PUMPKIN_GOLEM.get().create(level);
        if (entity != null)
        {
            int style = switch (variant)
            {
                case 2 -> PumpkinGolemEntity.WHITE;
                case 3 -> PumpkinGolemEntity.MELON;
                case 4 -> PumpkinGolemEntity.GLISTER;
                default -> level.getRandom().nextIntBetweenInclusive(PumpkinGolemEntity.MIN_STYLE, PumpkinGolemEntity.MAX_STYLE);
            };
            entity.setGolemStyle(style);
            entity.setGolemSleep(level.isDay());
            entity.moveTo((double)pos.getX() + 0.5, (double)pos.getY() + 0.05, (double)pos.getZ() + 0.5, 0.0F, 0.0F);
            dir.ifPresent((rect) -> entity.lookAt(EntityAnchorArgument.Anchor.EYES, Vec3.atCenterOf(pos.relative(rect))));
            level.addFreshEntity(entity);

            for (ServerPlayer serverPlayerEntity : level.getEntitiesOfClass(ServerPlayer.class, entity.getBoundingBox().inflate(5.0)))
            {
                CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, entity);
            }
        }
    }
}
