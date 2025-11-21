package net.runelite.client.plugins.pluginhub.com.arceuusSpellbookReminder;

import javax.inject.Inject;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.ItemID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
        name = "Arceuus Spellbook Reminder"
)
public class ReminderPlugin extends Plugin
{
    private static final int BOOK_OF_THE_DEAD = ItemID.BOOK_OF_THE_DEAD;
    private static final int ARCEUUS_SPELLBOOK_ID = 3;

    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ReminderConfig config;

    @Inject
    private ReminderOverlay overlay;

    @Provides
    ReminderConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ReminderConfig.class);
    }

    @Override
    protected void startUp()
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(overlay);
    }

    public boolean hasBookOfTheDead()
    {
        final ItemContainer inventory = client.getItemContainer(InventoryID.INV);
        if (inventory == null)
        {
            return false;
        }
        return inventory.contains(BOOK_OF_THE_DEAD);
    }

    public boolean isOnArceuusSpellbook()
    {
        return client.getVarbitValue(VarbitID.SPELLBOOK) == ARCEUUS_SPELLBOOK_ID;
    }

    public boolean hasClueScroll()
    {
        final ItemContainer inventory = client.getItemContainer(InventoryID.INV);
        if (inventory == null)
        {
            return false;
        }

        for (Item item : inventory.getItems())
        {
            if (item == null)
            {
                continue;
            }

            String name = client.getItemDefinition(item.getId()).getName().toLowerCase();

            if (name.contains("clue scroll") || name.contains("reward casket"))
            {
                return true;
            }
        }
        return false;
    }


}
