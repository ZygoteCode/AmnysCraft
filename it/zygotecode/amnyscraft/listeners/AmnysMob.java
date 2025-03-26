package it.zygotecode.amnyscraft.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.craftbukkit.v1_8_R1.CraftWorld;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.MobCombine;
import it.zygotecode.amnyscraft.utils.NoAI;
import net.minecraft.server.v1_8_R1.BlockPosition;
import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagList;
import net.minecraft.server.v1_8_R1.TileEntityMobSpawner;

public class AmnysMob implements Listener
{
	public static List<String> getSections(ConfigurationSection source)
	{
	    List<String> nodes = new ArrayList<String>();
	    
	    for (String key : source.getKeys(false))
	    {
	        if (source.isConfigurationSection(key))
	        {
	            nodes.add(key.toLowerCase());
	        }
	    }
	    
	    return nodes;
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInteract(PlayerInteractEvent event)
	{
		try
		{
			if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
			{
				if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith("§eCustom mob egg §c> §3"))
				{
					event.setCancelled(true);
					Player player = event.getPlayer();
					String mobName = event.getPlayer().getItemInHand().getItemMeta().getDisplayName();
					mobName = mobName.replace("§eCustom mob egg §c> §3", "");
					mobName = mobName.replace("§", "");
					mobName = mobName.replace(" ", "");
					mobName = mobName.toLowerCase();
					ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(mobName);
					EntityType entityType = EntityType.valueOf(section.getString("type").toUpperCase());
					String name = section.getString("name");
					name = name.replace("&", "§");
					double health = section.getDouble("health");
					double maxhealth = section.getDouble("maxhealth");
					LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(event.getClickedBlock().getLocation().add(0.0, 1.0, 0.0), entityType);
					
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
					
					if (player.getGameMode().equals(GameMode.SURVIVAL) || player.getGameMode().equals(GameMode.ADVENTURE))
					{
						player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
						player.updateInventory();
					}
				}
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlace(BlockPlaceEvent event)
	{
		long spawnTicks = 60L;
		
		try
		{
			if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith("§eCustom mob spawner §c> §3"))
			{
				Player player = event.getPlayer();
				String mobName = event.getItemInHand().getItemMeta().getDisplayName();
				mobName = mobName.replace("§eCustom mob spawner §c> §3", "");
				mobName = mobName.replace("§", "");
				mobName = mobName.replace(" ", "");
				mobName = mobName.toLowerCase();
				ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(mobName);
				spawnTicks = section.getLong("spawner-timing");
				Location loc = event.getBlockPlaced().getLocation();		
				ArrayList<String> spawners = ((ArrayList<String>) AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getList("spawners"));
			    spawners.add(mobName + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getName());
			    AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").set("spawners", spawners);
				BlockPosition blockPos = new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getZ());
				TileEntityMobSpawner spawner = (TileEntityMobSpawner) ((CraftWorld) player.getWorld()).getHandle().getTileEntity(blockPos);
				NBTTagCompound spawnerTag = new NBTTagCompound(); //spawner.d();
				spawnerTag.setShort("SpawnRange", (short) 20);
				spawnerTag.setShort("MaxNearbyEntities", (short) 100);
				NBTTagList handList = new NBTTagList();
				NBTTagList armorList = new NBTTagList();
				NBTTagCompound mainHand = new NBTTagCompound();
				NBTTagCompound offHand = new NBTTagCompound();
				mainHand.setString("id", "minecraft:" + section.getString("hand").toLowerCase());
				mainHand.setShort("Count", (short) 1);
				NBTTagList enchantments = new NBTTagList();	
				
				for (String s: ((ArrayList<String>) section.getList("hand-enchantments")))
				{
					String[] lol = s.split("\\s+");
					NBTTagCompound enchant = new NBTTagCompound();
					enchant.setShort("id", (short) Enchantment.getByName(lol[0]).getId());
					enchant.setShort("lvl", (short) Integer.parseInt(lol[1]));
					enchantments.add(enchant);
				}
				
				NBTTagCompound ench = new NBTTagCompound();
				ench.set("ench", enchantments);
				mainHand.set("tag", ench);
				handList.add(mainHand);
				handList.add(offHand);
				NBTTagCompound helmet = new NBTTagCompound();
				NBTTagCompound chestplate = new NBTTagCompound();
				NBTTagCompound leggings = new NBTTagCompound();
				NBTTagCompound boots = new NBTTagCompound();
				helmet.setString("id", "minecraft:" + section.getString("helmet").toLowerCase());
				helmet.setShort("Count", (short) 1);
				chestplate.setString("id", "minecraft:" + section.getString("chestplate").toLowerCase());
				chestplate.setShort("Count", (short) 1);
				boots.setString("id", "minecraft:" + section.getString("boots").toLowerCase());
				boots.setShort("Count", (short) 1);
				leggings.setString("id", "minecraft:" + section.getString("leggings").toLowerCase());
				leggings.setShort("Count", (short) 1);
				armorList.add(boots);
				armorList.add(leggings);
				armorList.add(chestplate);
				armorList.add(helmet);
				NBTTagCompound spawnData = new NBTTagCompound();
				spawnData.setString("id", section.getString("type").toLowerCase());
				spawnData.set("HandItems", handList);
				spawnData.set("ArmorItems", armorList);
				spawnerTag.set("SpawnData", spawnData);
				spawner.a(spawnerTag);
			}
		}
		catch (Exception ex)
		{
			
		}
		
		try
		{
			if (event.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith("§eCustom mob spawner §c> §3"))
			{
				Bukkit.getScheduler().scheduleSyncRepeatingTask(AmnysCraft.getPlugin(), new BukkitRunnable()
			    {
			        public void run()
			        {
			        	try
			        	{
			        		if (event.getBlockPlaced() != null)
			        		{
			        			if (!event.getBlockPlaced().isEmpty())
			        			{
									String mobNameh = event.getItemInHand().getItemMeta().getDisplayName();
									mobNameh = mobNameh.replace("§eCustom mob spawner §c> §3", "");
									mobNameh = mobNameh.replace("§", "");
									mobNameh = mobNameh.replace(" ", "");
									mobNameh = mobNameh.toLowerCase();
									ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(mobNameh);
									Player player = event.getPlayer();
									
									EntityType entityType = EntityType.valueOf(section.getString("type").toUpperCase());
									String name = section.getString("name");
									name = name.replace("&", "§");
									double health = section.getDouble("health");
									double maxhealth = section.getDouble("maxhealth");
									LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(event.getBlockPlaced().getLocation().add(0.0, 1.0, 0.0), entityType);
									
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
									
									AmnysVariables.entityDamage.add(new MobCombine(entity.getEntityId(), section.getDouble("damage"), mobNameh));					
									NoAI.setAiEnabled(entity, section.getBoolean("ai"));	
			        			}
			        		}
			        	}
			        	catch (Exception ex)
			        	{
			        		
			        	}
			        }
			    }, 1L, spawnTicks);
			}
		}
		catch (Exception ex)
		{
			
		}
	}
	
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDamage(EntityDamageByEntityEvent event)
	{
		for (MobCombine mc: AmnysVariables.entityDamage)
		{
			ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(mc.getCustomMobName());
			
			if (event.getDamager().getEntityId() == mc.getEntityId())
			{
				if (section.getBoolean("nodamage"))
				{
					event.setDamage(0.0D);
					event.setCancelled(true);
				}
				else
				{
					if (!(mc.getDamage() == 0.0D))
					{
						event.setDamage(mc.getDamage());
					}		
				}	
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.MONITOR)
	public void onDeath(EntityDeathEvent event)
	{
		Location loc = event.getEntity().getLocation();
		World world = event.getEntity().getWorld();
		
		for (int i = 0; i < AmnysVariables.entityDamage.size(); i++)
		{
			MobCombine mc = AmnysVariables.entityDamage.get(i);
			
			if (mc.getEntityId() == event.getEntity().getEntityId())
			{
				event.getDrops().clear();
				event.setDroppedExp(0);
				ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(mc.getCustomMobName());
				
				if (!section.getString("death-command").equals("") && !section.getString("death-command").replace(" ", "").equals("") && !section.getString("death-command").equals(null) && !section.getString("death-command").isEmpty())
				{
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = section.getString("death-command");
					Bukkit.dispatchCommand(console, command);
				}
				
				if (!section.getString("drop").equalsIgnoreCase("air"))
				{
					Random rnd = new Random();
					
					if (rnd.nextInt(1000) < Integer.parseInt(section.getString("drop-chance").replace("%", "").replace(" ", "")))
					{
						ItemStack drop = new ItemStack(Material.getMaterial(section.getString("drop")), section.getInt("drop-quantity"));
						ItemMeta itemMeta = drop.getItemMeta();
						itemMeta.setDisplayName(section.getString("drop-name"));
						ArrayList<String> lore = new ArrayList<String>();
						
						for (String s: ((ArrayList<String>) section.getList("drop-lore")))
						{
							lore.add(s);
						}
						
						itemMeta.setLore(lore);
						
						for (String s: ((ArrayList<String>) section.getList("drop-enchantments")))
						{
							String[] lol = s.split("\\s+");
							itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
						}
						
						drop.setItemMeta(itemMeta);
						world.dropItem(loc, drop);

						if (!section.getString("drop-command").equals("") && !section.getString("drop-command").replace(" ", "").equals("") && !section.getString("drop-command").equals(null) && !section.getString("drop-command").isEmpty())
						{
							ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
							String command = section.getString("drop-command");
							Bukkit.dispatchCommand(console, command);
						}
					}
				}
				
				/*try
				{
					for (String sh: getSections(section.getConfigurationSection("drops")))
					{
						try
						{
							Random rnd = new Random();
							
							if (rnd.nextInt(1000) < Integer.parseInt(section.getConfigurationSection("drops").getConfigurationSection(sh).getString("drop-chance").replace("%", "").replace(" ", "")))
							{
								ItemStack drop = new ItemStack(Material.getMaterial(section.getConfigurationSection("drops").getConfigurationSection(sh).getString("drop")), section.getConfigurationSection("drops").getConfigurationSection(sh).getInt("drop-quantity"));
								ItemMeta itemMeta = drop.getItemMeta();
								itemMeta.setDisplayName(section.getConfigurationSection("drops").getConfigurationSection(sh).getString("drop-name"));
								ArrayList<String> lore = new ArrayList<String>();
								
								for (String s: ((ArrayList<String>) section.getConfigurationSection("drops").getConfigurationSection(sh).getList("drop-lore")))
								{
									lore.add(s);
								}
								
								itemMeta.setLore(lore);
								
								for (String s: ((ArrayList<String>) section.getConfigurationSection("drops").getConfigurationSection(sh).getList("drop-enchantments")))
								{
									String[] lol = s.split("\\s+");
									itemMeta.addEnchant(Enchantment.getByName(lol[0]), Integer.parseInt(lol[1]), true);
								}
								
								drop.setItemMeta(itemMeta);
								world.dropItem(loc, drop);

								if (!section.getString("drop-command").equals("") && !section.getString("drop-command").replace(" ", "").equals("") && !section.getString("drop-command").equals(null) && !section.getString("drop-command").isEmpty())
								{
									ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
									String command = section.getString("drop-command");
									Bukkit.dispatchCommand(console, command);
								}
							}
						}
						catch (Exception ex)
						{
							
						}
					}
				}
				catch (Exception ex)
				{
					
				}*/
				
				if (!section.getString("money").equalsIgnoreCase("0 0"))
				{
					String[] theMoney = section.getString("money").split("\\s+");
					double startMoney = Double.parseDouble(theMoney[0]);
					double endMoney = Double.parseDouble(theMoney[1]);
					double finishedMoney = ThreadLocalRandom.current().nextDouble(startMoney, endMoney);
					
					if (event.getEntity().getLastDamageCause().getCause().equals(DamageCause.ENTITY_ATTACK))
					{
						EntityDamageByEntityEvent evant = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
						
						if (evant.getDamager() instanceof Player)
						{
							Player damager = (Player) evant.getDamager();
							AmnysCraft.getEconomy().depositPlayer(damager, finishedMoney);
							damager.sendMessage("§9Money> §7Hai guadagnato §c$" + Double.toString(finishedMoney) + " §7per aver killato il mob custom §c" + mc.getCustomMobName() + "§7.");
						}
					}
				}
				
				AmnysVariables.entityDamage.remove(mc);
			}
		}
	}
}