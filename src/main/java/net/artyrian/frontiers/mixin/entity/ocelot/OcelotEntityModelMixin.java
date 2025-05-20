package net.artyrian.frontiers.mixin.entity.ocelot;

import net.artyrian.frontiers.mixin_interfaces.OcelotMixIntf;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.OcelotEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.OcelotEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotEntityModel.class)
public abstract class OcelotEntityModelMixin<T extends Entity>
{
    @Shadow protected int animationState;
    @Shadow @Final private static int STANDING_ANIMATION_STATE;
    @Shadow @Final protected ModelPart body;
    @Shadow @Final protected ModelPart head;
    @Shadow @Final protected ModelPart upperTail;
    @Shadow @Final protected ModelPart lowerTail;
    @Shadow @Final protected ModelPart leftFrontLeg;
    @Shadow @Final protected ModelPart rightFrontLeg;
    @Shadow @Final protected ModelPart leftHindLeg;
    @Shadow @Final protected ModelPart rightHindLeg;

    @Inject(method = "animateModel", at = @At("TAIL"))
    private void alsoDoSittingCheckOcelotExcl(T entity, float limbAngle, float limbDistance, float tickDelta, CallbackInfo ci)
    {
        if (entity instanceof OcelotEntity ocelot && this.animationState == STANDING_ANIMATION_STATE)
        {
            if (((OcelotMixIntf)ocelot).frontiers$isInSittingPose())
            {
                this.body.pitch = (float) (Math.PI / 4);
                this.body.pivotY += -4.0F;
                this.body.pivotZ += 5.0F;
                this.head.pivotY += -3.3F;
                this.head.pivotZ++;
                this.upperTail.pivotY += 8.0F;
                this.upperTail.pivotZ += -2.0F;
                this.lowerTail.pivotY += 2.0F;
                this.lowerTail.pivotZ += -0.8F;
                this.upperTail.pitch = 1.7278761F;
                this.lowerTail.pitch = 2.670354F;
                this.leftFrontLeg.pitch = (float) (-Math.PI / 20);
                this.leftFrontLeg.pivotY = 16.1F;
                this.leftFrontLeg.pivotZ = -7.0F;
                this.rightFrontLeg.pitch = (float) (-Math.PI / 20);
                this.rightFrontLeg.pivotY = 16.1F;
                this.rightFrontLeg.pivotZ = -7.0F;
                this.leftHindLeg.pitch = (float) (-Math.PI / 2);
                this.leftHindLeg.pivotY = 21.0F;
                this.leftHindLeg.pivotZ = 1.0F;
                this.rightHindLeg.pitch = (float) (-Math.PI / 2);
                this.rightHindLeg.pivotY = 21.0F;
                this.rightHindLeg.pivotZ = 1.0F;
                this.animationState = 3;
            }
        }
    }
}
