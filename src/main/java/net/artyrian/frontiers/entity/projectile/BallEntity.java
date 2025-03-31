package net.artyrian.frontiers.entity.projectile;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.entity.ModEntity;
import net.artyrian.frontiers.item.ModItem;
import net.artyrian.frontiers.item.custom.BallItem;
import net.artyrian.frontiers.tag.ModTags;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BallEntity extends ThrownItemEntity
{
    private boolean intercepted = false;
    private int hitCount = 0;

    public BallEntity(EntityType<? extends BallEntity> entityType, World world)
    {
        super(entityType, world);
    }

    public BallEntity(double d, double e, double f, World world)
    {
        super(ModEntity.BALL, d, e, f, world);
    }

    public BallEntity(LivingEntity livingEntity, World world)
    {
        super(ModEntity.BALL, livingEntity, world);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Intercepted", this.intercepted);
        nbt.putInt("HitCount", this.hitCount);
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        if (nbt.contains("Intercepted"))
        {
            this.intercepted = nbt.getBoolean("Intercepted");
        }
        if (nbt.contains("HitCount"))
        {
            this.hitCount = nbt.getInt("HitCount");
        }
    }

    @Override
    protected Item getDefaultItem()
    {
        return ModItem.BALL;
    }

    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (!itemStack.isEmpty() && !itemStack.isOf(this.getDefaultItem()) ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack) : ParticleTypes.WHITE_SMOKE);
    }

    public void handleStatus(byte status) {
        if (status == 3) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onEntityHit(EntityHitResult entityHitResult) {
        super.onEntityHit(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.damage(this.getDamageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    public boolean deflect(ProjectileDeflection deflection, @Nullable Entity deflector, @Nullable Entity owner, boolean fromAttack)
    {
        return super.deflect(deflection, deflector, owner, fromAttack);
    }

    @Override
    protected void onDeflected(@Nullable Entity deflector, boolean fromAttack)
    {
        if (deflector instanceof PlayerEntity playerHitter && fromAttack && !this.intercepted)
        {
            // Get playername and color formatting
            ItemStack stack = this.getStack();
            String name = playerHitter.getNameForScoreboard();
            String stackname = stack.getName().getString();
            Formatting color = Formatting.WHITE;
            if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

            // Check if the player's hand is empty.
            boolean hand_empty = !this.intercepted && playerHitter.getMainHandStack().isEmpty();
            boolean proper_deflect = !this.intercepted && playerHitter.getMainHandStack().isIn(ModTags.Items.DEFLECTS_BALLS);

            // Set up message to send.
            String returnmessage = "entity.frontiers.ball.stopped";
            if (proper_deflect) returnmessage = "entity.frontiers.ball.hit";
            else if (hand_empty) returnmessage = "entity.frontiers.ball.caught";

            // Give the ball to the hitter if their hand is empty
            if (hand_empty)
            {
                playerHitter.setStackInHand(Hand.MAIN_HAND, this.getStack());
                this.discard();
            }
            // Else destroy the ball if it wasn't properly deflected
            else if (!proper_deflect)
            {
                if (this.getOwner() instanceof PlayerEntity player && !player.isCreative())
                {
                    this.dropStack(getStack());
                }
                this.discard();
            }
            // If all other cases fail, create 1 XP after 5 hits
            else
            {
                this.hitCount++;
                if (!playerHitter.getWorld().isClient && this.hitCount > 5) ExperienceOrbEntity.spawn((ServerWorld)playerHitter.getWorld(), playerHitter.getPos(), 1);
            }

            // Alert all nearby players
            this.announceToNearby(playerHitter, returnmessage, name, stackname, color);
        }
        super.onDeflected(deflector, fromAttack);
    }

    protected void onCollision(HitResult hitResult) {
        HitResult.Type hittype = hitResult.getType();
        boolean did_itemdrop = false;

        if (!this.getWorld().isClient)
        {
            if (hittype == HitResult.Type.ENTITY)
            {
                // Check for the entity.
                // Intercept if hitting another ball, or announce a player hit if player.
                EntityHitResult entityHitResult = (EntityHitResult)hitResult;
                Entity hittarget = entityHitResult.getEntity();
                if (hittarget instanceof BallEntity ballEntity)
                {
                    ballEntity.dropStack(ballEntity.getStack());
                    ballEntity.intercepted = true;
                    ballEntity.discard();

                    // Alert other players to an intercept
                    ItemStack stack = ballEntity.getStack();
                    String name = (this.getOwner() instanceof PlayerEntity player) ? player.getNameForScoreboard() : "Entity";
                    String stackname = stack.getName().getString();
                    Formatting color = Formatting.WHITE;
                    if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

                    // Do intercept text code.
                    this.announceToNearby(ballEntity, "entity.frontiers.ball.intercept", name, stackname, color);
                }
                else if (hittarget instanceof PlayerEntity player)
                {
                    did_itemdrop = true;
                    this.dropStack(getStack());

                    // Alert other players to an intercept
                    ItemStack stack = this.getStack();
                    String name = player.getNameForScoreboard();
                    String stackname = stack.getName().getString();
                    Formatting color = Formatting.WHITE;
                    if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

                    // Do intercept text code.
                    this.announceToNearby(player, "entity.frontiers.ball.got_hit", name, stackname, color);
                }
            }

            //this.getWorld().sendEntityStatus(this, (byte)3);
            if (this.getOwner() instanceof PlayerEntity player && !player.isCreative() && !did_itemdrop)
            {
                this.dropStack(getStack());
            }
            this.discard();
        }
        super.onCollision(hitResult);
    }

    private void announceToNearby(Entity caller, String text_key, String name, String stackname, Formatting color)
    {
        List<Entity> nearby = caller.getWorld().getOtherEntities(null, new Box(
                new Vec3d(caller.getBlockX() - 16, caller.getBlockY() - 16, caller.getBlockZ() - 16),
                new Vec3d(caller.getBlockX() + 16, caller.getBlockY() + 16, caller.getBlockZ() + 16)
        ));

        for (Entity i : nearby)
        {
            if (i instanceof PlayerEntity player)
            {
                player.sendMessage(Text.translatable(text_key, name, stackname).formatted(color), true);
            }
        }
    }
}
