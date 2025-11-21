package net.runelite.client.plugins.pluginhub.com.andmcadams.wikisync.dps.messages;

import lombok.Value;

@Value
public class Request
{
	RequestType _wsType;
	int sequenceId;
}
