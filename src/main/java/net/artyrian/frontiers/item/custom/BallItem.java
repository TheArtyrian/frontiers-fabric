package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.entity.projectile.BallEntity;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.SnowballEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class BallItem extends Item
{
    private final Formatting TEXT_COLOR;
    private final int bounces;

    public BallItem(Formatting color, Settings settings)
    {
        this(color, 0, settings);
    }

    public BallItem(Formatting color, int bounces, Settings settings)
    {
        super(settings);
        this.TEXT_COLOR = color;
        this.bounces = bounces;
    }

    public Formatting getColor() { return TEXT_COLOR; }
    public int getBounces() { return this.bounces; }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        world.playSound(null, user.getX(), user.getY(), user.getZ(), ModSounds.BALL_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        if (!world.isClient)
        {
            boolean sneaking = user.isSneaking();
            BallEntity ballEntity = new BallEntity(user, world);
            ballEntity.setItem(itemStack);
            ballEntity.setBounces((itemStack.getItem() instanceof BallItem ball) ? ball.getBounces() : 0);
            ballEntity.setVelocity(user, user.getPitch(), user.getYaw(), 0.0F, (sneaking) ? 0.4F : 0.8F, 1.0F);
            world.spawnEntity(ballEntity);
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this));
        itemStack.decrementUnlessCreative(1, user);
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
