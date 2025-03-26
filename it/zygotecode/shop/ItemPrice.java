package it.zygotecode.shop;

import org.bukkit.Material;

public class ItemPrice
{
	private Material material;
	private double price;
	
	public ItemPrice(Material material, double price)
	{
		this.material = material;
		this.price = price;
	}

	public Material getMaterial()
	{
		return material;
	}

	public void setMaterial(Material material)
	{
		this.material = material;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}
}