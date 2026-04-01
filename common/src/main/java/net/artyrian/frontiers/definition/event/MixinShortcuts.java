package net.artyrian.frontiers.definition.event;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.artyrian.frontiers.definition.networking.packet.client.ClientboundBossBarMusicPacket;
import net.artyrian.frontiers.definition.networking.packet.client.ClientboundItemToBlockPacket;
import net.artyrian.frontiers.definition.networking.packet.client.ClientboundManaOrbPacket;
import net.artyrian.frontiers.definition.networking.packet.server.ServerboundCurseAltarPacket;
import net.artyrian.frontiers.mixin_intf.BobberIntf;
import net.artyrian.frontiers.mixin_intf.HoglinIntf;
import net.artyrian.frontiers.mixin_intf.ParrotRenderIntf;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.client.model.ParrotModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.ProtocolInfoBuilder;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ServerGamePacketListener;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.entity.monster.piglin.Piglin;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;

/** A class for common code for when you need to make two different mixins for each major loader because they name their lambda methods differently!!!!1 */
public class MixinShortcuts
{
    public static void appendClientbound(ProtocolInfoBuilder<ClientGamePacketListener, RegistryFriendlyByteBuf> builder)
    {
        builder
                .addPacket(ModNetworkConstants.PICKUP_TO_BLOCK, ClientboundItemToBlockPacket.CODEC)
                .addPacket(ModNetworkConstants.SPAWN_MANA_ORB, ClientboundManaOrbPacket.CODEC)
                .addPacket(ModNetworkConstants.UPDATE_BOSSBAR_MUSIC, ClientboundBossBarMusicPacket.CODEC);
    }

    public static void appendServerbound(ProtocolInfoBuilder<ServerGamePacketListener, RegistryFriendlyByteBuf> builder)
    {
        builder
                .addPacket(ModNetworkConstants.CURSE_ALTAR_DISENCHANT, ServerboundCurseAltarPacket.STREAM_CODEC);
    }

    /** Used in Player shield checking. */
    public static boolean playerCobaltShieldCheck(boolean original, ItemStack stack)
    {
        return original || stack.is(ModItem.COBALT_SHIELD.get());
    }

    /** Used in custom Parrot shoulder rendering. */
    public static VertexConsumer parrotShoulderCustom(VertexConsumer original, ParrotModel model, CompoundTag nbtCompound, MultiBufferSource vertexConsumers)
    {
        String name = nbtCompound.getString("CustomName");

        if ("\"Kazooie\"".equals(name)) return vertexConsumers.getBuffer(model.renderType(ParrotRenderIntf.KAZOOIE_TEXTURE));
        else if ("\"Lovebirb\"".equals(name)) return vertexConsumers.getBuffer(model.renderType(ParrotRenderIntf.LOVEBIRB_TEXTURE));
        else if ("\"Keynis\"".equals(name)) return vertexConsumers.getBuffer(model.renderType(ParrotRenderIntf.KEYNIS_TEXTURE));

        return original;
    }

    /** Used in Iron Golem AI changes. */
    public static boolean ironGolemDefer(boolean original, LivingEntity entity)
    {
        return original
                && !entity.getType().is(ModTags.EntityTypes.IRON_GOLEM_NO_TARGET)
                && !(entity instanceof HoglinIntf hog && hog.frontiers_1_21x$isTruffled());
    }

    /** Used in Fishing Bobber item reeling event. */
    public static boolean fishingBobberReroute(boolean original, ItemStack stack, FishingHook hook)
    {
        return stack.is(((BobberIntf)hook).frontiers_1_21x$getParentItemStack().getItem());
    }

    /** Used in Enderman looking at event. */
    public static boolean endermanLookingAtMeOwO(boolean original, ItemStack stack)
    {
        return original ||
                stack.is(ModBlocks.CARVED_GLISTERING_MELON.get().asItem()) ||
                stack.is(ModBlocks.CARVED_MELON.get().asItem()) ||
                stack.is(ModBlocks.WHITE_PUMPKIN.get().asItem());
    }

    /** Used in Chicken food reroute event. */
    public static boolean chickenFood(boolean original, ItemStack stack)
    {
        return stack.is(ItemTags.CHICKEN_FOOD) || stack.is(ModTags.Items.GOLDEN_CHICKEN_FOOD);
    }

    /** Used in Piglin AI event. */
    public static boolean piglinOffhandDelegate(boolean original, Piglin entity)
    {
        return original || entity.getOffhandItem().is(ModTags.Items.OFFHAND_PRIORITY_ITEM);
    }

    /** Handles witch hat resist. */
    public static float doWitchHatDamage(LivingEntity target, float value, DamageSource source)
    {
        if (value > 0.0F)
        {
            if (!(target instanceof Witch) && !source.is(DamageTypeTags.BYPASSES_EFFECTS))
            {
                ItemStack stack = target.getItemBySlot(EquipmentSlot.HEAD);
                if (stack.is(ModItem.WITCH_HAT.get()) && source.is(DamageTypeTags.WITCH_RESISTANT_TO))
                {
                    int valueToDmg = Math.clamp(Math.round(value * 2), 1, 60);
                    stack.hurtAndBreak(valueToDmg, target, EquipmentSlot.HEAD);
                    return value * 0.15F;
                }
            }
        }
        return value;
    }
}
