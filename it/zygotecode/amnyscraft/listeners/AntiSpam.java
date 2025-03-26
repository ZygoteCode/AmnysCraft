package it.zygotecode.amnyscraft.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import it.zygotecode.amnyscraft.AmnysCraft;
import it.zygotecode.amnyscraft.utils.AmnysVariables;
import it.zygotecode.amnyscraft.utils.Messagim;

@SuppressWarnings("deprecation")
public class AntiSpam implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onChatMessage(PlayerChatEvent event)
	{
		if (!AmnysVariables.blockedMessages.contains(event.getPlayer().getName().toLowerCase()))
		{
			AmnysVariables.blockedMessages.add(event.getPlayer().getName().toLowerCase());
			String tMsg = "";
			
			try
			{
				if (AmnysVariables.lastMessage.size() > 0)
				{
					for (Messagim missigim: AmnysVariables.lastMessage)
					{
						if (missigim.getPlayerName().equals(event.getPlayer().getName().toLowerCase()))
						{
							AmnysVariables.lastMessage.remove(missigim);
							tMsg = missigim.getMessage().toLowerCase();
						}
					}
				}
			}
			catch (Exception ex)
			{
				
			}
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(AmnysCraft.getPlugin(), new BukkitRunnable()
					{
						@Override
						public void run()
						{
							AmnysVariables.blockedMessages.remove(event.getPlayer().getName().toLowerCase());
						}
					}, 40L);	
			
			String msg = event.getMessage();
			msg = msg.toLowerCase();
			tMsg = tMsg.toLowerCase();
			
			Messagim messagim = new Messagim(event.getPlayer().getName().toLowerCase(), msg);
			AmnysVariables.lastMessage.add(messagim);
			
			if (msg.equals(tMsg))
			{
				event.getPlayer().sendMessage("§9Security> §7Per favore, evita di inviare lo stesso messaggio di prima.");
				event.setMessage("");
				event.setCancelled(true);
				return;
			}
			
			if (msg.startsWith("what's 2+2? ["))
			{
				event.setCancelled(true);
				event.setMessage("");
				
				for (Player player: Bukkit.getOnlinePlayers())
				{
					if (player.isOp())
					{
						player.sendMessage("§9Security> §7Il player §a" + event.getPlayer().getName() + " §7sta usando l'Aristois Spambot.");
					}
				}
				
				return;
			}
		}
		else
		{
			event.getPlayer().sendMessage("§9Security> §7Per favore, aspetta qualche secondo prima di poter scrivere nuovamente in chat.");
			event.setMessage("");
			event.setCancelled(true);
			return;
		}	
	}
}