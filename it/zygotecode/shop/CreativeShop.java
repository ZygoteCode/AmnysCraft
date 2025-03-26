package it.zygotecode.shop;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.InventoryUtils;
import it.zygotecode.amnyscraft.utils.SavedInventory;

@SuppressWarnings("deprecation")
public class CreativeShop implements Listener
{
	private ArrayList<String> players;
	private ArrayList<SavedInventory> savedInventories;
	private ArrayList<ItemPrice> itemPrices;
	private ArrayList<String> waitingPlayers;
	
	public CreativeShop()
	{
		players = new ArrayList<String>();
		savedInventories = new ArrayList<SavedInventory>();
		itemPrices = new ArrayList<ItemPrice>();
		waitingPlayers = new ArrayList<>();
		addAllItemPrices();
	}
	
	public ArrayList<String> getWaitingPlayers()
	{
		return waitingPlayers;
	}
	
	public void addWaitingPlayer(String name)
	{
		waitingPlayers.add(name.toLowerCase());
	}
	
	public void removeWaitingPlayer(String name)
	{
		waitingPlayers.remove(name.toLowerCase());
	}
	
	public void deleteWaitingPlayer(String name)
	{
		waitingPlayers.remove(name.toLowerCase());
	}
	
	public boolean isWaitingPlayerPresent(String name)
	{
		return waitingPlayers.contains(name.toLowerCase());
	}
	
	public ArrayList<String> getPlayers()
	{
		return players;
	}
	
	public void addPlayer(String name)
	{
		players.add(name.toLowerCase());
	}
	
	public void removePlayer(String name)
	{
		players.remove(name.toLowerCase());
	}
	
	public void deletePlayer(String name)
	{
		players.remove(name.toLowerCase());
	}
	
	public boolean isPlayerPresent(String name)
	{
		return players.contains(name.toLowerCase());
	}
	
	public void addPlayer(Player player)
	{
		players.add(player.getName().toLowerCase());
	}
	
	public void removePlayer(Player player)
	{
		players.remove(player.getName().toLowerCase());
	}
	
	public void deletePlayer(Player player)
	{
		players.remove(player.getName().toLowerCase());
	}
	
	public boolean isPlayerPresent(Player player)
	{
		return players.contains(player.getName().toLowerCase());
	}
	
	public ArrayList<ItemPrice> getItemPrices()
	{
		return itemPrices;
	}
	
	public void addItemPrice(Material material, double price)
	{
		itemPrices.add(new ItemPrice(material, price));
	}
	
	public void addItemPrice(ItemPrice itemPrice)
	{
		itemPrices.add(itemPrice);
	}
	
	public void deleteItemPrice(Material material)
	{
		for (ItemPrice itemPrice: getItemPrices())
		{
			if (itemPrice.getMaterial().equals(material))
			{
				itemPrices.remove(itemPrice);
			}
		}
	}
	
	public void deleteItemPrice(ItemPrice itemPrice)
	{
		itemPrices.remove(itemPrice);
	}
	
	public void removeItemPrice(Material material)
	{
		for (ItemPrice itemPrice: getItemPrices())
		{
			if (itemPrice.getMaterial().equals(material))
			{
				itemPrices.remove(itemPrice);
			}
		}
	}
	
	public void removeItemPrice(ItemPrice itemPrice)
	{
		itemPrices.remove(itemPrice);
	}
	
	public double getItemPrice(Material material)
	{
		for (ItemPrice itemPrice: getItemPrices())
		{
			if (itemPrice.getMaterial().equals(material))
			{
				return itemPrice.getPrice();
			}
		}
		
		return 0.0D;
	}
	
