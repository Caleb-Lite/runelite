package net.runelite.client.plugins.pluginhub.com.prison_sentence.sources;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.PrisonType;

public interface ISource {

    boolean IsReady();
    int GetAmountAtStartOfPeriod(PrisonType aType, int aTarget);
}
