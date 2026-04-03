package net.vertisoft.vectorlib.agnostic.lolololol;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerPlayer;
import org.jetbrains.annotations.Nullable;

public class VectorJoinMsg
{
    @Nullable private final String join;
    @Nullable private final String leave;
    @Nullable private final String renamed;
    private final Integer color;

    public VectorJoinMsg(Integer color)
    {
        this(null, null, null, color);
    }

    public VectorJoinMsg(@Nullable String join, @Nullable String renamed, @Nullable String leave, Integer color)
    {
        this.join = join;
        this.renamed = renamed;
        this.leave = leave;
        this.color = color;
    }

    public Component changeJoin(Component original, ServerPlayer player, boolean changed, String oldName)
    {
        Component returnable = original;
        Style ogStyle = original.getStyle();

        if (changed && this.renamed != null) returnable = Component.translatable(this.renamed, player.getDisplayName(), oldName).withStyle(ogStyle);
        else if (this.join != null) returnable = Component.translatable(this.join, player.getDisplayName()).withStyle(ogStyle);

        returnable = returnable.copy().withColor(this.color);

        return returnable;
    }

    public Component changeLeave(Component original, ServerPlayer player)
    {
        Component returnable = original;
        Style ogStyle = original.getStyle();

        if (this.leave != null) returnable = Component.translatable(this.leave, player.getDisplayName()).withStyle(ogStyle);

        returnable = returnable.copy().withColor(this.color);

        return returnable;
    }
}
