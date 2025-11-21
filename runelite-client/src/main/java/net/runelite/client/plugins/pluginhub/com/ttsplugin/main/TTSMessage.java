package net.runelite.client.plugins.pluginhub.com.ttsplugin.main;

import lombok.Value;

@Value
public class TTSMessage {
	String message;
	int voice, distance;
	long time;
}
