package it.zygotecode.amnyscraft.utils;

import org.bukkit.enchantments.Enchantment;

public class Enchanter
{
	private Enchantment enchantment;
	private int value;
	private boolean isIt;
	
	public Enchanter(Enchantment enchantment, int value, boolean isIt)
	{
		this.enchantment = enchantment;
		this.value = value;
		this.isIt = isIt;
	}

	public Enchantment getEnchantment()
	{
		return enchantment;
	}

	public void setEnchantment(Enchantment enchantment)
	{
		this.enchantment = enchantment;
	}

	public int getValue()
	{
		return value;
	}

	public void setValue(int value)
	{
		this.value = value;
	}

	public boolean isIt()
	{
		return isIt;
	}

	public void setIt(boolean isIt)
	{
		this.isIt = isIt;
	}
}