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
            int OLD_CRYSTAL_COUNT = this.CRYSTAL_COUNT;
            this.CRYSTAL_COUNT = 0;

            Box boxULeft = new Box(pos.add(-1, 0, -1).toCenterPos(), pos.add(-3, 6, -3).toCenterPos());
            List<EndCrystalEntity> listUL = world.getNonSpectatingEntities(EndCrystalEntity.class, boxULeft);
            if (!listUL.isEmpty())
            {
                for (EndCrystalEntity crystal : listUL)
                {
                    if (((EndCrystalMixInterface)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalMixInterface)crystal).frontiers$getGoodBeamPos() != this.getPos() && !world.isClient)
                        {
                            ((EndCrystalMixInterface)crystal).frontiers$setGoodBeamPos(this.getPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            Box boxURight = new Box(pos.add(1, 0, -1).toCenterPos(), pos.add(3, 6, -3).toCenterPos());
            List<EndCrystalEntity> listUR = world.getNonSpectatingEntities(EndCrystalEntity.class, boxURight);
            if (!listUR.isEmpty())
            {
                for (EndCrystalEntity crystal : listUR)
                {
                    if (((EndCrystalMixInterface)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalMixInterface)crystal).frontiers$getGoodBeamPos() != this.getPos() && !world.isClient)
                        {
                            ((EndCrystalMixInterface)crystal).frontiers$setGoodBeamPos(this.getPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            Box boxDLeft = new Box(pos.add(-1, 0, 1).toCenterPos(), pos.add(-3, 6, 3).toCenterPos());
            List<EndCrystalEntity> listDL = world.getNonSpectatingEntities(EndCrystalEntity.class, boxDLeft);
            if (!listDL.isEmpty())
            {
                for (EndCrystalEntity crystal : listDL)
                {
                    if (((EndCrystalMixInterface)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalMixInterface)crystal).frontiers$getGoodBeamPos() != this.getPos() && !world.isClient)
                        {
                            ((EndCrystalMixInterface)crystal).frontiers$setGoodBeamPos(this.getPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            Box boxDRight = new Box(pos.add(1, 0, 1).toCenterPos(), pos.add(3, 6, 3).toCenterPos());
            List<EndCrystalEntity> listDR = world.getNonSpectatingEntities(EndCrystalEntity.class, boxDRight);
            if (!listDR.isEmpty())
            {
                for (EndCrystalEntity crystal : listDR)
                {
                    if (((EndCrystalMixInterface)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalMixInterface)crystal).frontiers$getGoodBeamPos() != this.getPos() && !world.isClient)
                        {
                            ((EndCrystalMixInterface)crystal).frontiers$setGoodBeamPos(this.getPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            this.markDirty();
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

    @Override
    public int frontiers$getCrystalCount()
    {
        return this.CRYSTAL_COUNT;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private static void addTickRunnableFrontiers(World world, BlockPos pos, BlockState state, EnchantingTableBlockEntity blockEntity, CallbackInfo ci)
    {
        ((EnchantTableMixInterface)blockEntity).frontiers$attemptPasseCheckForCrystals(world, pos, state);
    }
}
