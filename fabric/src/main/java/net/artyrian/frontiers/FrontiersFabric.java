package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.event.BlockBreakEvent;
import net.artyrian.frontiers.definition.event.ItemUseEvents;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.definition.networking.payload.attachment.*;
import net.artyrian.frontiers.exclusive.loot.FabricLootModify;
import net.artyrian.frontiers.exclusive.loot.FabricLootReplace;
import net.artyrian.frontiers.exclusive.poi.PoiFabric;
import net.artyrian.frontiers.exclusive.world.FabricWorldGen;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.content.ModStatusEffects;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDispenserActions;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
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

        // Misc.
        ModItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        FRRegistries.FurnaceFuels.register();
        FRRegistries.Flammable.register();
        FRRegistries.Compostable.register();
        FRRegistries.MobAttributes.register();
        ModDispenserActions.execute();
        PoiFabric.register();

        // Packets
        regPayloads();
        regC2SPackets();

        // Modifiers
        FabricLootModify.modify();						// Mods some loot tables
        FabricLootReplace.replace();					// Replaces some loot tables
        FabricWorldGen.generate();		                // World Gen

        // Events
        registerMiscEvents();
    }

    private void registerMiscEvents()
    {
        // Use block
        UseBlockCallback.EVENT.register(ItemUseEvents::tryForMelon);

        // Break block
        PlayerBlockBreakEvents.BEFORE.register(BlockBreakEvent::oreWitherAway);

        // Elytra
        EntityElytraEvents.CUSTOM.register((entity, tickElytra) -> entity.hasEffect(ModStatusEffects.QUICK_FLIGHT));
    }

    // Payload register
    public void regPayloads()
    {
        // Server --> Client
        PayloadTypeRegistry.playS2C().register(WitherHardmodePayload.ID, WitherHardmodePayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OreWitherPayload.ID, OreWitherPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(PlayerAvariceTotemPayload.ID, PlayerAvariceTotemPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(SanitySyncPayload.ID, SanitySyncPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CragsStalkerDespawnPayload.ID, CragsStalkerDespawnPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(CragsMonsterKillPayload.ID, CragsMonsterKillPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ChanceFoodItemPayload.ID, ChanceFoodItemPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ItemVacuumEmptyPayload.ID, ItemVacuumEmptyPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ItemVacuumStackSyncPayload.ID, ItemVacuumStackSyncPayload.CODEC);

        PayloadTypeRegistry.playS2C().register(BobberPayload.ID, BobberPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(ChickenPayload.ID, ChickenPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EndCrystalPayload.ID, EndCrystalPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(EvoFangsPayload.ID, EvoFangsPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(HoglinPayload.ID, HoglinPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(LightningPayload.ID, LightningPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(OcelotPayload.ID, OcelotPayload.CODEC);

        // Client --> Server
        PayloadTypeRegistry.playC2S().register(BottleMessageWritePayload.ID, BottleMessageWritePayload.CODEC);
    }

    private void regC2SPackets()
    {
        ServerPlayNetworking.registerGlobalReceiver(BottleMessageWritePayload.ID, ((payload, context) ->
                ModNetworkConstants.ToServer.bottleMessageWrite(payload, context.player()))
        );
    }
}
