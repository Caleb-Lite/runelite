package net.runelite.client.plugins.pluginhub.com.prayervolumecontrol;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.SoundEffectPlayed;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Prayer volume control"
)
public class PrayerVolumeControlPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private PrayerVolumeControlConfig config;

	@Subscribe
	public void onSoundEffectPlayed(SoundEffectPlayed soundEffectPlayed)
	{
		// Check for prayer sounds
		int soundId = soundEffectPlayed.getSoundId();
		int soundVolume = 100;
		boolean isPrayer = false;
		switch (soundId) {
			case SoundEffectID.PRAYER_ACTIVATE_MYSTIC_WILL_AUGURY:
				soundVolume = Math.round(config.auguryMysticWill());
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_BURST_OF_STRENGTH:
				soundVolume = config.burstOfStrength();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_CHIVALRY:
				soundVolume = config.chivalry();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_CLARITY_OF_THOUGHT:
				soundVolume = config.clarityOfThought();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_EAGLE_EYE:
				soundVolume = config.eagleEye();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_HAWK_EYE:
				soundVolume = config.hawkEye();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_IMPROVED_REFLEXES:
				soundVolume = config.improvedReflexes();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_INCREDIBLE_REFLEXES:
				soundVolume = config.incredibleReflexes();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_MYSTIC_LORE:
				soundVolume = config.mysticLore();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_MYSTIC_MIGHT:
				soundVolume = config.mysticMight();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_PIETY:
				soundVolume = config.piety();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_RAPID_RESTORE_PRESERVE:
				soundVolume = config.preserveRapidRestore();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_PROTECT_FROM_MAGIC:
				soundVolume = config.protectFromMagic();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_PROTECT_FROM_MELEE:
				soundVolume = config.protectFromMelee();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_PROTECT_FROM_MISSILES:
				soundVolume = config.protectFromMissiles();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_PROTECT_ITEM:
				soundVolume = config.protectItem();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_RAPID_HEAL:
				soundVolume = config.rapidHeal();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_REDEMPTION:
				soundVolume = config.redemption();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_RETRIBUTION:
				soundVolume = config.retribution();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_SHARP_EYE_RIGOUR:
				soundVolume = config.rigourSharpEye();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_ROCK_SKIN:
				soundVolume = config.rockSkin();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_SMITE:
				soundVolume = config.smite();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_STEEL_SKIN:
				soundVolume = config.steelSkin();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_SUPERHUMAN_STRENGTH:
				soundVolume = config.superhumanStrength();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_THICK_SKIN:
				soundVolume = config.thickSkin();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_ACTIVATE_ULTIMATE_STRENGTH:
				soundVolume = config.ultimateStrength();
				isPrayer = true;
				break;
			case SoundEffectID.PRAYER_DEACTIVE_VWOOP:
				soundVolume = config.prayerDeactivate();
				break;
			case SoundEffectID.UI_BOOP:
				soundVolume = config.uiBoop();
				break;
		}

		// Check if this is a prayer, and we are using the override prayer volume
		if (isPrayer && config.useOverridePrayerVolume()) {
			overridePrayerSound(soundEffectPlayed, soundId, config.overridePrayerVolume());
			return;
		}

		// Check if we should ignore this prayer (exactly 100 volume)
		if (soundVolume == 100) {
			return;
		}

		overridePrayerSound(soundEffectPlayed, soundId, soundVolume);
	}

	protected void overridePrayerSound(SoundEffectPlayed soundEffectPlayed, int soundId, int soundVolume)
	{
		// Cancel/consume the current sound effect
		soundEffectPlayed.consume();

		// Get the sound effect volume
		int currentVolume = client.getPreferences().getSoundEffectVolume();

		// Change the volume to the config's volume and play the sound effect
		client.getPreferences().setSoundEffectVolume(soundVolume);
		client.playSoundEffect(soundId, soundVolume);

		// Revert the sound effect volume to the original
		client.getPreferences().setSoundEffectVolume(currentVolume);

//		log.info("Overriding prayer sound " + soundEffectPlayed + " at volume " + soundVolume + ".");
	}

	@Provides
	PrayerVolumeControlConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PrayerVolumeControlConfig.class);
	}
}

