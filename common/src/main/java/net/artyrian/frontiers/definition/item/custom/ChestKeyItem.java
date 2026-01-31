package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ResolvableProfile;
import java.util.List;

public class ChestKeyItem extends Item
{
    public ChestKeyItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public boolean isFoil(ItemStack stack)
    {
        return stack.has(DataComponents.PROFILE) || super.isFoil(stack);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player user, LivingEntity entity, InteractionHand hand)
    {
        ResolvableProfile profilecomp = stack.get(DataComponents.PROFILE);
        if (profilecomp == null && entity instanceof Player player && entity.isAlive())
        {
            user.level().playSound(
                    user,
                    user.getX(),
                    user.getY(),
                    user.getZ(),
                    ModSounds.CHEST_KEY_TAGGED.get(),
                    SoundSource.PLAYERS,
                    1.0F,
                    0.8F + (Math.clamp(user.level().getRandom().nextFloat(), 0.15F, 0.5F))
            );

            boolean bool = !user.hasInfiniteMaterials() && stack.getCount() == 1;
            if (bool)
            {
                stack.set(DataComponents.PROFILE, new ResolvableProfile(player.getGameProfile()));
            }
            else
            {
                ItemStack stack2 = stack.transmuteCopy(ModItem.CHEST_KEY.get(), 1);
                stack.consume(1, player);
                stack2.set(DataComponents.PROFILE, new ResolvableProfile(player.getGameProfile()));
                if (!user.getInventory().add(stack2))
                {
                    user.drop(stack2, false);
                }
            }

            return InteractionResult.sidedSuccess(user.level().isClientSide);
        }
        else
        {
            return InteractionResult.PASS;
        }
    }

    @Override
    public Component getName(ItemStack stack) {
        ResolvableProfile profileComponent = stack.get(DataComponents.PROFILE);
        return (profileComponent != null && profileComponent.name().isPresent()
                ? Component.translatable(this.getDescriptionId() + ".named")
                : super.getName(stack));
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type) {
        super.appendHoverText(stack, context, tooltip, type);

        ResolvableProfile profileComponent = stack.get(DataComponents.PROFILE);
        if (profileComponent != null && profileComponent.name().isPresent())
        {
            String name = profileComponent.name().get();
            tooltip.add(Component.literal(name).withStyle(ChatFormatting.YELLOW));
        }
    }
}
