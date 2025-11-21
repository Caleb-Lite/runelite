package net.runelite.client.plugins.pluginhub.com.menustoneshider;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.input.KeyListener;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.input.KeyManager;

import javax.inject.Inject;
import java.awt.event.KeyEvent;

@Slf4j
@PluginDescriptor(
        name = "Menu Stones Hider",
        description = "Hides menu stones, background and sidebars",
        tags = {"stone", "hide", "menu", "bars", "background"}
)
public class MenuStonesHiderPlugin extends Plugin implements KeyListener
{
    @Inject
    private Client client;

    @Inject
    private MenuStonesHiderConfig config;

    @Inject
    private KeyManager keyManager;

    private boolean toggled = true;

    @Override
    protected void startUp()
    {
        updateOrShowWidgets(true);
        keyManager.registerKeyListener(this);
    }

    @Override
    protected void shutDown()
    {
        updateOrShowWidgets(false);
        keyManager.unregisterKeyListener(this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if (config.showMenuToggle().matches(e)) {
            toggled = !toggled;
            updateOrShowWidgets(toggled);
        }
    }

    @Override public void keyTyped(KeyEvent e) { }
    @Override public void keyReleased(KeyEvent e) { }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event)
    {
        if (toggled && (event.getGroupId() == InterfaceID.TOPLEVEL_OSRS_STRETCH || event.getGroupId() == InterfaceID.TOPLEVEL_PRE_EOC))
        {
            updateOrShowWidgets(true);
        }
    }

    @Subscribe
    public void onScriptPostFired(ScriptPostFired s)
    {
        if (!toggled && s.getScriptId() == 839)
        {
            updateOrShowWidgets(false);
        }

        if (toggled && (s.getScriptId() == ScriptID.TOPLEVEL_REDRAW || s.getScriptId() == 903))
        {
            updateOrShowWidgets(true);
        }
    }

    @Subscribe
    public void onConfigChanged(ConfigChanged event)
    {
        updateOrShowWidgets(toggled);
    }

    @Provides
    MenuStonesHiderConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(MenuStonesHiderConfig.class);
    }

    private void updateOrShowWidgets(boolean hide)
    {
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_MENU_GRAPHIC1, hide && config.hideLeftBar());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_MENU_GRAPHIC2, hide && config.hideRightBar());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_BACKGROUND, hide && config.hideBackground());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_MENU_GRAPHIC5, hide && config.hideTopMenu());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_TOP, hide && config.hideTopMenu());
        toggleWidget(InterfaceID.ToplevelPreEoc.SIDE_MOVABLE_LAYER, hide && config.hideTopMenu());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_MENU_GRAPHIC3, hide && config.hideBottomMenu());
        toggleWidget(InterfaceID.ToplevelOsrsStretch.SIDE_BOTTOM, hide && config.hideBottomMenu());
        toggleWidget(InterfaceID.ToplevelPreEoc.SIDE_STATIC_LAYER, hide && config.hideBottomMenu());
    }

    private void toggleWidget(int widgetId, boolean hide)
    {
        Widget widget = client.getWidget(widgetId);
        if (widget != null)
        {
            widget.setHidden(hide);
        }
    }
}