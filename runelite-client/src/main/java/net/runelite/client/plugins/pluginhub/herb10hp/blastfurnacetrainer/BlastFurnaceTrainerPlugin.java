package net.runelite.client.plugins.pluginhub.herb10hp.blastfurnacetrainer;

import com.google.inject.Provides;
import lombok.AccessLevel;
import lombok.Getter;
import net.runelite.api.Client;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.events.GameObjectDespawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.Notifier;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

import static net.runelite.api.ObjectID.*;

@PluginDescriptor(
        name = "Blast Furnace Trainer",
        description = "Highlights Skill training activities at the Blast Furnace.",
        tags = {"minigame", "overlay", "skilling", "crafting", "strength", "firemaking"}
)
public class BlastFurnaceTrainerPlugin extends Plugin
{
    @Getter(AccessLevel.PACKAGE)
    private GameObject westPipesFixed;
    @Getter(AccessLevel.PACKAGE)
    private GameObject westPipesBroken;

    @Getter(AccessLevel.PACKAGE)
    private GameObject eastPipesFixed;
    @Getter(AccessLevel.PACKAGE)
    private GameObject eastPipesBroken;

    @Getter(AccessLevel.PACKAGE)
    private GameObject cogsFixed;
    @Getter(AccessLevel.PACKAGE)
    private GameObject cogsBroken;

    @Getter(AccessLevel.PACKAGE)
    private GameObject driveBeltFixed;
    @Getter(AccessLevel.PACKAGE)
    private GameObject driveBeltBroken;

    @Getter(AccessLevel.PACKAGE)
    private GameObject pump;

    @Getter(AccessLevel.PACKAGE)
    private GameObject stoveEmpty;
    @Getter(AccessLevel.PACKAGE)
    private GameObject stovePartial;
    @Getter(AccessLevel.PACKAGE)
    private GameObject stoveFull;

    @Inject
    private Notifier notifier;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private BlastFurnaceClickBoxOverlay clickBoxOverlay;

    @Inject
    private BlastFurnaceTrainerConfig config;

    @Inject
    private Client client;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(clickBoxOverlay);
    }

    @Override
    protected void shutDown()
    {
        overlayManager.remove(clickBoxOverlay);

        westPipesFixed = null;
        eastPipesFixed = null;
        westPipesBroken = null;
        eastPipesBroken = null;
        cogsFixed = null;
        cogsBroken = null;
        driveBeltFixed = null;
        driveBeltBroken = null;
        pump = null;
        stoveEmpty = null;
        stovePartial = null;
        stoveFull = null;
    }

    @Provides
    BlastFurnaceTrainerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(BlastFurnaceTrainerConfig.class);
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        GameObject gameObject = event.getGameObject();

        switch (gameObject.getId())
        {
            case PIPES:
                westPipesFixed = gameObject;
                break;

            case PIPES_9117:
                westPipesBroken = gameObject;
                break;

            case PIPES_9120:
                eastPipesFixed = gameObject;
                break;

            case PIPES_9121:
                eastPipesBroken = gameObject;
                break;

            case DRIVE_BELT:
                driveBeltFixed = gameObject;
                break;

            case DRIVE_BELT_9103:
                driveBeltBroken = gameObject;
                break;

            case COGS:
                cogsFixed = gameObject;
                break;

            case COGS_9105:
                cogsBroken = gameObject;
                break;

            case PUMP:
                pump = gameObject;
                break;

            case STOVE:
                if (config.notifyEmptyStove()) {
                    notifier.notify("The Blast Furnace Stove needs Coke.");
                }
                stoveEmpty = gameObject;
                break;

            case STOVE_9086:
                stovePartial = gameObject;
                break;

            case STOVE_9087:
                stoveFull = gameObject;
                break;
        }
    }

    @Subscribe
    public void onGameObjectDespawned(GameObjectDespawned event)
    {
        GameObject gameObject = event.getGameObject();

        switch (gameObject.getId())
        {
            case PIPES:
                westPipesFixed = null;
                break;

            case PIPES_9117:
                westPipesBroken = null;
                break;

            case PIPES_9120:
                eastPipesFixed = null;
                break;

            case PIPES_9121:
                eastPipesBroken = null;
                break;

            case DRIVE_BELT:
                driveBeltFixed = null;
                break;

            case DRIVE_BELT_9103:
                driveBeltBroken = null;
                break;

            case COGS:
                cogsFixed = null;
                break;

            case COGS_9105:
                cogsBroken = null;
                break;

            case PUMP:
                pump = null;
                break;

            case STOVE:
                stoveEmpty = null;
                break;

            case STOVE_9086:
                stovePartial = null;
                break;

            case STOVE_9087:
                stoveFull = null;
                break;
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOADING)
        {
            westPipesFixed = null;
            eastPipesFixed = null;
            westPipesBroken = null;
            eastPipesBroken = null;
            cogsFixed = null;
            cogsBroken = null;
            driveBeltFixed = null;
            driveBeltBroken = null;
            pump = null;
            stoveEmpty = null;
            stovePartial = null;
            stoveFull = null;
        }
    }
}

