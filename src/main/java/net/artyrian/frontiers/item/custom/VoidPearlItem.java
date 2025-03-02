package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EnderChestInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.ParticleUtil;
import net.minecraft.screen.GenericContainerScreenHandler;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class VoidPearlItem extends Item
{
    private static final Text NAME = Text.translatable("item.frontiers.void_pearl");

    public VoidPearlItem(Settings settings)
    {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack itemStack = user.getStackInHand(hand);
        EnderChestInventory enderChestInventory = user.getEnderChestInventory();
        if (enderChestInventory != null)
        {
            for (int i = 0; i < 8; i++) {
                world
                        .addParticle(
                                new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack),
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
                    null, user.getX(), user.getY(), user.getZ(), ModSounds.VOID_PEARL_THROW, SoundCategory.PLAYERS, 0.9F, 1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F)
            );

            if (!world.isClient)
            {
                user.openHandledScreen(
                        new SimpleNamedScreenHandlerFactory(
                                (i, playerInventory, playerEntity) -> GenericContainerScreenHandler.createGeneric9x3(i, playerInventory, enderChestInventory), NAME
                        )
                );
                user.incrementStat(Stats.OPEN_ENDERCHEST);
                itemStack.decrementUnlessCreative(1, user);
            }
        }
        return TypedActionResult.success(itemStack, world.isClient());
    }
}
