package net.runelite.client.plugins.pluginhub.com.BetterGodwarsOverlay;

import lombok.Getter;
import lombok.Setter;
import net.runelite.client.util.ImageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BetterGodwarsGodInfo {

	private final BetterGodwarsOverlayPlugin plugin;

	@Getter
	private final BetterGodwarsOverlayGods god;

	private final BufferedImage infoBoxIcon;

	@Getter
	@Setter
	boolean hidden;

	@Getter
	private int currentKc = 0;

	private BetterGodwarsInfoBox infoBox = null;

	public BetterGodwarsGodInfo(BetterGodwarsOverlayPlugin plugin, BetterGodwarsOverlayGods god, boolean hidden) {
		this.plugin = plugin;
		this.god = god;
		this.hidden = hidden;
		this.infoBoxIcon = ImageUtil.loadImageResource(getClass(), god.getImage());
	}

	public void update() {
		updateHidden();
		updateKc();
		updateInfoBox();
	}

	public void remove() {
		if (infoBox != null) {
			plugin.infoBoxManager.removeInfoBox(infoBox);
			infoBox = null;
		}
	}

	private void updateHidden() {
		boolean showInfoBox = plugin.config.showInfoBox(); // config show all infoBoxes
		showInfoBox &= !getConfigIsHidden(); // config show this gwd infoBox
		showInfoBox &= plugin.playerInGwd(); // player in gwd
		showInfoBox &= plugin.config.showZeroKc() || getCurrentKc() > 0;  // config show infoBox with zero kc

		setHidden(!showInfoBox);
	}

	public void updateKc() {
		currentKc = plugin.client.getVarbitValue(god.getKillCountVarbit().getId());
	}

	public Color getTextColor() {
		return currentKc >= plugin.getRequiredKc() ? plugin.config.highlightOnKCColor() : Color.WHITE;
	}

	public void updateInfoBox() {
		if (infoBox != null && isHidden()) {
			plugin.infoBoxManager.removeInfoBox(infoBox);
			infoBox = null;
		} else if (infoBox == null && !isHidden()) {
			infoBox = new BetterGodwarsInfoBox(infoBoxIcon, plugin, this);
			plugin.infoBoxManager.addInfoBox(infoBox);
		}
	}

	boolean getConfigIsHidden() {
		switch (god.getName()) {
			case "Armadyl":
				return plugin.config.hideArmadyl();
			case "Bandos":
				return plugin.config.hideBandos();
			case "Saradomin":
				return plugin.config.hideSaradomin();
			case "Zamorak":
				return plugin.config.hideZamorak();
			case "Ancient":
				return plugin.config.hideAncient();
			default:
				return false;
		}
	}
}
