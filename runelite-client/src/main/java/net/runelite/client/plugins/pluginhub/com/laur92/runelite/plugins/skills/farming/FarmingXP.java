package net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.skills.farming;

import net.runelite.client.plugins.pluginhub.com.laur92.runelite.plugins.ItemXPConfig;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.JagexColors;
import net.runelite.client.ui.SkillColor;
import net.runelite.client.util.ColorUtil;

import javax.inject.Inject;
import java.awt.*;

import static com.laur92.runelite.plugins.ItemXPPlugin.NEW_LINE;
import static com.laur92.runelite.plugins.ItemXPPlugin.df;

@Slf4j
public class FarmingXP
{
    private final ItemXPConfig config;
    private final Client client;

    private static final Color plantColor = new Color(99, 46, 23);
    private static final Color checkHealthColor = new Color(92, 217, 197);
    private static final Color harvestColor = new Color(18, 121, 7);

    @Inject
    public FarmingXP(ItemXPConfig config, Client client)
    {
        this.config = config;
        this.client = client;
    }

    public StringBuilder getFarmingToolTip(int itemID, int quantity)
    {
        if(itemID == -1) return null;
        if(!config.showFarmingSkill()) return null;

        FarmingItem item = FarmingItems.getFarmingItem(itemID);
        CompostType compost = Compost.getCompost(itemID);

        if(item == null && compost == null) return null;

        StringBuilder sb = new StringBuilder();
        var currentLevel = client.getBoostedSkillLevel(Skill.FARMING);

        sb.append(ColorUtil.wrapWithColorTag("Farming", SkillColor.FARMING.getColor()));

        if(item != null)
        {
            if (config.showLevelRequirement())
            {
                sb.append(ColorUtil.wrapWithColorTag(" (lv ", ColorScheme.BRAND_ORANGE));
                sb.append(ColorUtil.wrapWithColorTag(Integer.toString(item.getLevel()), currentLevel >= item.getLevel() ? Color.GREEN : Color.RED));
                sb.append(ColorUtil.wrapWithColorTag(")", ColorScheme.BRAND_ORANGE));
            }

            sb.append(" ");
            sb.append(getPatchType(item));
            sb.append(" patch");
            sb.append(NEW_LINE);

            FarmingXPCalc calc = new FarmingXPCalc(item);

            addPlant(sb, item, calc, quantity);
            addCheckHealth(sb, item, calc);
            addHarvest(sb, item);
            if (config.showFarmingYieldRange()) addYieldRange(sb, item, calc);
            if (config.showFarmingExpectedYield()) addExpectedYield(sb, item, calc);
            addTotalRange(sb, calc);
            if (config.showFarmingExpectedYield()) addTotalExpected(sb, calc);

            if (config.showStackCalculations())
            {
                int numPlantings = quantity / item.getPlantQuantity();
                var stackCalc = new FarmingXPCalc(item, numPlantings);
                if (config.showFarmingExpectedYield()) addStackYield(sb, item, stackCalc, numPlantings);
                addStackRange(sb, stackCalc);
                if (config.showFarmingExpectedYield()) addStackExpected(sb, stackCalc);
            }
        }
        if(compost != null)
        {
            sb.append(": ");
            sb.append(df.format(compost.getXp()));
            sb.append("xp");
            if(config.showStackCalculations())
            {
                sb.append(NEW_LINE);
                sb.append(ColorUtil.colorTag(Color.CYAN));
                sb.append("Stack Total: ");
                sb.append(df.format((long) compost.getXp() * quantity));
                sb.append("xp");
                sb.append(ColorUtil.CLOSING_COLOR_TAG);
            }
        }

        return sb;
    }

    private void addPlant(StringBuilder sb, FarmingItem item, FarmingXPCalc calc, int quantity)
    {
        sb.append(ColorUtil.wrapWithColorTag("Plant: ", plantColor));
        sb.append(df.format(calc.getPlantXP()));
        sb.append("xp");
        if(item.getPlantQuantity() > 1)
        {
            sb.append(ColorUtil.wrapWithColorTag(" (", JagexColors.DARK_ORANGE_INTERFACE_TEXT));
            sb.append(ColorUtil.wrapWithColorTag(Integer.toString(item.getPlantQuantity()), quantity >= item.getPlantQuantity() ? Color.GREEN : Color.RED));
            sb.append(ColorUtil.wrapWithColorTag(" seeds)", JagexColors.DARK_ORANGE_INTERFACE_TEXT));
        }
    }

