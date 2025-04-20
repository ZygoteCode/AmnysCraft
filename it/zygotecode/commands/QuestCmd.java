package it.zygotecode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class QuestCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			AmnysCraft.getQuestManager().fixPlayer(player);
			
			try
			{
				if (args[0].equalsIgnoreCase("list"))
				{
					AmnysCraft.sendMessage(player, "§7Ecco qua la lista di tutte le quest:");
					
					if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Tetes"))
					{
						AmnysCraft.sendMessage(player, "§e1§7) Portami 500 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 1) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e2§7) Portami 4 blocchi di ferro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 2) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e3§7) Portami 2 blocchi di carbone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 3) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e4§7) Portami 2 blocchi di redstone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 4) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Agoghe"))
					{
						AmnysCraft.sendMessage(player, "§e5§7) Portami 600 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 5) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e6§7) Portami 2 blocchi d'oro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 6) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e7§7) Portami 16 pezzi di carbone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 7) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e8§7) Portami 16 mele. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 8) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e9§7) Portami 5 diamanti. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 9) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Iren"))
					{
						AmnysCraft.sendMessage(player, "§e10§7) Portami 600 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 10) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e11§7) Portami 6 blocchi di redstone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 11) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e12§7) Portami 24 pezzi di carbone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 12) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e13§7) Portami 2 pezzi di lana rossa. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 13) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e14§7) Portami 1 pezzo di lana nera. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 14) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Krypteia"))
					{
						AmnysCraft.sendMessage(player, "§e15§7) Portami 650 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 15) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e16§7) Portami 1 blocco di diamante. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 16) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e17§7) Portami 6 blocchi di ferro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 17) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e18§7) Portami 24 lingotti di oro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 18) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e19§7) Portami 2 secchi di lava. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 19) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Spartiato"))
					{
						AmnysCraft.sendMessage(player, "§e20§7) Portami 750 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 20) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e21§7) Portami 6 blocchi di diamante. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 21) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e22§7) Portami 5 pezzi di diamante. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 22) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e23§7) Portami 1 incudine. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 23) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e24§7) Portami 15 librerie. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 24) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e25§7) Portami 10 lingotti d'oro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 25) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e26§7) Portami 16 meloni. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 26) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e27§7) Portami 20 semi. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 27) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Oplita"))
					{
						AmnysCraft.sendMessage(player, "§e28§7) Portami 950 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 28) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e29§7) Portami 16 pezzi di mattoni di pietra. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 29) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e30§7) Portami 2 secchi di lava. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 30) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e31§7) Portami 2 secchi di acqua. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 31) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e32§7) Portami 64 pezzi di pane. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 32) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e33§7) Portami 30 pezzi di grano. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 33) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e34§7) Portami 2 balle di fieno. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 34) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Troiano") || PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Medea"))
					{
						AmnysCraft.sendMessage(player, "§e35§7) Portami 1050 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 35) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e36§7) Portami 1 zappa di diamante. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 36) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e37§7) Portami 32 pezzi di grano. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 37) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e38§7) Portami 6 balle di fieno. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 38) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e39§7) Portami 1 sella. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 39) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e40§7) Portami 10 pezzi di vetro colorato. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 40) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e41§7) Portami 16 pezzi di argilla. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 41) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Pretendente") || PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Veggente"))
					{
						AmnysCraft.sendMessage(player, "§e42§7) Portami 1050 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 42) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e43§7) Portami 16 nether wart. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 43) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e44§7) Portami 64 netherrack. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 44) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e45§7) Portami 16 di quarzo. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 45) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e46§7) Portami 32 pezzi di polvere da sparo. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 46) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Enea") || PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Amazzone"))
					{
						AmnysCraft.sendMessage(player, "§e47§7) Portami 2050 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 47) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e48§7) Portami 64 pezzi di quartz. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 48) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e49§7) Portami 32 pezzi di netherwart. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 49) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e50§7) Portami 32 rotten flesh. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 50) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e51§7) Portami 16 pezzi di ossa. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 51) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e52§7) Portami 32 gunpowder. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 52) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e53§7) Portami 16 blocchi di ferro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 53) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e54§7) Portami 16 cactus. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 54) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e55§7) Portami 10 biscotti. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 55) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e56§7) Portami 32 pezzi di zucchero. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 56) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Chanson") || PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Calcante"))
					{
						AmnysCraft.sendMessage(player, "§e57§7) Portami 3050 dollari. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 56) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e58§7) Portami 32 pezzi di endstone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 57) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e59§7) Portami 10 blocchi di diamanti. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 58) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e60§7) Portami 64 pezzi di lapis. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 59) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e61§7) Portami 64 pezzi di redstone. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 60) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e62§7) Portami 128 pezzi di terra. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 61) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e63§7) Portami 32 pezzi di gravel. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 62) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e64§7) Portami 64 gunpowder. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 63) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e65§7) Portami 24 blocchi di ferro. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 64) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e66§7) Portami 32 cactus. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 65) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e67§7) Portami 32 pezzi di carta. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 66) ? "§a§l✓" : "§c§l✘"));
						AmnysCraft.sendMessage(player, "§e68§7) Portami 24 ossa. " + (AmnysCraft.getQuestManager().hasCompletedQuest(player, 67) ? "§a§l✓" : "§c§l✘"));
						return true;
					}
					else if (PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Mythos") || PermissionsEx.getPermissionManager().getUser(player.getName()).inGroup("Circe"))
					{
						AmnysCraft.sendMessage(player, "§7Nessuna quest disponibile.");
						return true;
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Nessuna quest disponibile.");
						return true;
					}
				}
				else if (args[0].equalsIgnoreCase("complete"))
				{
					int id = Integer.parseInt(args[1]);
					
					if (AmnysCraft.getQuestManager().hasCompletedQuest(player, id))
					{
						AmnysCraft.sendMessage(player, "§7Hai già completato questa quest.");
						return true;
					}
					else
					{
						/*if (AmnysCraft.getQuestManager().whatNeedToComplete(player) == (id - 1))
						{
							if (AmnysCraft.getQuestManager().canCompleteQuest(player, id))
							{
								AmnysCraft.getQuestManager().completeQuest(player, id);
								AmnysCraft.sendMessage(player, "§7Hai completato con successo questa quest!");
								return true;
							}
							else
							{
								AmnysCraft.sendMessage(player, "§7Devi soddisfare i requisiti necessari per completare questa quest.");
								return true;
							}
						}
						else
						{
							AmnysCraft.sendMessage(player, "§7Devi prima completare una o altre quest prima di giungere a questa.");
							return true;
						}*/
						
						if (AmnysCraft.getQuestManager().canCompleteQuest(player, id))
						{
							boolean can = false;
							
							if (id == 1)
							{
								can = true;
							}
							else
							{
								if (AmnysCraft.getQuestManager().hasCompletedQuest(player, id - 1))
								{
									can = true;
								}
							}
							
							if (can)
							{
								AmnysCraft.getQuestManager().completeQuest(player, id);
								AmnysCraft.sendMessage(player, "§7Hai completato con successo questa quest!");
								return true;
							}
							else
							{
								AmnysCraft.sendMessage(player, "§7Devi prima completare una o altre quest prima di giungere a questa.");
								return true;
							}
						}
						else
						{
							AmnysCraft.sendMessage(player, "§7Devi soddisfare i requisiti necessari per completare questa quest.");
							return true;
						}
					}
				}
				else
				{
					AmnysCraft.sendMessage(player, "§7Format del comando non valida.");
					return true;
				}
			}
			catch (Exception ex)
			{
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
}