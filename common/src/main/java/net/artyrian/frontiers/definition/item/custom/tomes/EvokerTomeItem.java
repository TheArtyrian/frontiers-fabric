package net.artyrian.frontiers.definition.item.custom.tomes;

import net.artyrian.frontiers.definition.entity.intf.ManaUser;
import net.artyrian.frontiers.mixin_intf.EvoFangsIntf;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
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
import org.jetbrains.annotations.Nullable;

public class EvokerTomeItem extends TomeItem
{
    private static final int[] LEVELS = new int[]{4, 10, 20, 20};

    public EvokerTomeItem(int enchantability, Properties settings)
    {
        super(enchantability, settings);
    }

    @Override
    public InteractionResult useOn(UseOnContext context)
    {
        Level level = context.getLevel();
        ItemStack stack = context.getItemInHand();
        Player player = context.getPlayer();
        BlockPos position = context.getClickedPos();

        boolean castedProper = false;
        if (player instanceof ManaUser user && this.canCast(user)) castedProper = this.onCast(user, level, stack, context.getHand(), position.getBottomCenter());

        if (castedProper) return InteractionResult.sidedSuccess(level.isClientSide);
        else return super.useOn(context);
    }

    @Override protected int[] getLvlToMana() { return LEVELS; }

    @Override
    public boolean onCast(ManaUser user, Level level, ItemStack stack, @Nullable InteractionHand hand, Vec3 position)
    {
        BlockPos pos = new BlockPos((int)Math.floor(position.x), (int)Math.floor(position.y), (int)Math.floor(position.z));
        boolean is_gator = stack.getHoverName().getString().toLowerCase().matches("florida man");
        boolean clear_above = !level.getBlockState(pos).isSolid() || level.getBlockState(pos.above()).isAir();

        int lvl = user.getManaLevel();
        int pts = user.getManaPts();

        if (user instanceof Player player)
        {
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.SPELL_CAST_BASIC.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );
            level.playSound(
                    null,
                    player.getX(),
                    player.getY(),
                    player.getZ(),
                    ModSounds.SPELL_CAST_FANGS.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    1.0F
            );

            if (!level.isClientSide())
            {
                if (!player.isCreative() && hand != null)
                {
                    user.removeMana(this.getLvlToMana()[lvl]);
                    stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
                    player.getCooldowns().addCooldown(this, 30);
                }

                boolean is_player_pos = pos.equals(player.blockPosition().below());

                if (is_player_pos)
                {
                    double posY = (level.getBlockState(pos).isSolid()) ? pos.above().getY() : pos.getY();
                    Vec3 posiban = new Vec3(pos.getX() + 0.5, posY, pos.getZ() + 0.5);
                    Vec3 posiban_og = new Vec3(posiban.x, posiban.y, posiban.z);

                    float yaw = player.getYRot();
                    float floataddyaw = yaw;

                    for (int i = 0; i < 5; i++)
                    {
                        float g = yaw + (float)i * (float) Math.PI * 0.4F;
                        posiban = new Vec3(posiban_og.x + (double)Mth.cos(g) * 1.5, posiban_og.y, posiban_og.z + + (double)Mth.sin(g) * 1.5);

                        this.powerOfFloridaMan(level, posiban, floataddyaw, 1, player, is_gator);

                        floataddyaw += 72.0F;
                        if (floataddyaw > 180.0F) floataddyaw -= 180.0F;
                    }

                    floataddyaw = yaw;
                    for (int i = 0; i < 8; i++)
                    {
                        float g = yaw + (float)i * (float) Math.PI * 2.0F / 8.0F + (float) (Math.PI * 2.0 / 5.0);
                        posiban = new Vec3(posiban_og.x + (double)Mth.cos(g) * 2.5, posiban_og.y, posiban_og.z + + (double)Mth.sin(g) * 2.5);

                        this.powerOfFloridaMan(level, posiban, floataddyaw, 4, player, is_gator);

                        floataddyaw += 45.0F;
                        if (floataddyaw > 180.0F) floataddyaw-= 180.0F;
                    }
                }
                else
                {
                    float yaw = player.getYRot();

                    double posY = (level.getBlockState(pos).isSolid()) ? pos.above().getY() : pos.getY();
                    Vec3 posiban = new Vec3(pos.getX() + 0.5, posY, pos.getZ() + 0.5);
                    for (int i = 0; i < 10; i++)
                    {
                        boolean stopClock = this.powerOfFloridaMan(level, posiban, yaw, 2 * i, player, is_gator);

                        double d = -Mth.sin(yaw * (float) (Math.PI / 180.0));
                        double e = Mth.cos(yaw * (float) (Math.PI / 180.0));

                        posiban = posiban.add(d, 0, e);
                        if (!stopClock) break;
                    }
                }
            }
            return true;
        }
        return false;
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
        else buddy.remove(Entity.RemovalReason.DISCARDED);
        return valid;
    }
}
