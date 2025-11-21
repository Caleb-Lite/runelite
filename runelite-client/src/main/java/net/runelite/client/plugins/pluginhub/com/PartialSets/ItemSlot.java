package net.runelite.client.plugins.pluginhub.com.PartialSets;

import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.client.game.ItemManager;
import net.runelite.client.util.ColorUtil;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ItemSlot {

    enum EquipStatus
    {
        NotPresent,
        Equipped,
        EquippedNoWarning,
        NotRequired
    }

    enum Mode
    {
        Id,
        Name
    }

    Mode myMode;

    PartialSetsPlugin myPlugin;
    Integer myLastEquipped = -1;
    List<String> myNames = new ArrayList<>();
    int myNameIndex = 0;

    Integer[] myItems;
    String myByName;
    Integer[] myNonWarning = new Integer[]{};

    ItemSlot(PartialSetsPlugin aPlugin, Integer... aItems)
    {
        myMode = Mode.Id;

        myPlugin = aPlugin;
        myItems = aItems;

        assert myItems.length > 0;
    }

    ItemSlot(PartialSetsPlugin aPlugin, String aName)
    {
        myMode = Mode.Name;

        myPlugin = aPlugin;
        myByName = aName;
        myNames.add(aName);
        myItems = new Integer[]{1}; //This should never be visible, here as a safeguard
    }

    // Some sets (Shayzien 5) have items (slayer helm) which can be used for the set bonus
    // but that should not raise a warning when used on their own
    ItemSlot NonWarningAlternatives(Integer... aItems)
    {
        myNonWarning = aItems;

        assert myNonWarning.length > 0;

        return this;
    }

    public String toColoredString(ItemManager aItemManager)
    {
        if (myNames.isEmpty())
        {
            switch (myMode)
            {
                case Id:
                    for (Integer item : myItems)
                    {
                        myNames.add(myPlugin.myItemManager.getItemComposition(item).getMembersName());
                    }
                    break;
                case Name:
                    myNames.add(myByName);
                    break;
            }

            for (Integer item : myNonWarning)
            {
                myNames.add(myPlugin.myItemManager.getItemComposition(item).getMembersName());
            }

            myNames = myNames.stream().distinct().collect(Collectors.toList());
            myNames.sort(String::compareTo);
        }

        if (myLastEquipped == -1)
        {
            Color col = myPlugin.myConfig.UnequippedTextColor();

            if (myNames.size() > 1)
            {
                myNameIndex = (myNameIndex + 1) % myNames.size();
                return "</br>" + ColorUtil.wrapWithColorTag(myNames.get(myNameIndex) + " \t(" + (myNameIndex + 1) + "/" + (myItems.length) + ")", col);
            }
            else if (myNames.size() == 1)
            {
                return "</br>" + ColorUtil.wrapWithColorTag(myNames.get(0), col);
            }
            else
            {
                return "</br>";
            }
        }
        else
        {
            return "</br>" + ColorUtil.wrapWithColorTag(aItemManager.getItemComposition(myLastEquipped).getMembersName(), myPlugin.myConfig.EquippedTextColor());
        }
    }

    EquipStatus IsEquipped(ItemContainer aEquipment, ItemManager aItemManager)
    {
        switch (myMode)
        {
            case Id:
                {
                    Optional<Integer> equipped = Arrays.stream(myItems)
                            .filter(aEquipment::contains)
                            .findFirst();

                    if (equipped.isPresent())
                    {
                        myLastEquipped = equipped.get();
                        return EquipStatus.Equipped;
                    }
                }
                {
                    Optional<Integer> equipped = Arrays.stream(myNonWarning)
                            .filter(aEquipment::contains)
                            .findFirst();

                    if (equipped.isPresent())
                    {
                        myLastEquipped = equipped.get();
                        return EquipStatus.EquippedNoWarning;
                    }
                }

                myLastEquipped = -1;
                return EquipStatus.NotPresent;

            case Name:
                {
                    Optional<Item> equipped = Arrays.stream(aEquipment.getItems())
                            .filter(item -> myByName.equals(aItemManager.getItemComposition(item.getId()).getName()))
                            .findFirst();

                    if (equipped.isPresent())
                    {
                        myLastEquipped = equipped.get().getId();
                        return EquipStatus.Equipped;
                    }
                }

                {
                    Optional<Integer> equipped = Arrays.stream(myNonWarning)
                            .filter(aEquipment::contains)
                            .findFirst();

                    if (equipped.isPresent())
                    {
                        myLastEquipped = equipped.get();
                        return EquipStatus.EquippedNoWarning;
                    }
                }

                myLastEquipped = -1;
                return EquipStatus.NotPresent;
        }

        return EquipStatus.NotRequired;
    }
}
