package it.zygotecode.amnyscraft;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.maxgamer.maxbans.MaxBans;

import it.zygotecode.amnyscraft.gui.CraftingArmiGui;
import it.zygotecode.amnyscraft.listeners.AmnysMob;
import it.zygotecode.amnyscraft.listeners.AntiBot;
import it.zygotecode.amnyscraft.listeners.AntiLag;
import it.zygotecode.amnyscraft.listeners.BlockRegen;
import it.zygotecode.amnyscraft.quest.QuestListener;
import it.zygotecode.amnyscraft.quest.QuestManager;
import it.zygotecode.amnyscraft.rank.RankListener;
import it.zygotecode.amnyscraft.rank.RankManager;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.MobCombine;
import it.zygotecode.amnyscraft.utils.NoAI;
import it.zygotecode.commands.AmnysMobCmd;
import it.zygotecode.commands.BlockRegenCmd;
import it.zygotecode.commands.CompleteCmd;
import it.zygotecode.commands.DirectMsgCmd;
import it.zygotecode.commands.LimboCmd;
import it.zygotecode.commands.QuestCmd;
import it.zygotecode.commands.RankupCmd;
import it.zygotecode.commands.SellallCmd;
import it.zygotecode.commands.SetrankCmd;
import it.zygotecode.commands.ShopCmd;
import it.zygotecode.shop.CreativeShop;
import net.milkbowl.vault.Vault;
import net.milkbowl.vault.economy.Economy;

