package net.artyrian.frontiers.mixin.entity.end_crystal;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.attachments.ModAttachmentTypes;
import net.artyrian.frontiers.mixin.entity.EntityRenderMixin;
import net.artyrian.frontiers.mixin_interfaces.EndCrystalMixInterface;
import net.minecraft.block.Blocks;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EndCrystalEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LightType;
import net.minecraft.world.World;
import org.joml.Quaternionf;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.client.render.entity.EndCrystalEntityRenderer.getYOffset;

@Debug(export = true)
@Mixin(EndCrystalEntityRenderer.class)
public abstract class EndCrystalRenderMixin extends EntityRenderMixin
{
    @Shadow @Final private static RenderLayer END_CRYSTAL;

    @Unique private static final Identifier TEXTURE_CRACKED1 = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/end_crystal_damaged1.png");
    @Unique private static final RenderLayer LAYER_CRACKED1 = RenderLayer.getEntityCutoutNoCull(TEXTURE_CRACKED1);
    @Unique private static final Identifier TEXTURE_CRACKED2 = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/end_crystal_damaged2.png");
    @Unique private static final RenderLayer LAYER_CRACKED2 = RenderLayer.getEntityCutoutNoCull(TEXTURE_CRACKED2);
    @Unique private static final Identifier TEXTURE_FRIENDLY = Identifier.of(Frontiers.MOD_ID,"textures/entity/end_crystal/friendly_end_crystal.png");
    @Unique private static final RenderLayer LAYER_FRIENDLY = RenderLayer.getEntityCutoutNoCull(TEXTURE_FRIENDLY);

    @Unique private static final Identifier CRYSTAL_BEAM_TEXTURE_FRNT = Identifier.ofVanilla("textures/entity/end_crystal/end_crystal_beam.png");
    @Unique private static final RenderLayer CRYSTAL_BEAM_LAYER_FRNT = RenderLayer.getEntitySmoothCutout(CRYSTAL_BEAM_TEXTURE_FRNT);

    @Unique private static final float HF_SQRT = (float)(Math.sqrt(3.0) / 2.0);

    @Unique private boolean frontiers$CanProjectFriendlyBeams(World world, BlockPos gotoPos, BlockPos thisPos)
    {
        return world != null && gotoPos != thisPos && world.getBlockState(gotoPos).isOf(Blocks.ENCHANTING_TABLE) && thisPos.isWithinDistance(gotoPos, 6);
    }

    @Override
    protected int getBlockLight(Entity entity, BlockPos blockPos)
    {
        int hit_amnt = entity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        boolean is_friendly = entity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());
        boolean showing_base = ((EndCrystalEntity)entity).shouldShowBottom();

        int block_level = entity.getWorld().getLightLevel(LightType.BLOCK, blockPos);

