package net.artyrian.frontiers.definition.entity.projectile;

import net.artyrian.frontiers.definition.item.custom.BallItem;
import net.artyrian.frontiers.reg.content.ModEntity;
import net.artyrian.frontiers.reg.content.ModItem;
import net.artyrian.frontiers.reg.content.ModSounds;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.ModStats;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BallEntity extends ThrowableItemProjectile
{
    private boolean intercepted = false;
    private int hitCount = 0;
    private int defaultBounces = 0;
    private int bouncesLeft = 0;

    public BallEntity(EntityType<? extends BallEntity> entityType, Level world)
    {
        super(entityType, world);
    }

    public BallEntity(double d, double e, double f, Level world)
    {
        super(ModEntity.BALL.get(), d, e, f, world);
    }

    public BallEntity(LivingEntity livingEntity, Level world)
    {
        super(ModEntity.BALL.get(), livingEntity, world);
    }

    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Intercepted", this.intercepted);
        nbt.putInt("HitCount", this.hitCount);
        nbt.putInt("DefaultBounces", this.defaultBounces);
        nbt.putInt("BouncesLeft", this.bouncesLeft);
    }

    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Intercepted")) this.intercepted = nbt.getBoolean("Intercepted");
        if (nbt.contains("HitCount")) this.hitCount = nbt.getInt("HitCount");
        if (nbt.contains("DefaultBounces")) this.defaultBounces = nbt.getInt("DefaultBounces");
        if (nbt.contains("BouncesLeft")) this.bouncesLeft = nbt.getInt("BouncesLeft");
    }

    @Override
    protected Item getDefaultItem()
    {
        return ModItem.BALL.get();
    }

    @Override
    public boolean canUsePortal(boolean allowVehicles) { return false; }

    public void setBounces(int bounces)
    {
        this.defaultBounces = bounces;
        this.bouncesLeft = this.defaultBounces;
    }

    private ParticleOptions getParticleParameters()
    {
        ItemStack itemStack = this.getItem();
        return (!itemStack.isEmpty() ? new ItemParticleOption(ParticleTypes.ITEM, itemStack) : ParticleTypes.WHITE_SMOKE);
    }

    public void handleEntityEvent(byte status)
    {
        if (status == 3)
        {
            ParticleOptions particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
        else if (status == 5)
        {
            ParticleOptions particleEffect = this.getParticleParameters();

            for(int i = 0; i < 8; ++i)
            {
                this.level().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
                this.level().addParticle(ParticleTypes.WHITE_SMOKE, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }

    }

    protected void onHitEntity(EntityHitResult entityHitResult)
    {
        super.onHitEntity(entityHitResult);
        Entity entity = entityHitResult.getEntity();
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), 0.0F);
    }

    @Override
    public boolean deflect(ProjectileDeflection deflection, @Nullable Entity deflector, @Nullable Entity owner, boolean fromAttack)
    {
        return super.deflect(deflection, deflector, owner, fromAttack);
    }

    @Override
    protected void onDeflection(@Nullable Entity deflector, boolean fromAttack)
    {
        if (deflector instanceof Player playerHitter && fromAttack && !this.intercepted)
        {
            // Get playername and color formatting
            ItemStack stack = this.getItem();
            String name = playerHitter.getScoreboardName();
            String stackname = stack.getHoverName().getString();
            ChatFormatting color = ChatFormatting.WHITE;
            if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

            // Check if the player's hand is empty.
            boolean hand_empty = !this.intercepted && playerHitter.getMainHandItem().isEmpty();
            boolean proper_deflect = !this.intercepted && playerHitter.getMainHandItem().is(ModTags.Items.DEFLECTS_BALLS);

            // Set up message to send.
            String returnmessage = "entity.frontiers.ball.stopped";
            if (proper_deflect) returnmessage = "entity.frontiers.ball.hit";
            else if (hand_empty) returnmessage = "entity.frontiers.ball.caught";

            // Give the ball to the hitter if their hand is empty
            if (hand_empty)
            {
                playerHitter.setItemInHand(InteractionHand.MAIN_HAND, this.getItem());
                this.discard();
            }
            // Else destroy the ball if it wasn't properly deflected
            else if (!proper_deflect)
            {
                boolean do_drop = !(this.getOwner() instanceof Player player) || !player.isCreative();
                if (do_drop)
                {
                    this.spawnAtLocation(getItem());
                }
                this.discard();
            }
            // If all other cases fail, create 1 XP after 5 hits
            else
            {
                // Replenish bounces
                this.bouncesLeft = this.defaultBounces;
                this.hitCount++;
                playerHitter.awardStat(ModStats.getStat(ModStats.HIT_BALL.get()));

                if (!playerHitter.level().isClientSide && this.hitCount > 5)
                {
                    ExperienceOrb.award((ServerLevel)playerHitter.level(), playerHitter.position(), 1);
                    if (this.hitCount >= 20)
                    {
                        ((ServerPlayer)playerHitter).getAdvancements().award(
                                playerHitter.level().getServer().getAdvancements().get(ResourceLocation.withDefaultNamespace("adventure/hit_ball_twenty")),
                                "hit"
                        );
                    }
                }
            }

            // Alert all nearby players
            this.announceToNearby(playerHitter, returnmessage, name, stackname, color);
        }
        super.onDeflection(deflector, fromAttack);
    }

    protected void onHit(HitResult hitResult)
    {
        HitResult.Type hittype = hitResult.getType();
        boolean did_itemdrop = false;
        boolean attempt_discard = true;

        if (!this.level().isClientSide)
        {
            if (hittype == HitResult.Type.ENTITY)
            {
                // Check for the entity.
                // Intercept if hitting another ball, or announce a player hit if player.
                EntityHitResult entityHitResult = (EntityHitResult)hitResult;
                Entity hittarget = entityHitResult.getEntity();
                if (hittarget instanceof BallEntity ballEntity)
                {
                    ballEntity.spawnAtLocation(ballEntity.getItem());
                    ballEntity.intercepted = true;
                    ballEntity.discard();

                    // Alert other players to an intercept
                    ItemStack stack = ballEntity.getItem();
                    String name = (this.getOwner() instanceof Player player) ? player.getScoreboardName() : "Something";
                    String stackname = stack.getHoverName().getString();
                    ChatFormatting color = ChatFormatting.WHITE;
                    if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

                    // Do intercept text code.
                    this.announceToNearby(ballEntity, "entity.frontiers.ball.intercept", name, stackname, color);
                }
                else if (hittarget instanceof Player player)
                {
                    did_itemdrop = true;
                    this.spawnAtLocation(getItem());

                    // Alert other players to an intercept
                    ItemStack stack = this.getItem();
                    String name = player.getScoreboardName();
                    String stackname = stack.getHoverName().getString();
                    ChatFormatting color = ChatFormatting.WHITE;
                    if (stack.getItem() instanceof BallItem ball) color = ball.getColor();

                    // Do intercept text code.
                    this.announceToNearby(player, "entity.frontiers.ball.got_hit", name, stackname, color);
                }
            }
            else if (hittype == HitResult.Type.BLOCK)
            {
                BlockHitResult blockHitResult = (BlockHitResult)hitResult;
                BlockPos blockPos = blockHitResult.getBlockPos();
                if (this.bouncesLeft > 0)
                {
                    attempt_discard = false;

                    // SPECIAL THANKS TO YIRMIRI FOR HER HELP GO CHECK OUT DUNGEON'S DELIGHT!!!!
                    Vec3 reflected = new Vec3(getDeltaMovement().toVector3f().reflect(blockHitResult.getDirection().step())).scale(0.5F);
                    setDeltaMovement(reflected);
                    this.setPosRaw(this.getX() + reflected.x, this.getY() + reflected.y, this.getZ() + reflected.z);
                    this.hasImpulse = true;

                    this.bouncesLeft--;
                    this.level().playSound(this, this.blockPosition(), ModSounds.BALL_BOUNCE.get(), SoundSource.PLAYERS, 1.0F, (this.random.nextFloat() * 0.2F + 0.9F));

                    this.level().broadcastEntityEvent(this, (byte)5);
                    this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Context.of(this, this.level().getBlockState(blockPos)));
                }
                else
                {
                    this.onHitBlock(blockHitResult);
                    this.level().gameEvent(GameEvent.PROJECTILE_LAND, blockPos, GameEvent.Context.of(this, this.level().getBlockState(blockPos)));
                }
            }

            if (attempt_discard)
            {
                //this.getWorld().sendEntityStatus(this, (byte)3);
                boolean do_drop = !(this.getOwner() instanceof Player player) || !player.isCreative();
                if (do_drop && !did_itemdrop)
                {
                    this.spawnAtLocation(getItem());
                }
                this.discard();
            }
        }
    }

    private void announceToNearby(Entity caller, String text_key, String name, String stackname, ChatFormatting color)
    {
        List<Entity> nearby = caller.level().getEntities(null, new AABB(
                new Vec3(caller.getBlockX() - 16, caller.getBlockY() - 16, caller.getBlockZ() - 16),
                new Vec3(caller.getBlockX() + 16, caller.getBlockY() + 16, caller.getBlockZ() + 16)
        ));

        for (Entity i : nearby)
        {
            if (i instanceof Player player)
            {
                player.displayClientMessage(Component.translatable(text_key, name, stackname).withStyle(color), true);
            }
        }
    }
}
