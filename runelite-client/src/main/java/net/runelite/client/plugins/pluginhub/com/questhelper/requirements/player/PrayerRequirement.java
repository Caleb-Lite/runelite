package net.runelite.client.plugins.pluginhub.com.questhelper.requirements.player;

import net.runelite.client.plugins.pluginhub.com.questhelper.requirements.AbstractRequirement;
import net.runelite.api.Client;
import net.runelite.api.Prayer;

import javax.annotation.Nonnull;

/**
 * Requirement that checks if a specified {@link Prayer} is active
 */
public class PrayerRequirement extends AbstractRequirement
{
	private final Prayer prayer;
	private final String text;

	/**
	 * Checks if the {@link Prayer} is currently active.
	 *
	 * @param text the display text
	 * @param prayer the {@link Prayer} to check
	 */
	public PrayerRequirement(String text, Prayer prayer)
	{
		assert(prayer != null);
		this.prayer = prayer;
		this.text = text;
	}

	@Override
	public boolean check(Client client)
	{
		int currentPrayer = client.getVarbitValue(prayer.getVarbit());
		return currentPrayer == 1;
	}

	@Nonnull
	@Override
	public String getDisplayText()
	{
		return text;
	}
}
