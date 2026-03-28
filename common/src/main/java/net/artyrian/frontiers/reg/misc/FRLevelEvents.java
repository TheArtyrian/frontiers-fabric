package net.artyrian.frontiers.reg.misc;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.entity.types.misc.CragsStalkerEntity;
import net.artyrian.frontiers.definition.item.custom.EndCrystalShardItem;
import net.artyrian.frontiers.definition.item.custom.OnyxMealItem;
import net.artyrian.frontiers.definition.item.custom.SnowMeltItem;
import net.artyrian.frontiers.definition.particle.options.ColorExplodeOptions;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.mixin_intf.GuiIntf;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.sound.ModSounds;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FastColor;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.joml.Vector3f;

public class FRLevelEvents
{
    public static class Local
    {
        public static final VectorEventSync.EventData ORE_WITHER = VectorEventSync.Local.register(
                Frontiers.id("ore_wither_away"),
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

        public static final VectorEventSync.EventData TOWER_SPAWNER_SPAWN = VectorEventSync.Local.register(
                Frontiers.id("tower_spawner_spawn"),
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
                        level.addParticle((enraged) ? ModParticle.VEX_FLAME_BIG.get() : ModParticle.TOWER_FLAME.get(), x1, y1, z1, 0.0, 0.0, 0.0);
                    }
                }
        );

        public static final VectorEventSync.EventData TOWER_SPAWNER_TINY_POOF = VectorEventSync.Local.register(
                Frontiers.id("tower_spawner_homunculus_poof"),
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

        public static final VectorEventSync.EventData TOWER_SPAWNER_ENRAGE = VectorEventSync.Local.register(
                Frontiers.id("tower_spawner_enrage"),
                (level, minecraft, pos, data) ->
                {
                    level.playLocalSound(pos, ModSounds.TOWER_SPAWNER_ENRAGE.get(), SoundSource.BLOCKS, 2.0F, 1.0F, false);

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
                        level.addParticle(ModParticle.VEX_FLAME_BIG.get(), x2, y2, z2, 0.0, 0.0, 0.0);
                    }
                }
        );

