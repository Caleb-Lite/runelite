package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public enum BetterGodwarsOverlayGods {

	ARMADYL("Armadyl", BetterGodwarsOverlayVarbits.GWD_ARMADYL_KC, "armadyl.png"),
	BANDOS("Bandos", BetterGodwarsOverlayVarbits.GWD_BANDOS_KC, "bandos.png"),
	SARADOMIN("Saradomin", BetterGodwarsOverlayVarbits.GWD_SARADOMIN_KC, "saradomin.png"),
	ZAMORAK("Zamorak", BetterGodwarsOverlayVarbits.GWD_ZAMORAK_KC, "zamorak.png"),
	ZAROS("Ancient", BetterGodwarsOverlayVarbits.GWD_ZAROS_KC, "zaros.png");

	private final String name;

	private final BetterGodwarsOverlayVarbits killCountVarbit;

	private final String image;
}
