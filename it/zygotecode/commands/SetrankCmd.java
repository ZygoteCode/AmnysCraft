package it.zygotecode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.rank.RankType;

public class SetrankCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (!(sender instanceof Player))
		{
			try
			{
				if (args[1].toLowerCase().equals("medieval"))
				{
					AmnysCraft.getRankManager().addRank(Bukkit.getPlayer(args[0]), RankType.MEDIEVAL);
					sender.sendMessage("Impostato il rank di " + Bukkit.getPlayer(args[0]) + " a Medieval.");
					return true;
				}
				else if (args[1].toLowerCase().equals("fantasy"))
				{
					AmnysCraft.getRankManager().addRank(Bukkit.getPlayer(args[0]), RankType.FANTASY);
					sender.sendMessage("§fImpostato il rank di " + Bukkit.getPlayer(args[0]) + " a Fantasy.");
					return true;
				}
				else
				{
					sender.sendMessage("§fImpossibile eseguire il comando.");
					return true;
				}
			}
			catch (Exception ex)
			{
				sender.sendMessage("§fImpossibile eseguire il comando.");
				return true;
			}
		}
		else
		{
			if (sender instanceof Player)
			{
				AmnysCraft.sendMessage((Player) sender, "§7Il comando è solo ed esclusivamente utilizzabile dalla console!");
				return true;
			}
			else
			{
				sender.sendMessage("Il comando è solo ed esclusivamente utilizzabile dalla console!");
				return true;
			}
		}
	}
}