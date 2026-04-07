package net.artyrian.frontiers.mixin.block.nether_wart;

import net.artyrian.frontiers.definition.block.intf.OnyxMealableBlock;
import net.artyrian.frontiers.reg.content.FRParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.NetherWartBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(NetherWartBlock.class)
public class NetherWartMixin implements OnyxMealableBlock
{
    @Shadow @Final public static IntegerProperty AGE;
    @Shadow @Final public static int MAX_AGE;

    @Override
    public void createOnyxMealFRParticles(LevelAccessor level, BlockPos pos, int amount)
    {
        ParticleUtils.spawnParticleInBlock(level, pos, amount, FRParticles.WITHER_GLINT.get());
    }

    @Override
    public boolean isValidOnyxMealFRTarget(LevelReader reader, BlockPos pos, BlockState state)
    {
        return state.getValue(AGE) < MAX_AGE;
    }

    @Override
    public void performOnyxMealFRAction(ServerLevel level, RandomSource random, BlockPos pos, BlockState state)
    {
        if (!level.isClientSide)
        {
            int i = state.getValue(NetherWartBlock.AGE);
            if (level.random.nextFloat() > 0.5F)
            {
                state = state.setValue(NetherWartBlock.AGE, i + 1);
                level.setBlock(pos, state, Block.UPDATE_CLIENTS);
            }
        }
    }
}
