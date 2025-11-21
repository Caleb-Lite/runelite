package net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute.soundeffects;

import net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute.SoundEffectType;

public class SoundEffect
{
	int soundID;
	SoundEffectType type;

	public SoundEffect(int soundID, SoundEffectType type)
	{
		this.soundID = soundID;
		this.type = type;
	}

	public int getId()
	{
		return soundID;
	}

	public SoundEffectType getSoundEffectType()
	{
		return type;
	}
}
