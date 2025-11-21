package net.runelite.client.plugins.pluginhub.com.crosshaircursor;

import lombok.Setter;
import net.runelite.client.config.Keybind;
import net.runelite.client.input.KeyListener;

import javax.inject.Inject;
import java.awt.event.KeyEvent;

public class CrosshairHotkeyListener implements KeyListener
{
    @Inject
    private CrosshairCursorConfig config;

    @Setter
    private Runnable toggleCallback;

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent event)
    {
        Keybind keybind = config.toggleKey();
        if (keybind.matches(event) && toggleCallback != null)
        {
            toggleCallback.run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
