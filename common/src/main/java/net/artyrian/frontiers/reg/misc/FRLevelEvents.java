package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.TowerSpawnerBlock;
import net.artyrian.frontiers.reg.content.ModBlocks;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.block.state.BlockState;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;

public class FRLevelEvents
{
    public static class Local
    {
        // Ore withering away effect
        public static final ResourceLocation ORE_WITHER = VectorEventSync.Local.register(
                Frontiers.id("ore_wither_away"),
                (level, pos, data) ->
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
                                ModParticle.BLACK_PARTICLE,
                                pos.getX() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                pos.getY() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                pos.getZ() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                ((double)level.random.nextFloat() - 0.5) * 0.4,
                                ((double)level.random.nextFloat() - 0.5) * 0.4,
                                ((double)level.random.nextFloat() - 0.5) * 0.4
                        );
                        level.addParticle(
                                ModParticle.WITHER_PARTICLE,
                                pos.getX() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                pos.getY() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                pos.getZ() + 0.5 + ((double)level.random.nextFloat() - 0.5),
                                ((double)level.random.nextFloat() - 0.5) * 0.4,
                                ((double)level.random.nextFloat() - 0.5) * 0.4,
                                ((double)level.random.nextFloat() - 0.5) * 0.4
                        );
                    }

                    level.addParticle(
                            ModParticle.WITHER_FACE.get(),
                            pos.getX() + 0.5,
                            pos.getY() + 0.5,
                            pos.getZ() + 0.5,
                            0.0,
                            0.02,
                            0.0
                    );

                    level.playLocalSound(pos, ModSounds.ORE_WITHER.get(), SoundSource.BLOCKS, 0.8F,
                            1.0F / (level.getRandom().nextFloat() * 0.4F + 0.8F),
                            false);
                }
        );

        public static final ResourceLocation TOWER_SPAWNER_SPAWN = VectorEventSync.Local.register(
                Frontiers.id("tower_spawner_spawn"),
                (level, pos, data) ->
                {
                    boolean enraged = false;
                    BlockState stateAt = level.getBlockState(pos);
                    RandomSource randomsource = level.random;

                    if (stateAt.is(ModBlocks.TOWER_SPAWNER.get()))
                    {
                        enraged = stateAt.getOptionalValue(TowerSpawnerBlock.ENRAGED).orElse(false);
                    }

                    for (int u = 0; u < 20; u++)
                    {
                        double x1 = (double)pos.getX() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                        double y1 = (double)pos.getY() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;
                        double z1 = (double)pos.getZ() + 0.5 + (randomsource.nextDouble() - 0.5) * 2.0;

                        level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                        level.addParticle((enraged) ? ModParticle.VEX_FLAME_BIG.get() : ModParticle.TOWER_FLAME.get(), x1, y1, z1, 0.0, 0.0, 0.0);
                    }
                }
        );

        private static void register()
        {

        }
    }

    public static class Dual
    {
        public static final ResourceLocation SPIRIT_CANDLE_DETER = VectorEventSync.Dual.register(
                Frontiers.id("spirit_candle_deter"),
                (level, pos1, pos2, data) ->
                {
                    RandomSource random = level.random;

                    for (int i = 0; i < 20; i++)
                    {
                        double x1 = pos1.x() + (random.nextDouble() - 0.5) * 2.0;
                        double y1 = pos1.y() + (random.nextDouble() - 0.5) * 2.0;
                        double z1 = pos1.z() + (random.nextDouble() - 0.5) * 2.0;
                        level.addParticle(ParticleTypes.SMOKE, x1, y1, z1, 0.0, 0.0, 0.0);
                        level.addParticle(ModParticle.VEX_FLAME.get(), x1, y1, z1, 0.0, 0.0, 0.0);

                        double x2 = pos2.x() + (random.nextDouble() - 0.5) * 2.0;
                        double y2 = pos2.y() + (random.nextDouble() - 0.5) * 2.0;
                        double z2 = pos2.z() + (random.nextDouble() - 0.5) * 2.0;
                        level.addParticle(ParticleTypes.SMOKE, x2, y2, z2, 0.0, 0.0, 0.0);
                        level.addParticle(ModParticle.VEX_FLAME_BIG.get(), x2, y2, z2, 0.0, 0.0, 0.0);
                    }
                }
        );

        public static final ResourceLocation TOWER_SPAWNER_FLAMETRAIL = VectorEventSync.Dual.register(
                Frontiers.id("tower_spawner_flametrail"),
                (level, pos1, pos2, data) ->
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
                                enraged ? ModParticle.VEX_FLAME.get() : ModParticle.TOWER_FLAME_SMALL.get(),
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

        private static void register()
        {

        }
    }

    public static class Entity
    {
        public static final ResourceLocation TOWER_ENTITY_POOF = VectorEventSync.Entity.register(
                Frontiers.id("tower_entity_poof"),
                (level, entity, data) ->
                {
                    if (entity instanceof Mob mob)
                    {
                        for(int i = 0; i < 20; i++)
                        {
                            double xx = mob.getRandom().nextGaussian() * 0.02;
                            double yy = mob.getRandom().nextGaussian() * 0.02;
                            double zz = mob.getRandom().nextGaussian() * 0.02;
                            double amnt = 10.0;

                            mob.level().addParticle(ParticleTypes.POOF, mob.getX(1.0) - xx * amnt, mob.getRandomY() - yy * amnt, mob.getRandomZ(1.0) - zz * amnt, xx, yy, zz);
                        }
                    }
                }
        );

        private static void register()
        {

        }
    }

    public static class Global
    {
        private static void register()
        {

        }
    }

    public static void register()
    {
        Local.register();
        Dual.register();
        Entity.register();
        Global.register();
    }
}
