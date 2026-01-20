package net.artyrian.frontiers.definition.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.definition.data.savedata.StateSaveLoad;
import net.artyrian.frontiers.reg.content.ModItem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.state.BlockState;

public class FrostiteOreBlock extends HalfTransparentBlock
{
    public static final MapCodec<FrostiteOreBlock> CODEC = simpleCodec(FrostiteOreBlock::new);

    public MapCodec<? extends FrostiteOreBlock> codec() {
        return CODEC;
    }

    public FrostiteOreBlock(Properties settings)
    {
        super(settings);
    }

    public static BlockState getMeltedState() {
        return Blocks.WATER.defaultBlockState();
    }

    protected void randomTick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
    {
        Holder<Biome> biome = world.getBiome(pos);
        boolean is_icespikes = biome.unwrapKey().get().equals(Biomes.ICE_SPIKES);
        StateSaveLoad loader = StateSaveLoad.getServerState(world.getServer());
        boolean drop_item = loader.isInHardmode;

        if ((world.getBrightness(LightLayer.BLOCK, pos) > 11) && !is_icespikes)
        {
            this.melt(state, world, pos);

            if (drop_item)
            {
                ItemEntity ore = new ItemEntity(world,
                        pos.getX() + .5d,
                        pos.getY() + .1d,
                        pos.getZ() + .5d,
                        new ItemStack(ModItem.RAW_FROSTITE.get(), 1)
                );
                ore.setDeltaMovement(
                        .05d * (world.getRandom().nextDouble() * .02d),
                        .05d,
                        .05d * (world.getRandom().nextDouble() * 0.02D));
                ore.setDefaultPickUpDelay();
                world.addFreshEntity(ore);
                IntProvider exp = UniformInt.of(0, 3);
                this.popExperience(world, pos, exp.sample(world.getRandom()));
            }
        }

    }

    protected void melt(BlockState state, Level world, BlockPos pos)
    {
        if (world.dimensionType().ultraWarm()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockAndUpdate(pos, getMeltedState());
            world.neighborChanged(pos, getMeltedState().getBlock(), pos);
        }
    }

    @Override
    protected boolean propagatesSkylightDown(BlockState state, BlockGetter world, BlockPos pos)
    {
        return true;
    }
}
