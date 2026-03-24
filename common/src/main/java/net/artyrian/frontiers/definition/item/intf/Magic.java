package net.artyrian.frontiers.definition.item.intf;

import net.artyrian.frontiers.definition.entity.intf.ManaUser;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public interface Magic
{
    boolean onCast(ManaUser user, Level level, ItemStack stack, @Nullable InteractionHand hand, Vec3 position);
    boolean canCast(ManaUser user);
}
