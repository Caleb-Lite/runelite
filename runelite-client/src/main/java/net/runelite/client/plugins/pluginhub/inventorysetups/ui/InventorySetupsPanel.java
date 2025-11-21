package net.runelite.client.plugins.pluginhub.inventorysetups.ui;

import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetup;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsSection;
import net.runelite.client.plugins.pluginhub.inventorysetups.InventorySetupsPlugin;

import java.util.Arrays;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

// The base class for panels that each display a setup
public class InventorySetupsPanel extends JPanel implements InventorySetupsMoveHandler<InventorySetup>
{

	protected final InventorySetupsPlugin plugin;
	protected final InventorySetupsPluginPanel panel;
	protected final InventorySetup inventorySetup;
	protected InventorySetupsSection section;
	protected final JPopupMenu popupMenu;

	InventorySetupsPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel, InventorySetup invSetup, InventorySetupsSection section)
	{
		this(plugin, panel, invSetup, section, true);
	}

	InventorySetupsPanel(InventorySetupsPlugin plugin, InventorySetupsPluginPanel panel, InventorySetup invSetup, InventorySetupsSection section, boolean allowEditable)
	{
		this.plugin = plugin;
		this.panel = panel;
		this.inventorySetup = invSetup;
		this.section = section;

		if (allowEditable)
		{
			this.popupMenu = new InventorySetupsMoveMenu<>(plugin, panel, this, "Inventory Setup", invSetup);
		}
		else
		{
			this.popupMenu = new JPopupMenu();
		}

		JMenuItem addToSection = new JMenuItem("Add Setup to Sections..");
		popupMenu.add(addToSection);

		// If the section is not null, then add a menu to remove this setup from that section
		if (this.section != null && allowEditable)
		{
			JMenuItem removeFromSection = new JMenuItem("Remove from section");
			removeFromSection.addActionListener(e ->
			{
				plugin.removeInventorySetupFromSection(invSetup, section);
			});

			popupMenu.add(removeFromSection);
		}

		addToSection.addActionListener(e ->
		{
			if (plugin.getSections().isEmpty())
			{
				JOptionPane.showMessageDialog(panel,
					"You must create a section first",
					"No Sections to Add Setup To",
					JOptionPane.ERROR_MESSAGE);
				return;
			}

			final String[] sectionNames = plugin.getSections().stream().map(InventorySetupsSection::getName).toArray(String[]::new);
			Arrays.sort(sectionNames, String.CASE_INSENSITIVE_ORDER);
			final String message = "Select sections to add this setup to";
			final String title = "Select Sections";
			InventorySetupsSelectionPanel selectionDialog = new InventorySetupsSelectionPanel(panel, title, message, sectionNames);
			selectionDialog.setOnOk(e1 ->
			{
				List<String> selectedSections = selectionDialog.getSelectedItems();

				if (!selectedSections.isEmpty())
				{
					plugin.addSetupToSections(invSetup, selectedSections);
				}
			});
			selectionDialog.show();
		});

		JMenuItem deleteSetup = new JMenuItem("Delete Setup..");
		deleteSetup.addActionListener(e -> plugin.removeInventorySetup(inventorySetup));

		popupMenu.add(deleteSetup);
		setComponentPopupMenu(popupMenu);
	}

	@Override
	public void moveUp(final InventorySetup invSetup)
	{
		if (plugin.getConfig().sectionMode())
		{
			int invIndex = section.getSetups().indexOf(invSetup.getName());
			plugin.moveSetupWithinSection(section, invIndex, invIndex - 1);
		}
		else
		{
			int invIndex = plugin.getInventorySetups().indexOf(invSetup);
			plugin.moveSetup(invIndex, invIndex - 1);
		}

	}

	@Override
	public void moveDown(final InventorySetup invSetup)
	{
		if (plugin.getConfig().sectionMode())
		{
			int invIndex = section.getSetups().indexOf(invSetup.getName());
			plugin.moveSetupWithinSection(section, invIndex, invIndex + 1);
		}
		else
		{
			int invIndex = plugin.getInventorySetups().indexOf(invSetup);
			plugin.moveSetup(invIndex, invIndex + 1);
		}

	}

	@Override
	public void moveToTop(final InventorySetup invSetup)
	{
		if (plugin.getConfig().sectionMode())
		{
			int invIndex = section.getSetups().indexOf(invSetup.getName());
			plugin.moveSetupWithinSection(section, invIndex, 0);
		}
		else
		{
			int invIndex = plugin.getInventorySetups().indexOf(invSetup);
			plugin.moveSetup(invIndex, 0);
		}

	}

	@Override
	public void moveToBottom(final InventorySetup invSetup)
	{
		if (plugin.getConfig().sectionMode())
		{
			int invIndex = section.getSetups().indexOf(invSetup.getName());
			plugin.moveSetupWithinSection(section, invIndex, section.getSetups().size() - 1);
		}
		else
		{
			int invIndex = plugin.getInventorySetups().indexOf(invSetup);
			plugin.moveSetup(invIndex, plugin.getInventorySetups().size() - 1);
		}
	}

	@Override
	public void moveToPosition(final InventorySetup invSetup)
	{
		boolean sectionMode = plugin.getConfig().sectionMode();
		int invIndex = sectionMode ? section.getSetups().indexOf(invSetup.getName()) :
										plugin.getInventorySetups().indexOf(invSetup);
		int size = sectionMode ? section.getSetups().size() : plugin.getInventorySetups().size();

		final String posDialog = "Enter a position between 1 and " + size +
				". Current setup is in position " + (invIndex + 1) + ".";
		final String newPositionStr = JOptionPane.showInputDialog(panel,
				posDialog,
				"Move Setup",
				JOptionPane.PLAIN_MESSAGE);

		// cancel button was clicked
		if (newPositionStr == null)
		{
			return;
		}

		try
		{
			int newPosition = Integer.parseInt(newPositionStr);
			if (newPosition < 1 || newPosition > size)
			{
				JOptionPane.showMessageDialog(panel,
						"Invalid position.",
						"Move Setup Failed",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (sectionMode)
			{
				plugin.moveSetupWithinSection(section, invIndex, newPosition - 1);
			}
			else
			{
				plugin.moveSetup(invIndex, newPosition - 1);
			}

		}
		catch (NumberFormatException ex)
		{
			JOptionPane.showMessageDialog(panel,
					"Invalid position.",
					"Move Setup Failed",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
