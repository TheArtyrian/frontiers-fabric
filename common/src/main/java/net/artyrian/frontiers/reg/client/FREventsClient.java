package net.artyrian.frontiers.reg.client;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.misc.CragsStalkerEntity;
import net.artyrian.frontiers.definition.item.custom.OnyxMealItem;
import net.artyrian.frontiers.definition.item.custom.SnowMeltItem;
import net.artyrian.frontiers.definition.particle.options.ColorExplodeOptions;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin_intf.GuiIntf;
import net.artyrian.frontiers.reg.content.FRItems;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.artyrian.frontiers.reg.sound.FRSounds;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSyncClient;
import org.joml.Vector3f;

public class FREventsClient
{
    private static class Local
    {
        private static void bootstrap()
        {
            // Ore Wither
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.ORE_WITHER,
                    (level, minecraft, pos, data) ->
                    {
                        for (int i = 0; i < 12; i++)
                        {
                            level.addParticle(
                                    ParticleTypes.SMOKE,
                                    pos.getX() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getY() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getZ() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4
                            );
                        }
                        for (int i = 0; i < 8; i++)
                        {
                            level.addParticle(
                                    FRParticles.BLACK_PARTICLE,
                                    pos.getX() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getY() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getZ() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4
                            );
                            level.addParticle(
                                    FRParticles.WITHER_PARTICLE,
                                    pos.getX() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getY() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    pos.getZ() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4,
                                    ((double)level.random.nextFloat() - 0.5) * 0.4
                            );
                        }

                        level.addParticle(
                                FRParticles.WITHER_FACE.get(),
                                pos.getX() + 0.5,
                                pos.getY() + 0.5,
                                pos.getZ() + 0.5,
                                0.0,
                                0.02,
                                0.0
                        );

                        level.playLocalSound(pos, FRSounds.ORE_WITHER.get(), SoundSource.BLOCKS, 0.8F,
                                1.0F / (level.getRandom().nextFloat() * 0.4F + 0.8F),
                                false);
                    }
            );

            // Tower Spawner Spawn
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.TOWER_SPAWNER_SPAWN,
                    (level, minecraft, pos, data) ->
                    {
                        boolean enraged = (data == 1);
                        RandomSource randomsource = level.random;

                        for (int u = 0; u < 20; u++)
                        {
                            double x1 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double y1 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double z1 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;

                            level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                            level.addParticle((enraged) ? FRParticles.VEX_FLAME_BIG.get() : FRParticles.TOWER_FLAME.get(), x1, y1, z1, 0.0, 0.0, 0.0);
                        }
                    }
            );

            // Tower Spawner internal entity poof
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.TOWER_SPAWNER_TINY_POOF,
                    (level, minecraft, pos, data) ->
                    {
                        boolean enraged = (data == 1);
                        RandomSource randomsource = level.random;

                        for (int u = 0; u < 8; u++)
                        {
                            double x1 = (double)pos.getX() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.3);
                            double y1 = (double)pos.getY() + 1.3 + ((randomsource.nextDouble() - 0.5) * 0.3);
                            double z1 = (double)pos.getZ() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.3);

