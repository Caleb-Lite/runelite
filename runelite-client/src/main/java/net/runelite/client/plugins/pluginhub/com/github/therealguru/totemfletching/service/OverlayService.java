package net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.service;

import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.overlay.CarvingActionOverlay;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.overlay.EntTrailOverlay;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.overlay.PanelOverlay;
import net.runelite.client.plugins.pluginhub.com.github.therealguru.totemfletching.overlay.TotemFletchingOverlay;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.ui.overlay.OverlayManager;

@Slf4j
@Singleton
public class OverlayService {
    @Inject private OverlayManager overlayManager;

    @Inject private TotemFletchingOverlay gameOverlay;
    @Inject private CarvingActionOverlay carvingOverlay;
    @Inject private EntTrailOverlay entTrailOverlay;
    @Inject private PanelOverlay panelOverlay;

    public OverlayService() {}

    public void registerOverlays() {
        overlayManager.add(gameOverlay);
        overlayManager.add(carvingOverlay);
        overlayManager.add(entTrailOverlay);
        overlayManager.add(panelOverlay);
    }

    public void unregisterOverlays() {
        overlayManager.remove(gameOverlay);
        overlayManager.remove(carvingOverlay);
        overlayManager.remove(entTrailOverlay);
        overlayManager.remove(panelOverlay);
    }
}
