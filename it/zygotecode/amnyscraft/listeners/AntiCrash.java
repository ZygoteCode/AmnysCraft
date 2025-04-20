package it.zygotecode.amnyscraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class AntiCrash implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent event)
	{
		if (event.getMessage().toLowerCase().startsWith("//calc") || event.getMessage().toLowerCase().startsWith("/calc"))
		{
			event.setCancelled(true);
			event.getPlayer().abandonConversation(null);
		}
	}
}