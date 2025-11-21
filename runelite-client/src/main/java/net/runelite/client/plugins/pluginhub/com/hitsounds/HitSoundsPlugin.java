package net.runelite.client.plugins.pluginhub.com.hitsounds;

import com.google.inject.Provides;
import net.runelite.client.plugins.pluginhub.com.hitsounds.enums.HitSoundEnum;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.HitsplatID;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.RuneLite;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import javax.sound.sampled.*;
import java.io.*;

@Slf4j
@PluginDescriptor(
	name = "Hit Sounds",
	description = "This plugin will send a custom sound on hitsplat",
	tags = {"sound", "hitsplat", "max", "poison", "disease", "venom","damage", "health"},
	enabledByDefault = true
)
public class HitSoundsPlugin extends Plugin
{
	@Inject
	private Client client;
	@Inject
	private HitSoundsConfig config;
	@Inject
	private ClientThread clientThread;
	private Clip clip = null;

	private static final String BASE_DIRECTORY = System.getProperty("user.home") + "/.runelite/hitsounds/";
	public static final File NORMAL_FILE = new File(BASE_DIRECTORY, "normal.wav");
	public static final File MAX_FILE = new File(BASE_DIRECTORY, "max.wav");
	public static final File POISON_FILE = new File(BASE_DIRECTORY, "poison.wav");
	public static final File VENOM_FILE = new File(BASE_DIRECTORY, "venom.wav");
	public static final File DISEASE_FILE = new File(BASE_DIRECTORY, "disease.wav");
	public static final File MISS_FILE = new File(BASE_DIRECTORY, "miss.wav");
	public static final File HEALING_FILE = new File(BASE_DIRECTORY, "healing.wav");
	public static final File SHIELD_FILE = new File(BASE_DIRECTORY, "shield.wav");
	public static final File ARMOUR_FILE = new File(BASE_DIRECTORY, "armour.wav");
	public static final File CHARGE_FILE = new File(BASE_DIRECTORY, "charge.wav");
	public static final File UNCHARGE_FILE = new File(BASE_DIRECTORY, "uncharge.wav");
	public static final File OTHER_FILE = new File(BASE_DIRECTORY, "other.wav");

