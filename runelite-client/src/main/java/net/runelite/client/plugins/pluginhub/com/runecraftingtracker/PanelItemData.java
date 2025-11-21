package net.runelite.client.plugins.pluginhub.com.runecraftingtracker;

public class PanelItemData
{
	private String name;
	private int id;
	private boolean isVisible;
	private int crafted;
	private int costPerRune;


	public PanelItemData(String name, int id, boolean isVisible, int count, int costPerRune)
	{
		this.name = name;
		this.id = id;
		this.isVisible = isVisible;
		this.crafted = count;
		this.costPerRune = costPerRune;
	}

	public boolean isVisible()
	{
		return isVisible;
	}

	public void setVisible(boolean visible)
	{
		isVisible = visible;
	}

	public int getCrafted()
	{
		return crafted;
	}

	public void setCrafted(int crafted)
	{
		this.crafted = crafted;
	}

	public int getCostPerRune()
	{
		return costPerRune;
	}

	public void setCostPerRune(int costPerRune)
	{
		this.costPerRune = costPerRune;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
}

