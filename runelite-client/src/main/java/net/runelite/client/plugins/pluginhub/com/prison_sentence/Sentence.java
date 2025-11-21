package net.runelite.client.plugins.pluginhub.com.prison_sentence;

import net.runelite.client.plugins.pluginhub.com.prison_sentence.enums.PrisonType;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.sources.Current;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.sources.ISource;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.ui.SentenceInfoBox;
import net.runelite.client.plugins.pluginhub.com.prison_sentence.ui.Translators;
import net.runelite.client.util.ColorUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sentence {

    Boolean myShutDownRequested = false;

    ISource mySource;
    Current myCurrent;

    PrisonType myPrisonType;
    PrisonSentencePlugin myPlugin;
    SentenceInfoBox myInfobox = null;

    Timer mySetupTimer;

    int myProgress = -1;
    int myTarget;

    public Sentence(ISource aSource, Current aCurrent, PrisonType aPrisonType, PrisonSentencePlugin aPlugin, int aTarget)
    {
        mySource = aSource;
        myCurrent = aCurrent;

        myTarget = aTarget;

        myPrisonType = aPrisonType;
        myPlugin = aPlugin;

        mySetupTimer = new Timer(500, (ev) -> Setup());
        mySetupTimer.setRepeats(true);

        mySetupTimer.start();
    }

    public void Shutdown()
    {
        synchronized (myShutDownRequested)
        {
            if (myShutDownRequested)
                return;

            myShutDownRequested = true;
            TearDown();
        }
    }

    public int GetProgress()
    {
        return myProgress;
    }

    public int GetTarget()
    {
        return myTarget;
    }

    public String ToTooltip()
    {
        return "Time to get back to " + ColorUtil.wrapWithColorTag(Translators.FriendlyName(myPrisonType), Color.ORANGE);
    }

    void Setup()
    {
        assert myInfobox == null;

        synchronized (myShutDownRequested)
        {
            if (myShutDownRequested)
            {
                mySetupTimer.stop();
                return;
            }

            if (!myCurrent.IsReady(myPrisonType))
                return;

            if (!mySource.IsReady())
                return;

            mySetupTimer.stop();

            myProgress = myCurrent.GetCurrentAmount(myPrisonType) - mySource.GetAmountAtStartOfPeriod(myPrisonType, myTarget);

            if (myProgress >= myTarget)
            {
                Shutdown();
                return;
            }

            myInfobox = new SentenceInfoBox(this, myPrisonType, myPlugin);

            myPlugin.infoBoxManager.addInfoBox(myInfobox);

            myCurrent.OnChange(() -> {
                myProgress = myCurrent.GetCurrentAmount(myPrisonType) - mySource.GetAmountAtStartOfPeriod(myPrisonType, myTarget);

                if (myProgress >= myTarget)
                    Shutdown();
            });
        }
    }

    void TearDown()
    {
        if (myInfobox != null)
        {
            myPlugin.infoBoxManager.removeInfoBox(myInfobox);
        }
    }
}
