package it.zygotecode.amnyscraft.gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import it.zygotecode.amnyscraft.utils.GuiUtils;

public class CraftingArmiLittleGui implements Listener
{
	private Inventory inv;
	private String inventoryName;
	private int id;
	
	public CraftingArmiLittleGui(int id)
	{
		this.id = id;
		inventoryName = "§6Ecco qui il tuo crafting";
		inv = Bukkit.createInventory(null, InventoryType.WORKBENCH, inventoryName);
	}
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Inventory GetGUI()
	{
		Inventory toReturn = Bukkit.createInventory(null, InventoryType.WORKBENCH, inventoryName);
		
		if (id == 0)
		{
			GuiUtils.createItem(inv, Material.YELLOW_FLOWER, 1, 1, null, null, null);
			GuiUtils.createItem(inv, Material.ENDER_PEARL, 1, 2, null, null, null);
			GuiUtils.createItem(inv, Material.REDSTONE_TORCH_ON, 1, 3, null, null, null);
			GuiUtils.createItem(inv, Material.HARD_CLAY, 1, 4, null, null, null);
			GuiUtils.createItem(inv, Material.DIAMOND_BLOCK, 1, 5, null, null, null);
			GuiUtils.createItem(inv, Material.YELLOW_FLOWER, 1, 6, null, null, null);
			GuiUtils.createItem(inv, Material.REDSTONE, 1, 7, null, null, null);
			GuiUtils.createItem(inv, Material.PUMPKIN, 1, 8, null, null, null);
			GuiUtils.createItem(inv, Material.REDSTONE, 1, 9, null, null, null);
		}
		else if (id == 1)
		{
			
		}
		else if (id == 2)
		{
			
		}
		else if (id == 3)
		{
			
		}
		else if (id == 4)
		{
			
		}
		
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onClick(InventoryClickEvent e)
	{
		if (e.getInventory().equals(inv) || e.getInventory().equals(GetGUI()) || e.getClickedInventory().equals(inv) || e.getClickedInventory().equals(GetGUI()) || e.getClickedInventory().getName().equals(inventoryName) || e.getInventory().getName().equals(inventoryName))
		{
			e.setCancelled(true);
		}
	}
}