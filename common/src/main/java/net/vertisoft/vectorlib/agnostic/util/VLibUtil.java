package net.vertisoft.vectorlib.agnostic.util;

/** A small utility class for various things. */
public class VLibUtil
{
    /** Capitalizes the first letter of the string. */
    public static String capital(String string)
    {
        String pre = string.substring(0, 1);
        String suf = string.substring(1);
        pre = pre.toUpperCase();
        return pre + suf;
    }
}
