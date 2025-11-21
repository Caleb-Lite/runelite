package net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProfileSwapper {

    GroundMarkerProfilesPlugin plugin;
    GroundMarkerProfilesConfig config;

    @Getter
    @Setter
    Integer activeProfileIndex;

    private final List<String> tileProfiles = new ArrayList<>();

    @Inject
    protected ProfileSwapper(GroundMarkerProfilesPlugin plugin, GroundMarkerProfilesConfig config) {
        this.plugin = plugin;
        this.config = config;
        this.activeProfileIndex = config.previousMarkerProfile();

        Collections.addAll(tileProfiles,
                config.tilesProfile0(), config.tilesProfile1(), config.tilesProfile2(), config.tilesProfile3(), config.tilesProfile4(),
                config.tilesProfile5(), config.tilesProfile6(), config.tilesProfile7(), config.tilesProfile8(), config.tilesProfile9()
        );
    }

    public void updateTileProfiles() {
        tileProfiles.clear();
        Collections.addAll(tileProfiles,
                config.tilesProfile0(), config.tilesProfile1(), config.tilesProfile2(), config.tilesProfile3(), config.tilesProfile4(),
                config.tilesProfile5(), config.tilesProfile6(), config.tilesProfile7(), config.tilesProfile8(), config.tilesProfile9()
        );
    }

    public String getActiveTileProfile() {
        if (activeProfileIndex < tileProfiles.size()) {
            return tileProfiles.get(activeProfileIndex);
        } else {
            return "";
        }
    }
}
