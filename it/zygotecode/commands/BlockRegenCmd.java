package it.zygotecode.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.CompleteLoc;

public class BlockRegenCmd implements CommandExecutor
{
	@SuppressWarnings({ "deprecation" })
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				if (player.isOp())
				{
					if (args[0].equalsIgnoreCase("help"))
					{
						AmnysCraft.sendMessage(player, "§7Ecco qua la lista dei comandi:");
						AmnysCraft.sendMessage(player, "§e1§7) §c/blockregen §ecreate §a<nome-regione>");
						AmnysCraft.sendMessage(player, "§e2§7) §c/blockregen §edelete/remove §a<nome-regione>");
						AmnysCraft.sendMessage(player, "§e3§7) §c/blockregen §eregen §a<nome-regione>");
						AmnysCraft.sendMessage(player, "§e4§7) §c/blockregen §ewand");
						AmnysCraft.sendMessage(player, "§e5§7) §c/blockregen §elist");
						return true;
					}
					else if (args[0].equalsIgnoreCase("create"))
					{
						AmnysCraft.getConfigh().getConfigurationSection("block-regen").createSection(args[1].toLowerCase());
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("block-regen").getConfigurationSection(args[1].toLowerCase());
						CompleteLoc pos1 = null;
						CompleteLoc pos2 = null;
						
						for (CompleteLoc position: AmnysVariables.positions1)
						{
							if (position.getPlayer().equals(player))
							{
								pos1 = position;
								AmnysVariables.positions1.remove(position);
								break;
							}
						}
						
						for (CompleteLoc position: AmnysVariables.positions2)
						{
							if (position.getPlayer().equals(player))
							{
								pos2 = position;
								AmnysVariables.positions1.remove(position);
								break;
							}
						}
						
						section.set("pos1", pos1.getLocation().getBlockX() + " " + pos1.getLocation().getBlockY() + " " + pos1.getLocation().getBlockZ() + " " + pos1.getLocation().getWorld().getName());
						section.set("pos2", pos2.getLocation().getBlockX() + " " + pos2.getLocation().getBlockY() + " " + pos2.getLocation().getBlockZ() + " " + pos2.getLocation().getWorld().getName());
						ArrayList<String> blocks = new ArrayList<String>();
						Location loc1 = pos1.getLocation();
						Location loc2 = pos2.getLocation();
						
						for (Block block: blocksFromTwoPoints(loc1, loc2))
						{
							Material[] deniedTypes = {Material.SIGN, Material.CHEST, Material.FURNACE, Material.BURNING_FURNACE, Material.DISPENSER, Material.DROPPER};
							boolean canProceed = true;
							
							for (Material material: deniedTypes)
							{
								if (block.getType().equals(material))
								{
									canProceed = false;
									break;
								}
							}
							
							String lol = Byte.toString(block.getData());
							
							if (canProceed)				
							{
								blocks.add("normal " + Integer.toString(block.getTypeId()) + " " + block.getX() + " " + block.getY() + " " + block.getZ() + " " + block.getWorld().getName() + " " + lol);	
							}
							else
							{
								if (block.getType().equals(Material.SIGN))
								{
									BlockState state = block.getState();
									Sign sign = (Sign) state;
									String signText = "";
									
									for (String s: sign.getLines())
									{
										if (signText == "")
										{
											signText = s;
										}
										else
										{
											signText += "|" + s;
										}
									}
									
									blocks.add("sign " + Integer.toString(block.getTypeId()) + " " + block.getX() + " " + block.getY() + " " + block.getZ() + " " + block.getWorld().getName() + " " + lol + " " + signText);
								}
								else
								{
									blocks.add("normal " + Integer.toString(block.getTypeId()) + " " + block.getX() + " " + block.getY() + " " + block.getZ() + " " + block.getWorld().getName() + " " + lol);
								}
							}
						}
						
						section.set("blocks", blocks);
						AmnysCraft.sendMessage(player, "§7Creata con successo la region §c" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("remove") || args[0].equalsIgnoreCase("delete"))
					{
						AmnysCraft.getConfigh().getConfigurationSection("block-regen").set(args[1].toLowerCase(), null);
						AmnysCraft.sendMessage(player, "§7Eliminata con successo la region §c" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("regen"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("block-regen").getConfigurationSection(args[1].toLowerCase());
						@SuppressWarnings("unchecked")
						ArrayList<String> blocks = (ArrayList<String>) section.getList("blocks");
						
						for (String s: blocks)
						{
							String[] lol = s.split("\\s+");
							
							if (lol[0].toLowerCase().equalsIgnoreCase("normal"))
							{
								int typeId = Integer.parseInt(lol[1]);
								int x = Integer.parseInt(lol[2]);
								int y = Integer.parseInt(lol[3]);
								int z = Integer.parseInt(lol[4]);
								World world = Bukkit.getWorld(lol[5]);
								String omegaLul = lol[6];
								Byte blockData = Byte.parseByte(omegaLul);
								Block block = null;
								
								try
								{
									block = world.getBlockAt(new Location(world, x, y, z));
									
									if (block != null)
									{
										block.setTypeId(typeId);
										block.setData(blockData);
									}
								}
								catch (Exception ex)
								{
									
								}
							}
							else if (lol[0].toLowerCase().equalsIgnoreCase("sign"))
							{
								int typeId = Integer.parseInt(lol[1]);
								int x = Integer.parseInt(lol[2]);
								int y = Integer.parseInt(lol[3]);
								int z = Integer.parseInt(lol[4]);
								World world = Bukkit.getWorld(lol[5]);
								String omegaLul = lol[6];
								Byte blockData = Byte.parseByte(omegaLul);
								Block block = null;
								
								try
								{
									block = world.getBlockAt(new Location(world, x, y, z));
									
									if (block != null)
									{
										block.setTypeId(typeId);
										block.setData(blockData);
										BlockState state = block.getState();
										Sign sign = (Sign) state;
										String signTextBasic = "";
										signTextBasic = s.replace(lol[0] + " " + lol[1] + " " + lol[2] + " " + lol[3] + " " + lol[4] + " " + lol[5] + " " + lol[6] + " " , "");
										ArrayList<String> lines = new ArrayList<String>();
										
										for (String si: signTextBasic.split("\\|"))
										{
											lines.add(si);
										}
										
										for (int im = 0; im < lines.size(); im++)
										{
											sign.setLine(im, lines.get(im));
										}
										
										sign.update(true);
									}
								}
								catch (Exception ex)
								{
									
								}
							}
						}
						
						AmnysCraft.sendMessage(player, "§7Rigenerata con successo la region §c" + args[1].toLowerCase() + "§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("wand"))
					{
						ItemStack stack = new ItemStack(Material.STICK, 1);
						ItemMeta meta = stack.getItemMeta();
						meta.setDisplayName("§cBlockRegen Wand");
						stack.setItemMeta(meta);
						player.getInventory().addItem(stack);
						AmnysCraft.sendMessage(player, "§7Hai ottenuto con successo la wand del §cBlockRegen§7.");
						return true;
					}
					else if (args[0].equalsIgnoreCase("list"))
					{
						ConfigurationSection section = AmnysCraft.getConfigh().getConfigurationSection("block-regen");
						AmnysCraft.sendMessage(player, "§7Ecco qua la lista di tutte le region:");
						
						for (int i = 0; i < getSections(section).size(); i++)
						{
							AmnysCraft.sendMessage(player, "§e" + Integer.toString(i + 1) + "§7) §c" + getSections(section).get(i));
						}
						
						return true;
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Format del comando non valida.");
						return true;
					}
				}
				else
				{
					AmnysCraft.sendMessage(player, "§7Non hai il permesso.");
					return true;
				}
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
			sender.sendMessage("§fDevi essere un player per poter utilizzare questo comando.");
			return true;
		}
	}
	
	public static List<Block> blocksFromTwoPoints(Location loc1, Location loc2)
	{
		List<Block> blocks = new ArrayList<Block>();
		
		int topBlockX = (loc1.getBlockX() < loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		int bottomBlockX = (loc1.getBlockX() > loc2.getBlockX() ? loc2.getBlockX() : loc1.getBlockX());
		
		int topBlockY = (loc1.getBlockY() < loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		int bottomBlockY = (loc1.getBlockY() > loc2.getBlockY() ? loc2.getBlockY() : loc1.getBlockY());
		
		int topBlockZ = (loc1.getBlockZ() < loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		int bottomBlockZ = (loc1.getBlockZ() > loc2.getBlockZ() ? loc2.getBlockZ() : loc1.getBlockZ());
		
		for (int x = bottomBlockX; x <= topBlockX; x++)
		{
			for (int z = bottomBlockZ; z <= topBlockZ; z++)
			{
				for (int y = bottomBlockY; y <= topBlockY; y++)
				{
					Block block = loc1.getWorld().getBlockAt(x, y, z);
					blocks.add(block);
				}
			}
		}
		
		return blocks;
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