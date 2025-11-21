package net.runelite.client.plugins.pluginhub.com.toaspellbookreminder;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@PluginDescriptor(name = "ToA Spellbook Reminder")
public class ToaSpellbookReminderPlugin extends Plugin {
    @Inject
    private Client client;

    @Inject
    private ToaSpellbookReminderConfig config;

    @Inject
    ToaSpellbookReminderOverlay overlay;

    @Inject
    public OverlayManager overlayManager;

    @Getter
    private GameObject toaDoor;

    @Override
    protected void startUp() throws Exception {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception {
        overlayManager.remove(overlay);
        toaDoor = null;
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event) {
        if (event.getGameObject().getId() == 46089) {
            toaDoor = event.getGameObject();
        }
    }

    public String getSpellbook() {
        int spellbookId = client.getVarbitValue(4070);

        switch (spellbookId) {
            case 0:
                return "Standard";
            case 1:
                return "Ancient";
            case 2:
                return "Lunar";
            case 3:
                return "Arceuus";
            default:
                return "n/a";
        }
    }

    public int getRegionId() {
        return client.getLocalPlayer().getWorldLocation().getRegionID();
    }

    @Provides
    ToaSpellbookReminderConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(ToaSpellbookReminderConfig.class);
    }
}

