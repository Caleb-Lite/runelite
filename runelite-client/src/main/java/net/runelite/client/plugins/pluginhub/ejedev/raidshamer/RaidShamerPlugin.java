package net.runelite.client.plugins.pluginhub.ejedev.raidshamer;
import com.google.inject.Provides;
import lombok.Getter;
import net.runelite.api.*;
import net.runelite.api.events.ActorDeath;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.widgets.Widget;
import net.runelite.api.widgets.ComponentID;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.ui.DrawManager;
import net.runelite.client.util.ImageCapture;
import net.runelite.client.util.ImageUploadStyle;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.function.Consumer;

@PluginDescriptor(
        name = "Death Shamer",
        configName = "RaidShamerPlugin", // Plugin was renamed from raidshamer
        description = "Takes a screenshot of deaths during bosses and raids. Supports discord webhook integration.",
        tags = {"death", "raid", "raids", "shame", "tob", "theater", "cox", "chambers", "toa", "tombs", "discord", "webhook"}
)
public class RaidShamerPlugin extends Plugin {

    @Inject
    private Client client;

    @Inject
    private ImageCapture imageCapture;

    @Inject
    private DrawManager drawManager;

    @Inject
    private ScheduledExecutorService executor;

    @Inject
    private RaidShamerConfig config;

    @Getter
    private boolean inTob;

    @Provides
    RaidShamerConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(RaidShamerConfig.class);
    }

    @Subscribe
    public void onActorDeath(ActorDeath actorDeath)
    {
        Actor actor = actorDeath.getActor();
        if (actor instanceof Player)
        {
            Player player = (Player) actor;
            if (shouldTakeScreenshot(player))
            {
                takeScreenshot("Death of " + player.getName(), "Wall of Shame");
            }
            else {
                System.out.println("[DEBUG] Not in shameable zone sorry.");
            }
        }
    }

    private boolean shouldTakeScreenshot(Player player) {
        boolean isPlayerValidTarget = (config.captureOwnDeaths() && player == client.getLocalPlayer()) ||
                (player.isFriend()) ||
                (!config.captureFriendDeathsOnly() && !player.isFriend()) && player != client.getLocalPlayer();

        boolean inRaid = client.getVarbitValue(Varbits.IN_RAID) > 0;
        Widget toaWidget = client.getWidget(ComponentID.TOA_RAID_LAYER);
        boolean inToa = toaWidget != null;

        boolean isValidLocation = (config.activeInToB() && inTob) ||
                (config.activeInCoX() && inRaid) ||
                (config.activeInToA() && inToa) ||
                (config.activeOutsideOfRaids() && !inTob && !inRaid && !inToa);

        return isValidLocation && isPlayerValidTarget;
    }

    @Subscribe
    public void onVarbitChanged(VarbitChanged event)
    {
        inTob = client.getVarbitValue(Varbits.THEATRE_OF_BLOOD) > 1;
    }

    private void takeScreenshot(String fileName, String subDir)
    {
        Consumer<Image> imageCallback = (img) ->
        {
            executor.submit(() -> {
                try {
                    takeScreenshot(fileName, subDir, img);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        };
        drawManager.requestNextFrameListener(imageCallback);
    }

    private void takeScreenshot(String fileName, String subDir, Image image) throws IOException
    {
        BufferedImage screenshot = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = screenshot.getGraphics();
        int gameOffsetX = 0;
        int gameOffsetY = 0;
        graphics.drawImage(image, gameOffsetX, gameOffsetY, null);
        imageCapture.saveScreenshot(screenshot, fileName, subDir, false, false);
        ByteArrayOutputStream screenshotOutput = new ByteArrayOutputStream();
        ImageIO.write(screenshot, "png", screenshotOutput);

        if (config.webhookEnabled() && !config.webhookLink().equals(""))
        {
            DiscordWebhook FileSender = new DiscordWebhook();
            FileSender.SendWebhook(screenshotOutput, fileName, config.webhookLink());
        }
    }
}

