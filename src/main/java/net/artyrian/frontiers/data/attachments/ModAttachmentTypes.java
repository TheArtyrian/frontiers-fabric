package net.artyrian.frontiers.data.attachments;

import com.mojang.serialization.Codec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.mixin_interfaces.BobberType;
import net.fabricmc.fabric.api.attachment.v1.AttachmentRegistry;
import net.fabricmc.fabric.api.attachment.v1.AttachmentSyncPredicate;
import net.fabricmc.fabric.api.attachment.v1.AttachmentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;

public class ModAttachmentTypes
{
    // End Crystal - hits taken
    public static final AttachmentType<Integer> ENDCRYSTAL_HITS_TAKEN = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "endcrystal_hits_taken"),
            builder -> builder
                    .initializer(() -> 0)
                    .persistent(Codec.INT)
                    .syncWith(PacketCodecs.INTEGER, AttachmentSyncPredicate.all())
    );
    // End Crystal - is friendly
    public static final AttachmentType<Boolean> ENDCRYSTAL_FRIENDLY = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "endcrystal_friendly"),
            builder -> builder
                    .initializer(() -> false)
                    .persistent(Codec.BOOL)
                    .syncWith(PacketCodecs.BOOL, AttachmentSyncPredicate.all())
    );

    // Fishing Bobber - bobber power
    public static final AttachmentType<Integer> FISHBOBBER_BOBBER_POWER = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "fishbobber_bobber_power"),
            builder -> builder
                    .initializer(BobberType.DEFAULT::ordinal)
                    .persistent(Codec.INT)
                    .syncWith(PacketCodecs.INTEGER, AttachmentSyncPredicate.all())
    );
    // Fishing Bobber - line color
    public static final AttachmentType<Integer> FISHBOBBER_LINE_COLOR = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "fishbobber_line_color"),
            builder -> builder
                    .initializer(() -> Colors.BLACK)
                    .persistent(Codec.INT)
                    .syncWith(PacketCodecs.INTEGER, AttachmentSyncPredicate.all())
    );
    // Fishing Bobber - parent item
    public static final AttachmentType<ItemStack> FISHBOBBER_PARENT_ITEM = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "fishbobber_parent_item"),
            builder -> builder
                    .initializer(() -> new ItemStack(Items.FISHING_ROD, 1))
                    .persistent(ItemStack.CODEC)
                    .syncWith(ItemStack.OPTIONAL_PACKET_CODEC, AttachmentSyncPredicate.all())
    );

    // Hoglin - is Truffled
    public static final AttachmentType<Boolean> HOGLIN_IS_TRUFFLED = AttachmentRegistry.create(
            Identifier.of(Frontiers.MOD_ID, "hoglin_is_truffled"),
            builder -> builder
                    .initializer(() -> false)
                    .persistent(Codec.BOOL)
                    .syncWith(PacketCodecs.BOOL, AttachmentSyncPredicate.all())
    );

    // Registers mod attachment types.
    public static void registerModAttachments()
    {
        //Frontiers.LOGGER.info("Registering Mod Attachment Types for " + Frontiers.MOD_ID);
    }
}
