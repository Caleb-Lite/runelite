package net.runelite.client.plugins.pluginhub.com.github.MitzDK;

public enum Sound {
    VALUABLE_DROP("goodDrop.wav"),
    UNTRADEABLE_DROP("untradeableItem.wav");

    private final String resourceName;

    Sound(String resNam) {
        resourceName = resNam;
    }

    String getResourceName() {
        return resourceName;
    }
}
