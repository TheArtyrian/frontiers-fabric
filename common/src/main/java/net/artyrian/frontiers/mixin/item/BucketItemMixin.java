package net.artyrian.frontiers.mixin.item;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.util.CragsPortal;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.misc.ModDimension;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BucketItem.class)
public abstract class BucketItemMixin extends ItemMixinFrontiers
{
    @Inject(method = "checkExtraContent", at = @At("HEAD"))
    private void checkForCragsPortal(Player player, Level world, ItemStack stack, BlockPos pos, CallbackInfo ci)
    {
        if (
                world instanceof ServerLevel &&
                (world.dimension().equals(Level.NETHER) || world.dimension().equals(ModDimension.CRAGS_LEVEL_KEY)) &&
                world.getFluidState(pos).is(Fluids.LAVA) &&
                world.getBlockState(pos.below()).is(ModBlocks.GLOWING_OBSIDIAN.get())
        )
        {
            Frontiers.LOGGER.warn("[FRONTIERS] CHECKING CRAGS PORTAL SPAWN!");
            CragsPortal.checkForEmptyPortalAround(world, pos);
        }
    }
}
