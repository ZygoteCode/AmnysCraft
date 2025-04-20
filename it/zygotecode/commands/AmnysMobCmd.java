package it.zygotecode.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.MobCombine;
import it.zygotecode.amnyscraft.utils.NoAI;

public class AmnysMobCmd implements CommandExecutor
{
	@SuppressWarnings("unchecked")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (player.hasPermission("*") || player.isOp() || player.hasPermission("amnyscraft.*") || player.hasPermission("amnyscraft.amnysmob.*"))
			{
				try
				{
					if (args[0].equalsIgnoreCase("create"))
					{
						AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").createSection(args[1].toLowerCase());
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("type", "SKELETON");
						section.set("spawn-command", "");
						section.set("death-command", "");
						section.set("drop-command", "");
						//section.set("drops", new ArrayList<ConfigurationSection>());
						section.set("drop", "AIR");
						section.set("drop-quantity", 1);
						section.set("drop-name", "&fOmegalul");
						section.set("drop-lore", new ArrayList<String>());
						section.set("drop-enchantments", new ArrayList<String>());
						section.set("drop-chance", "100%");
						section.set("setname", false);
						section.set("name", "&fOmegalul");
						section.set("health", 20.0D);
						section.set("maxhealth", 20.0D);
						section.set("helmet", "AIR");
						section.set("helmet-name", "&fOmegalul");
						section.set("helmet-lore", new ArrayList<String>());
						section.set("helmet-enchantments", new ArrayList<String>());
						section.set("chestplate", "AIR");
						section.set("chestplate-name", "&fOmegalul");
						section.set("chestplate-lore", new ArrayList<String>());
						section.set("chestplate-enchantments", new ArrayList<String>());
						section.set("leggings", "AIR");
						section.set("leggings-name", "&fOmegalul");
						section.set("leggings-lore", new ArrayList<String>());
						section.set("leggings-enchantments", new ArrayList<String>());
						section.set("boots", "AIR");
						section.set("boots-name", "&fOmegalul");
						section.set("boots-lore", new ArrayList<String>());
						section.set("boots-enchantments", new ArrayList<String>());
						section.set("hand", "AIR");
						section.set("hand-name", "&fOmegalul");
						section.set("hand-lore", new ArrayList<String>());
						section.set("hand-enchantments", new ArrayList<String>());
						section.set("damage", 0.0D);
						section.set("nodamage", false);
						section.set("ai", true);
						section.set("spawner-timing", 60L);
						section.set("money", "0 0");
						AmnysCraft.sendMessage(player, "§7Creato con successo il nuovo mob §a" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("type"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("type", args[2].toUpperCase());
						AmnysCraft.sendMessage(player, "§7Settato con successo il tipo del mob §a" + args[1].toLowerCase() + " §7a §c" + args[2].toUpperCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("spawn"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						EntityType entityType = EntityType.valueOf(section.getString("type").toUpperCase());
						String name = section.getString("name");
						name = name.replace("&", "§");
						double health = section.getDouble("health");
						double maxhealth = section.getDouble("maxhealth");
						LivingEntity entity = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), entityType);
						
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
						
						AmnysVariables.entityDamage.add(new MobCombine(entity.getEntityId(), section.getDouble("damage"), args[1].toLowerCase()));					
						NoAI.setAiEnabled(entity, section.getBoolean("ai"));			
						AmnysCraft.sendMessage(player, "§7Spawnato con successo il mob custom §a" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("drop") || args[0].equalsIgnoreCase("drops"))
					{
						if (args[2].equalsIgnoreCase("hand"))
						{
							ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
							
							if (!player.getItemInHand().getType().equals(Material.AIR))
							{
								section.set("drop", player.getItemInHand().getType().toString());
								ItemMeta itemMeta = player.getItemInHand().getItemMeta();
								section.set("drop-name", itemMeta.getDisplayName());
								ArrayList<String> lore = new ArrayList<String>();
								ArrayList<String> enchantments = new ArrayList<String>();
								
								if (itemMeta.hasLore())
								{
									for (String s: itemMeta.getLore())
									{
										lore.add(s);
									}
									
									section.set("drop-lore", lore);
								}
								
								if (itemMeta.hasEnchants())
								{
									for (Enchantment enchantment: itemMeta.getEnchants().keySet())
									{
										enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
									}
									
									section.set("drop-enchantments", enchantments);
								}
								
								section.set("drop-quantity", player.getItemInHand().getAmount());
								
								try
								{
									section.set("drop-chance", args[3].toLowerCase());
								}
								catch (Exception ex)
								{
									section.set("drop-chance", "100%");
								}
							}
							else
							{
								section.set("drop", "AIR");
								section.set("drop-name", "");
								section.set("drop-lore", new ArrayList<String>());
								section.set("drop-enchantments", new ArrayList<String>());
								section.set("drop-chance", "100%");
								section.set("drop-quantity", 1);
							}
							
							AmnysCraft.sendMessage(player, "§7Settato con successo l'elmetto del mob.");
							return true;
						}
					}
					/*else if (args[0].equalsIgnoreCase("drop") || args[0].equalsIgnoreCase("drops"))
					{
						if (args[1].equalsIgnoreCase("add"))
						{
							if (args[4].equalsIgnoreCase("hand"))
							{
								ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[2].toLowerCase()).getConfigurationSection("drops");
								section.createSection(args[3].toLowerCase());
								ConfigurationSection dropSection = section.getConfigurationSection(args[3].toLowerCase());
								
								if (!player.getItemInHand().getType().equals(Material.AIR))
								{
									dropSection.set("drop", player.getItemInHand().getType().toString());
									ItemMeta itemMeta = player.getItemInHand().getItemMeta();
									dropSection.set("drop-name", itemMeta.getDisplayName());
									ArrayList<String> lore = new ArrayList<String>();
									ArrayList<String> enchantments = new ArrayList<String>();
									
									if (itemMeta.hasLore())
									{
										for (String s: itemMeta.getLore())
										{
											lore.add(s);
										}
										
										dropSection.set("drop-lore", lore);
									}
									
									if (itemMeta.hasEnchants())
									{
										for (Enchantment enchantment: itemMeta.getEnchants().keySet())
										{
											enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
										}
										
										dropSection.set("drop-enchantments", enchantments);
									}
									
									dropSection.set("drop-quantity", player.getItemInHand().getAmount());
									
									try
									{
										dropSection.set("drop-chance", args[5].toLowerCase());
									}
									catch (Exception ex)
									{
										dropSection.set("drop-chance", "100%");
									}
								}
								else
								{
									dropSection.set("drop", "AIR");
									dropSection.set("drop-name", "");
									dropSection.set("drop-lore", new ArrayList<String>());
									dropSection.set("drop-enchantments", new ArrayList<String>());
									dropSection.set("drop-chance", "100%");
									dropSection.set("drop-quantity", 1);
								}
								
								AmnysCraft.sendMessage(player, "§7Aggiunto con successo il drop §c" + dropSection.getName() + " §7del mob.");
								return true;
							}
						}
						else if (args[1].equalsIgnoreCase("remove"))
						{
							AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[2]).getConfigurationSection("drops").set(args[3].toLowerCase(), null);
							AmnysCraft.sendMessage(player, "§7Eliminato con successo il drop mob custom §a" + args[3].toLowerCase() + "§7.");
							return true;
						}
					}*/
					else if (args[0].equalsIgnoreCase("name"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						String totalName = "";
						int lal = 0;
						
						for (String s: args)
						{
							lal += 1;
							
							if (!(lal == 1 || lal == 2))
							{
								if (totalName == "")
								{
									totalName = s;
								}
								else
								{
									totalName += " " + s;
								}
							}
						}
						
						section.set("name", totalName);
						section.set("setname", true);
						AmnysCraft.sendMessage(player, "§7Settato con successo il nome del mob §a" + args[1].toLowerCase() + " §7a §c'" + totalName + "'§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("health"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("health", Double.parseDouble(args[2]));
						AmnysCraft.sendMessage(player, "§7Settata con successo la vita del mob §a" + args[1].toLowerCase() + " §7a §e" + Double.toString(Double.parseDouble(args[2])) + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("maxhealth"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("maxhealth", Double.parseDouble(args[2]));
						AmnysCraft.sendMessage(player, "§7Settata con successo la vita massima del mob §a" + args[1].toLowerCase() + " §7a §e" + Double.toString(Double.parseDouble(args[2])) + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("helmet"))
					{
						if (args[2].equalsIgnoreCase("hand"))
						{
							ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
							
							if (!player.getItemInHand().getType().equals(Material.AIR))
							{
								section.set("helmet", player.getItemInHand().getType().toString());
								ItemMeta itemMeta = player.getItemInHand().getItemMeta();
								
								if (itemMeta.getDisplayName() != null)
								{
									section.set("helmet-name", itemMeta.getDisplayName());
								}
								else
								{
									section.set("helmet-name", "§fOmegalul");
								}
								
								ArrayList<String> lore = new ArrayList<String>();
								ArrayList<String> enchantments = new ArrayList<String>();
								
								if (itemMeta.hasLore())
								{
									for (String s: itemMeta.getLore())
									{
										lore.add(s);
									}
									
									section.set("helmet-lore", lore);
								}
								
								if (itemMeta.hasEnchants())
								{
									for (Enchantment enchantment: itemMeta.getEnchants().keySet())
									{
										enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
									}
									
									section.set("helmet-enchantments", enchantments);
								}
							}
							else
							{
								section.set("helmet", "AIR");
								section.set("helmet-name", "");
								section.set("helmet-lore", new ArrayList<String>());
								section.set("helmet-enchantments", new ArrayList<String>());
							}
							
							AmnysCraft.sendMessage(player, "§7Settato con successo l'elmetto del mob.");
							return true;
						}
					}
					else if (args[0].equalsIgnoreCase("chestplate"))
					{
						if (args[2].equalsIgnoreCase("hand"))
						{
							ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
							
							if (!player.getItemInHand().getType().equals(Material.AIR))
							{
								section.set("chestplate", player.getItemInHand().getType().toString());
								ItemMeta itemMeta = player.getItemInHand().getItemMeta();
								
								if (itemMeta.getDisplayName() != null)
								{
									section.set("chestplate-name", itemMeta.getDisplayName());
								}
								else
								{
									section.set("chestplate-name", "§fOmegalul");
								}
								
								ArrayList<String> lore = new ArrayList<String>();
								ArrayList<String> enchantments = new ArrayList<String>();
								
								if (itemMeta.hasLore())
								{
									for (String s: itemMeta.getLore())
									{
										lore.add(s);
									}
									
									section.set("chestplate-lore", lore);
								}
								
								if (itemMeta.hasEnchants())
								{
									for (Enchantment enchantment: itemMeta.getEnchants().keySet())
									{
										enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
									}
									
									section.set("chestplate-enchantments", enchantments);
								}	
							}
							else
							{
								section.set("chestplate", "AIR");
								section.set("chestplate-name", "");
								section.set("chestplate-lore", new ArrayList<String>());
								section.set("chestplate-enchantments", new ArrayList<String>());
							}
							
							AmnysCraft.sendMessage(player, "§7Settato con successo la corazza del mob.");
							return true;
						}
					}
					else if (args[0].equalsIgnoreCase("leggings"))
					{
						if (args[2].equalsIgnoreCase("hand"))
						{
							ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
							
							if (!player.getItemInHand().getType().equals(Material.AIR))
							{
								section.set("leggings", player.getItemInHand().getType().toString());
								ItemMeta itemMeta = player.getItemInHand().getItemMeta();
								
								if (itemMeta.getDisplayName() != null)
								{
									section.set("leggings-name", itemMeta.getDisplayName());
								}
								else
								{
									section.set("leggings-name", "§fOmegalul");
								}
								
								ArrayList<String> lore = new ArrayList<String>();
								ArrayList<String> enchantments = new ArrayList<String>();
								
								if (itemMeta.hasLore())
								{
									for (String s: itemMeta.getLore())
									{
										lore.add(s);
									}
									
									section.set("leggings-lore", lore);
								}
								
								if (itemMeta.hasEnchants())
								{
									for (Enchantment enchantment: itemMeta.getEnchants().keySet())
									{
										enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
									}
									
									section.set("leggings-enchantments", enchantments);
								}	
							}
							else
							{
								section.set("leggings", "AIR");
								section.set("leggings-name", "");
								section.set("leggings-lore", new ArrayList<String>());
								section.set("leggings-enchantments", new ArrayList<String>());
							}
							
							AmnysCraft.sendMessage(player, "§7Settato con successo i gambali del mob.");
							return true;
						}
					}
					else if (args[0].equalsIgnoreCase("boots"))
					{
						if (args[2].equalsIgnoreCase("hand"))
						{
							ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
							
							if (!player.getItemInHand().getType().equals(Material.AIR))
							{
								section.set("boots", player.getItemInHand().getType().toString());
								ItemMeta itemMeta = player.getItemInHand().getItemMeta();
								
								if (itemMeta.getDisplayName() != null)
								{
									section.set("boots-name", itemMeta.getDisplayName());
								}
								else
								{
									section.set("boots-name", "§fOmegalul");
								}
								
								ArrayList<String> lore = new ArrayList<String>();
								ArrayList<String> enchantments = new ArrayList<String>();
								
								if (itemMeta.hasLore())
								{
									for (String s: itemMeta.getLore())
									{
										lore.add(s);
									}
									
									section.set("boots-lore", lore);
								}
								
								if (itemMeta.hasEnchants())
								{
									for (Enchantment enchantment: itemMeta.getEnchants().keySet())
									{
										enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
									}
									
									section.set("boots-enchantments", enchantments);
								}	
							}
							else
							{
								section.set("boots", "AIR");
								section.set("boots-name", "");
								section.set("boots-lore", new ArrayList<String>());
								section.set("boots-enchantments", new ArrayList<String>());
							}
							
							AmnysCraft.sendMessage(player, "§7Settato con successo gli stivaletti del mob.");
							return true;
						}
					}
					else if (args[0].equalsIgnoreCase("hand"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						
						if (!player.getItemInHand().getType().equals(Material.AIR))
						{
							section.set("hand", player.getItemInHand().getType().toString());
							ItemMeta itemMeta = player.getItemInHand().getItemMeta();
							
							if (itemMeta.getDisplayName() != null)
							{
								section.set("hand-name", itemMeta.getDisplayName());
							}
							else
							{
								section.set("hand-name", "§fOmegalul");
							}
							
							ArrayList<String> lore = new ArrayList<String>();
							ArrayList<String> enchantments = new ArrayList<String>();
							
							if (itemMeta.hasLore())
							{
								for (String s: itemMeta.getLore())
								{
									lore.add(s);
								}
								
								section.set("hand-lore", lore);
							}
							
							if (itemMeta.hasEnchants())
							{
								for (Enchantment enchantment: itemMeta.getEnchants().keySet())
								{
									enchantments.add(enchantment.getName() + " " + Integer.toString(itemMeta.getEnchantLevel(enchantment)));
								}
								
								section.set("hand-enchantments", enchantments);
							}	
						}
						else
						{
							section.set("hand", "AIR");
							section.set("hand-name", "");
							section.set("hand-lore", new ArrayList<String>());
							section.set("hand-enchantments", new ArrayList<String>());
						}
						
						AmnysCraft.sendMessage(player, "§7Settato con successo gli stivaletti del mob.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("damage"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("damage", Double.parseDouble(args[2]));
						AmnysCraft.sendMessage(player, "§7Settata con successo il danno del mob §a" + args[1].toLowerCase() + " §7a §e" + Double.toString(Double.parseDouble(args[2])) + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete"))
					{
						AmnysCraft.getConfigh().set(args[1].toLowerCase(), null);
						AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").set(args[1].toLowerCase(), null);
						AmnysCraft.sendMessage(player, "§7Eliminato con successo il mob custom §a" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("list"))
					{
						AmnysCraft.sendMessage(player, "§7Ecco qua la lista di tutti i custom mob:");
						
						int i = 0;
						
						for (String s: getSections(AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs")))
						{
							i += 1;
							AmnysCraft.sendMessage(player, "§e" + Integer.toString(i) + "§7) §a" + s.toLowerCase());
						}
						
						return true;
					}
					else if (args[0].equalsIgnoreCase("help"))
					{
						AmnysCraft.sendMessage(player, "§7Ecco qua la lista dei comandi: ");
						AmnysCraft.sendMessage(player, "§c/amnysmob §ecreate §a<nome-mob>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §etype §a<nome-mob> <tipo-mob>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §espawn §a<nome-mob>");
						//AmnysCraft.sendMessage(player, "§c/amnysmob §edrop §a<add/remove> <nome-mob> <nome-drop> §ehand §a<probabilità>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §edrop §a<nome-mob> §ehand §a<probabilità>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §ename §a<nome-mob> <nome-custom>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §ehealth §a<nome-mob> <vita-attuale>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §emaxhealth §a<nome-mob> <vita-massima>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §edamage §a<nome-mob> <danno-a-colpo>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §ehelmet §a<nome-mob> §ehand");
						AmnysCraft.sendMessage(player, "§c/amnysmob §echestplate §a<nome-mob> §ehand");
						AmnysCraft.sendMessage(player, "§c/amnysmob §eleggings §a<nome-mob> §ehand");
						AmnysCraft.sendMessage(player, "§c/amnysmob §eboots §a<nome-mob> §ehand");
						AmnysCraft.sendMessage(player, "§c/amnysmob §ehand §a<nome-mob>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §edelete/remove §a<nome-mob>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §elist");
						AmnysCraft.sendMessage(player, "§c/amnysmob §enodamage §a<nome-mob> <true/false>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §eai §a<nome-mob> <true/false>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §espawner §a<nome-mob> <secondi>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §eegg §a<nome-mob>");
						AmnysCraft.sendMessage(player, "§c/amnysmob §emoney §a<nome-mob> <soldi>");
						return true;
					}
					else if (args[0].equalsIgnoreCase("nodamage"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("nodamage", Boolean.parseBoolean(args[2].toLowerCase()));
						AmnysCraft.sendMessage(player, "§7Settato con successo il no damage del mob §a" + args[1].toLowerCase() + " §7a §c" + args[2].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("ai"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						section.set("ai", Boolean.parseBoolean(args[2].toLowerCase()));
						AmnysCraft.sendMessage(player, "§7Settata con successo l'ai del mob §a" + args[1].toLowerCase() + " §7a §c" + args[2].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("spawner"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						
						try
						{
							section.set("spawner-timing", 20L * Integer.parseInt(args[2].toLowerCase()));
						}
						catch (Exception ex)
						{
							section.set("spawner-timing", 60L);
						}
						
						ItemStack theStack = new ItemStack(Material.MOB_SPAWNER, 1);
						ItemMeta itemMeta = theStack.getItemMeta();
						itemMeta.setDisplayName("§eCustom mob spawner §c> §3" + args[1].toLowerCase());
						theStack.setItemMeta(itemMeta);
						player.getInventory().addItem(theStack);
						AmnysCraft.sendMessage(player, "§7Ecco qua lo spawner del tuo mob custom.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("egg"))
					{
						ItemStack theStack = new ItemStack(Material.MONSTER_EGG, 1);
						ItemMeta itemMeta = theStack.getItemMeta();
						itemMeta.setDisplayName("§eCustom mob egg §c> §3" + args[1].toLowerCase());
						theStack.setItemMeta(itemMeta);
						player.getInventory().addItem(theStack);
						AmnysCraft.sendMessage(player, "§7Ecco qua l'uovo del tuo mob custom.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("money"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("amnys-mobs").getConfigurationSection(args[1].toLowerCase());
						String thing = args[2].toLowerCase();
						thing = thing.replace("-", " ");
						thing = thing.replace("_", " ");
						section.set("money", thing);
						AmnysCraft.sendMessage(player, "§7Settati con successo i soldi che darà il mob al player quando sarà morto.");
						return true;
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Format del comando non valida.");
						return true;
					}
					
					return true;
				}
				catch (Exception ex)
				{
					ex.printStackTrace();
					AmnysCraft.sendMessage(player, "§7Impossibile eseguire il comando. Per favore, riprova in seguito.");
					return true;
				}
			}
			else
			{
				AmnysCraft.sendMessage(player, "§7Non hai il permesso.");
				return true;
			}
		}
		else
		{
			sender.sendMessage("§fDevi essere un player per poter utilizzare questo comando.");
			return true;
		}
	}
	
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
}