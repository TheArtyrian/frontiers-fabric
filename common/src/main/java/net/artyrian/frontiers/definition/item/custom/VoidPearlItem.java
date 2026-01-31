package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.PlayerEnderChestContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class VoidPearlItem extends Item
{
    private static final Component NAME = Component.translatable("item.frontiers.void_pearl");

    public VoidPearlItem(Properties settings)
    {
        super(settings);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand)
    {
        ItemStack itemStack = user.getItemInHand(hand);
        PlayerEnderChestContainer enderChestInventory = user.getEnderChestInventory();
        if (enderChestInventory != null)
        {
            for (int i = 0; i < 8; i++) {
                world
                        .addParticle(
                                new ItemParticleOption(ParticleTypes.ITEM, itemStack),
                                user.getX(),
                                user.getY() + 1.0D,
                                user.getZ(),
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8
                        );

                world
                        .addParticle(
                                ParticleTypes.PORTAL,
                                user.getX(),
                                user.getY() + 1.0D,
                                user.getZ(),
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8,
                                ((double)world.random.nextFloat() - 0.5) * 0.8
                        );
            }

            world.playSound(
                    null, user.getX(), user.getY(), user.getZ(), ModSounds.VOID_PEARL_THROW.get(), SoundSource.PLAYERS, 0.9F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );

            if (!world.isClientSide)
            {
                user.openMenu(
                        new SimpleMenuProvider(
                                (i, playerInventory, playerEntity) -> ChestMenu.threeRows(i, playerInventory, enderChestInventory), NAME
                        )
                );
                user.awardStat(Stats.OPEN_ENDERCHEST);
                itemStack.consume(1, user);
            }
        }
        return InteractionResultHolder.sidedSuccess(itemStack, world.isClientSide());
    }
}
