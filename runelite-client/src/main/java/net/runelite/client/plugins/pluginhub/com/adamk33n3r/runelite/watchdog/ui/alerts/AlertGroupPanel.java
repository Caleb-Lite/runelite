package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.alerts;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.AlertManager;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPlugin;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.alerts.AlertGroup;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.HorizontalRuleBorder;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.AlertListPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.AlertPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

public class AlertGroupPanel extends AlertPanel<AlertGroup> {
    public AlertGroupPanel(WatchdogPanel watchdogPanel, AlertGroup alert) {
        super(watchdogPanel, alert);
    }

    @Override
    protected void build() {
        AlertManager alertManager = WatchdogPlugin.getInstance().getAlertManager();
        this.addAlertDefaults();
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.add(new JLabel("Alerts"), BorderLayout.WEST);
        buttonPanel.setBorder(new EmptyBorder(0, 5, 8, 0));

        JButton alertDropDownButton = PanelUtils.createAlertDropDownButton(createdAlert -> {
            this.alert.getAlerts().add(createdAlert);
            alertManager.saveAlerts();
            this.rebuild();
            this.watchdogPanel.openAlert(createdAlert);
        });

        buttonPanel.add(alertDropDownButton, BorderLayout.EAST);
        JPanel subGroupPanel = new JPanel(new BorderLayout());
        subGroupPanel.setBorder(new CompoundBorder(new EmptyBorder(0, 5, 0, 5), new HorizontalRuleBorder(10)));
        subGroupPanel.add(buttonPanel, BorderLayout.NORTH);

        AlertListPanel alertListPanel = new AlertListPanel(this.alert.getAlerts(), this.alert, this::rebuild);

        subGroupPanel.add(alertListPanel);
        this.addSubPanel(subGroupPanel);
    }
}
