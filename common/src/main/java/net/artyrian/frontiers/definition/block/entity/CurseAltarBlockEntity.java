package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class CurseAltarBlockEntity extends BlockEntity implements Nameable
{
    public int ticks;
    public float tabletRotation;
    public float lastTabletRotation;
    public float targetTabletRotation;
    public float tabletGlow = 0.0F;
    private static final RandomSource RANDOM = RandomSource.create();
    @Nullable
    private Component customName;

    public CurseAltarBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY.get(), pos, state);}

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        if (this.hasCustomName())
        {
            nbt.putString("CustomName", Component.Serializer.toJson(this.customName, registryLookup));
        }
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);
        if (nbt.contains("CustomName", Tag.TAG_STRING))
        {
            this.customName = parseCustomNameSafe(nbt.getString("CustomName"), registryLookup);
        }
    }

    public static void tick(Level world, BlockPos pos, BlockState state, CurseAltarBlockEntity blockEntity)
    {
        blockEntity.lastTabletRotation = blockEntity.tabletRotation;
        Player playerEntity = world.getNearestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 3.0, false);
        if (playerEntity != null)
        {
            double d = playerEntity.getX() - ((double)pos.getX() + 0.5);
            double e = playerEntity.getZ() - ((double)pos.getZ() + 0.5);
            blockEntity.targetTabletRotation = (float) Mth.atan2(e, d);

            if (blockEntity.tabletGlow + 0.2F < 1.0F) { blockEntity.tabletGlow += 0.2F; }
            else { blockEntity.tabletGlow = 1.0F; }
        }
        else
        {
            blockEntity.targetTabletRotation += 0.02F;

            if (blockEntity.tabletGlow - 0.2F > 0.0F) { blockEntity.tabletGlow -= 0.2F; }
            else { blockEntity.tabletGlow = 0.0F; }
        }

        while (blockEntity.tabletRotation >= (float) Math.PI) { blockEntity.tabletRotation -= (float) (Math.PI * 2); }
        while (blockEntity.tabletRotation < (float) -Math.PI) { blockEntity.tabletRotation += (float) (Math.PI * 2); }

        while (blockEntity.targetTabletRotation >= (float) Math.PI) { blockEntity.targetTabletRotation -= (float) (Math.PI * 2); }
        while (blockEntity.targetTabletRotation < (float) -Math.PI) { blockEntity.targetTabletRotation += (float) (Math.PI * 2); }

        float g = blockEntity.targetTabletRotation - blockEntity.tabletRotation;

        while (g >= (float) Math.PI) { g -= (float) (Math.PI * 2); }
        while (g < (float) -Math.PI) { g += (float) (Math.PI * 2); }

        blockEntity.tabletRotation += g * 0.4F;
        blockEntity.ticks++;
    }

    @Override
    public Component getName() { return (this.customName != null ? this.customName : Component.translatable("container.frontiers.curse_altar"));}

    public void setCustomName(@Nullable Component customName) {
        this.customName = customName;
    }
    @Nullable @Override
    public Component getCustomName() {
        return this.customName;
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput components)
    {
        super.applyImplicitComponents(components);
        this.customName = components.get(DataComponents.CUSTOM_NAME);
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder)
    {
        super.collectImplicitComponents(componentMapBuilder);
        componentMapBuilder.set(DataComponents.CUSTOM_NAME, this.customName);
    }
}
