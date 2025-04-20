package it.zygotecode.amnyscraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result;

import it.zygotecode.amnyscraft.AmnysCraft;

import org.bukkit.event.player.PlayerLoginEvent;

public class AntiBot implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(PlayerLoginEvent event)
	{
		int ips = 0;
		
		for (Player player: Bukkit.getOnlinePlayers())
		{
			if (!event.getPlayer().equals(player))
			{
				try
				{
					if (player.getName().toLowerCase().equalsIgnoreCase(event.getPlayer().getName().toLowerCase()) || player.getUniqueId().equals(event.getPlayer().getUniqueId()))
					{
						event.setKickMessage("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
						event.getPlayer().kickPlayer("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
						event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
						event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
					}
					
					if (event.getPlayer().getAddress().getHostString().equals(player.getAddress().getHostString()))
					{
						ips += 1;
					}
				}
				catch (Exception ex)
				{
					
				}
			}
		}
		
		if (ips > 2)
		{
			event.setKickMessage("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
			event.getPlayer().kickPlayer("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
			event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
			event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
		}
		
		/*if (AmnysVariables.theIPs.size() > 0)
		{
			boolean contains = false;
			
			for (TheIP theIP: AmnysVariables.theIPs)
			{
				if (theIP.getIP().equals(event.getPlayer().getAddress().getHostString()) && !(theIP.getName().toLowerCase().equalsIgnoreCase(event.getPlayer().getName().toLowerCase())))
				{
					contains = true;
					int strike = theIP.getStrike();
					AmnysVariables.theIPs.remove(theIP);
					AmnysVariables.theIPs.add(new TheIP(event.getPlayer().getAddress().getHostString(), strike + 1, event.getPlayer().getName().toLowerCase()));
				}
			}
			
			if (!contains)
			{
				AmnysVariables.theIPs.add(new TheIP(event.getPlayer().getAddress().getHostString(), 1, event.getPlayer().getName().toLowerCase()));
			}
		}
		else
		{
			AmnysVariables.theIPs.add(new TheIP(event.getPlayer().getAddress().getHostString(), 1, event.getPlayer().getName().toLowerCase()));
		}
		
		if (AmnysVariables.theIPs.size() > 0)
		{
			for (TheIP theIP: AmnysVariables.theIPs)
			{
				if (theIP.getName().toLowerCase().equalsIgnoreCase(event.getPlayer().getName().toLowerCase()) && theIP.getStrike() > 4)
				{
					event.setKickMessage("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
					event.getPlayer().kickPlayer("§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
					event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
					event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "§9Security> §7La tua identità è sospetta. Ti preghiamo di cambiare il tuo nickname.");
					ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
					String command = "/ipban " + theIP.getName() + " -s Attacco bot";
					Bukkit.dispatchCommand(console, command);
					return;
				}
			}
		}*/
	}
	
	@SuppressWarnings("unchecked")
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onLogin(AsyncPlayerPreLoginEvent e)
	{
		try
		{
			if (!AmnysCraft.getConfigh().getConfigurationSection("allowed-players").isSet(e.getName().toLowerCase()))
			{
				AmnysCraft.getListp().put(e.getName().toLowerCase(), true);
				
				if ((boolean) AmnysCraft.getListp().get(e.getName().toLowerCase()))
				{
					AmnysCraft.getConfigh().getConfigurationSection("allowed-players").set(e.getName().toLowerCase(), true);
					AmnysCraft.getListp().put(e.getName(), false);
					String kickMessage = "§9Security> §7Per favore, rientra nuovamente nel server.";
					kickMessage = kickMessage.replace("&", "§");
					e.disallow(Result.KICK_OTHER, kickMessage);
				}
			}
			
			if (e.getKickMessage().toLowerCase().contains("please connect with"))
			{
				e.setLoginResult(AsyncPlayerPreLoginEvent.Result.ALLOWED);
				e.allow();
			}
		}
		catch (Exception ex)
		{
			
		}
	}
}