package net.runelite.client.plugins.pluginhub.xyz.oelderoth.runelite.forestry.domain;

import javax.annotation.Nullable;

@FunctionalInterface
public interface WoodcuttingStateListener
{
	void onWoodcuttingStateChanged(@Nullable WoodcuttingState state);
}
