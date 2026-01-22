package net.artyrian.frontiers.mixin.entity.ocelot;

import net.artyrian.frontiers.mixin_intf.OcelotMixIntf;
import net.minecraft.client.model.OcelotModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.Ocelot;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(OcelotModel.class)
public abstract class OcelotEntityModelMixin<T extends Entity>
{
    @Shadow protected int state;
    @Shadow @Final private static int WALK_STATE;
    @Shadow @Final protected ModelPart body;
    @Shadow @Final protected ModelPart head;
    @Shadow @Final protected ModelPart tail1;
    @Shadow @Final protected ModelPart tail2;
    @Shadow @Final protected ModelPart leftFrontLeg;
    @Shadow @Final protected ModelPart rightFrontLeg;
    @Shadow @Final protected ModelPart leftHindLeg;
    @Shadow @Final protected ModelPart rightHindLeg;

    @Inject(method = "prepareMobModel", at = @At("TAIL"))
    private void alsoDoSittingCheckOcelotExcl(T entity, float limbAngle, float limbDistance, float tickDelta, CallbackInfo ci)
    {
        if (entity instanceof Ocelot ocelot && this.state == WALK_STATE)
        {
            if (((OcelotMixIntf)ocelot).frontiers$isInSittingPose())
            {
                this.body.xRot = (float) (Math.PI / 4);
                this.body.y += -4.0F;
                this.body.z += 5.0F;
                this.head.y += -3.3F;
                this.head.z++;
                this.tail1.y += 8.0F;
                this.tail1.z += -2.0F;
                this.tail2.y += 2.0F;
                this.tail2.z += -0.8F;
                this.tail1.xRot = 1.7278761F;
                this.tail2.xRot = 2.670354F;
                this.leftFrontLeg.xRot = (float) (-Math.PI / 20);
                this.leftFrontLeg.y = 16.1F;
                this.leftFrontLeg.z = -7.0F;
                this.rightFrontLeg.xRot = (float) (-Math.PI / 20);
                this.rightFrontLeg.y = 16.1F;
                this.rightFrontLeg.z = -7.0F;
                this.leftHindLeg.xRot = (float) (-Math.PI / 2);
                this.leftHindLeg.y = 21.0F;
                this.leftHindLeg.z = 1.0F;
                this.rightHindLeg.xRot = (float) (-Math.PI / 2);
                this.rightHindLeg.y = 21.0F;
                this.rightHindLeg.z = 1.0F;
                this.state = 3;
            }
        }
    }
}