        if ((is_friendly && !showing_base) || hit_amnt == 1) return Math.max(8, block_level);
        else if (hit_amnt == 2) return Math.max(15, block_level);
        else return block_level;
    }

    @Unique
    private static void frontiersRenderFriendlyBeam(
            float dx, float dy, float dz, float tickDelta, int age, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light)
    {
        float f = MathHelper.sqrt(dx * dx + dz * dz);
        float g = MathHelper.sqrt(dx * dx + dy * dy + dz * dz);
        matrices.push();
        matrices.translate(0.0F, 0.75F, 0.0F);
        matrices.multiply(RotationAxis.POSITIVE_Y.rotation((float)(-Math.atan2(dz, dx)) - (float) (Math.PI / 2)));
        matrices.multiply(RotationAxis.POSITIVE_X.rotation((float)(-Math.atan2(f, dy)) - (float) (Math.PI / 2)));
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(CRYSTAL_BEAM_LAYER_FRNT);
        float h = 0.0F - ((float)age + tickDelta) * -0.01F;
        float i = g / 32.0F - ((float)age + tickDelta) * -0.01F;
        int j = 8;
        float k = 0.0F;
        float l = 0.75F;
        float m = 0.0F;
        MatrixStack.Entry entry = matrices.peek();

        for (int n = 1; n <= 8; n++)
        {
            float o = MathHelper.sin((float)n * (float) (Math.PI * 2) / 8.0F) * 0.75F;
            float p = MathHelper.cos((float)n * (float) (Math.PI * 2) / 8.0F) * 0.75F;
            float q = (float)n / 8.0F;
            vertexConsumer.vertex(entry, k * 0.2F, l * 0.2F, 0.0F)
                    .color(Colors.WHITE)
                    .texture(m, h)
                    .overlay(OverlayTexture.DEFAULT_UV)
                    .light(light)
                    .normal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.vertex(entry, k, l, g).color(0x62E4FF).texture(m, i).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.vertex(entry, o, p, g).color(0x62E4FF).texture(q, i).overlay(OverlayTexture.DEFAULT_UV).light(light).normal(entry, 0.0F, -1.0F, 0.0F);
            vertexConsumer.vertex(entry, o * 0.2F, p * 0.2F, 0.0F)
                    .color(Colors.WHITE)
                    .texture(q, h)
                    .overlay(OverlayTexture.DEFAULT_UV)
                    .light(light)
                    .normal(entry, 0.0F, -1.0F, 0.0F);
            k = o;
            l = p;
            m = q;
        }

        matrices.pop();
    }

    // Renders rays. Totally not based on dragon code.
    @Unique private static void renderRays(MatrixStack matrices, float amnt, float alpha_sub, float beam_len, int rays, VertexConsumer vCon)
    {
        matrices.push();

        int colhelp = ColorHelper.Argb.fromFloats(1.0F - alpha_sub, 1.0F, 1.0F, 1.0F);
        // Note; based on dragon code, def color is 16711935.
        int color = 16711935;
        Random random = Random.create(432L);
        Quaternionf quatro = new Quaternionf();
        float spinadd = Math.min(amnt > 0.8F ? (amnt - 0.8F) / 0.2F : 0.0F, 1.0F);
        Vector3f v3f_1 = new Vector3f();
        Vector3f v3f_2 = new Vector3f();
        Vector3f v3f_3 = new Vector3f();
        Vector3f v3f_4 = new Vector3f();

        for (int x = 0; x < rays; x++)
        {
            quatro.rotationXYZ(random.nextFloat() * (float) (Math.PI * 2), random.nextFloat() * (float) (Math.PI * 2), random.nextFloat() * (float) (Math.PI * 2))
                    .rotateXYZ(
                            random.nextFloat() * (float) (Math.PI * 2),
                            random.nextFloat() * (float) (Math.PI * 2),
                            random.nextFloat() * (float) (Math.PI * 2) + amnt * (float) (Math.PI / 2)
                    );
            matrices.multiply(quatro);

            float g = random.nextFloat() * 10.0F + 5.0F + (beam_len * 10.0F);
            float h = random.nextFloat() * 2.0F + 1.0F + (beam_len * 2.0F);
            v3f_2.set(-HF_SQRT * h, g, -0.5F * h);
            v3f_3.set(HF_SQRT * h, g, -0.5F * h);
            v3f_4.set(0.0F, g, h);

            MatrixStack.Entry entry = matrices.peek();
            vCon.vertex(entry, v3f_1).color(colhelp);
            vCon.vertex(entry, v3f_2).color(color);
            vCon.vertex(entry, v3f_3).color(color);
            vCon.vertex(entry, v3f_1).color(colhelp);
            vCon.vertex(entry, v3f_3).color(color);
            vCon.vertex(entry, v3f_3).color(color);
            vCon.vertex(entry, v3f_1).color(colhelp);
            vCon.vertex(entry, v3f_3).color(color);
            vCon.vertex(entry, v3f_2).color(color);
        }

        matrices.pop();
    }

    @Inject(method = "getTexture(Lnet/minecraft/entity/decoration/EndCrystalEntity;)Lnet/minecraft/util/Identifier;", at = @At("RETURN"), cancellable = true)
    public void getTexture(EndCrystalEntity endCrystalEntity, CallbackInfoReturnable<Identifier> cir)
    {
        int hit_amnt = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        boolean is_friendly = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());

        if (is_friendly) cir.setReturnValue(TEXTURE_FRIENDLY);
        else if (hit_amnt == 1) cir.setReturnValue(TEXTURE_CRACKED1);
        else if (hit_amnt == 2) cir.setReturnValue(TEXTURE_CRACKED2);
    }
    @Inject(
            method = "render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V",
            at = @At(value = "TAIL")
    )
    private void doRays(EndCrystalEntity endCrystalEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, CallbackInfo ci)
    {
        int hit_amnt = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        boolean is_friendly = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());

        if (is_friendly)
        {
            BlockPos gotoPos = ((EndCrystalMixInterface)endCrystalEntity).frontiers$getGoodBeamPos();
            BlockPos thisPos = endCrystalEntity.getBlockPos();
            World world = endCrystalEntity.getWorld();

            // youre witnessing a future day zero vulnerability causer right here
            if (this.frontiers$CanProjectFriendlyBeams(world, gotoPos, thisPos))
            {
                float ssX = (float)gotoPos.getX();
                float ssY = (float)gotoPos.getY() - 0.25F;
                float ssZ = (float)gotoPos.getZ();
                float bX = (float)((double)ssX - thisPos.getX());
                float bY = (float)((double)ssY - thisPos.getY());
                float bZ = (float)((double)ssZ - thisPos.getZ());

                matrixStack.push();
                matrixStack.translate(bX, bY, bZ);
                frontiersRenderFriendlyBeam(-bX, -bY + (EndCrystalEntityRenderer.getYOffset(endCrystalEntity, g) + 1.25F), -bZ, g, endCrystalEntity.age, matrixStack, vertexConsumerProvider, i);
                matrixStack.pop();
            }
        }
        else if (hit_amnt > 0)
        {
            int crack_spin = ((EndCrystalMixInterface)endCrystalEntity).frontiers_1_21x$getCrackSpin();
            float crack_float = ((EndCrystalMixInterface)endCrystalEntity).frontiers_1_21x$getCrackFloat();
            float beamlen = ((EndCrystalMixInterface)endCrystalEntity).frontiers_1_21x$getBeamLen();
            int rays = ((EndCrystalMixInterface)endCrystalEntity).frontiers_1_21x$getRays();

            float amnt_mat = ((float)crack_spin + g) / 20.0f;
            float offsety = getYOffset(endCrystalEntity, g);

            matrixStack.push();
            matrixStack.translate(0.0F, 1.5F + offsety / 2.0F, 0.0F);
            renderRays(matrixStack, amnt_mat, crack_float, beamlen, rays, vertexConsumerProvider.getBuffer(RenderLayer.getDragonRays()));
            renderRays(matrixStack, amnt_mat, crack_float, beamlen, rays, vertexConsumerProvider.getBuffer(RenderLayer.getDragonRaysDepth()));
            matrixStack.pop();
        }
    }

    @ModifyVariable(method = "render(Lnet/minecraft/entity/decoration/EndCrystalEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", at = @At(value = "STORE"), ordinal = 0)
    private VertexConsumer render_new_layer(VertexConsumer value, @Local EndCrystalEntity endCrystalEntity, @Local VertexConsumerProvider vertexConsumerProvider)
    {
        int hit_amnt = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN, ModAttachmentTypes.ENDCRYSTAL_HITS_TAKEN.initializer());
        boolean is_friendly = endCrystalEntity.getAttachedOrCreate(ModAttachmentTypes.ENDCRYSTAL_FRIENDLY, ModAttachmentTypes.ENDCRYSTAL_FRIENDLY.initializer());

        if (is_friendly) return vertexConsumerProvider.getBuffer(LAYER_FRIENDLY);
        else if (hit_amnt == 1) return vertexConsumerProvider.getBuffer(LAYER_CRACKED1);
        else if (hit_amnt == 2) return vertexConsumerProvider.getBuffer(LAYER_CRACKED2);
        else return vertexConsumerProvider.getBuffer(END_CRYSTAL);
    }

    @ModifyReturnValue(method = "shouldRender(Lnet/minecraft/entity/decoration/EndCrystalEntity;Lnet/minecraft/client/render/Frustum;DDD)Z", at = @At("RETURN"))
    private boolean shouldAlsoRenderWithBlockRays(boolean original, @Local(argsOnly = true) EndCrystalEntity endCrystalEntity)
    {
        BlockPos gotoPos = ((EndCrystalMixInterface)endCrystalEntity).frontiers$getGoodBeamPos();
        BlockPos thisPos = endCrystalEntity.getBlockPos();
        World world = endCrystalEntity.getWorld();

        return original || this.frontiers$CanProjectFriendlyBeams(world, gotoPos, thisPos);
    }
}
