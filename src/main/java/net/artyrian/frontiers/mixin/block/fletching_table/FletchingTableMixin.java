package net.artyrian.frontiers.mixin.block.fletching_table;

import net.artyrian.frontiers.client.screen.fletching.FletchingTableScreenHandler;
import net.artyrian.frontiers.misc.ModStats;
import net.artyrian.frontiers.mixin.block.crafting_table.CraftingTableMixin;
import net.minecraft.block.BlockState;
import net.minecraft.block.FletchingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FletchingTableBlock.class)
public abstract class FletchingTableMixin extends CraftingTableMixin
{
    @Unique @Final private static Text SCREEN_TITLE = Text.translatable("container.frontiers.fletching");

    @Inject(method = "onUse", at = @At(value = "TAIL"), cancellable = true)
    public void changeFactory(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit, CallbackInfoReturnable<ActionResult> cir)
    {
        if (world.isClient) {
            cir.setReturnValue(ActionResult.SUCCESS);
        } else {
            player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            player.incrementStat(ModStats.INTERACT_WITH_FLETCHING_TABLE);
            cir.setReturnValue(ActionResult.CONSUME);
        }
    }

    @Override
    public void screenInjector(BlockState state, World world, BlockPos pos, CallbackInfoReturnable<NamedScreenHandlerFactory> cir)
    {
        cir.setReturnValue(new SimpleNamedScreenHandlerFactory(
                (syncId, inventory, player) -> new FletchingTableScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), SCREEN_TITLE
        ));
    }
}
