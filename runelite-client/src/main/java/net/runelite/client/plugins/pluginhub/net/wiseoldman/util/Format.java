package net.runelite.client.plugins.pluginhub.net.wiseoldman.util;

import net.runelite.client.util.QuantityFormatter;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Format
{
    public static String formatNumber(long num)
    {
        if ((num < 10000 && num > -10000))
        {
            return QuantityFormatter.formatNumber(num);
        }

        DecimalFormat df = new DecimalFormat();
        df.setGroupingUsed(false);
        df.setRoundingMode(RoundingMode.FLOOR);
        df.setMaximumFractionDigits(2);

        // < 10 million
        if (num < 10_000_000 && num > -10_000_000)
        {
            df.setMaximumFractionDigits(0);
            return df.format(num / 1000.0) + "k";
        }

        // < 1 billion
        if (num < 1_000_000_000 && num > -1_000_000_000)
        {
            return df.format( num / 1_000_000.0) + "m";
        }

        return df.format(num / 1_000_000_000.0) + "b";
    }

    public static String formatNumber(double num)
    {
        if ((num < 10000 && num > -10000))
        {
            return String.format("%.0f", num);
        }

        DecimalFormat df = new DecimalFormat();
        df.setRoundingMode(RoundingMode.FLOOR);
        df.setMaximumFractionDigits(2);

        return df.format(num / 1000.0) + "k";
    }

    public static String formatDate(String date, boolean relative)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm");
        ZoneId localZone = ZoneId.systemDefault();
        ZonedDateTime updatedAt = Instant.parse(date).atZone(localZone);

        if (relative)
        {
            String lastUpdated = "";
            ZonedDateTime now = Instant.now().atZone(localZone);
            long difference = Duration.between(updatedAt, now).toHours();

            if (difference == 0)
            {
                return "less than 1 hour ago";
            }

            long days = difference / 24;
            long hours = difference % 24;

            String dayUnit = days > 1 ? " days, " : " day, ";
            String hourUnit = hours > 1 ? " hours ago" : " hour ago";

            lastUpdated += days > 0 ? days + dayUnit : "";
            lastUpdated += hours > 0 ? hours + hourUnit : "";

            return lastUpdated;
        }
        else
        {
            return formatter.format(updatedAt);
        }
    }
}
