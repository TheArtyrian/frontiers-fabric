package net.artyrian.frontiers.definition.item.custom;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;

public class CustomShieldItem extends ShieldItem
{
    private final boolean usingtag;
    private final TagKey<Item> repairTag;
    private final Item repairItem;
    private final String texID;

    public CustomShieldItem(String texName, Item repairitem, Properties settings)
    {
        super(settings);
        this.repairItem = repairitem;
        this.repairTag = null;
        this.usingtag = false;

        this.texID = texName;
    }

    public CustomShieldItem(String texName, TagKey<Item> repairtag, Properties settings)
    {
        super(settings);
        this.repairTag = repairtag;
        this.repairItem = null;
        this.usingtag = true;

        this.texID = texName;
    }

    @Override
    public boolean isValidRepairItem(ItemStack stack, ItemStack ingredient)
    {
        boolean ingredientMatches = (this.usingtag)
                ? this.repairTag != null && ingredient.is(this.repairTag)
                : this.repairItem != null && ingredient.is(this.repairItem);

        return ingredientMatches || super.isValidRepairItem(stack, ingredient);
    }

    public ResourceLocation getTexID(boolean hasBanner)
    {
        StringBuilder returner = new StringBuilder("entity/" + texID + "_base");
        if (!hasBanner) returner.append("_nopattern");

        return ResourceLocation.fromNamespaceAndPath(Frontiers.MOD_ID, String.valueOf(returner));
    }
}
