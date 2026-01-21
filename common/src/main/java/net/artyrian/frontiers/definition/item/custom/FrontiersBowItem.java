package net.artyrian.frontiers.definition.item.custom;

import org.jetbrains.annotations.Nullable;

import java.text.DecimalFormat;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class FrontiersBowItem extends BowItem
{
    private final SoundEvent SHOOT_SFX;
    private final float damage_modifier;
    private final float pullSpeedTicks;

    public FrontiersBowItem(SoundEvent sfx, float damage_modifier, float pullTicks, Properties settings)
    {
        super(settings);
        this.SHOOT_SFX = sfx;
        this.damage_modifier = damage_modifier;
        this.pullSpeedTicks = pullTicks;
    }

    public float getPullTickSpeed() {
        return pullSpeedTicks;
    }

    public float getCustomPullProgress(int useTicks) {
        float f = useTicks / this.pullSpeedTicks;
        f = (f * f + f * 2.0F) / 3.0F;
        if (f > 1.0F) {
            f = 1.0F;
        }

        return f;
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks) {
        if (user instanceof Player playerEntity) {
            ItemStack itemStack = playerEntity.getProjectile(stack);
            if (!itemStack.isEmpty()) {
                int i = this.getUseDuration(stack, user) - remainingUseTicks;
                float f = this.getCustomPullProgress(i);
                if (!((double)f < 0.1)) {
                    List<ItemStack> list = draw(stack, itemStack, playerEntity);
                    if (world instanceof ServerLevel serverWorld && !list.isEmpty()) {
                        this.shoot(serverWorld, playerEntity, playerEntity.getUsedItemHand(), stack, list, f * 3.0F, 1.0F, f == 1.0F, null);
                    }

                    world.playSound(
                            null,
                            playerEntity.getX(),
                            playerEntity.getY(),
                            playerEntity.getZ(),
                            SHOOT_SFX,
                            SoundSource.PLAYERS,
                            1.0F,
                            1.0F / (world.getRandom().nextFloat() * 0.4F + 1.2F) + f * 0.5F
                    );
                    playerEntity.awardStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    @Override
    protected Projectile createProjectile(Level world, LivingEntity shooter, ItemStack weaponStack, ItemStack projectileStack, boolean critical)
    {
        ArrowItem arrowItem2 = projectileStack.getItem() instanceof ArrowItem arrowItem ? arrowItem : (ArrowItem) Items.ARROW;
        AbstractArrow persistentProjectileEntity = arrowItem2.createArrow(world, projectileStack, shooter, weaponStack);
        persistentProjectileEntity.setBaseDamageFromMob(damage_modifier);
        if (critical) {
            persistentProjectileEntity.setCritArrow(true);
        }

        return persistentProjectileEntity;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag type)
    {
        super.appendHoverText(stack, context, tooltip, type);
        tooltip.add(Component.literal(""));
        tooltip.add(Component.translatable("item.modifiers.hand").withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.literal(" ").append(Component.translatable("tooltip.ranged.damage", new DecimalFormat("#.###").format(this.damage_modifier)).withStyle(ChatFormatting.GOLD)));
        if (this.pullSpeedTicks != 20.0f) {
            tooltip.add(Component.literal(" ").append(Component.translatable("tooltip.ranged.speed", new DecimalFormat("#.###").format(20.0f / this.pullSpeedTicks)).withStyle(ChatFormatting.GOLD)));
        }
    }
}
