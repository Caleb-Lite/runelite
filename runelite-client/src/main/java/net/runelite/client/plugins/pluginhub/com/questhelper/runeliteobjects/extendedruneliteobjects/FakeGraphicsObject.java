package net.runelite.client.plugins.pluginhub.com.questhelper.runeliteobjects.extendedruneliteobjects;

import net.runelite.api.Client;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.callback.ClientThread;

public class FakeGraphicsObject extends ExtendedRuneliteObject
{
	ExtendedRuneliteObject objectToSpawnAfter;
	protected FakeGraphicsObject(Client client, ClientThread clientThread, WorldPoint worldPoint,
								 int[] model, int animation, ExtendedRuneliteObject objectToSpawnAfter)
	{
		super(client, clientThread, worldPoint, model, animation);
		objectType = RuneliteObjectTypes.GRAPHICS_OBJECT;
		this.objectToSpawnAfter = objectToSpawnAfter;
		runeliteObject.setShouldLoop(false);
		runeliteObject.setActive(false);
	}

	protected FakeGraphicsObject(Client client, ClientThread clientThread, WorldPoint worldPoint,
								 int[] model, int animation)
	{
		super(client, clientThread, worldPoint, model, animation);
		objectType = RuneliteObjectTypes.GRAPHICS_OBJECT;
		this.objectToSpawnAfter = null;
		runeliteObject.setShouldLoop(false);
		runeliteObject.setActive(false);
	}

	@Override
	protected void actionOnClientTick()
	{
		if (runeliteObject.getAnimation().getNumFrames() <= runeliteObject.getAnimationFrame() + 1)
		{
			disable();
			if (objectToSpawnAfter != null)
			{
				objectToSpawnAfter.activate();
			}
		}
	}
}
