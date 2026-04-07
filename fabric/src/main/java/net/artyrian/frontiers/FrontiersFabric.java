package net.artyrian.frontiers;

import net.artyrian.frontiers.compat.FRIntegReg;
import net.artyrian.frontiers.definition.event.BlockEvent;
import net.artyrian.frontiers.definition.event.ItemUseEvents;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.exclusive.poi.PoiFabric;
import net.artyrian.frontiers.exclusive.world.FabricWorldGen;
import net.artyrian.frontiers.reg.content.FRItemTabs;
import net.artyrian.frontiers.reg.content.FRStatusEffects;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.property.FRDispenserActions;
import net.artyrian.frontiers.reg.misc.FRNetworking;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.EntityElytraEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.VectorLibFabric;

public class FrontiersFabric implements ModInitializer
{
    @Override
    public void onInitialize()
    {
        Frontiers.init();
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLib.bootstrap();
        VectorLibFabric.bootstrap();

        // Integration post
        FRIntegReg.integPost();

        // Misc.
        FRItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        FRRegistries.FurnaceFuels.register();
        FRRegistries.Flammable.register();
        FRRegistries.Compostable.register();
        FRRegistries.MobAttributes.register();
        FRDispenserActions.execute();
        PoiFabric.register();

        // Packets
        regPayloads();
        regC2SPackets();

        // Modifiers
        FabricWorldGen.generate();                        // World Gen

        // Events
        registerMiscEvents();
    }

    private void registerMiscEvents()
    {
        // Use block
        UseBlockCallback.EVENT.register(ItemUseEvents::tryForMelon);

        // Break block
        PlayerBlockBreakEvents.BEFORE.register(BlockEvent::oreWitherAway);

        // Elytra
        EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> entity.hasEffect(FRStatusEffects.QUICK_FLIGHT));
    }

    // Payload register
    public void regPayloads()
    {
        // Server --> Client
        PayloadTypeRegistry.playS2C().register(WitherHardmodePayload.ID, WitherHardmodePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(PlayerAvariceTotemPayload.ID, PlayerAvariceTotemPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CragsMonsterKillPayload.ID, CragsMonsterKillPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(BuffSyncPayload.ID, BuffSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SanitySyncPayload.ID, SanitySyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ManaSyncPayload.ID, ManaSyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ChanceFoodItemPayload.ID, ChanceFoodItemPayload.CODEC);

        // Client --> Server
        PayloadTypeRegistry.playC2S().register(BottleMessageWritePayload.ID, BottleMessageWritePayload.CODEC);
    }

    private void regC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(BottleMessageWritePayload.ID, ((payload, context) ->
                FRNetworking.ToServer.bottleMessageWrite(payload, context.player()))
        );
    }
}
