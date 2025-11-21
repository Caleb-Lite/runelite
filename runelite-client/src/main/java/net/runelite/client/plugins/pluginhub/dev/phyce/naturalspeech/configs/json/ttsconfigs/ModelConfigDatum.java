package net.runelite.client.plugins.pluginhub.dev.phyce.naturalspeech.configs.json.ttsconfigs;

import java.util.ArrayList;
import java.util.List;
import lombok.Value;

@Value
public class ModelConfigDatum {
	List<PiperConfigDatum> piperConfigData = new ArrayList<>();
}
