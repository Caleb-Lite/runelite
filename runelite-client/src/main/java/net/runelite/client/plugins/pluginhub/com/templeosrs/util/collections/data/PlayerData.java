package net.runelite.client.plugins.pluginhub.com.templeosrs.util.collections.data;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerData
{
	public int totalCollectionsAvailable;
	public Set<ObtainedCollectionItem> obtainedItems;
}