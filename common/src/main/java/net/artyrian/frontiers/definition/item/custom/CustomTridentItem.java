package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.definition.entity.types.projectile.PaleTridentEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.Position;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ThrownTrident;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class CustomTridentItem extends TridentItem
{
    public CustomTridentItem(Item.Properties settings)
    {
        super(settings);
    }

    public static ItemAttributeModifiers createAttributes()
    {
        return ItemAttributeModifiers.builder()
                .add(
                        Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(
                                BASE_ATTACK_DAMAGE_ID,
                                10.0,
                                AttributeModifier.Operation.ADD_VALUE
                        ),
                        EquipmentSlotGroup.MAINHAND)
                .add(
                        Attributes.ATTACK_SPEED,
                        new AttributeModifier(
                                BASE_ATTACK_SPEED_ID,
                                -2.5,
                                AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.MAINHAND).build();
    }

    @Override
    public void releaseUsing(ItemStack stack, Level world, LivingEntity user, int remainingUseTicks)
    {
        if (user instanceof Player playerEntity)
        {
            int maxtimewait = this.getUseDuration(stack, user) - remainingUseTicks;
            if (maxtimewait >= 6)
            {
                float f = EnchantmentHelper.getTridentSpinAttackStrength(stack, playerEntity);
                if (!(f > 0.0F) || playerEntity.isInWaterOrRain())
                {
                    if (!isTooDamagedToUse(stack))
                    {
                        Holder<SoundEvent> registryEntry = EnchantmentHelper.pickHighestLevel(stack, EnchantmentEffectComponents.TRIDENT_SOUND).orElse(SoundEvents.TRIDENT_THROW);
                        if (!world.isClientSide)
                        {
                            stack.hurtAndBreak(1, playerEntity, LivingEntity.getSlotForHand(user.getUsedItemHand()));
                            if (f == 0.0F)
                            {
                                PaleTridentEntity tridentEntity = new PaleTridentEntity(world, playerEntity, stack);
                                tridentEntity.shootFromRotation(playerEntity, playerEntity.getXRot(), playerEntity.getYRot(), 0.0F, 2.5F, 1.0F);
                                if (playerEntity.hasInfiniteMaterials())
                                {
                                    tridentEntity.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                                }

                                world.addFreshEntity(tridentEntity);
                                world.playSound(null, tridentEntity, registryEntry.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                                if (!playerEntity.hasInfiniteMaterials())
                                {
                                    playerEntity.getInventory().removeItem(stack);
                                }
                            }
                        }

                        playerEntity.awardStat(Stats.ITEM_USED.get(this));
                        if (f > 0.0F)
                        {
                            float g = playerEntity.getYRot();
                            float h = playerEntity.getXRot();
                            float j = -Mth.sin(g * 0.017453292F) * Mth.cos(h * 0.017453292F);
                            float k = -Mth.sin(h * 0.017453292F);
                            float l = Mth.cos(g * 0.017453292F) * Mth.cos(h * 0.017453292F);
                            float m = Mth.sqrt(j * j + k * k + l * l);
                            j *= f / m;
                            k *= f / m;
                            l *= f / m;
                            playerEntity.push((double)j, (double)k, (double)l);
                            playerEntity.startAutoSpinAttack(20, 8.0F, stack);
                            if (playerEntity.onGround())
                            {
                                float n = 1.1999999F;
                                playerEntity.move(MoverType.SELF, new Vec3(0.0, 1.1999999284744263, 0.0));
                            }

                            world.playSound(null, playerEntity, registryEntry.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                        }
                    }
                }
            }
        }
    }

    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        if (isTooDamagedToUse(itemStack))
        {
            return InteractionResultHolder.fail(itemStack);
        }
        else if (EnchantmentHelper.getTridentSpinAttackStrength(itemStack, user) > 0.0F && !user.isInWaterOrRain())
        {
            return InteractionResultHolder.fail(itemStack);
        }
        else
        {
            user.startUsingItem(hand);
            return InteractionResultHolder.consume(itemStack);
        }
    }

    private static boolean isTooDamagedToUse(ItemStack stack) {
        return stack.getDamageValue() >= stack.getMaxDamage() - 1;
    }
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    public void postHurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    public int getEnchantmentValue()
    {
        return 8;
    }

    public Projectile asProjectile(Level world, Position pos, ItemStack stack, Direction direction)
    {
        ThrownTrident tridentEntity = new ThrownTrident(world, pos.x(), pos.y(), pos.z(), stack.copyWithCount(1));
        tridentEntity.pickup = AbstractArrow.Pickup.ALLOWED;
        return tridentEntity;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient)
    {
        return ingredient.is(ModItem.PALE_PRISMARINE_SHARD.get()) || super.isValidRepairItem(stack, ingredient);
    }
}
