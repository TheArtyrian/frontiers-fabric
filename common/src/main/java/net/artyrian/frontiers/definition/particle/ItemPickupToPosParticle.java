package net.artyrian.frontiers.definition.particle;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.phys.Vec3;

public class ItemPickupToPosParticle extends Particle
{
    private static final int field_32656 = 3;
    private final RenderBuffers bufferStorage;
    private final Entity itemEntity;
    private final Vec3 gotoPos;
    private int ticksExisted;
    private final EntityRenderDispatcher dispatcher;
    private double targetX;
    private double targetY;
    private double targetZ;
    private double lastTargetX;
    private double lastTargetY;
    private double lastTargetZ;

    public ItemPickupToPosParticle(
            EntityRenderDispatcher dispatcher, RenderBuffers bufferStorage, ClientLevel world, Entity itemEntity, Vec3 goto_pos
    )
    {
        this(dispatcher, bufferStorage, world, itemEntity, goto_pos, itemEntity.getDeltaMovement());
    }

    private ItemPickupToPosParticle(
            EntityRenderDispatcher dispatcher, RenderBuffers bufferStorage, ClientLevel world, Entity itemEntity, Vec3 goto_pos, Vec3 velocity
    )
    {
        super(world, itemEntity.getX(), itemEntity.getY(), itemEntity.getZ(), velocity.x, velocity.y, velocity.z);
        this.bufferStorage = bufferStorage;
        this.itemEntity = this.getOrCopy(itemEntity);
        this.gotoPos = goto_pos;
        this.dispatcher = dispatcher;
        this.updateTargetPos();
        this.updateLastTargetPos();
    }

    private Entity getOrCopy(Entity entity)
    {
        return (!(entity instanceof ItemEntity) ? entity : ((ItemEntity)entity).copy());
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.CUSTOM;
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float tickDelta)
    {
        float f = ((float)this.ticksExisted + tickDelta) / 3.0F;
        f *= f;
        double d = Mth.lerp(tickDelta, this.lastTargetX, this.targetX);
        double e = Mth.lerp(tickDelta, this.lastTargetY, this.targetY);
        double g = Mth.lerp(tickDelta, this.lastTargetZ, this.targetZ);
        double h = Mth.lerp(f, this.itemEntity.getX(), d);
        double i = Mth.lerp(f, this.itemEntity.getY(), e);
        double j = Mth.lerp(f, this.itemEntity.getZ(), g);
        MultiBufferSource.BufferSource immediate = this.bufferStorage.bufferSource();
        Vec3 vec3d = camera.getPosition();
        this.dispatcher
                .render(
                        this.itemEntity,
                        h - vec3d.x(),
                        i - vec3d.y(),
                        j - vec3d.z(),
                        this.itemEntity.getYRot(),
                        tickDelta,
                        new PoseStack(),
                        immediate,
                        this.dispatcher.getPackedLightCoords(this.itemEntity, tickDelta)
                );
        immediate.endBatch();
    }

    @Override
    public void tick()
    {
        this.ticksExisted++;
        if (this.ticksExisted == 3)
        {
            this.remove();
        }

        this.updateLastTargetPos();
        this.updateTargetPos();
    }

    private void updateTargetPos()
    {
        this.targetX = this.gotoPos.x();
        this.targetY = this.gotoPos.y();
        this.targetZ = this.gotoPos.z();
    }

    private void updateLastTargetPos()
    {
        this.lastTargetX = this.targetX;
        this.lastTargetY = this.targetY;
        this.lastTargetZ = this.targetZ;
    }
}