public class AmnysCraft extends JavaPlugin
{
	private static String prefix = "§9AmnysCraft> §7";
	private static RankManager rankManager;
	private static QuestManager questManager;
	private static FileConfiguration config;
	private static CraftingArmiGui craftingArmi;
	private static CreativeShop creativeShop;
	@SuppressWarnings("rawtypes")
	private static Map listp;
	private static Economy economy;
	
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	@Override
	public void onEnable()
	{
		try
		{
			File file = new File(getDataFolder(), "config.yml");
			
			if (!file.exists())
			{			
				try
				{
					getConfig().createSection("general");
					getConfig().createSection("allowed-players");
					getConfig().createSection("amnys-mobs");
					getConfig().createSection("block-regen");
					getConfig().createSection("quests");
					ConfigurationSection general = getConfig().getConfigurationSection("general");
					general.addDefault("prefix", prefix.replace("§", "&"));
					ConfigurationSection amnysMobs = getConfig().getConfigurationSection("amnys-mobs");
					amnysMobs.addDefault("spawners", new ArrayList<String>());
					getConfig().options().copyDefaults(true);
					saveConfig();
					reloadConfig();
					print("Config saved.");
				}
				catch (Exception ex)
				{
					print("Failed to save config:");
					ex.printStackTrace();
				}
			}
			else
			{
				reloadConfig();
				print("Config succesfully reloaded.");
			}
			
			config = getConfig();
			prefix = getConfig().getConfigurationSection("general").getString("prefix").replace("&", "§");
			rankManager = new RankManager();
			questManager = new QuestManager();
			
			for (OfflinePlayer offlinePlayer: Bukkit.getOfflinePlayers())
			{
				try
				{
					Player player = offlinePlayer.getPlayer();
					
					if (getRankManager().hasRank(player))
					{
						try
						{
							
						}
						catch (Exception ex)
						{
							rankManager.addRank(player, rankManager.getRankType(player));
						}
					}
				}
				catch (Exception ex)
				{
					
				}
			}
			
			print("Registered managers.");
			addCmd("setrank", new SetrankCmd());
			addCmd("limbo", new LimboCmd());
			addCmd("quest", new QuestCmd());
			addCmd("rankup", new RankupCmd());
			addCmd("directmsg", new DirectMsgCmd());
			addCmd("amnysmob", new AmnysMobCmd());
			addCmd("blockregen", new BlockRegenCmd());
			addCmd("shop", new ShopCmd());
			addCmd("complete", new CompleteCmd());
			addCmd("sellall", new SellallCmd());
			print("Registered commands.");
			listp = new HashMap();
			creativeShop = new CreativeShop();
			addListener(creativeShop);
			addListener(new RankListener());
			addListener(new AntiBot());
			addListener(new AmnysMob());
			addListener(new QuestListener());
			addListener(new AntiLag());
			addListener(new BlockRegen());
			
			if (Bukkit.getPluginManager().getPlugin("Vault") instanceof Vault)
			{
				RegisteredServiceProvider<Economy> service = Bukkit.getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);

				if (service != null)
				{
					economy = service.getProvider();
				}
			}
			
		    Bukkit.getScheduler().scheduleSyncRepeatingTask(AmnysCraft.getPlugin(), new BukkitRunnable()
		    {
		        public void run()
		        {
		        	for (World w: Bukkit.getWorlds())
		        	{
		        		w.setWeatherDuration(0);
		        		w.setStorm(false);
		        		w.setThundering(false);
		        		w.setThunderDuration(0);
		        	}
		        	
		        	Bukkit.getServer().savePlayers();
		        	MaxBans.instance.getBanManager().setImmunity("zLex__", true);
		        	MaxBans.instance.getBanManager().setImmunity("zlex__", true);
		        }
		    }, 1L, 20L);
		    
		    craftingArmi = new CraftingArmiGui();
		    
		    try
		    {
				ConfigurationSection amnysMobs = getConfig().getConfigurationSection("amnys-mobs");
				
				for (String sh: ((ArrayList<String>) amnysMobs.getList("spawners")))
				{
					try
					{
						String[] lolh = sh.split("\\s+");
						String mobName = lolh[0];
        				ConfigurationSection sectionh = getConfig().getConfigurationSection("amnys-mobs").getConfigurationSection(mobName);
						long spawnTicks = sectionh.getLong("spawner-timing");
						int blockX = Integer.parseInt(lolh[1]);
						int blockY = Integer.parseInt(lolh[2]);
						int blockZ = Integer.parseInt(lolh[3]);
						String worldName = lolh[4];
						World world = Bukkit.getWorld(worldName);
						Block block = world.getBlockAt(blockX, blockY, blockZ);
						
						Bukkit.getScheduler().scheduleSyncRepeatingTask(AmnysCraft.getPlugin(), new BukkitRunnable()
					    {
							@Override
							public void run()
							{
				        		if (block != null)
				        		{
				        			if (!block.isEmpty())
				        			{
				        				ConfigurationSection section = getConfig().getConfigurationSection("amnys-mobs").getConfigurationSection(mobName);
										EntityType entityType = EntityType.valueOf(section.getString("type").toUpperCase());
										String name = section.getString("name");
										name = name.replace("&", "§");
										double health = section.getDouble("health");
										double maxhealth = section.getDouble("maxhealth");
										LivingEntity entity = (LivingEntity) world.spawnEntity(block.getLocation().add(0.0, 1.0, 0.0), entityType);
										
										if (section.getBoolean("setname", true))
										{
											entity.setCustomName(name);
											entity.setCustomNameVisible(true);
										}
										
										entity.setMaxHealth(maxhealth);
										entity.setHealth(health);
										
										if (!section.getString("helmet").equalsIgnoreCase("air"))
										{
											ItemStack helmet = new ItemStack(Material.getMaterial(section.getString("helmet")));
											ItemMeta itemMeta = helmet.getItemMeta();
											itemMeta.setDisplayName(section.getString("helmet-name"));
											ArrayList<String> lore = new ArrayList<String>();
											
											for (String s: ((ArrayList<String>) section.getList("helmet-lore")))
											{
												lore.add(s);
											}
											
											itemMeta.setLore(lore);
											
											for (String s: ((ArrayList<String>) section.getList("helmet-enchantments")))
											{
												String[] lol = s.split("\\s+");
												itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
											}
											
											helmet.setItemMeta(itemMeta);
											entity.getEquipment().setHelmet(helmet);
										}
										
										if (!section.getString("chestplate").equalsIgnoreCase("air"))
										{
											ItemStack chestplate = new ItemStack(Material.getMaterial(section.getString("chestplate")));
											ItemMeta itemMeta = chestplate.getItemMeta();
											itemMeta.setDisplayName(section.getString("chestplate"));
											ArrayList<String> lore = new ArrayList<String>();
											
											for (String s: ((ArrayList<String>) section.getList("chestplate-lore")))
											{
												lore.add(s);
											}
											
											itemMeta.setLore(lore);
											
											for (String s: ((ArrayList<String>) section.getList("chestplate-enchantments")))
											{
												String[] lol = s.split("\\s+");
												itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
											}
											
											chestplate.setItemMeta(itemMeta);
											entity.getEquipment().setChestplate(chestplate);
										}
										
										if (!section.getString("leggings").equalsIgnoreCase("air"))
										{
											ItemStack leggings = new ItemStack(Material.getMaterial(section.getString("leggings")));
											ItemMeta itemMeta = leggings.getItemMeta();
											itemMeta.setDisplayName(section.getString("leggings-name"));
											ArrayList<String> lore = new ArrayList<String>();
											
											for (String s: ((ArrayList<String>) section.getList("leggings-lore")))
											{
												lore.add(s);
											}
											
											itemMeta.setLore(lore);
											
											for (String s: ((ArrayList<String>) section.getList("leggings-enchantments")))
											{
												String[] lol = s.split("\\s+");
												itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
											}
											
											leggings.setItemMeta(itemMeta);
											entity.getEquipment().setLeggings(leggings);
										}
										
										if (!section.getString("boots").equalsIgnoreCase("air"))
										{
											ItemStack boots = new ItemStack(Material.getMaterial(section.getString("boots")));
											ItemMeta itemMeta = boots.getItemMeta();
											itemMeta.setDisplayName(section.getString("boots-name"));
											ArrayList<String> lore = new ArrayList<String>();
											
											for (String s: ((ArrayList<String>) section.getList("boots-lore")))
											{
												lore.add(s);
											}
											
											itemMeta.setLore(lore);
											
											for (String s: ((ArrayList<String>) section.getList("boots-enchantments")))
											{
												String[] lol = s.split("\\s+");
												itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
											}
											
											boots.setItemMeta(itemMeta);
											entity.getEquipment().setBoots(boots);
										}
										
										if (!section.getString("hand").equalsIgnoreCase("air"))
										{
											ItemStack hand = new ItemStack(Material.getMaterial(section.getString("hand")));
											ItemMeta itemMeta = hand.getItemMeta();
											itemMeta.setDisplayName(section.getString("hand-name"));
											ArrayList<String> lore = new ArrayList<String>();
											
											for (String s: ((ArrayList<String>) section.getList("hand-lore")))
											{
												lore.add(s);
											}
											
											itemMeta.setLore(lore);
											
											for (String s: ((ArrayList<String>) section.getList("hand-enchantments")))
											{
												String[] lol = s.split("\\s+");
												itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
											}
											
											hand.setItemMeta(itemMeta);
											entity.getEquipment().setItemInHand(hand);
										}
										
										if (!section.getString("spawn-command").equals("") && !section.getString("spawn-command").replace(" ", "").equals("") && !section.getString("spawn-command").equals(null) && !section.getString("spawn-command").isEmpty())
										{
											ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
											String command = section.getString("spawn-command");
											Bukkit.dispatchCommand(console, command);
										}
										
										AmnysVariables.entityDamage.add(new MobCombine(entity.getEntityId(), section.getDouble("damage"), mobName));					
										NoAI.setAiEnabled(entity, section.getBoolean("ai"));	
				        			}
				        		}
							}
					    }, 1L, spawnTicks);
					}
					catch (Exception ex)
					{
						
					}
				}
		    }
		    catch (Exception ex)
		    {
		    	
		    }
			
			print("Registered listeners.");
			print("Plugin succesfully enabled.");
		}
		catch (Exception ex)
		{
			print("An error occurred while enabling the plugin:");
			ex.printStackTrace();
		}
	}
	
	@Override
	public void onDisable()
	{
		try
		{
			this.saveConfig();
			print("Plugin succesfully disabled.");
		}
		catch (Exception ex)
		{
			print("An error occurred while disabling the plugin:");
			ex.printStackTrace();
		}
	}
	
	public static void registerListener(Listener listener)
	{
		Bukkit.getServer().getPluginManager().registerEvents(listener, getPlugin());
	}
	
	public static void addListener(Listener listener)
	{
		Bukkit.getServer().getPluginManager().registerEvents(listener, getPlugin());
	}
	
	public static void print(String line)
	{
		if (line != null && line != "" && !line.isEmpty() && !(line.replace(" ", "") == "") && !(line.replace(" ", "") == null))
		{
			System.out.println("[AmnysCraft] " + line);
		}
	}
	
	public static String getPrefix()
	{
		return prefix;
	}
	
	public static void setPrefix(String pref)
	{
		prefix = pref;
	}
	
	public static Plugin getPlugin()
	{
		return Bukkit.getServer().getPluginManager().getPlugin("AmnysCraft");
	}
	
	public static void sendMessage(Player p, String msg)
	{
		if (msg != "" && msg != null && !msg.isEmpty() && !(msg.replace(" ", "") == "") && !(msg.replace(" ", "") == null))
		{
			p.sendMessage(prefix + msg.replace("&", "§"));
		}
	}
	
	public static void msg(Player p, String msg)
	{
		p.sendMessage(prefix + msg);
	}
	
	public void addCmd(String name, CommandExecutor cmd)
	{
		getCommand(name.toLowerCase()).setExecutor(cmd);
	}
	
	public static FileConfiguration getConfigh()
	{
		return config;
	}
	
	public static CraftingArmiGui getCraftingArmi()
	{
		return craftingArmi;
	}
	
	public static RankManager getRankManager()
	{
		return rankManager;
	}
	
	@SuppressWarnings("rawtypes")
	public static HashMap getListp()
	{
		return (HashMap) listp;
	}
	
	public static QuestManager getQuestManager()
	{
		return questManager;
	}
	
	public static CreativeShop getCreativeShop()
	{
		return creativeShop;
	}
	
	public static Economy getEconomy()
	{
		return economy;
	}
}