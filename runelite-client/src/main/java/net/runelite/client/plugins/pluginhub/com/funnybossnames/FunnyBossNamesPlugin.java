package net.runelite.client.plugins.pluginhub.com.funnybossnames;

import javax.inject.Inject;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.*;

import java.util.Arrays;
import java.util.List;

@Slf4j
@PluginDescriptor(
        name = "Funny Boss Names"
)
public class FunnyBossNamesPlugin extends Plugin {
    @Inject
    private Client _client;

    @Inject
    private FunnyBossNamesConfig _config;

    @Subscribe
    public void onClientTick(ClientTick event) {
        //621 is parent widget collection log
        //Children:
        //CONTAINER = 0;
        //TABS = 3;
        //ENTRY = 17;
        //ENTRY_HEADER = 19;
        //ENTRY_ITEMS = 36;

        Widget bossName = _client.getWidget(303, 9);
        // Commented these out, cause they clashed with other plugins
        //Widget colLogName = client.getWidget(621,12);
        //Widget colLogNameBig = client.getWidget(621,19);
        //Widget colLogNameSmall = client.getWidget(621,19);
        Widget scoreBoardName = _client.getWidget(817, 6);

        if (bossName != null && _config.enableBossNicknames()) {
            String currentBossName = bossName.getText();
            bossName.setText(currentBossName.replace(bossName.getText(), getBossNickname(bossName.getText())));
        }

        if (scoreBoardName != null && _config.enableBossNicknames()) {
            String scoreBoardBossName = scoreBoardName.getText();
            scoreBoardName.setText(scoreBoardBossName.replace(scoreBoardBossName, getBossNickname(scoreBoardBossName)));
        }
    }

