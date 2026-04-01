package net.artyrian.frontiers.definition.menu.curse;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public record CurseEnchantInst(Optional<Holder<Enchantment>> enchantment, Optional<Integer> level, Integer cost, Integer chargeCost, boolean unconventional)
{
    // Insane things happened here
    public static final StreamCodec<RegistryFriendlyByteBuf, CurseEnchantInst> CODEC = new StreamCodec<>()
    {
        @Override @NotNull
        public CurseEnchantInst decode(RegistryFriendlyByteBuf buffer)
        {
            Optional<Holder<Enchantment>> enc = buffer.readOptional((writer) ->{
                ResourceKey<Enchantment> key = writer.readResourceKey(Registries.ENCHANTMENT);
                Registry<Enchantment> enchReg = buffer.registryAccess().registryOrThrow(Registries.ENCHANTMENT);
                Optional<Enchantment> optench = enchReg.getOptional(key);
                if (optench.isPresent()) return enchReg.wrapAsHolder(optench.get());
                else throw new IllegalArgumentException("Could not find a holder for the enchantment!");
            });
            Optional<Integer> level = buffer.readOptional(FriendlyByteBuf::readInt);
            Integer cost = buffer.readInt();
            Integer charge = buffer.readInt();
            boolean uncon = buffer.readBoolean();
            return new CurseEnchantInst(enc, level, cost, charge, uncon);
        }

        @Override
        public void encode(RegistryFriendlyByteBuf buffer, CurseEnchantInst inst)
        {
            buffer.writeOptional(inst.enchantment, (writer, holder) -> {
                holder.unwrapKey().ifPresent(writer::writeResourceKey);
            });
            buffer.writeOptional(inst.level, FriendlyByteBuf::writeInt);
            buffer.writeInt(inst.cost);
            buffer.writeInt(inst.chargeCost);
            buffer.writeBoolean(inst.unconventional);
        }
    };

    public boolean isUnconventional() { return this.unconventional() && this.enchantment.isEmpty() && this.level.isEmpty(); }

    public CurseEnchantInst(Holder<Enchantment> enchantment, Integer level, Integer cost, Integer chargeCost, boolean unconventional)
    {
        this(Optional.of(enchantment), Optional.of(level), cost, chargeCost, unconventional);
    }

    public Component getText()
    {
        if (!this.unconventional && this.enchantment.isPresent() && this.level.isPresent())
        {
            return Component.translatable("container.frontiers.curse_altar.exclaim",
                    Enchantment.getFullname(enchantment.get(), level.get())).withStyle(ChatFormatting.WHITE);
        }
        return Component.translatable("container.frontiers.curse_altar.exclaim",
                        Component.translatable("container.frontiers.curse_altar.uncurse").withStyle(ChatFormatting.GRAY)).withStyle(ChatFormatting.WHITE);
    }
}
