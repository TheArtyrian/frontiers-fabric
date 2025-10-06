package net.artyrian.frontiers.block.entity;

import net.artyrian.frontiers.Frontiers;
import net.artyrian.frontiers.data.packets.ItemBlockPickupS2CPacket;
import net.artyrian.frontiers.data.payloads.ItemVacuumEmptyPayload;
import net.artyrian.frontiers.data.payloads.ItemVacuumStackSyncPayload;
import net.artyrian.frontiers.mixin_interfaces.ExpMixImpl;
import net.artyrian.frontiers.tag.ModTags;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ContainerComponent;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.decoration.ItemFrameEntity;
import net.minecraft.inventory.SingleStackInventory.SingleStackBlockEntityInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerChunkManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemVacuumBlockEntity extends BlockEntity implements SingleStackBlockEntityInventory
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
        super(ModBlockEntities.ITEM_VACUUM, pos, state);
    }

    public double getRot() { return this.rotation; }
    public double getLastRot() { return this.lastRotation; }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() { return BlockEntityUpdateS2CPacket.create(this); }
    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) { return createNbt(registryLookup); }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.writeNbt(nbt, registryLookup);
        if (!this.stack.isEmpty())
        {
            nbt.put("item", this.stack.encode(registryLookup));
        }
        nbt.putInt("PickupCooldown", this.pickup_cooldown);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup)
    {
        super.readNbt(nbt, registryLookup);
        if (nbt.contains("item", NbtElement.COMPOUND_TYPE))
        {
            this.stack = ItemStack.fromNbt(registryLookup, nbt.getCompound("item")).orElse(ItemStack.EMPTY);
        }
        else
        {
            this.stack = ItemStack.EMPTY;
        }

        if (nbt.contains("PickupCooldown", NbtElement.INT_TYPE))
        {
            this.pickup_cooldown = nbt.getInt("PickupCooldown");
        }
    }

    @Override
    protected void addComponents(ComponentMap.Builder componentMapBuilder)
    {
        super.addComponents(componentMapBuilder);
        componentMapBuilder.add(DataComponentTypes.CONTAINER, ContainerComponent.fromStacks(List.of(this.stack)));
    }

    @Override
    protected void readComponents(BlockEntity.ComponentsAccess components)
    {
        super.readComponents(components);
        this.stack = components.getOrDefault(DataComponentTypes.CONTAINER, ContainerComponent.DEFAULT).copyFirstStack();
    }

    @Override
    public void removeFromCopiedStackNbt(NbtCompound nbt)
    {
        super.removeFromCopiedStackNbt(nbt);
        nbt.remove("item");
        nbt.remove("PickupCooldown");
    }

    @Override
    public ItemStack getStack()
    {
        return this.stack;
    }

    @Override
    public ItemStack decreaseStack(int count)
    {
        ItemStack itemStack = this.stack.split(count);
        if (this.stack.isEmpty())
        {
            this.stack = ItemStack.EMPTY;
        }

        return itemStack;
    }

    @Override
    public void setStack(ItemStack stack)
    {
        this.stack = stack;
    }

    @Override
    public BlockEntity asBlockEntity() {
        return this;
    }

    public static void clientTick(World world, BlockPos pos, BlockState state, ItemVacuumBlockEntity blockEntity)
    {
        if (!blockEntity.getStack().isEmpty())
        {
            Random random = world.getRandom();
            double d = (double)pos.getX() + random.nextDouble();
            double e = (double)pos.getY() + random.nextDouble();
            double f = (double)pos.getZ() + random.nextDouble();

            SimpleParticleType goalPart = ParticleTypes.FLAME;
            if (blockEntity.getStack().isIn(ModTags.Items.ITEM_VACUUM_SOUL_FIRE)) goalPart = ParticleTypes.SOUL_FIRE_FLAME;
            else if (blockEntity.getStack().isIn(ModTags.Items.ITEM_VACUUM_HEARTS)) goalPart = ParticleTypes.HEART;

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

    public static void serverTick(World world, BlockPos pos, BlockState state, ItemVacuumBlockEntity blockEntity)
    {
        if (blockEntity.pickup_cooldown > 0) blockEntity.pickup_cooldown--;
        if (blockEntity.pickup_cooldown == 0)
        {
            boolean passed = false;

            Box box = Box.of(blockEntity.getPos().toCenterPos(), 4.0, 2.5, 4.0);
            List<ItemEntity> itemlist = world.getEntitiesByClass(ItemEntity.class, box, item -> true);
            if (itemlist != null && !itemlist.isEmpty())
            {
                ItemEntity itemEnt = null;
                Item targetItem = null;

                Box boxTxx = Box.from(blockEntity.getPos().toCenterPos().add(0, 1, 0)).expand(1, 1, 1);
                List<ItemFrameEntity> frame = world.getEntitiesByClass(ItemFrameEntity.class, boxTxx, itemframeent -> {
                    return itemframeent.getHeldItemStack() != null && !itemframeent.getHeldItemStack().isEmpty(); }
                );

                if (frame != null && !frame.isEmpty())
                {
                    ItemFrameEntity frameEnt = frame.getFirst();
                    if (frameEnt != null) targetItem = frameEnt.getHeldItemStack().getItem();
                }

                for (ItemEntity ent : itemlist)
                {
                    boolean passCondition = (targetItem != null)
                            ? (!ent.getStack().isEmpty() && ent.getStack().isOf(targetItem))
                            : (!ent.getStack().isEmpty());

                    if (passCondition)
                    {
                        itemEnt = ent;
                        break;
                    }
                }

                if (itemEnt != null)
                {
                    ItemStack stack = itemEnt.getStack();
                    boolean canTryPickup = !stack.isEmpty() && (targetItem == null || stack.getItem().equals(targetItem));

                    if (canTryPickup)
                    {
                        boolean internalIsEmpty = blockEntity.stack.isEmpty();
                        boolean canMergeTwo = (!internalIsEmpty)
                                ? (ItemEntity.canMerge(stack, blockEntity.stack) && stack.getItem().equals(blockEntity.stack.getItem()))
                                : false;

                        if (internalIsEmpty || canMergeTwo)
                        {
                            passed = true;
                            boolean destroyEntity = false;

                            int reduce = stack.getCount();
                            if (canMergeTwo)
                            {
                                int preInt = stack.getCount();
                                Frontiers.LOGGER.info(String.valueOf(stack.getCount()));
                                ItemStack setStack = ItemEntity.merge(blockEntity.stack, stack, blockEntity.stack.getMaxCount());
                                int postInt = stack.getCount();
                                Frontiers.LOGGER.info(String.valueOf(stack.getCount()));

                                reduce = preInt - postInt;
                                blockEntity.setStack(setStack);

                                destroyEntity = (stack.isEmpty());
                            }
                            else
                            {
                                destroyEntity = true;
                                blockEntity.setStack(stack);
                            }

                            ServerChunkManager manager = ((ServerWorld)blockEntity.getWorld()).getChunkManager();
                            if (manager != null)
                            {
                                Vec3d posCen = blockEntity.getPos().toCenterPos();
                                manager.sendToOtherNearbyPlayers(itemEnt, new ItemBlockPickupS2CPacket(itemEnt.getId(), posCen.x, posCen.y, posCen.z, reduce));
                            }

                            if (destroyEntity) itemEnt.discard();
                        }
                    }
                }
            }

            blockEntity.pickup_cooldown = (passed) ? COOLDOWN_TIME : COOLDOWN_TIME_FAIL;
        }

        boolean emptyThisTick = blockEntity.stack.isEmpty();

        if (blockEntity.wasEmptyLastFrame != emptyThisTick)
        {
            world.updateComparators(pos, world.getBlockState(pos).getBlock());
            for (ServerPlayerEntity targeter : PlayerLookup.tracking((ServerWorld) world, pos))
            {
                ServerPlayNetworking.send(targeter,
                        blockEntity.stack.isEmpty() ? new ItemVacuumEmptyPayload(pos) : new ItemVacuumStackSyncPayload(pos, blockEntity.stack)
                );
            }
        }

        blockEntity.wasEmptyLastFrame = emptyThisTick;
    }
}
