package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.event.ClientEvents;
import net.artyrian.frontiers.definition.event.ItemUseEvents;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.exclusive.loot.LootNF;
import net.artyrian.frontiers.exclusive.networking.NetworkingNF;
import net.artyrian.frontiers.reg.content.ModItemTabs;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.misc.FRRegistries;
import net.artyrian.frontiers.reg.misc.ModDispenserActions;
import net.artyrian.frontiers.reg.misc.ModNetworkConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.platform.VectorClientNF;
import net.vertisoft.vectorlib.platform.VectorRegNF;

@Mod(Frontiers.MOD_ID)
public class FrontiersNF
{
    public FrontiersNF(IEventBus eventBus)
    {
        ((VectorRegNF)VectorLib.REGISTRY).setEventBus(eventBus);

        Frontiers.init();
        VectorLib.bootstrap();

        eventBus.addListener(this::clientSetup);
        eventBus.addListener(this::commonSetup);
    }

    @SubscribeEvent
    public void clientSetup(FMLClientSetupEvent event)
    {
        FrontiersClient.init();

        ClientEvents.registerDeathScreenMsg();
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent event)
    {
        ModItemTabs.registerModItemTabs();
        FRRegistries.ToolActions.register();
        FRRegistries.FurnaceFuels.register();
        FRRegistries.Flammable.register();
        FRRegistries.Compostable.register();
        FRRegistries.MobAttributes.register();
        ModDispenserActions.execute();

        // Thanks, NeoForge! :D
        FRRegistries.NoteBlockInst.FRONTIERS_LOG_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_LOG_DRUM.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_ICE_BELL.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ICE_BELL.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_HARPSICHORD.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_HARPSICHORD.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_STEEL_DRUM.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_STEEL_DRUM.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_ROBOLUNG.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_ROBOLUNG.getDelegate();
        FRRegistries.NoteBlockInst.FRONTIERS_JESKOLA.soundEvent = ModSounds.BLOCK_NOTE_BLOCK_JESKOLA.getDelegate();
    }

    @SubscribeEvent
    public void payloadSetup(final RegisterPayloadHandlersEvent event)
    {
        final PayloadRegistrar reg = event.registrar("V1");
        if (true)
        {
            // SERVER
            NetworkingNF.ToServer.register(reg);

            // CLIENT
            NetworkingNF.ToClient.register(reg);
        }
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        InteractionResult result = ItemUseEvents.tryForMelon(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());

        if (result.consumesAction()) {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    @SubscribeEvent
    public static void modifyLootTables(LootTableLoadEvent event)
    {
        LootNF.reg(event);
    }
}