package net.runelite.client.plugins.pluginhub.io.robrichardson.skillsorganizer;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.widgets.*;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.callback.ClientThread;
import java.awt.Color;
import java.util.Objects;
import net.runelite.api.events.GameStateChanged;
import static net.runelite.api.widgets.ComponentID.SKILLS_CONTAINER;
import static net.runelite.api.widgets.WidgetUtil.componentToId;

@Slf4j
@PluginDescriptor(
		name = "Skills Organizer"
)
public class SkillsOrganizerPlugin extends Plugin
{
	private static final int SCRIPTID_STATS_SKILLTOTAL = 396;

	@Inject
	private Client client;

	@Inject
	private ClientThread clientThread;

	@Inject
	private SkillsOrganizerConfig config;

	private SkillWidgetGroup[] groups = new SkillWidgetGroup[SkillOrganizerData.values().length];

	@Override
	protected void startUp() throws Exception
	{
		if (client.getGameState() == GameState.LOGGED_IN) {
			clientThread.invoke(this::setupSkillBars);
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		clientThread.invoke(this::resetSkillBars);
	}

	@Provides
	SkillsOrganizerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillsOrganizerConfig.class);
	}

	@Subscribe
	public void onConfigChanged(ConfigChanged event) {
		if (!SkillsOrganizerConfig.GROUP.equals(event.getGroup())) return;

		clientThread.invoke(this::setupSkillBars);
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired event) {
		if (event.getScriptId() == SCRIPTID_STATS_SKILLTOTAL) {
			resetSkillBars();
			setupSkillBars();
		}
	}
	@Subscribe
	public void onGameStateChanged(GameStateChanged event){
	if (event.getGameState() == GameState.HOPPING || event.getGameState() == GameState.LOGIN_SCREEN){
		resetSkillBars();
		}
	}

	private void setupSkillBars() {
		Widget skillsContainer = client.getWidget(SKILLS_CONTAINER);
		if (skillsContainer == null) {
			return;
		}

		for (Widget skillTile : skillsContainer.getStaticChildren()) {
			if (skillTile.getWidth() == 0 || skillTile.getHeight() == 0) {
				clientThread.invokeLater(this::setupSkillBars);
				return;
			}
			int idx = componentToId(skillTile.getId()) - 1;
			SkillOrganizerData skillOrganizerData = SkillOrganizerData.get(idx);
			if(skillOrganizerData == null) continue;

			SkillsOrganizerConfig.SkillPositionOption skillPositionConfig = skillOrganizerData.getSkillPositionConfig().apply(config);
			SkillsOrganizerConfig.SkillVisibilityOption skillVisibilityOption = skillOrganizerData.getSkillVisibilityConfig().apply(config);

			skillTile.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);
			skillTile.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
			skillTile.setOriginalX(skillPositionConfig.getX());
			skillTile.setOriginalY(skillPositionConfig.getY());
			skillTile.revalidate();

			SkillWidgetGroup widgetGroup = groups[idx];
			if(widgetGroup == null) {
				widgetGroup = buildWidgetGroup(skillTile);
			}
			Widget leftWidget = widgetGroup.getLeft();
			Widget rightWidget = widgetGroup.getRight();
			Widget darkenWidget = widgetGroup.getDarken();

			if(skillVisibilityOption == SkillsOrganizerConfig.SkillVisibilityOption.HIDE) {
				skillTile.setHidden(true);
				leftWidget.setHidden(true);
				rightWidget.setHidden(true);
				darkenWidget.setHidden(true);
			} else if(skillVisibilityOption == SkillsOrganizerConfig.SkillVisibilityOption.FADE) {
				skillTile.setHidden(false);
				leftWidget.setHidden(false);
				rightWidget.setHidden(false);
				darkenWidget.setHidden(true);
			} else if(skillVisibilityOption == SkillsOrganizerConfig.SkillVisibilityOption.DARK) {
				skillTile.setHidden(false);
				leftWidget.setHidden(true);
				rightWidget.setHidden(true);
				darkenWidget.setHidden(false);
			} else {
				skillTile.setHidden(false);
				leftWidget.setHidden(true);
				rightWidget.setHidden(true);
				darkenWidget.setHidden(true);
			}
		}
	}

	private SkillWidgetGroup buildWidgetGroup (Widget skillTile) {
		int idx = componentToId(skillTile.getId()) - 1;

		Widget left = skillTile.createChild(-1, WidgetType.GRAPHIC);
		left.setSpriteId(174);
		left.setOriginalWidth(36);
		left.setOriginalHeight(36);
		left.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);
		left.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
		left.setOriginalX(-2);
		left.setOriginalY(-2);
		left.setOpacity(90);
		left.setHidden(true);
		left.revalidate();

		Widget right = skillTile.createChild(-1, WidgetType.GRAPHIC);
		right.setSpriteId(176);
		right.setOriginalWidth(36);
		right.setOriginalHeight(36);
		right.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);
		right.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
		right.setOriginalX(28);
		right.setOriginalY(-2);
		right.setOpacity(90);
		right.setHidden(true);
		right.revalidate();

		int newChildIndex = skillTile.getChildren() != null ? skillTile.getChildren().length : 0;
		Widget darken = skillTile.createChild(newChildIndex, WidgetType.RECTANGLE);
		darken.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);
		darken.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);
		darken.setOriginalX(0);
		darken.setOriginalY(0);
		darken.setYPositionMode(WidgetPositionMode.ABSOLUTE_CENTER);
		darken.setWidthMode(WidgetSizeMode.ABSOLUTE);
		darken.setHeightMode(WidgetSizeMode.ABSOLUTE);
		darken.setOriginalWidth(skillTile.getWidth());
		darken.setOriginalHeight(skillTile.getHeight());
		darken.setFilled(true);
		darken.setTextColor(Color.BLACK.getRGB());
		darken.setOpacity(90);
		darken.setHidden(true);
		darken.revalidate();

		clientThread.invokeLater(() -> {
			darken.setOriginalWidth(skillTile.getWidth());
			darken.setOriginalHeight(skillTile.getHeight());
			darken.revalidate();
		});

		SkillWidgetGroup newGroup = new SkillWidgetGroup(left, right, darken);
		groups[idx] = newGroup;

		return newGroup;
	}

	private void resetSkillBars() {
		Widget skillsContainer = client.getWidget(SKILLS_CONTAINER);
		if (skillsContainer == null) {
			return;
		}

		for (Widget skillTile : skillsContainer.getStaticChildren()) {
			int idx = componentToId(skillTile.getId()) - 1;
			SkillOrganizerData skillOrganizerData = SkillOrganizerData.get(idx);
			if(skillOrganizerData == null) continue;

			// Reset position back to default
			skillTile.setOriginalX(skillOrganizerData.getDefaultPosition().getX());
			skillTile.setXPositionMode(WidgetPositionMode.ABSOLUTE_LEFT);

			skillTile.setOriginalY(skillOrganizerData.getDefaultPosition().getY());
			skillTile.setYPositionMode(WidgetPositionMode.ABSOLUTE_TOP);

			skillTile.revalidate();

			// Show tile if hidden
			skillTile.setHidden(false);
		}

		for (SkillWidgetGroup widgetGroup : groups) {
			if (widgetGroup == null) {
				continue;
			}
			Widget parent = widgetGroup.getLeft().getParent();
			Widget[] children = parent.getChildren();
			for (int i = 0; i < Objects.requireNonNull(children).length; i++) {
				Widget child = children[i];
				if (widgetGroup.contains(child)) {
					children[i] = null;
				}
			}
		}

		groups = new SkillWidgetGroup[SkillOrganizerData.values().length];
	}
}
