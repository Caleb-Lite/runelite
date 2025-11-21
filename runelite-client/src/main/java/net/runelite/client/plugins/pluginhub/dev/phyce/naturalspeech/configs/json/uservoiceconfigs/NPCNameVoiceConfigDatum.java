package net.runelite.client.plugins.pluginhub.dev.phyce.naturalspeech.configs.json.uservoiceconfigs;

import net.runelite.client.plugins.pluginhub.dev.phyce.naturalspeech.tts.VoiceID;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.Value;

// Used for JSON Serialization
@Data
public class NPCNameVoiceConfigDatum {

	List<VoiceID> voiceIDs = new ArrayList<>();

	/**
	 * Can be wildcard, ex *Bat matches Giant Bat, Little Bat, etc.
	 */
	String npcName;

	public NPCNameVoiceConfigDatum(String npcName) {
		this.npcName = npcName;
	}

}
