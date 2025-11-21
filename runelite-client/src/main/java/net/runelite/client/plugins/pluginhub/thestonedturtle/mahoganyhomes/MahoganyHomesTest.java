package net.runelite.client.plugins.pluginhub.thestonedturtle.mahoganyhomes;

import java.util.regex.Matcher;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MahoganyHomesTest
{
	@Test
	public void testAssignmentRegexPattern()
	{
		matchContractAssignment("Jess", "Please could you go see Jess, upstairs of the building south of the church in East Ardougne? You can get another job once you have furnished her home.");
		matchContractAssignment("Jess", "Go see Jess, upstairs of the building south of the church in East Ardougne. You can get another job once you have furnished her home.");

		matchContractAssignment("Sarah", "Please could you go see Sarah along the south wall of Varrock? You can get another job once you have furnished her home.");
		matchContractAssignment("Sarah", "Go see Sarah along the south wall of Varrock. You can get another job once you have furnished her home.");

		matchContractAssignment("Bob", "Please could you go see Bob in north-east Varrock, opposite the church? You can get another job once you have furnished his home.");
		matchContractAssignment("Bob", "Go see Bob in north-east Varrock, opposite the church. You can get another job once you have furnished his home.");

		matchContractAssignment("Barbara", "Please could you go see Barbara, south of Hosidius, near the mill for us? You can get another job once you have furnished her home.");
	}

	private void matchContractAssignment(final String name, final String message)
	{
		final Matcher matcher = MahoganyHomesPlugin.CONTRACT_PATTERN.matcher(message);
		assertTrue(matcher.matches());
		assertEquals(name, matcher.group(2));
	}

	@Test
	public void testReminderRegexPattern()
	{
		matchReminderContract("Expert", "Ross", "You're currently on an Expert Contract. Go see Ross, north of the church in East Ardounge. You can get another job once you have furnished his home.");
		matchReminderContract("Expert", "Jess", "You're currently on an Expert Contract. Go see Jess, upstairs of the building south of the church in East Ardougne. You can get another job once you have furnished her home.");
		matchReminderContract("Expert", "Barbara", "You're currently on an Expert Contract. Go see Barbara, south of Hosidius, near the mill. You can get another job once you have furnished her home.");
		matchReminderContract("Hard", "Bob", "You're currently on a Hard Contract. Go see Bob in north-east Varrock, opposite the church. You can get another job once you have furnished his home.");
        matchReminderContract("Novice", "Barbara", "You're currently on a Novice Contract. Go see Barbara, south of Hosidius, near the mill. You can get another job once you have furnished her home.");
	}

	private void matchReminderContract(final String tier, final String name, final String message)
	{
		final Matcher matcher = MahoganyHomesPlugin.REMINDER_PATTERN.matcher(message);
		assertTrue(matcher.matches());
		assertEquals(tier, matcher.group(1));
		assertEquals(name, matcher.group(2));
	}
}
