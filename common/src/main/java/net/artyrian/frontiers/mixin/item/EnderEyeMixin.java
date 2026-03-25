package net.artyrian.frontiers.mixin.item;

import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.EnderEyeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EndPortalFrameBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnderEyeItem.class)
public abstract class EnderEyeMixin
{
    /** Originally Void Pearl code. */
    @Inject(method = "use", at = @At("HEAD"), cancellable = true)
    private void frontiers$disableUseForEye(Level level, Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir)
    {
        ItemStack itemStack = player.getItemInHand(hand);
        PlayerEnderChestContainer enderChestInventory = player.getEnderChestInventory();
        if (enderChestInventory != null)
        {
            if (!level.isClientSide)
            {
                VectorEventSync.Dual.fireEvent(level, new Vec3(player.getX(), player.getY(0.5), player.getZ()), player.position(), FRLevelEvents.Dual.VOID_OR_ENDER_EYE_SMASH, 1);

                level.playSound(null,
                        player.getX(),
                        player.getY(),
                        player.getZ(),
                        ModSounds.ENDER_EYE_SMASH.get(),
                        SoundSource.PLAYERS,
                        0.9F, 1.0F / (level.getRandom().nextFloat() * 0.4F + 0.8F)
                );

                player.openMenu(
                        new SimpleMenuProvider(
                                (i, playerInventory, playerEntity) -> ChestMenu.threeRows(i, playerInventory, enderChestInventory), frontiers$artyrian$displayedInvName(itemStack)
                        )
                );

                player.awardStat(Stats.OPEN_ENDERCHEST);
                itemStack.consume(1, player);
            }
        }
        cir.setReturnValue(InteractionResultHolder.sidedSuccess(itemStack, level.isClientSide()));
    }

    /** Originally Void Pearl code. */
    @Inject(method = "useOn", at = @At("HEAD"), cancellable = true)
    private void frontiers$disableUseOnForEye(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir)
    {
        BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos());
        if (blockstate.is(Blocks.END_PORTAL_FRAME)) cir.setReturnValue(InteractionResult.PASS);
    }

    @Unique
    private static Component frontiers$artyrian$displayedInvName(ItemStack stack)
    {
        return stack.getOrDefault(DataComponents.CUSTOM_NAME, stack.getItem().getName(stack));
    }
}