	public void fixPlayer(Player player)
	{
		try
		{
			if (players.contains(player.getName().toLowerCase()))
			{
				players.remove(player.getName().toLowerCase());
			}
			
			if (player.getGameMode().equals(GameMode.CREATIVE))
			{
				player.setGameMode(GameMode.SURVIVAL);
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	
	public void fixPlayer(String name)
	{
		try
		{
			if (players.contains(name.toLowerCase()))
			{
				players.remove(name.toLowerCase());
			}
			
			try
			{
				Player player = Bukkit.getPlayer(name.toLowerCase());
			
				if (player.getGameMode().equals(GameMode.CREATIVE))
				{
					player.setGameMode(GameMode.SURVIVAL);
				}
			}
			catch (Exception ex)
			{
				
			}
			
			if (getSavedInventory(name.toLowerCase()) != null)
			{
				try
				{
					Player player = Bukkit.getPlayer(name.toLowerCase());
					player.getInventory().clear();
					player.getInventory().addItem(InventoryUtils.StringToInventory(getSavedInventory(player.getName().toLowerCase()).getInventory()).getContents());
					savedInventories.remove(getSavedInventory(player.getName().toLowerCase()));
				}
				catch (Exception ex)
				{
					
				}
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	
	public SavedInventory getSavedInventory(String name)
	{
		for (SavedInventory si: savedInventories)
		{
			if (si.getName().toLowerCase().equalsIgnoreCase(name.toLowerCase()))
			{
				return si;
			}
		}
		
		return null;
	}
	
	public void openShop(Player player)
	{
		player.closeInventory();
		savedInventories.add(new SavedInventory(player.getName().toLowerCase(), InventoryUtils.InventoryToString(player.getInventory())));
		player.getInventory().clear();
		player.setGameMode(GameMode.CREATIVE);
		addPlayer(player);
	}
	
	public String closeShop(Player player)
	{
		deletePlayer(player);
		String lolInventory = InventoryUtils.InventoryToString(player.getInventory());
		Inventory shopInventory = InventoryUtils.StringToInventory(lolInventory);
		player.setGameMode(GameMode.SURVIVAL);		
		double totalPrice = 0;
		
        for (ItemStack stack: shopInventory.getContents())
        {
        	if (stack != null)
        	{
        		totalPrice += (getItemPrice(stack.getType())) * stack.getAmount();
        	}
        }
        
		player.getInventory().clear();
        
		for (ItemStack stack: InventoryUtils.StringToInventory(getSavedInventory(player.getName().toLowerCase()).getInventory()).getContents())
		{
			if (stack != null)
			{
				player.getInventory().addItem(stack);
			}
		}
		
        if (AmnysCraft.getEconomy().has(player, totalPrice))
        {
        	AmnysCraft.getEconomy().withdrawPlayer(player, totalPrice);
        	
			for (ItemStack stack: shopInventory.getContents())
			{
				if (stack != null)
				{
					double price = (getItemPrice(stack.getType())) * stack.getAmount();
					
					if (!(price <= 0.0D))	
					{
						player.getInventory().addItem(stack);	
					}
				}
			}
    		
    		savedInventories.remove(getSavedInventory(player.getName().toLowerCase()));
    		
    		return "true " + Double.toString(totalPrice);
        }
        
        return "false " + Double.toString(totalPrice);
	}
	
	public double sellAll(Player player)
	{
		String lolInventory = InventoryUtils.InventoryToString(player.getInventory());
		Inventory shopInventory = InventoryUtils.StringToInventory(lolInventory);
		double totalPrice = 0.0D;
		
		for (int i = 0; i < shopInventory.getSize(); i++)
		{
			try
			{
				ItemStack stack = shopInventory.getItem(i);
				
				if (stack != null)	
				{
					double price = (getItemPrice(stack.getType())) * stack.getAmount();
					
					if (!(price <= 0.0D))
					{
		        		totalPrice += price;
		        		player.getInventory().removeItem(stack);
					}
				}
			}
			catch (Exception ex)
			{
				
			}
		}
        
		AmnysCraft.getEconomy().depositPlayer(player, totalPrice);	
		return totalPrice;
	}
	
	public void addAllItemPrices()
	{
		this.addItemPrice(Material.STONE, 5);
		this.addItemPrice(Material.GRASS, 5);
		this.addItemPrice(Material.COBBLESTONE, 2);
		this.addItemPrice(Material.WOOD, 1);
		this.addItemPrice(Material.SAPLING, 2);
		this.addItemPrice(Material.SAND, 1);
		this.addItemPrice(Material.DIRT, 1);
		this.addItemPrice(Material.GRAVEL, 1);
		this.addItemPrice(Material.LEAVES, 1);
		this.addItemPrice(Material.SPONGE, 10);
		this.addItemPrice(Material.GLASS, 7);
		this.addItemPrice(Material.LAPIS_BLOCK, 15);
		this.addItemPrice(Material.DISPENSER, 10);
		this.addItemPrice(Material.SANDSTONE, 3);
		this.addItemPrice(Material.NOTE_BLOCK, 7);
		this.addItemPrice(Material.BED, 0);
		this.addItemPrice(Material.POWERED_RAIL, 15);
		this.addItemPrice(Material.DETECTOR_RAIL, 20);
		this.addItemPrice(Material.PISTON_STICKY_BASE, 12);
		this.addItemPrice(Material.WEB, 7);
		this.addItemPrice(Material.DEAD_BUSH, 2);
		this.addItemPrice(Material.PISTON_BASE, 10);
		this.addItemPrice(Material.PISTON_EXTENSION, 10);
		this.addItemPrice(Material.PISTON_MOVING_PIECE, 10);
		this.addItemPrice(Material.WOOL, 2);
		this.addItemPrice(Material.YELLOW_FLOWER, 1);
		this.addItemPrice(Material.BROWN_MUSHROOM, 2);
		this.addItemPrice(Material.RED_MUSHROOM, 2);
		this.addItemPrice(Material.GOLD_BLOCK, 35);
		this.addItemPrice(Material.IRON_BLOCK, 33);
		this.addItemPrice(Material.DOUBLE_STONE_SLAB2, 5);
		this.addItemPrice(Material.STONE_SLAB2, 2);
		this.addItemPrice(Material.BRICK, 4);
		this.addItemPrice(Material.TNT, 17);
		this.addItemPrice(Material.BOOKSHELF, 6);
		this.addItemPrice(Material.MOSSY_COBBLESTONE, 4);
		this.addItemPrice(Material.OBSIDIAN, 20);
		this.addItemPrice(Material.TORCH, 1);
		this.addItemPrice(Material.WOOD_STAIRS, 3);
		this.addItemPrice(Material.CHEST, 1);
		this.addItemPrice(Material.DIAMOND_BLOCK, 50);
		this.addItemPrice(Material.WORKBENCH, 1);
		this.addItemPrice(Material.FURNACE, 2);
		this.addItemPrice(Material.SIGN, 2);
		this.addItemPrice(Material.WOOD_DOOR, 3);
		this.addItemPrice(Material.LADDER, 2);
		this.addItemPrice(Material.RAILS, 15);
		this.addItemPrice(Material.COBBLESTONE_STAIRS, 3);
		this.addItemPrice(Material.LEVER, 2);
		this.addItemPrice(Material.STONE_PLATE, 1);
		this.addItemPrice(Material.IRON_DOOR, 3);
		this.addItemPrice(Material.IRON_DOOR_BLOCK, 3);
		this.addItemPrice(Material.WOOD_PLATE, 1);
		this.addItemPrice(Material.STONE_BUTTON, 1);
		this.addItemPrice(Material.WOOD_BUTTON, 1);
		this.addItemPrice(Material.REDSTONE_TORCH_OFF, 2);
		this.addItemPrice(Material.SNOW, 5);
		this.addItemPrice(Material.SNOW_BLOCK, 5);
		this.addItemPrice(Material.ICE, 5);
		this.addItemPrice(Material.SUGAR_CANE, 2);
		this.addItemPrice(Material.SUGAR_CANE_BLOCK, 2);
		this.addItemPrice(Material.JUKEBOX, 7);
		this.addItemPrice(Material.FENCE, 2);
		this.addItemPrice(Material.PUMPKIN, 3);
		this.addItemPrice(Material.NETHERRACK, 2);
		this.addItemPrice(Material.SOUL_SAND, 7);
		this.addItemPrice(Material.GLOWSTONE, 5);
		this.addItemPrice(Material.JACK_O_LANTERN, 3);
		this.addItemPrice(Material.CAKE, 2);
		this.addItemPrice(Material.CAKE_BLOCK, 2);
		this.addItemPrice(Material.STAINED_GLASS, 10);
		this.addItemPrice(Material.TRAP_DOOR, 4);
		this.addItemPrice(Material.SMOOTH_BRICK, 4);
		this.addItemPrice(Material.IRON_BARDING, 10);
		this.addItemPrice(Material.STAINED_GLASS_PANE, 10);
		this.addItemPrice(Material.MELON, 3);
		this.addItemPrice(Material.MELON_BLOCK, 6);
		this.addItemPrice(Material.MELON_SEEDS, 1);
		this.addItemPrice(Material.PUMPKIN_STEM, 2);
		this.addItemPrice(Material.MELON_STEM, 2);
		this.addItemPrice(Material.VINE, 2);
		this.addItemPrice(Material.FENCE_GATE, 2);
		this.addItemPrice(Material.BRICK_STAIRS, 3);
		this.addItemPrice(Material.MYCEL, 7);
		this.addItemPrice(Material.WATER_LILY, 2);
		this.addItemPrice(Material.NETHER_BRICK, 7);
		this.addItemPrice(Material.NETHER_FENCE, 2);
		this.addItemPrice(Material.NETHER_BRICK_STAIRS, 3);
		this.addItemPrice(Material.NETHER_WARTS, 2);
		this.addItemPrice(Material.ENCHANTMENT_TABLE, 17);
		this.addItemPrice(Material.BREWING_STAND, 17);
		this.addItemPrice(Material.BREWING_STAND_ITEM, 17);
		this.addItemPrice(Material.CAULDRON, 7);
		this.addItemPrice(Material.CAULDRON_ITEM, 7);
		this.addItemPrice(Material.ENDER_STONE, 7);
		this.addItemPrice(Material.REDSTONE_LAMP_OFF, 5);
		this.addItemPrice(Material.REDSTONE_LAMP_ON, 5);
		this.addItemPrice(Material.COCOA, 2);
		this.addItemPrice(Material.SANDSTONE_STAIRS, 3);
		this.addItemPrice(Material.ENDER_CHEST, 30);
		this.addItemPrice(Material.TRIPWIRE_HOOK, 3);
		this.addItemPrice(Material.TRIPWIRE, 3);
		this.addItemPrice(Material.EMERALD_BLOCK, 60);
		this.addItemPrice(Material.BEACON, 30);
		this.addItemPrice(Material.COBBLE_WALL, 4);
		this.addItemPrice(Material.FLOWER_POT, 2);
		this.addItemPrice(Material.FLOWER_POT_ITEM, 2);
		this.addItemPrice(Material.ANVIL, 20);
		this.addItemPrice(Material.TRAPPED_CHEST, 3);
		this.addItemPrice(Material.REDSTONE_COMPARATOR, 5);
		this.addItemPrice(Material.DAYLIGHT_DETECTOR, 7);
		this.addItemPrice(Material.DAYLIGHT_DETECTOR_INVERTED, 7);
		this.addItemPrice(Material.REDSTONE_BLOCK, 30);
		this.addItemPrice(Material.HOPPER, 7);
		this.addItemPrice(Material.QUARTZ_BLOCK, 10);
		this.addItemPrice(Material.ACTIVATOR_RAIL, 25);
		this.addItemPrice(Material.DROPPER, 7);
		this.addItemPrice(Material.CLAY, 3);
		this.addItemPrice(Material.SLIME_BLOCK, 20);
		this.addItemPrice(Material.IRON_TRAPDOOR, 4);
		this.addItemPrice(Material.TRAP_DOOR, 2);
		this.addItemPrice(Material.PRISMARINE, 15);
		this.addItemPrice(Material.SEA_LANTERN, 20);
		this.addItemPrice(Material.HAY_BLOCK, 5);
		this.addItemPrice(Material.CARPET, 3);
		this.addItemPrice(Material.HARD_CLAY, 10);
		this.addItemPrice(Material.COAL_BLOCK, 20);
		this.addItemPrice(Material.PACKED_ICE, 8);
		this.addItemPrice(Material.BANNER, 3);
		this.addItemPrice(Material.STANDING_BANNER, 3);
		this.addItemPrice(Material.WALL_BANNER, 3);
		this.addItemPrice(Material.RED_SANDSTONE, 3);
		this.addItemPrice(Material.IRON_SPADE, 3);
		this.addItemPrice(Material.IRON_PICKAXE, 3);
		this.addItemPrice(Material.IRON_AXE, 3);
		this.addItemPrice(Material.FLINT_AND_STEEL, 3);
		this.addItemPrice(Material.ARROW, 1);
		this.addItemPrice(Material.BOW, 3);
		this.addItemPrice(Material.APPLE, 1);
		this.addItemPrice(Material.COAL, 3);
		this.addItemPrice(Material.DIAMOND, 6);
		this.addItemPrice(Material.GOLD_INGOT, 4);
		this.addItemPrice(Material.IRON_INGOT, 3);
		this.addItemPrice(Material.DIAMOND_SWORD, 20);
		this.addItemPrice(Material.DIAMOND_SPADE, 20);
		this.addItemPrice(Material.DIAMOND_AXE, 20);
		this.addItemPrice(Material.DIAMOND_PICKAXE, 20);
		this.addItemPrice(Material.STICK, 1);
		this.addItemPrice(Material.BOWL, 1);
		this.addItemPrice(Material.STRING, 1);
		this.addItemPrice(Material.FEATHER, 2);
		this.addItemPrice(Material.WHEAT, 1);
		this.addItemPrice(Material.BREAD, 3);
		this.addItemPrice(Material.DIAMOND_HELMET, 30);
		this.addItemPrice(Material.DIAMOND_CHESTPLATE, 35);
		this.addItemPrice(Material.DIAMOND_LEGGINGS, 35);
		this.addItemPrice(Material.DIAMOND_BOOTS, 25);
		this.addItemPrice(Material.FLINT, 1);
		this.addItemPrice(Material.PAINTING, 5);
		this.addItemPrice(Material.GOLDEN_APPLE, 25);
		this.addItemPrice(Material.SIGN, 1);
		this.addItemPrice(Material.BUCKET, 3);
		this.addItemPrice(Material.LAVA_BUCKET, 10);
		this.addItemPrice(Material.WATER_BUCKET, 7);
		this.addItemPrice(Material.MINECART, 7);
		this.addItemPrice(Material.SADDLE, 12);
		this.addItemPrice(Material.REDSTONE, 3);
		this.addItemPrice(Material.SNOW_BALL, 1);
		this.addItemPrice(Material.BOAT, 7);
		this.addItemPrice(Material.LEATHER, 2);
		this.addItemPrice(Material.MILK_BUCKET, 5);
		this.addItemPrice(Material.BOOK, 4);
		this.addItemPrice(Material.SLIME_BALL, 3);
		this.addItemPrice(Material.EXPLOSIVE_MINECART, 7);
		this.addItemPrice(Material.HOPPER_MINECART, 7);
		this.addItemPrice(Material.POWERED_MINECART, 7);
		this.addItemPrice(Material.STORAGE_MINECART, 7);
		this.addItemPrice(Material.EGG, 2);
		this.addItemPrice(Material.COMPASS, 4);
		this.addItemPrice(Material.FISHING_ROD, 2);
		this.addItemPrice(Material.GLOWSTONE_DUST, 1);
		this.addItemPrice(Material.RAW_FISH, 5);
		this.addItemPrice(Material.COOKED_FISH, 7);
		this.addItemPrice(Material.INK_SACK, 3);
		this.addItemPrice(Material.BONE, 2);
		this.addItemPrice(Material.SUGAR, 1);
		this.addItemPrice(Material.COOKIE, 5);
		this.addItemPrice(Material.MAP, 1);
		this.addItemPrice(Material.SHEARS, 3);
		this.addItemPrice(Material.RAW_BEEF, 5);
		this.addItemPrice(Material.COOKED_BEEF, 7);
		this.addItemPrice(Material.RAW_CHICKEN, 4);
		this.addItemPrice(Material.COOKED_CHICKEN, 6);
		this.addItemPrice(Material.ROTTEN_FLESH, 2);
		this.addItemPrice(Material.ENDER_PEARL, 10);
		this.addItemPrice(Material.BLAZE_ROD, 10);
		this.addItemPrice(Material.GHAST_TEAR, 12);
		this.addItemPrice(Material.GOLD_NUGGET, 2);
		this.addItemPrice(Material.POTION, 20);
		this.addItemPrice(Material.GLASS_BOTTLE, 1);
		this.addItemPrice(Material.SPIDER_EYE, 3);
		this.addItemPrice(Material.FERMENTED_SPIDER_EYE, 4);
		this.addItemPrice(Material.BLAZE_POWDER, 10);
		this.addItemPrice(Material.MAGMA_CREAM, 10);
		this.addItemPrice(Material.EYE_OF_ENDER, 14);
		this.addItemPrice(Material.EXP_BOTTLE, 20);
		this.addItemPrice(Material.BOOK_AND_QUILL, 1);
		this.addItemPrice(Material.EMERALD, 7);
		this.addItemPrice(Material.ITEM_FRAME, 3);
		this.addItemPrice(Material.CARROT, 5);
		this.addItemPrice(Material.CARROT_ITEM, 5);
		this.addItemPrice(Material.POTATO, 3);
		this.addItemPrice(Material.POTATO_ITEM, 3);
		this.addItemPrice(Material.BAKED_POTATO, 4);
		this.addItemPrice(Material.GOLDEN_CARROT, 8);
		this.addItemPrice(Material.CARROT_STICK, 5);
		this.addItemPrice(Material.NETHER_STAR, 50);
		this.addItemPrice(Material.PUMPKIN_PIE, 3);
		this.addItemPrice(Material.QUARTZ, 3);
		this.addItemPrice(Material.PRISMARINE_SHARD, 3);
		this.addItemPrice(Material.PRISMARINE_CRYSTALS, 5);
		this.addItemPrice(Material.RABBIT, 3);
		this.addItemPrice(Material.COOKED_RABBIT, 5);
		this.addItemPrice(Material.MUTTON, 3);
		this.addItemPrice(Material.COOKED_MUTTON, 5);
	}
	
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerJoinEvent event)
    {
    	fixPlayer(event.getPlayer());
    	
    	Bukkit.getScheduler().runTaskLater(AmnysCraft.getPlugin(), new BukkitRunnable()
    			{
    				@Override
    				public void run()
    				{
    					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    					String command = "spawn " + event.getPlayer().getName();
    					Bukkit.dispatchCommand(console, command);
    				}
    			}, 5L);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerQuitEvent event)
    {
    	fixPlayer(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onJoin(PlayerLoginEvent event)
    {
    	fixPlayer(event.getPlayer());
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerMoveEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()) && !(event.getTo() == event.getFrom()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerCommandPreprocessEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()) && !(event.getMessage().toLowerCase().startsWith("complete")) && !event.getMessage().toLowerCase().startsWith("/complete"))
    	{
    		event.setCancelled(true);
    	}
    	
    	if (event.getPlayer().getWorld().getName().toLowerCase().equalsIgnoreCase("limbo") && !(event.getMessage().toLowerCase().startsWith("rankup")) && !(event.getMessage().toLowerCase().startsWith("/rankup")) && !(event.getMessage().toLowerCase().startsWith("spawn")) && !(event.getMessage().toLowerCase().startsWith("/spawn")) && !event.getPlayer().isOp())
    	{
    		event.setCancelled(true);
    	}
    	
    	if ((event.getMessage().toLowerCase().startsWith("/warp limbo") || event.getMessage().toLowerCase().startsWith("warp limbo")) && !event.getPlayer().isOp())
    	{
    		event.setCancelled(true);
    	}
    	
    	if (event.getMessage().toLowerCase().equals("/sell") || event.getMessage().toLowerCase().equals("sell"))
    	{
    		event.setCancelled(true);
			ItemStack stack = event.getPlayer().getItemInHand();
			
			if (stack != null)
			{
				double price = (getItemPrice(stack.getType())) * stack.getAmount();
				
				if (!(price <= 0.0D))
				{
	        		AmnysCraft.getEconomy().depositPlayer(event.getPlayer(), price);
	        		event.getPlayer().getInventory().removeItem(stack);
				}
			}
			
			AmnysCraft.sendMessage(event.getPlayer(), "§7Hai venduto con successo quest'item!");
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerChatEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    	
    	if (event.getPlayer().getWorld().getName().toLowerCase().equalsIgnoreCase("limbo") && !event.getPlayer().isOp())
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerInteractEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerTeleportEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerPickupItemEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerInteractEntityEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerDropItemEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(PlayerFishEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(BlockBreakEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void onMove(BlockPlaceEvent event)
    {
    	if (players.contains(event.getPlayer().getName().toLowerCase()))
    	{
    		event.setCancelled(true);
    	}
    }
}