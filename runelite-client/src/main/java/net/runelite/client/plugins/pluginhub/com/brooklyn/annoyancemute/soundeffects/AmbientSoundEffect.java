package net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute.soundeffects;

import net.runelite.client.plugins.pluginhub.com.brooklyn.annoyancemute.SoundEffectType;
import lombok.Getter;

@Getter
public class AmbientSoundEffect extends SoundEffect
{
	int[] backgroundSoundEffects;

	public AmbientSoundEffect(int id, SoundEffectType soundEffectType)
	{
		super(id, soundEffectType);
		this.backgroundSoundEffects = new int[]{};
	}

	public AmbientSoundEffect(int id, SoundEffectType soundEffectType, int[] backgroundSoundEffects)
	{
		super(id, soundEffectType);
		this.backgroundSoundEffects = backgroundSoundEffects;
	}
}
