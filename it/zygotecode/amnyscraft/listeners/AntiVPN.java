package it.zygotecode.amnyscraft.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class AntiVPN implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onLogin(PlayerLoginEvent event)
	{
		/*try
		{
			if (VPNUtils.isVPN(event.getPlayer().getAddress().getAddress().getHostAddress()))
			{
				event.setKickMessage("§9Security> §7Ci dispiace, ma non è consentito l'uso di una VPN.");
				event.getPlayer().kickPlayer("§9Security> §7Ci dispiace, ma non è consentito l'uso di una VPN.");
			}
		}
		catch (Exception ex)
		{
			
		}*/
	}
}