package net.runelite.client.plugins.pluginhub.com.infernostats.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.awt.*;

@Getter
@AllArgsConstructor
public class WaveSpawn {
	private final int x;
	private final int y;
	private final int size;
	private final Color color;
}
