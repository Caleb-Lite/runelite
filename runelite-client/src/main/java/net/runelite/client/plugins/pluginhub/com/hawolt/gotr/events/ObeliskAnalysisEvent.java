package net.runelite.client.plugins.pluginhub.com.hawolt.gotr.events;

import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.data.ObeliskType;
import net.runelite.client.plugins.pluginhub.com.hawolt.gotr.utility.ObeliskAnalysis;
import lombok.Getter;

import java.util.Arrays;

@Getter
public class ObeliskAnalysisEvent {
    private final ObeliskType obeliskType;
    private final ObeliskAnalysis[] obeliskAnalysis;

    public ObeliskAnalysisEvent(ObeliskType obeliskType, ObeliskAnalysis... obeliskAnalysis) {
        this.obeliskAnalysis = obeliskAnalysis;
        this.obeliskType = obeliskType;
    }

    @Override
    public String toString() {
        return "ObeliskAnalysisEvent{" +
                "obeliskType=" + obeliskType +
                ", obeliskAnalysis=" + Arrays.toString(obeliskAnalysis) +
                '}';
    }
}
