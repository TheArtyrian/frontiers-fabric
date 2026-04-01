package net.artyrian.frontiers.definition.menu.curse;

import net.artyrian.frontiers.Frontiers;
import net.minecraft.Util;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;

public class CursedNames
{
    private static final CursedNames INSTANCE = new CursedNames();
    private final RandomSource random = RandomSource.create();

    private static final ResourceLocation VECTRESS = Frontiers.id("vectress");
    private static final Style STYLE = Style.EMPTY.withFont(VECTRESS);
    private static final String[] woe = new String[]
    {
            "what",
            "woah",
            "shiny",
            "angry",
            "lame",
            "gotemgg",
            "alex",
            "steve",
            "arty",
            "fear",
            "panic",
            "calm",
            "thirty",
            "unreal",
            "terra",
            "c418",
            "curse",
            "purify",
            "qwerty",
            "asdfg",
            "lmaoo",
            "lwjgl",
            "30 lvls",
            "12345",
            "spooky",
            "tablet",
            "power",
            "thegame"
    };

    private CursedNames() {}

    public MutableComponent goMyCurse()
    {
        String curse = Util.getRandom(woe, this.random);
        return Component.literal(curse).withStyle(STYLE);
    }

    public void seedUp(long seed, int shuffles) {
        this.random.setSeed(seed);
        if (shuffles > 0) for (int i = 0; i < shuffles; i++) this.random.nextInt();
    }

    public static CursedNames get() {
        return INSTANCE;
    }
}
