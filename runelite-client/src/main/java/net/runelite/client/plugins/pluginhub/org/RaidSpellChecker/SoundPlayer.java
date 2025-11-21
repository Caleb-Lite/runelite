package net.runelite.client.plugins.pluginhub.org.RaidSpellChecker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;
import net.runelite.client.audio.AudioPlayer;

import javax.inject.Inject;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SoundPlayer
{
    private final AudioPlayer audioPlayer;
    private final RaidSpellCheckerConfig config;

    public void play(String fileName, int volume)
    {
        try
        {
            float gainDb = 20f * (float) Math.log10(config.soundVolume() / 100f);
            audioPlayer.play(this.getClass(), "/sounds/" + fileName, gainDb);
        }
        catch (Exception e)
        {
            log.error(e.getMessage(),e);
        }
    }
}

