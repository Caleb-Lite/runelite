package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.data;

import lombok.Value;
import net.runelite.client.config.RuneScapeProfileType;

@Value
public class PlayerProfile
{
	String username;
	RuneScapeProfileType profileType;
}