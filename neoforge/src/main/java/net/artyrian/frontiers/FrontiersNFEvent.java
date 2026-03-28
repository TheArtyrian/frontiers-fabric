package net.artyrian.frontiers;

import net.artyrian.frontiers.definition.block.custom.HardmodeLockedExpBlock;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.definition.event.BlockEvent;
import net.artyrian.frontiers.definition.event.ItemUseEvents;
import net.artyrian.frontiers.definition.event.MixinShortcuts;
import net.artyrian.frontiers.reg.misc.FRTrade;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.level.BlockDropsEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;
import net.vertisoft.vectorlib.VectorLibNF;
import net.vertisoft.vectorlib.agnostic.util.VectorTrade;

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
    public static void preHurt(LivingDamageEvent.Pre event)
    {
        float target = MixinShortcuts.doWitchHatDamage(event.getEntity(), event.getOriginalDamage(), event.getSource());
        if (target < event.getOriginalDamage() && target < event.getNewDamage()) event.setNewDamage(target);
    }

    @SubscribeEvent
    public static void onBreakBlockBefore(net.neoforged.neoforge.event.level.BlockEvent.BreakEvent event)
    {
        BlockEvent.oreWitherAway((Level)event.getLevel(), event.getPlayer(), event.getPos(), event.getState(), null);
    }

    @SubscribeEvent
    public static void villagerTradeReg(VillagerTradesEvent event)
    {
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLibNF.villagerTrades(event);
    }

    @SubscribeEvent
    public static void wanderingTradeReg(WandererTradesEvent event)
    {
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLibNF.wanderingTrades(event);
    }

    @SubscribeEvent
    public static void commandsReg(RegisterCommandsEvent event)
    {
        // TODO: MOVE IF SEPARATING VECTOR AT ANY POINT!!!
        VectorLibNF.commands(event);
    }
}
