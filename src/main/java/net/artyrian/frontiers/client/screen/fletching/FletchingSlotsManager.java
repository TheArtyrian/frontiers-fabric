package net.artyrian.frontiers.client.screen.fletching;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.ForgingSlotsManager;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** The slot manager for the Fletching Table UI. */
public class FletchingSlotsManager
{
    private final List<FletchingSlotsManager.FletchingSlot> inputSlots;
    private final FletchingSlotsManager.FletchingSlot resultSlot;

    FletchingSlotsManager(List<FletchingSlotsManager.FletchingSlot> inputSlots, FletchingSlotsManager.FletchingSlot resultSlot)
    {
        if (!inputSlots.isEmpty() && !resultSlot.equals(FletchingSlotsManager.FletchingSlot.DEFAULT))
        {
            this.inputSlots = inputSlots;
            this.resultSlot = resultSlot;
        }
        else
        {
            throw new IllegalArgumentException("[FRONTIERS] Input and Result slots must be defined for FlectingSlotsManager!");
        }
    }

    public static FletchingSlotsManager.Builder create() {
        return new FletchingSlotsManager.Builder();
    }

    public boolean hasSlotIndex(int index) {
        return this.inputSlots.size() >= index;
    }

    public FletchingSlotsManager.FletchingSlot getInputSlot(int index)
    {
        return this.inputSlots.get(index);
    }
    public FletchingSlotsManager.FletchingSlot getResultSlot()
    {
        return this.resultSlot;
    }

    public List<FletchingSlotsManager.FletchingSlot> getInputSlots() {
        return this.inputSlots;
    }
    public int getInputSlotCount() {
        return this.inputSlots.size();
    }
    public int getResultSlotIndex() { return this.getInputSlotCount(); }

    public List<Integer> getInputSlotIndices()
    {
        return this.inputSlots.stream().map(FletchingSlotsManager.FletchingSlot::slotId).collect(Collectors.toList());
    }

    public static class Builder
    {
        private final List<FletchingSlotsManager.FletchingSlot> inputSlots = new ArrayList<>();
        private FletchingSlotsManager.FletchingSlot resultSlot = FletchingSlotsManager.FletchingSlot.DEFAULT;

        public FletchingSlotsManager.Builder input(int slotId, int x, int y, Predicate<ItemStack> mayPlace, Identifier slotTex)
        {
            this.inputSlots.add(new FletchingSlotsManager.FletchingSlot(slotId, x, y, mayPlace, slotTex));
            return this;
        }

        public FletchingSlotsManager.Builder output(int slotId, int x, int y)
        {
            this.resultSlot = new FletchingSlotsManager.FletchingSlot(slotId, x, y, stack -> false, null);
            return this;
        }

        public FletchingSlotsManager build() {
            return new FletchingSlotsManager(this.inputSlots, this.resultSlot);
        }
    }

    public record FletchingSlot(int slotId, int x, int y, Predicate<ItemStack> mayPlace, @Nullable Identifier slotTex)
    {
        static final FletchingSlotsManager.FletchingSlot DEFAULT = new FletchingSlotsManager.FletchingSlot(0, 0, 0,
                stack -> true,
                null
        );
    }
}
