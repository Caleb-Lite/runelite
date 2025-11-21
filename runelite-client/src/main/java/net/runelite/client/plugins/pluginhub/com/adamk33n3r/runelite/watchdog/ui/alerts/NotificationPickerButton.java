package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.alerts;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPlugin;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.Icons;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.util.function.Consumer;

public class NotificationPickerButton extends JPanel {
    public NotificationPickerButton(Consumer<String> callback) {
        JButton picker = PanelUtils.createActionButton(Icons.PICKER, Icons.PICKER_HOVER, "Pick a recent notification", (btn, mod) -> WatchdogPlugin.getInstance().getPanel().pickNotification(callback));
        this.add(picker);
    }
}
