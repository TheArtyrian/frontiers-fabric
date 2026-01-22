package net.artyrian.frontiers.mixin.block;

import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Collections;
import java.util.List;

@Debug(export = true)
@Mixin(Block.class)
public abstract class BlockMixin extends AbstractBlockMixin
{
    @Shadow public abstract BlockState withPropertiesOf(BlockState state);
    @Shadow public abstract StateDefinition<Block, BlockState> getStateDefinition();
    @Shadow public abstract BlockState defaultBlockState();
    @Shadow @Final protected StateDefinition<Block, BlockState> stateDefinition;
    @Shadow protected final void registerDefaultState(BlockState state) {};
    @Shadow public BlockState getStateForPlacement(BlockPlaceContext ctx) { return null; }
    @Shadow public static boolean isExceptionForConnection(BlockState state) { return false; }

    @Inject(method = "createBlockStateDefinition", at = @At("TAIL"))
    public void frontiers$appendMix(StateDefinition.Builder<Block, BlockState> builder, CallbackInfo ci)
    {

    }

    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;)Ljava/util/List;",
            at = @At("RETURN"), cancellable = true)
    private static void frontiers$appendHardmodeOreCheck(BlockState state, ServerLevel world, BlockPos pos, @Nullable BlockEntity blockEntity, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        if (state.is(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode) cir.setReturnValue(Collections.emptyList());
        }
    }

    @Inject(method = "getDrops(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"), cancellable = true)
    private static void frontiers$appendHardmodeOreCheckEntity(BlockState state, ServerLevel world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        if (state.is(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode) cir.setReturnValue(Collections.emptyList());
        }
    }

    @Inject(method = "updateEntityAfterFallOn", at = @At("HEAD"), cancellable = true)
    private void frontiers$HandleForSlimeShoes(BlockGetter world, Entity entity, CallbackInfo ci)
    {
        if (entity instanceof LivingEntity living)
        {
            ItemStack feet = living.getItemBySlot(EquipmentSlot.FEET);
            Vec3 vec3d = entity.getDeltaMovement();
            if (vec3d.y < -0.5 && feet.is(ModItem.SLIME_SHOES.get()))
            {
                if (vec3d.y <= -0.7)
                {
                    feet.hurtAndBreak(1, living, EquipmentSlot.FEET);
                }
                entity.setDeltaMovement(vec3d.x, -vec3d.y * 0.8, vec3d.z);
                ci.cancel();
            }
        }
    }
}
