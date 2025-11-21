package net.runelite.client.plugins.pluginhub.com.prison_sentence.sources;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.TimeUtils;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.PrisonType;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.TimePeriod;

import java.time.Instant;

public class Manual implements ISource
{
    Instant myStartTime;
    TimePeriod myPeriod;
    int myStartAmount;

    public Manual(TimePeriod aPeriod, Instant aStartTime, int aStartAmount)
    {
        myStartTime = aStartTime;
        myPeriod = aPeriod;
        myStartAmount = aStartAmount;
    }

    @Override
    public boolean IsReady()
    {
        return true;
    }

    @Override
    public int GetAmountAtStartOfPeriod(PrisonType aType, int aTarget)
    {
        switch (myPeriod)
        {
            case DAILY:
                return myStartAmount + aTarget * TimeUtils.DaysSince(myStartTime);
            case WEEKLY:
                return myStartAmount + aTarget * TimeUtils.WeeksSince(myStartTime);
            case MONTHLY:
                return myStartAmount + aTarget * TimeUtils.MonthsSince(myStartTime);
        }

        assert false;
        return -1;
    }
}
