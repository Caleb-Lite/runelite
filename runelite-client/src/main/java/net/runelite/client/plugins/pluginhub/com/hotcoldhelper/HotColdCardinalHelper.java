package net.runelite.client.plugins.pluginhub.com.hotcoldhelper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.coords.WorldPoint;

@Slf4j
public class HotColdCardinalHelper {

    public static Map<String, Integer> calculateCardinalInfoGain(List<WorldPoint> possibleLocations, WorldPoint currentLocation) {
        if (possibleLocations == null || possibleLocations.isEmpty() || currentLocation == null) {
            return new HashMap<>();
        }

        Map<String, Integer> infoGain = new HashMap<>();
        int step = 2;
        Map<String, WorldPoint> cardinalPoints = new HashMap<>();
        cardinalPoints.put("North", new WorldPoint(currentLocation.getX(), currentLocation.getY() + step, currentLocation.getPlane()));
        cardinalPoints.put("East", new WorldPoint(currentLocation.getX() + step, currentLocation.getY(), currentLocation.getPlane()));
        cardinalPoints.put("South", new WorldPoint(currentLocation.getX(), currentLocation.getY() - step, currentLocation.getPlane()));
        cardinalPoints.put("West", new WorldPoint(currentLocation.getX() - step, currentLocation.getY(), currentLocation.getPlane()));

        for (Map.Entry<String, WorldPoint> entry : cardinalPoints.entrySet()) {
            String direction = entry.getKey();
            WorldPoint newPoint = entry.getValue();

            int signalQualityScore = calculateSignalQualityScore(possibleLocations, currentLocation, newPoint);
            infoGain.put(direction, signalQualityScore);
        }
        return infoGain;
    }

    private static int calculateSignalQualityScore(List<WorldPoint> possibleLocations,
                                                   WorldPoint currentLocation,
                                                   WorldPoint newPoint) {
        int totalLocations = possibleLocations.size();
        if (totalLocations <= 1) return 0;

        Map<String, Integer> signalCounts = new HashMap<>();
        signalCounts.put("warmer", 0);
        signalCounts.put("colder", 0);
        signalCounts.put("same", 0);

        for (WorldPoint location : possibleLocations) {
            int currentDistance = calculateDistance(currentLocation, location);
            int newDistance = calculateDistance(newPoint, location);

            if (newDistance < currentDistance) {
                signalCounts.put("warmer", signalCounts.get("warmer") + 1);
            } else if (newDistance > currentDistance) {
                signalCounts.put("colder", signalCounts.get("colder") + 1);
            } else {
                signalCounts.put("same", signalCounts.get("same") + 1);
            }
        }
        int uniqueSignalTypes = (int) signalCounts.values().stream().filter(count -> count > 0).count();
        int balanceScore = 0;
        for (int count : signalCounts.values()) {
            if (count > 0) {
                balanceScore += count * (totalLocations - count);
            }
        }
        return (uniqueSignalTypes * 1000) + (balanceScore / 100);
    }

    private static int calculateDistance(WorldPoint p1, WorldPoint p2) {
        return Math.max(Math.abs(p1.getX() - p2.getX()), Math.abs(p1.getY() - p2.getY()));
    }

    public static class BestCardinalResult {
        private final String direction;
        private final int score;

        public BestCardinalResult(String direction, int score) {
            this.direction = direction;
            this.score = score;
        }

        public String getDirection() {
            return direction;
        }

        public int getScore() {
            return score;
        }
    }

    public static BestCardinalResult findBestCardinalDirection(Map<String, Integer> cardinalInfoGain) {
        if (cardinalInfoGain == null || cardinalInfoGain.isEmpty()) {
            return new BestCardinalResult("None", 0);
        }
        String bestDirection = "None";
        int highestScore = 0;
        for (Map.Entry<String, Integer> entry : cardinalInfoGain.entrySet()) {
            if (entry.getValue() > highestScore) {
                highestScore = entry.getValue();
                bestDirection = entry.getKey();
            }
        }
        return new BestCardinalResult(bestDirection, highestScore);
    }
}