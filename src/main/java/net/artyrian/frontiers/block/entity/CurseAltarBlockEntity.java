package net.artyrian.frontiers.block.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Nameable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CurseAltarBlockEntity extends BlockEntity implements Nameable
{
    public int ticks;
    public float tabletRotation;
    public float lastTabletRotation;
    public float targetTabletRotation;
    public float tabletGlow = 0.0F;
    private static final Random RANDOM = Random.create();
    @Nullable
    private Text customName;

    public CurseAltarBlockEntity(BlockPos pos, BlockState state) { super(ModBlockEntities.CURSE_ALTAR_BLOCKENTITY, pos, state);}

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        if (this.hasCustomName())
        {
            nbt.putString("CustomName", Text.Serialization.toJsonString(this.customName, registryLookup));
        }
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("CustomName", NbtElement.STRING_TYPE))
        {
            this.customName = tryParseCustomName(nbt.getString("CustomName"), registryLookup);
        }
    }

    public static void tick(World world, BlockPos pos, BlockState state, CurseAltarBlockEntity blockEntity)
    {
        blockEntity.lastTabletRotation = blockEntity.tabletRotation;
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 3.0, false);
        if (playerEntity != null)
        {
            double d = playerEntity.getX() - ((double)pos.getX() + 0.5);
            double e = playerEntity.getZ() - ((double)pos.getZ() + 0.5);
            blockEntity.targetTabletRotation = (float) MathHelper.atan2(e, d);

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
    public Text getName() { return (this.customName != null ? this.customName : Text.translatable("container.frontiers.curse_altar"));}

    public void setCustomName(@Nullable Text customName) {
        this.customName = customName;
    }
    @Nullable @Override
    public Text getCustomName() {
        return this.customName;
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components)
    {
        super.readComponents(components);
        this.customName = components.get(DataComponentTypes.CUSTOM_NAME);
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder)
    {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(DataComponentTypes.CUSTOM_NAME, this.customName);
    }
}
