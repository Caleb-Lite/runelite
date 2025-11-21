package net.runelite.client.plugins.pluginhub.com.mouseclickcounter;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.SwingUtilities;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.client.input.MouseAdapter;
import static net.runelite.client.RuneLite.RUNELITE_DIR;

public class MouseClickCounterListener extends MouseAdapter
{
	private int leftClickCounter;
	private int rightClickCounter;
	private int middleClickCounter;
	private int totalClickCounter;
	private final Client client;
	private final File CLICK_TOTAL_DIR =
		new File(RUNELITE_DIR,"mouseClickCounter");
	private final File CLICK_TOTAL_FILE =
		new File(CLICK_TOTAL_DIR, "click_totals.log");
	private final int SAVE_PERIODICITY = 50;
	private final int NUM_CLICK_TYPES = 4;
	private enum FILE_CLICK_TYPE_INDICES {
		TOTAL(0),
		LEFT(1),
		RIGHT(2),
		MIDDLE(3);
		private final int index;
		FILE_CLICK_TYPE_INDICES(final int newIndex)
		{
			index = newIndex;
		}
		public int getValue() { return index; }
	}

	MouseClickCounterListener(Client client) throws FileNotFoundException
	{
		loadMouseClicks();
		this.client = client;
	}

	@Override
	public MouseEvent mousePressed(MouseEvent event)
	{
		if(client.getGameState() == GameState.LOGGED_IN)
		{

			if (SwingUtilities.isLeftMouseButton(event))
			{
				this.leftClickCounter++;
				this.totalClickCounter++;
			}

			else if (SwingUtilities.isRightMouseButton(event))
			{
				this.rightClickCounter++;
				this.totalClickCounter++;
			}

			else if (SwingUtilities.isMiddleMouseButton(event))
			{
				this.middleClickCounter++;
				this.totalClickCounter++;
			}

			if (this.totalClickCounter%SAVE_PERIODICITY == 0)
			{
				try
				{
					saveMouseClicks();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		return event;
	}

	public int getLeftClickCounter() { return this.leftClickCounter; }

	public int getRightClickCounter() { return this.rightClickCounter; }

	public int getMiddleClickCounter() { return this.middleClickCounter; }

	public int getTotalClickCounter() { return this.totalClickCounter; }

	public void resetMouseClickCounterListener()
	{
		this.leftClickCounter = 0;
		this.rightClickCounter = 0;
		this.middleClickCounter = 0;
		this.totalClickCounter = 0;
		try
		{
			saveMouseClicks();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void saveMouseClicks() throws IOException
	{
		if(!this.CLICK_TOTAL_FILE.exists())
		{
			try
			{
				if(!this.CLICK_TOTAL_FILE.createNewFile())
				{
					System.out.println("Failed to create log file");
				}
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		FileWriter writer = new FileWriter(this.CLICK_TOTAL_FILE);

		Integer[] totals = {this.getTotalClickCounter(),
			this.getLeftClickCounter(),
			this.getRightClickCounter(),
			this.getMiddleClickCounter()};

		writer.write(totals[FILE_CLICK_TYPE_INDICES.TOTAL.getValue()] + " ");
		writer.write(totals[FILE_CLICK_TYPE_INDICES.LEFT.getValue()] + " ");
		writer.write(totals[FILE_CLICK_TYPE_INDICES.RIGHT.getValue()] + " ");
		writer.write(totals[FILE_CLICK_TYPE_INDICES.MIDDLE.getValue()] + " ");
		writer.close();
	}

	public void loadMouseClicks() throws FileNotFoundException
	{
		if(!this.CLICK_TOTAL_DIR.mkdir() && this.CLICK_TOTAL_FILE.exists())
		{
			Scanner scanner = new Scanner(this.CLICK_TOTAL_FILE);
			int [] totals = new int[NUM_CLICK_TYPES];
			int ii = 0;
			while(scanner.hasNextInt())
			{
				totals[ii++] = scanner.nextInt();
			}
			if(ii != NUM_CLICK_TYPES)
			{
				resetMouseClickCounterListener();
			}
			else
			{
				this.leftClickCounter =
					totals[FILE_CLICK_TYPE_INDICES.LEFT.getValue()];
				this.rightClickCounter =
					totals[FILE_CLICK_TYPE_INDICES.RIGHT.getValue()];
				this.middleClickCounter =
					totals[FILE_CLICK_TYPE_INDICES.MIDDLE.getValue()];
				this.totalClickCounter =
					totals[FILE_CLICK_TYPE_INDICES.TOTAL.getValue()];
			}
		}
		else
		{
			try
			{
				if(this.CLICK_TOTAL_FILE.createNewFile())
				{
					this.leftClickCounter = 0;
					this.rightClickCounter = 0;
					this.middleClickCounter = 0;
					this.totalClickCounter = 0;
				}
				else
				{
					System.out.println("Failed to create log file");
				}
			}
			catch (IOException e)
			{
				System.out.println("An error occurred creating the log file");
				e.printStackTrace();
			}
		}
	}
}

