package net.runelite.client.plugins.pluginhub.com.stevenwaterman.blindfold;

import com.google.inject.Provides;
import java.util.Objects;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Actor;
import net.runelite.api.Client;
import net.runelite.api.DynamicObject;
import net.runelite.api.GameObject;
import net.runelite.api.GameState;
import net.runelite.api.GraphicsObject;
import net.runelite.api.Model;
import net.runelite.api.ModelData;
import net.runelite.api.Projectile;
import net.runelite.api.Renderable;
import net.runelite.api.RuneLiteObject;
import net.runelite.api.Scene;
import net.runelite.api.Tile;
import net.runelite.api.TileItem;
import net.runelite.api.TileObject;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.callback.RenderCallback;
import net.runelite.client.callback.RenderCallbackManager;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.KeyManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.HotkeyListener;

@PluginDescriptor(
	name = "Blindfold",
	description = "Stops things rendering (requires GPU)",
	tags = {"blindfold", "blind", "black", "greenscreen", "render"}
)
@Slf4j
public class BlindfoldPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private BlindfoldPluginConfig config;

	@Inject
	private BlindfoldOverlay overlay;

	@Inject
	private RenderCallbackManager renderCallbackManager;

	private boolean enabled;

	@Inject
    private KeyManager keyManager;
    private final HotkeyListener hotkeyListener = new HotkeyListener(() -> config.hotKey()) {
        public void hotkeyPressed() {
			if (enabled){
				turnOff();
			}
			else {
				turnOn();
			}
		}
    };

	@Provides
	BlindfoldPluginConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BlindfoldPluginConfig.class);
	}

	private final RenderCallback DISABLE_RENDERING = new RenderCallback(){
		@Override
		public boolean addEntity(Renderable renderable, boolean ui)
		{
			return false;
		}

		@Override
		public boolean drawTile(Scene scene, Tile tile)
		{
			return false;
		}

		@Override
		public boolean drawObject(Scene scene, TileObject object)
		{
			return false;
		}
	};

	private final RenderCallback rcb = new RenderCallback()
	{
		@Override
		public boolean drawTile(Scene scene, Tile tile)
		{
			return config.enableTerrain();
		}

		@Override
		public boolean drawObject(Scene scene, TileObject object)
		{
			if (object instanceof GameObject)
			{
				Renderable renderable = ((GameObject) object).getRenderable();

				if (renderable == client.getLocalPlayer())
				{
					return true;
				}

				if (renderable instanceof Projectile ||
					renderable instanceof TileItem ||
					renderable instanceof Actor)
				{
					return config.enableEntities();
				}

				if (renderable instanceof RuneLiteObject)
				{
					return config.enableRuneLiteObjects();
				}

				if (renderable instanceof Model ||
					renderable instanceof ModelData ||
					renderable instanceof DynamicObject ||
					renderable instanceof GraphicsObject)
				{
					return config.enableScenery();
				}
				return true;
			}
			else {
				return config.enableScenery();
			}
		}
	};

	@Override
	protected void startUp()
	{
		turnOn();
		keyManager.registerKeyListener(hotkeyListener);
	}

	private void turnOn()
	{
		enabled = true;
		overlayManager.add(overlay);
		clientThread.invokeLater(() ->
			{
				renderCallbackManager.register(rcb);

				if (client.getGameState() == GameState.LOGGED_IN)
					client.setGameState(GameState.LOADING);
			}
		);
	}

	@Override
	protected void shutDown()
	{
		turnOff();
		keyManager.unregisterKeyListener(hotkeyListener);
	}

	private void turnOff()
	{
		enabled = false;
		overlayManager.remove(overlay);

		clientThread.invoke(() ->
			{
				renderCallbackManager.unregister(rcb);
//				renderCallbackManager.unregister(DISABLE_RENDERING);

				if (!config.enableScenery() || !config.enableTerrain()){
					if (client.getGameState() == GameState.LOGGED_IN)
						client.setGameState(GameState.LOADING);
				}
			}
		);
	}

//	@Subscribe
//	public void onGameStateChanged(GameStateChanged e){
//		if (e.getGameState() == GameState.LOGGED_IN){
//
//		}
//	}

//	@Subscribe
//	public void onFocusChanged(FocusChanged event)
//	{
//		if (!event.isFocused() && config.disableRendering() && client.getGameState() == GameState.LOGGED_IN){
//			clientThread.invoke(() ->
//				{
//					renderCallbackManager.unregister(rcb);
//					renderCallbackManager.register(DISABLE_RENDERING);
//				}
//			);
//			log.debug("Focus lost: rendering disabled");
//		}
//		else
//		{
//			clientThread.invoke(() ->
//				{
//					renderCallbackManager.register(rcb);
//					renderCallbackManager.unregister(DISABLE_RENDERING);
//					client.setGameState(GameState.LOADING);
//				}
//			);
//			log.debug("Focus gained: rendering reenabled");
//		}
//	}

//	@Subscribe
//	public void onNotificationFired(NotificationFired event){
//		if (config.disableRendering())
//		{
//			clientThread.invoke(() ->
//				{
//					renderCallbackManager.register(rcb);
//					renderCallbackManager.unregister(DISABLE_RENDERING);
//					client.setGameState(GameState.LOADING);
//				}
//			);
//			log.debug("notification sent: rendering reenabled");
//		}
//	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event){
		if (!Objects.equals(event.getGroup(), BlindfoldPluginConfig.GROUP)){
			return;
		}
//		if (Objects.equals(event.getKey(), "disableRendering")){
//			if (Objects.equals(event.getNewValue(), "false")){
//				clientThread.invoke(() ->
//					{
//						renderCallbackManager.register(rcb);
//						renderCallbackManager.unregister(DISABLE_RENDERING);
//						client.setGameState(GameState.LOADING);
//					}
//				);
//			}
//			else {
//				clientThread.invoke(() ->
//					{
//						renderCallbackManager.unregister(rcb);
//						renderCallbackManager.register(DISABLE_RENDERING);
//						client.setGameState(GameState.LOADING);
//					}
//				);
//			}
//		}

		if (Objects.equals(event.getKey(), "enableTerrain") || Objects.equals(event.getKey(), "enableScenery"))
		{
			clientThread.invokeLater(() -> {
				if (client.getGameState() == GameState.LOGGED_IN)
					client.setGameState(GameState.LOADING);
			});
		}
	}
}
