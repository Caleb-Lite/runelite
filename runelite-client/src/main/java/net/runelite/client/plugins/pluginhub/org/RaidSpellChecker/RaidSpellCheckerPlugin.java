package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import com.google.inject.Provides;

import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import java.util.ArrayList;
import java.util.List;

import static org.RaidSpellChecker.Spellbook.*;

@Slf4j
@PluginDescriptor(name = "Raid Spell Checker")
public class RaidSpellCheckerPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private RaidSpellCheckerConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private MissingSpellOverlay missingSpellOverlay;

    @Inject
    private SoundPlayer soundPlayer;

    // === Constants === //
    private static final WorldArea TOA_ZONE = new WorldArea(3357, 9113, 5, 4, 0);
    private static final WorldArea TOB_ZONE = new WorldArea(3670, 3215, 8, 8, 0);
    private static final int TOA_REGION = 13454;
    private static final int TOB_REGION = 14642;

    // === Dynamic State === //
    private WorldPoint lastPlayerLocation;
    private WorldPoint currentPlayerLocation;
    private WorldArea COX_ZONE;

    // === COX Scene State === //
    private boolean justLoadedRaidScene = false;
    private boolean raidLayoutChecked = false;
    private int lastRaidBaseRegion = -1;
    private long lastSceneIdentity = -1;

    // === Runtime Flags === //
    private boolean wrongSpellbook;
    private boolean noBookOfTheDead;


    @Override
    protected void startUp() throws Exception
    {
        log.info("Raid Rune Checker started!");
        overlayManager.add(missingSpellOverlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(missingSpellOverlay);
        log.info("Raid Rune Checker stopped!");
    }

    public WorldPoint getPlayerLocation()
    {
        if (client.getLocalPlayer() == null) return null;
        return client.getLocalPlayer().getWorldLocation();
    }

    public List<String> getSelectedSpellsCOX()
    {
        RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionCOX();
        List<String> selected = new ArrayList<>();
        int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
        if (currentSpellbook != activeSpellbook.ordinal())
        {
            selected.add(WRONG_SPELLBOOK);
            return selected;
        }
        switch (activeSpellbook)
        {
            case STANDARD:
                if (config.useAirCOX()) selected.add(AIR_SPELL);
                if (config.useEarthCOX()) selected.add(EARTH_SPELL);
                if (config.useFireCOX()) selected.add(FIRE_SPELL);
                if (config.useWaterCOX()) selected.add(WATER_SPELL);
                break;
            case ARCEUUS:
                if (config.useDemonbaneCOX()) selected.add(DEMONBANE_SPELL);
                if (config.useGraspCOX()) selected.add(GRASP_SPELL);
                if (config.useThrallsCOX()) selected.add(THRALL_SPELL);
                if (config.useDeathChargeCOX()) selected.add(DEATH_CHARGE_SPELL);
                if (config.useMarkOfDarknessCOX()) selected.add(MARK_OF_DARKNESS_SPELL);
                break;
            case ANCIENT:
                if (config.useSmokeCOX()) selected.add(SMOKE_SPELL);
                if (config.useShadowCOX()) selected.add(SHADOW_SPELL);
                if (config.useBloodCOX()) selected.add(BLOOD_SPELL);
                if (config.useIceCOX()) selected.add(ICE_SPELL);
                break;
            case LUNAR:
                if (config.useCureCOX()) selected.add(CURE_SPELL);
                if (config.usePotShareCOX()) selected.add(POT_SHARE);
                if (config.useVengeCOX()) selected.add(VENGE_SPELL);
                if (config.useHumidifyCOX()) selected.add(HUMIDIFY_SPELL);
                break;
        }
        return selected;
    }

    public List<String> getSelectedSpellsTOB()
    {
        RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionTOB();
        List<String> selected = new ArrayList<>();
        int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
        if (currentSpellbook != activeSpellbook.ordinal())
        {
            selected.add(WRONG_SPELLBOOK);
            return selected;
        }
        switch (activeSpellbook)
        {
            case STANDARD:
                if (config.useAirTOB()) selected.add(AIR_SPELL);
                if (config.useEarthTOB()) selected.add(EARTH_SPELL);
                if (config.useFireTOB()) selected.add(FIRE_SPELL);
                if (config.useWaterTOB()) selected.add(WATER_SPELL);
                break;
            case ARCEUUS:
                if (config.useDemonbaneTOB()) selected.add(DEMONBANE_SPELL);
                if (config.useGraspTOB()) selected.add(GRASP_SPELL);
                if (config.useThrallsTOB()) selected.add(THRALL_SPELL);
                if (config.useDeathChargeTOB()) selected.add(DEATH_CHARGE_SPELL);
                if (config.useMarkOfDarknessTOB()) selected.add(MARK_OF_DARKNESS_SPELL);
                break;
            case ANCIENT:
                if (config.useSmokeTOB()) selected.add(SMOKE_SPELL);
                if (config.useShadowTOB()) selected.add(SHADOW_SPELL);
                if (config.useBloodTOB()) selected.add(BLOOD_SPELL);
                if (config.useIceTOB()) selected.add(ICE_SPELL);
                break;
            case LUNAR:
                if (config.useCureTOB()) selected.add(CURE_SPELL);
                if (config.usePotShareTOB()) selected.add(POT_SHARE);
                if (config.useVengeTOB()) selected.add(VENGE_SPELL);
                if (config.useHumidifyTOB()) selected.add(HUMIDIFY_SPELL);
                break;
        }
        return selected;
    }

    public List<String> getSelectedSpellsTOA()
    {
        RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionTOA();
        List<String> selected = new ArrayList<>();
        int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
        if (currentSpellbook != activeSpellbook.ordinal())
        {
            selected.add(WRONG_SPELLBOOK);
            return selected;
        }
        switch (activeSpellbook)
        {
            case STANDARD:
                if (config.useAirTOA()) selected.add(AIR_SPELL);
                if (config.useEarthTOA()) selected.add(EARTH_SPELL);
                if (config.useFireTOA()) selected.add(FIRE_SPELL);
                if (config.useWaterTOA()) selected.add(WATER_SPELL);
                break;
            case ARCEUUS:
                if (config.useDemonbaneTOA()) selected.add(DEMONBANE_SPELL);
                if (config.useGraspTOA()) selected.add(GRASP_SPELL);
                if (config.useThrallsTOA()) selected.add(THRALL_SPELL);
                if (config.useDeathChargeTOA()) selected.add(DEATH_CHARGE_SPELL);
                if (config.useMarkOfDarknessTOA()) selected.add(MARK_OF_DARKNESS_SPELL);
                break;
            case ANCIENT:
                if (config.useSmokeTOA()) selected.add(SMOKE_SPELL);
                if (config.useShadowTOA()) selected.add(SHADOW_SPELL);
                if (config.useBloodTOA()) selected.add(BLOOD_SPELL);
                if (config.useIceTOA()) selected.add(ICE_SPELL);
                break;
            case LUNAR:
                if (config.useCureTOA()) selected.add(CURE_SPELL);
                if (config.usePotShareTOA()) selected.add(POT_SHARE);
                if (config.useVengeTOA()) selected.add(VENGE_SPELL);
                if (config.useHumidifyTOA()) selected.add(HUMIDIFY_SPELL);
                break;
        }
        return selected;
    }

    @Subscribe
    public void onGameTick(GameTick event)
    {
        if (client.getLocalPlayer() == null) return;
        currentPlayerLocation = getPlayerLocation();
        if (lastPlayerLocation == null)
        {
            lastPlayerLocation = currentPlayerLocation;
            return;
        }
        WorldPoint previous = lastPlayerLocation;
        lastPlayerLocation = currentPlayerLocation;
        if (config.enableTOA() && !previous.equals(currentPlayerLocation) && !previous.isInArea(TOA_ZONE) && currentPlayerLocation.isInArea(TOA_ZONE) && previous.getRegionID() == TOA_REGION)
        {
            if (!processSpells(getSelectedSpellsTOA()))
            {
                if (config.enablePopup())
                {
                    if (wrongSpellbook) missingSpellOverlay.show(WRONG_SPELLBOOK, config.popupDuration());
                    else if (noBookOfTheDead) missingSpellOverlay.show(NO_BOOK_OF_THE_DEAD, config.popupDuration());
                    else missingSpellOverlay.show(MISSING_RUNES, config.popupDuration());
                }
                if (config.enableSoundEffect()) playSound(config.soundEffect());
            }
        }
        else if (config.enableTOB() && !previous.equals(currentPlayerLocation) && !previous.isInArea(TOB_ZONE) && currentPlayerLocation.isInArea(TOB_ZONE) && previous.getRegionID() == TOB_REGION)
        {
            if (!processSpells(getSelectedSpellsTOB()))
            {
                if (config.enablePopup())
                {
                    if (wrongSpellbook) missingSpellOverlay.show(WRONG_SPELLBOOK, config.popupDuration());
                    else if (noBookOfTheDead) missingSpellOverlay.show(NO_BOOK_OF_THE_DEAD, config.popupDuration());
                    else missingSpellOverlay.show(MISSING_RUNES, config.popupDuration());
                }
                if (config.enableSoundEffect()) playSound(config.soundEffect());
            }

        }
        // === COX HANDLING. IF I EVER MEET WHO DESIGNED THIS RAID LAYOUT WE'RE THROWING HANDS === //
        else if (config.enableCox())
        {
            int flowerID = 29864;
            GameObject flower = findObjectInScene(client, flowerID);
            if (flower != null && justLoadedRaidScene && !raidLayoutChecked && client.isInInstancedRegion())
            {
                int[] regions = client.getMapRegions();
                if (regions == null || regions.length == 0) return;
                int baseRegion = regions[0];
                long sceneIdentity = flower.getWorldLocation().hashCode();
                if (baseRegion != lastRaidBaseRegion || sceneIdentity != lastSceneIdentity)
                {
                    lastRaidBaseRegion = baseRegion;
                    lastSceneIdentity = sceneIdentity;
                    raidLayoutChecked = true;
                    justLoadedRaidScene = false;
                    getRaidLayout(currentPlayerLocation);
                }
            }
            if (!previous.equals(currentPlayerLocation) && !previous.isInArea(COX_ZONE) && currentPlayerLocation.isInArea(COX_ZONE))
            {
                if (!processSpells(getSelectedSpellsCOX()))
                {
                    if (config.enablePopup())
                    {

                        if (wrongSpellbook) missingSpellOverlay.show(WRONG_SPELLBOOK, config.popupDuration());
                        else if (noBookOfTheDead) missingSpellOverlay.show(NO_BOOK_OF_THE_DEAD, config.popupDuration());
                        else missingSpellOverlay.show(MISSING_RUNES, config.popupDuration());
                    }
                    if (config.enableSoundEffect()) playSound(config.soundEffect());
                }
            }
            // === //
        }
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        if (event.getGameState() == GameState.LOADING)
        {
            // Scene is unloading, reset flags
            justLoadedRaidScene = true;
            raidLayoutChecked = false;
        }
    }

    public boolean processSpells(List<String> spells)
    {
        wrongSpellbook = false;
        noBookOfTheDead = false;
        for (String spell : spells)
        {
            switch (spell)
            {
                case WRONG_SPELLBOOK:
                    wrongSpellbook = true;
                    return false;
                case THRALL_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.THRALL_SPELLS))
                    {
                        if (!hasBookOfTheDead())
                        {
                            noBookOfTheDead = true;
                        }
                        return false;
                    }
                    break;
                case AIR_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.AIR_SPELLS)) return false;
                    break;
                case EARTH_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.EARTH_SPELLS)) return false;
                    break;
                case FIRE_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.FIRE_SPELLS)) return false;
                    break;
                case WATER_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.WATER_SPELLS)) return false;
                    break;
                case DEMONBANE_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.DEMONBANE_SPELLS)) return false;
                    break;
                case GRASP_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.GRASP_SPELLS)) return false;
                    break;
                case DEATH_CHARGE_SPELL:
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.DEATH_CHARGE)) return false;
                    break;
                case MARK_OF_DARKNESS_SPELL:
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.MARK_OF_DARKNESS)) return false;
                    break;
                case SMOKE_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.SMOKE_SPELLS)) return false;
                    break;
                case SHADOW_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.SHADOW_SPELLS)) return false;
                    break;
                case BLOOD_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.BLOOD_SPELLS)) return false;
                    break;
                case ICE_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.ICE_SPELLS)) return false;
                    break;
                case CURE_SPELL:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.CURE_SPELLS)) return false;
                    break;
                case POT_SHARE:
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.POT_SHARE_SPELLS)) return false;
                    break;
                case VENGE_SPELL:
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.VENGEANCE)) return false;
                    break;
                case HUMIDIFY_SPELL:
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.HUMIDIFY)) return false;
                    break;
                default:
                    return true;
            }
        }
        return true;
    }

    private boolean hasBookOfTheDead()
    {
        List<ItemContainer> containers = new ArrayList<>();
        ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);

        if (equipment != null) containers.add(equipment);
        if (inventory != null) containers.add(inventory);

        for (ItemContainer container : containers)
        {
            for (Item item : container.getItems())
            {
                if (item != null && item.getId() == ItemID.BOOK_OF_THE_DEAD)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void getRaidLayout(WorldPoint playerLocation)
    {
        //Using location of flowers inside the raid when you enter to determine which layout and thus set the COX_ZONE for when the player should be notified
        boolean layout1 = false;
        LocalPoint localPoint = LocalPoint.fromWorld(client, currentPlayerLocation);
        Tile tile = client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX() + 3][localPoint.getSceneY() + 11];
        for (GameObject g : tile.getGameObjects())
        {
            if (g != null && g.getId() == 29864) layout1 = true;
        }
        if (layout1)
        {
            //SET REGION OF DETECTION
            COX_ZONE = new WorldArea(playerLocation.getX() - 9, playerLocation.getY() + 11, 5, 6, playerLocation.getPlane());
            return;
        }
        boolean layout2 = false;
        for (GameObject g : client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX() + 13][localPoint.getSceneY() + 8].getGameObjects())
        {
            if (g != null && g.getId() == 29864) layout2 = true;
        }
        if (layout2)
        {
            //SET REGION OF DETECTION
            COX_ZONE = new WorldArea(playerLocation.getX() + 6, playerLocation.getY() + 13, 6, 4, playerLocation.getPlane());
            return;
        }
        boolean layout3 = false;
        for (GameObject g : client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX() + 4][localPoint.getSceneY() + 1].getGameObjects())
        {
            if (g != null && g.getId() == 29864) layout3 = true;
        }
        if (layout3)
        {
            //SET REGION OF DETECTION
            COX_ZONE = new WorldArea(playerLocation.getX() + 15, playerLocation.getY() + 6, 7, 5, playerLocation.getPlane());
            return;
        }
        //DO NOT MOVE REGION OF DETECTION!!!!!!
    }

    public GameObject findObjectInScene(Client client, int targetObjectId)
    {
        for (int x = 0; x < Constants.SCENE_SIZE; x++)
        {
            for (int y = 0; y < Constants.SCENE_SIZE; y++)
            {
                Tile tile = client.getScene().getTiles()[client.getPlane()][x][y];
                if (tile == null) continue;

                for (GameObject obj : tile.getGameObjects())
                {
                    if (obj != null && obj.getId() == targetObjectId)
                    {
                        return obj;
                    }
                }
            }
        }
        return null;
    }

    public void playSound(RaidSpellCheckerConfig.Sound sound)
    {
        if (sound.equals(RaidSpellCheckerConfig.Sound.RUBY_BOLTS))
        {
            soundPlayer.play("Ruby_bolts_(e).wav", config.soundVolume());
        }
        else if (sound.equals(RaidSpellCheckerConfig.Sound.ICE_BARRAGE))
        {
            soundPlayer.play("Ice_Barrage.wav", config.soundVolume());
        }
        else if (sound.equals(RaidSpellCheckerConfig.Sound.FAIRY_RING))
        {
            soundPlayer.play("Fairy_rings.wav", config.soundVolume());
        }
        else if (sound.equals(RaidSpellCheckerConfig.Sound.EEEEEEEE))
        {
            soundPlayer.play("ACB.wav", config.soundVolume());
        }
        else if (sound.equals(RaidSpellCheckerConfig.Sound.CUSTOM))
        {
            soundPlayer.play(config.customSound(), config.soundVolume());
        }
    }

    @Provides
    RaidSpellCheckerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(RaidSpellCheckerConfig.class);
    }
}
