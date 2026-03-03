package net.vertisoft.vectorlib.mixin.impl.eventsync;

import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelRenderer;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin implements VectorLevelRenderer
{
    @Shadow @Nullable private ClientLevel level;
    @Shadow @Final private Minecraft minecraft;

    @Override
    public void vectorLib$runGameEvent(int type, BlockPos pos, int data)
    {
        if (type >= 0 && type < VectorEventSync.Local.EVENT_LIST.size())
        {
            VectorEventSync.Local.VecEventAlias alias = VectorEventSync.Local.EVENT_LIST.get(type);
            alias.execute(this.level, pos, data);
        }
        else throw new ArrayIndexOutOfBoundsException(String.format("Value %s out of bounds in the VectorEventSync list", type));
    }

    @Override
    public void vectorLib$runDualEvent(int type, Vec3 pos1, Vec3 pos2, int data)
    {
        if (type >= 0 && type < VectorEventSync.Dual.EVENT_LIST.size())
        {
            VectorEventSync.Dual.VecDualEventAlias alias = VectorEventSync.Dual.EVENT_LIST.get(type);
            alias.execute(this.level, pos1, pos2, data);
        }
        else throw new ArrayIndexOutOfBoundsException(String.format("Value %s out of bounds in the dual-pos VectorEventSync list", type));
    }

    @Override
    public void vectorLib$runEntityEvent(int type, Entity entity, int data)
    {
        if (type >= 0 && type < VectorEventSync.Entity.EVENT_LIST.size())
        {
            VectorEventSync.Entity.VecEntityEventAlias alias = VectorEventSync.Entity.EVENT_LIST.get(type);
            alias.execute(this.level, entity, data);
        }
        else throw new ArrayIndexOutOfBoundsException(String.format("Value %s out of bounds in the entity VectorEventSync list", type));
    }

    @Override
    public void vectorLib$runGlobalEvent(int type, BlockPos pos, int data)
    {
        Camera camera = this.minecraft.gameRenderer.getMainCamera();

        if (camera.isInitialized())
        {
            double diffX = (double)pos.getX() - camera.getPosition().x;
            double diffY = (double)pos.getY() - camera.getPosition().y;
            double diffZ = (double)pos.getZ() - camera.getPosition().z;
            double sqrt = Math.sqrt(diffX * diffX + diffY * diffY + diffZ * diffZ);
            double camX = camera.getPosition().x;
            double camY = camera.getPosition().y;
            double camZ = camera.getPosition().z;
            if (sqrt > 0.0)
            {
                camX += diffX / sqrt * 2.0;
                camY += diffY / sqrt * 2.0;
                camZ += diffZ / sqrt * 2.0;
            }

            if (type >= 0 && type < VectorEventSync.Global.EVENT_LIST.size())
            {
                VectorEventSync.Global.VecGlobalEventAlias alias = VectorEventSync.Global.EVENT_LIST.get(type);
                alias.execute(this.level, new Vec3(camX, camY, camZ), data);
            }
            else throw new ArrayIndexOutOfBoundsException(String.format("Value %s out of bounds in the global VectorEventSync list", type));
        }
    }
}
