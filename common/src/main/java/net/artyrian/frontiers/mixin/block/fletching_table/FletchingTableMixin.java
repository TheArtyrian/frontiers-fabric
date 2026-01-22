package net.artyrian.frontiers.mixin.block.fletching_table;

import net.artyrian.frontiers.definition.menu.fletching.FletchingTableScreenHandler;
import net.artyrian.frontiers.mixin.block.crafting_table.CraftingTableMixin;
import net.artyrian.frontiers.reg.misc.ModStats;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.FletchingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FletchingTableBlock.class)
public abstract class FletchingTableMixin extends CraftingTableMixin
{
    @Unique @Final private static Component SCREEN_TITLE = Component.translatable("container.frontiers.fletching");

    @Inject(method = "useWithoutItem", at = @At(value = "TAIL"), cancellable = true)
    public void changeFactory(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir)
    {
        if (world.isClientSide)
        {
            cir.setReturnValue(InteractionResult.SUCCESS);
        }
        else
        {
            player.openMenu(state.getMenuProvider(world, pos));
            player.awardStat(ModStats.INTERACT_WITH_FLETCHING_TABLE.get());
            cir.setReturnValue(InteractionResult.CONSUME);
        }
    }

    @Override
    public void screenInjector(BlockState state, Level world, BlockPos pos, CallbackInfoReturnable<MenuProvider> cir)
    {
        cir.setReturnValue(new SimpleMenuProvider(
                (syncId, inventory, player) -> new FletchingTableScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), SCREEN_TITLE
        ));
    }
}
