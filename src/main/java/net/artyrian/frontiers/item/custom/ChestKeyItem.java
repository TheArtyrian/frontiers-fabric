package net.artyrian.frontiers.item.custom;

import com.mojang.authlib.GameProfile;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ProfileComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;

import java.util.List;

public class ChestKeyItem extends Item
{
    public ChestKeyItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public boolean hasGlint(ItemStack stack)
    {
        return stack.contains(DataComponentTypes.PROFILE) || super.hasGlint(stack);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand)
    {
        ProfileComponent profilecomp = stack.get(DataComponentTypes.PROFILE);
        if (profilecomp == null && entity instanceof PlayerEntity player && entity.isAlive())
        {
            user.getWorld().playSound(
                    user,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    ModSounds.CHEST_KEY_TAGGED,
                    SoundCategory.PLAYERS,
                    1.0F,
                    0.8F + (Math.clamp(user.getWorld().getRandom().nextFloat(), 0.15F, 0.5F))
            );

            boolean bool = !user.isInCreativeMode() && stack.getCount() == 1;
            if (bool)
            {
                stack.set(DataComponentTypes.PROFILE, new ProfileComponent(player.getGameProfile()));
            }
            else
            {
                ItemStack stack2 = stack.copyComponentsToNewStack(ModItem.CHEST_KEY, 1);
                stack.decrementUnlessCreative(1, player);
                stack2.set(DataComponentTypes.PROFILE, new ProfileComponent(player.getGameProfile()));
                if (!user.getInventory().insertStack(stack2))
                {
                    user.dropItem(stack2, false);
                }
            }

            return ActionResult.success(user.getWorld().isClient);
        }
        else
        {
            return ActionResult.PASS;
        }
    }

    @Override
    public Text getName(ItemStack stack) {
        ProfileComponent profileComponent = stack.get(DataComponentTypes.PROFILE);
        return (profileComponent != null && profileComponent.name().isPresent()
                ? Text.translatable(this.getTranslationKey() + ".named")
                : super.getName(stack));
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, tooltip, type);

        ProfileComponent profileComponent = stack.get(DataComponentTypes.PROFILE);
        if (profileComponent != null && profileComponent.name().isPresent())
        {
            String name = profileComponent.name().get();
            tooltip.add(Text.literal(name).formatted(Formatting.YELLOW));
        }
    }
}
