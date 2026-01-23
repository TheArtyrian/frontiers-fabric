package net.artyrian.frontiers.mixin.entity.shulker;

import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.reg.misc.ModLootTables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.projectile.ShulkerBullet;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShulkerBullet.class)
public abstract class ShulkerBulletMixin extends ProjectileMixin
{
    @Inject(method="hurt", at = @At("HEAD"))
    public void dropShulkScum(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        Level thisworld = this.level();
        if (!thisworld.isClientSide())
        {
            float drop_scum = thisworld.getRandom().nextFloat();
            boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
            if (drop_scum >= 0.5F && do_loot)
            {
                LootTable lootTable = thisworld.getServer().reloadableRegistries().getLootTable(ModLootTables.SHULKER_BULLET);
                LootParams lootContextParameterSet = new LootParams.Builder((ServerLevel)this.level())
                        .withParameter(LootContextParams.ORIGIN, this.position())
                        .withParameter(LootContextParams.THIS_ENTITY, (ShulkerBullet)(Object)this)
                        .create(LootContextParamSets.GIFT);

                List<ItemStack> list = lootTable.getRandomItems(lootContextParameterSet);
                for (ItemStack itemStack : list)
                {
                    this.spawnAtLocation(itemStack);
                }
            }
        }
    }

}
