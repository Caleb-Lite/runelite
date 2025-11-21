package net.runelite.client.plugins.pluginhub.com.whosgonnapoop;

import java.awt.Color;

import net.runelite.client.config.*;

@ConfigGroup(WhosGonnaPoopConfig.MAIN_GROUP)
public interface WhosGonnaPoopConfig extends Config
{
	String MAIN_GROUP = "whosgonnapoop";

	@ConfigItem(
            keyName = "nextPhaseHotkey",
            name = "Next Phase",
            description = "Increment the poop orbs",
            position = 1
    )
    default Keybind nextPhaseHotkey() {
        return Keybind.NOT_SET;
    }
}
