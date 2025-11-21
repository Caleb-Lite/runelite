package net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles.data.ColorTileMarker;
import net.runelite.client.plugins.pluginhub.com.groundMarkerProfiles.data.GroundMarkerPoint;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.coords.WorldPoint;

import javax.inject.Inject;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for translating the json strings into drawable points
 */
@Slf4j
@Value
public class PointManager {

    private List<ColorTileMarker> points = new ArrayList<>();

    private final ProfileSwapper profileSwapper;
    private final Gson gson;
    private final Client client;

    @Inject
    PointManager(ProfileSwapper profileSwapper, Gson gson, Client client) {
        this.profileSwapper = profileSwapper;
        this.gson = gson;
        this.client = client;
    }

    public void loadPoints() {
        points.clear();

        // When the plugin is toggled on from the sign in page, errors occur when using client.getMapRegions();
        if (client.getGameState() != GameState.LOGGED_IN) {
            return;
        }

        int[] regions = client.getMapRegions();

        if (regions == null) {
            return;
        }

        for (int regionId : regions) {
            Collection<GroundMarkerPoint> regionPoints = getActivePoints(regionId);
            Collection<ColorTileMarker> colorTileMarkers = translateToColorTileMarker(regionPoints);
            points.addAll(colorTileMarkers);
        }
    }

    private List<GroundMarkerPoint> getActivePoints() {
        List<GroundMarkerPoint> markers = new ArrayList<>();
        String activeGroundMarkerJson = profileSwapper.getActiveTileProfile();

        if (activeGroundMarkerJson.isEmpty()) {
            return markers;
        }

        try {
            markers.addAll(gson.fromJson(
                    activeGroundMarkerJson,
                    new TypeToken<List<GroundMarkerPoint>>() {
                    }.getType()));
        } catch (Exception ex) {
            return markers;
        }

        return markers;
    }

    //gets all the active points, filtered for a region
    private List<GroundMarkerPoint> getActivePoints(int regionId) {
        List<GroundMarkerPoint> activePoints = getActivePoints();
        Map<Integer, List<GroundMarkerPoint>> regionGroupedPoints = activePoints.stream()
                .collect(Collectors.groupingBy(GroundMarkerPoint::getRegionId));
        List<GroundMarkerPoint> regionPoints = regionGroupedPoints.get(regionId);
        if (regionPoints == null) {
            return Collections.emptyList();
        }
        return regionPoints;
    }


    private Collection<ColorTileMarker> translateToColorTileMarker(Collection<GroundMarkerPoint> points) {
        if (points.isEmpty()) {
            return Collections.emptyList();
        }

        return points.stream()
                .map(point -> new ColorTileMarker(
                        WorldPoint.fromRegion(point.getRegionId(), point.getRegionX(), point.getRegionY(), point.getZ()),
                        point.getColor(), point.getLabel()))
                .flatMap(colorTile ->
                {
                    final Collection<WorldPoint> localWorldPoints = WorldPoint.toLocalInstance(client, colorTile.getWorldPoint());
                    return localWorldPoints.stream().map(wp -> new ColorTileMarker(wp, colorTile.getColor(), colorTile.getLabel()));
                })
                .collect(Collectors.toList());
    }
}
