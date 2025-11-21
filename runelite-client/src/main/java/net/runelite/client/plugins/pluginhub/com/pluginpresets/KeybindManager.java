package net.runelite.client.plugins.pluginhub.com.pluginpresets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import lombok.Setter;
import net.runelite.client.config.Keybind;

/**
 * Container for storing all keybinds from all plugins across all presets
 */
@Singleton
public class KeybindManager
{
	private final HashMap<Keybind, List<PluginPreset>> keybinds;

	@Setter
	private CurrentConfigurations currentConfigurations;

	@Inject
	public KeybindManager()
	{
		this.keybinds = new HashMap<>();
	}

	public void cacheKeybinds(final List<PluginPreset> pluginPresets)
	{
		keybinds.clear();
		pluginPresets.forEach(preset -> {
			final Keybind keybind = preset.getKeybind();
			if (keybind != null)
			{
				// try to add to existing keybind list
				if (keybinds.containsKey(keybind))
				{
					keybinds.get(keybind).add(preset);
				}
				else
				{
					final List<PluginPreset> list = new ArrayList<>();
					list.add(preset);
					keybinds.put(keybind, list);
				}
			}
		});
	}

	public PluginPreset getPresetFor(final Keybind keybind)
	{
		if (keybinds.containsKey(keybind))
		{
			return getNextPreset(keybinds.get(keybind));
		}
		return null;
	}

	public void clearKeybinds()
	{
		keybinds.clear();
	}

	/**
	 * Returns the next preset for the same keybind.
	 * This allows for cycling through presets with the same keybind.
	 *
	 * @param list Plugin preset list for certain keybind
	 */
	private PluginPreset getNextPreset(final List<PluginPreset> list)
	{
		int currentIndex = -1;
		for (int i = 0; i < list.size(); i++)
		{
			final PluginPreset preset = list.get(i);
			if (preset.match(currentConfigurations))
			{
				currentIndex = i;
				break;
			}
		}
		return list.get(currentIndex == list.size() - 1 ? 0 : currentIndex + 1);
	}
}
