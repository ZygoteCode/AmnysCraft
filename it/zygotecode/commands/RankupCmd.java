package it.zygotecode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.rank.RankType;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankupCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				if (args[0].equalsIgnoreCase("Troiano") && player.getWorld().getName().toLowerCase().equalsIgnoreCase("Limbo") && PermissionsEx.getPermissionManager().getUser(player).inGroup("Oplita") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 34))
				{
					AmnysCraft.getRankManager().addRank(player, RankType.MEDIEVAL);
					AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §aTroiano§7!");
					
					for (Player p: Bukkit.getOnlinePlayers())
					{
						AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato a §cTroiano§7!");
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "spawn " + player.getName();
					Bukkit.dispatchCommand(console, command);
					
					return true;
				}
				else if (args[0].equalsIgnoreCase("Medea") && player.getWorld().getName().toLowerCase().equalsIgnoreCase("Limbo") && PermissionsEx.getPermissionManager().getUser(player).inGroup("Oplita")  && AmnysCraft.getQuestManager().hasCompletedQuest(player, 34))
				{
					AmnysCraft.getRankManager().addRank(player, RankType.FANTASY);
					AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §aMedea§7!");
					
					for (Player p: Bukkit.getOnlinePlayers())
					{
						AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato a §cMedea§7!");
					}
					
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "spawn " + player.getName();
					Bukkit.dispatchCommand(console, command);
					
					return true;
				}
				else
				{
					AmnysCraft.sendMessage(player, "§7Non hai il permesso.");
					return true;
				}
			}
			catch (Exception ex)
			{
				try
				{
					if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Tètes") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 4))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Tètes");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Agoghè");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo ad §cAgoghè§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato ad §cAgoghè§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Agoghe") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 9))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Agoghe");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Iren");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo ad §cIren§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato ad §cIren§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Iren") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 14))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Iren");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Krypteia");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cKrypteia§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato ad §cKrypteia§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Krypteia") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 19))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Krypteia");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Spartiato");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cSpartiato§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato ad §cSpartiato§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Spartiato") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 27))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Spartiato");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Agoghè");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cOplita§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato con successo ad §cOplita§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Oplita") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 34))
					{
						AmnysCraft.sendMessage(player, "§7Per favore, esegui il comando §c'/limbo' §7per continuare.");
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Troiano") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 35))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Troiano");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Pretendente");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cPretendente§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato con successo ad §cPretedente§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Pretendente") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 42))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Pretendente");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Enea");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cEnea§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato con successo ad §cEnea§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Enea") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 47))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Enea");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Chanson");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cChanson§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato con successo ad §cChanson§7!");
						}
						
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Chanson") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 57))
					{
						PermissionsEx.getPermissionManager().getUser(player.getName()).removeGroup("Chanson");
						PermissionsEx.getPermissionManager().getUser(player.getName()).addGroup("Mythos");
						AmnysCraft.sendMessage(player, "§7Sei rankato con successo a §cMythos§7!");
						
						for (Player p: Bukkit.getOnlinePlayers())
						{
							AmnysCraft.sendMessage(p, "§7Il player §a" + player.getName() + " §7è rankato con successo a §cMythos§7!");
						}
						
						return true;
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Devi soddisfare i requisiti necessari per salire di grado. Fai §c'/quest list' §7 per vedere tutte le quest da completare.");
					}
					
					return true;
				}
				catch (Exception exception)
				{
					AmnysCraft.sendMessage(player, "§7Impossibile eseguire il comando. Per favore, riprova in seguito.");
					return true;
				}
			}
		}
		else
		{
			sender.sendMessage("§fDevi essere un player per poter utilizzare questo comando.");
			return true;
		}
	}
}