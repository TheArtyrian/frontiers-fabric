package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.block.custom.HardmodeLockedExpBlock;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.event.BlockBreakEvent;
import net.artyrian.frontiers.definition.event.ItemUseEvents;
import net.artyrian.frontiers.exclusive.loot.LootNF;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.LootTableLoadEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.level.BlockEvent;

@EventBusSubscriber(modid = Frontiers.MOD_ID, bus = EventBusSubscriber.Bus.GAME)
public class FrontiersNFEvent
{
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event)
    {
        InteractionResult result = ItemUseEvents.tryForMelon(event.getEntity(), event.getLevel(), event.getHand(), event.getHitVec());

        if (result.consumesAction())
        {
            event.setCanceled(true);
            event.setCancellationResult(result);
        }
    }

    // Genuinely infuriating that I have to do this HERE but we ball
    // THANKS NEOFORGE :D
    @SubscribeEvent
    public static void onBreakBlockBefore(BlockDropsEvent event)
    {
        if (event.getState().getBlock() instanceof HardmodeLockedExpBlock)
        {
            ServerLevel level = event.getLevel();

            StateSaveLoad loader = StateSaveLoad.getServerState(level.getServer());
            boolean hardmode = loader.isInHardmode;

            if (!hardmode)
            {
                event.setDroppedExperience(0);
            }
        }
    }

    @SubscribeEvent
    public static void onBreakBlockBefore(BlockEvent.BreakEvent event)
    {
        BlockBreakEvent.oreWitherAway((Level)event.getLevel(), event.getPlayer(), event.getPos(), event.getState(), null);
    }

    @SubscribeEvent
    public static void modifyLootTables(LootTableLoadEvent event)
    {
        LootNF.reg(event);
    }
}
