package net.artyrian.frontiers.client.screen.fletching;

import com.mojang.datafixers.util.Pair;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.client.screen.ModScreenHandlers;
import net.artyrian.frontiers.client.screen.curse.CurseAltarScreenHandler;
import net.artyrian.frontiers.recipe.ModRecipes;
import net.artyrian.frontiers.recipe.fletching.ArrowFletchingRecipe;
import net.artyrian.frontiers.recipe.fletching.ArrowFletchingRecipeInput;
import net.artyrian.frontiers.sounds.ModSounds;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.SmithingRecipe;
import net.minecraft.recipe.input.SmithingRecipeInput;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.screen.slot.Slot;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FletchingTableScreenHandler extends ScreenHandler
{
    static final Identifier HEAD_SLOT_TEX = Identifier.of(Frontiers.MOD_ID, "item/empty_slot_arrowhead");
    static final Identifier STICK_SLOT_TEX = Identifier.of(Frontiers.MOD_ID, "item/empty_slot_stick");
    static final Identifier FEATHER_SLOT_TEX = Identifier.of(Frontiers.MOD_ID, "item/empty_slot_feather");

    private final ScreenHandlerContext context;
    protected final Inventory input;
    protected final CraftingResultInventory output = new CraftingResultInventory();
    private final List<Integer> inputSlotIndices;
    private final int resultSlotIndex;

    private Identifier arrow_texture = null;

    private final World world;
    @Nullable
    private RecipeEntry<ArrowFletchingRecipe> currentRecipe;
    private final List<RecipeEntry<ArrowFletchingRecipe>> recipes;

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory)
    {
        this(syncId, playerInventory, ScreenHandlerContext.EMPTY);
    }

    public FletchingTableScreenHandler(int syncId, PlayerInventory playerInventory, ScreenHandlerContext context)
    {
        super(ModScreenHandlers.FLETCHING_TABLE, syncId);
        this.context = context;
        this.world = playerInventory.player.getWorld();
        this.recipes = this.world.getRecipeManager().listAllOfType(ModRecipes.ARROW_FLETCHING);

        // Setup slots
        FletchingSlotsManager slotMan = this.getSlotManager();
        this.input = this.createInputInventory(slotMan.getInputSlotCount());
        this.inputSlotIndices = slotMan.getInputSlotIndices();
        this.resultSlotIndex = slotMan.getResultSlotIndex();
        this.addInputSlots(slotMan);
        this.addResultSlot(slotMan);

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

    protected FletchingSlotsManager getSlotManager()
    {
        return FletchingSlotsManager.create()
                .input(0, 80, 16, stack -> true, HEAD_SLOT_TEX)         // Arrowhead
                .input(1, 80, 36, stack -> true, STICK_SLOT_TEX)        // Stick
                .input(2, 80, 56, stack -> true, FEATHER_SLOT_TEX)      // Feather
                .output(3, 138, 36)
                .build();
    }

    private void addInputSlots(FletchingSlotsManager slotMan)
    {
        for (final FletchingSlotsManager.FletchingSlot fletchingSlot : slotMan.getInputSlots())
        {
            if (fletchingSlot.slotTex() == null)
            {
                this.addSlot(new Slot(this.input, fletchingSlot.slotId(), fletchingSlot.x(), fletchingSlot.y())
                {
                    @Override
                    public boolean canInsert(ItemStack stack)
                    {
                        return fletchingSlot.mayPlace().test(stack);
                    }
                });
            }
            else
            {
                this.addSlot(new Slot(this.input, fletchingSlot.slotId(), fletchingSlot.x(), fletchingSlot.y())
                {
                    @Override
                    public boolean canInsert(ItemStack stack)
                    {
                        return fletchingSlot.mayPlace().test(stack);
                    }

                    @Override
                    public Pair<Identifier, Identifier> getBackgroundSprite()
                    {
                        return Pair.of(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, fletchingSlot.slotTex());
                    }
                });
            }
        }
    }

    private void addResultSlot(FletchingSlotsManager slotMan)
    {
        this.addSlot(
                new Slot(this.output, slotMan.getResultSlot().slotId(), slotMan.getResultSlot().x(), slotMan.getResultSlot().y())
                {
                    @Override
                    public boolean canInsert(ItemStack stack) {
                        return false;
                    }

                    @Override
                    public boolean canTakeItems(PlayerEntity playerEntity)
                    {
                        return FletchingTableScreenHandler.this.canTakeOutput(playerEntity, this.hasStack());
                    }

                    @Override
                    public void onTakeItem(PlayerEntity player, ItemStack stack) {
                        FletchingTableScreenHandler.this.onTakeOutput(player, stack);
                    }
                }
        );
    }

    private SimpleInventory createInputInventory(int size)
    {
        return new SimpleInventory(size)
        {
            @Override
            public void markDirty()
            {
                super.markDirty();
                FletchingTableScreenHandler.this.onContentChanged(this);
            }
        };
    }

    private ArrowFletchingRecipeInput createRecipeInput()
    {
        return new ArrowFletchingRecipeInput(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2));
    }

    @Override
    public void onClosed(PlayerEntity player)
    {
        super.onClosed(player);
        this.context.run((world, pos) -> this.dropInventory(player, this.input));
    }

    @Override
    public void onContentChanged(Inventory inventory)
    {
        super.onContentChanged(inventory);
        if (inventory == this.input)
        {
            this.updateResult();
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
            int i = this.getPlayerInventoryStartIndex();
            int j = this.getPlayerHotbarEndIndex();

            if (slot == this.getResultSlotIndex())
            {
                if (!this.insertItem(itemStack2, i, j, true))
                {
                    return ItemStack.EMPTY;
                }

                slot2.onQuickTransfer(itemStack2, itemStack);
            }
            else if (this.inputSlotIndices.contains(slot))
            {
                if (!this.insertItem(itemStack2, i, j, false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot >= this.getPlayerInventoryStartIndex() && slot < this.getPlayerHotbarEndIndex())
            {
                int k = this.getSlotFor(itemStack);
                if (!this.insertItem(itemStack2, k, this.getResultSlotIndex(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (slot >= this.getPlayerInventoryStartIndex() && slot < this.getPlayerInventoryEndIndex())
            {
                if (!this.insertItem(itemStack2, this.getPlayerHotbarStartIndex(), this.getPlayerHotbarEndIndex(), false))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (
                    slot >= this.getPlayerHotbarStartIndex()
                    && slot < this.getPlayerHotbarEndIndex()
                    && !this.insertItem(itemStack2, this.getPlayerInventoryStartIndex(), this.getPlayerInventoryEndIndex(), false)
            )
            {
                return ItemStack.EMPTY;
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
        return canUse(this.context, player, Blocks.FLETCHING_TABLE);
    }


    public void updateResult()
    {
        ArrowFletchingRecipeInput recipeInput = this.createRecipeInput();
        List<RecipeEntry<ArrowFletchingRecipe>> list = this.world.getRecipeManager().getAllMatches(ModRecipes.ARROW_FLETCHING, recipeInput, this.world);
        if (list.isEmpty())
        {
            this.output.setStack(0, ItemStack.EMPTY);
            this.arrow_texture = null;
        }
        else
        {
            RecipeEntry<ArrowFletchingRecipe> recipeEntry = list.get(0);
            ItemStack itemStack = recipeEntry.value().craft(recipeInput, this.world.getRegistryManager());
            if (itemStack.isItemEnabled(this.world.getEnabledFeatures()))
            {
                this.currentRecipe = recipeEntry;
                this.output.setLastRecipe(recipeEntry);
                this.output.setStack(0, itemStack);
                if (recipeEntry.value().getArrowTex() != null)
                {
                    this.arrow_texture = recipeEntry.value().getArrowTex();
                }
            }
        }
    }

    protected boolean canTakeOutput(PlayerEntity player, boolean present)
    {
        return this.currentRecipe != null && this.currentRecipe.value().matches(this.createRecipeInput(), this.world);
    }

    public void onTakeOutput(PlayerEntity player, ItemStack stack)
    {
        stack.onCraftByPlayer(player.getWorld(), player, stack.getCount());
        this.output.unlockLastRecipe(player, this.getInputStacks());
        this.decrementStack(0);
        this.decrementStack(1);
        this.decrementStack(2);
        this.context.run((world, pos) ->
                world.playSound(
                        null,
                        pos,
                        ModSounds.FLETCHING_TABLE_USE,
                        SoundCategory.BLOCKS, 1.0F, world.random.nextFloat() * 0.1F + 0.9F)
        );
    }

    private void decrementStack(int slot)
    {
        ItemStack itemStack = this.input.getStack(slot);
        if (!itemStack.isEmpty())
        {
            itemStack.decrement(1);
            this.input.setStack(slot, itemStack);
        }
    }

    // A bunch of methods for getting slot indices.
    public int getSlotFor(ItemStack stack) { return this.input.isEmpty() ? 0 : this.inputSlotIndices.get(0); }
    public int getResultSlotIndex() { return this.resultSlotIndex; }
    public Identifier getArrowTex() { return arrow_texture; }
    private List<ItemStack> getInputStacks() { return List.of(this.input.getStack(0), this.input.getStack(1), this.input.getStack(2)); }
    private int getPlayerInventoryStartIndex() { return this.getResultSlotIndex() + 1; }
    private int getPlayerInventoryEndIndex() { return this.getPlayerInventoryStartIndex() + 27; }
    private int getPlayerHotbarStartIndex() { return this.getPlayerInventoryEndIndex(); }
    private int getPlayerHotbarEndIndex() { return this.getPlayerHotbarStartIndex() + 9; }
}
