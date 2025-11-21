package net.runelite.client.plugins.pluginhub.com.prison_sentence;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

public class TimeUtils
{
    public static int DaysSince(Instant aInstant)
    {
        try
        {
            return (int)ChronoUnit.DAYS.between(aInstant, Instant.now());
        }
        catch (Exception e)
        {
            return  0;
        }
    }
    public static int WeeksSince(Instant aInstant)
    {
        return DaysSince(aInstant) / 7;
    }
    public static int MonthsSince(Instant aInstant)
    {
        try
        {
            LocalDate start = LocalDate.ofInstant(aInstant, ZoneId.systemDefault());
            LocalDate now = LocalDate.now();

            return (int)ChronoUnit.MONTHS.between(start, now);
        }
        catch (Exception e)
        {
            return  0;
        }
    }
}
