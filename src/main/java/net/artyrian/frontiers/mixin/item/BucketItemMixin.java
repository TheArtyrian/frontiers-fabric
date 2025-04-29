package net.artyrian.frontiers.mixin.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.dimension.ModDimension;
import net.artyrian.frontiers.util.CragsPortal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends ItemMixinFrontiers
{
    @Inject(method = "onEmptied", at = @At("HEAD"))
    private void checkForCragsPortal(PlayerEntity player, World world, ItemStack stack, BlockPos pos, CallbackInfo ci)
    {
        if (
                world instanceof ServerWorld &&
                (world.getRegistryKey().equals(World.NETHER) || world.getRegistryKey().equals(ModDimension.CRAGS_LEVEL_KEY)) &&
                world.getFluidState(pos).isOf(Fluids.LAVA) &&
                world.getBlockState(pos.down()).isOf(ModBlocks.GLOWING_OBSIDIAN)
        )
        {
            Frontiers.LOGGER.warn("[FRONTIERS] CHECKING CRAGS PORTAL SPAWN!");
            CragsPortal.checkForEmptyPortalAround(world, pos);
        }
    }
}
