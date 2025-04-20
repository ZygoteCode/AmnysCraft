package it.zygotecode.amnyscraft.utils;

public class SavedInventory
{
	private String name;
	private String inventory;
	
	public SavedInventory(String name, String inventory)
	{
		this.name = name;
		this.inventory = inventory;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getInventory()
	{
		return inventory;
	}

	public void setInventory(String inventory)
	{
		this.inventory = inventory;
	}
}