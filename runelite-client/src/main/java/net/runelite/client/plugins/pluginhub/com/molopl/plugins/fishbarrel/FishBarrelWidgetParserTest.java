package net.runelite.client.plugins.pluginhub.com.molopl.plugins.fishbarrel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FishBarrelWidgetParserTest
{
	private final FishBarrelWidgetParser parser = new FishBarrelWidgetParser();

	@Test
	public void testInvalidMessages()
	{
		assertEquals(-1, parser.parse("Hello, World!"));
		assertEquals(-1, parser.parse("28 x Raw swordfish"));
	}

	@Test
	public void testValidEmptyMessage()
	{
		assertEquals(0, parser.parse("The barrel is empty."));
	}

	@Test
	public void testValidMessages()
	{
		assertEquals(27, parser.parse(String.join("<br>",
			"The barrel contains:",
			"27 x Raw anglerfish")));
		assertEquals(12, parser.parse(String.join("<br>",
			"The barrel contains:",
			"1 x Raw anglerfish, 2 x Raw monkfish, 3 x Raw",
			"shrimps, 1 x Raw anchovies, 1 x Raw salmon, 1 x Raw",
			"cod, 1 x Raw macerel, 1 x Raw tuna, 1 x Raw bass")));
	}
}

