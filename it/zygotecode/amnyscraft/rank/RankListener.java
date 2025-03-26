package it.zygotecode.amnyscraft.rank;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import it.zygotecode.amnyscraft.AmnysCraft;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class RankListener implements Listener
{
	@EventHandler(priority = EventPriority.MONITOR)
	public void onCommand(PlayerCommandPreprocessEvent e)
	{
		if (!AmnysCraft.getRankManager().hasRank(e.getPlayer()) && PermissionsEx.getPermissionManager().getUser(e.getPlayer()).inGroup("Oplita") && e.getMessage().toLowerCase().startsWith("rankup"))
		{
			AmnysCraft.sendMessage(e.getPlayer(), "§7Ci dispiace, ma non puoi più salire di rank. Esegui il comando §c'/limbo' §7per scegliere se essere §aTroiano §7o §aMedea§7.");
			e.setCancelled(true);
		}
		else if (!PermissionsEx.getPermissionManager().getUser(e.getPlayer()).inGroup("owner") && (e.getMessage().toLowerCase().startsWith("wea") || e.getMessage().toLowerCase().startsWith("/wea")))
		{
			AmnysCraft.sendMessage(e.getPlayer(), "§7Devi essere di ruolo §aOwner §7per poter utilizzare questo comando.");
			e.setCancelled(true);
		}
	}
}