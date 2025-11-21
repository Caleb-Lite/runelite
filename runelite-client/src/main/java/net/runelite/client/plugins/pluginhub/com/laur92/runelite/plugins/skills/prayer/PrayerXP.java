package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.prayer;

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

public class PrayerXP
{
    private final ItemXPConfig config;
    private final Client client;

    @Inject
    public PrayerXP(ItemXPConfig config, Client client)
    {
        this.config = config;
        this.client = client;
    }

    public StringBuilder getPrayerToolTip(int itemID, int quantity)
    {
        if(itemID == -1) return null;
        if(!config.showPrayerSkill()) return null;

        PrayerItem item = PrayerItems.getPrayerItem(itemID);

        if(item == null) return null;

        StringBuilder sb = new StringBuilder();
        var currentLevel = client.getRealSkillLevel(Skill.PRAYER);

        sb.append(ColorUtil.wrapWithColorTag("Prayer", SkillColor.PRAYER.getColor()));

        if (config.showLevelRequirement())
        {
            sb.append(ColorUtil.wrapWithColorTag(" (lv ", ColorScheme.BRAND_ORANGE));
            sb.append(ColorUtil.wrapWithColorTag(Integer.toString(item.getLevel()), currentLevel >= item.getLevel() ? Color.GREEN : Color.RED));
            sb.append(ColorUtil.wrapWithColorTag(")", ColorScheme.BRAND_ORANGE));
        }

        switch(item.getItemType())
        {
            case BONES:
                addLine(sb, "Bury", item.getXp(), quantity, null);
                if(config.prayerShowEcto())
                {
                    addLine(sb, "Ecto-worship", item.getXp() * 4, quantity, ColorUtil.fromHex("#8ac485"));
                }
                if(config.prayerPOHAltar() != POHAltar.NONE)
                {
                    var multiplier = config.prayerPOHAltar().altar.getMultiplierNoBurners() + 0.5*config.prayerPOHBurners();
                    addLine(sb, config.prayerPOHAltar().altar.getName(), item.getXp() * multiplier, quantity, ColorUtil.fromHex("#f1c41c"));
                    switch(config.prayerPOHBurners())
                    {
                        case 0:
                            sb.append(" (no burners)");
                            break;
                        case 1:
                            sb.append(" (1 burner)");
                            break;
                        case 2:
                            sb.append(" (2 burners)");
                            break;
                    }
                }
                if(config.prayerChaosTemple())
                {
                    addLine(sb, "Chaos temple worship", item.getXp() * 3.5, quantity, ColorUtil.fromHex("#f6341d"));
                }
                if(config.prayerOfferingSpells())
                {
                    addLine(sb, "Sinister offering", item.getXp() * 3, quantity, ColorUtil.fromHex("#422a29"));
                    sb.append(" (req ");
                    sb.append(ColorUtil.wrapWithColorTag(Integer.toString(92), client.getBoostedSkillLevel(Skill.MAGIC) >= 92 ? Color.GREEN : Color.RED));
                    sb.append(" ");
                    sb.append(ColorUtil.wrapWithColorTag("Magic", SkillColor.MAGIC.getColor()));
                    sb.append(")");
                }
                break;
            case BONEMEAL:
                addLine(sb, "Ecto-worship", item.getXp(), quantity, ColorUtil.fromHex("#8ac485"));
                break;
            case ENRICHED_BONE:
                addLine(sb, "Strange machine", item.getXp(), quantity, null);
                break;
            case GOBLIN_PRIEST_BONE:
                addLine(sb, "Bury in Yu'biusk", item.getXp(), quantity, null);
                break;
            case ASHES:
                addLine(sb, "Scatter", item.getXp(), quantity, null);
                if(config.prayerOfferingSpells())
                {
                    addLine(sb, "Demonic offering", item.getXp() * 3, quantity, ColorUtil.fromHex("#6373bd"));
                    sb.append(" (req ");
                    sb.append(ColorUtil.wrapWithColorTag(Integer.toString(84), client.getBoostedSkillLevel(Skill.MAGIC) >= 84 ? Color.GREEN : Color.RED));
                    sb.append(" ");
                    sb.append(ColorUtil.wrapWithColorTag("Magic", SkillColor.MAGIC.getColor()));
                    sb.append(")");
                }
                break;
            case SHADE_REMAINS:
                //TODO
                break;
            case FISH_OFFERING:
                addLine(sb, "Offer at Camdozaal altar", item.getXp(), quantity, null);
                break;
            case DARK_ALTAR:
                addLine(sb, "Sacrifice at Dark altar", item.getXp(), quantity, null);
                break;
            case EGG_OFFERING:
                addLine(sb, "Offer to shrine", item.getXp(), quantity, null);
                break;
            case BRAZIER:
                addLine(sb, "Offer to saradomin brazier", item.getXp(), quantity, null);
                sb.append(NEW_LINE);
                sb.append(ColorUtil.wrapWithColorTag("Two dust used at a time unless wearing Hallowed symbol", Color.GRAY));
                break;
            case ELIDINIS_WARD:
                addLine(sb, "Fortify", item.getXp(), quantity, null);
                sb.append(" (req ");
                sb.append(ColorUtil.wrapWithColorTag(Integer.toString(90), client.getBoostedSkillLevel(Skill.SMITHING) >= 90 ? Color.GREEN : Color.RED));
                sb.append(" ");
                sb.append(ColorUtil.wrapWithColorTag("Smithing", SkillColor.SMITHING.getColor()));
                sb.append(")");
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
