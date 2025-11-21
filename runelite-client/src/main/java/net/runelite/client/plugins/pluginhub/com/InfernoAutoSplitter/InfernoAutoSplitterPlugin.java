package net.runelite.client.plugins.pluginhub.com.InfernoAutoSplitter;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ChatMessage;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.ClientToolbar;
import net.runelite.client.ui.NavigationButton;
import net.runelite.client.util.ImageUtil;

import javax.inject.Inject;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;

@Slf4j
@PluginDescriptor(
        name = "Inferno AutoSplitter",
        enabledByDefault = false,
        description = "Automatically splits for LiveSplit in Inferno"
)
public class InfernoAutoSplitterPlugin extends Plugin {

    // The tick we entered the instance
    private int lastTick = 0;

    // The variables to interact with livesplit
    PrintWriter writer;

    // The waves we have splits for
    private final int[] SPLIT_WAVES = new int[] {9, 18, 25, 35, 42, 50, 57, 60, 63, 66, 67, 68, 69};

    @Inject
    public Client client;

    @Inject
    private InfernoAutoSplitterConfig config;

    @Inject
    private ClientToolbar clientToolbar;

    // side panel
    private NavigationButton navButton;
    private InfernoAutoSplitterPanel panel;

    @Provides
    InfernoAutoSplitterConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(InfernoAutoSplitterConfig.class);
    }


    /*
    void startUp
    The function is called when Runelite loads the plugin or is enabled by the user. We create the panel and give it
    access to what it needs
    Parameters:
        None
    Returns:
        None
     */
    @Override
    protected void startUp()
    {
        final BufferedImage icon = ImageUtil.loadImageResource(getClass(), "/inferno_split_icon.png");
        panel = new InfernoAutoSplitterPanel(client, writer, config, this);
        navButton = NavigationButton.builder().tooltip("Inferno Autosplit")
                .icon(icon).priority(6).panel(panel).build();
        clientToolbar.addNavigation(navButton);

        panel.startPanel();
    }

    /*
    void shutDown
    Called when the user disables the plugin. We disconnect from the LiveSplit Server
    Parameters:
        None
    Returns:
        None
     */
    @Override
    protected void shutDown()
    {
        clientToolbar.removeNavigation(navButton);
        panel.disconnect();  // terminates active socket
    }

    /*
    void onChatMessage
    Called every time the client receives a message in the chat box. For each message we check to see if the text contains what wave we're on in the inferno
    Parameters:
        event (ChatMessage): The object that contains the chat message text
    Returns:
        None
     */
    @Subscribe
    public void onChatMessage(ChatMessage event) {

        // Does the message received show
        if (event.getMessage().contains("Wave:")) {

            boolean foundWave = false;
            int i = 0;

            while (i < SPLIT_WAVES.length && !foundWave) {

                if (event.getMessage().contains("Wave: " + SPLIT_WAVES[i])) {

                    foundWave = true;
                    sendMessage("split");
                }
                i++;
            }
        } else if (event.getMessage().contains("Your TzKal-Zuk")) {
            sendMessage("split");
        }
    }

    /*
    void onGameTick
    Called each game tick. We check to see if we're in the inferno and if it has been 10 ticks since entering, we tell LiveSplit to start the timer
    Parameters:
        None
    Returns:
        None
     */
    @Subscribe
    public void onGameTick(GameTick event) {

        // Are we in the inferno?
        if (lastTick != -1 && isInCaves()) {

            // Get the time since entering the instance
            int currTick = client.getTickCount() - lastTick;

            // If the time since is 10, we tell livesplit to reset and then start the timer
            if (currTick == 11) {
                sendMessage("reset");
                sendMessage("starttimer");
            }
        }
    }

    /*
    void onGameStateChanged
    Called when the game state of the client changes. This event fires when we jump into the inferno and some other non related reasons
    Parameters:
        event (GameStateChanged): The object which contains the value of the current game state. We want LOGGED_IN
    Returns:
        None
     */
    @Subscribe
    public void onGameStateChanged(GameStateChanged event) {

        if (!event.getGameState().equals(GameState.LOGGED_IN)) {

            if (client.getTickCount() == 0) {
                lastTick = -1;
            } else {
                lastTick = client.getTickCount();
            }

        }
    }

    /*
    boolean isInCaves
    Lets us know if we are in the inferno
    Parameters:
        None
    Returns:
        True if we are in the inferno, false otherwise
     */
    public boolean isInCaves() {
        return client.getVarbitValue(11878) == 1;
    }

    /*
    void sendMessage
    Sends a message to the LiveSplit server
    Parameters:
        message (String): The message we are sending
    Returns:
        None
     */
    private void sendMessage(String message) {

        if (writer != null) {
            writer.write(message + "\r\n");
            writer.flush();
        }
    }
}
