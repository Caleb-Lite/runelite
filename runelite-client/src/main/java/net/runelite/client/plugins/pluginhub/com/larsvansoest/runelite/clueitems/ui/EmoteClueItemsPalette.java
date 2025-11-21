package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.client.ui.ColorScheme;

import javax.swing.*;
import java.awt.*;

/**
 * Contains all {@link Color} instances used for any ui-related requirement progress display {@link JComponent} objects for the {@link com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPanel}.
 *
 * @author Lars van Soest
 * @since 2.0.0
 */
@Getter
@RequiredArgsConstructor
public enum EmoteClueItemsPalette
{
	RUNELITE(
			ColorScheme.DARKER_GRAY_COLOR,
			ColorScheme.DARKER_GRAY_HOVER_COLOR,
			ColorScheme.DARKER_GRAY_HOVER_COLOR,
			ColorScheme.DARK_GRAY_HOVER_COLOR,
			ColorScheme.LIGHT_GRAY_COLOR,
			ColorScheme.MEDIUM_GRAY_COLOR,
			ColorScheme.DARKER_GRAY_HOVER_COLOR,
			ColorScheme.BRAND_ORANGE,
			ColorScheme.LIGHT_GRAY_COLOR,
			new Color(ColorScheme.LIGHT_GRAY_COLOR.getRed(), ColorScheme.LIGHT_GRAY_COLOR.getGreen(), ColorScheme.LIGHT_GRAY_COLOR.getBlue(), 150)
	);

	private final Color defaultColor;
	private final Color hoverColor;
	private final Color selectColor;
	private final Color foldContentColor;
	private final Color foldHeaderTextColor;
	private final Color footerColor;
	private final Color disclaimerColor;
	private final Color brandingColor;
	private final Color propertyNameColor;
	private final Color propertyValueColor;
}