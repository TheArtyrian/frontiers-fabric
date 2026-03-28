package net.vertisoft.vectorlib.agnostic.lolololol;

import org.jetbrains.annotations.Nullable;

public record VectorJoinMsg(@Nullable String join, @Nullable String leave, @Nullable String renamed, @Nullable Integer color)
{
    public VectorJoinMsg(String join, String leave, String renamed, Integer color)
    {
        this.join = join;
        this.renamed = renamed;
        this.leave = leave;
        this.color = color;
    }
}
