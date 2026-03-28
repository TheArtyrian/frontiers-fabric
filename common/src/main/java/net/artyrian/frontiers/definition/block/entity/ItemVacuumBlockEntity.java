package net.artyrian.frontiers.definition.block.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.definition.block.custom.ItemVacuumBlock;
import net.artyrian.frontiers.definition.networking.packet.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.definition.util.MethodToolbox;
import net.artyrian.frontiers.reg.content.ModBlockEntities;
import net.artyrian.frontiers.reg.content.ModTags;
import net.artyrian.frontiers.reg.misc.FRLevelEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.decoration.ItemFrame;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemContainerContents;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.ticks.ContainerSingleItem.*;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ItemVacuumBlockEntity extends BlockEntity implements BlockContainerSingleItem
{
    public static final int COOLDOWN_TIME = 40;
    public static final int COOLDOWN_TIME_FAIL = 10;

    private ItemStack stack = ItemStack.EMPTY;
    private boolean wasEmptyLastFrame = false;
    private int pickup_cooldown = COOLDOWN_TIME;
    private double rotation = 0;
    private double lastRotation = 0;

    public ItemVacuumBlockEntity(BlockPos pos, BlockState state)
    {
        super(ModBlockEntities.ITEM_VACUUM.get(), pos, state);
    }

    public double getRot() { return this.rotation; }
    public double getLastRot() { return this.lastRotation; }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() { return ClientboundBlockEntityDataPacket.create(this); }
    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registryLookup) { return saveWithoutMetadata(registryLookup); }

    @Override
    protected void saveAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.saveAdditional(nbt, registryLookup);
        if (!this.stack.isEmpty()) nbt.put("item", this.stack.save(registryLookup));

        nbt.putInt("PickupCooldown", this.pickup_cooldown);
    }

    @Override
    protected void loadAdditional(CompoundTag nbt, HolderLookup.Provider registryLookup)
    {
        super.loadAdditional(nbt, registryLookup);

        if (nbt.contains("item", Tag.TAG_COMPOUND)) this.stack = ItemStack.parse(registryLookup, nbt.getCompound("item")).orElse(ItemStack.EMPTY);
        else this.stack = ItemStack.EMPTY;

        if (nbt.contains("PickupCooldown", Tag.TAG_INT)) this.pickup_cooldown = nbt.getInt("PickupCooldown");

        this.wasEmptyLastFrame = this.stack.isEmpty();
    }

    @Override
    protected void collectImplicitComponents(DataComponentMap.Builder componentMapBuilder)
    {
        super.collectImplicitComponents(componentMapBuilder);
        componentMapBuilder.set(DataComponents.CONTAINER, ItemContainerContents.fromItems(List.of(this.stack)));
    }

    @Override
    protected void applyImplicitComponents(DataComponentInput components)
    {
        super.applyImplicitComponents(components);
        this.stack = components.getOrDefault(DataComponents.CONTAINER, ItemContainerContents.EMPTY).copyOne();
    }

    @Override
    public void removeComponentsFromTag(CompoundTag nbt)
    {
        super.removeComponentsFromTag(nbt);
        nbt.remove("item");
        nbt.remove("PickupCooldown");
    }

    @Override
    public ItemStack getTheItem()
    {
        return this.stack;
    }

    @Override
    public ItemStack splitTheItem(int count)
    {
        ItemStack itemStack = this.stack.split(count);
        if (this.stack.isEmpty())
        {
            this.stack = ItemStack.EMPTY;
        }

        return itemStack;
    }

    @Override
    public void setTheItem(ItemStack stack)
    {
        this.stack = stack;
    }

    @Override
    public BlockEntity getContainerBlockEntity() {
        return this;
    }

    public static void clientTick(Level world, BlockPos pos, BlockState state, ItemVacuumBlockEntity blockEntity)
    {
        if (!blockEntity.getTheItem().isEmpty())
        {
            RandomSource random = world.getRandom();
            double d = (double)pos.getX() + random.nextDouble();
            double e = (double)pos.getY() + random.nextDouble();
            double f = (double)pos.getZ() + random.nextDouble();

            ItemStack stack = blockEntity.getTheItem();
            SimpleParticleType goalPart = switch (getItemNumeric(stack))
            {
                case 1 -> ParticleTypes.SOUL_FIRE_FLAME;
                case 2 -> MethodToolbox.tryForDundelightFire(false);
                case 3 -> ParticleTypes.HEART;
                default -> ParticleTypes.FLAME;
            };

            world.addParticle(ParticleTypes.SMOKE, d, e, f, 0.0, 0.0, 0.0);
            world.addParticle(goalPart, d, e, f, 0.0, 0.0, 0.0);

            blockEntity.lastRotation = blockEntity.rotation;
            blockEntity.rotation = blockEntity.rotation + 1.0;

            if (blockEntity.rotation == 36.0)
            {
                blockEntity.lastRotation = -1.0F;
                blockEntity.rotation = 0.0F;
            }
        }
        else
        {
            blockEntity.lastRotation = 0;
            blockEntity.rotation = 0;
        }
    }

    public static void serverTick(Level world, BlockPos pos, BlockState state, ItemVacuumBlockEntity blockEntity)
    {
        boolean emptyThisTick = blockEntity.stack.isEmpty();

        if (blockEntity.wasEmptyLastFrame != emptyThisTick)
        {
            world.sendBlockUpdated(pos, state, state, Block.UPDATE_ALL_IMMEDIATE);
        }

        blockEntity.wasEmptyLastFrame = emptyThisTick;

        if (blockEntity.pickup_cooldown > 0) blockEntity.pickup_cooldown--;
        if (blockEntity.pickup_cooldown == 0)
        {
            boolean passed = false;

            AABB box = new AABB(pos).inflate(2.0, 1.0, 2.0);
            List<ItemEntity> itemlist = world.getEntitiesOfClass(ItemEntity.class, box, item -> true);
            if (itemlist != null && !itemlist.isEmpty())
            {
                ItemEntity itemEnt = null;
                Item targetItem = null;

                List<ItemFrame> frames = new ArrayList<>();
                for (Direction dir : Direction.values())
                {
                    if (dir.equals(Direction.DOWN)) continue;

                    AABB boxTxx = new AABB(pos).expandTowards(dir.getStepX() * 0.1, dir.getStepY() * 0.1, dir.getStepZ() * 0.1);
                    List<ItemFrame> pre = world.getEntitiesOfClass(ItemFrame.class, boxTxx, (itemframeent) -> !itemframeent.getItem().isEmpty());
                    if (!pre.isEmpty())
                    {
                        frames.addAll(pre);
                        break;
                    }
                }

                if (!frames.isEmpty())
                {
                    ItemFrame frameEnt = frames.getFirst();
                    if (frameEnt != null) targetItem = frameEnt.getItem().getItem();
                }

                for (ItemEntity ent : itemlist)
                {
                    boolean passCondition = (targetItem != null)
                            ? (!ent.getItem().isEmpty() && ent.getItem().is(targetItem))
                            : (!ent.getItem().isEmpty());

                    if (passCondition)
                    {
                        itemEnt = ent;
                        break;
                    }
                }

                if (itemEnt != null)
                {
                    ItemStack stack = itemEnt.getItem();
                    boolean canTryPickup = !stack.isEmpty() && (targetItem == null || stack.getItem().equals(targetItem));

                    if (canTryPickup)
                    {
                        boolean internalIsEmpty = blockEntity.stack.isEmpty();
                        boolean canMergeTwo = (!internalIsEmpty)
                                ? (ItemEntity.areMergable(stack, blockEntity.stack) && stack.getItem().equals(blockEntity.stack.getItem()))
                                : false;

                        if (internalIsEmpty || canMergeTwo)
                        {
                            passed = true;
                            boolean destroyEntity;

                            ServerLevel serverLevel = ((ServerLevel)world);
                            VectorEventSync.Local.fireEvent(serverLevel, pos, FRLevelEvents.Local.ITEM_VACUUM_FLARE, getItemNumeric(stack));

                            int reduce = stack.getCount();
                            if (canMergeTwo)
                            {
                                int preInt = stack.getCount();
                                ItemStack setStack = ItemEntity.merge(blockEntity.stack, stack, blockEntity.stack.getMaxStackSize());
                                int postInt = stack.getCount();

                                reduce = preInt - postInt;
                                blockEntity.setTheItem(setStack);

                                destroyEntity = (stack.isEmpty());
                            }
                            else
                            {
                                destroyEntity = true;
                                blockEntity.setTheItem(stack);
                            }

                            ServerChunkCache manager = serverLevel.getChunkSource();
                            Vec3 posCen = blockEntity.getBlockPos().getCenter();
                            manager.broadcast(itemEnt, new ItemBlockPickupS2CPacket(itemEnt.getId(), posCen.x, posCen.y, posCen.z, reduce));

                            if (destroyEntity) itemEnt.discard();
                        }
                    }
                }
            }
            blockEntity.pickup_cooldown = (passed) ? COOLDOWN_TIME : COOLDOWN_TIME_FAIL;
        }
    }

    // yandev ass code - artyrian (he made this code)
    private static int getItemNumeric(ItemStack stack)
    {
        if (stack.is(ModTags.Items.ITEM_VACUUM_SOUL_FIRE)) return 1;
        else if (stack.is(ModTags.Items.ITEM_VACUUM_LIVING_FIRE) && Frontiers.DUNGEONS_DELIGHT_LOADED) return 2;
        else if (stack.is(ModTags.Items.ITEM_VACUUM_HEARTS)) return 3;
        return 0;
    }
}
