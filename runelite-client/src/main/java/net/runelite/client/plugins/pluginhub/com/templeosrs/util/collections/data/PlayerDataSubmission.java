package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.data;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class PlayerDataSubmission
{
	String username;
	String profile;
	long playerHash;
	PlayerData data;
}