package net.artyrian.frontiers.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.client.render.TexturedRenderLayers;
import net.minecraft.client.util.SpriteIdentifier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShieldItem;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.StringIdentifiable;
import org.spongepowered.asm.mixin.Unique;

public class CustomShieldItem extends ShieldItem
{
    private final boolean usingtag;
    private final TagKey<Item> repairTag;
    private final Item repairItem;
    private final String texID;

    public CustomShieldItem(String texName, Item repairitem, Settings settings)
    {
        super(settings);
        this.repairItem = repairitem;
        this.repairTag = null;
        this.usingtag = false;

        this.texID = texName;
    }

    public CustomShieldItem(String texName, TagKey<Item> repairtag, Settings settings)
    {
        super(settings);
        this.repairTag = repairtag;
        this.repairItem = null;
        this.usingtag = true;

        this.texID = texName;
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient)
    {
        boolean ingredientMatches = (this.usingtag)
                ? this.repairTag != null && ingredient.isIn(this.repairTag)
                : this.repairItem != null && ingredient.isOf(this.repairItem);

        return ingredientMatches || super.canRepair(stack, ingredient);
    }

    public Identifier getTexID(boolean hasBanner)
    {
        StringBuilder returner = new StringBuilder("entity/" + texID + "_base");
        if (!hasBanner) returner.append("_nopattern");

        return Identifier.of(Frontiers.MOD_ID, String.valueOf(returner));
    }
}