                            level.addParticle(enraged ? ColorExplodeOptions.ENRAGED_SMALL : ColorExplodeOptions.REG_SMALL, x1, y1, z1, 0.0, 0.0, 0.0);
                        }
                    }
            );

            // Enraged Tower Spawner
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.TOWER_SPAWNER_ENRAGE,
                    (level, minecraft, pos, data) ->
                    {
                        level.playLocalSound(pos, FRSounds.TOWER_SPAWNER_ENRAGE.get(), SoundSource.BLOCKS, 2.0F, 1.0F, false);

                        RandomSource randomsource = level.random;
                        for (int u = 0; u < 20; u++)
                        {
                            double px = randomsource.nextGaussian() * 0.02;
                            double py = randomsource.nextGaussian() * 0.02;
                            double pz = randomsource.nextGaussian() * 0.02;
                            double x1 = (double)pos.getX() + 0.5 + ((randomsource.nextDouble() - 0.5) * 1.2);
                            double y1 = (double)pos.getY() + 0.5 + ((randomsource.nextDouble() - 0.5) * 1.2);
                            double z1 = (double)pos.getZ() + 0.5 + ((randomsource.nextDouble() - 0.5) * 1.2);

                            level.addParticle(ColorExplodeOptions.ENRAGED_BIG, x1, y1, z1, px, py, pz);

                            double x2 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double y2 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double z2 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            level.addParticle(FRParticles.VEX_FLAME_BIG.get(), x2, y2, z2, 0.0, 0.0, 0.0);
                        }
                    }
            );

            // Onyx Meal
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.ONYX_MEAL,
                    (level, minecraft, pos, data) ->
                    {
                        OnyxMealItem.createBadParticles(level, pos, data);
                        level.playLocalSound(pos, FRSounds.ONYX_MEAL_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    }
            );

            // Snow Melt
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.SNOW_MELT,
                    (level, minecraft, pos, data) ->
                    {
                        SnowMeltItem.createParticles(level, pos, data);
                        level.playLocalSound(pos, FRSounds.SNOW_MELT_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
                    }
            );

            // Glistening Snow Melt on block
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.SNOW_MELT_GLISTEN,
                    (level, minecraft, pos, data) ->
                    {
                        if (Frontiers.CONFIG.doSnowMeltGlisten())
                        {
                            RandomSource randomsource = level.random;

                            for (int u = 0; u < data; u++)
                            {
                                double px = randomsource.nextGaussian() * 0.02;
                                double py = randomsource.nextGaussian() * 0.02;
                                double pz = randomsource.nextGaussian() * 0.02;
                                double x1 = (double)pos.getX() + 0.5 + ((randomsource.nextDouble() - 0.5) * 1.2);
                                double y1 = (double)pos.getY() + 1.2 + ((randomsource.nextDouble() - 0.5) * 1.2);
                                double z1 = (double)pos.getZ() + 0.5 + ((randomsource.nextDouble() - 0.5) * 1.2);

                                level.addParticle(FRParticles.SNOW_GLINT.get(), x1, y1, z1, px, py, pz);
                            }
                        }
                    }
            );

            // Cursed Tablet place
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.CURSED_TABLET,
                    (level, minecraft, pos, data) ->
                    {
                        if (Frontiers.CONFIG.doCursedTabletFX())
                        {
                            Vec3 area = pos.getCenter().add(0.0, 0.4, 0.0);

                            for (int i = 0; i < 10; i++)
                            {
                                double xx = level.getRandom().nextGaussian() * 0.02;
                                double yy = level.getRandom().nextGaussian() * 0.02;
                                double zz = level.getRandom().nextGaussian() * 0.02;
                                double amnt = 10.0;
                                level.addParticle(
                                        ColorExplodeOptions.ENRAGED_SMALL,
                                        area.x - xx * amnt,
                                        area.y - yy * amnt,
                                        area.z - zz * amnt,
                                        0.0,
                                        0.0,
                                        0.0
                                );

                                double xx2 = level.getRandom().nextGaussian() * 0.02;
                                double yy2 = level.getRandom().nextGaussian() * 0.02;
                                double zz2 = level.getRandom().nextGaussian() * 0.02;
                                level.addParticle(
                                        FRParticles.VEX_FLAME.get(),
                                        area.x - xx2 * amnt,
                                        area.y - yy2 * amnt,
                                        area.z - zz2 * amnt,
                                        0.0,
                                        0.0,
                                        0.0
                                );
                            }

                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.1F;
                            level.playLocalSound(pos, FRSounds.CURSE_ALTAR_TABLET.get(), SoundSource.BLOCKS, 2.0F, 1.0F + pitch, false);
                        }
                    }
            );

            // Brewing Stand fill
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.BREWING_STAND_FILL,
                    (level, minecraft, pos, data) ->
                    {
                        if (Frontiers.CONFIG.doBrewChargeFX())
                        {
                            Vec3 area = pos.getCenter().add(0.0, 0.4, 0.0);

                            for (int i = 0; i < 10; i++)
                            {
                                double xx = level.getRandom().nextGaussian() * 0.001;
                                double yy = level.getRandom().nextGaussian() * 0.001;
                                double zz = level.getRandom().nextGaussian() * 0.001;
                                double amnt = 10.0;
                                level.addParticle(
                                        FRParticles.BREWING_BLAZE,
                                        area.x - xx * amnt,
                                        area.y - yy * amnt,
                                        area.z - zz * amnt,
                                        0.0,
                                        0.01,
                                        0.0
                                );
                            }

                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                            level.playLocalSound(pos, FRSounds.BREWING_STAND_FILL.get(), SoundSource.BLOCKS, 2.0F, 1.0F + pitch, false);
                        }
                    }
            );

            // Furnaces light
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.FURNACES_LIGHT,
                    (level, minecraft, pos, data) ->
                    {
                        if (Frontiers.CONFIG.doFurnaceCrackle())
                        {
                            boolean extinguished = (data == 1);
                            RandomSource randomsource = level.random;

                            SoundEvent toPlay = (extinguished) ? FRSounds.FURNACE_EXTINGUISH.get() : FRSounds.FURNACE_LIGHT.get();
                            BlockState state = level.getBlockState(pos);
                            Block block = state.getBlock();
                            if (block instanceof SmokerBlock) toPlay = (extinguished) ? FRSounds.SMOKER_EXTINGUISH.get() : FRSounds.SMOKER_LIGHT.get();
                            else if (block instanceof BlastFurnaceBlock) toPlay = (extinguished) ? FRSounds.BLAST_FURNACE_EXTINGUISH.get() : FRSounds.BLAST_FURNACE_LIGHT.get();

                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                            level.playLocalSound(pos, toPlay, SoundSource.BLOCKS, 0.7F, 1.0F + pitch, false);


                            if (state.hasProperty(AbstractFurnaceBlock.FACING))
                            {
                                Direction facingdir = state.getValue(AbstractFurnaceBlock.FACING);

                                for (int u = 0; u < 13; u++)
                                {
                                    double x1 = ((double)pos.getX() + 0.5 + (facingdir.getStepX() * 0.4)) + (randomsource.nextDouble() - 0.5) * 0.5;
                                    double y1 = ((double)pos.getY() + 0.2 + (facingdir.getStepY() * 0.2)) + (randomsource.nextDouble() - 0.5) * 0.5;
                                    double z1 = ((double)pos.getZ() + 0.5 + (facingdir.getStepZ() * 0.4)) + (randomsource.nextDouble() - 0.5) * 0.5;

                                    level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, (extinguished) ? 0.01 : 0.0, 0.0);
                                    if (!extinguished) level.addParticle(ParticleTypes.FLAME, x1, y1, z1, 0.0, 0.01, 0.0);
                                }
                            }
                            else
                            {
                                for (int u = 0; u < 12; u++)
                                {
                                    double x1 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                                    double y1 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                                    double z1 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;

                                    level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, (extinguished) ? 0.01 : 0.0, 0.0);
                                    if (!extinguished) level.addParticle(ParticleTypes.FLAME, x1, y1, z1, 0.0, 0.01, 0.0);
                                }
                            }
                        }
                    }
            );

            // Crags Teleportation
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.CRAGS_TELEPORT,
                    (level, minecraft, pos, data) ->
                    {
                        RandomSource randomsource = level.random;
                        minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(FRSounds.CRAGS_TRAVEL.get(), randomsource.nextFloat() * 0.4F + 0.8F, 0.25F));
                    }
            );

            // Mana GUI flash
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.MANA_GUI_EFFECT,
                    (level, minecraft, pos, data) ->
                    {
                        RandomSource randomsource = level.random;
                        ((GuiIntf)minecraft.gui).frontiersML$flashMana(data);
                    }
            );

            // Monster Spawner-like flare
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.FLAME_PARTICLE_FLARE,
                    (level, minecraft, pos, data) ->
                    {
                        RandomSource randomsource = level.random;
                        SimpleParticleType flame = switch (data)
                        {
                            case 1 -> ParticleTypes.SOUL_FIRE_FLAME;
                            case 2 -> MethodToolbox.tryForDundelightFire(false);
                            case 3 -> ParticleTypes.HEART;
                            default -> ParticleTypes.FLAME;
                        };

                        for (int u = 0; u < 20; u++)
                        {
                            double x1 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double y1 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                            double z1 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;

                            level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                            if (data != 4) level.addParticle(flame, x1, y1, z1, 0.0, 0.0, 0.0);
                        }

                        if (data == 4)
                        {
                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                            level.playLocalSound(pos, FRSounds.MONSTER_BAKERY_EXTINGUISH.get(), SoundSource.BLOCKS, 0.7F, 1.0F + pitch, false);
                        }
                    }
            );

            // Monster Bakery
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.MONSTER_BAKERY_LIGHT,
                    (level, minecraft, pos, data) ->
                    {
                        if (Frontiers.CONFIG.doMonsterBakeryFX())
                        {
                            boolean extinguished = (data == 0);
                            RandomSource randomsource = level.random;
                            SimpleParticleType flame = MethodToolbox.tryForDundelightFire(false);;

                            for (int u = 0; u < 20; u++)
                            {
                                double x1 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                                double y1 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                                double z1 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;

                                level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                                if (!extinguished) level.addParticle(flame, x1, y1, z1, 0.0, 0.0, 0.0);
                            }

                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                            level.playLocalSound(pos, (extinguished) ? FRSounds.MONSTER_BAKERY_EXTINGUISH.get() : FRSounds.MONSTER_BAKERY_LIGHT.get(), SoundSource.BLOCKS, 0.7F, 1.0F + pitch, false);
                        }
                    }
            );

            // End Crystal Damage
            VectorEventSyncClient.assignLocal(FRLevelEvents.Local.END_CRYSTAL_HARM,
                    // 0: hit 1, 1: pre-die, 2: die, 3: friendly
                    (level, minecraft, pos, data) ->
                    {
                        RandomSource randomsource = level.random;
                        ItemParticleOption shard = new ItemParticleOption(ParticleTypes.ITEM, FRItems.END_CRYSTAL_SHARD.get().getDefaultInstance());

                        level.addDestroyBlockEffect(pos.above(), Blocks.GLASS.defaultBlockState());

                        if (data < 3)
                        {
                            for (int i = 0; i < 30; i++)
                            {
                                // Shard
                                double xx = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 1.2;
                                double yy = (double)pos.getY() + 1.5 + (randomsource.nextDouble() - 0.5) * 1.2;
                                double zz = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 1.2;

                                double sx = (randomsource.nextDouble() - 0.5) * 0.5;
                                double sy = (randomsource.nextDouble() - 0.5) * 0.5;
                                double sz = (randomsource.nextDouble() - 0.5) * 0.5;

                                level.addParticle(shard, xx, yy, zz, sx, sy, sz);

                                // Smokes
                                if (i < 20)
                                {
                                    sx = (randomsource.nextDouble() - 0.5);
                                    sy = (randomsource.nextDouble() - 0.5);
                                    sz = (randomsource.nextDouble() - 0.5);
                                    xx = (double)pos.getX() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    yy = (double)pos.getY() + 1.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    zz = (double)pos.getZ() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    level.addParticle(ParticleTypes.WHITE_SMOKE, xx, yy, zz, sx, sy, sz);

                                    sx = (randomsource.nextDouble() - 0.5);
                                    sy = (randomsource.nextDouble() - 0.5);
                                    sz = (randomsource.nextDouble() - 0.5);
                                    xx = (double)pos.getX() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    yy = (double)pos.getY() + 1.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    zz = (double)pos.getZ() + 0.5 + ((randomsource.nextDouble() - 0.5) * 0.4);
                                    level.addParticle(ParticleTypes.LARGE_SMOKE, xx, yy, zz, sx, sy, sz);
                                }

                                // Explosion
                                int limiter = (data < 2) ? (data + 1) * 2 : 0;
                                if (i < limiter)
                                {
                                    xx = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 1.2;
                                    yy = (double)pos.getY() + 1.5 + (randomsource.nextDouble() - 0.5) * 1.2;
                                    zz = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 1.2;
                                    sx = (randomsource.nextDouble() - 0.5) * 0.1;
                                    sy = (randomsource.nextDouble() - 0.5) * 0.1;
                                    sz = (randomsource.nextDouble() - 0.5) * 0.1;

                                    level.addParticle(ParticleTypes.EXPLOSION, xx, yy, zz, sx, sy, sz);
                                }
                            }

                            if (data == 2)
                            {
                                Particle particle = minecraft.particleEngine.createParticle(ParticleTypes.FLASH, pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, 0.0, 0.0, 0.0);
                                Vector3f fxf = Vec3.fromRGB24(0xFF4EC7).toVector3f();
                                particle.setColor(fxf.x, fxf.y, fxf.z);
                                particle.scale(4.0F);

                                double pi2 = (Math.PI * 2);
                                for (double d9 = 0.0; d9 < pi2; d9 += pi2 / 40.0F)
                                {
                                    level.addParticle(
                                            ColorExplodeOptions.CRYSTALSHARD_BIG,
                                            (pos.getX() + 0.5),
                                            (pos.getY() + 1.5),
                                            (pos.getZ() + 0.5),
                                            Math.cos(d9) * -1.0,
                                            0.0,
                                            Math.sin(d9) * -1.0);
                                    level.addParticle(
                                            ColorExplodeOptions.CRYSTALSHARD_SMALL,
                                            (pos.getX() + 0.5),
                                            (pos.getY() + 1.5),
                                            (pos.getZ() + 0.5),
                                            Math.cos(d9) * -3.0,
                                            0.0,
                                            Math.sin(d9) * -3.0);
                                }
                            }
                        }
                    }
            );
        }
    }

    private static class Dual
    {
        private static void bootstrap()
        {
            // Spirit Candle Deter
            VectorEventSyncClient.assignDual(FRLevelEvents.Dual.SPIRIT_CANDLE_DETER,
                    (level, minecraft, pos1, pos2, data) ->
                    {
                        RandomSource random = level.random;

                        for (int i = 0; i < 20; i++)
                        {
                            double x1 = pos1.x() + ((random.nextDouble() - 0.5) * 0.6);
                            double y1 = pos1.y() + ((random.nextDouble() - 0.5) * 0.6);
                            double z1 = pos1.z() + ((random.nextDouble() - 0.5) * 0.6);
                            level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                            level.addParticle(FRParticles.VEX_FLAME.get(), x1, y1, z1, 0.0, 0.0, 0.0);

                            double x2 = pos2.x() + (random.nextDouble() - 0.5) * 2.0;
                            double y2 = pos2.y() + (random.nextDouble() - 0.5) * 2.0;
                            double z2 = pos2.z() + (random.nextDouble() - 0.5) * 2.0;
                            level.addParticle(ParticleTypes.SMOKE, x2, y2, z2, 0.0, 0.0, 0.0);
                            level.addParticle(FRParticles.VEX_FLAME_BIG.get(), x2, y2, z2, 0.0, 0.0, 0.0);
                        }
                    }
            );

            // Tower Spawning flame trail
            VectorEventSyncClient.assignDual(FRLevelEvents.Dual.TOWER_SPAWNER_FLAMETRAIL,
                    (level, minecraft, pos1, pos2, data) ->
                    {
                        boolean enraged = (data == 1);
                        RandomSource random = level.random;

                        int totalTime = 20;

                        double diffX = (pos2.x - pos1.x) / (double)totalTime;
                        double diffY = (pos2.y - pos1.y) / (double)totalTime;
                        double diffZ = (pos2.z - pos1.z) / (double)totalTime;

                        for (int i = 0; i < totalTime; i++)
                        {
                            double x1 = (pos1.x() + (diffX * i)) + ((random.nextDouble() - 0.5) * 0.25);
                            double y1 = (pos1.y() + (diffY * i));
                            double z1 = (pos1.z() + (diffZ * i)) + ((random.nextDouble() - 0.5) * 0.25);

                            level.addParticle(
                                    enraged ? FRParticles.VEX_FLAME.get() : FRParticles.TOWER_FLAME_SMALL.get(),
                                    x1,
                                    y1,
                                    z1,
                                    0.0,
                                    0.0,
                                    0.0
                            );
                        }
                    }
            );

            // Crags Stalker despawn
            VectorEventSyncClient.assignDual(FRLevelEvents.Dual.CRAGS_STALKER_DESPAWN,
                    (level, minecraft, pos1, pos2, data) ->
                    {
                        RandomSource random = level.getRandom();

                        for (int i = 0; i < 20; i++)
                        {
                            level.addParticle(CragsStalkerEntity.SMOG,
                                    pos1.x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                    pos1.y() + (0.1 * (1 + (random.nextInt(17)))),
                                    pos1.z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                    (0.1 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.1 * random.nextIntBetweenInclusive(-2, 2)));

                            level.addParticle(FRParticles.CRAG_SMOG.get(),
                                    pos1.x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                    pos1.y() + 1.0,
                                    pos1.z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                    (0.1 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.1 * random.nextIntBetweenInclusive(-2, 2)));
                        }
                    }
            );

            // Void or Eye of Ender smashed
            VectorEventSyncClient.assignDual(FRLevelEvents.Dual.VOID_OR_ENDER_EYE_SMASH,
                    (level, minecraft, pos1, pos2, data) ->
                    {
                        double xx = pos1.x();
                        double yy = pos1.y();
                        double zz = pos1.z();

                        ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, (data == 0) ? FRItems.VOID_PEARL.get().getDefaultInstance() : Items.ENDER_EYE.getDefaultInstance());
                        for(int i = 0; i < 8; i++)
                        {
                            level.addParticle(
                                    part,
                                    xx,
                                    yy,
                                    zz,
                                    level.random.nextGaussian() * 0.15,
                                    level.random.nextDouble() * 0.2,
                                    level.random.nextGaussian() * 0.15
                            );
                        }

                        for (double d9 = 0.0; d9 < 6.283185307179586; d9 += 0.15707963267948966)
                        {
                            level.addParticle(ParticleTypes.PORTAL, xx + Math.cos(d9) * 5.0, yy - 0.4, zz + Math.sin(d9) * 5.0, Math.cos(d9) * -5.0, 0.0, Math.sin(d9) * -5.0);
                            level.addParticle(ParticleTypes.PORTAL, xx + Math.cos(d9) * 5.0, yy - 0.4, zz + Math.sin(d9) * 5.0, Math.cos(d9) * -7.0, 0.0, Math.sin(d9) * -7.0);
                        }
                    }
            );

            // End Crystal Shard
            VectorEventSyncClient.assignDual(FRLevelEvents.Dual.END_CRYSTAL_SHARD,
                    (level, minecraft, pos1, pos2, data) ->
                    {
                        ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, FRItems.END_CRYSTAL_SHARD.get().getDefaultInstance());
                        RandomSource random = level.random;

                        Particle particle = minecraft.particleEngine.createParticle(ParticleTypes.FLASH, pos1.x(), pos1.y(), pos1.z(), 0.0, 0.0, 0.0);
                        Vector3f fxf = Vec3.fromRGB24(0xFF4EC7).toVector3f();
                        particle.setColor(fxf.x, fxf.y, fxf.z);
                        particle.scale(4.0F);

                        for (int i = 0; i < 12; i++)
                        {
                            level.addParticle(
                                    part,
                                    pos1.x(),
                                    pos1.y(),
                                    pos1.z(),
                                    ((double)random.nextFloat() - 0.5) * -0.8,
                                    ((double)random.nextFloat() - 0.5) * -0.8,
                                    ((double)random.nextFloat() - 0.5) * -0.8
                            );
                        }

                        double pi2 = (Math.PI * 2);
                        for (double d9 = 0.0; d9 < pi2; d9 += pi2 / 40.0F)
                        {
                            level.addParticle(
                                    ColorExplodeOptions.CRYSTALSHARD_BIG,
                                    pos1.x() + Math.cos(d9) * 5.0,
                                    pos1.y() - 0.4,
                                    pos1.z() + Math.sin(d9) * 5.0,
                                    Math.cos(d9) * -1.0,
                                    0.0,
                                    Math.sin(d9) * -1.0);
                            level.addParticle(
                                    ColorExplodeOptions.CRYSTALSHARD_SMALL,
                                    pos1.x() + Math.cos(d9) * 5.0,
                                    pos1.y() - 0.4,
                                    pos1.z() + Math.sin(d9) * 5.0,
                                    Math.cos(d9) * -3.0,
                                    0.0,
                                    Math.sin(d9) * -3.0);
                            level.addParticle(
                                    ParticleTypes.POOF,
                                    pos1.x() + Math.cos(d9) * 5.0,
                                    pos1.y() - 0.4,
                                    pos1.z() + Math.sin(d9) * 5.0,
                                    Math.cos(d9) * -1.0,
                                    0.0,
                                    Math.sin(d9) * -1.0);
                        }

                        level.addParticle(
                                ParticleTypes.EXPLOSION_EMITTER,
                                pos2.x(),
                                pos2.y(),
                                pos2.z(),
                                ((double)random.nextFloat() - 0.5) * 0.8,
                                ((double)random.nextFloat() - 0.5) * 0.8,
                                ((double)random.nextFloat() - 0.5) * 0.8
                        );
                    }
            );
        }
    }

    private static class Entity
    {
        private static void bootstrap()
        {
            // Tower entity poof
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.TOWER_ENTITY_POOF,
                    (level, minecraft, entity, data) ->
                    {
                        if (entity instanceof Mob mob)
                        {
                            boolean enraged = (data == 1);
                            for (int i = 0; i < 20; i++)
                            {
                                double xx = mob.getRandom().nextGaussian() * 0.02;
                                double yy = mob.getRandom().nextGaussian() * 0.02;
                                double zz = mob.getRandom().nextGaussian() * 0.02;
                                double amnt = 10.0;

                                mob.level().addParticle(
                                        enraged ? ColorExplodeOptions.ENRAGED_BIG : ColorExplodeOptions.REG_BIG,
                                        mob.getX(1.0) - xx * amnt,
                                        mob.getRandomY() - yy * amnt,
                                        mob.getRandomZ(1.0) - zz * amnt,
                                        xx,
                                        yy,
                                        zz
                                );
                            }
                        }
                    }
            );

            // Witch Hat sparkle
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.WITCH_HAT_SPARKLE,
                    (level, minecraft, entity, data) ->
                    {
                        for (int i = 0; i < entity.getRandom().nextInt(35) + 10; i++)
                        {
                            entity.level().addParticle(ParticleTypes.WITCH,
                                    entity.getX() + entity.getRandom().nextGaussian() * 0.12999999523162842,
                                    entity.getBoundingBox().maxY + 0.5 + entity.getRandom().nextGaussian() * 0.12999999523162842,
                                    entity.getZ() + entity.getRandom().nextGaussian() * 0.12999999523162842,
                                    0.0,
                                    0.0,
                                    0.0
                            );
                        }
                    }
            );

            // Hoglin taming
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.HOGLIN_TAME,
                    (level, minecraft, entity, data) ->
                    {
                        RandomSource random = entity.getRandom();
                        Vec3 facing = entity.getLookAngle().add(0.2, 0.0, 0.2);
                        ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, FRItems.TRUFFLE.get().getDefaultInstance());

                        for (int i = 0; i < 10; i++)
                        {
                            entity.level().addParticle(ParticleTypes.POOF,
                                    entity.getRandomX(0.8) + random.nextGaussian() * 0.2,
                                    entity.getY(0.5) + random.nextGaussian() * 0.5,
                                    entity.getRandomZ(0.8) + random.nextGaussian() * 0.2,
                                    0.0,
                                    0.0,
                                    0.0
                            );

                            entity.level().addParticle(ParticleTypes.HEART,
                                    entity.getRandomX(0.8) + random.nextGaussian() * 0.05,
                                    entity.getY(0.8 + ((double)random.nextInt(1) - 0.5) * 0.2) + random.nextGaussian() * 0.05,
                                    entity.getRandomZ(0.8) + random.nextGaussian() * 0.05,
                                    0.0,
                                    0.0,
                                    0.0
                            );

                            entity.level().addParticle(part,
                                    entity.getX() + facing.x + (random.nextGaussian() * 0.05),
                                    entity.getY(0.5) + facing.y + (random.nextGaussian() * 0.05),
                                    entity.getZ() + facing.z + (random.nextGaussian() * 0.05),
                                    (random.nextGaussian() * 0.08),
                                    (random.nextGaussian() * 0.08),
                                    (random.nextGaussian() * 0.08)
                            );
                        }
                    }
            );

            // Pumpkin Golem toggle
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.TOGGLE_PUMPKIN_GOLEM,
                    (level, minecraft, entity, data) ->
                    {
                        boolean isAwakening = (data == 0);
                        float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                        level.playLocalSound(
                                entity.position().x,
                                entity.position().y,
                                entity.position().z,
                                (isAwakening) ? FRSounds.PUMPKIN_GOLEM_ENABLE.get() : FRSounds.PUMPKIN_GOLEM_DISABLE.get(),
                                SoundSource.NEUTRAL,
                                1.5F,
                                1.0F + pitch,
                                false
                        );

                        for (int i = 0; i < 20; i++)
                        {
                            double xx = entity.getRandom().nextGaussian() * 0.02;
                            double yy = entity.getRandom().nextGaussian() * 0.02;
                            double zz = entity.getRandom().nextGaussian() * 0.02;
                            double amnt = 8.0;

                            level.addParticle(
                                    (isAwakening) ? ParticleTypes.POOF : ParticleTypes.LARGE_SMOKE,
                                    (entity.getRandomX(1.0)) - xx * amnt,
                                    entity.getRandomY() - yy * amnt,
                                    (entity.getRandomZ(1.0)) - zz * amnt,
                                    xx,
                                    yy,
                                    zz
                            );

                            if (isAwakening)
                            {
                                double xx2 = entity.getRandom().nextGaussian() * 0.02;
                                double yy2 = entity.getRandom().nextGaussian() * 0.02;
                                double zz2 = entity.getRandom().nextGaussian() * 0.02;
                                double amnt2 = 12.0;

                                level.addParticle(
                                        FRParticles.VEX_FLAME.get(),
                                        (entity.getRandomX(1.0)) - xx2 * amnt2,
                                        entity.getRandomY() - yy2 * amnt2,
                                        (entity.getRandomZ(1.0)) - zz2 * amnt2,
                                        xx2,
                                        yy2,
                                        zz2
                                );
                            }
                        }
                    }
            );

            // That April Event I hate
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.NOTHING_SPECIAL_NO_CAP,
                    (level, minecraft, entity, data) -> {
                        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
                        {
                            float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.4F;
                            level.playLocalSound(entity, FRSounds.STEVE.value(), SoundSource.PLAYERS, 1.0F, 1.0F + pitch);
                        }
                    }
            );

            // LMAOOOOOOOO
            VectorEventSyncClient.assignEntity(FRLevelEvents.Entity.REALLY_ANNOYING_SOUND,
                    (level, minecraft, entity, data) -> {
                        if (Frontiers.EVENTS.IS_APRIL_FOOLS)
                        {
                            level.playSeededSound(
                                    minecraft.player,
                                    entity.getX(),
                                    entity.getY(),
                                    entity.getZ(),
                                    FRSounds.APRIL_FOOLS_DEATH_SFX,
                                    SoundSource.PLAYERS,
                                    1.0F,
                                    1.0F,
                                    data
                            );
                        }
                    }
            );
        }
    }

    private static class Global
    {
        private static void bootstrap()
        {

        }
    }

    public static void bootstrap()
    {
        Local.bootstrap();
        Dual.bootstrap();
        Entity.bootstrap();
        Global.bootstrap();
    }
}
