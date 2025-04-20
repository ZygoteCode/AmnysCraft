package it.zygotecode.amnyscraft.quest;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.InventoryUtils;

public class QuestManager
{
	public void fixPlayer(Player player)
	{
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName()).isEmpty())
			{
				String s = AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName());
				s = "";
				
				if (s == "")
				{
					s = "";
				}
			}
		}
		catch (Exception ex)
		{
			AmnysCraft.getConfigh().getConfigurationSection("quests").set(player.getName(), "0");
		}
	}
	
	public boolean hasCompletedQuest(Player player, int id)
	{
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName()).isEmpty())
			{
				String s = AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName());
				String[] lol = s.split("\\s+");
				
				for (String l: lol)
				{
					if (l.equals(Integer.toString(id)))
					{
						return true;
					}
				}
				
				return false;
			}
		}
		catch (Exception ex)
		{
			fixPlayer(player);
			return false;
		}
		
		return false;
	}
	
	public void completeQuest(Player player, int id)
	{
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName()).isEmpty())
			{
				String s = AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName());
				AmnysCraft.getConfigh().getConfigurationSection("quests").set(player.getName(), s + " " + Integer.toString(id));
				
				if (id == 1)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 500);
				}
				else if (id == 2)
				{
					removeItem(player, Material.IRON_BLOCK, 4);
				}
				else if (id == 3)
				{
					removeItem(player, Material.COAL_BLOCK, 2);
				}
				else if (id == 4)
				{
					removeItem(player, Material.REDSTONE_BLOCK, 2);
				}
				else if (id == 5)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 600);
				}
				else if (id == 6)
				{
					removeItem(player, Material.GOLD_BLOCK, 2);
				}
				else if (id == 7)
				{
					removeItem(player, Material.COAL, 16);
				}
				else if (id == 8)
				{
					removeItem(player, Material.APPLE, 16);
				}
				else if (id == 9)
				{
					removeItem(player, Material.DIAMOND, 5);
				}
				else if (id == 10)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 600);
				}
				else if (id == 11)
				{
					removeItem(player, Material.REDSTONE_BLOCK, 6);
				}
				else if (id == 12)
				{
					removeItem(player, Material.COAL, 24);	
				}
				else if (id == 13)
				{
					removeItem(player, Material.WOOL, 2);
				}
				else if (id == 14)
				{
					removeItem(player, Material.WOOL, 1);
				}
				else if (id == 15)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 650);
				}
				else if (id == 16)
				{
					removeItem(player, Material.DIAMOND_BLOCK, 1);
				}
				else if (id == 17)
				{
					removeItem(player, Material.IRON_BLOCK, 6);
				}
				else if (id == 18)
				{
					removeItem(player, Material.GOLD_INGOT, 24);
				}
				else if (id == 19)
				{
					removeItem(player, Material.LAVA_BUCKET, 2);
				}
				else if (id == 20)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 750);
				}
				else if (id == 21)
				{
					removeItem(player, Material.DIAMOND_BLOCK, 6);	
				}
				else if (id == 22)
				{
					removeItem(player, Material.DIAMOND, 5);
				}
				else if (id == 23)
				{
					removeItem(player, Material.ANVIL, 1);
				}
				else if (id == 24)
				{
					removeItem(player, Material.BOOKSHELF, 15);
				}
				else if (id == 25)
				{
					removeItem(player, Material.GOLD_INGOT, 10);
				}
				else if (id == 26)
				{
					removeItem(player, Material.MELON, 16);
				}
				else if (id == 27)
				{
					removeItem(player, Material.SEEDS, 20);
				}
				else if (id == 28)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 950);
				}
				else if (id == 29)
				{
					removeItem(player, Material.BRICK, 16);	
				}
				else if (id == 30)
				{
					removeItem(player, Material.LAVA_BUCKET, 2);
				}
				else if (id == 31)
				{
					removeItem(player, Material.WATER_BUCKET, 2);
				}
				else if (id == 32)
				{
					removeItem(player, Material.BREAD, 64);
				}
				else if (id == 33)
				{
					removeItem(player, Material.WHEAT, 30);					
				}
				else if (id == 34)
				{
					removeItem(player, Material.HAY_BLOCK, 2);
				}
				else if (id == 35)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 1050);
				}
				else if (id == 36)
				{
					removeItem(player, Material.DIAMOND_HOE, 1);
				}
				else if (id == 37)
				{
					removeItem(player, Material.WHEAT, 32);
				}
				else if (id == 38)
				{
					removeItem(player, Material.HAY_BLOCK, 6);	
				}
				else if (id == 39)
				{
					removeItem(player, Material.SADDLE, 1);
				}
				else if (id == 40)
				{
					removeItem(player, Material.GLASS, 10);
				}
				else if (id == 41)
				{
					removeItem(player, Material.CLAY, 16);	
				}
				else if (id == 42)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 1050);
				}
				else if (id == 43)
				{
					removeItem(player, Material.NETHER_WARTS, 16);	
				}
				else if (id == 44)
				{
					removeItem(player, Material.NETHERRACK, 64);
				}
				else if (id == 45)
				{
					removeItem(player, Material.QUARTZ, 16);
				}
				else if (id == 46)
				{
					
				}
				else if (id == 47)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 2050);
				}
				else if (id == 48)
				{
					removeItem(player, Material.QUARTZ, 64);
				}
				else if (id == 49)
				{
					removeItem(player, Material.NETHER_WARTS, 32);
				}
				else if (id == 50)
				{
					removeItem(player, Material.ROTTEN_FLESH, 32);
				}
				else if (id == 51)
				{
					removeItem(player, Material.BONE, 16);
				}
				else if (id == 52)
				{
					
				}
				else if (id == 53)
				{
					removeItem(player, Material.IRON_BLOCK, 16);
				}
				else if (id == 54)
				{
					removeItem(player, Material.CACTUS, 16);
				}
				else if (id == 55)
				{
					removeItem(player, Material.COOKIE, 10);
				}
				else if (id == 56)
				{
					removeItem(player, Material.SUGAR, 32);
				}
				else if (id == 57)
				{
					AmnysCraft.getEconomy().withdrawPlayer(player, 3050);
				}
				else if (id == 58)
				{
					removeItem(player, Material.ENDER_STONE, 32);
				}
				else if (id == 59)
				{
					removeItem(player, Material.DIAMOND_BLOCK, 10);
				}
				else if (id == 60)
				{
					removeItem(player, Material.LAPIS_ORE, 64);
				}
				else if (id == 61)
				{
					removeItem(player, Material.REDSTONE, 64);	
				}
				else if (id == 62)
				{
					removeItem(player, Material.DIRT, 128);
				}
				else if (id == 63)
				{
					removeItem(player, Material.GRAVEL, 32);	
				}
				else if (id == 64)
				{
					
				}
				else if (id == 65)
				{
					removeItem(player, Material.IRON_BLOCK, 24);
				}
				else if (id == 66)
				{
					removeItem(player, Material.CACTUS, 32);
				}
				else if (id == 67)
				{
					removeItem(player, Material.PAPER, 32);
				}
				else if (id == 68)
				{
					removeItem(player, Material.BONE, 24);
				}
			}
		}
		catch (Exception ex)
		{
			fixPlayer(player);
		}
	}
	
	public int getCompletedQuests(Player player)
	{
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName()).isEmpty())
			{
				String s = AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName());
				String[] lol = s.split("\\s+");
				return lol.length;
			}
		}
		catch (Exception ex)
		{
			fixPlayer(player);
			return 0;
		}
		
		return 0;
	}
	
	public List<Integer> getCompletedQuestsList(Player player)
	{
		List<Integer> quests = new ArrayList<Integer>();
		
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName()).isEmpty())
			{
				String s = AmnysCraft.getConfigh().getConfigurationSection("quests").getString(player.getName());
				String[] lol = s.split("\\s+");
				
				for (String l: lol)
				{
					quests.add(Integer.parseInt(l));
				}
				
				return quests;
			}
		}
		catch (Exception ex)
		{
			fixPlayer(player);
			return quests;
		}
		
		return quests;
	}
	
	public boolean hasItem(Player player, Material material, int amount)
	{
		String lolInventory = InventoryUtils.InventoryToString(player.getInventory());
		Inventory inventory = InventoryUtils.StringToInventory(lolInventory);
		int calculatedAmount = 0;
		
		for (ItemStack stack: inventory.getContents())
		{
			if (stack != null)
			{
				if (stack.getType().equals(material))
				{
					calculatedAmount += stack.getAmount();
				}
			}
		}
		
		if (calculatedAmount >= amount)
		{
			return true;
		}
		
		return false;
	}
	
	public void removeItem(Player player, Material material, int amount)
	{
		String lolInventory = InventoryUtils.InventoryToString(player.getInventory());
		Inventory inventory = InventoryUtils.StringToInventory(lolInventory);
		int calculatedAmount = 0;
		
		for (ItemStack stack: inventory.getContents())
		{
			if (stack != null)
			{
				if (stack.getType().equals(material))
				{
					if (stack.getAmount() >= amount)
					{
						stack.setAmount(stack.getAmount() - amount);
					}
					else
					{
						boolean totally = true;
						int amianto = 0;
						
						for (int i = 0; i < stack.getAmount(); i++)
						{
							if (!(calculatedAmount >= amount))
							{
								calculatedAmount += 1;
								amianto += 1;
							}
							else
							{
								totally = false;
								break;
							}
						}
						
						if (totally)
						{
							player.getInventory().remove(stack);
							player.updateInventory();
						}
						else
						{
							stack.setAmount(amianto);
							player.updateInventory();
						}
					}
				}
			}
		}
		
		player.getInventory().clear();
		player.getInventory().setContents(inventory.getContents());
		player.updateInventory();
	}
	
	public boolean canCompleteQuest(Player player, int id)
	{
		if (id == 1)
		{
			if (AmnysCraft.getEconomy().has(player, 500))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 2)
		{
			if (hasItem(player, Material.IRON_BLOCK, 4))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 3)
		{
			if (hasItem(player, Material.COAL_BLOCK, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 4)
		{
			if (hasItem(player, Material.REDSTONE_BLOCK, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 5)
		{
			if (AmnysCraft.getEconomy().has(player, 600))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 6)
		{
			if (hasItem(player, Material.GOLD_BLOCK, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 7)
		{
			if (hasItem(player, Material.COAL, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 8)
		{
			if (hasItem(player, Material.APPLE, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 9)
		{
			if (hasItem(player, Material.DIAMOND, 5))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 10)
		{
			if (AmnysCraft.getEconomy().has(player, 600))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 11)
		{
			if (hasItem(player, Material.REDSTONE_BLOCK, 6))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 12)
		{
			if (hasItem(player, Material.COAL, 24))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 13)
		{
			if (hasItem(player, Material.WOOL, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 14)
		{
			if (hasItem(player, Material.WOOL, 1))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 15)
		{
			if (AmnysCraft.getEconomy().has(player, 650))
			{
				return true;
			}
				
			return false;
		}
		else if (id == 16)
		{
			if (hasItem(player, Material.DIAMOND_BLOCK, 1))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 17)
		{
			if (hasItem(player, Material.IRON_BLOCK, 6))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 18)
		{
			if (hasItem(player, Material.GOLD_INGOT, 24))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 19)
		{
			if (hasItem(player, Material.LAVA_BUCKET, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 20)
		{
			if (AmnysCraft.getEconomy().has(player, 750))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 21)
		{
			if (hasItem(player, Material.DIAMOND_BLOCK, 6))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 22)
		{
			if (hasItem(player, Material.DIAMOND, 5))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 23)
		{
			if (hasItem(player, Material.ANVIL, 1))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 24)
		{
			if (hasItem(player, Material.BOOKSHELF, 15))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 25)
		{
			if (hasItem(player, Material.GOLD_INGOT, 10))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 26)
		{
			if (hasItem(player, Material.MELON, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 27)
		{
			if (hasItem(player, Material.SEEDS, 20))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 28)
		{
			if (AmnysCraft.getEconomy().has(player, 950))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 29)
		{
			if (hasItem(player, Material.BRICK, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 30)
		{
			if (hasItem(player, Material.LAVA_BUCKET, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 31)
		{
			if (hasItem(player, Material.WATER_BUCKET, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 32)
		{
			if (hasItem(player, Material.BREAD, 64))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 33)
		{
			if (hasItem(player, Material.WHEAT, 30))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 34)
		{
			if (hasItem(player, Material.HAY_BLOCK, 2))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 35)
		{
			if (AmnysCraft.getEconomy().has(player, 1050))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 36)
		{
			if (hasItem(player, Material.DIAMOND_HOE, 1))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 37)
		{
			if (hasItem(player, Material.WHEAT, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 38)
		{
			if (hasItem(player, Material.HAY_BLOCK, 6))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 39)
		{
			if (hasItem(player, Material.SADDLE, 1))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 40)
		{
			if (hasItem(player, Material.GLASS, 10))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 41)
		{
			if (hasItem(player, Material.CLAY, 10))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 42)
		{
			if (AmnysCraft.getEconomy().has(player, 1050))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 43)
		{
			if (hasItem(player, Material.NETHER_WARTS, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 44)
		{
			if (hasItem(player, Material.NETHERRACK, 64))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 45)
		{
			if (hasItem(player, Material.QUARTZ, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 46)
		{
			return true;
		}
		else if (id == 47)
		{
			if (AmnysCraft.getEconomy().has(player, 2050))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 48)
		{
			if (hasItem(player, Material.QUARTZ, 64))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 49)
		{
			if (hasItem(player, Material.NETHER_WARTS, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 50)
		{
			if (hasItem(player, Material.ROTTEN_FLESH, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 51)
		{
			if (hasItem(player, Material.BONE, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 52)
		{
			return true;
		}
		else if (id == 53)
		{
			if (hasItem(player, Material.IRON_BLOCK, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 54)
		{
			if (hasItem(player, Material.CACTUS, 16))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 55)
		{
			if (hasItem(player, Material.COOKIE, 10))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 56)
		{
			if (hasItem(player, Material.SUGAR, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 57)
		{
			if (AmnysCraft.getEconomy().has(player, 3050))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 58)
		{
			if (hasItem(player, Material.ENDER_STONE, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 59)
		{
			if (hasItem(player, Material.DIAMOND_BLOCK, 10))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 60)
		{
			if (hasItem(player, Material.LAPIS_ORE, 64))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 61)
		{
			if (hasItem(player, Material.REDSTONE, 64))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 62)
		{
			if (hasItem(player, Material.DIRT, 128))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 63)
		{
			if (hasItem(player, Material.GRAVEL, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 64)
		{
			return true;
		}
		else if (id == 65)
		{
			if (hasItem(player, Material.IRON_BLOCK, 24))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 66)
		{
			if (hasItem(player, Material.CACTUS, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 67)
		{
			if (hasItem(player, Material.PAPER, 32))
			{
				return true;
			}
			
			return false;
		}
		else if (id == 68)
		{
			if (hasItem(player, Material.BONE, 24))
			{
				return true;
			}
			
			return false;
		}
		else
		{
			return false;
		}
	}
	
	public int whatNeedToComplete(Player player)
	{
		return getCompletedQuests(player) + 1;
	}
}