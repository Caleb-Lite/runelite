package net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.clues;

import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.EmoteClueItemsImages;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.data.EmoteClue;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.data.EmoteClueAssociations;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.data.EmoteClueDifficulty;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.data.EmoteClueItem;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.EmoteClueItemsPalette;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components.ItemCollectionPanel;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.components.RequirementPanel;
import net.runelite.client.plugins.pluginhub.com.larsvansoest.runelite.clueitems.ui.stashes.StashUnitPanel;
import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Displays data of a {@link com.larsvansoest.runelite.clueitems.data.EmoteClueItem}. Implements {@link com.larsvansoest.runelite.clueitems.ui.components.FoldablePanel}.
 *
 * @author Lars van Soest
 * @since 2.0.0
 */
public class EmoteClueItemPanel extends RequirementPanel
{
	@Getter
	private final EmoteClueDifficulty[] difficulties;
	@Getter
	private final int quantity;
	private final ArrayList<StashUnitPanel> stashUnitPanels;
	private ItemCollectionPanel itemCollectionPanel;

	/**
	 * Creates the panel.
	 *
	 * @param palette       Colour scheme for the panel.
	 * @param emoteClueItem EmoteClueItem of which data is displayed by this panel.
	 */
	public EmoteClueItemPanel(final EmoteClueItemsPalette palette, final EmoteClueItem emoteClueItem)
	{
		super(palette, emoteClueItem.getCollectiveName(), 160, 20);

		final EmoteClue[] emoteClues = EmoteClueAssociations.EmoteClueItemParentToEmoteClues.get(emoteClueItem);

		this.stashUnitPanels = new ArrayList<>();
		this.difficulties = Arrays.stream(emoteClues).map(EmoteClue::getEmoteClueDifficulty).distinct().toArray(EmoteClueDifficulty[]::new);
		final Insets insets = new Insets(2, 0, 2, 5);
		Arrays
				.stream(this.difficulties)
				.map(EmoteClueItemsImages.Icons.RuneScape.EmoteClue.Ribbon::get)
				.map(ImageIcon::new)
				.map(JLabel::new)
				.forEach(label -> super.addRight(label, insets, 0, 0, DisplayMode.Default));
		this.quantity = emoteClues.length;
		super.addRight(new JLabel(String.valueOf(this.quantity)), insets, 0, 0, DisplayMode.Default);
	}

	/**
	 * Specify the {@link ItemCollectionPanel} containing all items required to complete the {@link com.larsvansoest.runelite.clueitems.data.EmoteClueItem} requirement.
	 *
	 * @param itemCollectionPanel Item collection panel displaying items required to complete the {@link com.larsvansoest.runelite.clueitems.data.EmoteClueItem} requirement.
	 * @param displayModes        Specify when the panel should be displayed.
	 */
	public void setItemCollectionPanel(final ItemCollectionPanel itemCollectionPanel, final DisplayMode... displayModes)
	{
		if (Objects.nonNull(this.itemCollectionPanel))
		{
			super.removeChild(itemCollectionPanel);
		}
		final Runnable onHeaderMousePressed = itemCollectionPanel.getOnHeaderMousePressed();
		itemCollectionPanel.setOnHeaderMousePressed(() ->
		{
			this.stashUnitPanels.stream().map(StashUnitPanel::getItemCollectionPanel).filter(Objects::nonNull).forEach(ItemCollectionPanel::fold);
			onHeaderMousePressed.run();
		});
		this.itemCollectionPanel = itemCollectionPanel;
		super.addChild(itemCollectionPanel, displayModes);
	}

	/**
	 * Add a sub-display {@link com.larsvansoest.runelite.clueitems.ui.stashes.StashUnitPanel} entry to display a {@link com.larsvansoest.runelite.clueitems.data.StashUnit}.
	 *
	 * @param stashUnitPanel The sub-display which displays {@link com.larsvansoest.runelite.clueitems.data.StashUnit} data.
	 * @param displayModes   Specify when the panel should be displayed.
	 */
	public void addStashUnitPanel(final StashUnitPanel stashUnitPanel, final DisplayMode... displayModes)
	{
		final ItemCollectionPanel stashUnitItemCollectionPanel = stashUnitPanel.getItemCollectionPanel();
		final Runnable onHeaderMousePressed = stashUnitItemCollectionPanel.getOnHeaderMousePressed();
		stashUnitItemCollectionPanel.setOnHeaderMousePressed(() ->
		{
			if (Objects.nonNull(this.itemCollectionPanel))
			{
				this.itemCollectionPanel.fold();
			}
			onHeaderMousePressed.run();
		});
		this.stashUnitPanels.add(stashUnitPanel);
		super.addChild(stashUnitPanel, displayModes);
	}
}