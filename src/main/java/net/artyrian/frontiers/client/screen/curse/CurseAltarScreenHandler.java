package net.artyrian.frontiers.client.screen.curse;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.block.ModBlocks;
import net.artyrian.frontiers.block.custom.CurseAltarBlock;
import net.artyrian.frontiers.client.screen.ModScreenHandlers;
import net.artyrian.frontiers.criterion.ModCriteria;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModStats;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Blocks;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.*;
import net.minecraft.screen.slot.Slot;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Iterator;
import java.util.Set;

public class CurseAltarScreenHandler extends ScreenHandler
{
    static final Identifier TABLET_SLOT_TEX = Identifier.of(Frontiers.MOD_ID, "item/empty_slot_tablet");

    private final ScreenHandlerContext context;
    private final Inventory inventory = new SimpleInventory(2)
    {
        @Override
        public void markDirty()
        {
            super.markDirty();
            CurseAltarScreenHandler.this.onContentChanged(this);
        }
    };

    public CurseAltarScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public CurseAltarScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(ModScreenHandlers.CURSE_ALTAR, syncId);
        this.context = context;

        // Slot 0 - Tool/Output
        this.addSlot(new Slot(this.inventory, 0, 88, 20)
        {
            @Override
            public int getMaxItemCount() {
                return 1;
            }

            @Override
            public boolean canInsert(ItemStack stack)
            {
                return hasCurses(stack);
            }
        });
        // Slot 1 - Tablet
        this.addSlot(new Slot(this.inventory, 1, 132, 20)
        {
            @Override
            public boolean canInsert(ItemStack stack) {
                return stack.isOf(ModItem.CURSED_TABLET);
            }

            @Override
            public Pair<Identifier, Identifier> getBackgroundSprite()
            {
                return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, CurseAltarScreenHandler.TABLET_SLOT_TEX);
            }
        });

        // Player Inv
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 9; j++) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        // Player Hotbar
        for (int i = 0; i < 9; i++) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void onClosed(PlayerEntity player)
    {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.inventory));
    }

    @Override
    public boolean onButtonClick(PlayerEntity player, int id)
    {
        if (id >= 0)
        {
            ItemStack itemStack = this.inventory.getStack(0);
            ItemStack itemStack2 = this.inventory.getStack(1);

            if ((itemStack.isEmpty() || itemStack2.isEmpty()))
            {
                return false;
            }
            else
            {
                this.context.run((world, pos) -> {

                    if (itemStack.isOf(Items.END_CRYSTAL)) this.inventory.setStack(0, new ItemStack(ModItem.PURIFIED_END_CRYSTAL, itemStack.getCount()));
                    else this.inventory.setStack(0, this.removeCurses(itemStack));

                    itemStack2.decrementUnlessCreative(1, player);
                    if (itemStack2.isEmpty())
                    {
                        this.inventory.setStack(1, ItemStack.EMPTY);
                    }

                    if (!player.isCreative()) player.addExperienceLevels(-CurseAltarScreen.REQUIRED_XP);
                    player.incrementStat(ModStats.REMOVE_CURSE);
                    if (player instanceof ServerPlayerEntity)
                    {
                        ModCriteria.USED_CURSE_ALTAR.trigger((ServerPlayerEntity)player, itemStack);
                    }

                    this.inventory.markDirty();
                    this.onContentChanged(this.inventory);
                    world.playSound(null, pos, ModSounds.CURSE_ALTAR_USE, SoundCategory.BLOCKS, 1.2F, world.random.nextFloat() * 0.1F + 0.9F);
                });
                return true;
            }
        }
        else
        {
            Util.error(player.getName() + " pressed invalid button id: " + id);
            return false;
        }
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slot)
    {
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot2 = this.slots.get(slot);
        if (slot2 != null && slot2.hasStack())
        {
            ItemStack itemStack2 = slot2.getStack();
            itemStack = itemStack2.copy();
            if (slot == 0)
            {
                if (!this.insertItem(itemStack2, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot == 1)
            {
                if (!this.insertItem(itemStack2, 2, 38, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (itemStack2.isOf(ModItem.CURSED_TABLET))
            {
                if (!this.insertItem(itemStack2, 1, 2, true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else
            {
                if (this.slots.get(0).hasStack() || !this.slots.get(0).canInsert(itemStack2))
                {
                    return ItemStack.EMPTY;
                }

                ItemStack itemStack3 = itemStack2.copyWithCount(1);
                itemStack2.decrement(1);
                this.slots.get(0).setStack(itemStack3);
            }

            if (itemStack2.isEmpty())
            {
                slot2.setStack(ItemStack.EMPTY);
            }
            else
            {
                slot2.markDirty();
            }

            if (itemStack2.getCount() == itemStack.getCount())
            {
                return ItemStack.EMPTY;
            }

            slot2.onTakeItem(player, itemStack2);
        }

        return itemStack;
    }

    @Override
    public boolean canUse(PlayerEntity player)
    {
        return canUse(this.context, player, ModBlocks.CURSE_ALTAR);
    }

    public boolean hasCurses(ItemStack stack)
    {
        if (stack.isOf(Items.END_CRYSTAL)) return true;

        ItemEnchantmentsComponent comp = stack.getEnchantments();
        if (comp != null)
        {
            Set<RegistryEntry<Enchantment>> enc_list = comp.getEnchantments();
            for (RegistryEntry<Enchantment> enc : enc_list)
            {
                if (enc.isIn(EnchantmentTags.CURSE))
                {
                    return true;
                }
            }
        }
        return false;
    }

    public ItemStack removeCurses(ItemStack stack)
    {
        ItemEnchantmentsComponent comp = stack.getEnchantments();
        if (comp != null)
        {
            ItemStack copy = stack.copy();
            copy.set(DataComponentTypes.ENCHANTMENTS, ItemEnchantmentsComponent.DEFAULT);

            Set<RegistryEntry<Enchantment>> enc_list = comp.getEnchantments();
            for (RegistryEntry<Enchantment> enc : enc_list)
            {
                if (!enc.isIn(EnchantmentTags.CURSE))
                {
                    copy.addEnchantment(enc, stack.getEnchantments().getLevel(enc));
                }
            }

            return copy;

        }
        return stack;
    }
}
