package it.zygotecode.amnyscraft.gui;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import it.zygotecode.amnyscraft.utils.Enchanter;
import it.zygotecode.amnyscraft.utils.GuiUtils;

public class CraftingArmiGui implements Listener
{
	private Inventory inv;
	private String inventoryName;
	public static int invRows = 1 * 9;
	
	public CraftingArmiGui()
	{
		inv = Bukkit.createInventory(null, invRows);
		inventoryName = "§4Crafting armi";
	}
	
	public Inventory GetGUI()
	{
		Inventory toReturn = Bukkit.createInventory(null, invRows, inventoryName);
		ArrayList<String> lore = new ArrayList<String>();
		ArrayList<Enchanter> enchantments = new ArrayList<Enchanter>();
		lore.add("§7Predizione II");
		enchantments.add(new Enchanter(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true));
		enchantments.add(new Enchanter(Enchantment.DURABILITY, 1, true));
		GuiUtils.createItem(inv, Material.DIAMOND_HELMET, 1, 1, "§f§ki§d§ki§f§ki§dE§fl§dm§fo§dD§fe§dl§fl§da§fS§di§fb§di§fl§dl§fa§d§ki§f§ki§d§ki", lore, enchantments);
		enchantments.clear();
		lore.clear();
		lore.add("§7Maledizione III");
		enchantments.add(new Enchanter(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true));
		enchantments.add(new Enchanter(Enchantment.DURABILITY, 1, true));
		GuiUtils.createItem(inv, Material.DIAMOND_CHESTPLATE, 1, 2, " &c&ki&4&ki&c&ki&4C&co&4r&ca&4z&cz&4a&cD&4i&cA&4s&cc&4a&cl&4a&cf&4o&c&ki&4&ki&c&ki", lore, enchantments);
		enchantments.clear();
		lore.clear();
		lore.add("§7Calore V");
		enchantments.add(new Enchanter(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true));
		enchantments.add(new Enchanter(Enchantment.DURABILITY, 1, true));
		GuiUtils.createItem(inv, Material.DIAMOND_LEGGINGS, 1, 3, "&e&ki&6&ki&e&ki&6G&ea&6m&eb&6a&el&6i&eD&6i&eF&6e&et&6o&en&6t&ee&6&ki&e&ki&6&ki", lore, enchantments);
		enchantments.clear();
		lore.clear();
		lore.add("§7Falsità IV");
		enchantments.add(new Enchanter(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true));
		enchantments.add(new Enchanter(Enchantment.DURABILITY, 1, true));
		GuiUtils.createItem(inv, Material.DIAMOND_BOOTS, 1, 4, "&7&ki&8&ki&7&ki&8S&7t&8i&7v&8a&7l&8i&7D&8i&7H&8o&7r&8k&7o&8s&7&ki&8&ki&7&ki", lore, enchantments);
		enchantments.clear();
		lore.clear();
		enchantments.add(new Enchanter(Enchantment.DIG_SPEED, 2, true));
		enchantments.add(new Enchanter(Enchantment.LUCK, 1, true));
		GuiUtils.createItem(inv, Material.IRON_PICKAXE, 1, 5, "&7M&fi&7n&fi&7e&fr&7e &fD&7i &fS&7p&fa&7r&ft&7a", lore, enchantments);
		enchantments.clear();
		toReturn.setContents(inv.getContents());
		return toReturn;
	}
	
	public void clicked(Player p, int slot, ItemStack clicked, Inventory inv)
	{
		CraftingArmiLittleGui newGui = new CraftingArmiLittleGui(0);
		
		if (clicked.getItemMeta().getDisplayName() == "§f§ki§d§ki§f§ki§dE§fl§dm§fo§dD§fe§dl§fl§da§fS§di§fb§di§fl§dl§fa§d§ki§f§ki§d§ki")
		{
			newGui.setId(0);
		}
		else if (clicked.getItemMeta().getDisplayName() == "§c§ki§4§ki§c§ki§4C§co§4r§ca§4z§cz§4a§cD§4i§cA§4s§cc§4a§cl§4a§cf§4o§c§ki§4§ki§c§ki")
		{
			newGui.setId(1);
		}
		else if (clicked.getItemMeta().getDisplayName() == "§e§ki§6§ki§e§ki§6G§ea§6m§eb§6a§el§6i§eD§6i§eF§6e§et§6o§en§6t§ee§6§ki§e§ki§6§ki")
		{
			newGui.setId(2);
		}
		else if (clicked.getItemMeta().getDisplayName() == "§7§ki§8§ki§7§ki§8S§7t§8i§7v§8a§7l§8i§7D§8i§7H§8o§7r§8k§7o§8s§7§ki§8§ki§7§ki")
		{
			newGui.setId(3);
		}
		else if (clicked.getItemMeta().getDisplayName() == "§7M§fi§7n§fi§7e§fr§7e §fD§7i §fS§7p§fa§7r§ft§7a")
		{
			newGui.setId(4);
		}
		
		p.closeInventory();
		p.openInventory(newGui.GetGUI());
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onClick(InventoryClickEvent e)
	{
		if (e.getInventory().equals(inv) || e.getInventory().equals(GetGUI()) || e.getClickedInventory().equals(inv) || e.getClickedInventory().equals(GetGUI()) || e.getClickedInventory().getName().equals(inventoryName) || e.getInventory().getName().equals(inventoryName))
		{
			clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
			e.setCancelled(true);
		}
	}
}