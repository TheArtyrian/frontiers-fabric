package net.artyrian.frontiers.mixin.frontiers;

import net.artyrian.frontiers.definition.block.custom.HardmodeLockedExpBlock;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.minecraft.core.BlockPos;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockExtension;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// FUCK YOU NEOFORGE FOR MAKING ME MIXIN MY OWN FUCKING CLASSES TO WORK WITH YOUR MODLOADER ON ML
// HAHAHAHAHAHAHAHAHAHAHAHA
@Mixin(HardmodeLockedExpBlock.class)
public class IGenuinelyHateNeoforgeSoMuch implements IBlockExtension
{
    @Final @Shadow private IntProvider xpRange;

    @Override
    public int getExpDrop(BlockState state, LevelAccessor level, BlockPos pos, @Nullable BlockEntity blockEntity, @Nullable Entity breaker, ItemStack tool) {
        StateSaveLoad loader = StateSaveLoad.getServerState(level.getServer());
        boolean hardmode = loader.isInHardmode;

        return (hardmode) ? this.xpRange.sample(level.getRandom()) : 0;
    }
}