	private long lastClipMTime = CLIP_MTIME_UNLOADED;
	private static final long CLIP_MTIME_UNLOADED = -2;
	private static final long CLIP_MTIME_BUILTIN = -1;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Hit Sounds started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Hit Sounds stopped!");
	}

	@Subscribe
	public void onHitsplatApplied(HitsplatApplied hitsplatApplied){
		switch(hitsplatApplied.getHitsplat().getHitsplatType()){

			case HitsplatID.DAMAGE_OTHER:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.DAMAGE_ME:
				if (!config.normalHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.NORMAL)){
					clientThread.invoke(() -> client.playSoundEffect(config.normalHitSound().getID()));
				};
				break;

			case HitsplatID.BLOCK_OTHER:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.BLOCK_ME:
				if (!config.missHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.MISS)){
					clientThread.invoke(() -> client.playSoundEffect(config.missHitSound().getID()));
				}
				break;

			case HitsplatID.DAMAGE_MAX_ME:
				if (!config.maxHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.MAX)){
					clientThread.invoke(() -> client.playSoundEffect(config.maxHitSound().getID()));
				}
				break;
			case HitsplatID.DISEASE:
				if (!config.diseaseHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.DISEASE)){
					clientThread.invoke(() -> client.playSoundEffect(config.diseaseHitSound().getID()));
				}
				break;
			case HitsplatID.VENOM:
				if (!config.venomHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.VENOM)){
					clientThread.invoke(() -> client.playSoundEffect(config.venomHitSound().getID()));
				}
				break;

			case HitsplatID.HEAL:
				if (!config.healingHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.HEALING)){
					clientThread.invoke(() -> client.playSoundEffect(config.healingHitSound().getID()));
				}
				break;
			case HitsplatID.POISON:
				if (!config.poisonHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.POISON)){
					clientThread.invoke(() -> client.playSoundEffect(config.poisonHitSound().getID()));
				}
				break;

			case HitsplatID.DAMAGE_OTHER_CYAN:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.DAMAGE_ME_CYAN:
				if (!config.shieldHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.SHIELD)){
					clientThread.invoke(() -> client.playSoundEffect(config.shieldHitSound().getID()));
				}
				break;
			case HitsplatID.DAMAGE_MAX_ME_CYAN:
				if (config.shieldHitBoolean() && config.maxHitBoolean()){
					if (!playCustomSound(HitSoundEnum.SHIELD)){
						clientThread.invoke(() -> client.playSoundEffect(config.shieldHitSound().getID()));
					}
					break;
				}
				break;

			case HitsplatID.DAMAGE_OTHER_WHITE:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.DAMAGE_ME_WHITE:
				if (!config.unchargeHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.UNCHARGE)){
					clientThread.invoke(() -> client.playSoundEffect(config.unchargeHitSound().getID()));
				}
				break;
			case HitsplatID.DAMAGE_MAX_ME_WHITE:
				if (config.unchargeHitBoolean() && config.maxHitBoolean()){
					if (!playCustomSound(HitSoundEnum.UNCHARGE)){
						clientThread.invoke(() -> client.playSoundEffect(config.unchargeHitSound().getID()));
					}
					break;
				}
				break;

			case HitsplatID.DAMAGE_OTHER_YELLOW:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.DAMAGE_ME_YELLOW:
				if (!config.chargeHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.CHARGE)){
					clientThread.invoke(() -> client.playSoundEffect(config.chargeHitSound().getID()));
				}
				break;
			case HitsplatID.DAMAGE_MAX_ME_YELLOW:
				if (config.chargeHitBoolean() && config.maxHitBoolean()){
					if (!playCustomSound(HitSoundEnum.CHARGE)){
						clientThread.invoke(() -> client.playSoundEffect(config.chargeHitSound().getID()));
					}
					break;
				}
				break;

			case HitsplatID.DAMAGE_OTHER_ORANGE:
				if (config.muteOthers()){
					break;
				}
			case HitsplatID.DAMAGE_ME_ORANGE:
				if (!config.armourHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.ARMOUR)){
					clientThread.invoke(() -> client.playSoundEffect(config.armourHitSound().getID()));
				}
				break;
			case HitsplatID.DAMAGE_MAX_ME_ORANGE:
				if (config.armourHitBoolean() && config.maxHitBoolean()){
					if (!playCustomSound(HitSoundEnum.ARMOUR)){
						clientThread.invoke(() -> client.playSoundEffect(config.armourHitSound().getID()));
					}
					break;
				}
				break;

			default:
				if (!config.otherHitBoolean()){
					break;
				}
				if (!playCustomSound(HitSoundEnum.OTHER)){
					clientThread.invoke(() -> client.playSoundEffect(config.otherHitSound().getID()));
				}
				break;
		}
	}


	// modified from the notifier default plugin:
	// https://github.com/runelite/runelite/blob/63dd8af9b51757eb8140674d361a0473cf7e0441/runelite-client/src/main/java/net/runelite/client/Notifier.java#L446-L512
	private synchronized boolean playCustomSound(HitSoundEnum hitSoundEnum)
	{
		long currentMTime = hitSoundEnum.getFile().exists() ? hitSoundEnum.getFile().lastModified() : CLIP_MTIME_BUILTIN;
		if (clip == null || currentMTime != lastClipMTime || !clip.isOpen())
		{
			try
			{
				clip = AudioSystem.getClip();
			}
			catch (LineUnavailableException e)
			{
				lastClipMTime = CLIP_MTIME_UNLOADED;
				log.warn("Unable to play sound", e);
				return false;
			}
			lastClipMTime = currentMTime;
			if (!tryLoadCustomSound(hitSoundEnum))
			{
				return false;
			}
		}
		clip.loop(1);
		return true;
	}

	private boolean tryLoadCustomSound(HitSoundEnum hitSoundEnum)
	{
		if (hitSoundEnum.getFile().exists())
		{
			try (InputStream fileStream = new BufferedInputStream(new FileInputStream(hitSoundEnum.getFile()));
				 AudioInputStream sound = AudioSystem.getAudioInputStream(fileStream))
			{
				clip.open(sound);
				return true;
			}
			catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
			{
				log.warn("Unable to load sound", e);
			}
		}
		return false;
	}

	@Provides
	HitSoundsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(HitSoundsConfig.class);
	}
}
