package net.runelite.client.plugins.pluginhub.com.homeassistant.enums;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum AggressionStatus {
    UNKNOWN("unknown"),
    ACTIVE("active"),
    SAFE("safe"),;

    private final String id;
}
