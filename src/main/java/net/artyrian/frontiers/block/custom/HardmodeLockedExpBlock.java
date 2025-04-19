package net.artyrian.frontiers.block.custom;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.payloads.OreWitherPayload;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.event.GameEvent;
import net.minecraft.world.explosion.Explosion;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

public class HardmodeLockedExpBlock extends ExperienceDroppingBlock
{
    public HardmodeLockedExpBlock(IntProvider experienceDropped, Settings settings)
    {
        super(experienceDropped, settings);
    }

    @Override
    public void onExploded(BlockState state, World world, BlockPos pos, Explosion explosion, BiConsumer<ItemStack, BlockPos> stackMerger)
    {
        if (!state.isAir() && explosion.getDestructionType() != Explosion.DestructionType.TRIGGER_BLOCK) {
            Block block = state.getBlock();
            boolean bl = explosion.getCausingEntity() instanceof PlayerEntity;
            if (block.shouldDropItemsOnExplosion(explosion) && world instanceof ServerWorld serverWorld) {
                BlockEntity blockEntity = state.hasBlockEntity() ? world.getBlockEntity(pos) : null;
                LootContextParameterSet.Builder builder = new LootContextParameterSet.Builder(serverWorld)
                        .add(LootContextParameters.ORIGIN, Vec3d.ofCenter(pos))
                        .add(LootContextParameters.TOOL, ItemStack.EMPTY)
                        .addOptional(LootContextParameters.BLOCK_ENTITY, blockEntity)
                        .addOptional(LootContextParameters.THIS_ENTITY, explosion.getEntity());
                if (explosion.getDestructionType() == Explosion.DestructionType.DESTROY_WITH_DECAY) {
                    builder.add(LootContextParameters.EXPLOSION_RADIUS, explosion.getPower());
                }

                state.onStacksDropped(serverWorld, pos, ItemStack.EMPTY, bl);

                // HERE: Prevents stacks from dropping HM
                MinecraftServer server = world.getServer();
                StateSaveLoad loader = StateSaveLoad.getServerState(server);
                boolean hardmode = loader.isInHardmode;

                if (hardmode) state.getDroppedStacks(builder).forEach(stack -> stackMerger.accept(stack, pos));
                else if (bl)
                {
                    for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) world, pos)) {
                        ServerPlayNetworking.send(targeter, new OreWitherPayload(pos));
                    }
                }
            }

            world.setBlockState(pos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
            block.onDestroyedByExplosion(world, pos, explosion);
        }
    }


    @Override
    protected void onStacksDropped(BlockState state, ServerWorld world, BlockPos pos, ItemStack tool, boolean dropExperience)
    {
        StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
        boolean hardmode = loader.isInHardmode;

        if (hardmode) super.onStacksDropped(state, world, pos, tool, dropExperience);
    }
}
