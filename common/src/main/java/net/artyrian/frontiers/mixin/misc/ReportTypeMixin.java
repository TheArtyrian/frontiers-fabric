package net.artyrian.frontiers.mixin.misc;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.artyrian.frontiers.Frontiers;
import net.minecraft.ReportType;
import net.minecraft.Util;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.util.List;

// Is this considered bad modding practice? Come now, it's funny as hell
@Debug(export = true)
@Mixin(ReportType.class)
public abstract class ReportTypeMixin
{
    @Shadow @Final private List<String> nuggets;
    @Shadow @Final private String header;

    @Shadow @Final public static ReportType TEST;
    @Shadow @Final public static ReportType CRASH;
    @Shadow @Final public static ReportType CHUNK_IO_ERROR;
    @Shadow @Final public static ReportType NETWORK_PROTOCOL_ERROR;
    @Shadow @Final public static ReportType PROFILE;

    @Unique private static final List<String> FRONTIERS_CRASH_MESSAGES = List.of(
            "Guess you could say...that was your final Frontier. *snicker*",
            "Maybe I shouldn't have suppressed experimental warnings...",
            "Proooobably shouldn't report this log to Mojang.",
            "Did you modify his answer?",
            "Does this mean no more Java mods or custom skins?",
            "...did you think I wasn't capable of using mixins here? lol",
            "USE ALL THE MIXINS, JERRY",
            "I thought...I thought I was [title card].",
            "Oh, good. Game engine guts.",
            "LWJGL my beloved",
            "I tried, okay? Cut me a little slack.",
            "That's probably going to leave a mark.",
            "Hey, on the bright side, this is an excuse to go outside now!",
            "Did you know mitochondria is the powerhouse of the cell?",
            "Ten thousand?! MY PC",
            "The consequences of the fill command with TNT",
            "Your crash WILL be reported, but first I must QUICKLY tell y'all that...",
            "Sorry, no free cake for you!",
            "Whoops! You have to put the CD in your computer!",
            "Famous Frontiers mod developer Artyrian was subsequently taken out back and put down",
            "Reality is often disappointing, isn't it?"
    );
    @Unique private static final List<String> FRONTIERS_CHUNK_MESSAGES = List.of(
            "Dang it, I thought we actually removed Herobrine this time!",
            "The .mca format is perfect and flawless",
            "Sorry, I was hungry",
            "Creepypasta moment",
            "I bet it was one of those worldgen overhaul mods you probably have installed. Tsk.",
            "You think there's an anthropomorphic goat down there?",
            "...I don't suppose a hug will make up for this, will it?",
            "Cripes."
    );
    @Unique private static final List<String> FRONTIERS_PROTOCOL_MESSAGES = List.of(
            "There's an axe in the server!",
            "Great, now we gotta contact tech support.",
            "Could this be the work of Skynet?!",
            "You made sure your modem is hooked up, right?",
            "Is someone using the phone line right now? That might be the issue.",
            "MOM, GET OFF THE PHONE!!!",
            "Where are you, a house made of lead?",
            "Have your checked your VPN?",
            "You DID make sure you forwarded that port, right?",
            "The only thing that could make this worse is your IP address getting leaked!",
            "Steve Breaks the Internet"
    );
    @Unique private static final List<String> FRONTIERS_PROFILER_MESSAGES = List.of(
            "Because you can never have enough optimization in Java",
            "If you have OptiFine installed, then this won't even make a difference.",
            "C# better.",
            "At least this is easier than profiling C++. Probably",
            "Whoa, and a profiler? This thing's deluxe!",
            "Because writing more performant code isn't an option!"
    );
    @Unique private static final List<String> FRONTIERS_TEST_MESSAGES = List.of(
            "Hey ma, I'm in a test log!",
            "A widdle baby crash!",
            "You have not crashed! Click Here to crash!",
            "Bet I scared you there, huh?",
            "Did I jumpscare you? Yeah, I bet I did."
    );

    @ModifyReturnValue(method = "getErrorComment", at = @At(value = "RETURN", ordinal = 0))
    private String frontiers$addNewCrashTextBecauseItsFunny(String original)
    {
        String header = this.header;
        List<String> newModdedList = new java.util.ArrayList<>(List.copyOf(this.nuggets));

        String test = TEST.header();
        String crash = CRASH.header();
        String chunk = CHUNK_IO_ERROR.header();
        String protocol = NETWORK_PROTOCOL_ERROR.header();
        String profiler = PROFILE.header();

        if (header.equals(crash)) newModdedList.addAll(FRONTIERS_CRASH_MESSAGES);
        else if (header.equals(test)) newModdedList.addAll(FRONTIERS_TEST_MESSAGES);
        else if (header.equals(chunk)) newModdedList.addAll(FRONTIERS_CHUNK_MESSAGES);
        else if (header.equals(protocol)) newModdedList.addAll(FRONTIERS_PROTOCOL_MESSAGES);
        else if (header.equals(profiler)) newModdedList.addAll(FRONTIERS_PROFILER_MESSAGES);

        return newModdedList.get((int)(Util.getNanos() % (long)newModdedList.size()));
    }
}
