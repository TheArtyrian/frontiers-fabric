package net.artyrian.frontiers.definition.item.dispenser;

import net.artyrian.frontiers.definition.entity.projectile.BallEntity;
import net.artyrian.frontiers.definition.item.custom.BallItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Position;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.List;

public class BallDispenserBehavior extends DefaultDispenseItemBehavior
{
    private final int bounces;

    public BallDispenserBehavior(int bounces)
    {
        this.bounces = bounces;
    }

    @Override
    protected ItemStack execute(BlockSource pointer, ItemStack stack)
    {
        Level world = pointer.level();
        Position position = DispenserBlock.getDispensePosition(pointer);
        Direction direction = pointer.state().getValue(DispenserBlock.FACING);

        BallEntity ballEntity = new BallEntity(position.x(), position.y(), position.z(), world);
        ballEntity.setItem(stack);
        ballEntity.shoot(direction.getStepX(), ((float) direction.getStepY() + 0.1F), direction.getStepZ(), 0.8F, 1.0F);
        ballEntity.setBounces((stack.getItem() instanceof BallItem ball) ? ball.getBounces() : 0);
        world.addFreshEntity(ballEntity);

        if (stack.getItem() instanceof BallItem ball)
        {
            String stackname = stack.getHoverName().getString();
            ChatFormatting color = color = ball.getColor();

            List<Entity> nearby = world.getEntities(null, new AABB(
                    new Vec3(position.x() - 16, position.y() - 16, position.z() - 16),
                    new Vec3(position.x() + 16, position.y() + 16, position.z() + 16)
            ));

            for (Entity i : nearby)
            {
                if (i instanceof Player player)
                {
                    player.displayClientMessage(Component.translatable("entity.frontiers.ball.dispenser", stackname).withStyle(color), true);
                }
            }
        }

        stack.shrink(1);
        world.playSound(null,
                position.x(),
                position.y(),
                position.z(),
                ModSounds.BALL_THROW.get(),
                SoundSource.BLOCKS,
                0.5F,
                0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
        );

        return stack;
    }

    protected void playSound(BlockSource pointer)
    {
        pointer.level().levelEvent(1002, pointer.pos(), 0);
    }


    protected float getVariation() {
        return 10.0F;
    }

    protected float getForce() {
        return 0.5F;
    }
}
