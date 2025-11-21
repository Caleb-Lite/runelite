package net.runelite.client.plugins.pluginhub.com.rolerandomizer;

import java.util.List;
import lombok.Value;

@Value
public class PlayerPrefs
{
	String name;
	List<MetaRoleInfo> prefs;
}
