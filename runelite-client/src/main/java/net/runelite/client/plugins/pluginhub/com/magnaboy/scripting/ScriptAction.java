package net.runelite.client.plugins.pluginhub.com.magnaboy.scripting;

import net.runelite.client.plugins.pluginhub.com.magnaboy.AnimationID;
import net.runelite.client.plugins.pluginhub.com.magnaboy.CardinalDirection;
import net.runelite.api.coords.WorldPoint;

public class ScriptAction {

	public ActionType action;
	public float secondsTilNextAction;

	// 'Parameters'
	public Integer timesToLoop;
	public WorldPoint targetPosition;
	public String message;
	public AnimationID animationId;
	public String scriptName;
	public CardinalDirection targetRotation;
}

