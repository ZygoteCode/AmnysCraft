package it.zygotecode.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import it.zygotecode.amnyscraft.AmnysCraft;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class LimboCmd implements CommandExecutor
{
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
	{
		if (sender instanceof Player)
		{
			Player player = (Player) sender;
			
			try
			{
				if (!AmnysCraft.getRankManager().hasRank(player))
				{
					if (PermissionsEx.getPermissionManager().getUser(player).inGroup("Oplita") && AmnysCraft.getQuestManager().hasCompletedQuest(player, 34))
					{
						ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
						String command = "warp Limbo " + player.getName();
						Bukkit.dispatchCommand(console, command);
						AmnysCraft.sendMessage(player, "§7Bene, qui potrai scegliere la tua classe. Troiano o Medea?");
						return true;
					}
					else
					{
						AmnysCraft.sendMessage(player, "§7Devi essere per forza rank Oplita per poter utilizzare questo comando.");
						return true;
					}
				}
				else
				{
					AmnysCraft.sendMessage(player, "§7Hai già scelto una classe e non puoi nuovamente utilizzare questo comando.");
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