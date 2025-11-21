package net.runelite.client.plugins.pluginhub.net.wiseoldman.events;

import lombok.Value;
import net.runelite.client.plugins.pluginhub.net.wiseoldman.web.WomRequestType;

@Value
public class WomRequestFailed
{
	String username;
	WomRequestType type;
}
