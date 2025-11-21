package net.runelite.client.plugins.pluginhub.com.ashleythew.cookingtooltip;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.BeforeRender;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.WidgetUtil;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.tooltip.Tooltip;
import net.runelite.client.ui.overlay.tooltip.TooltipManager;
import net.runelite.client.util.ColorUtil;
import java.awt.Color;

@Slf4j
@PluginDescriptor(name = "Cooking Chance Tooltip", description = "Show cooking success chances and XP estimates in tooltips", tags = {
        "cooking", "tooltip", "chance", "xp" })
public class CookingTooltipPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private CookingTooltipConfig config;

    @Inject
    private TooltipManager tooltipManager;

    @Override
    protected void startUp() throws Exception {
        log.info("Cooking Chance Tooltip started!");
    }

    @Override
    protected void shutDown() throws Exception {
        log.info("Cooking Chance Tooltip stopped!");
    }

    @Subscribe
    public void onBeforeRender(BeforeRender event) {
        if (!config.showCookingTooltips()) {
            return;
        }

        final MenuEntry[] menuEntries = client.getMenu().getMenuEntries();
        final int last = menuEntries.length - 1;
        if (last < 0) {
            return;
        }

        final MenuEntry menuEntry = menuEntries[last];
        final MenuAction action = menuEntry.getType();
        final int widgetId = menuEntry.getParam1();
        final int groupId = WidgetUtil.componentToInterface(widgetId);

        switch (action) {
            case WIDGET_TARGET_ON_WIDGET:
                // Check target widget is the inventory
                if (menuEntry.getWidget().getId() != ComponentID.INVENTORY_CONTAINER) {
                    break;
                }
                // FALLTHROUGH
            case CC_OP:
            case ITEM_USE:
            case ITEM_FIRST_OPTION:
            case ITEM_SECOND_OPTION:
            case ITEM_THIRD_OPTION:
            case ITEM_FOURTH_OPTION:
            case ITEM_FIFTH_OPTION:
                if (groupId == InterfaceID.INVENTORY || groupId == InterfaceID.BANK
                        || groupId == InterfaceID.BANK_INVENTORY) {
                    addCookingTooltip(menuEntry);
                }
                break;
            case WIDGET_TARGET:
                // Check that this is the inventory
                if (menuEntry.getWidget().getId() == ComponentID.INVENTORY_CONTAINER) {
                    addCookingTooltip(menuEntry);
                }
                break;
            default:
                // Do nothing for other menu actions
                break;
        }
    }

    private Color getChanceColor(double chance) {
        if (chance >= 95)
            return new Color(0, 255, 0); // Bright green
        if (chance >= 80)
            return new Color(150, 255, 0); // Light green
        if (chance >= 60)
            return new Color(255, 255, 0); // Yellow
        if (chance >= 40)
            return new Color(255, 150, 0); // Orange
        return new Color(255, 0, 0); // Red
    }

    private void addCookingTooltip(MenuEntry menuEntry) {
        Widget widget = menuEntry.getWidget();
        if (widget == null) {
            return;
        }

        int itemId = widget.getItemId();
        Cookables cookable = Cookables.fromItemId(itemId);
        if (cookable == null) {
            return;
        }

        int level = client.getBoostedSkillLevel(Skill.COOKING);
        StringBuilder sb = new StringBuilder();

        int requiredLevel = cookable.getLevel();
        if (level < requiredLevel) {
            sb.append(ColorUtil.wrapWithColorTag("Required cooking level: " + requiredLevel, new Color(255, 0, 0)));
            tooltipManager.add(new Tooltip(sb.toString()));
            return;
        }

        sb.append(ColorUtil.wrapWithColorTag(
                "Cooking chances:",
                new Color(255, 255, 0)));
        double fireChance = cookable.calculateFireChance(level);
        if (fireChance > 0) {
            sb.append("<br>Fire: ").append(ColorUtil.wrapWithColorTag(
                    String.format("%.2f", fireChance) + "%",
                    getChanceColor(fireChance)));
            if (widget.getId() == ComponentID.BANK_ITEM_CONTAINER) {
                int quantity = widget.getItemQuantity();
                double xp = cookable.getXp();
                int totalXp = (int) (quantity * xp * fireChance / 100);
                sb.append("<br>Est. XP: ").append(ColorUtil.wrapWithColorTag(
                        String.format("%,d", totalXp),
                        new Color(0, 255, 0)));
            }
        }
        double rangeChance = cookable.calculateRangeChance(level);
        if (rangeChance > 0) {
            sb.append("<br>Range: ").append(ColorUtil.wrapWithColorTag(
                    String.format("%.2f", rangeChance) + "%",
                    getChanceColor(rangeChance)));
            if (widget.getId() == ComponentID.BANK_ITEM_CONTAINER) {
                int quantity = widget.getItemQuantity();
                double xp = cookable.getXp();
                int totalXp = (int) (quantity * xp * rangeChance / 100);
                sb.append("<br>Est. XP: ").append(ColorUtil.wrapWithColorTag(
                        String.format("%,d", totalXp),
                        new Color(0, 255, 0)));
            }
        }

        tooltipManager.add(new Tooltip(ColorUtil.wrapWithColorTag(sb.toString(), new Color(238, 238, 238))));
    }

    @Provides
    CookingTooltipConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(CookingTooltipConfig.class);
    }
}
