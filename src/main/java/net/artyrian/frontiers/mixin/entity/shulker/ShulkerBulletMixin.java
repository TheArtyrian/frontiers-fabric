package net.artyrian.frontiers.mixin.entity.shulker;

import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.misc.ModLootTables;
import net.artyrian.frontiers.mixin.entity.ProjectileMixin;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.projectile.ShulkerBulletEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ShulkerBulletEntity.class)
public abstract class ShulkerBulletMixin extends ProjectileMixin
{
    @Inject(method="damage", at = @At("HEAD"))
    public void dropShulkScum(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        World thisworld = this.getWorld();
        if (!thisworld.isClient())
        {
            float drop_scum = thisworld.getRandom().nextFloat();
            boolean do_loot = thisworld.getGameRules().getBoolean(GameRules.DO_MOB_LOOT);
            if (drop_scum >= 0.5F && do_loot)
            {
                LootTable lootTable = thisworld.getServer().getReloadableRegistries().getLootTable(ModLootTables.SHULKER_BULLET);
                LootContextParameterSet lootContextParameterSet = new LootContextParameterSet.Builder((ServerWorld)this.getWorld())
                        .add(LootContextParameters.ORIGIN, this.getPos())
                        .add(LootContextParameters.THIS_ENTITY, (ShulkerBulletEntity)(Object)this)
                        .build(LootContextTypes.GIFT);

                List<ItemStack> list = lootTable.generateLoot(lootContextParameterSet);
                for (ItemStack itemStack : list)
                {
                    this.dropStack(itemStack);
                }
            }
        }
    }

}
