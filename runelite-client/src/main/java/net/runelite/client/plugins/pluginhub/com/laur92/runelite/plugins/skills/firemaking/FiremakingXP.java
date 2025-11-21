package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.firemaking;

import net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.ItemXPConfig;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.SkillColor;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;

import static com.laur92.runelite.plugins.ItemXPPlugin.NEW_LINE;
import static com.laur92.runelite.plugins.ItemXPPlugin.df;

public class FiremakingXP
{
    private final ItemXPConfig config;
    private final Client client;

    @Inject
    public FiremakingXP(ItemXPConfig config, Client client)
    {
        this.config = config;
        this.client = client;
    }

    public StringBuilder getFiremakingToolTip(int itemID, int quantity)
    {
        if(itemID == -1) return null;
        if(!config.showFiremakingSkill()) return null;

        FiremakingItem item = FiremakingItems.getFiremakingItem(itemID);

        if(item == null) return null;

        StringBuilder sb = new StringBuilder();
        var currentLevel = client.getBoostedSkillLevel(Skill.FIREMAKING);

        sb.append(ColorUtil.wrapWithColorTag("Firemaking", SkillColor.FIREMAKING.getColor()));

        if (config.showLevelRequirement())
        {
            sb.append(ColorUtil.wrapWithColorTag(" (lv ", ColorScheme.BRAND_ORANGE));
            sb.append(ColorUtil.wrapWithColorTag(Integer.toString(item.getLevel()), currentLevel >= item.getLevel() ? Color.GREEN : Color.RED));
            sb.append(ColorUtil.wrapWithColorTag(")", ColorScheme.BRAND_ORANGE));
        }

        switch(item.getItemType())
        {
            case LOGS:
                addLine(sb, "Burn", item.getXp(), quantity, null);
                if(config.firemakingShowCampfire())
                {
                    addLine(sb, "Add to forester's campfire", Math.floor(item.getXp() * 10 / 3) / 10, quantity, Color.ORANGE);
                }
                if(item.getMinSacredOilDoses() > 0 && config.firemakingShowPyre())
                {
                    double minXP = 10;
                    double maxXP = 16;
                    switch(item.getMinSacredOilDoses())
                    {
                        case 3:
                            minXP = 12;
                            break;
                        case 4:
                            minXP = 16;
                            break;
                    }
                    sb.append(NEW_LINE);
                    sb.append(ColorUtil.wrapWithColorTag("Turn into pyre logs", ColorUtil.fromHex("#98bb2b")));
                    sb.append(": ");
                    if(minXP == maxXP)
                    {
                        sb.append(df.format(minXP));
                    }
                    else
                    {
                        sb.append(df.format(minXP));
                        sb.append("-");
                        sb.append(df.format(maxXP));
                    }
                    sb.append("xp");
                    if(config.showStackCalculations() && quantity > 1)
                    {
                        sb.append(ColorUtil.colorTag(Color.CYAN));
                        sb.append(" (Stack: ");
                        if(minXP == maxXP)
                        {
                            sb.append(df.format(minXP*quantity));
                        }
                        else
                        {
                            sb.append(df.format(minXP*quantity));
                            sb.append("-");
                            sb.append(df.format(maxXP*quantity));
                        }
                        sb.append("xp)");
                        sb.append(ColorUtil.CLOSING_COLOR_TAG);
                    }
                }
                break;
            case PYRE_LOGS:
                addLine(sb, "Cremate remains", item.getXp(), quantity, null);
                break;
            case SACRED_OIL:
                addLine(sb, "Create pyre logs", item.getXp(), quantity, null);
                break;
            case ORIGAMI_BALLOON:
                addLine(sb, "Fly", item.getXp(), quantity, null);
                break;
            case WINDTERTODT_BRAZIER_ROOT:
                addLine(sb, "Add to brazier", 3*(currentLevel), quantity, null);
                break;
            case WINDTERTODT_BRAZIER_KINDLING:
                addLine(sb, "Add to brazier", 3.8*(currentLevel), quantity, null);
                break;
            case BLAST_FURNACE:
                addLine(sb, "Add to blast furnace", item.getXp(), quantity, null);
                break;
            case BONES:
                addLine(sb, "Burn", item.getXp(), quantity, null);
                break;
            case LIGHT_ORB:
                addLine(sb, "Repair", item.getXp(), quantity, null);
                break;
        }

        return sb;
    }

    private void addLine(StringBuilder sb, String method, double xp, int quantity, Color colour)
    {
        sb.append(NEW_LINE);
        if(colour != null)
        {
            sb.append(ColorUtil.wrapWithColorTag(method, colour));
        }
        else
        {
            sb.append(method);
        }
        sb.append(": ");
        sb.append(df.format(xp));
        sb.append("xp");
        if(config.showStackCalculations() && quantity > 1)
        {
            sb.append(ColorUtil.colorTag(Color.CYAN));
            sb.append(" (Stack: ");
            sb.append(df.format(xp*quantity));
            sb.append("xp)");
            sb.append(ColorUtil.CLOSING_COLOR_TAG);
        }
    }
}
