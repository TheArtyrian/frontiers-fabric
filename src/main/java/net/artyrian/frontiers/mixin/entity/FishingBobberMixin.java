package net.artyrian.frontiers.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.util.FishingBobberInterface;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// Mixes customs into fishing bobber class.
@Mixin(FishingBobberEntity.class)
public class FishingBobberMixin extends ProjectileMixin
{
    public static final Identifier VANILLA_BOBBER = Identifier.ofVanilla("textures/entity/fishing_hook.png");
    public static final Identifier COBALT_BOBBER = Identifier.of(Frontiers.MOD_ID, "textures/entity/cobalt_fishing_hook.png");
    public static final RenderLayer VANILLA_LAYER = RenderLayer.getEntityCutout(VANILLA_BOBBER);
    public static final RenderLayer COBALT_LAYER = RenderLayer.getEntityCutout(COBALT_BOBBER);

    private Item ITEM_PARENT = ModItem.COBALT_FISHING_ROD;

    private int rod_tier = 0;

    //@Override
    public int getLineColor()
    {
        if (rod_tier == 1) return Colors.BLUE;
        else return Colors.BLACK;
    }

    //@Override
    public Identifier getBobberTex()
    {
        if (rod_tier == 1) return COBALT_BOBBER;
        else return VANILLA_BOBBER;
    }

    @Unique
    public RenderLayer getBobberLayer()
    {
        if (rod_tier == 1) return VANILLA_LAYER;
        else return COBALT_LAYER;
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("HEAD"))
    public void writeCustomDataToNbt(NbtCompound nbt, CallbackInfo info)
    {
        nbt.putInt("frontiers:RodTier", this.rod_tier);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("HEAD"))
    public void readCustomDataFromNbt(NbtCompound nbt, CallbackInfo info)
    {
        this.rod_tier = nbt.getInt("frontiers:RodTier");
    }

    @WrapOperation(method = "removeIfInvalid", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean removeIfInvalid(ItemStack stack, Item item, Operation<Boolean> original)
    {
        return (stack.isOf(ITEM_PARENT));
    }
}