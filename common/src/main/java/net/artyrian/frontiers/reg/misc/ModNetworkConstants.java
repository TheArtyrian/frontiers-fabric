package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.entity.ItemVacuumBlockEntity;
import net.artyrian.frontiers.definition.data.nbt_sync.PlayerPersistentNBT;
import net.artyrian.frontiers.definition.entity.misc.CragsStalkerEntity;
import net.artyrian.frontiers.definition.item.component.BottleContentComponent;
import net.artyrian.frontiers.definition.networking.packet.BossBarMusicS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.networking.packet.ManaOrbSpawnS2CPacket;
import net.artyrian.frontiers.definition.networking.payload.*;
import net.artyrian.frontiers.definition.networking.payload.attachment.*;
import net.artyrian.frontiers.mixin_intf.*;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.Filterable;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Ocelot;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import net.minecraft.world.entity.monster.hoglin.Hoglin;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ModNetworkConstants
{
    // Payload Packets
    public static final ResourceLocation WITHER_HARDMODE = Frontiers.id("wither_hardmode");
    public static final ResourceLocation ORE_WITHER_PACKET = Frontiers.id("ore_wither_packet");
    public static final ResourceLocation PLAYER_AVARICE_PACKET = Frontiers.id("player_avarice_packet");
    public static final ResourceLocation SANITY_SYNC_PACKET = Frontiers.id("sanity_sync_packet");
    public static final ResourceLocation CRAGS_STALKER_DESPAWN_PACKET = Frontiers.id("crags_stalker_despawn_packet");
    public static final ResourceLocation CRAGS_MONSTER_KILL_PACKET = Frontiers.id("crags_monster_kill_packet");
    public static final ResourceLocation CHANCE_FOOD_ITEM = Frontiers.id("chance_food_item");
    public static final ResourceLocation ITEM_VACUUM_EMPTY = Frontiers.id("item_vacuum_empty");
    public static final ResourceLocation ITEM_VACUUM_SYNC = Frontiers.id("item_vacuum_sync");

    public static final ResourceLocation MESSAGE_BOTTLE = Frontiers.id("message_bottle");

    public static final ResourceLocation BOBBER = Frontiers.id("bobber_p2p");
    public static final ResourceLocation CHICKEN = Frontiers.id("chicken_p2p");
    public static final ResourceLocation END_CRYSTAL = Frontiers.id("end_crystal_p2p");
    public static final ResourceLocation EVO_FANGS = Frontiers.id("evo_fangs_p2p");
    public static final ResourceLocation HOGLIN = Frontiers.id("hoglin_p2p");
    public static final ResourceLocation LIGHTNING = Frontiers.id("lightning_p2p");
    public static final ResourceLocation OCELOT = Frontiers.id("ocelot_p2p");

    // Basic S2C Packets
    public static final PacketType<ItemBlockPickupS2CPacket> PICKUP_TO_BLOCK = doS2CPacket("frontiers_pickup_to_block");
    public static final PacketType<ManaOrbSpawnS2CPacket> SPAWN_MANA_ORB = doS2CPacket("frontiers_spawn_mana_orb");
    public static final PacketType<BossBarMusicS2CPacket> UPDATE_BOSSBAR_MUSIC = doS2CPacket("frontiers_update_bossbar_music");

    private static <T extends Packet<ClientGamePacketListener>> PacketType<T> doS2CPacket(String id)
    {
        return new PacketType<>(PacketFlow.CLIENTBOUND, Frontiers.id(id));
    }

    public static class ToServer
    {
        public static void bottleMessageWrite(BottleMessageWritePayload payload, ServerPlayer player)
        {
            int slot = payload.slot();
            String text = payload.text();

            ItemStack signed = new ItemStack(ModItem.BOTTLED_MESSAGE.get());
            signed.set(ModDataComponents.BOTTLE_CONTENT.get(), new BottleContentComponent(Filterable.passThrough(text)));

            player.getInventory().setItem(slot, signed);
        }
    }

    public static class ToClient
    {
        public static void witherHardmodeSet(WitherHardmodePayload payload, Minecraft client)
        {
            client.execute(() -> {
                if (payload.bool()) Frontiers.LOGGER.info("[Frontiers] Hardmode has been successfully set.");
            });
        }

        public static void witherOre(OreWitherPayload payload, Level world)
        {
            for (int i = 0; i < 12; i++)
            {
                world.addParticle(
                        ParticleTypes.SMOKE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
            }
            for (int i = 0; i < 8; i++)
            {
                world.addParticle(
                        ModParticle.BLACK_PARTICLE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
                world.addParticle(
                        ModParticle.WITHER_PARTICLE,
                        payload.pos().getX() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getY() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        payload.pos().getZ() + 0.5 + ((double)world.random.nextFloat() - 0.5),
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4,
                        ((double)world.random.nextFloat() - 0.5) * 0.4
                );
            }

            world.addParticle(
                    ModParticle.WITHER_FACE.get(),
                    payload.pos().getX() + 0.5,
                    payload.pos().getY() + 0.5,
                    payload.pos().getZ() + 0.5,
                    0.0,
                    0.02,
                    0.0
            );

            world.playLocalSound(payload.pos(), ModSounds.ORE_WITHER.get(), SoundSource.BLOCKS, 0.8F,
                    1.0F / (world.getRandom().nextFloat() * 0.4F + 0.8F),
                    false);
        }

        public static void avariceTotem(PlayerAvariceTotemPayload payload, LocalPlayer player)
        {
            boolean boolpayload = payload.bool();

            PlayerPersistentNBT.AvariceTotem.setTotemStatus(((PlayerMixInterface)player), boolpayload);

            //NbtCompound persistentData = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
            //if (persistentData != null && persistentData.contains("totem"))
            //{
            //    Frontiers.LOGGER.info("Player avarice check (S2C) -> " + String.valueOf(persistentData.getBoolean("totem")));
            //}
        }

        public static void sanitySync(SanitySyncPayload payload, Player reciever)
        {
            UUID uuid = payload.player_id();
            int sanity = payload.sanity();
            int sanitytick = payload.sanitytick();

            Player player = reciever.level().getPlayerByUUID(uuid);

            if (player != null)
            {
                CompoundTag compound = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
                compound.putInt("sanity", sanity);
                compound.putInt("sanity_tick", sanitytick);
            }
            else
            {
                Frontiers.LOGGER.warn("[FRONTIERS]: Received sanity sync packet with an unknown player UUID of " + uuid.toString() + ", ignoring");
            }
        }

        public static void cragsMonsterKillPlayer(CragsMonsterKillPayload payload, LocalPlayer player)
        {
            CompoundTag compound = ((PlayerMixInterface)player).frontiersArtyrian$getPersistentNbt();
            compound.putBoolean("cragsmonster_kill", payload.bool());
        }

        public static void despawnCragsStalker(CragsStalkerDespawnPayload payload, Level world)
        {
            RandomSource random = world.getRandom();

            Vec3 remappedpos = new Vec3(payload.x(), payload.y(), payload.z());

            for (int i = 0; i < 20; i++)
            {
                world.addParticle(CragsStalkerEntity.SMOG,
                        remappedpos.x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        remappedpos.y() + (0.1 * (1 + (random.nextInt(17)))),
                        remappedpos.z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        (0.1 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.1 * random.nextIntBetweenInclusive(-2, 2)));

                world.addParticle(ModParticle.CRAG_SMOG.get(),
                        remappedpos.x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        remappedpos.y() + 1.0,
                        remappedpos.z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                        (0.1 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.1 * random.nextIntBetweenInclusive(-2, 2)));
            }
        }

        public static void chanceFoodItem(ChanceFoodItemPayload payload, LocalPlayer player)
        {
            ItemStack stack = payload.stack();

            stack.consume(1, player);
        }

        public static void emptyItemVacuum(ItemVacuumEmptyPayload payload, Level world)
        {
            BlockPos pos = payload.pos();
            BlockEntity entityAt = world.getBlockEntity(pos);

            if (entityAt instanceof ItemVacuumBlockEntity vac)
            {
                vac.setTheItem(ItemStack.EMPTY);
                world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                world.updateNeighbourForOutputSignal(pos, world.getBlockState(pos).getBlock());
            }
        }

        public static void syncItemVacuumStack(ItemVacuumStackSyncPayload payload, Level world)
        {
            BlockPos pos = payload.pos();
            ItemStack stack = payload.stack();
            BlockEntity entityAt = world.getBlockEntity(pos);

            if (entityAt instanceof ItemVacuumBlockEntity vac)
            {
                vac.setTheItem(stack);
                world.sendBlockUpdated(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
                world.updateNeighbourForOutputSignal(pos, world.getBlockState(pos).getBlock());
            }
        }

        public static void syncBobber(BobberPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof FishingHook hook)
            {
                ((BobberIntf)hook).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncChicken(ChickenPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof Chicken chicken)
            {
                ((ChickenIntf)chicken).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncEndCrystal(EndCrystalPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof EndCrystal cryst)
            {
                ((EndCrystalIntf)cryst).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncEvoFangs(EvoFangsPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof EvokerFangs evo)
            {
                ((EvoFangsIntf)evo).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncHoglin(HoglinPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof Hoglin hog)
            {
                ((HoglinIntf)hog).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncLightning(LightningPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof LightningBolt bolt)
            {
                ((LightningIntf)bolt).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }

        public static void syncOcelot(OcelotPayload payload, Level level)
        {
            Entity target = level.getEntity(payload.id());
            if (target instanceof Ocelot ocelot)
            {
                ((OcelotMixIntf)ocelot).frontiersArtyrian$syncNbt(payload.nbt());
            }
        }
    }
}
