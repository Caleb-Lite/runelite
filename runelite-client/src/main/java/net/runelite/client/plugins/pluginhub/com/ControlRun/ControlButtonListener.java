package net.runelite.client.plugins.pluginhub.com.ControlRun;

import javax.inject.Inject;
import java.awt.event.KeyEvent;
import net.runelite.api.*;
import net.runelite.client.input.KeyListener;


public class ControlButtonListener implements KeyListener
{
    @Inject
    private Client client;

    int runState;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 17)
        {
            runState = client.getVarpValue(173);
            if (runState == 1)
                e.consume();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

