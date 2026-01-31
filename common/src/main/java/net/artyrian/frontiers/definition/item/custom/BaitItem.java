package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.projectile.BaitEntity;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ProjectileItem;
import net.minecraft.world.level.Level;

public class BaitItem extends Item implements ProjectileItem
{
    public BaitItem(Item.Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);

        world.playSound(
                null, user.getX(), user.getY(), user.getZ(), ModSounds.BAIT_THROW.get(), SoundSource.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        if (!world.isClientSide)
        {
            BaitEntity bait = new BaitEntity(world, user);
            bait.setItem(itemStack);
            bait.shootFromRotation(user, user.getXRot(), user.getYRot(), 0.0F, 0.6F, 1.0F);
            world.addFreshEntity(bait);
        }

        user.awardStat(Stats.ITEM_USED.get(this));
        itemStack.consume(1, user);
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }

    @Override
    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        BaitEntity bait = new BaitEntity(world, pos.x(), pos.y(), pos.z());
        bait.setItem(stack);
        return bait;
    }
}