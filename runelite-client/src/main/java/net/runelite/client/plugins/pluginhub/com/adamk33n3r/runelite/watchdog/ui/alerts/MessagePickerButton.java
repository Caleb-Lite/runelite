package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.alerts;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.GameMessageType;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.PlayerChatType;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPlugin;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.Icons;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import javax.swing.JButton;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class MessagePickerButton {
    public static JButton createNotificationPickerButton(Consumer<String> callback) {
        return PanelUtils.createActionButton(Icons.PICKER, Icons.PICKER_HOVER, "Pick a recent notification", (btn, mod) -> WatchdogPlugin.getInstance().getPanel().pickNotification(callback));
    }

    public static JButton createGameMessagePickerButton(Consumer<String> callback, Supplier<GameMessageType> typeFilter) {
        return PanelUtils.createActionButton(Icons.PICKER, Icons.PICKER_HOVER, "Pick a message from chat", (btn, mod) -> WatchdogPlugin.getInstance().getPanel().pickMessage(callback, (msg) -> typeFilter.get().isOfType(msg.getType())));
    }

    public static JButton createPlayerChatPickerButton(Consumer<String> callback, Supplier<PlayerChatType> typeFilter) {
        return PanelUtils.createActionButton(Icons.PICKER, Icons.PICKER_HOVER, "Pick a message from chat", (btn, mod) -> WatchdogPlugin.getInstance().getPanel().pickMessage(callback, (msg) -> typeFilter.get().isOfType(msg.getType())));
    }

    public static JButton createOverheadTextPickerButton(Consumer<String> callback) {
        return PanelUtils.createActionButton(Icons.PICKER, Icons.PICKER_HOVER, "Pick a recent overhead text", (btn, mod) -> WatchdogPlugin.getInstance().getPanel().pickOverheadText(callback));
    }
}
