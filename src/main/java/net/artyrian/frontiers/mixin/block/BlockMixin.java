package net.artyrian.frontiers.mixin.block;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.custom.HardmodeLockedExpBlock;
import net.artyrian.frontiers.data.world.StateSaveLoad;
import net.artyrian.frontiers.effect.ModStatusEffects;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
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
    @Shadow public abstract BlockState getStateWithProperties(BlockState state);
    @Shadow public abstract StateManager<Block, BlockState> getStateManager();
    @Shadow public abstract BlockState getDefaultState();
    @Shadow @Final protected StateManager<Block, BlockState> stateManager;
    @Shadow protected final void setDefaultState(BlockState state) {};
    @Shadow public BlockState getPlacementState(ItemPlacementContext ctx) {return null;}
    @Shadow public static boolean cannotConnect(BlockState state) { return false; }

    @Inject(method = "appendProperties", at = @At("TAIL"))
    public void appendMix(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {

    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;",
            at = @At("RETURN"), cancellable = true)
    private static void appendHardmodeOreCheck(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        if (state.isIn(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode) cir.setReturnValue(Collections.emptyList());
        }
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;",
            at = @At("RETURN"), cancellable = true)
    private static void appendHardmodeOreCheckEntity(BlockState state, ServerWorld world, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir)
    {
        if (state.isIn(ModTags.Blocks.ONLY_DROP_IN_HARDMODE))
        {
            StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode) cir.setReturnValue(Collections.emptyList());
        }
    }

    @Inject(method = "onEntityLand", at = @At("HEAD"), cancellable = true)
    private void frontiersHandleForSlimeShoes(BlockView world, Entity entity, CallbackInfo ci)
    {
        if (entity instanceof LivingEntity living)
        {
            ItemStack feet = living.getEquippedStack(EquipmentSlot.FEET);
            Vec3d vec3d = entity.getVelocity();
            if (vec3d.y < -0.5 && feet.isOf(ModItem.SLIME_SHOES))
            {
                if (vec3d.y <= -0.7)
                {
                    feet.damage(1, living, EquipmentSlot.FEET);
                }
                entity.setVelocity(vec3d.x, -vec3d.y * 0.8, vec3d.z);
                ci.cancel();
            }
        }
    }
}
