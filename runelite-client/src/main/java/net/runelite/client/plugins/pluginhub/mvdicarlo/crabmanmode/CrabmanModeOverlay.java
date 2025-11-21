package net.runelite.client.plugins.pluginhub.mvdicarlo.crabmanmode;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.game.ItemManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;

@Slf4j
public class CrabmanModeOverlay extends Overlay {

    private final Client client;
    private final CrabmanModePlugin plugin;

    private Integer currentUnlock;
    private long displayTime;
    private int displayY;

    private final List<Integer> itemUnlockList;

    @Inject
    private ItemManager itemManager;

    @Inject
    public CrabmanModeOverlay(Client client, CrabmanModePlugin plugin) {
        super(plugin);
        this.client = client;
        this.plugin = plugin;
        this.itemUnlockList = new ArrayList<>();
        setPosition(OverlayPosition.TOP_CENTER);
    }

    public void addItemUnlock(int itemId) {
        if (!itemUnlockList.contains(itemId))
            itemUnlockList.add(itemId);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        if (client.getGameState() != GameState.LOGGED_IN || itemUnlockList.isEmpty()) {
            return null;
        }
        if (itemManager == null) {
            return null;
        }
        if (currentUnlock == null) {
            currentUnlock = itemUnlockList.get(0);
            displayTime = System.currentTimeMillis();
            displayY = -20;
            return null;
        }

        // Drawing unlock pop-up at the top of the screen.
        graphics.drawImage(plugin.getUnlockImage(), -62, displayY, null);
        graphics.drawImage(itemManager.getImage(currentUnlock, 1, false), -50, displayY + 7, null);
        if (displayY < 10) {
            displayY = displayY + 1;
        }

        if (System.currentTimeMillis() > displayTime + (5000)) {
            itemUnlockList.remove(currentUnlock);
            currentUnlock = null;
        }
        return null;
    }
}
