package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.notifications.panels;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.LengthLimitFilter;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.SimpleDocumentListener;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications.MessageNotification;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.FlatTextArea;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.NotificationsPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import javax.swing.text.AbstractDocument;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class MessageNotificationPanel extends NotificationPanel {
    public MessageNotificationPanel(MessageNotification notification, NotificationsPanel parentPanel, Runnable onChangeListener, PanelUtils.OnRemove onRemove) {
        this(notification, false, parentPanel, onChangeListener, onRemove);
    }

    public MessageNotificationPanel(MessageNotification notification, boolean supportsFormattingTags, NotificationsPanel parentPanel, Runnable onChangeListener, PanelUtils.OnRemove onRemove) {
        super(notification, parentPanel, onChangeListener, onRemove);

        FlatTextArea flatTextArea = new FlatTextArea(supportsFormattingTags ? "Enter your formatted message..." : "Enter your message...", true);
        flatTextArea.setText(notification.getMessage());
        ((AbstractDocument) flatTextArea.getDocument()).setDocumentFilter(new LengthLimitFilter(200));
        flatTextArea.getDocument().addDocumentListener((SimpleDocumentListener) ev -> {
            notification.setMessage(flatTextArea.getText());
        });
        flatTextArea.getTextArea().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                flatTextArea.getTextArea().selectAll();
            }

            @Override
            public void focusLost(FocusEvent e) {
                onChangeListener.run();
            }
        });
        this.settings.add(flatTextArea);
    }
}
