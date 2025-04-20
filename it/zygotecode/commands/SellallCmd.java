package it.zygotecode.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;

public class SellallCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				double gained = AmnysCraft.getCreativeShop().sellAll(player);
				AmnysCraft.sendMessage(player, "§7Hai venduto tutti gli items presenti nel tuo inventario che era possibile vendere e hai guadagnato esattamente §e$" + gained + "§7.");
				return true;
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