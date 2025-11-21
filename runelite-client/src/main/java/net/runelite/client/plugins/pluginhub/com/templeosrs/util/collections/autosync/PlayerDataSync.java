package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.autosync;

import net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.data.ObtainedCollectionItem;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerDataSync
{
	private String username;
	private String profile;
	private long playerHash;
	private Set<ObtainedCollectionItem> items;
}