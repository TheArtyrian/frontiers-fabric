package net.artyrian.frontiers.mixin.ui.enchantment;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.EnchantTableMixInterface;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.EnchantmentScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.util.math.random.Random;
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
@Mixin(EnchantmentScreenHandler.class)
public abstract class EnchantmentHandlerMixin
{
    @Shadow @Final private ScreenHandlerContext context;

    @WrapOperation(method = "generateEnchantments", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/enchantment/EnchantmentHelper;generateEnchantments(Lnet/minecraft/util/math/random/Random;Lnet/minecraft/item/ItemStack;ILjava/util/stream/Stream;)Ljava/util/List;"))
    private List<EnchantmentLevelEntry> dude(
            Random random, ItemStack stack, int level, Stream<RegistryEntry<Enchantment>> possibleEnchantments, Operation<List<EnchantmentLevelEntry>> original,
            @Local(argsOnly = true) DynamicRegistryManager registryManager
    )
    {
        Stream<RegistryEntry<Enchantment>> returnerStreamMixTry = possibleEnchantments;

        Optional<Integer> crystalCount = this.context.get((world, pos) ->
        {
            BlockEntity entity = world.getBlockEntity(pos);
            if (entity instanceof EnchantingTableBlockEntity table)
            {
                return ((EnchantTableMixInterface)table).frontiers$getCrystalCount();
            }
            return 0;
        });
        if (crystalCount.isPresent() && crystalCount.get() >= 4)
        {
            Frontiers.LOGGER.info("4 present");
            Optional<RegistryEntryList.Named<Enchantment>> optional = registryManager.get(RegistryKeys.ENCHANTMENT).getEntryList(EnchantmentTags.TREASURE);
            if (optional.isPresent())
            {
                RegistryEntryList.Named<Enchantment> optionalPulled = optional.get();
                List<RegistryEntry<Enchantment>> UNCURSED_LIST = new ArrayList<>();

                // Purge curses from the list.
                for (RegistryEntry<Enchantment> enchantmentRegistryEntry : optionalPulled)
                {
                    if (!enchantmentRegistryEntry.isIn(EnchantmentTags.CURSE))
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
