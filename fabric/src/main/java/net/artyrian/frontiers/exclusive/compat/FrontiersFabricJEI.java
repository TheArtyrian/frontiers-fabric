package net.artyrian.frontiers.exclusive.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.artyrian.frontiers.compat.jei.FrontiersJEI;
import net.minecraft.resources.ResourceLocation;

@JeiPlugin
public class FrontiersFabricJEI implements IModPlugin
{
    @Override
    public void registerRecipes(IRecipeRegistration registration)
    {
        FrontiersJEI.registerRecipes(registration);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration)
    {
        FrontiersJEI.registerCategories(registration);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration)
    {
        FrontiersJEI.registerRecipeCatalysts(registration);
    }

    @Override public ResourceLocation getPluginUid() { return FrontiersJEI.ID; }
}
