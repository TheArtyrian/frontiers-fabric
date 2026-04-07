package net.artyrian.frontiers.mixin.entity.vex;

import net.artyrian.frontiers.mixin.MobEntityMixin;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.misc.FRLootTables;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Vex.class)
public abstract class VexMixin extends MobEntityMixin
{
    @Shadow public abstract boolean isCharging();

    @Shadow public abstract void setIsCharging(boolean charging);

    @Override
    public void dropEquipmentHook(ServerLevel world, DamageSource source, boolean causedByPlayer, CallbackInfo ci)
    {
        if (causedByPlayer && this.isCharging())
        {
            this.setIsCharging(false);

            world.sendParticles(
                    FRParticles.VEX_CHARGE_PARTICLE_LR,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    6,
                    0.4,
                    0.4,
                    0.4,
                    0.7
            );
            world.sendParticles(
                    FRParticles.VEX_CHARGE_PARTICLE_R,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    6,
                    0.4,
                    0.4,
                    0.4,
                    0.7
            );

            boolean do_loot = world.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
            if (do_loot)
            {
                LootTable lootTable = world.getServer().reloadableRegistries().getLootTable(FRLootTables.VEX_RAGE);
                LootParams lootContextParameterSet = new LootParams.Builder((ServerLevel)this.level())
                        .withParameter(LootContextParams.ORIGIN, this.position())
                        .withParameter(LootContextParams.THIS_ENTITY, (Vex)(Object)this)
                        .withParameter(LootContextParams.ATTACKING_ENTITY, source.getEntity())
                        .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                        .create(LootContextParamSets.ENTITY);

                List<ItemStack> list = lootTable.getRandomItems(lootContextParameterSet);
                for (ItemStack itemStack : list)
                {
                    this.spawnAtLocation(itemStack);
                }
            }
        }
    }

    @ModifyArg(method = "populateDefaultEquipmentSlots", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;<init>(Lnet/minecraft/world/level/ItemLike;)V"))
    private ItemLike switchToMourningGold(ItemLike item)
    {
        return FRItems.MOURNING_GOLD_SWORD.get();
    }
}
