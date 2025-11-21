package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.alerts.Alert;

public interface INotification {
    Alert getAlert();
    void setAlert(Alert alert);
    void fire(String[] triggerValues);
    void setDefaults();
}
