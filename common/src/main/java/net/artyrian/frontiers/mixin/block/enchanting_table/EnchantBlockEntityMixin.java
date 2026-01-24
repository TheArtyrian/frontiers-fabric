package net.artyrian.frontiers.mixin.block.enchanting_table;

import net.artyrian.frontiers.mixin.entity.BlockEntityMixin;
import net.artyrian.frontiers.mixin_intf.EnchantTableMixInterface;
import net.artyrian.frontiers.mixin_intf.EndCrystalIntf;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.EnchantingTableBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(EnchantingTableBlockEntity.class)
public abstract class EnchantBlockEntityMixin extends BlockEntityMixin implements EnchantTableMixInterface
{
    @Unique private int serverTicks;
    @Unique private int CRYSTAL_COUNT = 0;

    @Override
    public void frontiers$attemptPasseCheckForCrystals(Level world, BlockPos pos, BlockState state)
    {
        this.serverTicks++;
        if (this.serverTicks % 20 == 0)
        {
            int OLD_CRYSTAL_COUNT = this.CRYSTAL_COUNT;
            this.CRYSTAL_COUNT = 0;

            AABB boxULeft = new AABB(pos.offset(-1, 0, -1).getCenter(), pos.offset(-3, 6, -3).getCenter());
            List<EndCrystal> listUL = world.getEntitiesOfClass(EndCrystal.class, boxULeft);
            if (!listUL.isEmpty())
            {
                for (EndCrystal crystal : listUL)
                {
                    if (((EndCrystalIntf)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalIntf)crystal).frontiers$getGoodBeamPos() != this.getBlockPos() && !world.isClientSide)
                        {
                            ((EndCrystalIntf)crystal).frontiers$setGoodBeamPos(this.getBlockPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            AABB boxURight = new AABB(pos.offset(1, 0, -1).getCenter(), pos.offset(3, 6, -3).getCenter());
            List<EndCrystal> listUR = world.getEntitiesOfClass(EndCrystal.class, boxURight);
            if (!listUR.isEmpty())
            {
                for (EndCrystal crystal : listUR)
                {
                    if (((EndCrystalIntf)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalIntf)crystal).frontiers$getGoodBeamPos() != this.getBlockPos() && !world.isClientSide)
                        {
                            ((EndCrystalIntf)crystal).frontiers$setGoodBeamPos(this.getBlockPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            AABB boxDLeft = new AABB(pos.offset(-1, 0, 1).getCenter(), pos.offset(-3, 6, 3).getCenter());
            List<EndCrystal> listDL = world.getEntitiesOfClass(EndCrystal.class, boxDLeft);
            if (!listDL.isEmpty())
            {
                for (EndCrystal crystal : listDL)
                {
                    if (((EndCrystalIntf)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalIntf)crystal).frontiers$getGoodBeamPos() != this.getBlockPos() && !world.isClientSide)
                        {
                            ((EndCrystalIntf)crystal).frontiers$setGoodBeamPos(this.getBlockPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            AABB boxDRight = new AABB(pos.offset(1, 0, 1).getCenter(), pos.offset(3, 6, 3).getCenter());
            List<EndCrystal> listDR = world.getEntitiesOfClass(EndCrystal.class, boxDRight);
            if (!listDR.isEmpty())
            {
                for (EndCrystal crystal : listDR)
                {
                    if (((EndCrystalIntf)crystal).frontiers_1_21x$isFriendly())
                    {
                        if (((EndCrystalIntf)crystal).frontiers$getGoodBeamPos() != this.getBlockPos() && !world.isClientSide)
                        {
                            ((EndCrystalIntf)crystal).frontiers$setGoodBeamPos(this.getBlockPos());
                        }
                        CRYSTAL_COUNT++;
                    }
                }
            }

            this.setChanged();
        }
    }

    @Inject(method = "loadAdditional", at = @At("TAIL"))
    private void readNbtMix(CompoundTag nbt, HolderLookup.Provider registryLookup, CallbackInfo ci)
    {
        if (nbt.contains("CrystalCount", Tag.TAG_INT)) this.CRYSTAL_COUNT = nbt.getInt("CrystalCount");
    }

    @Inject(method = "saveAdditional", at = @At("TAIL"))
    private void writeNbtMix(CompoundTag nbt, HolderLookup.Provider registryLookup, CallbackInfo ci)
    {
        if (this.CRYSTAL_COUNT != 0) nbt.putInt("CrystalCount", this.CRYSTAL_COUNT);
    }

    @Override
    public int frontiers$getCrystalCount()
    {
        return this.CRYSTAL_COUNT;
    }

    @Inject(method = "bookAnimationTick", at = @At("TAIL"))
    private static void addTickRunnableFrontiers(Level world, BlockPos pos, BlockState state, EnchantingTableBlockEntity blockEntity, CallbackInfo ci)
    {
        ((EnchantTableMixInterface)blockEntity).frontiers$attemptPasseCheckForCrystals(world, pos, state);
    }
}
