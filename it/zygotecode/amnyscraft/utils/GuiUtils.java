package it.zygotecode.amnyscraft.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GuiUtils
{
	public static ItemStack createItem(Inventory inv, Material material, int amount, int invSlot, String displayName, ArrayList<String> loreString, ArrayList<Enchanter> enchantments)
	{
		ItemStack item;
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<String> lore = new ArrayList();
		item = new ItemStack(material, amount);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName.replace("&", "§"));
		
		for (String s : loreString)
		{
			lore.add(s.replace("&", "§"));
		}
		
		for (Enchanter e: enchantments)
		{
			meta.addEnchant(e.getEnchantment(), e.getValue(), e.isIt());
		}
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot - 1, item);
		return item;
	}
	
	public static ItemStack createItemByte(Inventory inv, Material material, int byteId, int amount, int invSlot, String displayName, ArrayList<String> loreString)
	{
		ItemStack item;
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<String> lore = new ArrayList();
		item = new ItemStack(material, amount, (short) byteId);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(displayName.replace("&", "§"));
		
		for (String s : loreString)
		{
			lore.add(s.replace("&", "§"));
		}
		
		meta.setLore(lore);
		item.setItemMeta(meta);
		inv.setItem(invSlot - 1, item);
		return item;
	}
}