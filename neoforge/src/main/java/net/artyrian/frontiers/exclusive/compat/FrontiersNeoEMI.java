package net.artyrian.frontiers.exclusive.compat;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import net.artyrian.frontiers.compat.recipeview.emi.FrontiersEMI;

@EmiEntrypoint
public class FrontiersNeoEMI implements EmiPlugin
{
    @Override
    public void register(EmiRegistry emiRegistry)
    {
        FrontiersEMI.register(emiRegistry);
    }
}