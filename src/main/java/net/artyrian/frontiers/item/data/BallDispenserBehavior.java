package net.artyrian.frontiers.item.data;

import net.artyrian.frontiers.entity.projectile.BallEntity;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ItemDispenserBehavior;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.*;
import net.minecraft.world.World;

import java.util.List;

public class BallDispenserBehavior extends ItemDispenserBehavior
{
    public BallDispenserBehavior()
    {

    }

    @Override
    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack)
    {
        World world = pointer.world();
        Position position = DispenserBlock.getOutputLocation(pointer);
        Direction direction = pointer.state().get(DispenserBlock.FACING);

        BallEntity ballEntity = new BallEntity(position.getX(), position.getY(), position.getZ(), world);
        ballEntity.setItem(stack);
        ballEntity.setVelocity(direction.getOffsetX(), ((float) direction.getOffsetY() + 0.1F), direction.getOffsetZ(), 0.8F, 1.0F);
        world.spawnEntity(ballEntity);

        if (stack.getItem() instanceof BallItem ball)
        {
            String stackname = stack.getName().getString();
            Formatting color = color = ball.getColor();

            List<Entity> nearby = world.getOtherEntities(null, new Box(
                    new Vec3d(position.getX() - 16, position.getY() - 16, position.getZ() - 16),
                    new Vec3d(position.getX() + 16, position.getY() + 16, position.getZ() + 16)
            ));

            for (Entity i : nearby)
            {
                if (i instanceof PlayerEntity player)
                {
                    player.sendMessage(Text.translatable("entity.frontiers.ball.dispenser", stackname).formatted(color), true);
                }
            }
        }

        stack.decrement(1);
        world.playSound(null,
                position.getX(),
                position.getY(),
                position.getZ(),
                ModSounds.BALL_THROW,
                SoundCategory.BLOCKS,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        return stack;
    }

    protected void playSound(BlockPointer pointer)
    {
        pointer.world().syncWorldEvent(1002, pointer.pos(), 0);
    }


    protected float getVariation() {
        return 10.0F;
    }

    protected float getForce() {
        return 0.5F;
    }
}
