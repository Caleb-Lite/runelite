package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.notifications.panels;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications.RequestFocus;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.NotificationsPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import javax.swing.JCheckBox;

public class RequestFocusNotificationPanel extends NotificationPanel {
    public RequestFocusNotificationPanel(RequestFocus notification, NotificationsPanel parentPanel, Runnable onChangeListener, PanelUtils.OnRemove onRemove) {
        super(notification, parentPanel, onChangeListener, onRemove);

        JCheckBox force = PanelUtils.createCheckbox(
            "Force",
            "Force window focus (bring to foreground)",
            notification.isForceFocus(),
            (val) -> {
                notification.setForceFocus(val);
                onChangeListener.run();
            }
        );
        this.settings.add(force);
    }
}
