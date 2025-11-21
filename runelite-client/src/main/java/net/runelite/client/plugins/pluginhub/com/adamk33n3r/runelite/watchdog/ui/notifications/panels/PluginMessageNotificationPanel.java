package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.notifications.panels;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.notifications.PluginMessage;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.FlatTextArea;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.FlatTextAreaNamespace;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.NotificationsPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;
import net.runelite.client.ui.ColorScheme;

import java.awt.*;

public class PluginMessageNotificationPanel extends NotificationPanel {
    public PluginMessageNotificationPanel(PluginMessage notification, NotificationsPanel parentPanel, Runnable onChangeListener, PanelUtils.OnRemove onRemove) {
        super(notification, parentPanel, onChangeListener, onRemove);

        this.rebuild();
    }
    private void rebuild() {
        this.settings.removeAll();

        PluginMessage notification = (PluginMessage) this.notification;

        FlatTextAreaNamespace fullText = PanelUtils.createTextFieldNamespace(
            "Namespace",
            "The namespace of the plugin message. Usually the name of the plugin.",
            notification.getNamespace(),
            "Method",
            "The method or action of the plugin message.",
            notification.getName(),
            (val1, val2) -> {
                notification.setNamespace(val1);
                notification.setName(val2);
                onChangeListener.run();
            }
        );
        fullText.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
        this.settings.add(fullText);

        FlatTextArea dataText = PanelUtils.createTextField(
            "Data (JSON)",
            "The data of the plugin message as a JSON object string. Leave empty if not needed.",
            notification.getData(),
            val -> {
                notification.setData(val);
                onChangeListener.run();
            }
        );
        this.settings.add(dataText);
    }
}

/*
 * Copyright (c) 2018, Jasper <Jasper0781@gmail.com>
 * Copyright (c) 2020, melky <https://github.com/melkypie>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */