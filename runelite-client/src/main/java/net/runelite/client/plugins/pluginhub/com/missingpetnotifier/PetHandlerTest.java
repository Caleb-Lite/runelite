package net.runelite.client.plugins.pluginhub.com.missingpetnotifier;

import static com.missingpetnotifier.PetHandler.itemMatchesPet;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class PetHandlerTest
{
	@Before
	public void init()
	{
		PetHandler.init();
	}

	@Test
	public void GIVEN_matchingItemAndNpcNames_WHEN_itemMatchesPet_THEN_returnsTrue()
	{
		String npcName = "Jal-Nib-Rek";
		String itemName = "jal-nib-rek";

		assertTrue(itemMatchesPet(itemName, npcName));
	}

	@Test
	public void GIVEN_npcVariants_WHEN_itemMatchesPet_THEN_returnsTrue()
	{
		String npcName = "Tektiny";
		String itemName = "Flying vespina";

		assertTrue(itemMatchesPet(itemName, npcName));
	}

	@Test
	public void GIVEN_itemAndNpcMismatch_WHEN_itemMatchesPet_THEN_returnsTrue()
	{
		String npcName = "K'ril Tsutsaroth Jr.";
		String itemName = "Pet k'ril tsutsaroth";

		assertTrue(itemMatchesPet(itemName, npcName));
	}
}
