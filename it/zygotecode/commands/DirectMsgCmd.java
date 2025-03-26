package it.zygotecode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class DirectMsgCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		boolean canProceed = false;
		
		if (sender instanceof ConsoleCommandSender)
		{
			canProceed = true;
		}
		else if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			if (player.hasPermission("*") || player.isOp() || player.hasPermission("amnyscraft.*") || player.hasPermission("amnyscraft.directmsg"))
			{
				canProceed = true;
			}
		}
		
		if (canProceed)
		{
			try
			{
				Player player = Bukkit.getPlayer(args[0]);
				String total = "";
				boolean ignored = false;
				
				for (String s: args)
				{
					if (!ignored)
					{
						ignored = true;
						continue;
					}
					
					if (total == "")
					{
						total = s;
					}
					else
					{
						total += " " + s;
					}
				}
				
				total = total.replace("&", "§");
				
				player.sendMessage(total);
				return true;
			}
			catch (Exception ex)
			{
				sender.sendMessage("§9AmnysCraft> §7Impossibile eseguire il comando.");
				return true;
			}
		}
		else
		{
			return true;
		}
	}
}