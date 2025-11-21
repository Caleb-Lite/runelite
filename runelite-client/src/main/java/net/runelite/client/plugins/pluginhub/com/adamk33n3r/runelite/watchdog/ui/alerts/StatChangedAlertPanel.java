package net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.alerts;

import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.WatchdogPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.alerts.StatChangedAlert;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.alerts.StatChangedMode;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.ComparableNumber;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.AlertPanel;
import net.runelite.client.plugins.pluginhub.com.adamk33n3r.runelite.watchdog.ui.panels.PanelUtils;

import net.runelite.api.Skill;

import javax.swing.*;

public class StatChangedAlertPanel extends AlertPanel<StatChangedAlert> {
    public StatChangedAlertPanel(WatchdogPanel watchdogPanel, StatChangedAlert alert) {
        super(watchdogPanel, alert);
    }

    @Override
    protected void build() {
        this.addAlertDefaults()
            .addSelect("Skill", "The skill to track.", Skill.class, this.alert.getSkill(), this.alert::setSkill)
            .addSelect("Changed Mode", "The mode to compare the skill to the amount with.", StatChangedMode.class, this.alert.getChangedMode(), (val) -> {
                this.alert.setChangedMode(val);
                this.rebuild();
            })
            .addSubPanelControl(this.alert.getChangedMode() == StatChangedMode.RELATIVE ?
                this.createRelativeLevelPanel() :
                this.createAbsoluteLevelPanel())
            .addNotifications();
    }

    private JPanel createRelativeLevelPanel() {
        return PanelUtils.createLabeledComponent(
            "Changed Amount",
            "The difference in level to trigger the alert. Can be positive for boost and negative for drain.",
            new ComparableNumber(this.alert.getChangedAmount(), this.alert::setChangedAmount, -99, 99, 1, this.alert.getChangedComparator(), this.alert::setChangedComparator));
    }

    private JPanel createAbsoluteLevelPanel() {
        return PanelUtils.createLabeledComponent(
            "Level",
            "The level to trigger the alert.",
            new ComparableNumber(this.alert.getChangedAmount(), this.alert::setChangedAmount, 0, 99, 1, this.alert.getChangedComparator(), this.alert::setChangedComparator));
    }
}
