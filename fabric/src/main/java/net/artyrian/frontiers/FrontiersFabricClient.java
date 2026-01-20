package net.artyrian.frontiers;

import net.fabricmc.api.ClientModInitializer;

public class FrontiersFabricClient implements ClientModInitializer
{
    @Override
    public void onInitializeClient()
    {
        FrontiersClient.init();
    }
}
