package net.artyrian.frontiers.mixin.block.enchanting_table;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.mixin_interfaces.EnchantTableMixInterface;
import net.artyrian.frontiers.mixin_interfaces.EndCrystalMixInterface;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.EnchantingTableBlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

@Mixin(EnchantingTableBlockEntity.class)
public abstract class EnchantBlockEntityMixin extends BlockEntityMixin implements EnchantTableMixInterface
{
    @Unique private int serverTicks;

    @Unique private int CRYSTAL_COUNT = 0;

    @Override
    public void frontiers$attemptPasseCheckForCrystals(World world, BlockPos pos, BlockState state)
    {
        this.serverTicks++;
        if (this.serverTicks % 20 == 0)
        {
            Box boxULeft = new Box(pos.add(-2, 0, -2)).expand(1, 0, 1).stretch(0, 5, 0);
            List<EndCrystalEntity> listUL = world.getNonSpectatingEntities(EndCrystalEntity.class, boxULeft);

            Box boxURight = new Box(pos.add(2, 0, -2)).expand(1, 0, 1).stretch(0, 5, 0);
            List<EndCrystalEntity> listUR = world.getNonSpectatingEntities(EndCrystalEntity.class, boxURight);

            Box boxDLeft = new Box(pos.add(-2, 0, 2)).expand(1, 0, 1).stretch(0, 5, 0);
            List<EndCrystalEntity> listDL = world.getNonSpectatingEntities(EndCrystalEntity.class, boxDLeft);

            Box boxDRight = new Box(pos.add(2, 0, 2)).expand(1, 0, 1).stretch(0, 5, 0);
            List<EndCrystalEntity> listDR = world.getNonSpectatingEntities(EndCrystalEntity.class, boxDRight);
        }
    }

    @Inject(method = "readNbt", at = @At("TAIL"))
    private void readNbtMix(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo ci)
    {
        if (nbt.contains("CrystalCount", NbtElement.INT_TYPE)) this.CRYSTAL_COUNT = nbt.getInt("CrystalCount");
    }

    @Inject(method = "writeNbt", at = @At("TAIL"))
    private void writeNbtMix(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup, CallbackInfo ci)
    {
        if (this.CRYSTAL_COUNT != 0) nbt.putInt("CrystalCount", this.CRYSTAL_COUNT);
    }
}
