package net.artyrian.frontiers.definition.item.custom.tomes;

import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class EvokerTomeItem extends TomeItem
{
    public EvokerTomeItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level this_world = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Player wake_up = context.getPlayer();
        BlockPos position = context.getClickedPos();
        boolean is_gator = stack.getHoverName().getString().toLowerCase().matches("florida man");

        boolean clear_above = !this_world.getBlockState(position).isSolid() || this_world.getBlockState(position.above()).isAir();

        if (stack.getItem() == ModItem.TOME_OF_FANGS.get() && wake_up != null && clear_above)
        {
            this_world.playSound(
                    null,
                    wake_up.getX(),
                    wake_up.getY(),
                    wake_up.getZ(),
                    ModSounds.SPELL_CAST_BASIC.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
            this_world.playSound(
                    null,
                    wake_up.getX(),
                    wake_up.getY(),
                    wake_up.getZ(),
                    ModSounds.SPELL_CAST_FANGS.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            if (!this_world.isClientSide())
            {
                if (!wake_up.hasInfiniteMaterials()) stack.hurtAndBreak(1, wake_up, LivingEntity.getSlotForHand(context.getHand()));
                wake_up.getCooldowns().addCooldown(this, 30);

                boolean is_player_pos = position.equals(wake_up.blockPosition().below());

                if (is_player_pos)
                {
                    double posY = (this_world.getBlockState(position).isSolid()) ? position.above().getY() : position.getY();
                    Vec3 posiban = new Vec3(position.getX() + 0.5, posY, position.getZ() + 0.5);
                    Vec3 posiban_og = new Vec3(posiban.x, posiban.y, posiban.z);

                    float yaw = wake_up.getYRot();

                    float floataddyaw = yaw;
                    for (int i = 0; i < 5; i++) {
                        float g = yaw + (float)i * (float) Math.PI * 0.4F;
                        posiban = new Vec3(posiban_og.x + (double)Mth.cos(g) * 1.5, posiban_og.y, posiban_og.z + + (double)Mth.sin(g) * 1.5);

                        this.powerOfFloridaMan(this_world, posiban, floataddyaw, 1, wake_up, is_gator);

                        floataddyaw += 72.0F;
                        if (floataddyaw > 180.0F) floataddyaw-= 180.0F;
                    }

                    floataddyaw = yaw;
                    for (int i = 0; i < 8; i++) {
                        float g = yaw + (float)i * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
                        posiban = new Vec3(posiban_og.x + (double)Mth.cos(g) * 2.5, posiban_og.y, posiban_og.z + + (double)Mth.sin(g) * 2.5);

                        this.powerOfFloridaMan(this_world, posiban, floataddyaw, 4, wake_up, is_gator);

                        floataddyaw += 45.0F;
                        if (floataddyaw > 180.0F) floataddyaw-= 180.0F;
                    }
                }
                else
                {
                    float yaw = wake_up.getYRot();
                    //Frontiers.LOGGER.info(String.valueOf(yaw));

                    double posY = (this_world.getBlockState(position).isSolid()) ? position.above().getY() : position.getY();
                    Vec3 posiban = new Vec3(position.getX() + 0.5, posY, position.getZ() + 0.5);
                    for (int i = 0; i < 10; i++)
                    {
                        boolean stopClock = this.powerOfFloridaMan(this_world, posiban, yaw, 2 * i, wake_up, is_gator);

                        double d = (-Mth.sin(yaw * (float) (Math.PI / 180.0)));
                        double e = Mth.cos(yaw * (float) (Math.PI / 180.0));

                        posiban = posiban.add(d, 0, e);
                        if (!stopClock) break;
                    }
                }

                return InteractionResult.SUCCESS;
            }
        }
        return super.useOn(context);
    }

    // I am the funniest man alive (lie)
    private boolean powerOfFloridaMan(Level this_world, Vec3 posy, float yaw, int warmup, Player florida_man, boolean florida)
    {
        EvokerFangs buddy = new EvokerFangs(this_world, posy.x, posy.y, posy.z, 0.0F, warmup, florida_man);
        buddy.setYRot(yaw);
        boolean valid = true;

        ((EvoFangsIntf)buddy).frontiers_1_21x$setFriendly(true);
        if (florida) ((EvoFangsIntf)buddy).frontiers_1_21x$setGator(true);

        if (buddy.isInWall())
        {
            valid = false;
            for (int i = 0; i < 3; i++)
            {
                buddy.setPos(buddy.getX(), buddy.getY() + 1, buddy.getZ());

                if (!buddy.isInWall())
                {
                    valid = true;
                    break;
                }
            }
        }
        if (this_world.getBlockState(buddy.blockPosition().below()).isAir())
        {
            valid = false;
            for (int i = 0; i < 3; i++)
            {
                buddy.setPos(buddy.getX(), buddy.getY() - 1, buddy.getZ());

                if (!this_world.getBlockState(buddy.blockPosition().below()).isAir())
                {
                    valid = true;
                    break;
                }
            }
        }

        if (valid)
        {
            this_world.addFreshEntity(buddy);
            florida_man.level().gameEvent(GameEvent.ENTITY_PLACE, posy, GameEvent.Context.of(florida_man));
        }
        else
        {
            buddy.remove(Entity.RemovalReason.DISCARDED);
        }
        return valid;
    }
}
