package net.artyrian.frontiers.definition.block.custom;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

import java.util.function.BiConsumer;

public class HardmodeLockedExpBlock extends DropExperienceBlock
{
    private final IntProvider xpRange;

    public HardmodeLockedExpBlock(IntProvider experienceDropped, Properties settings)
    {
        super(experienceDropped, settings);
        this.xpRange = experienceDropped;
    }

    @Override
    public void onExplosionHit(BlockState state, Level world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger)
    {
        if (!state.isAir() && explosion.getBlockInteraction() != Explosion.BlockInteraction.TRIGGER_BLOCK)
        {
            Block block = state.getBlock();
            boolean playerfault = explosion.getIndirectSourceEntity() instanceof Player;
            if (block.dropFromExplosion(explosion) && world instanceof ServerLevel serverWorld)
            {
                BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                LootParams.Builder builder = new LootParams.Builder(serverWorld)
                        .withParameter(LootContextParams.ORIGIN, Vec3.atCenterOf(pos))
                        .withParameter(LootContextParams.TOOL, ItemStack.EMPTY)
                        .withOptionalParameter(LootContextParams.BLOCK_ENTITY, blockEntity)
                        .withOptionalParameter(LootContextParams.THIS_ENTITY, explosion.getDirectSourceEntity());
                if (explosion.getBlockInteraction() == Explosion.BlockInteraction.DESTROY_WITH_DECAY)
                {
                    builder.withParameter(LootContextParams.EXPLOSION_RADIUS, explosion.radius());
                }

                state.spawnAfterBreak(serverWorld, pos, ItemStack.EMPTY, playerfault);

                // HERE: Prevents stacks from dropping HM
                MinecraftServer server = world.getServer();
                StateSaveLoad loader = StateSaveLoad.getServerState(server);
                boolean hardmode = loader.isInHardmode;

                if (hardmode) state.getDrops(builder).forEach(stack -> stackMerger.accept(stack, pos));
                else if (playerfault) VectorEventSync.Local.fireEvent(world, pos, FRLevelEvents.Local.ORE_WITHER, 0);
            }

            world.setBlock(pos, Blocks.AIR.defaultBlockState(), Block.UPDATE_ALL);
            block.wasExploded(world, pos, explosion);
        }
    }


    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
        boolean hardmode = loader.isInHardmode;

        if (hardmode) super.spawnAfterBreak(state, world, pos, tool, dropExperience);
    }
}