    private void addCheckHealth(StringBuilder sb, FarmingItem item, FarmingXPCalc calc)
    {
        if (!(item.getCheckHealthXP() > 0)) return;

        sb.append(NEW_LINE);
        sb.append(ColorUtil.wrapWithColorTag("Check Health: ", checkHealthColor));
        sb.append(df.format(calc.checkHealthXP));
        sb.append("xp");
    }

    private void addHarvest(StringBuilder sb, FarmingItem item)
    {
        if (!(item.getHarvestXP() > 0)) return;

        sb.append(NEW_LINE);
        sb.append(ColorUtil.wrapWithColorTag("Harvest: ", harvestColor));
        sb.append(df.format(item.getHarvestXP()));
        if(item.getPatchType() != FarmingPatchType.FLOWER)
        {
            sb.append("xp per ");
            sb.append(item.getName());
        }
        else
        {
            sb.append("xp");
        }
    }

    private void addYieldRange(StringBuilder sb, FarmingItem item, FarmingXPCalc calc)
    {
        if(calc.minHarvestQuantity == 0 && calc.maxHarvestQuantity == 0) return;

        sb.append(NEW_LINE);
        sb.append(ColorUtil.colorTag(Color.GRAY));

        sb.append("Yield Range: ");
        if(calc.minHarvestQuantity == 0)
        // we're limited at the top of the range
        {
            sb.append("max ");
            sb.append(calc.maxHarvestQuantity);
            sb.append(" ");
            sb.append(calc.maxHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
            if(item.getPatchType() != FarmingPatchType.FLOWER)
            {
                sb.append(" (max ");
                sb.append(df.format(calc.maxHarvestXP));
                sb.append("xp)");
            }
        }
        else if (calc.minHarvestQuantity == calc.maxHarvestQuantity)
        // we've got an exact number
        {
            sb.append(calc.minHarvestQuantity);
            sb.append(" ");
            sb.append(calc.minHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
            if(item.getPatchType() != FarmingPatchType.FLOWER)
            {
                sb.append(" (");
                sb.append(df.format(calc.minHarvestXP));
                sb.append("xp)");
            }
        }
        else if (calc.maxHarvestQuantity == 0)
        // we're limited at the bottom of the range
        {
            sb.append("min ");
            sb.append(calc.minHarvestQuantity);
            sb.append(" ");
            sb.append(calc.minHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
            if(item.getPatchType() != FarmingPatchType.FLOWER)
            {
                sb.append(" (min ");
                sb.append(df.format(calc.minHarvestXP));
                sb.append("xp)");
            }
        }
        else
        // we have a specific range
        {
            sb.append(calc.minHarvestQuantity);
            sb.append("-");
            sb.append(calc.maxHarvestQuantity);
            sb.append(" ");
            sb.append(calc.maxHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
            if(item.getPatchType() != FarmingPatchType.FLOWER)
            {
                sb.append(" (");
                sb.append(df.format(calc.minHarvestXP));
                sb.append("-");
                sb.append(df.format(calc.maxHarvestXP));
                sb.append("xp)");
            }
        }

        sb.append(ColorUtil.CLOSING_COLOR_TAG);
    }


    private void addExpectedYield(StringBuilder sb, FarmingItem item, FarmingXPCalc calc)
    {
        if(calc.expectedHarvestQuantity == 0) return;

        sb.append(NEW_LINE);
        sb.append(ColorUtil.colorTag(Color.ORANGE));

        sb.append("Expected Yield: ");
        sb.append(calc.expectedHarvestQuantity);
        sb.append(" ");
        sb.append(calc.expectedHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
        if(item.getPatchType() != FarmingPatchType.FLOWER)
        {
            sb.append(" (");
            sb.append(df.format(calc.expectedHarvestXP));
            sb.append("xp)");
        }

        sb.append(ColorUtil.CLOSING_COLOR_TAG);
    }

    private void addTotalRange(StringBuilder sb, FarmingXPCalc calc)
    {
        sb.append(NEW_LINE);
        sb.append("Total XP Range: ");

        var minXP = calc.plantXP + calc.checkHealthXP + calc.minHarvestXP;
        var maxXP = calc.plantXP + calc.checkHealthXP + calc.maxHarvestXP;

        if(minXP == maxXP)
        {
            sb.append(df.format(minXP));
        }
        else
        {
            if(calc.minHarvestXP == 0)
            {
                sb.append("max ");
                sb.append(df.format(maxXP));
            }
            else if(calc.maxHarvestXP == 0)
            {
                sb.append("min ");
                sb.append(df.format(minXP));
            }
            else
            {
                sb.append(df.format(minXP));
                sb.append("-");
                sb.append(df.format(maxXP));
            }
        }
        sb.append("xp");
    }

    private void addTotalExpected(StringBuilder sb, FarmingXPCalc calc)
    {
        var expectedXP = calc.plantXP + calc.checkHealthXP + calc.expectedHarvestXP;
        sb.append(NEW_LINE);
        sb.append("Total Expected XP: ");
        sb.append(df.format(expectedXP));
        sb.append("xp");
    }

    private void addStackYield(StringBuilder sb, FarmingItem item, FarmingXPCalc calc, int numPlantings)
    {
        if(calc.expectedHarvestQuantity == 0) return;

        sb.append(NEW_LINE);
        sb.append(ColorUtil.colorTag(Color.MAGENTA));

        sb.append("Stack Expected Yield: ");
        sb.append(calc.expectedHarvestQuantity);
        sb.append(" ");
        sb.append(calc.expectedHarvestQuantity > 1 ? item.getPlural_name() : item.getName());
        sb.append(" (");
        sb.append(df.format(numPlantings));
        sb.append(" plantings)");

        sb.append(ColorUtil.CLOSING_COLOR_TAG);
    }

    private void addStackRange(StringBuilder sb, FarmingXPCalc calc)
    {
        sb.append(NEW_LINE);
        sb.append(ColorUtil.colorTag(Color.CYAN));
        sb.append("Stack Total XP Range: ");

        var minXP = calc.plantXP + calc.checkHealthXP + calc.minHarvestXP;
        var maxXP = calc.plantXP + calc.checkHealthXP + calc.maxHarvestXP;

        if(minXP == maxXP)
        {
            sb.append(df.format(minXP));
        }
        else
        {
            if(calc.minHarvestXP == 0)
            {
                sb.append("max ");
                sb.append(df.format(maxXP));
            }
            else if(calc.maxHarvestXP == 0)
            {
                sb.append("min ");
                sb.append(df.format(minXP));
            }
            else
            {
                sb.append(df.format(minXP));
                sb.append("-");
                sb.append(df.format(maxXP));
            }
        }
        sb.append("xp");
        sb.append(ColorUtil.CLOSING_COLOR_TAG);
    }

    private void addStackExpected(StringBuilder sb, FarmingXPCalc calc)
    {
        var expectedXP = calc.plantXP + calc.checkHealthXP + calc.expectedHarvestXP;
        sb.append(NEW_LINE);
        sb.append(ColorUtil.colorTag(Color.CYAN));
        sb.append("Stack Total Expected XP: ");
        sb.append(df.format(expectedXP));
        sb.append("xp");
        sb.append(ColorUtil.CLOSING_COLOR_TAG);
    }

    private static String getPatchType(FarmingItem item)
    {
        switch(item.getPatchType())
        {
            case ALLOTMENT:
                return "Allotment";
            case FLOWER:
                return "Flower";
            case HERB:
                return "Herb";
            case HOP:
                return "Hops";
            case BUSH:
                return "Bush";
            case TREE:
                return "Tree";
            case FRUIT_TREE:
                return "Fruit tree";
            case SEAWEED:
                return "Seaweed";
            case GRAPE:
                return "Vine";
            case MUSHROOM:
                return "Mushroom";
            case BELLADONNA:
                return "Belladonna";
            case HESPORI:
                return "Hespori";
            case ANIMA:
                return "Anima";
            case HARDWOOD:
                return "Hardwood tree";
            case CALQUAT:
                return "Calquat";
            case CRYSTAL:
                return "Crystal tree";
            case SPIRIT_TREE:
                return "Spirit tree";
            case CELASTRUS:
                return "Celastrus";
            case REDWOOD:
                return "Redwood tree";
            case CACTUS:
                return "Cactus";
            default:
                return "Unknown";
        }
    }

    @Getter
    private class FarmingXPCalc
    {
        private final double plantXP;
        private final double checkHealthXP;

        private final double minHarvestXP;
        private final double maxHarvestXP;
        private final double expectedHarvestXP;

        private final int minHarvestQuantity;
        private final int maxHarvestQuantity;
        private final int expectedHarvestQuantity;

        private boolean usesHarvestLives;
        private int harvestLives;

        public FarmingXPCalc(FarmingItem item)
        {
            this(item, 1);
        }

        public FarmingXPCalc(FarmingItem item, int numTimes)
        {
            plantXP = item.getPlantXP() * numTimes;
            checkHealthXP = item.getCheckHealthXP() * numTimes;

            CompostType compost = config.compostType().compost;

            switch(item.getPatchType())
            {
                case ALLOTMENT:
                case HERB:
                case HOP:
                case SEAWEED:
                case BELLADONNA:
                case CELASTRUS:
                case CACTUS:
                    //Harvest Lives
                    minHarvestQuantity = compost.getHarvestLives() * numTimes;
                    maxHarvestQuantity = 0;
                    usesHarvestLives = true;
                    harvestLives = compost.getHarvestLives();
                    break;
                case GRAPE:
                    //Harvest Lives
                    minHarvestQuantity = 5;
                    maxHarvestQuantity = 0;
                    usesHarvestLives = true;
                    harvestLives = 5;
                    break;
                case FLOWER:
                case BUSH:
                case FRUIT_TREE:
                case MUSHROOM:
                case HESPORI:
                case CALQUAT:
                case CRYSTAL:
                    //Fixed
                    minHarvestQuantity = item.getMinHarvestQuantity() * numTimes;
                    maxHarvestQuantity = item.getMaxHarvestQuantity() * numTimes;
                    break;
                case TREE:
                case ANIMA:
                case HARDWOOD:
                case SPIRIT_TREE:
                case REDWOOD:
                    //Harvest not applicable
                    minHarvestQuantity = 0;
                    maxHarvestQuantity = 0;
                    break;
                default:
                    minHarvestQuantity = 0;
                    maxHarvestQuantity = 0;
                    break;
            }

            if(usesHarvestLives)
            {
                expectedHarvestQuantity = (int)Math.round(
                        calculateExpectedYield(client.getBoostedSkillLevel(Skill.FARMING),
                                item.getMinCTS(), item.getMaxCTS(), harvestLives, item.getPatchType()) * numTimes);
            }
            else
            {
                expectedHarvestQuantity = (minHarvestQuantity + maxHarvestQuantity)/2;
            }

            if(item.getPatchType() != FarmingPatchType.FLOWER)
            {
                minHarvestXP = item.getHarvestXP() * minHarvestQuantity;
                maxHarvestXP = item.getHarvestXP() * maxHarvestQuantity;
                expectedHarvestXP = item.getHarvestXP() * expectedHarvestQuantity;
            }
            else
            {
                minHarvestXP = item.getHarvestXP();
                maxHarvestXP = item.getHarvestXP();
                expectedHarvestXP = item.getHarvestXP();
            }
        }

        //Formula from: https://oldschool.runescape.wiki/w/Farming#Variable_crop_yield
        //CTS values from: https://oldschool.runescape.wiki/w/Talk:Farming#Yield_rates_of_various_crops
        private double calculateExpectedYield(int farmingLevel, int minCTS, int maxCTS, int harvestLives, FarmingPatchType patchType)
        {
            double modifiedMinCTS = minCTS;
            double modifiedMaxCTS = maxCTS;

            double itemBoost = 0;
            // Per https://twitter.com/JagexAsh/status/1691110392443899904, some patches ignore secateurs
            if(config.farmingUsingMagicSecateurs()
                    && patchType != FarmingPatchType.CACTUS
                    && patchType != FarmingPatchType.BELLADONNA
                    && patchType != FarmingPatchType.SEAWEED)
            {
                itemBoost += 0.1;
            }
            if(config.farmingUsingSkillCape())
            {
                itemBoost += 0.05;
            }
            modifiedMinCTS = Math.floor(modifiedMinCTS * (1+itemBoost));
            modifiedMaxCTS = Math.floor(modifiedMaxCTS * (1+itemBoost));

            //TODO: Work out whether to handle diary boosts...

            if(config.farmingUsingAttas())
            {
                modifiedMinCTS = Math.floor(modifiedMinCTS * (1+0.05));
                modifiedMaxCTS = Math.floor(modifiedMaxCTS * (1+0.05));
            }

            var cts = (Math.floor(modifiedMinCTS*(99-farmingLevel)/98)
                            +Math.floor(modifiedMaxCTS*(farmingLevel-1)/98)
                            +1)
                    /256;
            return harvestLives/(1-cts);
        }
    }
}

