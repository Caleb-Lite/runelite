package net.runelite.client.plugins.pluginhub.com.prison_sentence.sources;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.PrisonSentencePlugin;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.PrisonType;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.ui.Translators;
import net.runelite.api.events.VarbitChanged;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Current {

    PrisonSentencePlugin myPlugin;

    Map<Integer, Integer> myStashedKC = new HashMap<>();

    List<Runnable> mySubscribers = new ArrayList<>();

    public Current(PrisonSentencePlugin aPlugin)
    {
        myPlugin = aPlugin;

        for (PrisonType prison : PrisonType.values())
        {
            Integer varpID = Translators.VarpIdFromPrisonType(prison);
            myStashedKC.put(varpID, -1);
        }
    }

    public void LoadInitialValues()
    {
        for (PrisonType prison : PrisonType.values())
        {
            Integer varpID = Translators.VarpIdFromPrisonType(prison);
            myStashedKC.put(varpID, myPlugin.client.getVarpValue(varpID));
        }
    }

    public void OnChange(Runnable aRunnable)
    {
        mySubscribers.add(aRunnable);
    }

    public int GetCurrentAmount(PrisonType aType)
    {
        return myStashedKC.get(Translators.VarpIdFromPrisonType(aType));
    }

    public boolean IsReady(PrisonType aPrisonType)
    {
        return myStashedKC.get(Translators.VarpIdFromPrisonType(aPrisonType)) != -1;
    }

    public void onVarbitChanged(VarbitChanged varbitChanged)
    {
        Integer id = varbitChanged.getVarpId();

        if (!myStashedKC.keySet().contains(id))
            return;

        myStashedKC.put(id, varbitChanged.getValue());

        for (Runnable sub : mySubscribers)
            sub.run();
    }
}
