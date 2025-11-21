package net.runelite.client.plugins.pluginhub.net.wiseoldman.events;

import net.runelite.client.plugins.pluginhub.net.wiseoldman.beans.GroupInfoWithMemberships;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WomGroupSynced
{
	GroupInfoWithMemberships groupInfo;
	boolean silent;

	public WomGroupSynced(GroupInfoWithMemberships groupInfo)
	{
		this(groupInfo, false);
	}
}
