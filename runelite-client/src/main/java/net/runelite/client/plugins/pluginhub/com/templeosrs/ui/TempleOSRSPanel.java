package net.runelite.client.plugins.pluginhub.com.templeosrs.ui;

import net.runelite.client.plugins.pluginhub.com.templeosrs.TempleOSRSPlugin;
import net.runelite.client.plugins.pluginhub.com.templeosrs.ui.clans.TempleClans;
import net.runelite.client.plugins.pluginhub.com.templeosrs.ui.competitions.TempleCompetitions;
import net.runelite.client.plugins.pluginhub.com.templeosrs.ui.ranks.TempleRanks;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.time.Instant;
import javax.imageio.ImageIO;
import javax.inject.Inject;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import static net.runelite.client.RuneLite.SCREENSHOT_DIR;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.materialtabs.MaterialTab;
import net.runelite.client.ui.components.materialtabs.MaterialTabGroup;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.util.LinkBrowser;

public class TempleOSRSPanel extends PluginPanel
{
	public static final String DEFAULT = "--";

	private static final String SCREENSHOTS = SCREENSHOT_DIR + File.separator + "Temple-Snapshots" + File.separator;

	public final TempleRanks ranks;

	public final TempleClans groups;

	public final TempleCompetitions competitions;

	public final MaterialTabGroup tabGroup;

	public final MaterialTab ranksTab;

	public final MaterialTab groupsTab;

	public final MaterialTab competitionsTab;

	@Inject
	public TempleOSRSPanel(TempleRanks ranks, TempleClans groups, TempleCompetitions competitions)
	{
		this.ranks = ranks;
		this.groups = groups;
		this.competitions = competitions;

		setBackground(ColorScheme.DARKER_GRAY_COLOR);
		getScrollPane().setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		JPanel layoutPanel = new JPanel();
		layoutPanel.setLayout(new BoxLayout(layoutPanel, BoxLayout.Y_AXIS));
		layoutPanel.setBackground(ColorScheme.DARKER_GRAY_COLOR);

		/* material-tab-group for each API Endpoint (Players, Groups, Competitions)*/
		JPanel display = new JPanel();
		tabGroup = new MaterialTabGroup(display);

		ranksTab = new MaterialTab("Ranks", tabGroup, ranks);
		groupsTab = new MaterialTab("Clans", tabGroup, groups);
		competitionsTab = new MaterialTab("Competitions", tabGroup, competitions);

		tabGroup.addTab(ranksTab);
		tabGroup.addTab(groupsTab);
		tabGroup.addTab(competitionsTab);

		tabGroup.select(ranksTab);

		layoutPanel.add(tabGroup);
		layoutPanel.add(display);
		layoutPanel.add(buildScreenshots());

		add(layoutPanel);
	}

	/* build screenshots button */
	private JPanel buildScreenshots()
	{
		JPanel saveLayout = new JPanel(new BorderLayout());
		saveLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
		saveLayout.setBackground(ColorScheme.DARKER_GRAY_COLOR);

		/* create menu and menu-options */
		JPopupMenu menu = new JPopupMenu();

		JMenuItem takeScreenshot = new JMenuItem();
		takeScreenshot.setText("Take Screenshot of current view...");
		takeScreenshot.addActionListener(e -> screenshot(this));
		menu.add(takeScreenshot);

		JMenuItem openFolder = new JMenuItem();
		openFolder.setText("Open screenshot folder...");
		openFolder.addActionListener(e -> {
			if (SCREENSHOT_DIR.exists() || SCREENSHOT_DIR.mkdirs())
			{
				LinkBrowser.open(SCREENSHOT_DIR.getAbsolutePath());
			}
		});
		menu.add(openFolder);

		/* build button and add menu options to take-snapshot/open-snapshots-folder */
		JButton screenshotButton = new JButton();
		screenshotButton.setBorder(new EmptyBorder(5, 5, 5, 5));
		screenshotButton.setIcon(new ImageIcon(ImageUtil.loadImageResource(TempleOSRSPlugin.class, "save.png")));
		screenshotButton.setBackground(ColorScheme.SCROLL_TRACK_COLOR);
		screenshotButton.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				menu.show(screenshotButton, e.getX(), e.getY());
			}
		});
		saveLayout.add(screenshotButton, BorderLayout.WEST);

		return saveLayout;
	}

	/* take a perfectly cropped image of the plugin-layout */
	private void screenshot(JPanel panel)
	{
		/* use epoch-time as unique file-name */
		String timestamp = String.valueOf(Instant.now().getEpochSecond());

		/* create directory if not exists,
		 * continue if success */
		File directory = new File(SCREENSHOTS);
		if (directory.exists() || directory.mkdirs())
		{
			/* create image */
			BufferedImage img = new BufferedImage(panel.getSize().width, panel.getSize().height, BufferedImage.TYPE_INT_RGB);
			panel.paint(img.createGraphics());
			File imageFile = new File(SCREENSHOTS + timestamp + ".png");

			/* attempt to save image-file to directory */
			try
			{
				if (imageFile.createNewFile())
				{
					ImageIO.write(img, "png", imageFile);
				}
			}
			catch (Exception ignored)
			{

			}
		}
	}
}
