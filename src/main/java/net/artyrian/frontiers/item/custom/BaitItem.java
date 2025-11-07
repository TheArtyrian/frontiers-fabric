package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.entity.projectile.BaitEntity;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.sound.SoundCategory;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;

public class BaitItem extends Item implements ProjectileItem
{
    public BaitItem(Item.Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);

        world.playSound(
                null, user.getX(), user.getY(), user.getZ(), ModSounds.BAIT_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClient)
        {
            BaitEntity bait = new BaitEntity(world, user);
            bait.setItem(itemStack);
            bait.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, 0.6F, 1.0F);
            world.spawnEntity(bait);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction)
    {
        BaitEntity bait = new BaitEntity(world, pos.getX(), pos.getY(), pos.getZ());
        bait.setItem(stack);
        return bait;
    }
}