package net.runelite.client.plugins.pluginhub.com.totalcost.models;

public class ItemModel
{
	// Fields
	private int id;
	private String name;
	private int quantity;
	private int currentPrice;
	private int haPrice;
	private int value;

	// Constructor
	public ItemModel(int id, String name, int quantity, int currentPrice, int haPrice, int value)
	{
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.currentPrice = currentPrice;
		this.haPrice = haPrice;
		this.value = value;
	}

	// Properties
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public int getCurrentPrice()
	{
		return currentPrice;
	}

	public void setCurrentPrice(int currentPrice)
	{
		this.currentPrice = currentPrice;
	}

	public int getHaPrice()
	{
		return haPrice;
	}

	public void setHaPrice(int haPrice)
	{
		this.haPrice = haPrice;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}
}
