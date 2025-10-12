package net.artyrian.frontiers.misc;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.Calendar;

public class FrontiersEventSystem
{
    // All event types
    public final boolean IS_CHRISTMAS;
    public final boolean IS_APRIL_FOOLS;
    public final boolean IS_HALLOWEEN;
    public final boolean IS_XENS_BDAY;
    public final boolean IS_WES_BDAY;
    public final boolean IS_HECCO_BDAY;
    public final boolean IS_THE_WORST_DAY_EVER;	// Artyrian's bday (EW)

    public FrontiersEventSystem()
    {
        LocalDate localDate = LocalDate.now();
        int day = localDate.getDayOfMonth();
        Month month = localDate.getMonth();

        this.IS_CHRISTMAS = this.is_halloween(day, month);
        this.IS_HALLOWEEN = this.is_halloween(day, month);
        this.IS_APRIL_FOOLS = this.is_aprilFools(day, month);

        this.IS_XENS_BDAY = (day == 22 && month == Month.SEPTEMBER);
        this.IS_WES_BDAY = (day == 15 && month == Month.NOVEMBER);
        this.IS_HECCO_BDAY = (day == 1 && month == Month.OCTOBER);
        this.IS_THE_WORST_DAY_EVER = (day == 30 && month == Month.AUGUST);
    }

    private boolean is_aprilFools(int day, Month month)
    {
        return month == Month.APRIL && day == 1;
    }

    /** 12 days of christmas lmao */
    private boolean is_christmas(int day, Month month)
    {
        return month == Month.DECEMBER && day >= 13 && day <= 25;
    }

    private boolean is_halloween(int day, Month month)
    {
        return month == Month.OCTOBER && day >= 20 || month == Month.NOVEMBER && day <= 3;
    }
}