        public static final VectorEventSync.EventData ONYX_MEAL = VectorEventSync.Local.register(
                Frontiers.id("onyx_meal_grow"),
                (level, minecraft, pos, data) ->
                {
                    OnyxMealItem.createBadParticles(level, pos, data);
                    level.playLocalSound(pos, ModSounds.ONYX_MEAL_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
                }
        );

        public static final VectorEventSync.EventData SNOW_MELT = VectorEventSync.Local.register(
                Frontiers.id("snow_melt_use"),
                (level, minecraft, pos, data) ->
                {
                    SnowMeltItem.createParticles(level, pos, data);
                    level.playLocalSound(pos, ModSounds.SNOW_MELT_USE.get(), SoundSource.BLOCKS, 1.0F, 1.0F, false);
                }
        );

        public static final VectorEventSync.EventData SNOW_MELT_GLISTEN = VectorEventSync.Local.register(
                Frontiers.id("snow_melt_glisten"),
                (level, minecraft, pos, data) ->
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

                        level.addParticle(ModParticle.SNOW_GLINT.get(), x1, y1, z1, px, py, pz);
                    }
                }
        );

        public static final VectorEventSync.EventData CURSED_TABLET = VectorEventSync.Local.register(
                Frontiers.id("cursed_tablet"),
                (level, minecraft, pos, data) ->
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
                                ModParticle.VEX_FLAME.get(),
                                area.x - xx2 * amnt,
                                area.y - yy2 * amnt,
                                area.z - zz2 * amnt,
                                0.0,
                                0.0,
                                0.0
                        );
                    }

                    float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.1F;
                    level.playLocalSound(pos, ModSounds.CURSE_ALTAR_TABLET.get(), SoundSource.BLOCKS, 2.0F, 1.0F + pitch, false);
                }
        );

        public static final VectorEventSync.EventData BREWING_STAND_FILL = VectorEventSync.Local.register(
                Frontiers.id("brewing_stand_fill"),
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
                                    ModParticle.BREWING_BLAZE,
                                    area.x - xx * amnt,
                                    area.y - yy * amnt,
                                    area.z - zz * amnt,
                                    0.0,
                                    0.01,
                                    0.0
                            );
                        }

                        float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                        level.playLocalSound(pos, ModSounds.BREWING_STAND_FILL.get(), SoundSource.BLOCKS, 2.0F, 1.0F + pitch, false);
                    }
                }
        );

        public static final VectorEventSync.EventData CRAGS_TELEPORT = VectorEventSync.Local.register(
                Frontiers.id("crags_teleport"),
                (level, minecraft, pos, data) ->
                {
                    RandomSource randomsource = level.random;
                    minecraft.getSoundManager().play(SimpleSoundInstance.forLocalAmbience(ModSounds.CRAGS_TRAVEL.get(), randomsource.nextFloat() * 0.4F + 0.8F, 0.25F));
                }
        );

        public static final VectorEventSync.EventData MANA_GUI_EFFECT = VectorEventSync.Local.register(
                Frontiers.id("mana_gui_effect"),
                (level, minecraft, pos, data) ->
                {
                    RandomSource randomsource = level.random;
                    ((GuiIntf)minecraft.gui).frontiersML$flashMana(data);
                }
        );

        public static final VectorEventSync.EventData ITEM_VACUUM_FLARE = VectorEventSync.Local.register(
                Frontiers.id("item_vacuum_flare"),
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
                        level.addParticle(flame, x1, y1, z1, 0.0, 0.0, 0.0);
                    }
                }
        );

        private static void register()
        {

        }
    }

    public static class Dual
    {
        public static final VectorEventSync.EventData SPIRIT_CANDLE_DETER = VectorEventSync.Dual.register(
                Frontiers.id("spirit_candle_deter"),
                (level, minecraft, pos1, pos2, data) ->
                {
                    RandomSource random = level.random;

                    for (int i = 0; i < 20; i++)
                    {
                        double x1 = pos1.x() + ((random.nextDouble() - 0.5) * 0.6);
                        double y1 = pos1.y() + ((random.nextDouble() - 0.5) * 0.6);
                        double z1 = pos1.z() + ((random.nextDouble() - 0.5) * 0.6);
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

        public static final VectorEventSync.EventData TOWER_SPAWNER_FLAMETRAIL = VectorEventSync.Dual.register(
                Frontiers.id("tower_spawner_flametrail"),
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

        public static final VectorEventSync.EventData CRAGS_STALKER_DESPAWN = VectorEventSync.Dual.register(
                Frontiers.id("crags_stalker_despawn"),
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

                        level.addParticle(ModParticle.CRAG_SMOG.get(),
                                pos1.x() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                pos1.y() + 1.0,
                                pos1.z() + (0.5 * (2.0 * random.nextDouble() - 1.0) * 0.5),
                                (0.1 * random.nextIntBetweenInclusive(-2, 2)), (0.1 * random.nextIntBetweenInclusive(1, 3)), (0.1 * random.nextIntBetweenInclusive(-2, 2)));
                    }
                }
        );

        public static final VectorEventSync.EventData VOID_OR_ENDER_EYE_SMASH = VectorEventSync.Dual.register(
                Frontiers.id("void_or_ender_eye_smash"),
                (level, minecraft, pos1, pos2, data) ->
                {
                    double xx = pos1.x();
                    double yy = pos1.y();
                    double zz = pos1.z();

                    ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, (data == 0) ? ModItem.VOID_PEARL.get().getDefaultInstance() : Items.ENDER_EYE.getDefaultInstance());
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

        public static final VectorEventSync.EventData END_CRYSTAL_SHARD = VectorEventSync.Dual.register(
                Frontiers.id("end_crystal_shard"),
                (level, minecraft, pos1, pos2, data) ->
                {
                    ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, ModItem.END_CRYSTAL_SHARD.get().getDefaultInstance());
                    RandomSource random = level.random;

                    Particle particle = minecraft.particleEngine.createParticle(ParticleTypes.FLASH, pos1.x(), pos1.y(), pos1.z(), 0.0, 0.0, 0.0);
                    Vector3f fxf = Vec3.fromRGB24(0xFF4EC7).toVector3f();
                    particle.setColor(fxf.x, fxf.y, fxf.z);
                    particle.scale(4.0F);

                    level.addParticle(
                            ParticleTypes.FLASH,
                            pos1.x(),
                            pos1.y(),
                            pos1.z(),
                            ((double)random.nextFloat() - 0.5) * -0.8,
                            ((double)random.nextFloat() - 0.5) * -0.8,
                            ((double)random.nextFloat() - 0.5) * -0.8
                    );

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

        private static void register()
        {

        }
    }

    public static class Entity
    {
        public static final VectorEventSync.EventData TOWER_ENTITY_POOF = VectorEventSync.Entity.register(
                Frontiers.id("tower_entity_poof"),
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

        public static final VectorEventSync.EventData WITCH_HAT_SPARKLE = VectorEventSync.Entity.register(
                Frontiers.id("witch_hat_sparkle"),
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

        public static final VectorEventSync.EventData HOGLIN_TAME = VectorEventSync.Entity.register(
                Frontiers.id("hoglin_tame"),
                (level, minecraft, entity, data) ->
                {
                    RandomSource random = entity.getRandom();
                    Vec3 facing = entity.getLookAngle().add(0.2, 0.0, 0.2);
                    ItemParticleOption part = new ItemParticleOption(ParticleTypes.ITEM, ModItem.TRUFFLE.get().getDefaultInstance());

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

        public static final VectorEventSync.EventData TOGGLE_PUMPKIN_GOLEM = VectorEventSync.Entity.register(
                Frontiers.id("toggle_pumpkin_golem"),
                (level, minecraft, entity, data) ->
                {
                    boolean isAwakening = (data == 0);
                    float pitch = (level.getRandom().nextFloat() - 0.5F) * 0.2F;
                    level.playLocalSound(
                            entity.position().x,
                            entity.position().y,
                            entity.position().z,
                            (isAwakening) ? ModSounds.PUMPKIN_GOLEM_ENABLE.get() : ModSounds.PUMPKIN_GOLEM_DISABLE.get(),
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
                                    ModParticle.VEX_FLAME.get(),
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
