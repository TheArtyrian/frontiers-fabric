package net.artyrian.frontiers.mixin.entity.shulker;

import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.artyrian.frontiers.reg.misc.FRLootTables;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
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
        if (!thisworld.isClientSide() && source.getEntity() instanceof Player player)
        {
            boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.RULE_DOMOBLOOT);
            if (do_loot)
            {
                LootTable lootTable = thisworld.getServer().reloadableRegistries().getLootTable(FRLootTables.SHULKER_BULLET);
                LootParams lootContextParameterSet = new LootParams.Builder((ServerLevel)this.level())
                        .withParameter(LootContextParams.ORIGIN, this.position())
                        .withParameter(LootContextParams.THIS_ENTITY, (ShulkerBullet)(Object)this)
                        .withParameter(LootContextParams.DAMAGE_SOURCE, source)
                        .withParameter(LootContextParams.ATTACKING_ENTITY, player)
                        .create(LootContextParamSets.ENTITY);

                List<ItemStack> list = lootTable.getRandomItems(lootContextParameterSet);
                for (ItemStack itemStack : list)
                {
                    this.spawnAtLocation(itemStack);
                }
            }
        }
    }

}
