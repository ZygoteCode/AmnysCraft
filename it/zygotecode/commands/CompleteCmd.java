package it.zygotecode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;

public class CompleteCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				if (AmnysCraft.getCreativeShop().isPlayerPresent(player))
				{
					if (AmnysCraft.getCreativeShop().isWaitingPlayerPresent(player.getName().toLowerCase()))
					{
						String lol = AmnysCraft.getCreativeShop().closeShop(player);
						String[] miao = lol.split("\\s+");
						
						if (miao[0].toLowerCase().equalsIgnoreCase("true"))
						{
							AmnysCraft.sendMessage(player, "§7Grazie per aver effettuato i tuoi acquisti! Hai speso esattamente §e$" + miao[1] + "§7.");
							return true;
						}
						else
						{
							AmnysCraft.sendMessage(player, "§7Il saldo sul tuo conto non è sufficiente per completare l'acquisto! Hai bisogno di almeno §e$" + miao[1] + " §7per poter effettuare l'acquisto correttamente.");
							return true;
						}	
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Sei sicuro di voler effettuare quest'acquisto? Spenderai un totale di §e$0.0§7. Per procedere, digita nuovamente il comando §c'/complete'§7.");
						AmnysCraft.getCreativeShop().addWaitingPlayer(player.getName().toLowerCase());
						return true;
					}
				}
				else
				{
					AmnysCraft.sendMessage(player, "§7Non stai effettuando alcun acquisto da completare!");
					return true;
				}
			}
			catch (Exception ex)
			{
				AmnysCraft.sendMessage(player, "§7Impossibile eseguire il comando. Per favore, riprova in seguito.");
				ex.printStackTrace();
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