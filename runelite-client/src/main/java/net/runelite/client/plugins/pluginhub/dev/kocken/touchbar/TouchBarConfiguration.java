package net.runelite.client.plugins.pluginhub.dev.kocken.touchbar;

import net.runelite.client.plugins.pluginhub.dev.kocken.TouchBarPluginConfig;

import java.util.LinkedHashMap;
import java.util.Map;

public class TouchBarConfiguration {

    public Map<String, Integer> topRowButtonsConfiguration(TouchBarPluginConfig config){
        return new LinkedHashMap<String, Integer>() {{
            put("combat", config.combat().getKeyCode());
            put("skills", config.skills().getKeyCode());
            put("quests", config.quests().getKeyCode());
            put("inventory", config.inventory().getKeyCode());
            put("equipment", config.equipment().getKeyCode());
            put("prayer", config.prayer().getKeyCode());
            put("spellbook", config.magic().getKeyCode());
        }};
    }

    public Map<String, Integer> bottomRowButtonsConfiguration(TouchBarPluginConfig config){
        return new LinkedHashMap<String, Integer>() {{
            put("chat", config.chat().getKeyCode());
            put("friends", config.friends().getKeyCode());
            put("account", config.account().getKeyCode());
            put("logout", config.logout().getKeyCode());
            put("settings", config.settings().getKeyCode());
            put("emotes", config.emotes().getKeyCode());
            put("music", config.music().getKeyCode());
        }};
    }
}

/**
 * JTouchBar
 *
 * Copyright (c) 2018 - 2019 thizzer.com
 *
 * This software may be modified and distributed under the terms
 * of the MIT license.  See the LICENSE file for details.
 *
 * @author  	M. ten Veldhuis
 */