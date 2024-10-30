package net.artyrian.frontiers.block.custom;

import com.mojang.serialization.MapCodec;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.item.ModItem;
import net.minecraft.block.*;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;

public class FrostiteOreBlock extends TranslucentBlock
{
    public static final MapCodec<FrostiteOreBlock> CODEC = createCodec(FrostiteOreBlock::new);

    public MapCodec<? extends FrostiteOreBlock> getCodec() {
        return CODEC;
    }

    public FrostiteOreBlock(AbstractBlock.Settings settings)
    {
        super(settings);
    }

    public static BlockState getMeltedState() {
        return Blocks.WATER.getDefaultState();
    }

    protected void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        RegistryEntry<Biome> biome = world.getBiome(pos);
        boolean is_icespikes = biome.getKey().get().equals(BiomeKeys.ICE_SPIKES);

        if ((world.getLightLevel(LightType.BLOCK, pos) > 11 - state.getOpacity(world, pos)) && !is_icespikes) {
            this.melt(state, world, pos);
            ItemEntity ore = new ItemEntity(world,
                    pos.getX() + .5d,
                    pos.getY() + .1d,
                    pos.getZ() + .5d,
                    new ItemStack(ModItem.RAW_FROSTITE, 1)
            );
            ore.setVelocity(
                    .05d * (world.getRandom().nextDouble() * .02d),
                    .05d,
                    .05d * (world.getRandom().nextDouble() * 0.02D));
            ore.setToDefaultPickupDelay();
            world.spawnEntity(ore);
            IntProvider exp = UniformIntProvider.create(0, 3);
            this.dropExperience(world, pos, exp.get(world.getRandom()));
        }

    }

    protected void melt(BlockState state, World world, BlockPos pos) {
        if (world.getDimension().ultrawarm()) {
            world.removeBlock(pos, false);
        } else {
            world.setBlockState(pos, getMeltedState());
            world.updateNeighbor(pos, getMeltedState().getBlock(), pos);
        }
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return true;
    }
}
