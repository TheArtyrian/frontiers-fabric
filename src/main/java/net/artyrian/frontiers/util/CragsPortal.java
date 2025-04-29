package net.artyrian.frontiers.util;

import net.artyrian.frontiers.block.ModBlocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Optional;

public class CragsPortal
{
    /** Checks for an empty portal in the surrounding plus-shape. Will create a portal if able. Used in the bucket of lava event. */
    public static boolean checkForEmptyPortalAround(World world, BlockPos pos)
    {
        Optional<BlockPos> passcheck;

        while (true)
        {
            passcheck = isEmptyPortalMadeHere(world, pos, true);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(-1, 0, 0), true);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(1, 0, 0), true);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(0, 0, -1), true);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(0, 0, 1), true);
            break;
        }

        if (passcheck.isPresent())
        {
            createPortal(world, passcheck.get());
            return true;
        }
        else
        {
            return false;
        }
    }

    /** Checks for an existing portal in a plus area. Returns an optional BlockPos.
     *
     * <p>This is used exclusively in the CragsPortalBlock neighbor update.
     * */
    public static Optional<BlockPos> checkForExistingPortal(World world, BlockPos pos)
    {
        Optional<BlockPos> passcheck;

        while (true)
        {
            passcheck = isEmptyPortalMadeHere(world, pos, false);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(-1, 0, 0), false);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(1, 0, 0), false);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(0, 0, -1), false);
            if (passcheck.isPresent()) break;

            passcheck = isEmptyPortalMadeHere(world, pos.add(0, 0, 1), false);
            break;
        }

        return passcheck;
    }

    /** Checks if an empty Crags portal is built centered around the position. Badly programmed, i know :P */
    public static Optional<BlockPos> isEmptyPortalMadeHere(World world, BlockPos pos, boolean making_new_portal)
    {
        boolean pass = true;
        BlockPos check = pos;

        // Empty center checks (only checked if making new portal)
        if (making_new_portal)
        {
            pass = isEitherPortalOrEmpty(world, check);
            check = pos.add(1, 0, 0);
            pass = (pass) && isEitherPortalOrEmpty(world, check);
            check = pos.add(-1, 0, 0);
            pass = (pass) && isEitherPortalOrEmpty(world, check);
            check = pos.add(0, 0, 1);
            pass = (pass) && isEitherPortalOrEmpty(world, check);
            check = pos.add(0, 0, -1);
            pass = (pass) && isEitherPortalOrEmpty(world, check);
        }

        // Surrounding outer
        check = pos.add(0, 0, -2);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(0, 0, 2);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(-2, 0, 0);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(-2, 0, 0);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(-1, 0, -1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(-1, 0, 1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(1, 0, -1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(1, 0, 1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);

        // Bottoms
        check = pos.add(0, -1, 0);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(1, -1, 0);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(-1, -1, 0);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(0, -1, 1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);
        check = pos.add(0, -1, -1);
        pass = (pass) && world.getBlockState(check).isOf(ModBlocks.GLOWING_OBSIDIAN);

        if (pass) return Optional.of(pos);
        else return Optional.empty();
    }

    /** Checks the actual "portal" part of the frame. */
    private static boolean isEitherPortalOrEmpty(World world, BlockPos pos)
    {
        return (world.getBlockState(pos).isAir() ||
                world.getBlockState(pos).isOf(ModBlocks.CRAGS_PORTAL) ||
                world.getFluidState(pos).isOf(Fluids.LAVA) ||
                world.getFluidState(pos).isOf(Fluids.FLOWING_LAVA)
        );
    }

    /** Creates a t-shape of portal blocks around this position. */
    public static void createPortal(World world, BlockPos pos)
    {
        world.setBlockState(pos, ModBlocks.CRAGS_PORTAL.getDefaultState());
        world.setBlockState(pos.add(-1, 0, 0), ModBlocks.CRAGS_PORTAL.getDefaultState());
        world.setBlockState(pos.add(1, 0, 0), ModBlocks.CRAGS_PORTAL.getDefaultState());
        world.setBlockState(pos.add(0, 0, -1), ModBlocks.CRAGS_PORTAL.getDefaultState());
        world.setBlockState(pos.add(0, 0, 1), ModBlocks.CRAGS_PORTAL.getDefaultState());
    }
}
