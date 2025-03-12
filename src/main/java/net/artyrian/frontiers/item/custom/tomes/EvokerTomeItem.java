package net.artyrian.frontiers.item.custom.tomes;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.mixin_interfaces.FangsMixInterface;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class EvokerTomeItem extends TomeItem
{
    public EvokerTomeItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context)
    {
        World this_world = context.getWorld();
        ItemStack stack = context.getStack();
        PlayerEntity wake_up = context.getPlayer();
        BlockPos position = context.getBlockPos();
        boolean is_gator = stack.getName().getString().matches("Florida Man");

        boolean clear_above = !this_world.getBlockState(position).isSolid() || this_world.getBlockState(position.up()).isAir();

        if (stack.getItem() == ModItem.TOME_OF_FANGS && wake_up != null && clear_above)
        {
            this_world.playSound(
                    null,
                    wake_up.getX(),
                    wake_up.getY(),
                    wake_up.getZ(),
                    ModSounds.SPELL_CAST_BASIC,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );
            this_world.playSound(
                    null,
                    wake_up.getX(),
                    wake_up.getY(),
                    wake_up.getZ(),
                    ModSounds.SPELL_CAST_FANGS,
                    SoundCategory.PLAYERS,
                    1.0F,
                    1.0F
            );

            if (!this_world.isClient())
            {
                if (!wake_up.isInCreativeMode()) stack.damage(1, wake_up, LivingEntity.getSlotForHand(context.getHand()));
                wake_up.getItemCooldownManager().set(this, 30);

                boolean is_player_pos = position.equals(wake_up.getBlockPos().down());

                if (is_player_pos)
                {
                    double posY = (this_world.getBlockState(position).isSolid()) ? position.up().getY() : position.getY();
                    Vec3d posiban = new Vec3d(position.getX() + 0.5, posY, position.getZ() + 0.5);
                    Vec3d posiban_og = new Vec3d(posiban.x, posiban.y, posiban.z);

                    float yaw = wake_up.getYaw();

                    float floataddyaw = yaw;
                    for (int i = 0; i < 5; i++) {
                        float g = yaw + (float)i * (float) Math.PI * 0.4F;
                        posiban = new Vec3d(posiban_og.x + (double)MathHelper.cos(g) * 1.5, posiban_og.y, posiban_og.z + + (double)MathHelper.sin(g) * 1.5);

                        this.powerOfFloridaMan(this_world, posiban, floataddyaw, 1, wake_up, is_gator);

                        floataddyaw += 72.0F;
                        if (floataddyaw > 180.0F) floataddyaw-= 180.0F;
                    }

                    floataddyaw = yaw;
                    for (int i = 0; i < 8; i++) {
                        float g = yaw + (float)i * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
                        posiban = new Vec3d(posiban_og.x + (double)MathHelper.cos(g) * 2.5, posiban_og.y, posiban_og.z + + (double)MathHelper.sin(g) * 2.5);

                        this.powerOfFloridaMan(this_world, posiban, floataddyaw, 4, wake_up, is_gator);

                        floataddyaw += 45.0F;
                        if (floataddyaw > 180.0F) floataddyaw-= 180.0F;
                    }
                }
                else
                {
                    float yaw = wake_up.getYaw();
                    //Frontiers.LOGGER.info(String.valueOf(yaw));

                    double posY = (this_world.getBlockState(position).isSolid()) ? position.up().getY() : position.getY();
                    Vec3d posiban = new Vec3d(position.getX() + 0.5, posY, position.getZ() + 0.5);
                    for (int i = 0; i < 10; i++)
                    {
                        boolean stopClock = this.powerOfFloridaMan(this_world, posiban, yaw, 2 * i, wake_up, is_gator);

                        double d = (double)(-MathHelper.sin(yaw * (float) (Math.PI / 180.0)));
                        double e = (double)MathHelper.cos(yaw * (float) (Math.PI / 180.0));

                        posiban = posiban.add(d, 0, e);
                        if (!stopClock) break;
                    }
                }

                return ActionResult.SUCCESS;
            }
        }
        return super.useOnBlock(context);
    }

    // I am the funniest man alive (lie)
    private boolean powerOfFloridaMan(World this_world, Vec3d posy, float yaw, int warmup, PlayerEntity florida_man, boolean florida)
    {
        EvokerFangsEntity buddy = new EvokerFangsEntity(this_world, posy.x, posy.y, posy.z, 0.0F, warmup, florida_man);
        buddy.setYaw(yaw);
        boolean valid = true;

        ((FangsMixInterface)buddy).frontiers_1_21x$setFriendly(true);
        if (florida) ((FangsMixInterface)buddy).frontiers_1_21x$setGator(true);

        if (buddy.isInsideWall())
        {
            valid = false;
            for (int i = 0; i < 3; i++)
            {
                buddy.setPosition(buddy.getX(), buddy.getY() + 1, buddy.getZ());

                if (!buddy.isInsideWall())
                {
                    valid = true;
                    break;
                }
            }
        }
        if (this_world.getBlockState(buddy.getBlockPos().down()).isAir())
        {
            valid = false;
            for (int i = 0; i < 3; i++)
            {
                buddy.setPosition(buddy.getX(), buddy.getY() - 1, buddy.getZ());

                if (!this_world.getBlockState(buddy.getBlockPos().down()).isAir())
                {
                    valid = true;
                    break;
                }
            }
        }

        if (valid)
        {
            this_world.spawnEntity(buddy);
            florida_man.getWorld().emitGameEvent(GameEvent.ENTITY_PLACE, posy, GameEvent.Emitter.of(florida_man));
        }
        else
        {
            buddy.remove(Entity.RemovalReason.DISCARDED);
        }
        return valid;
    }
}
