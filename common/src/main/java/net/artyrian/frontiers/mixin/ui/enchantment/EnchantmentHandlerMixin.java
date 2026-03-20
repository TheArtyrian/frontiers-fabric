package net.artyrian.frontiers.mixin.ui.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.mixin_intf.EnchTableIntf;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Debug(export = true)
@Mixin(EnchantmentMenu.class)
public abstract class EnchantmentHandlerMixin
{
    @Shadow @Final private ContainerLevelAccess access;

    @WrapOperation(method = "getEnchantmentList", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;selectEnchantment(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/item/ItemStack;ILjava/util/stream/Stream;)Ljava/util/List;"))
    private List<EnchantmentInstance> frontiers$dude(
            RandomSource random, ItemStack stack, int level, Stream<Holder<Enchantment>> possibleEnchantments, Operation<List<EnchantmentInstance>> original,
            @Local(argsOnly = true) RegistryAccess registryManager
    )
    {
        Stream<Holder<Enchantment>> returnerStreamMixTry = possibleEnchantments;

        Optional<Integer> crystalCount = this.access.evaluate((world, pos) ->
        {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof EnchantingTableBlockEntity table)
            {
                return ((EnchTableIntf)table).frontiers$getCrystalCount();
            }
            return 0;
        });
        if (crystalCount.isPresent() && crystalCount.get() >= 4)
        {
            //Frontiers.LOGGER.info("4 present");
            Optional<HolderSet.Named<Enchantment>> optional = registryManager.registryOrThrow(Registries.ENCHANTMENT).getTag(EnchantmentTags.TREASURE);
            if (optional.isPresent())
            {
                HolderSet.Named<Enchantment> optionalPulled = optional.get();
                List<Holder<Enchantment>> UNCURSED_LIST = new ArrayList<>();

                // Purge curses from the list.
                for (Holder<Enchantment> enchantmentRegistryEntry : optionalPulled)
                {
                    if (!enchantmentRegistryEntry.is(EnchantmentTags.CURSE))
                    {
                        UNCURSED_LIST.add(enchantmentRegistryEntry);
                    }
                }

                // Concat the streams together
                returnerStreamMixTry = Stream.concat(possibleEnchantments, UNCURSED_LIST.stream());
            }
        }
        return original.call(random, stack, level, returnerStreamMixTry);
    }
}