    public String getBossNickname(String bossName) {
        if (!_config.enableBossNicknames()) {
            return bossName;
        }

        // Refactor this at some point, this is wild
        if (bossName.contains("General Graardor")) {
            return _config.enableGeneralGraardor() ? bossName.replace("General Graardor", _config.generalGraardor()) : bossName;
        } else if (bossName.contains("K'ril Tsutsaroth")) {
            return _config.enableKrilTsutsaroth() ? bossName.replace("K'ril Tsutsaroth", _config.krilTsutsaroth()) : bossName;
        } else if (bossName.contains("Kree'arra")) {
            return _config.enableKreearra() ? bossName.replace("Kree'arra", _config.kreearra()) : bossName;
        } else if (bossName.contains("Commander Zilyana")) {
            return _config.enableCommanderZilyana() ? bossName.replace("Commander Zilyana", _config.commanderZilyana()) : bossName;
        } else if (bossName.contains("Callisto")) {
            return _config.enableCallisto() ? bossName.replace("Callisto", _config.callisto()) : bossName;
        } else if (bossName.contains("Artio")) {
            return _config.enableArtio() ? bossName.replace("Artio", _config.artio()) : bossName;
        } else if (bossName.contains("Chaos Elemental")) {
            return _config.enableChaosElemental() ? bossName.replace("Chaos Elemental", _config.chaosElemental()) : bossName;
        } else if (bossName.contains("Chaos Fanatic")) {
            return _config.enableChaosFanatic() ? bossName.replace("Chaos Fanatic", _config.chaosFanatic()) : bossName;
        } else if (bossName.contains("Crazy Archaeologist")) {
            return _config.enableCrazyArchaeologist() ? bossName.replace("Crazy Archaeologist", _config.crazyArchaeologist()) : bossName;
        } else if (bossName.contains("King Black Dragon")) {
            return _config.enableKingBlackDragon() ? bossName.replace("King Black Dragon", _config.kingBlackDragon()) : bossName;
        } else if (bossName.contains("Scorpia")) {
            return _config.enableScorpia() ? bossName.replace("Scorpia", _config.scorpia()) : bossName;
        } else if (bossName.contains("Venenatis")) {
            return _config.enableVenenatis() ? bossName.replace("Venenatis", _config.venenatis()) : bossName;
        } else if (bossName.contains("Spindel")) {
            return _config.enableSpindel() ? bossName.replace("Spindel", _config.spindel()) : bossName;
        } else if (bossName.contains("Vet'ion")) {
            return _config.enableVetion() ? bossName.replace("Vet'ion", _config.vetion()) : bossName;
        } else if (bossName.contains("Calvar'ion")) {
            return _config.enableCalvarion() ? bossName.replace("Calvar'ion", _config.calvarion()) : bossName;
        } else if (bossName.contains("Dagannoth Prime")) {
            return _config.enableDagannothPrime() ? bossName.replace("Dagannoth Prime", _config.dagannothPrime()) : bossName;
        } else if (bossName.contains("Dagannoth Rex")) {
            return _config.enableDagannothRex() ? bossName.replace("Dagannoth Rex", _config.dagannothRex()) : bossName;
        } else if (bossName.contains("Dagannoth Supreme")) {
            return _config.enableDagannothSupreme() ? bossName.replace("Dagannoth Supreme", _config.dagannothSupreme()) : bossName;
        } else if (bossName.contains("Corporeal Beast")) {
            return _config.enableCorporealBeast() ? bossName.replace("Corporeal Beast", _config.corporealBeast()) : bossName;
        } else if (bossName.contains("Giant Mole")) {
            return _config.enableGiantMole() ? bossName.replace("Giant Mole", _config.giantMole()) : bossName;
        } else if (bossName.contains("Deranged Archaeologist")) {
            return _config.enableDerangedArchaeologist() ? bossName.replace("Deranged Archaeologist", _config.derangedArchaeologist()) : bossName;
        } else if (bossName.contains("Cerberus")) {
            return _config.enableCerberus() ? bossName.replace("Cerberus", _config.cerberus()) : bossName;
        } else if (bossName.contains("Thermonuclear Smoke Devil")) {
            return _config.enableThermonuclearSmokeDevil() ? bossName.replace("Thermonuclear Smoke Devil", _config.thermonuclearSmokeDevil()) : bossName;
        } else if (bossName.contains("Kraken")) {
            return _config.enableKraken() ? bossName.replace("Kraken", _config.kraken()) : bossName;
        } else if (bossName.contains("Kalphite Queen")) {
            return _config.enableKalphiteQueen() ? bossName.replace("Kalphite Queen", _config.kalphiteQueen()) : bossName;
        } else if (bossName.contains("Dusk")) {
            return _config.enableDusk() ? bossName.replace("Dusk", _config.dusk()) : bossName;
        } else if (bossName.contains("Dawn")) {
            return _config.enableDawn() ? bossName.replace("Dawn", _config.dawn()) : bossName;
        } else if (bossName.contains("Alchemical Hydra")) {
            return _config.enableAlchemicalHydra() ? bossName.replace("Alchemical Hydra", _config.alchemicalHydra()) : bossName;
        } else if (bossName.contains("Sarachnis")) {
            return _config.enableSarachnis() ? bossName.replace("Sarachnis", _config.sarachnis()) : bossName;
        } else if (bossName.contains("Zalcano")) {
            return _config.enableZalcano() ? bossName.replace("Zalcano", _config.zalcano()) : bossName;
        } else if (bossName.contains("Phantom Muspah")) {
            return _config.enablePhantomMuspah() ? bossName.replace("Phantom Muspah", _config.phantomMuspah()) : bossName;
        } else if (bossName.contains("The Leviathan")) {
            return _config.enableTheLeviathan() ? bossName.replace("The Leviathan", _config.theLeviathan()) : bossName;
        } else if (bossName.contains("Vardorvis")) {
            return _config.enableVardorvis() ? bossName.replace("Vardorvis", _config.vardorvis()) : bossName;
        } else if (bossName.contains("Duke Sucellus")) {
            return _config.enableDukeSuccellus() ? bossName.replace("Duke Sucellus", _config.dukeSuccellus()) : bossName;
        } else if (bossName.contains("The Whisperer")) {
            return _config.enableTheWhisperer() ? bossName.replace("The Whisperer", _config.theWhisperer()) : bossName;
        } else if (bossName.contains("Whisperer")) {
            return _config.enableWhisperer() ? bossName.replace("Whisperer", _config.whisperer()) : bossName;
        } else if (bossName.contains("Chambers of Xeric")) {
            return _config.enableChambersOfXeric() ? bossName.replace("Chambers of Xeric", _config.chambersOfXeric()) : bossName;
        } else if (bossName.contains("Theatre of Blood")) {
            return _config.enableTheatreOfBlood() ? bossName.replace("Theatre of Blood", _config.theatreOfBlood()) : bossName;
        } else if (bossName.contains("Tombs of Amascut")) {
            return _config.enableTombsOfAmascut() ? bossName.replace("Tombs of Amascut", _config.tombsOfAmascut()) : bossName;
        } else if (bossName.contains("Abyssal Sire")) {
            return _config.enableAbyssalSire() ? bossName.replace("Abyssal Sire", _config.abyssalSire()) : bossName;
        } else if (bossName.contains("Barrows Chests")) {
            return _config.enableBarrowsChests() ? bossName.replace("Barrows Chests", _config.barrowsChests()) : bossName;
        } else if (bossName.contains("Barrows Chest")) {
            return _config.enableBarrowsChest() ? bossName.replace("Barrows Chest", _config.barrowsChest()) : bossName;
        } else if (bossName.contains("Bryophyta")) {
            return _config.enableBryophyta() ? bossName.replace("Bryophyta", _config.bryophyta()) : bossName;
        } else if (bossName.contains("Dagannoth Kings")) {
            return _config.enableDagganotheKings() ? bossName.replace("Dagannoth Kings", _config.dagganotheKings()) : bossName;
        } else if (bossName.contains("TzTok-Jad")) {
            return _config.enableTztokJad() ? bossName.replace("TzTok-Jad", _config.tztokJad()) : bossName;
        } else if (bossName.contains("The Gauntlet")) {
            return _config.enableTheGauntlet() ? bossName.replace("The Gauntlet", _config.theGauntlet()) : bossName;
        } else if (bossName.contains("Crystalline Hunllef")) {
            return _config.enableCrystallineHunllef() ? bossName.replace("Crystalline Hunllef", _config.crystallineHunllef()) : bossName;
        } else if (bossName.contains("Corrupted Hunllef")) {
            return _config.enableCorruptedHunllef() ? bossName.replace("Corrupted Hunllef", _config.corruptedHunllef()) : bossName;
        } else if (bossName.contains("Grotesque Guardians")) {
            return _config.enableGrotesqueGuardians() ? bossName.replace("Grotesque Guardians", _config.grotesqueGuardians()) : bossName;
        } else if (bossName.contains("Hespori")) {
            return _config.enableHespori() ? bossName.replace("Hespori", _config.hespori()) : bossName;
        } else if (bossName.contains("The Inferno")) {
            return _config.enableTheInferno() ? bossName.replace("The Inferno", _config.theInferno()) : bossName;
        } else if (bossName.contains("Nex")) {
            return _config.enableNex() ? bossName.replace("Nex", _config.nex()) : bossName;
        } else if (bossName.contains("The Nightmare")) {
            return _config.enableTheNightmare() ? bossName.replace("The Nightmare", _config.theNightmare()) : bossName;
        } else if (bossName.contains("Phosani's Nightmare")) {
            return _config.enablePhosanisNightmare() ? bossName.replace("Phosani's Nightmare", _config.phosanisNightmare()) : bossName;
        } else if (bossName.contains("Obor")) {
            return _config.enableObor() ? bossName.replace("Obor", _config.obor()) : bossName;
        } else if (bossName.contains("Skotizo")) {
            return _config.enableSkotizo() ? bossName.replace("Skotizo", _config.skotizo()) : bossName;
        } else if (bossName.contains("Tempoross")) {
            return _config.enableTempoross() ? bossName.replace("Tempoross", _config.tempoross()) : bossName;
        } else if (bossName.contains("Wintertodt")) {
            return _config.enableWintertodt() ? bossName.replace("Wintertodt", _config.wintertodt()) : bossName;
        } else if (bossName.contains("Zulrah")) {
            return _config.enableZulrah() ? bossName.replace("Zulrah", _config.zulrah()) : bossName;
        } else if (bossName.contains("The Fight Caves")) {
            return _config.enableTheFightCaves() ? bossName.replace("The Fight Caves", _config.theFightCaves()) : bossName;
        } else if (bossName.contains("Vorkath")) {
            return _config.enableVorkath() ? bossName.replace("Vorkath", _config.vorkath()) : bossName;
        } else if (bossName.contains("Gauntlet")) {
            return _config.enableGauntlet() ? bossName.replace("Gauntlet", _config.gauntlet()) : bossName;
        } else if (bossName.contains("Corrupted Gauntlet")) {
            return _config.enableCorruptedGauntlet() ? bossName.replace("Corrupted Gauntlet", _config.corruptedGauntlet()) : bossName;
        } else if (bossName.contains("The Maiden of Sugadinti")) {
            return _config.enableTheMaidenOfSugadinti() ? bossName.replace("The Maiden of Sugadinti", _config.theMaidenOfSugadinti()) : bossName;
        } else if (bossName.contains("Pestilent Bloat")) {
            return _config.enablePestilentBloat() ? bossName.replace("Pestilent Bloat", _config.pestilentBloat()) : bossName;
        } else if (bossName.contains("Sotetseg")) {
            return _config.enableSotetseg() ? bossName.replace("Sotetseg", _config.sotetseg()) : bossName;
        } else if (bossName.contains("Xarpus")) {
            return _config.enableXarpus() ? bossName.replace("Xarpus", _config.xarpus()) : bossName;
        } else if (bossName.contains("Verzik Vitur")) {
            return _config.enableVerzikVitur() ? bossName.replace("Verzik Vitur", _config.verzikVitur()) : bossName;
        } else if (bossName.contains("Ice Demon")) {
            return _config.enableIceDemon() ? bossName.replace("Ice Demon", _config.iceDemon()) : bossName;
        } else if (bossName.contains("Lizardman Shaman")) {
            return _config.enableLizardmanShamans() ? bossName.replace("Lizardman Shaman", _config.lizardmanShamans()) : bossName;
        } else if (bossName.contains("Muttadile")) {
            return _config.enableMuttadiles() ? bossName.replace("Muttadile", _config.muttadiles()) : bossName;
        } else if (bossName.contains("Skeletal Mystic")) {
            return _config.enableSkeletalMystic() ? bossName.replace("Skeletal Mystic", _config.skeletalMystic()) : bossName;
        } else if (bossName.contains("Tekton")) {
            return _config.enableTekton() ? bossName.replace("Tekton", _config.tekton()) : bossName;
        } else if (bossName.contains("Vanguard")) {
            return _config.enableVanguard() ? bossName.replace("Vanguard", _config.vanguard()) : bossName;
        } else if (bossName.contains("Vasa Nistirio")) {
            return _config.enableVasaNistirio() ? bossName.replace("Vasa Nistirio", _config.vasaNistirio()) : bossName;
        } else if (bossName.contains("Vespula")) {
            return _config.enableVespula() ? bossName.replace("Vespula", _config.vespula()) : bossName;
        } else if (bossName.contains("Great Olm")) {
            return _config.enableOlm() ? bossName.replace("Great Olm", _config.olm()) : bossName;
        } else if (bossName.contains("Great Olm - Left hand")) {
            return _config.enableOlmsLeftHand() ? bossName.replace("Great Olm - Left hand", _config.olmsLeftHand()) : bossName;
        } else if (bossName.contains("Great Olm - Right hand")) {
            return _config.enableOlmsRightHand() ? bossName.replace("Great Olm - Right hand", _config.olmsRightHand()) : bossName;
        } else if (bossName.contains("Kephri")) {
            return _config.enableKephri() ? bossName.replace("Kephri", _config.kephri()) : bossName;
        } else if (bossName.contains("Akkha")) {
            return _config.enableAkkha() ? bossName.replace("Akkha", _config.akkha()) : bossName;
        } else if (bossName.contains("Zebak")) {
            return _config.enableZebak() ? bossName.replace("Zebak", _config.zebak()) : bossName;
        } else if (bossName.contains("Ba-Ba")) {
            return _config.enableBaba() ? bossName.replace("Ba-Ba", _config.baba()) : bossName;
        } else if (bossName.contains("Tumeken's Warden")) {
            return _config.enableTumekensWarden() ? bossName.replace("Tumeken's Warden", _config.tumekensWarden()) : bossName;
        } else if (bossName.contains("Elidinis' Warden")) {
            return _config.enableElidinisWarden() ? bossName.replace("Elidinis' Warden", _config.elidinisWarden()) : bossName;
        } else if (bossName.contains("Scurrius")) {
            return _config.enableScurrius() ? bossName.replace("Scurrius", _config.scurrius()) : bossName;
        } else if (bossName.contains("Sol Heredit")) {
            return _config.enableSolHeredit() ? bossName.replace("Sol Heredit", _config.solHeredit()) : bossName;
        } else if (bossName.contains("Araxxor")) {
            return _config.enableAraxxor() ? bossName.replace("Araxxor", _config.araxxor()) : bossName;
        } else if (bossName.contains("Yama")) {
            return _config.enableYama() ? bossName.replace("Yama", _config.yama()) : bossName;
        }
        else if (bossName.contains("Doom of Mokhaiotl")) {
            return _config.enableMokha() ? bossName.replace("Doom of Mokhaiotl", _config.mokha()) : bossName;
        }

        return bossName;
    }

    @Subscribe
    public void onMenuEntryAdded(MenuEntryAdded event) {

        if (event != null && _config.enableBossNicknames()) {
            String currentBossName = event.getMenuEntry().getTarget();
            event.getMenuEntry().setTarget(event.getTarget().replace(currentBossName, getBossNickname(currentBossName)));
        }
    }

    @Override
    protected void shutDown() {
        log.info("Funny boss names plugin stopped!");
    }

    @Override
    protected void startUp() {
        log.info("Funny boss names plugin started!");
    }

    @Provides
    FunnyBossNamesConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(FunnyBossNamesConfig.class);
    }
}
