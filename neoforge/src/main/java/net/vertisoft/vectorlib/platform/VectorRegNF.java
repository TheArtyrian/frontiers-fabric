package net.vertisoft.vectorlib.platform;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.MapCodec;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.vertisoft.vectorlib.agnostic.util.VectorItemTab;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class VectorRegNF implements VectorRegistryIntf
{
    private final Map<String, Map<ResourceKey<? extends Registry<?>>, DeferredRegister<?>>> MOD_REG = new HashMap<>();
    private IEventBus EVENT_BUS = ModLoadingContext.get().getActiveContainer().getEventBus();

    public void setEventBus(IEventBus eventBus) {
        this.EVENT_BUS = eventBus;
    }

    private Map<ResourceKey<? extends Registry<?>>, DeferredRegister<?>> initRegistry(String modId)
    {
        if (!MOD_REG.containsKey(modId)) MOD_REG.put(modId, new HashMap<>());
        return MOD_REG.get(modId);
    }

    @Override
    public <T> Supplier<T> register(String modid, String id, Registry<T> registry, Supplier<T> supplier)
    {
        DeferredRegister<T> deferredRegister = DeferredRegister.create(registry.key(), modid);
        deferredRegister.register(EVENT_BUS);
        deferredRegister.register(id, supplier);
        return () -> registry.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> Holder<T> registerHolder(String modid, String id, Registry<T> registry, Supplier<T> holder)
    {
        DeferredRegister<T> defReg;
        var registries = initRegistry(modid);
        if (!registries.containsKey(registry.key())) {
            var i = DeferredRegister.create((ResourceKey)registry.key(), modid);
            i.register(EVENT_BUS);
            registries.put(registry.key(), i);
        }
        defReg = (DeferredRegister<T>)registries.get(registry.key());
        var register = defReg.register(id, holder);
        return register;
    }

    @Override
    public <T> Holder.Reference<T> registerHolderRef(String modid, String id, Registry<T> registry, Supplier<T> holder)
    {
        return (Holder.Reference<T>) registerHolder(modid, id, registry, holder);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends BlockEntity> Supplier<BlockEntityType<T>> registerBlockEntityType(String modid, String id, Supplier<BlockEntityType<T>> type)
    {
        var registryKey = Registries.BLOCK_ENTITY_TYPE;
        var registries = initRegistry(modid);

        if (!registries.containsKey(registryKey))
        {
            var i = DeferredRegister.create((ResourceKey)registryKey, modid);
            i.register(EVENT_BUS);
            registries.put(registryKey, i);
        }
        var registry = (DeferredRegister<BlockEntityType<?>>) registries.get(registryKey);
        return registry.register(id, type);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends BlockEntity> BlockEntityType<T> registerBlockEntity(BlockEntityData<T> data, Supplier<Block>... blocks)
    {
        return BlockEntityType.Builder.of(data::create, Arrays.stream(blocks).map(Supplier::get).toArray(Block[]::new)).build(null);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends EntityType<?>> Supplier<T> registerEntityType(String modid, String id, Supplier<T> entity)
    {
        DeferredRegister<T> registry;
        var registries = initRegistry(modid);

        if (!registries.containsKey(Registries.ENTITY_TYPE))
        {
            var i = DeferredRegister.create(Registries.ENTITY_TYPE, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.ENTITY_TYPE, i);
        }
        registry = (DeferredRegister<T>) registries.get(Registries.ENTITY_TYPE);
        registry.register(id, entity);
        return () -> (T) BuiltInRegistries.ENTITY_TYPE.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Holder<SoundEvent> registerSoundReference(String modid, String id)
    {
        DeferredRegister<SoundEvent> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.SOUND_EVENT))
        {
            var i = DeferredRegister.create((ResourceKey) Registries.SOUND_EVENT, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.SOUND_EVENT, i);
        }
        registry = (DeferredRegister<SoundEvent>) registries.get(Registries.SOUND_EVENT);
        var register = registry.register(id, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(modid, id)));
        return register;
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> Supplier<DataComponentType<T>> registerComponentType(String modid, String id, UnaryOperator<DataComponentType.Builder<T>> component_unary)
    {
        DeferredRegister.DataComponents registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.DATA_COMPONENT_TYPE))
        {
            registry = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, modid);
            registry.register(EVENT_BUS);
            registries.put(Registries.DATA_COMPONENT_TYPE, registry);
        }
        else
        {
            registry = (DeferredRegister.DataComponents) registries.get(Registries.DATA_COMPONENT_TYPE);
        }
        registry.registerComponentType(id, component_unary);
        return () -> (DataComponentType<T>) BuiltInRegistries.DATA_COMPONENT_TYPE.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public Supplier<SimpleParticleType> registerParticleType(String modid, String id)
    {
        DeferredRegister<ParticleType<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.PARTICLE_TYPE))
        {
            var i = DeferredRegister.create(Registries.PARTICLE_TYPE, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.PARTICLE_TYPE, i);
        }
        registry = (DeferredRegister<ParticleType<?>>) registries.get(Registries.PARTICLE_TYPE);
        registry.register(id, () -> new SimpleParticleType(false));
        return () -> (SimpleParticleType) BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends ParticleOptions> Supplier<ParticleType<T>> registerParticleType(String modid, String id, Function<ParticleType<T>, MapCodec<T>> codec, Function<ParticleType<T>, StreamCodec<? super RegistryFriendlyByteBuf, T>> streamcodec)
    {
        DeferredRegister<ParticleType<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.PARTICLE_TYPE))
        {
            var i = DeferredRegister.create(Registries.PARTICLE_TYPE, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.PARTICLE_TYPE, i);
        }
        registry = (DeferredRegister<ParticleType<?>>) registries.get(Registries.PARTICLE_TYPE);

        registry.register(id, () -> new ParticleType<T>(false)
        {
            @NotNull public MapCodec<T> codec() { return codec.apply(this); }
            @NotNull public StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec() { return streamcodec.apply(this); }
        });

        return () -> (ParticleType<T>) BuiltInRegistries.PARTICLE_TYPE.get(ResourceLocation.fromNamespaceAndPath(modid, id));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends AbstractContainerMenu> Supplier<MenuType<T>> registerMenu(String modid, String id, MenuData<T> data)
    {
        DeferredRegister<MenuType<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.MENU))
        {
            var i = DeferredRegister.create(Registries.MENU, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.MENU, i);
        }
        registry = (DeferredRegister<MenuType<?>>) registries.get(Registries.MENU);
        return registry.register(id, () -> new MenuType<>(data::create, FeatureFlags.DEFAULT_FLAGS));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends Recipe<?>> Supplier<RecipeType<T>> registerRecipeType(String modid, String id)
    {
        DeferredRegister<RecipeType<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.RECIPE_TYPE))
        {
            var i = DeferredRegister.create(Registries.RECIPE_TYPE, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.RECIPE_TYPE, i);
        }
        registry = (DeferredRegister<RecipeType<?>>) registries.get(Registries.RECIPE_TYPE);

        return registry.register(id, () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(modid, id)));
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends Recipe<?>> Supplier<RecipeSerializer<T>> registerRecipeSerializer(String modid, String id, RecipeSerializer<T> serializer)
    {
        DeferredRegister<RecipeSerializer<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.RECIPE_SERIALIZER))
        {
            var i = DeferredRegister.create(Registries.RECIPE_SERIALIZER, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.RECIPE_SERIALIZER, i);
        }
        registry = (DeferredRegister<RecipeSerializer<?>>) registries.get(Registries.RECIPE_SERIALIZER);

        return registry.register(id, () -> serializer);
    }

    @Override
    @SuppressWarnings({"unchecked"})
    public <T extends CriterionTrigger<?>> Supplier<T> registerAdvCriteria(String modid, String id, Supplier<T> criterion)
    {
        DeferredRegister<CriterionTrigger<?>> registry;
        var registries = initRegistry(modid);
        if (!registries.containsKey(Registries.TRIGGER_TYPE))
        {
            var i = DeferredRegister.create(Registries.TRIGGER_TYPE, modid);
            i.register(EVENT_BUS);
            registries.put(Registries.TRIGGER_TYPE, i);
        }
        registry = (DeferredRegister<CriterionTrigger<?>>) registries.get(Registries.TRIGGER_TYPE);

        return registry.register(id, criterion);
    }

    @Override
    public void addToCreativeTab(VectorItemTab tab, VectorItemTab.AddMode mode)
    {
        EVENT_BUS.addListener((BuildCreativeModeTabContentsEvent event) ->
        {
            Pair<ResourceKey<CreativeModeTab>, List<Pair<ItemStack, ItemStack>>> list = tab.unpack(mode);

            ResourceKey<CreativeModeTab> key = list.getFirst();
            List<Pair<ItemStack, ItemStack>> pairs = list.getSecond();

            if (event.getTabKey().equals(key))
            {
                for (Pair<ItemStack, ItemStack> entry : pairs)
                {
                    if (mode == VectorItemTab.AddMode.BEFORE)
                    {
                        event.insertBefore(
                                entry.getFirst(),
                                entry.getSecond(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                        );
                    }
                    else
                    {
                        event.insertBefore(
                                entry.getFirst(),
                                entry.getSecond(),
                                CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS
                        );
                    }
                }
            }
        });
    }
}
