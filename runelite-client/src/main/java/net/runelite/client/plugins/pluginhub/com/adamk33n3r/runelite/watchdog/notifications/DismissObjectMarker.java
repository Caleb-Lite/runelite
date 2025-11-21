package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPlugin;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@Accessors(chain = true)
public class DismissObjectMarker extends Notification {
    private String dismissId;

    @Override
    protected void fireImpl(String[] triggerValues) {
        WatchdogPlugin.getInstance().getObjectMarkerManager().hideObjectMarkerById(this.dismissId);
    }
}
