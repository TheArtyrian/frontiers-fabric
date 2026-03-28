package net.vertisoft.vectorlib.agnostic.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.coordinates.BlockPosArgument;
import net.minecraft.commands.arguments.coordinates.Vec3Argument;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.phys.Vec3;
import net.vertisoft.vectorlib.VectorLib;
import net.vertisoft.vectorlib.agnostic.networking.eventsync.VectorEventSync;
import net.vertisoft.vectorlib.mixin_intf.event.VectorLevelAccess;

import java.lang.reflect.Field;

/** A debugging class meant for calling level events at specific locations. */
public class VLEventCommand
{
    private static final SimpleCommandExceptionType ERROR_FAILED = new SimpleCommandExceptionType(Component.translatable("commands.vectorlib.event.failed"));

    private static final String VECTORLIB = "vectorlib";
    private static final String VANILLA = "vanilla";

    public VLEventCommand() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher)
    {
        dispatcher.register(
            // VL_EVENT base (only dev environment or
            Commands.literal("vl_event").requires((stack) -> stack.hasPermission(Commands.LEVEL_GAMEMASTERS) && VectorLib.CONFIG.doVectorCommands())
                // VectorEventSync
                .then(
                    Commands.literal(VECTORLIB)
                    .then(
                        // Local Events
                        Commands.literal("local")
                        .then(Commands.argument("namespace", StringArgumentType.word())
                            .then(Commands.argument("event_name", StringArgumentType.word())
                                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                    .then(Commands.argument("data", IntegerArgumentType.integer())
                                        .executes((stack) -> executeVLLocal(
                                                stack,
                                                StringArgumentType.getString(stack, "namespace"),
                                                StringArgumentType.getString(stack, "event_name"),
                                                BlockPosArgument.getLoadedBlockPos(stack, "pos"),
                                                IntegerArgumentType.getInteger(stack, "data")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                    .then(
                        // Dual Events
                        Commands.literal("dual")
                        .then(Commands.argument("namespace", StringArgumentType.word())
                            .then(Commands.argument("event_name", StringArgumentType.word())
                                .then(Commands.argument("primary_pos", Vec3Argument.vec3())
                                    .then(Commands.argument("secondary_pos", Vec3Argument.vec3())
                                        .then(Commands.argument("data", IntegerArgumentType.integer())
                                            .executes((stack) -> executeVLDual(
                                                    stack,
                                                    StringArgumentType.getString(stack, "namespace"),
                                                    StringArgumentType.getString(stack, "event_name"),
                                                    Vec3Argument.getVec3(stack, "primary_pos"),
                                                    Vec3Argument.getVec3(stack, "secondary_pos"),
                                                    IntegerArgumentType.getInteger(stack, "data")
                                                )
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                    .then(
                        // Entity Events
                        Commands.literal("entity")
                        .then(Commands.argument("namespace", StringArgumentType.word())
                            .then(Commands.argument("event_name", StringArgumentType.word())
                                .then(Commands.argument("entity", EntityArgument.entity())
                                    .then(Commands.argument("data", IntegerArgumentType.integer())
                                        .executes((stack) -> executeVLEntity(
                                                stack,
                                                StringArgumentType.getString(stack, "namespace"),
                                                StringArgumentType.getString(stack, "event_name"),
                                                EntityArgument.getEntity(stack, "entity"),
                                                IntegerArgumentType.getInteger(stack, "data")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                    .then(
                        // Global Events
                        Commands.literal("global")
                        .then(Commands.argument("namespace", StringArgumentType.word())
                            .then(Commands.argument("event_name", StringArgumentType.word())
                                .then(Commands.argument("pos", BlockPosArgument.blockPos())
                                    .then(Commands.argument("data", IntegerArgumentType.integer())
                                        .executes((stack) -> executeVLGlobal(
                                                stack,
                                                StringArgumentType.getString(stack, "namespace"),
                                                StringArgumentType.getString(stack, "event_name"),
                                                BlockPosArgument.getLoadedBlockPos(stack, "pos"),
                                                IntegerArgumentType.getInteger(stack, "data")
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
                // Vanilla Level Events
                .then(
                    Commands.literal(VANILLA)
                    .then(Commands.argument("event_id", IntegerArgumentType.integer())
                        .then(Commands.argument("pos", BlockPosArgument.blockPos())
                            .then(Commands.argument("data", IntegerArgumentType.integer())
                                .executes((stack) -> executeVanillaAtSet(
                                        stack,
                                        IntegerArgumentType.getInteger(stack, "event_id"),
                                        BlockPosArgument.getLoadedBlockPos(stack, "pos"),
                                        IntegerArgumentType.getInteger(stack, "data"),
                                        false
                                    )
                                )
                                .then(Commands.literal("global")
                                    .executes((stack) -> executeVanillaAtSet(
                                            stack,
                                            IntegerArgumentType.getInteger(stack, "event_id"),
                                            BlockPosArgument.getLoadedBlockPos(stack, "pos"),
                                            IntegerArgumentType.getInteger(stack, "data"),
                                            true
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
        );
    }

    private static int executeVLLocal(CommandContext<CommandSourceStack> stack, String namespace, String id, BlockPos pos, int data)
    {
        if (VectorEventSync.Local.EVENT_MAPDEX.containsKey(namespace))
        {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, id);
            if (VectorEventSync.Local.MAP_FREEZER.containsKey(location))
            {
                int ord = VectorEventSync.Local.MAP_FREEZER.get(location);
                VectorEventSync.Local.fireEventRaw(null, (VectorLevelAccess)stack.getSource().getLevel(), pos, namespace, ord, data);

                stack.getSource().sendSuccess(
                        () -> Component.translatable(
                                "commands.vectorlib.event.eventsync.local_success",
                                String.format("%1s, %2s, %3s", pos.getX(), pos.getY(), pos.getZ())
                        ),
                        true);
                return 1;
            }
            else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_action", "Local", id));
        }
        else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_namespace", "Local"));
        return 0;
    }

    private static int executeVLDual(CommandContext<CommandSourceStack> stack, String namespace, String id, Vec3 pos1, Vec3 pos2, int data)
    {
        if (VectorEventSync.Dual.EVENT_MAPDEX.containsKey(namespace))
        {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, id);
            if (VectorEventSync.Dual.MAP_FREEZER.containsKey(location))
            {
                int ord = VectorEventSync.Dual.MAP_FREEZER.get(location);
                VectorEventSync.Dual.fireEventRaw(null, (VectorLevelAccess)stack.getSource().getLevel(), pos1, pos2, namespace, ord, data);

                stack.getSource().sendSuccess(
                        () -> Component.translatable(
                                "commands.vectorlib.event.eventsync.dual_success",
                                String.format("%1s, %2s, %3s", pos1.x(), pos1.y(), pos1.z()),
                                String.format("%1s, %2s, %3s", pos2.x(), pos2.y(), pos2.z())
                        ),
                        true);
                return 1;
            }
            else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_action", "Dual", id));
        }
        else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_namespace", "Dual"));
        return 0;
    }

    private static int executeVLEntity(CommandContext<CommandSourceStack> stack, String namespace, String id, Entity entity, int data)
    {
        if (VectorEventSync.Entity.EVENT_MAPDEX.containsKey(namespace))
        {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, id);
            if (VectorEventSync.Entity.MAP_FREEZER.containsKey(location))
            {
                int ord = VectorEventSync.Entity.MAP_FREEZER.get(location);
                VectorEventSync.Entity.fireEventRaw(null, (VectorLevelAccess)stack.getSource().getLevel(), entity, namespace, ord, data);

                stack.getSource().sendSuccess(
                        () -> Component.translatable(
                                "commands.vectorlib.event.eventsync.entity_success",
                                entity.getDisplayName()
                        ),
                        true);
                return 1;
            }
            else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_action", "Entity", id));
        }
        else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_namespace", "Entity"));
        return 0;
    }

    private static int executeVLGlobal(CommandContext<CommandSourceStack> stack, String namespace, String id, BlockPos pos, int data)
    {
        if (VectorEventSync.Global.EVENT_MAPDEX.containsKey(namespace))
        {
            ResourceLocation location = ResourceLocation.fromNamespaceAndPath(namespace, id);
            if (VectorEventSync.Global.MAP_FREEZER.containsKey(location))
            {
                int ord = VectorEventSync.Global.MAP_FREEZER.get(location);
                VectorEventSync.Global.fireEventRaw(null, (VectorLevelAccess)stack.getSource().getLevel(), pos, namespace, ord, data);

                stack.getSource().sendSuccess(
                        () -> Component.translatable(
                                "commands.vectorlib.event.eventsync.global_success",
                                String.format("%1s, %2s, %3s", pos.getX(), pos.getY(), pos.getZ())
                        ),
                        true);
                return 1;
            }
            else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_action", "Global", id));
        }
        else stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.eventsync_no_namespace", "Global"));
        return 0;
    }

    private static int executeVanillaAtSet(CommandContext<CommandSourceStack> stack, int event, BlockPos pos, int data, boolean global)
    {
        try
        {
            for (Field field : LevelEvent.class.getFields())
            {
                if (field.getGenericType().equals(Integer.TYPE))
                {
                    var integer = (Integer)field.get(null);
                    if (integer == event)
                    {
                        if (global) stack.getSource().getLevel().globalLevelEvent(event, pos, data);
                        else stack.getSource().getLevel().levelEvent(event, pos, data);
                        stack.getSource().sendSuccess(
                                () -> Component.translatable(
                                        "commands.vectorlib.event.vanilla.success",
                                        event,
                                        String.format("%1s, %2s, %3s", pos.getX(), pos.getY(), pos.getZ())
                                ),
                                true);
                        return 1;
                    }
                }
            }
            stack.getSource().sendFailure(Component.translatable("commands.vectorlib.event.vanilla_no_exist"));
            return 0;
        }
        catch (IllegalAccessException illegal)
        {
            stack.getSource().sendFailure(Component.translatable("command.failed"));
            VectorLib.LOGGER.error("Something happened while trying to check existing level events, see below:", illegal);
        }
        return 0;
    }
}
