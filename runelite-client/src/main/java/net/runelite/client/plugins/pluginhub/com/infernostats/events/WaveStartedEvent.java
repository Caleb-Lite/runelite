package net.runelite.client.plugins.pluginhub.com.infernostats.events;

import net.runelite.client.plugins.pluginhub.com.infernostats.model.Wave;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WaveStartedEvent {
	Wave wave;
}
