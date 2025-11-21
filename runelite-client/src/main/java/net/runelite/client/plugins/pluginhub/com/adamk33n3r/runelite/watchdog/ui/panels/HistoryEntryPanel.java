package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.Util;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.alerts.Alert;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications.MessageNotification;

import net.runelite.client.ui.DynamicGridLayout;

import lombok.Getter;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class HistoryEntryPanel extends JPanel {
    @Getter
    private final Alert alert;

    public HistoryEntryPanel(Alert alert, String[] triggerValues) {
        super(new DynamicGridLayout(0, 1, 3, 3));
        this.alert = alert;
        this.setBorder(new EtchedBorder());

        JLabel alertType = new JLabel(alert.getType().getName());
        this.add(alertType);
        JLabel alertName = new JLabel(alert.getName());
        this.add(alertName);
        alert.getNotifications().stream()
            .filter(notification -> notification instanceof MessageNotification)
            .map(notification -> (MessageNotification) notification)
            .forEach(notification -> {
                String message = Util.processTriggerValues(notification.getMessage(), triggerValues);
                JTextArea wrappingTextArea = new JTextArea(notification.getType().getName() + ": " + message);
                wrappingTextArea.setLineWrap(true);
                wrappingTextArea.setWrapStyleWord(true);
                wrappingTextArea.setOpaque(false);
                wrappingTextArea.setEditable(false);
                wrappingTextArea.setFocusable(false);
                this.add(wrappingTextArea);
            });
        String formattedTime = DateTimeFormatter.ofPattern("HH:mm:ss").withZone(ZoneId.systemDefault()).format(Instant.now());
        this.add(new JLabel(formattedTime));
    }
}

/**
 *  A panel that implements the Scrollable interface. This class allows you
 *  to customize the scrollable features by using newly provided setter methods
 *  so you don't have to extend this class every time.
 *
 *  Scrollable amounts can be specifed as a percentage of the viewport size or
 *  as an actual pixel value. The amount can be changed for both unit and block
 *  scrolling for both horizontal and vertical scrollbars.
 *
 *  The Scrollable interface only provides a boolean value for determining whether
 *  or not the viewport size (width or height) should be used by the scrollpane
 *  when determining if scrollbars should be made visible. This class supports the
 *  concept of dynamically changing this value based on the size of the viewport.
 *  In this case the viewport size will only be used when it is larger than the
 *  panels size. This has the effect of ensuring the viewport is always full as
 *  components added to the panel will be size to fill the area available,
 *  based on the rules of the applicable layout manager of course.
 */